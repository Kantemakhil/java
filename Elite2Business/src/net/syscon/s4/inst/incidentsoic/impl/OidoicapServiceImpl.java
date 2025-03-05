package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.OidoicapRepository;
import net.syscon.s4.inst.incidentsoic.OidoicapService;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidentsCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenalties;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenaltiesCommitBean;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppeals;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealsCommitBean;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationService;

/**
 * Class OidoicapServiceImpl
 */
@Service
public class OidoicapServiceImpl extends BaseBusiness implements OidoicapService {

	@Autowired
	private OidoicapRepository oidoicapRepository;

	@Autowired
	private TagAdjudicationService tagAdjudicationService;

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgHeardByRecordGroup() {
		return oidoicapRepository.rgHeardByRecordGroup();

	}

	public List<OicHearingResults> rgHearingOffencesRecordGroup(int offenderBookingId) {

		return oidoicapRepository.rgHearingOffencesRecordGroup(offenderBookingId);

	}

	public List<OffenderOicAppealPenalties> rgOicSeqLogRecordGroup(OffenderOicAppealPenalties searchRecord) {
		return oidoicapRepository.rgOicSeqLogRecordGroup(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicAppeals> offOicaExecuteQuery(OffenderOicAppeals searchRecord) {
		List<OffenderOicAppeals> returnList = new ArrayList<>();
		returnList = oidoicapRepository.offOicaExecuteQuery(searchRecord);
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_OICA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offOicaCommit(OffenderOicAppealsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderOicAppeals obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoicapRepository.offOicaInsertOffenderOicAppeals(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderOicAppeals obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoicapRepository.offOicaUpdateOffenderOicAppeals(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderOicAppeals e : commitBean.getDeleteList()) {
				e.setModifyUserId(commitBean.getCreateUserId());
				int deleteCount = oidoicapRepository.onAppealCheckDeleteMaster(e.getOicApprealId());
				if (deleteCount == 1) {
					return 2;
				}
			}
			liReturn = oidoicapRepository.offOicaDeleteOffenderOicAppeals(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicAppealIncidents> offOicaiExecuteQuery(OffenderOicAppealIncidents searchRecord) {
		List<OffenderOicAppealIncidents> returnList = new ArrayList<>();
		returnList = oidoicapRepository.offOicaiExecuteQuery(searchRecord);
		returnList.forEach(obj -> {
			searchRecord.setOicHearingId(obj.getOicHearingId());
			searchRecord.setResultSeq(obj.getResultSeq());
			List<OicHearingResults> returnObj = oidoicapRepository.offOicaiPostQuery(searchRecord);
			if (returnObj != null && returnObj.size() > 0) {
				returnObj.forEach(e -> {
					obj.setChargedOffenceCode(e.getOicOffenceCode());
					obj.setOffenceDescription(e.getDescription());
					obj.setOicOffenceId(e.getOicOffenceId());
					obj.setAgencyIncidentId(e.getAgencyIncidentId());
					obj.setChargeSeq(e.getChargeSeq());
					obj.setOicIncidentId(e.getOicIncidentId());
					obj.setHearingDate(e.getHearingDate());
				});
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_OICAI
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offOicaiCommit(OffenderOicAppealIncidentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderOicAppealIncidents obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoicapRepository.offOicaiInsertoffOffenderOicAppealIncidents(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderOicAppealIncidents obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoicapRepository.offOicaiUpdateOffenderOicAppealIncidents(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderOicAppealIncidents obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				final int deleteValid = oidoicapRepository.offOicaiOnCheckDeleteMaster(obj);
				if (deleteValid == 1) {
					return 2;
				}
			}
			liReturn = oidoicapRepository.offOicaiDeleteOffenderOicAppealIncidents(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicAppealPenalties> offOicapExecuteQuery(OffenderOicAppealPenalties searchRecord) {
		return oidoicapRepository.offOicapExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_OICAP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offOicapCommit(OffenderOicAppealPenaltiesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderOicAppealPenalties obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setSeq(oidoicapRepository.offOicapPreInsertOffenderOicAppealPenalties(obj.getOffenderBookingId()));
				liReturn = oidoicapRepository.offOicapInsertOffenderOicAppealPenalties(obj);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderOicAppealPenalties obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidoicapRepository.offOicapUpdateOffenderOicAppealPenalties(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderOicAppealPenalties obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				final int deleteValid = oidoicapRepository.onOicAppealPenaltiesCheckDeleteMaster(obj);
				if (deleteValid == 1) {
					return 2;
				}
			}
			liReturn = oidoicapRepository.offOicapDeleteOffenderOicAppealPenalties(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public String butOriginalPenalty(OffenderOicAppealIncidents searchBean) {
		return oidoicapRepository.butOriginalPenalty(searchBean);
	}

	@Override
	public String getoffencedetails(OffenderOicAppealIncidents searchBean) {
		String offenceTypeDesc = null;
		OicOffences oicOffences = tagAdjudicationService.getoffencedetails(searchBean.getOicOffenceId());
		if (oicOffences != null) {
			offenceTypeDesc = oicOffences.getOffenceTypeDesc();
		}
		return offenceTypeDesc;
	}

	@Override
	public OicOffences getOicOffenceCodeCur(Integer oicOffenceId) {
		return oidoicapRepository.getOicOffenceCodeCur(oicOffenceId);
	}

}