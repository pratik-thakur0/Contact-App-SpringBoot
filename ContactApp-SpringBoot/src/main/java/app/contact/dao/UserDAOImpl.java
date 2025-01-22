package app.contact.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import app.contact.domain.User;
import app.contact.rm.UserRowMapper;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public void save(User u) {
		String sql = "INSERT INTO user(`name`, `phone`, `email`, `address`, `loginId`, `password`, `role`, `status`)"
				+ "VALUES(:name, :phone, :email, :address, :loginId, :password, :role, :status)";
		MapSqlParameterSource ps = new MapSqlParameterSource();
		ps.addValue("name", u.getName());
		ps.addValue("phone", u.getPhone());
		ps.addValue("email", u.getEmail());
		ps.addValue("address", u.getAddress());
		ps.addValue("loginId", u.getLoginId());
		ps.addValue("password", u.getPassword());
		ps.addValue("role", u.getRole());
		ps.addValue("status", u.getStatus());
		
		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, ps, kh);
		u.setUserId(kh.getKey().intValue()); 
		
	}

	@Override
	public void update(User u) {
		String sql = "UPDATE user SET name=:name, phone=:phone, email=:email, address=:address, "
				+ "loginId=:loginId, password=:password WHERE userId=:userId";
		MapSqlParameterSource ps = new MapSqlParameterSource();
		ps.addValue("name", u.getName());
		ps.addValue("phone", u.getPhone());
		ps.addValue("email", u.getEmail());
		ps.addValue("address", u.getAddress());
		ps.addValue("loginId", u.getLoginId());
		ps.addValue("password", u.getPassword());
		ps.addValue("userId", u.getUserId());
		namedParameterJdbcTemplate.update(sql, ps);
	}

	@Override
	public void delete(Integer userId) {
		namedParameterJdbcTemplate.update("DELETE FROM user WHERE userId=:userId", new MapSqlParameterSource("userId", userId));
	}

	@Override
	public User findById(Integer userId) {
		String sql = "SELECT `name`, `phone`, `email`, `address`, `loginId`, `password` FROM user WHERE userId=:userId";
		return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("userId", userId), new UserRowMapper());
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("SELECT `name`, `phone`, `email`, `address`, `loginId`, `password` FROM user", new UserRowMapper());	
	}

	@Override
	public List<User> findByProperty(String feildName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
