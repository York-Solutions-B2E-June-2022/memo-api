package net.yorksolutions.memoapi;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String text;
    Date date;
    Boolean finished;

    @ManyToOne()
    Account createdBy;

    public Memo() {}
    public Memo(String text, Date date, Account createdBy) {
        this.text = text;
        this.date = date;
        this.createdBy = createdBy;
        this.finished = false;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Account getCreatedBy() {
        return createdBy;
    }
}
