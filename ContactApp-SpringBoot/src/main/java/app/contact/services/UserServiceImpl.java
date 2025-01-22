package app.contact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import app.contact.dao.UserDAO;
import app.contact.domain.User;
import app.contact.rm.UserRowMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public void register(User u) {
		userDAO.save(u);
	}

	@Override
	public User login(String username, String password) {
		String sql = "SELECT `userId`, `name`, `phone`, `email`, `address`, `loginId`, `role`, `status` FROM user WHERE loginId=:username AND password=:pwd";
		try {
			MapSqlParameterSource ps = new MapSqlParameterSource();
			ps.addValue("username", username);
			ps.addValue("pwd", password);
			User user = namedParameterJdbcTemplate.queryForObject(sql, ps, new UserRowMapper());
//			System.out.println(user.getStatus());
			return user;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void editProfile(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeLoginStatus(Integer userId, Integer newStatus) {
		// TODO Auto-generated method stub

	}

}
