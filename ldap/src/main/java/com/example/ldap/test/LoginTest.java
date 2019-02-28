package com.example.ldap.test;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LoginTest {
    public static void main(String[] args) {

//        String ldapUser = "cn=read-only-admin, dc=example, dc=com";
        String ldapUser = "uid=curie, dc=example, dc=com";
        String ldappass = "password";
        final String LdapHost = "ldap://ldap.forumsys.com:389";
        String searchBase = "dc=example, dc=com";

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LdapHost);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, ldapUser);
        env.put(Context.SECURITY_CREDENTIALS, ldappass);

        try {
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Active Directory Connected");
            String searchFilter = "(&(objectClass=person)(uid=curie))";
            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            ctls.setReturningAttributes(new String[]{"cn", "mail"});
            NamingEnumeration<SearchResult> result = ctx.search(searchBase, searchFilter, ctls);

            while (result.hasMoreElements()) {
                SearchResult sr = result.next();
                Attributes attrs = sr.getAttributes();
                System.out.println("attributes: " + attrs);
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

}
