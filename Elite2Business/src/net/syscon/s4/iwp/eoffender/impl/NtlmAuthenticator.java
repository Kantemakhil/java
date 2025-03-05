/**
 *
 */
package net.syscon.s4.iwp.eoffender.impl;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @author ankur.gupta
 *
 */
public class NtlmAuthenticator extends Authenticator {

	private final String username;
    private final char[] password;

    public NtlmAuthenticator(final String username, final String password) {
        super();
        this.username = username;
        this.password = password.toCharArray();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return (new PasswordAuthentication(username, password));
    }

}
