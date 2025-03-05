package net.syscon.s4.pkgs.oms_utils;

import java.math.BigDecimal;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SystemMessages;

public interface OmsUtilsRepository {

	Boolean changeUserPassword(final String userName, final String passWord);

	Integer expireUser(final String userName);

	String getStaffName(final BigDecimal supervisorStaffId);

	Integer getStaffId(String userName);

	Integer getPosition(final String pUserid);

	String getIllegalChars(final String pUserid);

	Long getProfilevalue();

	Integer getSubString(final String passWord);

	String getLtrim(final String passWord);

	SystemMessages getSystemMsg(final Integer msgNumber, final String applicationSystem);

	String checkProfCur();

	Boolean createUser(final String userName, final String passWord, final String defaultTableSpace,
			final String tempTableSpace);

	Boolean grantPermToUser(final String userName);

	Boolean toAssignProfile(final String userName);

	Boolean expirePassword(final String userName);

	String grantS4UserProxy(final String userName);

	Boolean connectPermToUser(String userName, String grantUser);

	Offenders getOffBookCur(Long offenderBookId);

	BigDecimal staffCur(String userId);
}