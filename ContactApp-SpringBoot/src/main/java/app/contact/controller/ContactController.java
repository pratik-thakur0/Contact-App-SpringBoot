package app.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.contact.domain.Contact;
import app.contact.services.ContactService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {

	@Autowired
	ContactService contactService;

	@GetMapping("/user/contact-form")
	public String contactForm(Model m) {
		m.addAttribute("contact", new Contact());
//		System.out.println("-------------contactForm()---------------");
		return "contact_form";
	}

	@PostMapping("/user/save-contact")
	public String contactAction(@ModelAttribute Contact c, Model m, HttpSession session) {
//		System.out.println("-------------contactAction()---------------");
		if (c.getContactId() == null) {
			// Save
//			System.out.println("-------------save---------------");
			Integer userId = (Integer) session.getAttribute("userId");
			c.setUserId(userId);
			contactService.save(c);
			return "redirect:/user/contact-list?act=sv";
		} else {
			// Update
//			System.out.println("-------------update---------------");
			contactService.upadte(c);
			return "redirect:/user/contact-list?act=ed";
		}
	}

	@GetMapping("/user/contact-list")
	public String contactList(Model m, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Contact> list = contactService.findUserContacts(userId);
		m.addAttribute("contactList", list);
		return "contact_list";
	}

	@GetMapping("/user/contact/delete/{contactId}/")
	public String deleteContact(@PathVariable Integer contactId) {
		contactService.delete(contactId);
		return "redirect:/user/contact-list";
	}
	
	@GetMapping("/user/contact/bulkDelete")
	public String bulkDeleteContact(@RequestParam("cids") Integer[] contactIds) {
		contactService.bulkDelete(contactIds);
		return "redirect:/user/contact-list";
	}

	@GetMapping("/user/contact/edit-form/{contactId}/")
	public String editContactFrom(@PathVariable Integer contactId, Model m) {
		Contact c = contactService.getContactById(contactId);
		m.addAttribute("contact", c);
		return "contact_form";
	}
	
	@GetMapping("/user/contact/search")
	public String contactSearch(@RequestParam("criteria") String criteria, Model m, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Contact> list = contactService.search(userId, criteria);
		m.addAttribute("contactList", list);
		return "contact_list";
	}

}
