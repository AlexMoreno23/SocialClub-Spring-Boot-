package by.morunov.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Alex Morunov
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "app_user_id")
    private User user;

    public ConfirmToken(String token,
                        LocalDateTime createdAt,
                        LocalDateTime expiredAt,
                        User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
