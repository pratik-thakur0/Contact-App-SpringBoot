package app.contact.services;

import java.util.List;

import app.contact.domain.Contact;

public interface ContactService {

	public void save(Contact c);

	public void upadte(Contact c);

	public void delete(Integer userId);

	public void bulkDelete(Integer[] contactIds);

	public Contact getContactById(Integer contactId);

	public List<Contact> search(Integer userId, String criteria);

	public List<Contact> findUserContacts(Integer userId);
	
}
