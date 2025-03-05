package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OiditranRepository;
import net.syscon.s4.inst.movementexternal.OiditranService;
import net.syscon.s4.inst.movementexternal.OidreleaRepository;
import net.syscon.s4.inst.movementexternal.OidtrwjuRepository;
import net.syscon.s4.pkgs.common.CommonService;
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
public class OiditranServiceImpl extends BaseBusiness implements OiditranService {

	@Autowired
	private OiditranRepository oiditranRepository;
	
	@Autowired
	private OidtrwjuRepository oidtrwjuRepository;
	
	@Autowired
	private OidreleaRepository oidreleaRepository;

	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private  OffenderBookingsBkadmTrgService  offenderBookingsBkadmTrgService;
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	
	@Autowired
	private OffenderExternalMovementsT5Service  offenderExternalMovementsT5Service;
	
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;
	
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	
	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;
	
	@Autowired
	private OffExtMvVineIntfTrgService  offExtMvVineIntfTrgService;
	
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	
	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	
	@Autowired
	private OffenderExternalMovementsT5Service offenderExtMovementsT5Service;
	
	@Autowired
	private CommonService commonService;
	
	private static Logger logger = LogManager.getLogger(OiditranServiceImpl.class.getName());
	/**
	 * Creates new OiditranServiceImpl class Object
	 */
	public OiditranServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = oiditranRepository.createFormGlobalscreateFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	@Override
	public void cgfkchkOffEmOffEmOffBkg(final OffenderBookings paramBean) {
       return;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyLoc(final AgencyLocations paramBean) {
		final AgencyLocations agencyLocations = oiditranRepository.cgfkchkOffEmOffEmAgyLoc(paramBean);
		return agencyLocations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public LivingUnits tohlocLov(final LivingUnits paramBean) {
		final LivingUnits livingUnits = (LivingUnits) oiditranRepository.tohlocLov(paramBean);
		return livingUnits;

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderBookings> nonassSecValProc(final OffenderBookings paramBean) {
		final List<OffenderBookings> offenderBookingsList = (List<OffenderBookings>) oiditranRepository.nonassSecValProc(paramBean);
		return offenderBookingsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public BedAssignmentHistories insIntoBedAssHist(final BedAssignmentHistories paramBean) {
		final BedAssignmentHistories bedAssignmentHistories = oiditranRepository.insIntoBedAssHist(paramBean);
		return bedAssignmentHistories;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderExternalMovements insIntoOffExtMovement(final OffenderExternalMovements paramBean) {
		final OffenderExternalMovements offenderExternalMovements = oiditranRepository.insIntoOffExtMovement(paramBean);
		return offenderExternalMovements;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Caseloads initForm(final Caseloads paramBean) {
		final Caseloads caseloads = new Caseloads();
		return caseloads;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<LivingUnits> populateLivingUnit(final LivingUnits paramBean) {
		final List<LivingUnits> livingUnitsList = oiditranRepository.populateLivingUnit(paramBean);
		return livingUnitsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CaseloadAdmOtherProfiles> getCountOfAgyInCase(final CaseloadAdmOtherProfiles paramBean) {
		final List<CaseloadAdmOtherProfiles> caseloadAdmOtherProfilesList = (List<CaseloadAdmOtherProfiles>) oiditranRepository
				.getCountOfAgyInCase(paramBean);
		return caseloadAdmOtherProfilesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public LivingUnits checkCapacityIndividual(final LivingUnits paramBean) {
		final LivingUnits livingUnits = (LivingUnits) oiditranRepository.checkCapacityIndividual(paramBean);
		return livingUnits;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<TransactionOperations> insertMasterRec(final TransactionOperations paramBean) {
		final List<TransactionOperations> transactionOperationsList = new ArrayList<>();
		return transactionOperationsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	/*
	 *
	 
	 */
	public TransactionOperations storGlobals(final TransactionOperations paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public CaseloadAdmAlertProfiles displayNecessaryAlerts(final CaseloadAdmAlertProfiles paramBean) {
		final CaseloadAdmAlertProfiles caseloadAdmAlertProfiles = oiditranRepository.displayNecessaryAlerts(paramBean);
		return caseloadAdmAlertProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffExm> offEmExecuteQuery(final String caseloadId) {
		final OffenderBookings beanObj = new OffenderBookings();
		final List<VOffExm> returnList = oiditranRepository.offEmExecuteQuery(caseloadId);
		for (final VOffExm obj : returnList) {
			beanObj.setOffenderBookId(Long.parseLong(obj.getOffenderBookId().toString()));
			final Offenders returnObj = oiditranRepository.cgfkchkOffEmOffEmOffBkg(beanObj);
			obj.setFirstName(returnObj.getFirstName());
			obj.setLastName(returnObj.getLastName());
			obj.setOffenderIdDisplay(returnObj.getOffenderIdDisplay());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 * @
	 */
	@Transactional
	public Integer offEmCommit(final OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		OffenderExternalMovements bookings=new OffenderExternalMovements();
		List<OffenderBookings> updateList = new ArrayList<OffenderBookings>();
		Long offenderBookId=null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				OffenderBookings bean=new OffenderBookings();
				bean.setCreateAgyLocId(obj.getFromAgyLocId());
				bean.setCreateUserId(obj.getCreateUserId());
				bean.setCreateDatetime(obj.getCreateDatetime());
				bean.setLivingUnitId(new BigDecimal(obj.getLivingUnitId().toString()));
				bean.setOffenderBookId(obj.getOffenderBookId());
				bean.setAgyLocId(obj.getDspDescription());
				bean.setModifyUserId(commitBean.getCreateUserId());
				updateList.add(bean);
				obj.setModifyUserId(commitBean.getCreateUserId());
				List<OffenderExternalMovements> insertVal = new ArrayList<OffenderExternalMovements>();
				List<OffenderExternalMovements> updatetVal = new ArrayList<OffenderExternalMovements>();
				updatetVal.add(obj);
				MovementReasons reasonsone=new MovementReasons();
				BeanUtils.copyProperties(obj, reasonsone);
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
				//update OFFENDER_EXTERNAL_MOVEMENTS
				liReturn = oiditranRepository.offEmUpdateOffenderExternalMovements(updatetVal);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(reasonsone, reasonsone, offenderBookId, "UPDATING");
				Integer sequenceVal = oiditranRepository
						.offExternalMovmentssgetMaxBookIdMovmentSeq(obj.getOffenderBookId());
				offenderBookId=obj.getOffenderBookId();
				BeanUtils.copyProperties(bookings, offenderBookId);
				bookings=obj;
				bookings.setCreateUserId(commitBean.getCreateUserId());
				obj.setMovementSeq(Long.parseLong(sequenceVal.toString()));
				obj.setActiveFlag("Y");
				obj.setMovementTime(dateService.getDBTime());
				obj.setMovementType("ADM");
				obj.setMovementReasonCode("INT");
				obj.setDirectionCode("IN");
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setToAgyLocId(obj.getToAgyLocId());
			offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
			insertVal.add(obj);
			//insert OFFENDER_EXTERNAL_MOVEMENTS
			liReturn = oiditranRepository.offEmInsertOffenderExternalMovements(insertVal);
			offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
			offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
			offenderExternalMovementsT6Service.offenderExternalMovementsT6(obj);
			MovementReasons reasons=new MovementReasons();
			BeanUtils.copyProperties(obj, reasons);
			offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, reasons, offenderBookId, "INSERTING");
			offenderExternalMovementsT8Service.updateObligationWR(offenderBookId, obj.getMovementType(),commitBean.getCreateUserId());
			offExtMvVineIntfTrgService.offExtMvVineIntfTrg(bookings);
			offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(obj, null);
			offenderExtMovementsTwfService.offenderExternalMovementsTrigger(obj);
			}
			
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderExternalMovements object : commitBean.getUpdateList()){
				object.setActiveFlag("N");
				object.setModifyUserId(commitBean.getCreateUserId());
				List<OffenderExternalMovements> list=new ArrayList<OffenderExternalMovements>();
				list.add(object);
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(object);
				liReturn = oiditranRepository.offEmUpdateVOffExm(list);
				offenderExtMovementsT5Service.offenderExternalMovementsT5(object);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(object);
				OffenderExternalMovements old= oidtrwjuRepository.getOffenderExternalMovements(object.getOffenderBookId(),object.getMovementSeq());
				if (old != null) {
					net.syscon.s4.im.beans.MovementReasons oldRef = new net.syscon.s4.im.beans.MovementReasons();
					BeanUtils.copyProperties(old, oldRef);
					net.syscon.s4.im.beans.MovementReasons newRef = new net.syscon.s4.im.beans.MovementReasons();
					BeanUtils.copyProperties(object, newRef);
					// OFFENDER_EXTERNAL_MOVEMENTS_T1
					offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldRef, newRef,
							old.getOffenderBookId(), "UPDATING");
				}
				BedAssignmentHistories obj=new BedAssignmentHistories();
				obj.setOffenderBookId(object.getOffenderBookId().intValue());
				obj.setLivingUnitId(Integer.parseInt(object.getLivingUnitId()));
				obj.setAssignmentDate(trunc(commitBean.getInsertList().get(0).getMovementTime()));
				obj.setAssignmentTime(commitBean.getInsertList().get(0).getMovementTime());
				obj.setCreateUserId(commitBean.getCreateUserId());
				commonService.insIntoBedAssHist(obj);
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oiditranRepository.offEmDeleteVOffExm(commitBean.getDeleteList());
		}
		if(updateList.size()>0 && liReturn!=0) {
		offBkgCommit(updateList);
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> moveRsnLovRecordGroup() {
		return oiditranRepository.moveRsnLovRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<CaseloadAdmOtherProfiles> cgfkOffEmToAgyLocIdRecordGroup(final String caseloadId) {
		List<CaseloadAdmOtherProfiles> refList = oiditranRepository.cgfkOffEmToAgyLocIdRecordGroup(caseloadId);
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
	public List<String> findToAgyLocIdList() {
		return oiditranRepository.findToAgyLocIdList();
	}
	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKG
	 *
	 * @
	 */
	@Transactional
	public Integer offBkgCommit(final List<OffenderBookings> commitBean) {
		int liReturn = 0;
		OffenderBookings bookings=new OffenderBookings();
		// updateRecords
			for(final OffenderBookings obj : commitBean){
				OffenderBookings olddta = oidreleaRepository
						.gettingOldData(BigDecimal.valueOf(obj.getOffenderBookId()) != null
								? BigDecimal.valueOf(obj.getOffenderBookId())
								: null);
				List<OffenderBookings> listData = new ArrayList<OffenderBookings>();
				bookings=obj;
				obj.setActiveFlag("Y");
				obj.setStatusReason("ADM-INT");
				obj.setInOutStatus("IN");
			offenderBookingsT2Service.offenderBookingsT2(obj, olddta);
			listData.add(obj);
			liReturn = oiditranRepository.offBkgCommit(listData);
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, obj, "INSERTING");
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(obj, "INSERTING");
			offenderBookingsT7Service.offenderBookingsT7Trigger(obj);
		}
		return liReturn;
	}

	@Override
	public CaseloadAdmOtherProfiles getCountOfAgyInCase(final String caseloadId) {
		final CaseloadAdmOtherProfiles bean = new CaseloadAdmOtherProfiles();
		final Integer returnValue = oiditranRepository.getCountOfAgyInCase(caseloadId);
		if(returnValue == 1){
			final String returnObj = oiditranRepository.oidadmisGetDefaults(caseloadId);
			if(returnObj!= null){
			final String[] list = returnObj.split("-");
			bean.setLivUnitId(list[1]);
			if(list[0] != null) {
				final String returnVal = oiditranRepository.getActiveAgyLocDesc(list[0]);
				if(returnVal != null){
					bean.setCode(list[0]);
				}
			}
			}
		}
		if(bean.getLivUnitId()!= null){
			final LivingUnits returnObj = oiditranRepository.populateLivingUnit(bean.getLivUnitId());
			bean.setLivUnitCode(returnObj.getLivingUnitCode());
			bean.setDspDescription(returnObj.getDescription());
		}
		return bean;
	}
	
	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
	
	@Override
	public Integer noOfBedAvailableInTheGivenLocation(BigDecimal living_unit_id) {
		return oiditranRepository.noOfBedAvailableInTheGivenLocation(living_unit_id);
	}

}