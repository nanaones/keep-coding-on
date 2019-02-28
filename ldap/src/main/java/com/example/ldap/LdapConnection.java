package com.example.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LdapConnection {


    private static LdapConnection ldapconn = null;
    //    private String ldappass = "password";
    private final String LdapHost = "ldap://ldap.forumsys.com:389";
    private String searchBase = "dc=example, dc=com";
    private static Hashtable<String, String> env;
    private static DirContext ctx;
    private String ldapUser = null;
    private String ldapPass = null;

    private LdapConnection() {
        try {
            env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, LdapHost);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            ctx = new InitialDirContext(env);
            System.out.println("Activate Directory Connected");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static LdapConnection getInstance() {
        if (ldapconn == null) {
            ldapconn = new LdapConnection();
        }
        return ldapconn;
    }

    public Attributes ldapSearch(String username, String pwd) {
        Attributes attrs = null;
        try {
            ldapUser = "uid=" + username;
            ldapPass = pwd;
            env.put(Context.SECURITY_PRINCIPAL, ldapUser + searchBase);
            env.put(Context.SECURITY_CREDENTIALS, ldapPass);
            String searchFilter = String.format("(&(objectClass=person)(%s))", ldapUser);
            SearchControls ctls = new SearchControls();
            NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, ctls);

            while (results.hasMoreElements()) {
                SearchResult sr = results.next();
                attrs = sr.getAttributes();
                System.out.println("어트리뷰트: " + attrs);
            }
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return attrs;
    }
}
