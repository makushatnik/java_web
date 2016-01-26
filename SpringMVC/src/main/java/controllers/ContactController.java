package controllers;

import com.google.common.collect.Lists;
import contact.Contact;
import contact.ContactGrid;
import contact.ContactService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utils.Message;
import utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ageev Evgeny on 26.01.2016.
 */
//@SuppressWarnings("unchecked")
@RequestMapping("/contacts")
@Controller
public class ContactController {
    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;
    private MessageSource messageSource;
    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        logger.info("Listing contacts");
        List<Contact> contacts = contactService.findAll();
        uiModel.addAttribute("contacts", contacts);
        logger.info("No. of contacts: " + contacts.size());
        return "contacts/list";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Contact cont = contactService.findById(id);
        uiModel.addAttribute("contact", cont);
        return "contacts/show";
    }
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Contact cont, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest req, RedirectAttributes attr, Locale locale) {
        logger.info("Updating contact");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage(
                    "contact_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("contact", cont);
            return "contacts/update";
        }
        uiModel.asMap().clear();
        attr.addFlashAttribute("message", new Message("success", messageSource.getMessage(
                "contact_save_success", new Object[]{}, locale)));
        contactService.save(cont);
        return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(cont.getId().toString(), req);
    }
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("contact", contactService.findById(id));
        return "contacts/update";
    }
    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid Contact cont, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest req, RedirectAttributes attr, Locale locale,
                         @RequestParam(value = "file", required = false) Part file) {
        logger.info("Creating contact");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage(
                    "contact_create_fail", new Object[]{}, locale)));
            uiModel.addAttribute("contact", cont);
            return "contacts/create";
        }
        uiModel.asMap().clear();
        attr.addFlashAttribute("message", new Message("success", messageSource.getMessage(
                "contact_create_success", new Object[]{}, locale)));
        logger.info("Created contact id: " + cont.getId());
        if (file != null) {
            logger.info("File name: " + file.getName());
            logger.info("File size: " + file.getSize());
            logger.info("File content type: " + file.getContentType());
            byte[] content = null;
            try {
                InputStream in = file.getInputStream();
                if (in == null) logger.info("File inputstream is null");
                else {
                    content = IOUtils.toByteArray(in);
                    //cont.setPhoto(content);
                }
            } catch (IOException e) {
                logger.info("Error saving uploaded file");
            }
            cont.setPhoto(content);
        }
        contactService.save(cont);
        return "redirect:/contacts/" + UrlUtil.encodeUrlPathSegment(cont.getId().toString(), req);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        Contact cont = new Contact();
        uiModel.addAttribute("contact", cont);
        return "contacts/create";
    }
    @RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ContactGrid listgrid(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer rows,
                                @RequestParam(value = "sidx", required = false) String sortBy,
                                @RequestParam(value = "sord", required = false) String order) {
        logger.info("Listing contacts for grid with page: {}, rows: {}", page, rows);
        logger.info("Listing contacts for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("birthDateString"))
            orderBy = "birthDate";
        if (orderBy != null && order != null) {
            if (order.equals("desc"))
                sort = new Sort(Sort.Direction.DESC, orderBy);
            else
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }

        PageRequest pageRequest = null;
        if (sort != null)
            pageRequest = new PageRequest(page - 1, rows, sort);
        else
            pageRequest = new PageRequest(page - 1, rows);
        Page<Contact> contactPage = contactService.findAllByPage(pageRequest);
        ContactGrid contactGrid = new ContactGrid();
        contactGrid.setCurrentPage(contactPage.getNumber() + 1);
        contactGrid.setTotalPages(contactPage.getTotalPages());
        contactGrid.setTotalRecords(contactPage.getTotalElements());
        contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));
        return contactGrid;
    }
    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto(@PathVariable("id") Long id) {
        Contact cont = contactService.findById(id);
        if (cont == null) return null;
        if (cont.getPhoto() != null) {
            logger.info("Downloading photo for id: {}, with size: {}", cont.getId(), cont.getPhoto().length);
        }
        return cont.getPhoto();
    }
}
