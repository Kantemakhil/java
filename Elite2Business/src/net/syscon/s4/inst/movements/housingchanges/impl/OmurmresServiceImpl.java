package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;
import net.syscon.s4.inst.movements.housingchanges.OmurmresRepository;
import net.syscon.s4.inst.movements.housingchanges.OmurmresService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.SystemPofilesTjnService;

/**
 * Class OmurmresServiceImpl
 */
@Service
public class OmurmresServiceImpl extends BaseBusiness implements OmurmresService {

	@Autowired
	private OmurmresRepository omurmresRepository;
	
	@Autowired
	private SystemPofilesTjnService systemProfileTjnService;

	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffenderBookingsT2Service OffenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService OffenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService OffendersBookVineIntfTrgService;
	/**
	 * Creates new OmurmresServiceImpl class Object
	 */
	public OmurmresServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkResBlResBlRefCod(final ReferenceCodes paramBean) {
		List<ReferenceCodes> referenceCodesList = (List<ReferenceCodes>) omurmresRepository
				.cgfkchkResBlResBlRefCod(paramBean);
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VHeaderBlock cgfkchkResBlResBlVHbF1(final VHeaderBlock paramBean) {
		final List<VHeaderBlock> vHeaderBlock = omurmresRepository.cgfkchkResBlResBlVHbF1(paramBean);
		return (VHeaderBlock) vHeaderBlock;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<ReserveBedLocations> resBlExecuteQuery(final ReserveBedLocations searchRecord) {
		final List<ReserveBedLocations> returnList = new ArrayList<>();
		final ReserveBedLocations returnListTemp = new ReserveBedLocations();
		if (searchRecord != null) {
			final VHeaderBlock beanObj = new VHeaderBlock();
			beanObj.setCreateUserId(searchRecord.getCreateUserId());
			beanObj.setOffenderId(searchRecord.getOffenderId());
			beanObj.setAgyLocId(searchRecord.getAgyLocId());
			final VHeaderBlock returnObj = omurmresRepository.displayItemsExecuteQuery(beanObj);
			returnListTemp.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
			returnListTemp.setLivingUnitCode(returnObj.getAgyLocId() + "-" + returnObj.getLivUnitDesc());
			returnListTemp.setLastName(returnObj.getLastName());
			returnListTemp.setFirstName(returnObj.getFirstName());
			returnListTemp.setFirstName(returnObj.getFirstName());
			returnListTemp.setLivingUnitDesc(returnObj.getLivingUnitDescription());
			returnListTemp.setOffenderBookId(returnObj.getOffenderBookId());
			returnList.add(returnListTemp);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstRES_BL
	 *
	 * @
	 */
	@Transactional
	public Integer resBlCommit(final ReserveBedLocationscommitBean commitBean) {
		List<ReserveBedLocations> deleteList = new ArrayList<>();
		deleteList = commitBean.getDeleteList();
		VHeaderBlock updateBean = new VHeaderBlock();
		BedAssignmentHistories insertBed = new BedAssignmentHistories();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(ReserveBedLocations bean:commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = omurmresRepository.resBlInsertReserveBedLocations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(ReserveBedLocations bean:commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = omurmresRepository.resBlUpdateReserveBedLocations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {

			for (final ReserveBedLocations obj : deleteList) {
				if(obj.getOffenderBookId()!= null){
					updateBean = new VHeaderBlock();
					updateBean.setLivingUnitId(obj.getLivingUnitId());
					updateBean.setOffenderBookId(obj.getOffenderBookId());
					updateBean.setModifyUserId(commitBean.getCreateUserId());
					OffenderBookings newRec = new OffenderBookings();
					BeanUtils.copyProperties(obj, newRec);
					newRec.setAgyLocId(obj.getAgyLocId());
					newRec.setOffenderBookId(obj.getOffenderBookId().longValue());
					newRec.setLivingUnitId(obj.getLivingUnitId());
					newRec.setModifyUserId(commitBean.getCreateUserId());
					List<OffenderBookings> oldRecord = omurmresRepository.getoffBookOldRec(obj.getOffenderBookId());
					OffenderBookings oldRec = oldRecord.get(0);
					OffenderBookingsT2Service.offenderBookingsT2(newRec, oldRec);
					liReturn = omurmresRepository.offBookingUpdate(updateBean);
					OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(newRec, oldRec, "UPDATING");
					offenderBookingsT7Service.offenderBookingsT7Trigger(newRec);
					OffendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(newRec,ApplicationConstants.UPDATING);
					insertBed = new BedAssignmentHistories();
					insertBed.setOffenderBookId(Integer.parseInt(obj.getOffenderBookId().toString()));
					if(obj.getLivingUnitId()!=null) {
					insertBed.setLivingUnitId(Integer.parseInt(obj.getLivingUnitId().toString()));
					}
					insertBed.setAssignmentDate(obj.getReserveUntilDate());
					insertBed.setAssignmentTime(obj.getReserveUntilDate());
					insertBed.setAssignmentReason(obj.getRemoveReason());
					insertBed.setCreateUserId(commitBean.getCreateUserId());
					final Integer seqVal = omurmresRepository.bedAhPreInsert(insertBed);
					insertBed.setBedAssignSeq(seqVal);
					liReturn = omurmresRepository.bedAhInsertBedAssignmentHistories(insertBed);
				}
				commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = omurmresRepository.resBlDeleteReserveBedLocations(commitBean.getDeleteList());
			}

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
		return omurmresRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = omurmresRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
			final String status="INSERTING";
			for(SystemProfiles bean:commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			//systemProfileTjnService.systemProfilesForInserting(commitBean.getInsertList(),status);

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = omurmresRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkResBlRemoveReasonRecordGroup() {
		return omurmresRepository.cgfkResBlRemoveReasonRecordGroup();

	}

}