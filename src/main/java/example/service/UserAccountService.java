package example.service;

import example.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService {

    BigDecimal getAmountOfMoney(long userId);

    BigDecimal putMoneyToAccount(long userId, BigDecimal money);

    BigDecimal withdrawMoney(long userId, BigDecimal money);
    UserAccount createUserAccount(UserAccount userAccount);
    void deleteUserAccount(UserAccount userAccount);
}
