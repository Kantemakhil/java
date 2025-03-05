package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Offenders;
//import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.OidpaoeService;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.movementexternal.OidreleaRepository;
import net.syscon.s4.inst.movementexternal.OidreleaService;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsService;
import net.syscon.s4.pkgs.tag_termination.TagTerminationService;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderEscapesT1Service;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;


/**
 * Class OidreleaServiceImpl
 */
@Service
public class OidreleaServiceImpl extends BaseBusiness implements OidreleaService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidreleaServiceImpl.class.getName());
	@Autowired
	private OidreleaRepository oidreleaRepository;
	@Autowired
	private TagTerminationService tagTerminationService;
	@Autowired
	private OmsMovementsService omsMovementsService;
	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;
	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	@Autowired
    private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	@Autowired
	private OffenderEscapesT1Service offenderEscapesT1Service;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OidpaoeService oidParoleService;
	@Autowired
	private OcdintakRepository ocdintakRepository;


	/**
	 * Creates new OidreleaServiceImpl class Object
	 */
	public OidreleaServiceImpl() {
		// OidreleaServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String offEmPreInsertc(final OffenderExternalMovements paramBean) {
		return oidreleaRepository.offEmPreInsertc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkOffEmOffEmRefMovc(final ReferenceCodes paramBean) {
		return oidreleaRepository.cgfkchkOffEmOffEmRefMovc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkOffEmOffEmMoveRsc(final MovementReasons paramBean) {
		return oidreleaRepository.cgfkchkOffEmOffEmMoveRsc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<MovementReasons> cgfkchkOffEmOffEmMovec(final MovementReasons paramBean) {
		return oidreleaRepository.cgfkchkOffEmOffEmMovec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Dual> cgwhenNewFormInstancec(final Dual paramBean) {
		return oidreleaRepository.cgwhenNewFormInstancec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemProfiles> callToShowFingerprint(final SystemProfiles paramBean) {
		return oidreleaRepository.callToShowFingerprint(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			final OffenderExternalMovements searchRecord) {
		return oidreleaRepository.offEmSearchOffenderExternalMovements(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderExternalMovements
	 */
	@Transactional
	public Integer offEmInsertOffenderExternalMovements(final OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		Integer returnType = 0;
		final OffenderEscapes escBean = new OffenderEscapes();
		final List<OffenderEscapes> escInsertList = new ArrayList<>();

		
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			MovementReasons newRef = new MovementReasons();
			Offenders offenders = new Offenders();
			MovementReasons oldRef = new MovementReasons();
			for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
				final String returnValue = oidreleaRepository.escapeCursor(obj.getMovementReasonCode());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
				obj.setModifyUserId(commitBean.getCreateUserId());
				returnType = oidreleaRepository.offEmUpdateOffenderExternalMovements(obj);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
				String operationType="UPDATING";
				newRef.setMovementType(obj.getMovementType());
				newRef.setMovementReasonCode(obj.getMovementReasonCode());
				newRef.setModifyUserId(obj.getCreateUserId());
				oldRef=	oidreleaRepository.gettingOldDataOffenderExternal(obj.getOffenderBookId(),obj.getMovementSeq());
				if(oldRef !=null) {
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldRef, newRef, obj.getOffenderBookId(), operationType);
				}
				if (returnValue != null && "Y".equals(returnValue)) {
					escBean.setOffenderBookId(Integer.parseInt(obj.getOffenderBookId().toString()));
					escBean.setEscapeDate(obj.getMovementDate());
					escBean.setEscapeTime(obj.getMovementTime());
					escBean.setAdjustSentenceFlag("N");
					escBean.setInCompanyFlag("N");
					escBean.setModifyUserId(commitBean.getCreateUserId());
					escBean.setEscapeMovementReason(obj.getMovementReasonCode());
					escBean.setCreateUserId(commitBean.getCreateUserId());
					escBean.setEscapeAgyLocId(obj.getFromAgyLocId());//S4-24274
					if (obj.getCommentText() != null) {
						escBean.setEscapeCommentText(obj.getCommentText());
					} else {
						escBean.setEscapeCommentText("Auto inserted by Release");
					}
					escInsertList.add(escBean);
					OffenderEscape offenderescape=new OffenderEscape();
					offenderescape.setEscapeDate(escBean.getEscapeDate());
					offenderescape.setEscapeTime(escBean.getEscapeTime());
					offenderEscapesT1Service.offenderEscapesT1(offenderescape);
					final Integer escreturnValue = oidreleaRepository.postInsertEscape(escInsertList);
				}
	
			}
			for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				String operationType="INSERTING";
			    obj.setModifyUserId(commitBean.getCreateUserId());
			    obj.setCreateUserId(commitBean.getCreateUserId());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
				commitBean.getInsertList().get(0).setCreateUserId(commitBean.getCreateUserId());
				liReturn = oidreleaRepository.offEmInsertOffenderExternalMovements(commitBean.getInsertList());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldRef, newRef, obj.getOffenderBookId(), operationType);
				offenderExternalMovementsT8Service.updateObligationWR(obj.getOffenderBookId(), obj.getMovementType(),commitBean.getCreateUserId());
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(obj, offenders);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(obj);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(obj);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(obj);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);

			}

         for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
				OffenderBookings  olddta=oidreleaRepository.gettingOldData(BigDecimal.valueOf(obj.getOffenderBookId()));
				OffenderBookings  offenderBooking=new OffenderBookings();
				offenderBooking.setAgyLocId(obj.getToAgyLocId());
				offenderBooking.setLivingUnitId(obj.getLivingUnitId() != null ? new BigDecimal(obj.getLivingUnitId()) :null);
				offenderBooking.setOffenderBookId(obj.getOffenderBookId().longValue());
				offenderBooking.setOffenderId(obj.getOffenderId());
				offenderBooking.setActiveFlag(obj.getActiveFlag());
				offenderBooking.setModifyUserId(obj.getCreateUserId());
				if ("TRUE".equals(obj.getReasonCodeValid())) {
					String data = oidreleaRepository.offEmPostInsertc(obj);
					
					if ("C".equals(data) || "CLOSE".equals(data)) {
						offenderBookingsT2Service.offenderBookingsT2(offenderBooking, olddta);
						oidreleaRepository.postInsert(obj);
						String operation="UPDATING";
						offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, offenderBooking, operation);
						offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offenderBooking,ApplicationConstants.UPDATING);
						offenderBookingsT7Service.offenderBookingsT7Trigger(offenderBooking);
					}
				}
				BedAssignmentHistories bedAh=oidreleaRepository.getBedAh(obj.getOffenderBookId() != null ? obj.getOffenderBookId().longValue():null);
				bedAh.setAssignmentEndDate(obj.getMovementDate());
				bedAh.setAssignmentEndTime(obj.getMovementTime());
				bedAh.setCreateUserId(commitBean.getCreateUserId());
				oidreleaRepository.updateBedAh(bedAh);
			}
         
         	List<OffenderPayrolEvent> events = new ArrayList<OffenderPayrolEvent>();
         	for (final OffenderExternalMovements obj : commitBean.getInsertList()) {
	         	 if(obj.getMovementReasonCode().equalsIgnoreCase("PARL")) {
			        	 OffenderPayrolEvent offednerPayrolEvent =  new OffenderPayrolEvent();
			        	 offednerPayrolEvent.setOffenderBookId(obj.getOffenderBookId());
			        	 offednerPayrolEvent.setRecordFlag("I");
			        	 offednerPayrolEvent.setParoleEvent("RPAR");
			        	 offednerPayrolEvent.setEventDate(obj.getMovementDate());
			        	 offednerPayrolEvent.setCreateUserId(obj.getCreateUserId());
			        	 events.add(offednerPayrolEvent);
				        	 
	         	 }
         	}
         	if(events.size()>0) {
         		try {
         			events.stream().forEach(e -> e.setCreateUserId(commitBean.getCreateUserId()));
    				oidParoleService.addUpdateDeleteEvent(events);
    			} catch (CustomException e) {
    				logger.error("Movement Release could not add Parole event, error is "+e.getMessage());
    				e.printStackTrace();
    			}
         	}
         	
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles searchRecord) {
		return oidreleaRepository.sysPflSearchSystemProfiles(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		return oidreleaRepository.cgfkOffEmMovementReasonCoRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgMovementReasonCodeRgroup() {
		List<ReferenceCodes> refList = oidreleaRepository.rgMovementReasonCodeRecordGroup();
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

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkOffEmMovementReasonCoRgroup() {
		return null;
	}

	@Transactional
	public Integer offBookingUpdateOffenderExternalMovements(final VHeaderBlock commitBean) {
		int liReturn = 0;
		OffenderBookings  olddta=oidreleaRepository.gettingOldData(commitBean.getOffenderBookId()!=null?commitBean.getOffenderBookId():null);
		OffenderBookings  offenderBooking=new OffenderBookings();
		offenderBooking.setAgyLocId(commitBean.getAgyLocId());
		offenderBooking.setCreateAgyLocId(commitBean.getCreateAgyLocId());
		offenderBooking.setLivingUnitId(commitBean.getLivingUnitId());
		offenderBooking.setOffenderBookId(commitBean.getOffenderBookId().longValue());
		offenderBooking.setOffenderId(commitBean.getOffenderId());
		offenderBooking.setActiveFlag(commitBean.getActiveFlag());
		commitBean.setModifyUserId(commitBean.getCreateuserId());
		if (commitBean != null) {
			offenderBookingsT2Service.offenderBookingsT2(offenderBooking, olddta);
			liReturn = oidreleaRepository.offBookingUpdateOffenderExternalMovements(commitBean);
			String operation="UPDATING";
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, offenderBooking, operation);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offenderBooking,operation);
			offenderBookingsT7Service.offenderBookingsT7Trigger(offenderBooking);
		}
		return liReturn;
	}

	@Transactional
	public Integer offBkgUpdateOffenderExternalMovements(final VHeaderBlock commitBean) {
		int liReturn = 0;
		OffenderBookings  olddta=oidreleaRepository.gettingOldData(commitBean.getOffenderBookId()!=null?commitBean.getOffenderBookId():null);
		OffenderBookings  offenderBooking=new OffenderBookings();
		offenderBooking.setAgyLocId(commitBean.getAgyLocId());
		offenderBooking.setCreateAgyLocId(commitBean.getCreateAgyLocId());
		offenderBooking.setLivingUnitId(commitBean.getLivingUnitId());
		offenderBooking.setOffenderBookId(commitBean.getOffenderBookId().longValue());
		offenderBooking.setOffenderId(commitBean.getOffenderId());
		offenderBooking.setActiveFlag(commitBean.getActiveFlag());
		offenderBooking.setModifyUserId(commitBean.getModifyUserId());
		if (commitBean != null) {
			offenderBookingsT2Service.offenderBookingsT2(offenderBooking, olddta);
			liReturn = oidreleaRepository.offBkgUpdateOffenderExternalMovements(commitBean);
			String operation="UPDATING";
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, offenderBooking, operation);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offenderBooking,commitBean.getCreateUserId());
			offenderBookingsT7Service.offenderBookingsT7Trigger(offenderBooking);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param ofExMovBean
	 *
	 * @
	 */
	public Integer omsMovementsCheckActiveSentence(final OffenderExternalMovements ofExMovBean) {
		int liReturn = 0;
		liReturn = omsMovementsService.checkActiveSentence(ofExMovBean);

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public OffenderExternalMovements offExtMvntsReleaseDateCheck(final OffenderExternalMovements searchRecord) {
		return omsMovementsService.releaseDateCheck(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public String omsMovementsCheckActiveCases(final OffenderExternalMovements searchRecord) {
		return tagTerminationService.chkActiveCases(searchRecord);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderExternalMovements> movementDateComparison(final Long offenderBookId) {
		return oidreleaRepository.movementDateComparison(offenderBookId);
	}
	
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public String gettingProfileValue() {
		return oidreleaRepository.gettingProfileValue();
	}
	
	public String getClosedFlag(final String movementCode) {
		return oidreleaRepository.getClosedFlag(movementCode);
	}

	@Override
	public Integer updateCustodyStatus(Long offenderBookId, String userName) {
		int result = 0;
		String custodyStatus = "";
		OffenderCustodyStatus offenderCustodyStatus = new OffenderCustodyStatus();
		offenderCustodyStatus.setOffenderBookId(offenderBookId);
		offenderCustodyStatus.setCreateUserId(userName);
		try {
			custodyStatus = oidreleaRepository.getCustodyStatus();
			offenderCustodyStatus.setCustodyStatus(custodyStatus);
			List<OffenderCustodyStatus> offenderCustodyStatusList = new ArrayList<OffenderCustodyStatus>();
			offenderCustodyStatusList.add(offenderCustodyStatus);		
			result = ocdintakRepository.updateCustodyStatus(offenderCustodyStatusList);
		} catch(Exception e) {
			logger.error( "In oidrelea updateCustodyStatus: ", e);
		}
		return result;
	}
	
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderReleaseDetails> getOffenderreleaseSchedule(final Long offenderBookId) {
		return oidreleaRepository.getOffenderreleaseSchedule(offenderBookId);
	}
	
	@Override
	public String getOffenderCommentText(Integer offenderBookId) {
		return oidreleaRepository.getOffenderCommentText(offenderBookId);
	}

}