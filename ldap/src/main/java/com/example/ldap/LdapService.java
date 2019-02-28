package com.example.ldap;

import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;

@Service
public class LdapService {

    LdapConnection ldapconn = LdapConnection.getInstance();

    public Boolean ldapLogin(String username, String pwd) {
        boolean result = false;
        Attributes attrs = ldapconn.ldapSearch(username, pwd);
        if (attrs != null) {
            result = true;
        }
        return result;
    }
}
