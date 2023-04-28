package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcMemoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Memo save(Memo memo) {
		String sql = "insert into memo values(?,?)";
		jdbcTemplate.update(sql, memo.getId(), memo.getText());
		return memo;
	}

	public List<Memo> findAll() {
		String sql = "select * from memo";
		return jdbcTemplate.query(sql, memoRowMapper());
	}

	public Optional<Memo> findById(int id) {            // Optional 사용하면 혹시 모를 널값을 처리해줌
		String sql = "select * from memo where id = ?";
		return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
	}

	private RowMapper<Memo> memoRowMapper() {
		//ResultSet
		//{id = 1, test = 'hello'}
		//ResultSet 을 Memo 라는 형식으로 mapping 해주는 작업
		return (rs, rowNum) -> new Memo(
				rs.getInt("id"),
				rs.getString("text"));
	}
}
