package com.example.youtube.Subscriber;

import com.example.youtube.Subscriber.subscriber;
import com.example.youtube.User.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface subscriberRepository extends JpaRepository<subscriber, Long> {

    List<subscriber> deleteByUseridAndAndSubscriberid(@NotNull(message = "null")User userid, @NotNull(message = "null") User subscriberid);
    Optional<subscriber> findByUseridAndSubscriberid(@NotNull(message = "null")User userid, @NotNull(message = "null") User subscriberid);
    @Override
    Optional<subscriber> findById(Long aLong);
}