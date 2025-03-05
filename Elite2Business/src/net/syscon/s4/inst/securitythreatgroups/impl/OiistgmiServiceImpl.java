package net.syscon.s4.inst.securitythreatgroups.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.VStgMembershipInquiry;
import net.syscon.s4.common.beans.VStgMembershipInquiryCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.securitythreatgroups.OiistgmiRepository;
import net.syscon.s4.inst.securitythreatgroups.OiistgmiService;

/**
 * Class OiistgmiServiceImpl
 */
@Service
public class OiistgmiServiceImpl extends BaseBusiness implements OiistgmiService {

	@Autowired
	private OiistgmiRepository oiistgmiRepository;

	/**
	 * Creates new OiistgmiServiceImpl class Object
	 */
	public OiistgmiServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VStgMembershipInquiry> vStgMembershipInquiryExecuteQuery(final VStgMembershipInquiry searchRecord) {
		return oiistgmiRepository.vStgMembershipInqExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_STG_MEMBERSHIP_INQUIRY
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vStgMembershipInquiryCommit(final VStgMembershipInquiryCommitBean commitBean) {
		return null;
	}

	/**
	 * Fetch the description value from database table
	 *
	 * @param searchBean
	 *
	 * @throws SQLException
	 */
	@Override
	public String getStgGroupDescription(final SecurityThreatGroups searchBean) {
		return oiistgmiRepository.getStgGroupDescription(searchBean);
	}

}