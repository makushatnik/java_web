package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.Message;

import java.util.Locale;

/**
 * Created by Ageev Evgeny on 26.01.2016.
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
    private final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private MessageSource messageSource;
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @RequestMapping("/loginfail")
    public String loginFail(Model uiModel, Locale locale) {
        logger.info("Login failed detected");
        uiModel.addAttribute("message", new Message("error",
                messageSource.getMessage("message_login_fail", new Object[]{}, locale)));
        return "contacts/list";
    }
}
