package memo.repository;

import memo.domain.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcMemoRepository implements MemoRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Memo save(Memo memo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("memo").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", memo.getTitle());
        parameters.put("contents", memo.getContents());
        Number id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        memo.setId(id.longValue());
        return memo;
    }

    @Override
    public void deleteById(Long id) {
        String query = "delete from memo where id = "+id;
        jdbcTemplate.execute(query);
//        jdbcTemplate.query("delete from memo where id = ?",memoRowMapper(),id);
    }

    @Override
    public Optional<Memo> findById(Long id) {
        List<Memo> rs = jdbcTemplate.query("select * from memo where id = ?", memoRowMapper(), id);
        return rs.stream().findAny();
    }

    @Override
    public List<Memo> findAll() {
        return jdbcTemplate.query("select * from memo", memoRowMapper());
    }

    private RowMapper<Memo> memoRowMapper(){
        return (rs, rowNum) -> {
            Memo memo = new Memo();
            memo.setId(rs.getLong("id"));
            memo.setTitle(rs.getString("title"));
            memo.setContents(rs.getString("contents"));
            return memo;
        };
    }
}
