package com.example.ldap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LdapController {

    @RequestMapping(value = "/index")
    public String index() {
        return "";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login() {
        return "";
    }


}
