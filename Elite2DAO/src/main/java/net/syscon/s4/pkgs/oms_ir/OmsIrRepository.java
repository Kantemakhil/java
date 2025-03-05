package net.syscon.s4.pkgs.oms_ir;

public interface OmsIrRepository {

	Boolean ChangeUserPassword(final String userName,final String passWord,final String newPassword, String loggedUserName);
}