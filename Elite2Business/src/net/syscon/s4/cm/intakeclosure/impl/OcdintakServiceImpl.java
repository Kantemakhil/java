package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.community_supervision_tiers.OcdotrlvService;
import net.syscon.s4.cm.community_supervision_tiers.impl.OcdotrlvRepositoryImpl;
//import net.syscon.s4.cf.deductions.OcdofaccRepository;
//import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.cm.intakeclosure.OcdintakService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
//import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
//import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
//import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderResidence;
import net.syscon.s4.common.beans.OffenderResidenceCommitBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VHeaderBlock2CommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inst.workflow.maintenance.OcmcnperRepository;
//import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
//import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.pkgs.ocdintak.OcdintakPkgService;
import net.syscon.s4.pkgs.omkcopy.OmkcopyService;
import net.syscon.s4.pkgs.oms.OmsService;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT1Service;
import net.syscon.s4.triggers.OffenderBookingsT3Service;
import net.syscon.s4.triggers.OffenderBookingsT4Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderBookingsT8Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OcdintakServiceImpl@Service
 */
@Service
public class OcdintakServiceImpl extends BaseBusiness implements OcdintakService {

	private static Logger logger = LogManager.getLogger(OcdintakServiceImpl.class.getName());

	@Autowired
	private OcdintakRepository ocdintakRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	OffenderBookingAgyLocsT2Service OffenderBookingAgyLocsT2Service;
	
//	@Autowired
//	private OcdexpowRepository ocdexpowRepository;
//	@Autowired
//	private OcdofaccRepository ocdofaccRepository;
//	@Autowired
//	private OcdsupstRepository ocdsupstRepository;	
	@Autowired
	private OcdintakPkgService ocdintakService;
	@Autowired
	private OmkcopyService omkcopyService;
	
	@Autowired
	private OffenderBookingAgyLocsT1Service offenderBookingAgyLocsT1Service;
	@Autowired
	private OmsService omsService;
    
	@Autowired
	private OffenderBookingsT4Service offenderBookingsT4Service;
	
	@Autowired
	private OffenderBookingsT3Service offenderBookingsT3Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT1Service offenderBookingsT1Service;
	@Autowired
	private OffenderBookingsT8Service offenderBookingsT8Service;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OcdotrlvService ocdotrlvService;
	@Autowired 
	OcmpconfRepository ocmpconfRepository;
//	@Autowired
//	private OcdexpowRepository ocdexpowRepository;
//	@Autowired
//	private OcdofaccRepository ocdofaccRepository;
	
