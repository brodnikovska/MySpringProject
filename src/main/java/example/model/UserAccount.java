package example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @Column(name = "user_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(precision = 10, scale = 2)
    private BigDecimal prepaidMoney;

    public UserAccount(User user, BigDecimal prepaidMoney) {
        this.user = user;
        this.prepaidMoney = prepaidMoney;
    }

}
