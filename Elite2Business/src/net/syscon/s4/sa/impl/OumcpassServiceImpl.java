package net.syscon.s4.sa.impl;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.UserAuthenticationDao;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffAccessibleCaseloadsCommitBean;
import net.syscon.s4.pkgs.oms_ir.OmsIrService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.sa.OumcpassRepository;
import net.syscon.s4.sa.OumcpassService;

/**
 * Class OumcpassServiceImpl
 */
@Service
public class OumcpassServiceImpl extends BaseBusiness implements OumcpassService {

	@Autowired
	private OumcpassRepository oumcpassRepository;
	@Autowired
	private OmsIrService omslrService;
	@Autowired
	private UserAuthenticationDao oracleProxyAuthenticationDao;
	@Autowired
	private OmsUtilsService omsUtilsService;
	
	public static final String valid = "validPassword";

	/**
	 * Fetch the records from database table s
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> staffExecuteQuery(StaffMembers searchRecord) {
		return oumcpassRepository.staffExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer staffCommit(final String userId) {
		return oumcpassRepository.staffUpdateStaffMembers(userId);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(StaffAccessibleCaseloads searchRecord) {
		return oumcpassRepository.staffAcExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_AC
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer staffAcCommit(StaffAccessibleCaseloadsCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}
	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAFF_AC
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer changePassword(String userId,String oldPassword,String newPassword,String loggedUserName) {
		Integer liReturn = 0;
		if (userId != null) {
			Integer updateCount = oumcpassRepository.staffUpdateStaffMembers(userId);
			if(updateCount ==1) {
				Boolean retVal = omslrService.changePassword(userId, oldPassword, newPassword, loggedUserName);
				liReturn =retVal?1:0;
			}
		}
		return liReturn;
	}
	@Override
	public boolean authenticate(final String username, final String password) {
		Base64.Decoder decoder  = Base64.getDecoder();
		String decryptpwd = new String(decoder.decode(password));
		return oracleProxyAuthenticationDao.authenticate(username, decryptpwd);
	}
	@Override
	public String validatePassword(String newPassword) {
		final Map<String, Object> map = omsUtilsService.validatePassword(newPassword);
		if (map != null && map.get("P_ERROR_MESSAGE") != null) {
			return (String) map.get("P_ERROR_MESSAGE");
		}
		return valid;
	}

}