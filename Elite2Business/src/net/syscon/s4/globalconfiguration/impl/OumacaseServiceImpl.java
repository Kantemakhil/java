package net.syscon.s4.globalconfiguration.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumacaseRepository;
import net.syscon.s4.globalconfiguration.OumacaseService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.CaseloadsCommitBean;

/**
 * Class OumacaseServiceImpl
 */
@Service
public class OumacaseServiceImpl extends BaseBusiness implements OumacaseService {

	@Autowired
	private OumacaseRepository oucaseRepository;

	/**
	 * Creates new OumacaseServiceImpl class Object
	 */
	public OumacaseServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CaseloadAgencyLocations> onCheckDeleteMaster(final CaseloadAgencyLocations paramBean) {
		return oucaseRepository.onCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> executeQuery(final Caseloads searchRecord) {
		return oucaseRepository.executeQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer csldCommit(final CaseloadsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final Caseloads obj : commitBean.getInsertList()) {
				obj.setAccessPropertyFlag("N");
				obj.setCreateUserId(commitBean.getCreateUserId());
				if("Y".equals(obj.getTrustAccountsFlag())) {
					String seqName = "SEQUENCE_"+obj.getCaseloadId();
					generateCaseloadSeq(seqName);
				}
			}
			liReturn = oucaseRepository.insertCaseloads(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(final Caseloads obj : commitBean.getUpdateList()) {
				if("Y".equals(obj.getTrustAccountsFlag())) {
					String seqName = "SEQUENCE_"+obj.getCaseloadId();
					Integer returnVal = oucaseRepository.checkCaseloadSeqExistorNot(seqName);
					if(returnVal != 1) {
						generateCaseloadSeq(seqName);
					}
					
					Integer dSeqExitsVal = oucaseRepository.checkCaseloadSeqExistorNot(seqName+"_D");
					if(dSeqExitsVal != 1) {
						generateCaseloadSeq(seqName+"_D");
					}

				}
			}
			for (Caseloads obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oucaseRepository.updateCaseloads(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * To create sequence for caseloadId
	 *
	 *
	 * @throws SQLException
	 */

	public Integer generateCaseloadSeq(String seqName) {
		return oucaseRepository.generateCaseloadSeq(seqName);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAgencyLocations> alExecuteQuery(final CaseloadAgencyLocations searchRecord) {
		return oucaseRepository.alExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCSLD_AL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer alCommit(final CaseloadAgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CaseloadAgencyLocations obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oucaseRepository.insertCaseloadAgencyLocations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final CaseloadAgencyLocations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oucaseRepository.updateCaseloadAgencyLocations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final CaseloadAgencyLocations obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oucaseRepository.deleteCaseloadAgencyLocations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> payrollTrustRgRecordGroup() {
		return oucaseRepository.payrollTrustRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> commissaryTrustRgRecordGroup() {
		return oucaseRepository.commissaryTrustRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> trustCommissaryRgRecordGroup() {
		return oucaseRepository.trustCommissaryRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> communityTrustRgRecordGroup() {
		return oucaseRepository.communityTrustRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> typeRgRecordGroup() {
		return oucaseRepository.typeRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		return oucaseRepository.alAgyLocIdRgRecordGroup();

	}

	/**
	 * This method executes when trigger event is called
	 * 
	 * throws SQLException
	 *
	 */
	public List<Object> checkAgency(final CaseloadAgencyLocations paramBean) {
		return oucaseRepository.checkAgency(paramBean);
	}
}
