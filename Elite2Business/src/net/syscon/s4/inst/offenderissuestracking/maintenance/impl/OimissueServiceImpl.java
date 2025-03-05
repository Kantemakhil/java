package net.syscon.s4.inst.offenderissuestracking.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.GrievanceReasons;
import net.syscon.s4.im.beans.GrievanceReasonsCommitBean;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimissueRepository;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimissueService;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;

/**
 * Class OimissueServiceImpl
 */
@Service
public class OimissueServiceImpl extends BaseBusiness implements OimissueService {

	@Autowired
	private OimissueRepository oimissueRepository;

	@Autowired
	private TagSecurityService tagSecurityService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTypes> grievanceTypesExecuteQuery(final GrievanceTypes searchRecord) {
		return oimissueRepository.grievanceTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGRIEVANCE_TYPES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<GrievanceTypes> grievanceTypesCommit(final GrievanceTypesCommitBean commitBean) {
		final List<GrievanceTypes> liReturnData = new ArrayList<>();
		final GrievanceTypes grevenceTypes = new GrievanceTypes();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (GrievanceTypes bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifiedUserId(commitBean.getCreateUserId());
			}
			liReturn = oimissueRepository.grievanceTypesInsertGrievanceTypes(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (GrievanceTypes bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimissueRepository.grievanceTypesUpdateGrievanceTypes(commitBean.getUpdateList());
		}
		grevenceTypes.setReturnValue(liReturn);
		liReturnData.add(grevenceTypes);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GrievanceReasons> grievanceReasonsExecuteQuery(final GrievanceReasons searchRecord) {
		return oimissueRepository.grievanceReasonsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGRIEVANCE_REASONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<GrievanceReasons> grievanceReasonsCommit(final GrievanceReasonsCommitBean commitBean) {
		final List<GrievanceReasons> liReturnData = new ArrayList<>();
		final GrievanceReasons grevencereasons = new GrievanceReasons();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (GrievanceReasons bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifiedUserId(commitBean.getCreateUserId());				
			}
			liReturn = oimissueRepository.grievanceReasonsInsertGrievanceReasons(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (GrievanceReasons bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimissueRepository.grievanceReasonsUpdateGrievanceReasons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimissueRepository.grievanceReasonsDeleteGrievanceReasons(commitBean.getDeleteList());
		}
		grevencereasons.setReturnValue(liReturn);
		liReturnData.add(grevencereasons);
		return liReturnData;
	}

	
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTxns> grievanceTxnsExecuteQuery(final GrievanceTxns searchRecord) {
		return oimissueRepository.grievanceTxnsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGRIEVANCE_TXNS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<GrievanceTxns> grievanceTxnsCommit(final GrievanceTxnsCommitBean commitBean) {
		final List<GrievanceTxns> liReturnData = new ArrayList<>();
		final GrievanceTxns grevenceTxns = new GrievanceTxns();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (GrievanceTxns bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimissueRepository.grievanceTxnsInsertGrievanceTxns(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (GrievanceTxns bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimissueRepository.grievanceTxnsUpdateGrievanceTxns(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimissueRepository.grievanceTxnsDeleteGrievanceTxns(commitBean.getDeleteList());
		}
		grevenceTxns.setReturnValue(liReturn);
		liReturnData.add(grevenceTxns);
		return liReturnData;
	}

	/**
	 * To check the records exist in reason table or not
	 *
	 * @param paramBean
	 *
	 * @throws SQLException
	 */
	@Override
	public List<GrievanceReasons> grievanceTypesOnCheckDeleteMaster(final GrievanceReasons paramBean) {
		// TODO Auto-generated method stub
		return oimissueRepository.grievanceTypesOnCheckDeleteMaster(paramBean);
	}

	/**
	 * To check the records exist in Transaction table or not
	 *
	 * @param paramBean
	 *
	 * @throws SQLException
	 */
	@Override
	public GrievanceReasons onDeleteReasons(final GrievanceReasons paramBean) {
		Integer deleteCountRecord = 0;
		deleteCountRecord = oimissueRepository.onDeleteReasons(paramBean);

		paramBean.setDeleteCountRecord(deleteCountRecord);
		return paramBean;
	}

	/**
	 * To check the records exist in Transaction table or not
	 *
	 * @param paramBean
	 *
	 * @throws SQLException
	 */
	@Override
	public GrievanceTxns cgrichkMovementReasonsDeleteCheck(final GrievanceTxns paramBean) {
		Integer deleteCountRecord = 0;
		deleteCountRecord = oimissueRepository.cgrichkMovementReasonsScheduleCheck(paramBean);

		paramBean.setDeleteRecordCount(deleteCountRecord);
		return paramBean;
	}

	/**
	 * To check the records exist in reason table or not
	 *
	 * @param paramBean
	 *
	 * @throws SQLException
	 */
	@Override
	public GrievanceTxns getTabSecuityEnable(final String userName) {
		String reasonTabsecty;
		String txnTabSecurity;
		final GrievanceTxns returnObject = new GrievanceTxns();
		reasonTabsecty = tagSecurityService.getGroupPrivilege("OIMGRIEV_T01", userName);
		txnTabSecurity = tagSecurityService.getGroupPrivilege("OIMGRIEV_T02", userName);
		returnObject.setReasonsTabSecurity(reasonTabsecty);
		returnObject.setTxnTabSecurity(txnTabSecurity);
		return returnObject;
	}

}