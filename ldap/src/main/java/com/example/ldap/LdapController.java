package com.example.ldap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LdapController {

    LdapService ldapService = new LdapService();

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(Model model, @RequestParam String username, String pwd) {
        ModelAndView mav = new ModelAndView();
        boolean result = ldapService.ldapLogin(username, pwd);

        if (result) {
            model.addAttribute("msg", "login success");
        } else {
            model.addAttribute("msg", "login fail");
        }
        mav.setViewName("loginResult");

        return mav;
    }


}
