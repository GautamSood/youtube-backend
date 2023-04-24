package com.example.youtube.Subscriber.SubscriberService;

import com.example.youtube.Subscriber.SubscriberDTO.SubscriberResponse;
import com.example.youtube.Subscriber.subscriber;
import com.example.youtube.Subscriber.subscriberRepository;
import com.example.youtube.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final subscriberRepository subscriberRepository;
    private final UserRepository userRepository;

    @Transactional
    public SubscriberResponse subs(long userId, long subsId) {

        var user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user1 not found"));
        var user2 = userRepository.findById(subsId).orElseThrow(() -> new UsernameNotFoundException("user2 not found"));


        if(subscriberRepository.findByUseridAndSubscriberid(user1,user2).isPresent()){
            var subs = subscriberRepository.findByUseridAndSubscriberid(user1,user2).orElseThrow(() -> new UsernameNotFoundException("subs not found"));
            var res = SubscriberResponse.builder().subscriber(subs).message("already subscribed").build();
            return res;
        }
        var subs = subscriber.builder()
                .subscriberid(user2)
                .userid(user1)
                .build();
        List<subscriber> subscriberList = user2.getSubscribers();
        subscriberList.add(subs);
        user2.setSubscribers(subscriberList);
        userRepository.save(user2);
        var res = SubscriberResponse.builder().subscriber(subs).message("subscribed").build();
        return res;
    }

    @Transactional
    public String unsubs(long userId, long subsId) {
        var user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user1 not found"));
        var user2 = userRepository.findById(subsId).orElseThrow(() -> new UsernameNotFoundException("user2 not found"));
        if(subscriberRepository.findByUseridAndSubscriberid(user1,user2).isPresent()){
            var subs = subscriberRepository.deleteByUseridAndAndSubscriberid(user1,user2);
            return "unsubed";
        }
        return "already unsubed";
    }
}
