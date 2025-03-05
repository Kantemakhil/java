package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inst.movementexternal.OidunctaRepository;
import net.syscon.s4.inst.movementexternal.OidunctaService;
import net.syscon.s4.pkgs.omkhead.OmkheadService;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsService;
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

/**
 * Class OidunctaServiceImpl
 */
@Service
public class OidunctaServiceImpl extends BaseBusiness implements OidunctaService {

	private static final String INSERTING = "INSERTING";
	private static final String UPDATING = "UPDATING";

	@Autowired
	private OidunctaRepository oidunctaRepository;

	@Autowired
	private OmsMovementsService omsMovementsService;

	@Autowired
	private OmkheadService omkheadService;

	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;

	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;

	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;

	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;

	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;

	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;

	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;

	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;

	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;

	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;

	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;

	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidunctaServiceImpl.class.getName());

	/**
	 * Creates new OidunctaServiceImpl class Object
	 */
	public OidunctaServiceImpl() {
	}

	public List<AgencyLocations> cgfkchkOffEmOffEmAgyLoc() {
		return oidunctaRepository.cgfkchkOffEmOffEmAgyLoc();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public OffenderExternalMovements offEmExecuteQuery(final OffenderExternalMovements searchRecord) {
		String directionCode = null;
		String movementReasonCode = null;
		String movementType = null;
		String fromAgyLocId = null;
		String toAgyLocId = null;
		String toCity = null;
		String fromCity = null;
		Date movementDate = null;
		final OffenderExternalMovements offExMovObj = new OffenderExternalMovements();

		directionCode = omsMovementsService.getNextDirectionCode(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());
		offExMovObj.setDirectionCode(directionCode);
		movementType = omsMovementsService.getLastMovementCode(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());

		offExMovObj.setMovementType(movementType);
		movementReasonCode = omsMovementsService.getLastMovementReasonCode(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());
		offExMovObj.setMovementReasonCode(movementReasonCode);
		fromAgyLocId = omsMovementsService.getLastFromAgyLocId(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());
		offExMovObj.setFromAgyLocId(fromAgyLocId);
		toAgyLocId = omsMovementsService.getLastToAgyLocId(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());

		offExMovObj.setToAgyLocId(toAgyLocId);
		toCity = omsMovementsService.getLastToCity(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());
		offExMovObj.setToCity(toCity);
		fromCity = omsMovementsService.getLastFromCity(
				searchRecord.getOffenderBookId() == null ? null : searchRecord.getOffenderBookId().intValue());
		offExMovObj.setFromCity(fromCity);
		movementDate = oidunctaRepository
				.getLastMovementDateTime(Integer.valueOf(searchRecord.getOffenderBookId().toString()));
		offExMovObj.setMovementDate(movementDate);

		return offExMovObj;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 * 
	 */
	@Transactional
	public Integer offEmCommit(final OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		Integer index = 0;
		commitBean.getInsertList().stream().forEach(bean -> {
			bean.setCreateUserId(commitBean.getCreateUserId());
			bean.setModifyUserId(commitBean.getCreateUserId());
		});
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExeM);
			}
			liReturn = oidunctaRepository.offEmInsertOffenderExternalMovements(commitBean.getInsertList());
			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				MovementReasons oldBean = new MovementReasons();
				MovementReasons newBean = new MovementReasons();
				newBean.setMovementType(offExeM.getMovementType());
				newBean.setMovementReasonCode(offExeM.getMovementReasonCode());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldBean, newBean,
						offExeM.getOffenderBookId(), INSERTING);
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(offExeM, null);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExeM);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(offExeM);
				offenderExternalMovementsT8Service.updateObligationWR(offExeM.getOffenderBookId(),
						offExeM.getMovementType(),commitBean.getCreateUserId());
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExeM);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(offExeM);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(offExeM);
			}
		}
		if (liReturn == 1) {
			index = omkheadService.toggleInOutStatus(commitBean.getInsertList().get(index).getOffenderBookId(),
					commitBean.getCreateUserId());
			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				OffenderBookings newBean = new OffenderBookings();
				OffenderBookings oldBean = new OffenderBookings();
				oldBean = oidunctaRepository.getOldOffBkgsBean(offExeM.getOffenderBookId());
				newBean.setAgyLocId(offExeM.getFromAgyLocId());
				if(Optional.ofNullable(offExeM.getLivingUnitId()).isPresent())
				newBean.setLivingUnitId(new BigDecimal(offExeM.getLivingUnitId()));
				offenderBookingsT2Service.offenderBookingsT2(newBean, oldBean);
			}
			index = oidunctaRepository.updateOffenderBookings(commitBean.getInsertList());
			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				OffenderBookings newBean = new OffenderBookings();
				OffenderBookings oldBean = new OffenderBookings();
				newBean.setActiveFlag(offExeM.getActiveFlag());
				newBean.setOffenderId(offExeM.getOffenderId());
				newBean.setOffenderBookId(offExeM.getOffenderBookId());
				newBean.setModifyUserId(commitBean.getCreateUserId());
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(newBean,ApplicationConstants.UPDATING);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldBean, newBean, UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(newBean);
			}

			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExeM);
			}
			index = oidunctaRepository.updateExternalMovements(commitBean.getInsertList());
			for (final OffenderExternalMovements offExeM : commitBean.getInsertList()) {
				OffenderExternalMovements oldOffExeBean = new OffenderExternalMovements();
				MovementReasons oldBean = new MovementReasons();
				MovementReasons newBean = new MovementReasons();
				oldOffExeBean = oidunctaRepository.getOldRecordOfOffExeMvmt(offExeM);

				oldBean.setMovementType(oldOffExeBean.getMovementType());
				oldBean.setMovementReasonCode(oldOffExeBean.getMovementReasonCode());
				newBean.setMovementType(offExeM.getMovementType());
				newBean.setMovementReasonCode(offExeM.getMovementReasonCode());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldBean, newBean,
						offExeM.getOffenderBookId(), UPDATING);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExeM);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExeM);
			}
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
		return oidunctaRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup(final String toagyLocId) {
		List<AgencyLocations> refList = oidunctaRepository.cgfkOffEmFromAgyLocIdRecordGroup(toagyLocId);
		return filterAgyLocOnActiveFlag(refList);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(final String directionCode, final String fromAgyLocId) {
		 List<AgencyLocations> refList = oidunctaRepository.cgfkOffEmToAgyLocIdRecordGroup(directionCode, fromAgyLocId);
		return filterAgyLocOnActiveFlag(refList);
	}

	private List<AgencyLocations> filterAgyLocOnActiveFlag(List<AgencyLocations> refList) {
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
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		List<ReferenceCodes> refList = oidunctaRepository.cgfkOffEmMovementTypeRecordGroup();
		return filterRefCodeOnActiveFlag(refList);

	}

	private List<ReferenceCodes> filterRefCodeOnActiveFlag(List<ReferenceCodes> refList) {
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
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup(final String movementType) {
		List<ReferenceCodes> refList = oidunctaRepository.cgfkOffEmMovementReasonCoRecordGroup(movementType);
		return filterRefCodeOnActiveFlag(refList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		 List<ReferenceCodes> refList = oidunctaRepository.cgfkOffEmToCityRecordGroup();
		return filterRefCodeOnActiveFlag(refList);
	}

}