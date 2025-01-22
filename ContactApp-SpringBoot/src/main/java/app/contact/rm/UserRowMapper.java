package app.contact.rm;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import app.contact.domain.User;

public class UserRowMapper implements RowMapper<User> {
	 
		@Override
		public User mapRow(ResultSet rs, int i) throws SQLException {
			// Fetch record form result set and binds its values to User object
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setName(rs.getString("name"));
			u.setPhone(rs.getString("phone"));
			u.setEmail(rs.getString("email"));
			u.setAddress(rs.getString("address"));
			u.setLoginId(rs.getString("loginId"));
			
			u.setRole(rs.getInt("role"));
			u.setStatus(rs.getInt("status"));
			return u;
		}
}
