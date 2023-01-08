package memo.repository;

import memo.domain.Memo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryMemoRepository implements MemoRepository{

    private static List<Memo> memos = new ArrayList<>();
    private static Long id = 0L;

    @Override
    public Memo save(Memo memo) {
        memo.setId(++id);
        memos.add(memo);
        return memo;
    }

    @Override
    public void deleteById(Long id) {
        memos.remove(findById(id));
    }

    @Override
    public Optional<Memo> findById(Long id) {
        return memos.stream()
                .filter(memo -> memo.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Memo> findAll() {
        return memos;
    }

    public void clearAll(){
        memos.clear();
    }
}
