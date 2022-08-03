package net.yorksolutions.memoapi;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String username;
    String password;

    @OneToMany()
    Set<Memo> memos;

    public Account() {}
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
