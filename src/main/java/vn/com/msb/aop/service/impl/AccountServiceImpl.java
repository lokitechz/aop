package vn.com.msb.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.msb.aop.entity.Account;
import vn.com.msb.aop.exception.ResourceNotFoundException;
import vn.com.msb.aop.repository.AccountRepository;
import vn.com.msb.aop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account updateAccount(Account request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id: " + request.getAccountId()));

        account.setEmail(request.getEmail());
        account.setUserName(request.getUserName());
        account.setFullName(request.getFullName());

        accountRepository.save(account);

        return account;
    }

}
