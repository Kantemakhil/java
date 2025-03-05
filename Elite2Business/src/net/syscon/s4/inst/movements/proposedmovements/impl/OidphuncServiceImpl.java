package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.OidphuncRepository;
import net.syscon.s4.inst.movements.proposedmovements.OidphuncService;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;

/**
 * Class OidphuncServiceImpl
 */
@Service
public class OidphuncServiceImpl implements OidphuncService {

	@Autowired
	private OidphuncRepository oidphuncRepository;

	@Autowired
	private EliteProposedMovementService eliteProposedMovementService;

	@Autowired
	private NonAssociationService nonAssociationService;

	@Autowired
	private TagUtilsService tagutils;

	@Autowired
	private TagSecurityService tagsecurity;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 *
	 */

	public List<OffenderProposedIntlocs> propMoveExecuteQuery(OffenderProposedIntlocs searchRecord) {

		List<OffenderProposedIntlocs> returnList = oidphuncRepository.propMoveExecuteQuery(searchRecord);
		String commentRole = tagsecurity.checkPrivilege("P_COMMENT", searchRecord.getCreateUserId()!=null?  searchRecord.getCreateUserId():null);
		for (OffenderProposedIntlocs bean : returnList) {
			bean.setFromLivUnitidDesc(tagutils.getLivingUnitDescp(
					bean.getFromLivingUnitId() != null ? bean.getFromLivingUnitId().intValue() : null));
			bean.setCommentRole(commentRole);
			List<LivingUnits> nodesCurList = oidphuncRepository.getNodesCur(bean.getToLivingUnitId());
			int i = 1;
			for (LivingUnits bo : nodesCurList) {
				if (i == 1) {
					bean.setLevelOneId(bo.getLivingUnitId() != null ? bo.getLivingUnitId().longValue() : null);
				} else if (i == 2) {
					bean.setLevelTwoId(bo.getLivingUnitId() != null ? bo.getLivingUnitId().longValue() : null);
				} else if (i == 3) {
					bean.setLevelThreeId(bo.getLivingUnitId() != null ? bo.getLivingUnitId().longValue() : null);
				} else {
					bean.setLevelFourId(bo.getLivingUnitId() != null ? bo.getLivingUnitId().longValue() : null);
				}
				i++;
			}
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer propMoveCommit(OffenderProposedIntlocsCommitBean commitBean) {
		int liReturn = 0;
		List<OffenderProposedIntlocs> listObj = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (OffenderProposedIntlocs bean : commitBean.getInsertList()) {
				long locSeqId = eliteProposedMovementService.getLocationSeq(bean.getOffenderBookId().intValue());
				bean.setLocationSeq(locSeqId);
				listObj = new ArrayList<>();
				listObj.add(bean);
				liReturn = oidphuncRepository.propMoveInsertOffenderProposedIntlocs(listObj);
			}
			if (liReturn == 1) {
				postinsertLocChngDtls(commitBean.getInsertList());
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oidphuncRepository.propMoveUpdateOffenderProposedIntlocs(commitBean.getUpdateList());
		}
		return liReturn;
	}
	/**
	 * Insert the records from database table
	 *
	 * @param insertList
	 *
	 * @throws SQLException
	 */
	public Integer postinsertLocChngDtls(List<OffenderProposedIntlocs> insertList) {
		Integer liReturn = 0;
		for (OffenderProposedIntlocs bean : insertList) {
			OffenderLocChngDtls insrtObj = new OffenderLocChngDtls();
			insrtObj.setOffenderBookId(bean.getOffenderBookId().intValue());
			insrtObj.setLocationSeq(bean.getLocationSeq().intValue());
			insrtObj.setDetailSeq(1);
			insrtObj.setRecordedBy(bean.getCreateUserId());
			insrtObj.setRecordedDate(bean.getCreateDatetime());
			insrtObj.setStatusCode(ApplicationConstants.NEW);
			insrtObj.setTxnStatus(ApplicationConstants.PEN);
			insrtObj.setCreateUserId(bean.getCreateUserId());
			insrtObj.setCreateDatetime(bean.getCreateDatetime());
			insrtObj.setAppRsn(ApplicationConstants.DOT);
			insrtObj.setTxnRsn(ApplicationConstants.DOT);
			liReturn = eliteProposedMovementService.insertLocChngDtls(insrtObj);
		}
		return liReturn;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgFromLivUnitRecordGroup() {
		return oidphuncRepository.rgFromLivUnitRecordGroup();

	}

	@Override
	public List<InternalScheduleReasons> rgTypeLivUnitRecordGroup() {
		return oidphuncRepository.rgTypeLivUnitRecordGroup();
	}

	@Override
	public List<InternalScheduleReasons> rgReasonLivUnitRecordGroup(String internalScheduleType) {
		return oidphuncRepository.rgReasonLivUnitRecordGroup(internalScheduleType);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLevelOneRecordGroup(String agyLocid) {
		return oidphuncRepository.rgLevelOneRecordGroup(agyLocid);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLevelTwoRecordGroup(BigDecimal livingUnitId) {
		return oidphuncRepository.rgLevelTwoRecordGroup(livingUnitId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLevelThreeRecordGroup(BigDecimal livingUnitId) {
		return oidphuncRepository.rgLevelThreeRecordGroup(livingUnitId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLevelFourRecordGroup(BigDecimal livingUnitId) {
		return oidphuncRepository.rgLevelFourRecordGroup(livingUnitId);

	}

	@Override
	public OffenderLocChngDtls getStatuses(OffenderProposedIntlocs searchBean) {
		OffenderLocChngDtls returnObj = new OffenderLocChngDtls();
		List<OffenderLocChngDtls> appObj = eliteProposedMovementService.latestStatusesIntlocs(ApplicationConstants.APP,
				searchBean.getOffenderBookId().intValue(), searchBean.getLocationSeq().intValue());
		if (appObj.size() > 0) {
			returnObj.setStatusCode(appObj.get(0).getStatusCode());
			if (!"ApplicationConstants.DOT".equals(appObj.get(0).getAppRsn())) {
				returnObj.setAppRsn(appObj.get(0).getAppRsn());
			}
		}
		List<OffenderLocChngDtls> txnObj = eliteProposedMovementService.latestStatusesIntlocs(ApplicationConstants.TXN,
				searchBean.getOffenderBookId().intValue(), searchBean.getLocationSeq().intValue());
		if (txnObj.size() > 0) {
			returnObj.setTxnStatus(txnObj.get(0).getTxnStatus());
			if (!"ApplicationConstants.DOT".equals(txnObj.get(0).getTxnRsn())) {
				returnObj.setTxnRsn(txnObj.get(0).getTxnRsn());
			}

			if ("COMP".equals(txnObj.get(0).getTxnStatus())) {
				returnObj.setRecordedBy(txnObj.get(0).getRecordedBy());
				returnObj.setRecordedDate(txnObj.get(0).getRecordedDate());
				returnObj.setRecoredTime(txnObj.get(0).getRecordedDate());
			}
		}
		if (".".equals(returnObj.getAppRsn())) {
			returnObj.setAppRsn("");

		}
		if (".".equals(returnObj.getTxnRsn())) {
			returnObj.setTxnRsn("");
		}

		return returnObj;
	}

	@Override
	public OffenderProposedIntlocs checkNonAssociationAndSecurity(List<OffenderProposedIntlocs> listObj) {
		OffenderProposedIntlocs bean = new OffenderProposedIntlocs();
		for (OffenderProposedIntlocs obj : listObj) {
			String lvNonass = nonAssociationService.checkNonAssociation(BigDecimal.valueOf(obj.getOffenderId()),
					BigDecimal.valueOf(obj.getToLivingUnitId()));
			String lvchksec = nonAssociationService.checkSecurity(BigDecimal.valueOf(obj.getOffenderId()),
					BigDecimal.valueOf(obj.getToLivingUnitId()));
			if (lvNonass != null && "N".equals(lvNonass)) {
				bean.setLvReturnCheckNonAsso(lvNonass);
			}
			if (lvchksec != null && "N".equals(lvchksec)) {
				bean.setLvReturnCheckNonAsso(lvchksec);
			}
		}

		return bean;
	}
}