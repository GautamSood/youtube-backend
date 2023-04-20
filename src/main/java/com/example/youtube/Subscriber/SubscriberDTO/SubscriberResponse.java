package com.example.youtube.Subscriber.SubscriberDTO;

import com.example.youtube.Subscriber.subscriber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberResponse {
    private subscriber subscriber;
    private String message;
}
