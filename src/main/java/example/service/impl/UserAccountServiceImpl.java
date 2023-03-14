package example.service.impl;

import example.dao.EventDao;
import example.dao.TicketDao;
import example.dao.UserAccountDao;
import example.dao.UserDao;
import example.model.User;
import example.model.UserAccount;
import example.service.UserAccountService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Inject
    private TicketDao ticketDao;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    @Inject
    private UserAccountDao userAccountDao;

    @Override
    public BigDecimal getAmountOfMoney(long userId) {
        User user = userDao.findById(userId).orElseThrow();
        return userAccountDao.getAmountOfMoney(user);
    }

    @Override
    public BigDecimal putMoneyToAccount(long userId, BigDecimal money) {
        User user = userDao.findById(userId).orElseThrow();
        return userAccountDao.putMoneyToAccount(user, money);
    }

    @Override
    public BigDecimal withdrawMoney(long userId, BigDecimal money) {
        User user = userDao.findById(userId).orElseThrow();
        return userAccountDao.withdrawMoney(user, money);
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountDao.save(userAccount);
    }
}
