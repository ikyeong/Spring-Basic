package memo.repository;

import memo.domain.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);
    void deleteById(Long id);
    Optional<Memo> findById(Long id);
    List<Memo> findAll();
}
