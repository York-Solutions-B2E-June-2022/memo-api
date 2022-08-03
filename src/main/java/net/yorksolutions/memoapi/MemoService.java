package net.yorksolutions.memoapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class MemoService {

    MemoRepository memoRepository;
    AccountRepository accountRepository;

    public MemoService(MemoRepository memoRepository, AccountRepository accountRepository){
        this.memoRepository = memoRepository;
        this.accountRepository = accountRepository;
    }

    public void createMemo(String text, Date date, String username, String password) {
        if (text == null || date == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Account> accountOpt = accountRepository.findByUsernameAndPassword(username, password);
        if (accountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Account account = accountOpt.get();

        Memo memo = new Memo(text, date, account);
        memoRepository.save(memo);

        account.memos.add(memo);
        accountRepository.save(account);

    }

    public ArrayList<Memo> getMemos(String username, String password) {
        Optional<Account> account = accountRepository.findByUsernameAndPassword(username, password);
        if (account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return memoRepository.findByCreatedBy(account.get());
    }
}
