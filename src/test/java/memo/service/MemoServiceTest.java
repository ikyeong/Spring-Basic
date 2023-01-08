package memo.service;

import memo.domain.Memo;
import memo.repository.MemoRepository;
import memo.repository.MemoryMemoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemoServiceTest {

    MemoService memoService;
    MemoryMemoRepository rep;

    @BeforeEach
    public void before(){
        rep = new MemoryMemoRepository();
        memoService = new MemoService(rep);
    }

    @AfterEach
    public void after(){
        rep.clearAll();
    }

    @Test
    public void insert(){
        Memo memo = new Memo();
        memo.setTitle("hi");
        memo.setContents("contents");

        Long id = memoService.insert(memo).getId();

        Memo findMemo = memoService.findMemo(id).get();
        Assertions.assertThat(memo.getContents()).isEqualTo(findMemo.getContents());
    }
}
