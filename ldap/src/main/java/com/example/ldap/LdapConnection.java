package com.example.ldap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class LdapConnection {

    //    String searchBase = "cn=read-only-admin, dc=example, dc=com";
//    String searchBase = "dc=example, dc=com";
    private static DirContext ctx = null;
    final String ldappass = "password";
    final String LdapHost = "ldap://ldap.forumsys.com:389";
    //    String ldapUser = "cn=read-only-admin, dc=example, dc=com";
    private String dc = "dc=example, dc=com";

    private LdapConnection() {

    }

    public static DirContext getConnection(Hashtable<String, String> env) {
        try {
            if (ctx == null) {
                ctx = new InitialDirContext(env);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ctx;
    }

    public Hashtable<String, String> ldapLoginArguments(String uid, String pwd) {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LdapHost);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, uid + dc);
        env.put(Context.SECURITY_CREDENTIALS, pwd);
        return env;
    }

}
