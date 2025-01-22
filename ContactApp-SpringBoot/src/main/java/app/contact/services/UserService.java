package app.contact.services;

import app.contact.domain.User;

public interface UserService {
	
	public static final Integer ROLE_ADMIN = 1;
	public static final Integer ROLE_USER = 2;
	
	public static final Integer LOGIN_STATUS_ACTIVE = 1;
	public static final Integer LOGIN_STATUS_BLOCKED = 2;

	public void register(User u);

	public User login(String username, String password);

	public void editProfile(User u);

	public void changeLoginStatus(Integer userId, Integer newStatus);

}
