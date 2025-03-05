package net.syscon.s4.common.utils;

public final class UserSecurityUtils {

	private static UserSecurityUtils instance;

	private UserSecurityUtils() {
	}

	public static UserSecurityUtils getInstance() {
		if (instance == null) {
			instance = new UserSecurityUtils();
		}
		return instance;
	}

	private ThreadLocal<String> currentUserName = new ThreadLocal<String>();

	public String getCurrentUserName() {
		return currentUserName.get();
	}

	public void setCurrentUserName(String userID) {
		this.currentUserName.set(userID);
	}

}
