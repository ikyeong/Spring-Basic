package memo.service;

import memo.domain.Memo;
import memo.repository.MemoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemoService {
    private final MemoRepository rep;

    public MemoService(MemoRepository rep) {
        this.rep = rep;
    }

    public Memo insert(Memo memo){
        return rep.save(memo);
    }

    public void delete(Long id){
        rep.deleteById(id);
    }

    public List<Memo> findMemos(){
        return rep.findAll();
    }

    public Optional<Memo> findMemo(Long id){
        return rep.findById(id);
    }

}
