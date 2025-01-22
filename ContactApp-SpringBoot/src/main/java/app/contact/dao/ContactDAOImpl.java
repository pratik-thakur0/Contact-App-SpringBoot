package app.contact.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import app.contact.domain.Contact;
import app.contact.rm.ContactRowMapper;

@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

	@Override
	public void save(Contact c) {
		String sql = "INSERT INTO contact(`userId`, `name`, `phone`, `email`, `address`, `remark`) "
				+ "VALUES(:userId, :name, :phone, :email, :address, :remark)";
		MapSqlParameterSource ps = new MapSqlParameterSource();
		ps.addValue("userId", c.getUserId());
		ps.addValue("name", c.getName());
		ps.addValue("phone", c.getPhone());
		ps.addValue("email", c.getEmail());
		ps.addValue("address", c.getAddress());
		ps.addValue("remark", c.getRemark());
		
		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, ps, kh);
		
		c.setContactId(kh.getKey().intValue());
	}

	@Override
	public void update(Contact c) {
		String sql = "UPDATE contact SET name=:name, phone=:phone, email=:email, address=:address, remark=:remark WHERE contactId=:contactId";
		MapSqlParameterSource ps = new MapSqlParameterSource();
		ps.addValue("name", c.getName());
		ps.addValue("phone", c.getPhone());
		ps.addValue("email", c.getEmail());
		ps.addValue("address", c.getAddress());
		ps.addValue("remark", c.getRemark());
		ps.addValue("contactId", c.getContactId());
		namedParameterJdbcTemplate.update(sql, ps);
	}

	@Override
	public void delete(Integer contactId) {
		namedParameterJdbcTemplate.update("DELETE FROM contact WHERE contactId=:contactId", new MapSqlParameterSource("contactId", contactId));

	}

	@Override
	public Contact findById(Integer contactId) {
		String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remark` FROM contact WHERE contactId=:contactId";
		return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("contactId", contactId), new ContactRowMapper());
	}

	@Override
	public List<Contact> findAll() {
		return jdbcTemplate.query("SELECT `name`, `phone`, `email`, `address`, `remark` FROM contact", new ContactRowMapper());	
	}

	@Override
	public List<Contact> findByProperty(String feildName, Object feildValue) {
		String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remark` FROM contact WHERE "+ feildName +"=?";
		return jdbcTemplate.query(sql, new ContactRowMapper(), feildValue);
	}

	@Override
	public List<Contact> findUserContacts(Integer userId) {
		String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remark` FROM contact WHERE userId=:userId";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), new ContactRowMapper());
	}

}
