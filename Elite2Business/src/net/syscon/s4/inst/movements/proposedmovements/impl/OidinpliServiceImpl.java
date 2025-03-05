package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.OidinpliRepository;
import net.syscon.s4.inst.movements.proposedmovements.OidinpliService;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMoves;
import net.syscon.s4.inst.movements.proposedmovements.beans.VHousingMovesCommitBean;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.pkgs.elite_proposed_movement.EliteProposedMovementService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oms_intake.OmsIntakeService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;

/**
 * Class OidinpliServiceImpl
 */
@Service
public class OidinpliServiceImpl implements OidinpliService {

	@Autowired
	private OidinpliRepository oidinpliRepository;

	@Autowired
	private TagUtilsService tagUtilsService;

	@Autowired
	private EliteProposedMovementService eliteProposedMovementService;
	
	@Autowired
	private NonAssociationService nonAssociationService;
	
	@Autowired
	private OmsIntakeService omsIntakeService;
	
	private static Logger logger = LogManager.getLogger(OidinpliServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VHousingMoves> extrMoveExecuteQuery(VHousingMoves searchRecord) {
		List<VHousingMoves> returnlist = new ArrayList<VHousingMoves>();
		try {
			returnlist = oidinpliRepository.extrMoveExecuteQuery(searchRecord);
			returnlist.forEach(ele -> {
				Long vCapacity = oidinpliRepository.capacityCur(Long.valueOf(ele.getToLivingUnitId().toString()));
				Long vOccupied = oidinpliRepository.occupiedCur(Long.valueOf(ele.getToLivingUnitId().toString()));
				Long lvBedMaxAssignSeq = oidinpliRepository
						.getMaxBedAssignSeqCur(Long.valueOf(ele.getOffenderBookId()));
				Long vBedSeq = oidinpliRepository.nextBedSeqCur(Long.valueOf(ele.getOffenderBookId()));
				Integer vCount = oidinpliRepository.poteSchCur(ele.getOffenderBookId());
				String commentRole = eliteProposedMovementService.ifRoleAssigned(searchRecord.getCreateUserId(),
						"P_COMMENT");
				ele.setNonAssoFlag(nonAssociationService.checkAnyNonAssociation(ele.getOffenderId().longValue()));
				if (vCount > 0) {
					ele.setPotSchFlag("Y");
				} else {
					ele.setPotSchFlag("N");
				}
				ele.setvCapacity(vCapacity);
				ele.setvOccupied(vOccupied);
				ele.setLvBedMaxAssignSeq(lvBedMaxAssignSeq);
				ele.setvBedSeq(vBedSeq);
				ele.setCommentRole(commentRole);
				ele.setSchedYn("N");
				if(ele.getSecurityLevel() == null) {
					ele.setSecurityLevel(omsIntakeService.omsIntakeGetOffenderSupervision(Long.valueOf(ele.getOffenderId()), searchRecord.getCreateUserId()));
				}
				if (ele.getLivingUnitId() != null) {
					ele.setCurrAgyDesc(
							tagUtilsService.getLivingUnitDescp(Integer.valueOf(ele.getLivingUnitId().toString())));
				} else {
					ele.setCurrAgyDesc(
							tagUtilsService.getLivingUnitDescp(Integer.valueOf(ele.getFromLivingUnitId().toString())));
				}
				if (ele.getFromLivingUnitId() != null) {
					ele.setFromAgyDesc(
							tagUtilsService.getLivingUnitDescp(Integer.valueOf(ele.getFromLivingUnitId().toString())));
				}
				if (ele.getToLivingUnitId() != null) {
					ele.setToAgyDesc(
							tagUtilsService.getLivingUnitDescp(Integer.valueOf(ele.getToLivingUnitId().toString())));
				}
				List<OffenderLocChngDtls> listObj = eliteProposedMovementService
						.latestStatusesIntlocs(ApplicationConstants.APP, ele.getOffenderBookId(), ele.getLocationSeq());

				if (listObj.size() > 0) {
					ele.setApprovalDate(listObj.get(0).getRecordedDate());
					ele.setStaffName(listObj.get(0).getRecordedBy());
					ele.setStatusCode(listObj.get(0).getStatusCode());
					ele.setAppReason(listObj.get(0).getAppRsn());
					ele.setTxnStatus(listObj.get(0).getTxnStatus());
					ele.setTxnRsn(listObj.get(0).getTxnRsn());
				}
			});
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " extrMoveExecuteQuery() ", e);
		}

		return returnlist;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstINMA_DET
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer inmaDetCommit(VHousingMoves commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidinpliRepository.inmaDetUpdateOffenderProposedIntlocs(commitBean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " inmaDetCommit() ", e);
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
	public List<OffenderLocChngDtls> statDetExecuteQuery(OffenderLocChngDtls searchRecord) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();

		List<OffenderLocChngDtls> newListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.NEW, searchRecord.getOffenderBookId(), searchRecord.getLocationSeq());
		if (newListObj.size() > 0) {
			newListObj.get(0).setChoice(ApplicationConstants.NEW);
			returnList.add(newListObj.get(0));
		}
		List<OffenderLocChngDtls> appListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.APP, searchRecord.getOffenderBookId(), searchRecord.getLocationSeq());
		if (appListObj.size() > 0) {
			appListObj.get(0).setChoice(ApplicationConstants.APP);
			returnList.add(appListObj.get(0));
		}
		List<OffenderLocChngDtls> creqListObj = eliteProposedMovementService.latestStatusesIntlocs(
				ApplicationConstants.CREQ, searchRecord.getOffenderBookId(), searchRecord.getLocationSeq());
		if (creqListObj.size() > 0) {
			creqListObj.get(0).setChoice(ApplicationConstants.CREQ);
			returnList.add(creqListObj.get(0));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> rgLocRecordGroup(String caseLoadId) {
		return oidinpliRepository.rgLocRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<InternalScheduleReasons> rgMoveReasonRecordGroup(String movementType) {
		return oidinpliRepository.rgMoveReasonRecordGroup(movementType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */

	public List<InternalScheduleReasons> rgMoveTypeRecordGroup() {
		return oidinpliRepository.rgMoveTypeRecordGroup();

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAT_DET
	 *
	 * @throws SQLException
	 */
	@Transactional
	@Override
	public Integer saveStatDet(OffenderLocChngDtls bean) {
		Integer insertCount = 0;
		try {
			bean.setCreateUserId(bean.getCreateUserId());
			insertCount = eliteProposedMovementService.insertLocChngDtls(bean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " saveStatDet() ", e);
		}
		return insertCount;
	}

	@Transactional
	@Override
	public Integer transactCommitQuery(VHousingMovesCommitBean commitBean) {
		OffenderLocChngDtls newObject = new OffenderLocChngDtls();
		commitBean.getUpdateList().forEach(data -> {
			if (data.getSchedYn().equals("Y")) {
				if (data.getOffenderBookId() != null) {

					try {
						BeanUtils.copyProperties(newObject, data);
					} catch (IllegalAccessException e) {
						logger.error("Exception occured in " + this.getClass().getName() + " transactCommitQuery() ",
								e);
					} catch (InvocationTargetException e) {
						logger.error("Exception occured in " + this.getClass().getName() + " transactCommitQuery() ",
								e);
					}
					data.setCreateUserId(commitBean.getCreateUserId());
					newObject.setAppRsn(null);
					newObject.setTxnRsn(".");
					newObject.setStatusCode("APP");
					newObject.setTxnStatus("COMP");
					newObject.setRecordedBy(data.getCreateUserId());
					newObject.setRecordedDate(data.getModifyDatetime());
					newObject.setCreateUserId(data.getCreateUserId());
					newObject.setModifyUserId(data.getCreateUserId());
					data.setModifyUserId(data.getCreateUserId());
					eliteProposedMovementService.insertLocChngDtls(newObject);
					oidinpliRepository.offBookUpdate(data);
					if (data.getLvBedMaxAssignSeq() > 0) {
						oidinpliRepository.bedAssignmentHistoriesUpdt(data);
					} else {
						oidinpliRepository.bedAssignmentHistoriesInsert(data);
					}
				}
			}
		});
		if (commitBean.getUpdateList().size() > 0) {
			return 1;
		} else {
			return null;
		}
	}

	@Override
	public List<ReferenceCodes> rgLocFromRecordGroup(String agyLocId) {
		return oidinpliRepository.rgLocFromRecordGroup(agyLocId);
	}

	@Override
	public List<ReferenceCodes> rgLocToRecordGroup(String agyLocId) {
		return oidinpliRepository.rgLocToRecordGroup(agyLocId);
	}
}