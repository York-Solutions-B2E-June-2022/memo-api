package net.yorksolutions.memoapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MemoRepository extends CrudRepository<Memo, Long> {
    ArrayList<Memo> findByCreatedBy(Account account);
}
