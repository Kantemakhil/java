package net.syscon.s4.pkgs.oms_utils;

import java.math.BigDecimal;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.StaffMembers;

public interface OmsUtilsService {

	Integer changeUserPassword(final String userName, final String passWord);

	Integer expireUser(final String userName);

	Integer getStaffId(String userName);

	Map<String, Object> validateUserid(final StaffMembers paramBean);

	Map<String, Object> validatePassword(final String passWord);

	String displayUserMessage(final Integer msgNumber, final String applicationSystem);

	Integer createUser(final String username, final String password, final String defaulttableSpace,
			final String tempTableSpace);

	void grantS4UserProxy(final String userName);

	String getStaffName(final BigDecimal pStaffId);

	OffenderCourseAttendance getOffenderBookingRec(final Long offenderBookId);

	BigDecimal getStaffid(String userId);
	
	String displayUserMessage(Integer pMsgNumber, String pApplicationSystem, String pMsgParamOne, String pMsgParamTwo);

	String displayUserMessageStub(Integer pMsgNumber, String pApplicationSystem,String[] pMsgTable);
}
