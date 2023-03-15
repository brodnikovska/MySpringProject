package example.service.impl;

import example.dao.repository.EventRepository;
import example.dao.repository.TicketRepository;
import example.dao.repository.UserAccountRepository;
import example.dao.repository.UserRepository;
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
    private TicketRepository ticketRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EventRepository eventRepository;

    @Inject
    private UserAccountRepository userAccountRepository;

    @Override
    public BigDecimal getAmountOfMoney(long userId) {
        return userAccountRepository.findByUserId(userId).orElseThrow().getPrepaidMoney();
    }

    @Override
    public BigDecimal putMoneyToAccount(long userId, BigDecimal money) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).orElseThrow();
        BigDecimal newBalance = (userAccount.getPrepaidMoney()).add(money);
        userAccount.setPrepaidMoney(newBalance);
        userAccountRepository.save(userAccount);
        return userAccountRepository.findByUserId(userId).orElseThrow().getPrepaidMoney();
    }

    @Override
    public BigDecimal withdrawMoney(long userId, BigDecimal money) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId).orElseThrow();
        BigDecimal newBalance = (userAccount.getPrepaidMoney()).subtract(money);
        userAccount.setPrepaidMoney(newBalance);
        userAccountRepository.save(userAccount);
        return userAccountRepository.findByUserId(userId).orElseThrow().getPrepaidMoney();
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
