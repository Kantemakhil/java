package net.syscon.s4.pkgs.tag_security;

public interface TagSecurityService {
	String getGroupPrivilege(final String moduleName);

	String getCaseloadId(final String userName);

	Boolean checkPrivilegeExists(final String roleCode, final String userName);
	
	String getGroupPrivilege(final String moduleName,final String userName);

	String checkPrivilege(String roleCode,  String userName);
}