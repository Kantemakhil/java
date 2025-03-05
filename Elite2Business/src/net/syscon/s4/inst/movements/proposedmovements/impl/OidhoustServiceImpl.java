package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.OidhoustRepository;
import net.syscon.s4.inst.movements.proposedmovements.OidhoustService;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oms_intake.OmsIntakeService;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;


/**
 * Class OidhoustServiceImpl
 * 
 */
@Service
public class OidhoustServiceImpl implements OidhoustService {

	@Autowired
	private OidhoustRepository oidhoustRepository;

	@Autowired
	private EliteProposedMovementService eliteProposedMovementService;

	@Autowired
	private TagUtilsService tagutils;

	@Autowired
	private TagSecurityService tagsecurity;

	@Autowired
	private NonAssociationService nonassociation;

	@Autowired
	private OmsIntakeService omsintakeService;

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgAgyIdRecordGroup(String caseloadId) {
		return oidhoustRepository.rgAgyIdRecordGroup(caseloadId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId) {
		return oidhoustRepository.rgLocFromRecordGroup(agyLocId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgLocToRecordGroup(String agyLocId) {
		return oidhoustRepository.rgLocToRecordGroup(agyLocId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		return oidhoustRepository.rgMoveTypeRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgMoveReasonRecordGroup(String movementType) {
		return oidhoustRepository.rgMoveReasonRecordGroup(movementType);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgAppStatusRecordGroup() {
		return oidhoustRepository.rgAppStatusRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgTxnStatusRecordGroup() {
		return oidhoustRepository.rgTxnStatusRecordGroup();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VHousingMoves> housMoveExecuteQuery(VHousingMoves searchBean) {
		List<VHousingMoves> returnList = oidhoustRepository.housMoveExecuteQuery(searchBean);
		for (VHousingMoves bean : returnList) {

			String secLevel = omsintakeService.omsIntakeGetOffenderSupervision(Long.valueOf(bean.getOffenderBookId()),
					searchBean.getCreateUserId());
			if (secLevel != null) {
				bean.setSecLevel(secLevel);
			}
			String commentRole = tagsecurity.checkPrivilege("P_COMMENT", searchBean.getCreateUserId());
			bean.setCurrAgyDesc(tagutils
					.getLivingUnitDescp(bean.getLivingUnitId() != null ? bean.getLivingUnitId().intValue() : null));
			if (bean.getFromLivingUnitId() != null) {
				bean.setFromAgyDesc(tagutils.getLivingUnitDescp(
						bean.getFromLivingUnitId() != null ? bean.getFromLivingUnitId().intValue() : null));
			}
			if (bean.getToLivingUnitId() != null) {
				bean.setToAgyDesc(tagutils.getLivingUnitDescp(
						bean.getToLivingUnitId() != null ? bean.getToLivingUnitId().intValue() : null));
			}
			bean.setCommentRole(commentRole);
			List<OffenderLocChngDtls> listObj = eliteProposedMovementService
					.latestStatusesIntlocs(ApplicationConstants.APP, bean.getOffenderBookId(), bean.getLocationSeq());
			if (listObj.size() > 0) {
				if (ApplicationConstants.APP.equalsIgnoreCase(listObj.get(0).getStatusCode())) {
					bean.setApprovalDate(listObj.get(0).getRecordedDate());
				}
				bean.setStaffName(listObj.get(0).getRecordedBy());
				bean.setStatusCode(listObj.get(0).getStatusCode());
				bean.setAppReason(listObj.get(0).getAppRsn());
				bean.setTxnStatus(listObj.get(0).getTxnStatus());
				bean.setTxnRsn(listObj.get(0).getTxnRsn());
			}
		}
		return returnList;
	}

	@Override
	public List<OffenderProposedIntlocs> populateInmaDetails(VHousingMoves searchBean) {
		return oidhoustRepository.populateInmaDetails(searchBean);
	}
	/**
	 * Update the records from database table
	 *
	 * @param updateBean
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer inmateCommit(VHousingMoves updateBean) {
		return oidhoustRepository.inmateCommit(updateBean);
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderLocChngDtls> populatestatDetDetails(VHousingMoves searchBean) {
		String vNonAssoc = nonassociation.checkNonAssociation(searchBean.getFromLivingUnitId(),
				BigDecimal.valueOf(searchBean.getOffenderId()));
		String cancRole = tagsecurity.checkPrivilege(searchBean.getCreateUserId(), "P_INT_CANCEL");
		String appRole = tagsecurity.checkPrivilege(searchBean.getCreateUserId(), "P_INT_APPROVER");

		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		List<OffenderLocChngDtls> newListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.NEW, searchBean.getOffenderBookId(), searchBean.getLocationSeq());
		if (newListObj.size() > 0) {
			newListObj.get(0).setChoice(ApplicationConstants.NEW);
			newListObj.get(0).setAppRole(appRole);
			newListObj.get(0).setCancRole(cancRole);
			returnList.add(newListObj.get(0));
		}
		List<OffenderLocChngDtls> appListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.APP, searchBean.getOffenderBookId(), searchBean.getLocationSeq());
		if (appListObj.size() > 0) {
			appListObj.get(0).setChoice(ApplicationConstants.APP);
			appListObj.get(0).setNonAssoFlag(vNonAssoc);
			returnList.add(appListObj.get(0));
		}
		List<OffenderLocChngDtls> creqListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.CREQ, searchBean.getOffenderBookId(), searchBean.getLocationSeq());
		if (creqListObj.size() > 0) {
			creqListObj.get(0).setChoice(ApplicationConstants.CREQ);
			returnList.add(creqListObj.get(0));
		}
		List<OffenderLocChngDtls> cancListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.CANC, searchBean.getOffenderBookId(), searchBean.getLocationSeq());
		if (cancListObj.size() > 0) {
			cancListObj.get(0).setChoice(ApplicationConstants.CANC);
			returnList.add(cancListObj.get(0));
		}
		List<OffenderLocChngDtls> compListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.COMP, searchBean.getOffenderBookId(), searchBean.getLocationSeq());
		if (compListObj.size() > 0) {
			compListObj.get(0).setChoice(ApplicationConstants.COMP);
			returnList.add(compListObj.get(0));
		}
		return returnList;
	}
	/**
	 * Insert the records from database table
	 *
	 * @param insertBean
	 *
	 * @throws SQLException
	 */
	@Override
	public Integer statDetCommit(OffenderLocChngDtls insertBean) {
		return eliteProposedMovementService.insertLocChngDtls(insertBean);
	}

	@Override
	public String getCurInmAppStatus(VHousingMoves searchBean) {
		return oidhoustRepository.getCurInmAppStatus(searchBean);
	}

}
