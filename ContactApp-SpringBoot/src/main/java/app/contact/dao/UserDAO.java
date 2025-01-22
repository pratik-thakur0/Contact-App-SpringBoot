package app.contact.dao;

import java.util.List;

import app.contact.domain.User;

public interface UserDAO {

	public void save(User u);

	public void update(User u);

	public void delete(Integer userId);

	public User findById(Integer userId);

	public List<User> findAll();

	public List<User> findByProperty(String feildName, Object value);
}
