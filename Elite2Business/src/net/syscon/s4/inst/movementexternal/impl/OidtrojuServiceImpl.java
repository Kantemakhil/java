package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OidtrojuRepository;
import net.syscon.s4.inst.movementexternal.OidtrojuService;
import net.syscon.s4.pkgs.oidtroju.OidtrojuPkgService;
import net.syscon.s4.pkgs.payroll.PayrollService;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

@Service
public class OidtrojuServiceImpl extends BaseBusiness implements OidtrojuService {

	@Autowired
	private OidtrojuRepository oidtrojuRepository;

	@Autowired
	private EliteDateService dateService;
	@Autowired
	private OidtrojuPkgService oidtrojuService;

	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;
	
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	
	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;
	
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	
	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	
	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;
	
	@Autowired
	private PayrollService payrollService;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;

	/**
	 * Creates new OidtrojuServiceImpl class Object
	 */
	public OidtrojuServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer offEmPreInsert(final OffenderExternalMovements paramBean) {
		return oidtrojuRepository.offEmPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefTo(final ReferenceCodes paramBean) {
		return oidtrojuRepository.cgfkchkOffEmOffEmRefTo(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		return oidtrojuRepository.cgwhenNewFormInstance(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(final OffenderExternalMovements searchRecord) {
		return oidtrojuRepository.offEmExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 * @
	 */
	@Transactional
	public Integer  offEmCommit(final OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		OffenderExternalMovements beanObj = new OffenderExternalMovements();
		final BedAssignmentHistories bedassObj = new BedAssignmentHistories();
		final String operationType = "INSERTING";
		final String operation = "UPDATING";
		beanObj = commitBean.getInsertList().get(0);
		bedassObj.setAssignmentEndDate(beanObj.getMovementDate());
		bedassObj.setAssignmentEndTime(beanObj.getMovementTime());
		final Integer offBkgId = Integer.parseInt(beanObj.getOffenderBookId().toString());
		final VHeaderBlock vhbBeanObj = oidtrojuRepository.getVsHeadcurOffId(beanObj.getOffIdDisplay(),commitBean.getCreateUserId());
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				final Date date = oidtrojuRepository.offExmGetMaxMovDate(obj);
				if (date != null) {
					final Integer dateVal = obj.getMovementDate().compareTo(date);
					if (dateVal != 1) {
						liReturn = 2;
						return liReturn;
					}
				}
				final Integer resultSeq = oidtrojuRepository.offEmPreInsert(obj);
				obj.setMovementSeq(Long.parseLong(resultSeq.toString()));
			}
			offenderExternalMovementsT9Service.offenderExternalMovementsT9(beanObj); 
			liReturn = oidtrojuRepository.offEmInsertoffEm(commitBean.getInsertList());  
		final MovementReasons refBean = new MovementReasons(); 
			for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				refBean.setMovementReasonCode(obj.getMovementReasonCode());
				refBean.setMovementType(obj.getMovementType());
				refBean.setModifyUserId(commitBean.getCreateUserId());
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, refBean, obj.getOffenderBookId(), operationType);
				offenderExternalMovementsT8Service.updateObligationWR(obj.getOffenderBookId(), obj.getMovementType(),commitBean.getCreateUserId());
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(obj, null);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(obj);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(obj);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(obj);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
			}
		}

		 oidtrojuService.insNotification(beanObj, commitBean.getCreateUserId());
		if (liReturn == 1) {
			final MovementReasons returnObj = oidtrojuRepository.clostContactFlag();
			if ("Y".equals(returnObj.getCloseContactFlag())) {
				final OffenderBookings returnValue = oidtrojuRepository.commFlagCur(offBkgId);
				final OffenderBookings updateList = new OffenderBookings();
				updateList.setModifyUserId(commitBean.getCreateUserId());
				updateList.setOffenderBookId(returnValue.getOffenderBookId());
				final OffenderBookings oldRefData = oidtrojuRepository.getOldOffenderBookingsRecords(returnValue); 
				
				if ("N".equals(returnValue.getCommunityActiveFlag())) {
					offenderBookingsT2Service.offenderBookingsT2(updateList, oldRefData);
					oidtrojuRepository.offbkgUpdateQueryForN(updateList);
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldRefData, updateList, operation);
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(updateList,operation);
					offenderBookingsT7Service.offenderBookingsT7Trigger(updateList);
					
				} else if ("Y".equals(returnValue.getCommunityActiveFlag())) {
					offenderBookingsT2Service.offenderBookingsT2(updateList, oldRefData);
					oidtrojuRepository.offbkgUpdateQueryForY(updateList);
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldRefData, updateList, operation);
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(updateList,operation);
					offenderBookingsT7Service.offenderBookingsT7Trigger(updateList);
				} else {
					offenderBookingsT2Service.offenderBookingsT2(updateList, oldRefData);
					oidtrojuRepository.offbkgUpdateQuery(updateList);
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldRefData, updateList, operation);
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(updateList,operation);
					offenderBookingsT7Service.offenderBookingsT7Trigger(updateList);
				}
			}

			if (vhbBeanObj.getOffenderId() != null) {
				payrollService.updateWorkAsgnStatuses(vhbBeanObj.getOffenderId(), beanObj.getCaseloadId(), dateService.getDBTime(), beanObj.getCreateUserId());
			}
			final Integer bedAssignSeq = oidtrojuRepository.getMaxBedAssignSeqCur(offBkgId);
			if (bedAssignSeq != 0) {
				bedassObj.setModifyUserId(commitBean.getCreateUserId());
				bedassObj.setOffenderBookId(offBkgId);
				bedassObj.setBedAssignSeq(bedAssignSeq);
				oidtrojuRepository.updateBedAssignmentHistories(bedassObj);
			}
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 * @
	 */
	@Transactional
	public Integer offEmInsertoffEm(final List<OffenderExternalMovements> searchRecord) {
		final List<OffenderExternalMovements> insertList = new ArrayList<>();
		Integer liReturn = 0;
		for (final OffenderExternalMovements result : searchRecord) {
			final Integer resultSeq = oidtrojuRepository.offEmPreInsert(result);
			result.setMovementSeq(Long.parseLong(resultSeq.toString()));
			liReturn = oidtrojuRepository.offEmInsertoffEm(insertList);
		}
		return liReturn;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidtrojuRepository.sysPflExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffEmToProvStatCodeRecordGroup() {
		final List<ReferenceCodes> resultList = oidtrojuRepository.cgfkOffEmToProvStatCodeRecordGroup();
		if(Optional.ofNullable(resultList).isPresent()) {
			resultList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return resultList;
	}

	@Override
	public Integer checkMovementDate(OffenderExternalMovements obj) {
		Integer liReturn=0;
		final Date date = oidtrojuRepository.offExmGetMaxMovDate(obj);
		if (date != null) {
			final Integer dateVal = obj.getMovementDate().compareTo(date);
			if (dateVal != 1) {
				liReturn = 1;	
			}
		}
		return liReturn;
	}

}