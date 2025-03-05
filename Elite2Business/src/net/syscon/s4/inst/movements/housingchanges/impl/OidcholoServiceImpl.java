package net.syscon.s4.inst.movements.housingchanges.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.BedAssignmentHistoriesCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.housingchanges.OidcholoRepository;
import net.syscon.s4.inst.movements.housingchanges.OidcholoService;
import net.syscon.s4.inst.movements.housingchanges.beans.CourtMovementTmp;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

@Service
public class OidcholoServiceImpl extends BaseBusiness implements OidcholoService {

	@Autowired
	private OidcholoRepository oidcholoRepository;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	@Autowired
	private OffenderBookingsBkadmTrgService OffenderBookingsBkadmTrgService;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private EliteDateService  elitedateservice ;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OidcholoServiceImpl class Object
	 */
	public OidcholoServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> CgfkchkCrtMvTmpCrtMvTmp(final ReferenceCodes paramBean) {
		List<ReferenceCodes> referenceCodesList = oidcholoRepository.cgfkchkCrtMvTmpCrtMvTmp(paramBean);
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public AgencyLocations agyCgfkchkCrtMvTmpCrtMv(final AgencyLocations paramBean) {
		AgencyLocations agencyLocations = oidcholoRepository.cgfkchkCrtMvTmpCrtMv(paramBean);
		return agencyLocations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<LivingUnits> CgfklkpCrtMvTmpCrtMvTmp(final LivingUnits paramBean) {
		List<LivingUnits> livingUnitsList = oidcholoRepository.cgfklkpCrtMvTmpCrtMv(paramBean);
		return livingUnitsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<LivingUnits> CgfkchkCrtMvTmpCrtMv(final LivingUnits paramBean) {
		List<LivingUnits> livingUnitsList = oidcholoRepository.cgfkchkCrtMvTmpCrtMv(paramBean);
		return livingUnitsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<LivingUnits> CgfklkpCrtMvTmpCrtMv(final LivingUnits paramBean) {
		List<LivingUnits> livingUnitsList = oidcholoRepository.cgfklkpCrtMvTmpCrtMv(paramBean);
		return livingUnitsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<LivingUnits> untCgfkchkCrtMvTmpCrtMv(final LivingUnits paramBean) {
		List<LivingUnits> livingUnitsList = oidcholoRepository.cgfkchkCrtMvTmpCrtMv(paramBean);
		return livingUnitsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<VHeaderBlock> CgfkchkBedAhBedAhVhbF1(final VHeaderBlock paramBean) {
		List<VHeaderBlock> vHeaderBlockList = oidcholoRepository.cgfkchkBedAhBedAhVhbF1(paramBean);
		return vHeaderBlockList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<VHeaderBlock> CgfklkpBedAhBedAhVhbF1(final VHeaderBlock paramBean) {
		List<VHeaderBlock> vHeaderBlockList = oidcholoRepository.cgfklkpBedAhBedAhVhbF1(paramBean);
		return vHeaderBlockList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> CgfkchkBedAhBedAhRefCod(final ReferenceCodes paramBean) {
		List<ReferenceCodes> referenceCodesList = oidcholoRepository.cgfkchkBedAhBedAhRefCod(paramBean);
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public ReferenceCodes CgfklkpBedAhBedAhRefCod(final ReferenceCodes paramBean) {
		ReferenceCodes referenceCodes = oidcholoRepository.cgfklkpBedAhBedAhRefCod(paramBean);
		return referenceCodes;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VHeaderBlock> crtMvTmpExecuteQuery(final CourtMovementTmp searchRecord) {
		return oidcholoRepository.crtMvTmpExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRT_MV_TMP
	 *
	 * 
	 */
	@Transactional
	public Integer crtMvTmpCommit(final BedAssignmentHistoriesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<BedAssignmentHistories> insertList = commitBean.getInsertList();
			List<BedAssignmentHistories> insertData = new ArrayList<>();
			OffenderBookings bookingData = new OffenderBookings();
			List<OffenderBookings> bookingList = new ArrayList<>();
			for (BedAssignmentHistories data : insertList) {
				data.setCreateUserId(commitBean.getCreateUserId());
				insertData.add(data);
				liReturn = oidcholoRepository.crtMvTmpInsertCourtMovementTmp(insertData);
				insertData.clear();
				bookingData.setAgyLocId(data.getAgyLocId());
				bookingData.setOffenderBookId(data.getOffenderBookId().longValue());
				bookingData.setCgnbtBookingStatus(data.getDspDescription());
				bookingData.setModifyUserId(commitBean.getCreateUserId());
				bookingList.add(bookingData);
				List<OffenderBookings> offBookList = oidcholoRepository.getOldRecOffBooking(data.getOffenderBookId());
				OffenderBookings offBook = offBookList.get(0);
				offenderBookingsT2Service.offenderBookingsT2(bookingData, offBook);
				liReturn = oidcholoRepository.oidcholoCrtmvtmpUpdateOffbkgs(bookingList);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
				OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
				bookingData.setAgyLocId(null);
				bookingData.setOffenderBookId(null);
				bookingData.setCgnbtBookingStatus(null);
				bookingList.clear();
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<BedAssignmentHistories> insertList = commitBean.getUpdateList();
			List<BedAssignmentHistories> insertData = new ArrayList<>();
			OffenderBookings bookingData = new OffenderBookings();
			List<OffenderBookings> bookingList = new ArrayList<>();
			for (BedAssignmentHistories data : insertList) {
				data.setModifyUserId(commitBean.getCreateUserId());
				data.setAssignmentDate(new Date());
				data.setAssignmentTime(elitedateservice.getDBTime());
				data.setCreateUserId(commitBean.getCreateUserId());
				insertData.add(data);
				liReturn = oidcholoRepository.crtMvTmpUpdateCourtMovementTmp(insertData);
				liReturn = oidcholoRepository.crtMvTmpInsertCourtMovementTmp(insertData);
				insertData.clear();
				bookingData.setLivingUnitId(new BigDecimal(data.getLivingUnitId()));
				bookingData.setAgyLocId(data.getAgyLocId());
				bookingData.setOffenderBookId(data.getOffenderBookId().longValue());
				bookingData.setCgnbtBookingStatus(data.getDspDescription());
				bookingData.setModifyUserId(commitBean.getCreateUserId());
				bookingList.add(bookingData);
				List<OffenderBookings> offBookList = oidcholoRepository.getOldRecOffBooking(data.getOffenderBookId());
				OffenderBookings offBook = offBookList.get(0);
				 offenderBookingsT2Service.offenderBookingsT2(bookingData,offBook);
				liReturn = oidcholoRepository.oidcholoCrtmvtmpUpdateOffbkgs(bookingList);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
				OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
				bookingData.setAgyLocId(null);
				bookingData.setOffenderBookId(null);
				bookingData.setCgnbtBookingStatus(null);
				bookingList.clear();
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpMovementReasoRecordGroup() {
		List<ReferenceCodes> resultList = oidcholoRepository.cgfkCrtMvTmpMovementReasoRecordGroup();
		resultList.forEach(result -> {
			result.setParentCode(result.getDescription());
			result.setDescription(result.getCode());
		});
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup() {
		return oidcholoRepository.cgfkBedAhDspDescriptionRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkBedAhDspOffenderIdDiRecordGroup(String userName) {
		return oidcholoRepository.cgfkBedAhDspOffenderIdDiRecordGroup(userName);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> cgfkCrtMvTmpDspLiving4RecordGroup(final String agyLocId) {
		List<LivingUnits> resultResult = oidcholoRepository.cgfkCrtMvTmpDspLiving4RecordGroup(agyLocId);
		for (LivingUnits result : resultResult) {
			result.setCode(result.getLivingUnitCode());
			result.setDescription(result.getLivingUnitCode());
		}
		if(Optional.ofNullable(resultResult).isPresent()) {
			resultResult.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return resultResult;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> cgfkCrtMvTmpDspLiving3RecordGroup(final String agyLocId, final String livingUnitId) {
		List<LivingUnits> resultList = oidcholoRepository.cgfkCrtMvTmpDspLiving3RecordGroup(agyLocId, livingUnitId);
		resultList.forEach(result -> {
			result.setCode(result.getLivingUnitCode());
			result.setDescription(result.getLivingUnitCode());
		});
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

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpDspLiving2RecordGroup() {
		return oidcholoRepository.cgfkCrtMvTmpDspLiving2RecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpDspLivingUniRecordGroup() {
		return oidcholoRepository.cgfkCrtMvTmpDspLivingUniRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkCrtMvTmpAgyLocIdRecordGroup(final String caseload) {
		 List<ReferenceCodes> refList = oidcholoRepository.cgfkCrtMvTmpAgyLocIdRecordGroup(caseload);
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

	@Override
	public List<VHeaderBlock> oidcholoCgfklkpBedAhBedDatetimeProc(Integer livingUnitId, Integer offenderBookId) {
		return oidcholoRepository.oidcholoCgfklkpBedAhBedDatetimeProc(livingUnitId,offenderBookId);
	}

}