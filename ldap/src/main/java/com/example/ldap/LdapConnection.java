package com.example.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LdapConnection {

    private static DirContext ctx = null;
    //    private String ldappass = "password";
    private final String LdapHost = "ldap://ldap.forumsys.com:389";
    private String searchBase = "dc=example, dc=com";
    private String ldapUser = "";
    private String ldapPass = "";

    private LdapConnection() {
    }

    public static DirContext getConnection(Hashtable<String, String> env) {
        try {
            if (ctx == null) {
                ctx = new InitialDirContext(env);
            }
        } catch (NamingException e) {
            e.getStackTrace();
        }
        System.out.println("Active Directory Connected");
        return ctx;
    }

    public Hashtable<String, String> ldapLoginArguments(String uid, String pwd) {
        ldapUser = uid;
        ldapPass = pwd;
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LdapHost);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapUser + searchBase);
        env.put(Context.SECURITY_CREDENTIALS, ldapPass);

        return env;
    }

    public Attributes searchUser() {
        Attributes attrs = null;
        try {
            String searchFilter = "(&(objectClass=person)(" + ldapUser + "))";
            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> result = ctx.search(searchBase, searchFilter, ctls);
            while (result.hasMoreElements()) {
                SearchResult sr = result.next();
                attrs = sr.getAttributes();
                System.out.println("attributes: " + attrs);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return attrs;
    }
}
