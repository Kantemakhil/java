package net.syscon.s4.common;

public interface UserAuthenticationDao {

	boolean authenticate(String username, String password);

}
