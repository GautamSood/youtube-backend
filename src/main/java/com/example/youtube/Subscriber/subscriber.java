package com.example.youtube.Subscriber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.youtube.User.User;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "subscriber"
)
public class subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", nullable = false )
    private User subscriber_id;


}
