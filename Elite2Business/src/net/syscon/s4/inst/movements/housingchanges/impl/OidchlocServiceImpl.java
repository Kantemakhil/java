package net.syscon.s4.inst.movements.housingchanges.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.movements.housingchanges.OidchlocRepository;
import net.syscon.s4.inst.movements.housingchanges.OidchlocService;
import net.syscon.s4.inst.movements.maintenance.OimmholoService;

import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OidchlocServiceImpl
 */
@Service
public class OidchlocServiceImpl extends BaseBusiness implements OidchlocService {
	private static Logger logger = LogManager.getLogger(OidchlocServiceImpl.class.getName());

	private static final String UPDATE = "UPDATING";
	@Autowired
	private OidchlocRepository oidchlocRepo;
	@Autowired
	private OmuavbedPkgService omuavbedService;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OimmholoService oimmholoService;
	
	@Autowired
	private NonAssociationService nonAssociationService;
	
	@Autowired
	private OciscataRepository ociscataRepository;

	/**
	 * Creates new OidchlocServiceImpl class Object
	 */
	public OidchlocServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<Object> offBkgOnCheckDeleteMaster(final BedAssignmentHistories paramBean) {
		return oidchlocRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Integer bedAhPreInsert(final BedAssignmentHistories paramBean) {
		return oidchlocRepo.bedAhPreInsert(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<BedAssignmentHistories> bedAhExecuteQuery(final BedAssignmentHistories searchRecord) {
		return oidchlocRepo.bedAhExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBED_AH
	 *
	 */
	@Transactional
	public Integer bedAhCommit(final BedAssignmentHistoriesCommitBean commitBean) {
		int liReturn = 0;
		Integer bedAssSeq = 0;
		BedAssignmentHistories bedAssignHist = new BedAssignmentHistories();
		final BedAssignmentHistories bedAsHisUp = new BedAssignmentHistories();
		List<BedAssignmentHistories> list = new ArrayList<BedAssignmentHistories>();
		final List<BedAssignmentHistories> listforUpdate = new ArrayList<BedAssignmentHistories>();
		if (commitBean.getInsertList().size() > 0) {
			bedAssignHist = new BedAssignmentHistories();
			list = commitBean.getInsertList();
			for (final BedAssignmentHistories obj : list) {
				bedAssignHist.setOffenderBookId(obj.getOffenderBookId());
				bedAssSeq = oidchlocRepo.bedAhPreInsert(bedAssignHist);
				obj.setBedAssignSeq(bedAssSeq);
				bedAsHisUp.setBedAssignSeq(bedAssSeq - 1);
				bedAsHisUp.setOffenderBookId(obj.getOffenderBookId());
				bedAsHisUp.setAssignmentEndDate(obj.getAssignmentDate());
				bedAsHisUp.setAssignmentEndTime(obj.getAssignmentTime());
				bedAsHisUp.setModifyUserId(commitBean.getCreateUserId());
				listforUpdate.add(bedAsHisUp);
			}
		}
		if (!listforUpdate.isEmpty()) {
			oidchlocRepo.bedAhUpdateBedAssignmentHistories(listforUpdate);
		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (BedAssignmentHistories bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String beforeiep=null;
			String iep=null;
			for (BedAssignmentHistories bean : list) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				try {
					beforeiep=oimmholoService.getIEPCode(bean.getLivingUnitId().longValue(), bean.getAgyLocId());
				}catch (Exception e) {
					beforeiep=e.getMessage();
					if(beforeiep != null) {
						iep=beforeiep.split(",")[0];
					}
					logger.error(this.getClass().getName()+" bedAhCommit"+e);
				}
			}
			liReturn = oidchlocRepo.bedAhInsertBedAssignmentHistories(list,iep);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidchlocRepo.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		return oidchlocRepo.rgAssignmentReasonRecordGroup();

	}

	/**
	 * method for query callings
	 */
	public LivingUnits cgfkchkBedAhBedAhVLiv(final LivingUnits paramBean) {
		return oidchlocRepo.cgfkchkBedAhBedAhVLiv(paramBean);
	}

	/**
	 * Perfomring basic Oracle form functions i.e.update in the database table
	 * 
	 * @Param commitBean
	 */
	@Transactional
	public Integer offBookingUpdate(VHeaderBlock bean) {
		Integer liReturn = 0;
		if (bean != null) {
			OffenderBookings oldBean = oidchlocRepo.getOldDataOffenderBookings(bean.getOffenderBookId().longValue());
			OffenderBookings newBean = new OffenderBookings();
			newBean.setActiveFlag(bean.getActiveFlag());
			newBean.setAgyLocId(bean.getAgyLocId());
			newBean.setAssignedStaffId(bean.getAssignedStaffId());
			newBean.setLivUnitDesc(bean.getLivingUnitDescription());
			newBean.setLivingUnitId(bean.getLivingUnitId());
			newBean.setOffenderBookId(bean.getOffenderBookId().longValue());
			newBean.setOffenderId(bean.getOffenderId());
			newBean.setCreateUserId(bean.getCreateUserId());
			newBean.setModifyUserId(bean.getCreateUserId());
			// Trigger call
			OffenderBookings bean1 = offenderBookingsT2Service.offenderBookingsT2(newBean, oldBean);
			liReturn = oidchlocRepo.offBookingUpdate(bean);
			// Trigger call
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldBean, newBean, UPDATE);
			// Trigger call
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(newBean, UPDATE);
			// Trigger call
			try {
				// Trigger call
				offenderBookingsT7Service.offenderBookingsT7Trigger(newBean);
			} catch (Exception e) {
				logger.error("offenderBookingsT7Trigger :" + e);
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the Date and Time from database table
	 *
	 * @param searchRecord
	 *
	 */
	public BedAssignmentHistories getMovementDateAndTime(final BedAssignmentHistories searchRecord) {
		return oidchlocRepo.getMovementDateAndTime(searchRecord);

	}

	/**
	 * below method is used to execute DB procedure to check Conflicts param
	 * objSearchDao returns BedAssignmentHistories
	 */
	public BedAssignmentHistories checkAllConficts(final BedAssignmentHistories searchRecord) {
		final BedAssignmentHistories returnList = new BedAssignmentHistories();
		try {
			String nonAssocationDetails = "";
			List<String> nonAssOffenderDetails = oidchlocRepo
					.getNonAssocationOffenderDetails(searchRecord.getOffenderId(), searchRecord.getLivingUnitId());
			if (nonAssOffenderDetails != null && nonAssOffenderDetails.size() > 0) {

				for (String str : nonAssOffenderDetails) {
					nonAssocationDetails = nonAssocationDetails + str + " ,";
				}
			}
			 oidchlocRepo.offenderDetailsByOffenderId(searchRecord.getOffenderId());
			if (nonAssocationDetails.length() > 0) {
			} else {
				
			}
			returnList.setSealFlag("EMPTYDATA");
			Map<String, Object> returnObject = omuavbedService.getConflictWarning(
					searchRecord.getOffenderBookId() != null ? BigDecimal.valueOf(searchRecord.getOffenderBookId())
							: null,
					searchRecord.getOffenderId() != null ? BigDecimal.valueOf(searchRecord.getOffenderId()) : null,
					BigDecimal.valueOf(searchRecord.getLivingUnitId()) , searchRecord.getAgyLocId() != null ? searchRecord.getAgyLocId() : null);
			
			
			returnList.setWarningMsg(
					returnObject.get("P_WARNING_MSG") != null ? String.valueOf(returnObject.get("P_WARNING_MSG"))
							: "null");
			returnList.setWarningPrompt(
					returnObject.get("P_WARNING_PROMPT") != null ? String.valueOf(returnObject.get("P_WARNING_PROMPT"))
							: "null");
			
			returnList.setInserted(oidchlocRepo.checkMoveAdminRoleForUser(searchRecord.getCreateUserId()));
		} catch (Exception e) {
			logger.error("checkAllConficts", e);
			returnList.setWarningMsg("null");
			returnList.setWarningPrompt("null");
			return returnList;
		}
		return returnList;
	}
	
	@Override
	public List<BedAssignmentHistories> checkNonIndGangConficts(List<BedAssignmentHistories> searchList) {
		List<Integer> offenderBookIdList = new ArrayList<Integer>();
		for (BedAssignmentHistories offebderBookId : searchList) {
			offenderBookIdList.add(offebderBookId.getOffenderBookId().intValue());
		}
		for (int i = 0; i < searchList.size(); i++) {
			List<Offenders> offenderInd = new ArrayList<Offenders>();
			List<Offenders> offenderGang = new ArrayList<Offenders>();
			List<OffenderNaDetails> nonAssList = nonAssociationService
					.getNonAssociationOffenderList(searchList.get(i).getOffenderBookId().intValue());
			List<OffenderStgAffiliations> nonAssListGang = nonAssociationService
					.getOffendersOfNonAssociationGroup(new BigDecimal(searchList.get(i).getOffenderBookId()));
			if (nonAssList != null && nonAssList.size() > 0) {
				for (OffenderNaDetails offenderNaDetails : nonAssList) {
					if (offenderBookIdList.contains(offenderNaDetails.getNsOffenderBookId().intValue())) {

						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderNaDetails.getNsOffenderBookId());
						offender.get(0).setOffenderBookId(offenderNaDetails.getNsOffenderBookId().longValue());
						offenderInd.addAll(offender);
					}
				}
			}

			if (nonAssListGang != null && nonAssListGang.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : nonAssListGang) {
					if (offenderBookIdList.contains(offenderStgAffiliations.getOffenderBookId().intValue())) {
						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderStgAffiliations.getOffenderBookId());
						offender.get(0).setOffenderBookId(offenderStgAffiliations.getOffenderBookId().longValue());
						offenderGang.addAll(offender);
					}

				}
			}
			if ((offenderGang != null && offenderGang.size() > 0) || (offenderInd != null && offenderInd.size() > 0)) {
				BedAssignmentHistories obj = oidchlocRepo.offenderName(searchList.get(i).getOffenderBookId());
				searchList.get(i).setOffenderName(obj.getOffenderName());
				searchList.get(i).setOffenderIdDisplay(obj.getOffenderIdDisplay());
			}
			
			if (offenderGang != null && offenderGang.size() > 0)
				searchList.get(i).setOffenderNonAssociationsByGang(offenderGang);
			else
				searchList.get(i).setOffenderNonAssociationsByGang(null);
			if (offenderInd != null && offenderInd.size() > 0)
				searchList.get(i).setOffenderNonAssociationsByInd(offenderInd);
			else
				searchList.get(i).setOffenderNonAssociationsByInd(null);
		}

		return searchList;
	}
}