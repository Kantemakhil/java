package net.syscon.s4.sa.audit.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.OuiauactRepository;
import net.syscon.s4.sa.audit.OuiauactService;
import net.syscon.s4.sa.audit.SysTagAuditFormGetUserDetail;
import net.syscon.s4.sa.audit.SysTagAuditFormGetUserDetailCommitBean;

/**
 * Class OuiauactServiceImpl
 */
@Service
public class OuiauactServiceImpl extends BaseBusiness implements OuiauactService {
	
	@Autowired
	private OuiauactRepository ouiauactRepository;
	private static final String DATEFORMAT = "dd-MMM-yyyy HH:mm:ss";

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @throws ParseException
	 *
	 * @throws SQLException
	 */
	public List<SysTagAuditFormGetUserDetail> getUserDetailExecuteQuery(final SysTagAuditFormGetUserDetail searchRecord)
			throws ParseException {
		final List<SysTagAuditFormGetUserDetail> returnList = ouiauactRepository
				.getUserDetailExecuteQuery(searchRecord);
		final SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT);
		for (final SysTagAuditFormGetUserDetail obj : returnList) {
			obj.setStamp(formatter.parse(obj.getActTimestamp()));
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGET_USER_DETAIL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer getUserDetailCommit(final SysTagAuditFormGetUserDetailCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStfMemberRecordGroup() {
		return ouiauactRepository.rgStfMemberRecordGroup();

	}

}