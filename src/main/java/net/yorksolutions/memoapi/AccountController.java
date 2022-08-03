package net.yorksolutions.memoapi;
import net.yorksolutions.memoapi.requestBodys.AccountAuthRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public void create(@RequestBody AccountAuthRequest body) {
        this.accountService.createAccount(body.username, body.password);
    }

    @GetMapping("/listMemos")
    public Set<Memo> listMemo(@RequestBody AccountAuthRequest body) {
        return this.accountService.listMemo(body.username, body.password);
    }
}