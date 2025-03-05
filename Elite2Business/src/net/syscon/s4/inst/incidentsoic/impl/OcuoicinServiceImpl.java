package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvStatementsCommitBean;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.im.beans.AgyIncInvestigationsCommitBean;
import net.syscon.s4.inst.incidentsoic.OcuoicinRepository;
import net.syscon.s4.inst.incidentsoic.OcuoicinService;

/**
 * Class OcuoicinServiceImpl
 */
@Service
public class OcuoicinServiceImpl extends BaseBusiness implements OcuoicinService {

	@Autowired
	private OcuoicinRepository ocuoicinDao;

	/**
	 * Creates new OcuoicinBusiness class Object
	 */
	public OcuoicinServiceImpl() {
		// OcuoicinServiceImpl;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * 
	 * @param investgation
	 * @throws Exception
	 * @return List<Object>
	 */
	public List<Object> oicInvestOnCheckDeleteMasteroicInvestStaCur(final String investgation) {
		return ocuoicinDao.oicInvestOnCheckDeleteMasteroicInvestStaCur(investgation);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @return List<AgyIncInvestigations>
	 * @param searchRecord
	 * @
	 */
	public List<AgyIncInvestigations> oicInvestSearchAgyIncInvestigations(final AgyIncInvestigations searchRecord) {
		List<AgyIncInvestigations> returnList = null;
		returnList = ocuoicinDao.oicInvestSearchAgyIncInvestigations(searchRecord);
		for (final AgyIncInvestigations obj : returnList) {
			obj.setInvestigatorIdDes(obj.getInvestigatorId().toString());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param AgyIncInvestigationsCommitBean
	 *            commitBean
	 * @ @return Integer
	 */
	@Transactional
	public Integer oicInvestInsertAgyIncInvestigations(final AgyIncInvestigationsCommitBean commitBean) {
		int liReturn = 0;

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(final AgyIncInvestigations obj :commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoicinDao.oicInvestInsertAgyIncInvestigations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(final AgyIncInvestigations obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoicinDao.oicInvestUpdateAgyIncInvestigations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = ocuoicinDao.oicInvestDeleteAgyIncInvestigations(commitBean.getDeleteList());
		}

		return liReturn;

	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgyIncInvestigations
	 * @ @return Integer
	 */
	@Transactional
	public Integer oicInvestUpdateAgyIncInvestigations(final List<AgyIncInvestigations> lstAgyIncInvestigations) {
		return ocuoicinDao.oicInvestUpdateAgyIncInvestigations(lstAgyIncInvestigations);
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgyIncInvestigations
	 * @ @return Integer
	 */
	@Transactional
	public Integer oicInvestDeleteAgyIncInvestigations(final List<AgyIncInvestigations> lstAgyIncInvestigations) {
		return ocuoicinDao.oicInvestDeleteAgyIncInvestigations(lstAgyIncInvestigations);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param AgyIncInvStatements
	 *            searchRecord
	 * @return List<AgyIncInvStatements> @
	 */
	public List<AgyIncInvStatements> oicInvestStaSearchAgyIncInvStatements(final AgyIncInvStatements searchRecord) {
		return ocuoicinDao.oicInvestStaSearchAgyIncInvStatements(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgyIncInvStatements
	 * @return Integer @
	 */
	@Transactional
	public Integer oicInvestStaInsertAgyIncInvStatements(final AgyIncInvStatementsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final AgyIncInvStatements obj :commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoicinDao.oicInvestStaInsertAgyIncInvStatements(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final AgyIncInvStatements obj :commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoicinDao.oicInvestStaUpdateAgyIncInvStatements(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = ocuoicinDao.oicInvestStaDeleteAgyIncInvStatements(commitBean.getDeleteList());
		}

		return liReturn;

	}

	/**
	 * Update the records from database table
	 * 
	 * @param lstAgyIncInvStatements
	 * @ @return Integer
	 */
	@Transactional
	public Integer oicInvestStaUpdateAgyIncInvStatements(final List<AgyIncInvStatements> lstAgyIncInvStatements) {
		return ocuoicinDao.oicInvestStaUpdateAgyIncInvStatements(lstAgyIncInvStatements);
	}

	/**
	 * Insert the records from database table
	 * 
	 * @param lstAgyIncInvStatements
	 * @ @return Integer
	 */
	@Transactional
	public Integer oicInvestStaDeleteAgyIncInvStatements(final List<AgyIncInvStatements> lstAgyIncInvStatements) {
		return ocuoicinDao.oicInvestStaDeleteAgyIncInvStatements(lstAgyIncInvStatements);
	}

	/**
	 * This method is used to execute a ReferenceCodes
	 * 
	 * @ @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatementTypeRecordGroup() {
		return ocuoicinDao.rgStatementTypeRecordGroup();

	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            caseloadId
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final String caseloadId) {

		List<StaffMembers> returnList = new ArrayList<>();
		returnList = ocuoicinDao.rgAgyIncpStaffIdRecordGroup(caseloadId);
		for (final StaffMembers staffObj : returnList) {
			staffObj.setCode(staffObj.getStaffId());
			staffObj.setStaffId(null);
		}
		return returnList;
	}

}