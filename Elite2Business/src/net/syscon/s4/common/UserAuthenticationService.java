package net.syscon.s4.common;

public interface UserAuthenticationService {

	boolean authenticate(String username, String password);

}