	/**
	 * Creates new OcdintakServiceImpl class Object
	 */
	public OcdintakServiceImpl() {
		// OcdintakServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<StaffMembers> cgfkchkOffBkgsOffBkgStaf(final StaffMembers paramBean) {
		return ocdintakRepository.cgfkchkOffBkgsOffBkgStaf(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkchkOffBkgsOffBkgRef(final ReferenceCodes paramBean) {
		return ocdintakRepository.cgfkchkOffBkgsOffBkgRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkchkOffBkgeOffBkgeAgy(final AgencyLocations paramBean) {
		return ocdintakRepository.cgfkchkOffBkgeOffBkgeAgy(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<AgencyLocations> cgfklkpOffBkgeOffBkgeAgy(final AgencyLocations paramBean) {
		return ocdintakRepository.cgfklkpOffBkgeOffBkgeAgy(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkchkOffBkgeOffBkge(final AgencyLocations paramBean) {
		return ocdintakRepository.cgfkchkOffBkgeOffBkge(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<AgencyLocations> cgfklkpOffBkgeOffBkge(final AgencyLocations paramBean) {
		return ocdintakRepository.cgfklkpOffBkgeOffBkge(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkchkOffBkgeOffBkgeRef(final ReferenceCodes paramBean) {
		return ocdintakRepository.cgfkchkOffBkgeOffBkgeRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfklkpOffBkgeOffBkgeRef(final ReferenceCodes paramBean) {
		return ocdintakRepository.cgfklkpOffBkgeOffBkgeRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkchkOffBkgeOffBkge(final ReferenceCodes paramBean) {
		return ocdintakRepository.cgfkchkOffBkgeOffBkge(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfklkpOffBkgeOffBkge(final ReferenceCodes paramBean) {
		return ocdintakRepository.cgfklkpOffBkgeOffBkge(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VHeaderBlock2> offBkgExecuteQuery(final VHeaderBlock2 searchRecord) {
		return ocdintakRepository.offBkgExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKG
	 *
	 */
	@Transactional
	public Integer offBkgCommit(final VHeaderBlock2CommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderBookings> offBkgsExecuteQuery(final OffenderBookings searchRecord) {
		final List<OffenderBookings> returnList = ocdintakRepository.offBkgsExecuteQuery(searchRecord);
		for (final OffenderBookings obj : returnList) {
			if (obj.getSealFlag() != null) {
				obj.setDspLastName(obj.getSealFlag());
			}
			if ("Y".equals(obj.getActiveFlag())) {
				obj.setInstStatus("ACTIVE");
			} else if ("N".equals(obj.getActiveFlag())) {
				obj.setInstStatus("INACTIVE");
			} else if (obj.getActiveFlag() == null) {
				obj.setInstStatus("NO BOOKING");
			}

			if ("Y".equals(obj.getCommunityActiveFlag())) {
				//final String returnVal = ocdintakRepository.getAgyStatus("1");
				final String returnVal = omsService.systemProfile("1");
				obj.setIntakeStatus(returnVal);
			} else if ("N".equals(obj.getCommunityActiveFlag())) {
				//final String returnVal = ocdintakRepository.getAgyStatus("2");
				final String returnVal = omsService.systemProfile("2");
				obj.setIntakeStatus(returnVal);
			} else if (obj.getCommunityActiveFlag() == null) {
				obj.setIntakeStatus("NO BOOKING");
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKGS
	 *
	 * 
	 */
	@Transactional
	public Integer offBkgsCommit(final OffenderBookingsCommitBean commitBean) {

		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderBookingEvent> offBkgeExecuteQuery(final OffenderBookingEvent searchRecord) {
		List<OffenderBookingEvent> returnList = ocdintakRepository.offBkgeExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKGE
	 *
	 * 
	 */
	@Transactional
	public Integer offBkgeCommit(final OffenderBookingEventCommitBean commitBean) {
		int liReturn = 0;
		String lvRec = "N";
		OffenderBookingEvent object = new OffenderBookingEvent();
		for (final OffenderBookingEvent obj : commitBean.getInsertList()) {
			obj.setCreateUserId(commitBean.getCreateUserId());
			if ("Y".equals(obj.getCreateBookingFlag())) {
				object = offBkgeOnCommitTwo(obj);
				if (object != null && "2".equals(object.getSealFlag())) {
					liReturn = 2;
					return liReturn;
				}
			}
			liReturn = oldContact(obj);	
		}
		if (commitBean.getReportInsertList() != null && commitBean.getReportInsertList().size() > 0) {
			for (final OffenderResidence repObj : commitBean.getReportInsertList()) {
				repObj.setCreateUserId(commitBean.getCreateUserId());
				liReturn = insOffSch(repObj);
			}
		}
		logger.info(this.getClass().getName()+" defaultTierLevelAfterCustodyIntake method call start");
		ocdotrlvService.defaultTierLevelAfterCustodyIntake(commitBean.getInsertList().get(0).getOffenderBookId(), commitBean.getInsertList().get(0).getCreateUserId());
		logger.info(this.getClass().getName()+" defaultTierLevelAfterCustodyIntake method call end");
		return liReturn;
	}

	public Integer createBookingLocations(final OffenderBookingEvent obj) {
		int liReturn = 0;
		String lvRec = "N";
	
		obj.setCreateUserId(obj.getCreateUserId());
		obj.setModifyUserId(obj.getCreateUserId());
		if ("Y".equals(obj.getNbtOffenderBookId())) {
			lvRec = ocdintakRepository.createBookingLocationRecExistCur(obj);
			if ("Y".equals(lvRec)) {
				ocdintakRepository.updateOffAgyLoc(obj);
				OffenderBookingAgyLocs locs=new OffenderBookingAgyLocs();
				BeanUtils.copyProperties(obj, locs);
				locs.setAgyLocId(obj.getToAgyLocId());
				offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(locs, "UPDATING");
			} else {
				liReturn =ocdintakService.instOffBookAgyLoc(obj, obj.getCreateUserId());
				OffenderBookingAgyLocs locs=new OffenderBookingAgyLocs();
				BeanUtils.copyProperties(obj, locs);
				locs.setAgyLocId(obj.getToAgyLocId());
				offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(locs, "INSERTING");
				OffenderBookingAgyLocsT2Service.offenderBookingAgyLocsT2Triger(locs);
			}
		} else if ("N".equals(obj.getNbtOffenderBookId())) {
			liReturn =ocdintakService.instOffBookAgyLoc(obj, obj.getCreateUserId());
			OffenderBookingAgyLocs locs=new OffenderBookingAgyLocs();
			BeanUtils.copyProperties(obj, locs);
			locs.setAgyLocId(obj.getToAgyLocId());;
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(locs, "INSERTING");
			OffenderBookingAgyLocsT2Service.offenderBookingAgyLocsT2Triger(locs);
			
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderResidence> reportInExecuteQuery(OffenderResidence searchRecord) {
		return ocdintakRepository.reportInExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstREPORT_IN
	 *
	 * 
	 */
	@Transactional
	public Integer reportInCommit(final OffenderResidenceCommitBean commitBean) {
		int liReturn = 0;
		for (OffenderResidence obj : commitBean.getInsertList()) {
			liReturn = insOffSch(obj);
		}

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocdintakRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgToAgyLocRecordGroup(final String caseloadId, final Long offenderId) {
		final List<AgencyLocations> returnList = ocdintakRepository.rgToAgyLocRecordGroup(caseloadId, offenderId);
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgIntakeTypeRecordGroup() {
		return ocdintakRepository.rgIntakeTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgIntakeRsnRecordGroup() {
		return ocdintakRepository.rgIntakeRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgFromAgyLocRecordGroup() {
		return ocdintakRepository.rgFromAgyLocRecordGroup();
	}

	@Override
	public Date checkPrevBooking(final OffenderBookings obj) {
		return ocdintakRepository.checkPrevBooking(obj);
	}

	@Override
	public String wNewBlockInstanceintakeCase() {
		return ocdintakRepository.wNewBlockInstanceintakeCase();
	}

	@Override
	public Integer checkForActiveBooking(final VHeaderBlock obj) {
		final Integer returnValue = 0;
		final Integer actFlagValue = ocdintakRepository.checkForActiveBooking(obj);
		if (actFlagValue == 1) {
			final String value = ocdintakRepository.tagBookingCommActiveBookingExists(obj);
			if (value == null) {
				final String returnVal = ocdintakRepository.getProfileValue();
				if ("Y".equals(returnVal)) {
					return 1;
				} else {
					return returnValue;
				}
			}
		}
		return returnValue;
	}

	@Override
	public OffenderBookingEvent setNewContactFlag(final OffenderBookings searchBean) {
		final OffenderBookingEvent returnObj = new OffenderBookingEvent();
		if ("O".equals(searchBean.getBookingStatus())) {
			returnObj.setNbtOffenderBookId("N");
			returnObj.setNbtOffenderBookId2(searchBean.getBookingNo());
			returnObj.setOffenderBookId(searchBean.getOffenderBookId());
			returnObj.setDspOffenderBookId(searchBean.getBookingNo());

		} else if (searchBean.getBookingStatus() == null) {
			returnObj.setNbtOffenderBookId("Y");
			returnObj.setTempValue("Y");
			returnObj.setCheckFlag("Y");
		} else {
			String profileValue = ocdintakRepository.getProfileValueConstants("CLIENT", "REST_DUAL_BK");
			if (profileValue != null) {
				if ("Y".equals(profileValue)) {
					if (searchBean.getRootOffenderId() != null) {
						VHeaderBlock obj = new VHeaderBlock();
						obj.setRootOffenderId(searchBean.getRootOffenderId());
						Integer trueFlag = ocdintakRepository.checkForActiveBooking(obj);
						if (trueFlag == 1) {
							returnObj.setNbtOffenderBookId("N");
							returnObj.setNbtOffenderBookId2(searchBean.getBookingNo());
							returnObj.setOffenderBookId(searchBean.getOffenderBookId());
							returnObj.setDspOffenderBookId(searchBean.getBookingNo());
						}

					} else {
						String profileValueOne = ocdintakRepository.getProfileValueConstants("CLIENT", "INTAKE_NEWDE");
						if ("Y".equals(profileValueOne)) {
							returnObj.setNbtOffenderBookId("Y");
							returnObj.setTempValue("Y");
							returnObj.setCheckFlag("Y");

						} else if ("N".equals(profileValueOne)) {
							returnObj.setNbtOffenderBookId("N");
							returnObj.setNbtOffenderBookId2(searchBean.getBookingNo());
							returnObj.setOffenderBookId(searchBean.getOffenderBookId());
							returnObj.setDspOffenderBookId(searchBean.getBookingNo());
						}
					}
				}
			} else {
				String profileValueOne = ocdintakRepository.getProfileValueConstants("CLIENT", "INTAKE_NEWDE");
				if ("Y".equals(profileValueOne)) {
					returnObj.setNbtOffenderBookId("Y");
					returnObj.setTempValue("Y");
					returnObj.setCheckFlag("Y");
				} else if ("N".equals(profileValueOne)) {
					returnObj.setCreateBookingFlag("N");
					returnObj.setNbtOffenderBookId2(searchBean.getBookingNo());
					returnObj.setOffenderBookId(searchBean.getOffenderBookId());
					returnObj.setDspOffenderBookId(searchBean.getBookingNo());
				}
			}
		}
		returnObj.setCheckFlag("N");
		return returnObj;
	}

	@Override
	public OffenderBookingEvent getDefaultIntakeValues(final String intakeType, final String intakeReason) {
		final OffenderBookingEvent returnObj = new OffenderBookingEvent();
		final Integer returnVal = ocdintakRepository.getDefaultIntakeValuesCountCur(intakeType);
		if (returnVal == 1) {
			returnObj.setReasonCode("INTAKE");
		}
		final Integer returnValue = ocdintakRepository.getDefaultIntakeValuesCountCur(intakeReason);
		if (returnValue == 1) {
			final String val = ocdintakRepository.getRefCodeValues(intakeReason);
			returnObj.setDspDescription4(val);
		}
		return returnObj;
	}

	@Override
	public Integer intakeCaseMultiple(final VHeaderBlock searchBean) {
		final Integer actBkgs = ocdintakRepository.intakeCaseMultipleActBkgs(searchBean);
		final Integer numOfComAgy = ocdintakRepository.intakeCaseMultipleNumOfComAgy(searchBean);
		if (numOfComAgy == 0) {
			return 1;
		} else if (actBkgs == numOfComAgy) {
			return 2;
		}
		return null;
	}

	public String getDspDescription() {
		return ocdintakRepository.getDspDescription();
	}

	public String intakeCaseactBkgExistFlag(VHeaderBlock obj) {
		return ocdintakRepository.intakeCaseactBkgExistFlag(obj);
	}

	public String toAgyLoc(OffenderBookingEvent obj) {
		return ocdintakRepository.toAgyLoc(obj);
	}

	@Override
	public String getTrustValues(final String client, String intakeTrust) {
		String returnObj = null;
		returnObj = ocdintakRepository.getProfileValueConstants(client, intakeTrust);
		if (returnObj == null) {
			returnObj = "Y";
		}
		return returnObj;
	}

	public OffenderBookingEvent offbkgeCommitOne(final OffenderBookingEvent returnObj) {
		if ("Y".equals(returnObj.getNbtOffenderBookId())) {
			String getProfileValue = ocdintakRepository.getNvlProfileVal();
			if ("N".equals(getProfileValue)) {
				if (returnObj.getDspOffenderBookId() == null) {
					returnObj.setSealFlag("Error : Booking No. cannot be null");
					return returnObj;
				}
			}
		}
		return returnObj;

	}

	@Override
	public Integer oldContact(final OffenderBookingEvent object) {
		Integer returnVal = 0;
		String pCommStatus;
		String pBookingNo = null;
		Integer pBookIdOld;
		if(object.getReasonCode() != null) {
			List<ReferenceCodes> list=ocdintakRepository.getBookingEventCode(object.getReasonCode());
			if(list != null && list.size() > 0) {
				object.setBookingEventCode(list.get(0).getCode());
			}
		}
		String lvBookingType = ocdintakRepository.oldContactCheckBookingCur(object);
		String lvDate = ocdintakRepository.oldContactLvYearCur();
		final String pCommStatusOne = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS1);
		String pCommStatusTwo = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS2);
		Integer staffId = ocdintakRepository.oldContactGetStaffId(object.getCreateUserId());
		Integer pBookCount = ocdintakRepository.oldContactPbookCount(object);
		if (pBookCount > 1) {
			String offenderBookId = ocdintakRepository.getOldOffenderBookId(object);
			String sentStatus = caclucateSentStatus(offenderBookId);
			if(ApplicationConstants.PROB_STATUS1.equals(sentStatus)) {
				pCommStatus = pCommStatusOne;
			} else {
			pCommStatus = pCommStatusTwo;
			}
		} else {
			pCommStatus = pCommStatusOne;
		}
		if ("N".equals(object.getNbtOffenderBookId())) {
			pBookingNo = object.getNbtOffenderBookId2();

			final Integer eventSeq = ocdintakRepository.oldContactGetEventSeq(object);
			object.setEventSeq(eventSeq);
			if (lvBookingType == null) {
				lvBookingType = "COMM";
			}
			OffenderBookings bookings=new OffenderBookings();
			BeanUtils.copyProperties(object, bookings);
			pBookCount =ocdintakService.updateOffBookings(object.getToAgyLocId(), bookings, bookings.getCreateUserId(), staffId, pCommStatus, lvBookingType);
			pBookCount = createBookingLocations(object);
		} else if ("Y".equals(object.getNbtOffenderBookId())) {
			String pProfileValue = ocdintakRepository.getProfileValueConstants("CLIENT", "BOOKING_NO");

			if (object.getDspOffenderBookId() != null) {
				pBookingNo = object.getDspOffenderBookId();
			} else if (object.getDspOffenderBookId() == null) {
				if ("Y".equals(pProfileValue)) {
					pBookingNo = ocdintakRepository.oidadmisGetNewBookingNo();
				} else {
					return 5;
				}
			}

			object.setNbtOffenderBookId2(pBookingNo);

			object.setOffenderBookId(ocdintakRepository.getNewBookId());
			final Integer eventSeq = ocdintakRepository.oldContactGetEventSeq(object);
			object.setEventSeq(eventSeq);
			pBookIdOld = ocdintakRepository.getLatestBooking(object);
			object.setpBookIdOld(pBookIdOld);
			final String lvcaseloadType = ocdintakRepository.newContactCur(object.getCreateUserId());
			object.setBookingType(lvcaseloadType);
			object.setInOutStatus("IN");
			object.setStaffId(Long.valueOf(staffId));
			object.setpCommStatus(pCommStatus);
			object.setCreateDatetime(eliteDateService.getDBTime());
			returnVal =ocdintakService.instOffBooking(object, object.getCreateUserId());

			pBookCount = createBookingLocations(object);
			if (pBookIdOld != null) {
				
				returnVal=omkcopyService.copyBookingData("CRI", object.getReasonCode(),(long) object.getpBookIdOld(),(long) object.getOffenderBookId(), object.getCreateUserId());
			}
		}
		OffenderBookings bookings=new OffenderBookings();
		BeanUtils.copyProperties(object, bookings);
		bookings.setAgyLocId(object.getToAgyLocId());
		bookings.setOffenderId(new BigDecimal(object.getOffenderId()));
		ocdintakRepository.offBkgeInsertOffenderBookingEvents(object);
		return pBookCount;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKGE
	 *
	 * 
	 */
	@Transactional
	public OffenderBookingEvent offBkgeOnCommitTwo(final OffenderBookingEvent object) {
		OffenderBookingEvent obj = new OffenderBookingEvent();
		OffenderBookingEvent returnValue = null;
		if ("Y".equals(object.getCreateBookingFlag())) {
			String trustcaseloadId = ocdintakRepository.onCommitcommunityTrustCaseloadId(object.getCaseloadId());
			obj.setCaseloadId(trustcaseloadId);
			obj.setRootOffenderId(object.getRootOffenderId());
			obj.setCreateUserId(object.getCreateUserId());
			obj.setModifyUserId(object.getCreateUserId());
			returnValue=ocdintakService.processOcdintakTrust(obj);
		}
		return returnValue;
	}

	public Integer insOffSch(final OffenderResidence object) {
		return ocdintakService.insOffSchedule(object);

	}

	@Override
	public String getProfileValueDisabled() {
		String returnData = ocdintakRepository.getProfileValueDisabled();
		if (returnData == null) {
			returnData = "1";
		}
		return returnData;
	}

	@Override
	public String getProfileTrustValueDisabled() {
		String returnData = ocdintakRepository.getProfileTrustValueDisabled();
		if (returnData == null) {
			returnData = "1";
		}
		return returnData;
	}

	public Integer newContact(final OffenderBookingEvent object) {
		Integer returnVal = 0;
		String lvBookingType = ocdintakRepository.oldContactCheckBookingCur(object);
		String lvCommStatus;
		String lvBookingNo = null;
		Long lvBookingId;
		final OffenderBookingEvent returnObj = new OffenderBookingEvent();
		final String lvcaseloadType = ocdintakRepository.newContactCur(object.getCreateUserId());
		final String returnData = ocdintakRepository.oldContactCheckBookingCur(object);
		Integer staffId = ocdintakRepository.oldContactGetStaffId(object.getCreateUserId());
		String lvCommStatusOne = ocdintakRepository.getCommStatusOne();
		String lvCommStatusTwo = ocdintakRepository.getCommStatusTwo();
		Integer lvBookingCount = ocdintakRepository.newContactCheckBookingCur(object);
		if (lvBookingCount > 1) {
			lvCommStatus = lvCommStatusTwo;
		} else {
			lvCommStatus = lvCommStatusOne;
		}
		if ("N".equals(object.getNbtOffenderBookId())) {
			lvBookingNo = object.getNbtOffenderBookId2();
			lvBookingId = object.getOffenderBookId();
			final Integer eventSeq = ocdintakRepository.oldContactGetEventSeq(object);
			object.setEventSeq(eventSeq);
			if (lvBookingType == null) {
				lvBookingType = "COMM";
			}
			returnVal = ocdintakRepository.updateOffenderBookings(object.getToAgyLocId(), staffId, lvCommStatus,
					lvBookingType, object.getOffenderBookId());
			returnVal = createBookingLocations(object);
		} else if ("Y".equals(object.getNbtOffenderBookId())) {
			String lvDate = ocdintakRepository.oldContactLvYearCur();
			lvBookingNo = String.valueOf(ocdintakRepository.oldContactGetLvBookingNo(lvDate));

			object.setNbtOffenderBookId2(lvBookingNo);
			lvBookingId = object.getOffenderBookId();
			object.setOffenderBookId(ocdintakRepository.getNewBookId());
			final Integer eventSeq = ocdintakRepository.oldContactGetEventSeq(object);
			object.setEventSeq(eventSeq);
			staffId = ocdintakRepository.oldContactGetStaffId(object.getCreateUserId());
			if (lvcaseloadType == null) {
				return 1;
			}
			object.getBookingType();
			object.setInOutStatus("OUT");
		//	returnVal = ocdintakRepository.instOffenderBooking(object);
			returnVal =ocdintakService.instOffBooking(object, object.getCreateUserId());
			lvBookingId = object.getOffenderBookId();
			returnVal = createBookingLocations(object);
		}
		return lvBookingCount;

	}
	
	@Override
	public Date getBackdatedAdmissionDate() {
		// TODO Auto-generated method stub
		Date date = null;
		String days = ocdintakRepository.getBackdatedAdmissionDate();
		if(days != null) {
		LocalDateTime today = eliteDateService.getDBTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		//today = today.plusDays(Long.parseLong(days));
		today = today.minusDays(Long.parseLong(days));
		date = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
		}
		return date;
	}

	@Override
	public String caclucateSentStatus(String offenderBookId) {
		String sentStatus = "";
		String formIdentifier = "{\"offenderBookId\":\""+offenderBookId+"\"}";
		try {
			List<OdynfrmSubmitDataBean> legalSummaryData = ocdintakRepository.getLegalSummaryData(formIdentifier);
			for(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum : legalSummaryData) {
				Map<String, String> formIdMapSum = new ObjectMapper().readValue(odynfrmSubmitDataBeanSum.getFormIdentifier(), HashMap.class);
				Map<String,List<Map<String,Object>>> formInfoJsonSum = new ObjectMapper().readValue(odynfrmSubmitDataBeanSum.getFormInfoJson(), HashMap.class);
				if(formInfoJsonSum.containsKey(ApplicationConstants.SENTENCE_DATES)) {
					List<Map<String, Object>> sentenceOrderDates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES);
					for(Map<String,Object> sentOrdDate : sentenceOrderDates) {
						String resultingStatus = ocmpconfRepository.getResultingStatus(sentOrdDate.get(ApplicationConstants.RESULTINGSTATUS).toString());
						if(sentOrdDate.containsKey(ApplicationConstants.SENTENCEORDERTYPE) && (ApplicationConstants.NCUS.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE)) ||
								ApplicationConstants.PAR.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE)) || ApplicationConstants.BAIL.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE))) &&
								"A".equals(resultingStatus)) {
							sentStatus = ApplicationConstants.PROB_STATUS1;
						} else {
							sentStatus = ApplicationConstants.PROB_STATUS2;
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " caclucateSentStatus and  Exception ", e);
		}
		return sentStatus;
	}
}