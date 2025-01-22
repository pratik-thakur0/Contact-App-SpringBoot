package app.contact.dao;

import java.util.List;

import app.contact.domain.Contact;

public interface ContactDAO {
	
	public void save(Contact c);

	public void update(Contact c);

	public void delete(Integer contactId);

	public Contact findById(Integer contactId);
	
	public List<Contact> findUserContacts(Integer userId);

	public List<Contact> findAll();

	public List<Contact> findByProperty(String feildName, Object value);
	
}