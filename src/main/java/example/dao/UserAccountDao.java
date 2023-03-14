package example.dao;

import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserId(Long userId);

    default BigDecimal getAmountOfMoney(User user) {
            UserAccount userAccount = findByUserId(user.getId()).orElseThrow();
            return userAccount.getPrepaidMoney();
    }

    default BigDecimal putMoneyToAccount(User user, BigDecimal money) {
            UserAccount userAccount = findByUserId(user.getId()).orElseThrow();
            BigDecimal newBalance = (userAccount.getPrepaidMoney()).add(money);
            userAccount.setPrepaidMoney(newBalance);
            save(userAccount);
            return userAccount.getPrepaidMoney();
    }

    default BigDecimal withdrawMoney(User user, BigDecimal money) {
            UserAccount userAccount = findByUserId(user.getId()).orElseThrow();
            BigDecimal newBalance = (userAccount.getPrepaidMoney()).subtract(money);
            userAccount.setPrepaidMoney(newBalance);
            save(userAccount);
            return userAccount.getPrepaidMoney();
    }
}
