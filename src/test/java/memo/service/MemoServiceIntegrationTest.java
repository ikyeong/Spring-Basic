package memo.service;

import memo.domain.Memo;
import memo.repository.MemoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemoServiceIntegrationTest {

    @Autowired MemoService memoService;
    @Autowired MemoRepository memoRepository;

    @Test
    public void insert(){
        //given
        Memo memo = new Memo("tit","con");
        //when
        Long id = memoService.insert(memo).getId();
        //then
        Memo findMemo = memoService.findMemo(id).get();
        assertThat(memo.getContents()).isEqualTo(findMemo.getContents());
    }

    @Test
    public void delete(){
        Memo memo = new Memo("title","contents");

        Long id = memoService.insert(memo).getId();
        memoService.delete(id);

        assertThat(memoService.findMemo(id).isPresent()).isFalse();
    }
}
