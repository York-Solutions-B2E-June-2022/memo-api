package net.yorksolutions.memoapi;

import net.yorksolutions.memoapi.requestBodys.AccountAuthRequest;
import net.yorksolutions.memoapi.requestBodys.CreateMemoRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/memo")
public class MemoController {

    MemoService memoService;
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateMemoRequest body) {
        memoService.createMemo(body.text, body.date, body.username, body.password);
    }

    @GetMapping("/list")
    public ArrayList<Memo> list(@RequestBody AccountAuthRequest body) {
        return this.memoService.getMemos(body.username, body.password);
    }
}
