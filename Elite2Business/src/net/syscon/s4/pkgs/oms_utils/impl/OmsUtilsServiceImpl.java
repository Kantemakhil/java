package net.syscon.s4.pkgs.oms_utils.impl;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsRepository;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.triggers.SubstanceTestResults;
/* MODIFICATION HISTORY
 * Below comments are copied from validate_userid function
Version     Developer       Date        Comments
---------   -----------     ----------  ------------------------------------------
2.23        Wellington      18-JUL-2018 Adding GRANT user proxy connection on CREATE_USER (required by S4)
2.22        Rajshree        21-JUL-2014 HPQC#23975 Changed message to be  Password length cannot be less than 8.
2.21        Ruxandra        21-FEB-2014 HPQC#21588 CJIS Password compliance - added expire password procedure
2.20        Ruxandra        16-JAN-2014 HPQC#21588 CJIS Password compliance
                                                 -- added function exists_in_dictionary
                                                 -- modified procedure create_user to expire user and to assign oracle profile upon creation of the user
2.19        Niko            11-DEC-2008 Issue No.  7682 : opened : When user created, Role Based security bypassed
                                        Default set to None
2.18        Manjul          27-NOV-2008 Added procedure lock_unlock_account
2.17        GJC             14-Oct-2006 Remove DBMS_OUTPUT calls
2.16        Surya           24-Apr-2006 Re-Created the Create_User procedure with the dynamic
 			  				  			  native sql statements.
2.15        Surya           24-Apr-2006 Modified the Create_User Procedure, oracle error
			  				  			  was coming while log into the application for the new
										  user:
			  				  			  1.Removed the admin option to Tag_User Role.
										  2.Assigned the CONNECT Role to New User
										  3.Made the Tag_User Role as the default role to the user.
2.14	      D Rice	      14-Apr-2006 Added Function: get_offender_booking_rec
2.13        Venu            03-Jan-2006 D# 47. Removed get_db_sequence_no from package body.
2.12	      M Cox	      29-Dec-2005 Removed Grant statements from script.
2.11        Claus           14-Dec-2005 D# 47. Removed get_db_sequence_no and generate_next_sequence.
2.10        Surya           02-Dec-2005 Added get_staff_name_rec function.
2.8         Surya           07-Oct-2005 1.Added get_db_sequence_no function, which returns
                                        database object sequence's nextval dynamically.
                                        2.Added overloaded generate_next_sequence -
                                        Get the next primary key column seq by passing
                                        table name(ex:OFFENDER_SENTENCE_TERMS)
                                              ,the name of primary key1 column(ex:OFFENDER_BOOK_ID)
                                              ,the name of second primary key column(ex: SENTENCE_SEQ)
                                              ,the name of third primary key column(ex: TERM_SEQ)
                                              ,the value of first primary key column(book id value)
                                              ,the value of second primary key column(sentence seq value).
2.7         Laurence        22-Sep-2005 Removed format_staff_name().
2.6         Surya           22-Sep-2005 Added the Function combine_datetime which combines
                                        the date and time portions and returns the date and time.
2.5         Laurence        15-Sep-2005 Added procedure:
                                        get_staff_id_and_name().
                                        format_staff_name() - for project-wide consistency.
                                        Modified:
                                        get_staff_name() to use format_staff_name().
2.4         Surya           13-Sep-2005 Corrected Version Label
2.2         Surya           25-Aug-2005 Added the following generic sub-routines for the
                                        regular development usage:
                                        1.get_staff_id - Get the staff name by passing staff_id parameter.
                                        2.get_last_name - Get last_name and first name or the staff member
                                                          by passing staff_id parameter.
                                        3.generate_next_sequence - Get the next primary key column seq
                                                          by passing table name(ex:OFFENDER_SENTENCES)
                                                          ,the name of primary key column(ex:OFFENDER_BOOK_ID)
                                                          and name of second primary key column(ex: SENTENCE_SEQ)
                                                          and the value of first primary key
                                                          column(ex: offender_book_id value - 1732).
10.2.1      Michael         14-JUL-2005 Removed insert into Journal Table
6.1.0.0     Vipul           08-APR-2002 Modified create_user proc to  grant create session
                                        privileges before granting tag_user.
4.9.0.0     Simon           06/27/2000  modified CREATE_USER for Role-based security
                                        refer to comments in the procedure
4.9.0.1     Simon           06/29/2000  new procedure CHANGE_ROLE_PASSWORD created to
                                        change the password of a ROLE.  currently called
                                        by the system profiles screen OUMSYPFL
4.9.0.1     Simon           07/05/2000  user created are granted with the admin option
                                        so that they in turn can grant roles to the
                                        users they create
4.9.0.2     Surya           09-AUG-2000 Added the SHOW_VERSION().
*/
@Service
public class OmsUtilsServiceImpl implements OmsUtilsService {

