package net.yorksolutions.memoapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    AccountRepository accountRepository;
    MemoRepository memoRepository;
    public AccountService(AccountRepository accountRepository, MemoRepository memoRepository) {
        this.accountRepository = accountRepository;
        this.memoRepository = memoRepository;
    }

    public void createAccount(String username, String password) {
        if (username.length() < 4 && password.length() < 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Account> existingAccount = accountRepository.findByUsername(username);
        if (existingAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Account account = new Account(username, password);
        accountRepository.save(account);
    }

    public Set<Memo> listMemo(String username, String password) {
        Optional<Account> account = accountRepository.findByUsernameAndPassword(username, password);
        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return account.get().memos;
    }
}
