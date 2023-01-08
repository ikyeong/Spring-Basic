package memo;

import jakarta.persistence.EntityManager;
import memo.repository.JdbcMemoRepository;
import memo.repository.JpaMemoRepository;
import memo.repository.MemoRepository;
import memo.repository.MemoryMemoRepository;
import memo.service.MemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private MemoRepository rep = new MemoryMemoRepository();
    DataSource dataSource;
    EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemoService memoService(){
        return new MemoService(memoRepository());
    }

    @Bean
    public MemoRepository memoRepository(){
        return new JpaMemoRepository(em);
//        return new JdbcMemoRepository(dataSource);
    }
}