	@Autowired
	private OmsUtilsRepository omsUtilsRepository;
	
	private static String SEARCHSTRINGC = "^s";

	final private static Logger logger = LogManager.getLogger(OmsUtilsServiceImpl.class.getName());

	@Override
	public Integer changeUserPassword(final String userName, final String passWord) {
		final Boolean retVal = omsUtilsRepository.changeUserPassword(userName, passWord);
		if (retVal) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer expireUser(final String userName) {
		return omsUtilsRepository.expireUser(userName);
	}
	//Get the staff name by passing staff_id parameter.
	@Override
	public Integer getStaffId(String userName) {
		return omsUtilsRepository.getStaffId(userName);
	}
    
	

	   /*HPQC#21588
	    To check a password is valid.
	   this function is used to check validate password by screens OUMAPASS, OUMCPASS,
	   but it is also used to validate the user id by screen OUMUSERS;
	   To avoid confusion and because the logic to validate pasword should contain other checks at the screen level we'll discontinue the use of this function,
	   create validate_userid to be used by screen OUMUSERS, and modify and use validate_password to validate password

	   */
	   
	@Override
	public Map<String, Object> validateUserid(final StaffMembers paramBean) {
		String lIllegalChars = null;
		String pIllegalChars = null;
		Integer retVal = null;
		Map<String, Object> outParams = new HashMap<String, Object>();
		try {
			final Integer position = omsUtilsRepository.getPosition(paramBean.getUserId());
			if (position == 0) {
				retVal = -1;
			}
			lIllegalChars = omsUtilsRepository.getIllegalChars(paramBean.getUserId());
			if (lIllegalChars != null) {
				pIllegalChars = lIllegalChars;
				retVal = -2;
			}
			retVal = 0;
			outParams.put("P_ILLEGAL_CHARS", pIllegalChars);
			outParams.put("RETURN_VALUE", retVal);
		} catch (Exception e) {
			outParams = null;
		}
		return outParams;
	}

	@Override
	public Map<String, Object> validatePassword(final String passWord) {
		final Map<String, Object> map = new HashMap();
		Integer pMinPwdlength = null;
		String pErrorMsg = null;

		String lvIllegaclChars = null;
		Integer result = null;
		final Long profileval = omsUtilsRepository.getProfilevalue();
		if (passWord.length() < 1 || passWord == null) {
			pMinPwdlength = profileval.intValue();
			pErrorMsg = "Password length cannot be less than " + profileval.toString();
			result = -1;
		}
		if (passWord.length() > 20) {
			pErrorMsg = "Password length cannot be longer than 20 characters.";
			result = -2;
		}

		final Integer count = omsUtilsRepository.getSubString(passWord);
		if (count == 0) {
			pErrorMsg = "'Password must start with a letter.";
			result = -3;
		}

		lvIllegaclChars = omsUtilsRepository.getLtrim(passWord);
		if (lvIllegaclChars != null) {
			pErrorMsg = "Password contains invalid characters " + lvIllegaclChars.toString();
			result = -4;
		}
		displayUserMessage(10, "OMFS");
		// display_user_message (10, 'OMS', 'OMFVALPW', SQLERRM)
		map.put("P_ERROR_MESSAGE", pErrorMsg);
		map.put("P_MIN_PASSWD_LEN", pMinPwdlength);
		return map;
	}

	@Override
	public String displayUserMessage(final Integer msgNumber, final String applicationSystem) {
		final SystemMessages bean = omsUtilsRepository.getSystemMsg(msgNumber, applicationSystem);
		if (bean != null && bean.getMessageType() == null && bean.getMessageText() != null) {
			return applicationSystem + " " + " " + msgNumber.toString() + " " + bean.getMessageType() + " "
					+ bean.getMessageText() + " " + bean.getActionText();
		} else {
			return "Message number " + msgNumber + "not found in SYSTEM_MESSAGES table. Call support";
		}
	}

	@Override
	public Integer createUser(final String userName, final String passWord, final String defaultTableSpace,
			final String tempTableSpace) {
		try {
			final String result = omsUtilsRepository.checkProfCur();
			String lvSecprofExists = "N";
			final Boolean createUser = omsUtilsRepository.createUser(userName, passWord, defaultTableSpace,
					tempTableSpace);
			final Boolean grandPerm = omsUtilsRepository.grantPermToUser(userName);
			if (createUser && grandPerm) {
				lvSecprofExists = result;
				if (lvSecprofExists.equals("Y")) {
					omsUtilsRepository.toAssignProfile(userName);
					// Oms_Utils.grant_s4user_proxy(p_user_name);
					omsUtilsRepository.expirePassword(userName);
					grantS4UserProxy(userName);
				}
			}
		} catch (Exception e) {
			logger.error("createUser", e);
			return 0;
		}
		return 1;
	}

	@Override
	public void grantS4UserProxy(final String userName) {
		String proval = omsUtilsRepository.grantS4UserProxy(userName);
		if (proval != null) {

		} else {
			proval = "ELITE_0BF0C9181DAE13";
		}
		omsUtilsRepository.connectPermToUser(userName, proval);
	}

	@Override
	public String getStaffName(final BigDecimal pStaffId) {
		return omsUtilsRepository.getStaffName(pStaffId);
	}

	@Override
	public OffenderCourseAttendance getOffenderBookingRec(final Long offenderBookId) {
		final OffenderCourseAttendance offAtdns = new OffenderCourseAttendance();
		String offName = null;
		String offenderIdDisplay = null;
		try {
			final Offenders off = omsUtilsRepository.getOffBookCur(offenderBookId);
			offName = off.getLastName().concat(" , ").concat(off.getFirstName());
			offenderIdDisplay = off.getOffenderIdDisplay();
		} catch (Exception e) {
			logger.error("getOffenderBookingRec", e.getMessage());
		}
		offAtdns.setOffenderIdDisplay(offenderIdDisplay);
		offAtdns.setOffenderName(offName);

		return offAtdns;
	}

	@Override
	public BigDecimal getStaffid(final String userId) {
		return omsUtilsRepository.staffCur(userId);
	}

	@Override
	public String displayUserMessage(Integer pMsgNumber, String pApplicationSystem, String pMsgParamOne,
			String pMsgParamTwo) {
		String[] vMsgTable = new String[2];
		vMsgTable[0] = pMsgParamOne;
		vMsgTable[1] = pMsgParamTwo;
		return displayUserMessageStub(pMsgNumber, pApplicationSystem,vMsgTable);
		
	}

	@Override
	public String displayUserMessageStub(Integer pMsgNumber, String pApplicationSystem,String[] pMsgTable) {
		String vSysRemarksText;
		String vActionText;
		String vMsgType;
		String vMsgTxt;
		Integer vCharLocation;
		Integer vLenOfSearchString;
		Integer vTableCounter;
		String[] vMsgTable = pMsgTable ;
		Integer vMsgTableLength = pMsgTable.length;
		
		vTableCounter = 0;
		vCharLocation = 1;
		vLenOfSearchString = SEARCHSTRINGC.length();
		SystemMessages sysMsgCur = omsUtilsRepository.getSystemMsg(pMsgNumber, pApplicationSystem);
		vMsgType = sysMsgCur.getMessageType();
		vMsgTxt = sysMsgCur.getMessageText();
		vActionText = sysMsgCur.getActionText();
		vSysRemarksText = sysMsgCur.getSystemRemarksText();
		while (vTableCounter< vMsgTableLength) {
			vCharLocation = vMsgTxt.indexOf(SEARCHSTRINGC)+1;
			if (vCharLocation ==0) {
				 return (pApplicationSystem + "- -2 ERROR Message Number" + pMsgNumber + "has been called with an invalid # of arguments. Call support'");
			}
			vMsgTxt = (vMsgTxt.substring(1,vCharLocation-1).concat(vMsgTable[vTableCounter])).concat(vMsgTxt.substring(vCharLocation+vLenOfSearchString));
			vTableCounter++;		
		}
		return (pApplicationSystem +" " + pMsgNumber + " " +vMsgType+" "+ vMsgTxt+" "+vActionText);
	}

}
