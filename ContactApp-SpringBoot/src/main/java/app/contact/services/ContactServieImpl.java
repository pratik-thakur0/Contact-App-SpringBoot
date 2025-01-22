package app.contact.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import app.contact.dao.ContactDAO;
import app.contact.domain.Contact;
import app.contact.rm.ContactRowMapper;

@Service
public class ContactServieImpl implements ContactService {

	@Autowired
	ContactDAO contactDAO;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Contact c) {
		contactDAO.save(c);
	}

	@Override
	public void upadte(Contact c) {
		contactDAO.update(c);

	}

	@Override
	public void delete(Integer contactId) {
		contactDAO.delete(contactId);
	}

	@Override
	public void bulkDelete(Integer[] contactIds) {
		String ids = StringUtils.arrayToCommaDelimitedString(contactIds);
		String sql = "DELETE FROM contact WHERE contactId IN("+ids+")";
		jdbcTemplate.update(sql);
	}

	@Override
	public Contact getContactById(Integer contactId) {
		return contactDAO.findById(contactId);
	}

	@Override
	public List<Contact> search(Integer userId, String criteria) {
		String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remark` FROM contact WHERE userId=? AND (name LIKE '%"+criteria+"%' OR phone LIKE '%"+ criteria+"%' OR phone LIKE '%"+criteria+"%' OR email LIKE '%"+criteria+"%' OR address LIKE '%"+criteria+"%' OR remark LIKE '%"+criteria+"%' )";
		return jdbcTemplate.query(sql, new ContactRowMapper(), userId);
	}

	@Override
	public List<Contact> findUserContacts(Integer userId) {
		return contactDAO.findByProperty("userId", userId);
	}

}
