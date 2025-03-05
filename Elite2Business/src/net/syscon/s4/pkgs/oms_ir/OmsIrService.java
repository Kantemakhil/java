package net.syscon.s4.pkgs.oms_ir;

public interface OmsIrService {
	Boolean changePassword(final String userId, final String oldPassword, final String newPassword, String loggedUserName);
}