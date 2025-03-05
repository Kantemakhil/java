package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OidbutabRepository;
import net.syscon.s4.inst.movementexternal.OidbutabService;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
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
 * Class OidbutabServiceImpl
 */
@Service
public class OidbutabServiceImpl extends BaseBusiness implements OidbutabService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidbutabServiceImpl.class.getName());

	private final static String UPDATE = "UPDATING";

	private final static String INSERTING = "INSERTING";

	@Autowired
	private OidbutabRepository oidbutabRepository;

	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;

	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;

	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrg;

	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;

	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;

	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;

	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;

	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;

	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;

	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;

	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;

	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;

	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;

	/**
	 * Creates new OidbutabServiceImpl class Object
	 */
	public OidbutabServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> qryBlkWhenNewBlockInstance(final AgencyLocations paramBean) {
		final List<AgencyLocations> agencyLocationsList = new ArrayList<>();
		return agencyLocationsList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements vhbPostQuery(final OffenderExternalMovements paramBean) {
		final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
		return offenderExternalMovements;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements oidbutabKeyCommit(final OffenderExternalMovements paramBean) {
		final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
		return offenderExternalMovements;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OmsModules CreateFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = new OmsModules();
		return omsModules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public SystemProfiles setOffIdDisplayPrompt(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = new SystemProfiles();
		return systemProfiles;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements hasLaterMovement(final OffenderExternalMovements paramBean) {
		return oidbutabRepository.hasLaterMovement(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VHeaderBlock> vhbExecuteQuery(final VHeaderBlock searchRecord) {
		OffenderExternalMovements offExMov = new OffenderExternalMovements();
		List<VHeaderBlock> vHeaderList = new ArrayList<>();
		vHeaderList = oidbutabRepository.vhbExecuteQuery(searchRecord);
		if (searchRecord.getInOutStatus().equals("OUT")) {
			for (final VHeaderBlock vObj : vHeaderList) {
				offExMov.setFromAgyLocId(vObj.getAgyLocId());
				offExMov.setOffenderBookId(Long.valueOf(vObj.getOffenderBookId().toString()));
				offExMov = oidbutabRepository.getlastMoveAndLoc(offExMov);
				vObj.setIntakeAgyLocId(
						(offExMov.getToCity() == null) ? offExMov.getToAgyLocId() : offExMov.getToCity());
				vObj.setOfficer(offExMov.getCommentText());
			}
		}
		if (searchRecord.getInOutStatus().equals("IN")) {
			for (final VHeaderBlock vObj : vHeaderList) {
				offExMov.setFromAgyLocId(vObj.getAgyLocId());
				offExMov.setOffenderBookId(Long.valueOf(vObj.getOffenderBookId().toString()));
				offExMov = oidbutabRepository.getlastMoveAndLoc(offExMov);
				vObj.setIntakeAgyLocId(null);
				vObj.setOfficer(null);
			}
		}
		return vHeaderList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVHB
	 * 
	 */
	@Transactional
	public Integer vhbCommit(final VHeaderBlockCommitBean commitBean) {
		int msgIndex = 0;
		int upOffBook = 0;
		int upOffExMov = 0;
		int inOffExMov = 0;
		int updateReturn = 0;
		final OffenderBookings offBookObj = new OffenderBookings();
		final VNameSearch vnameObj = new VNameSearch();
		List<VHeaderBlock> VHeaderBlockList = new ArrayList<>();
		VHeaderBlockList.addAll(commitBean.getUpdateList());
		VHeaderBlockList.addAll(commitBean.getInsertList());
		final OffenderExternalMovements offExMovObj = new OffenderExternalMovements();
		OffenderExternalMovements offExMovObjSeq = new OffenderExternalMovements();
		for (VHeaderBlock updateBean : VHeaderBlockList) {
			if (updateBean.getActiveFlag().equals("Y") || updateBean.getActiveFlag().equals("true")) {
				if (updateBean.getInOutStatus().equals("IN") || (updateBean.getInOutStatus().equals("OUT")
						&& updateBean.getMovementReason() != null
						&& ((updateBean.getLocationCode() != null && updateBean.getEthnicity() == null)
								|| (updateBean.getLocationCode() == null && updateBean.getEthnicity() != null)))) {
					offBookObj.setInOutStatus(updateBean.getInOutStatus());
					offBookObj.setOffenderBookId(Long.valueOf(updateBean.getOffenderBookId().toString()));
					offBookObj.setModifyUserId(commitBean.getCreateUserId());
					offBookObj.setAgyLocId(updateBean.getAgyLocId());
					OffenderBookings offBookObj1 = new OffenderBookings();
					BeanUtils.copyProperties(offBookObj, offBookObj1);
					offBookObj1.setActiveFlag(updateBean.getActiveFlag());
					offBookObj1.setModifyUserId(commitBean.getCreateUserId());
					OffenderBookings oldData = null;
					try {
						oldData = oidbutabRepository.getOldOffenderBookingsData(offBookObj);
					} catch (Exception e) {
						logger.error("getOldOffenderBookingsData :" + e);
					}
					offBookObj1 = offenderBookingsT2Service.offenderBookingsT2(offBookObj1, oldData);
					upOffBook = oidbutabRepository.updateOffenderBookings(offBookObj1);
					try {
						offenderBookingsT7Service.offenderBookingsT7Trigger(offBookObj1);
					} catch (Exception e) {
						logger.error("offenderBookingsT7Trigger :" + e);
					}
					try {
						offenderBookingsBkadmTrg.offenderBookingsBkadmTrg(oldData, offBookObj, "UPDATING");
					} catch (Exception e) {
						logger.error("offenderBookingsBkadmTrg :" + e);
					}
					try {
						offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offBookObj1,ApplicationConstants.UPDATING);
					} catch (Exception e) {
						logger.error("OffendersBookVineIntfTrgTrigger :" + e);
					}
					offBookObj.setActiveFlag(updateBean.getActiveFlag());
					List<OffenderExternalMovements> OldDataList = new ArrayList<>();
					try {
						OldDataList = oidbutabRepository.getOldDataOfExternalMovements(offBookObj.getOffenderBookId());
					} catch (Exception e) {
						logger.error("getOldDataOfExternalMovements :" + e);
					}
					if (OldDataList != null) {
						OldDataList.forEach(bo -> {
							bo.setActiveFlag("N");
						});
					}
					
					for (int i = 0; i < OldDataList.size(); i++) {
						offenderExternalMovementsT9Service.offenderExternalMovementsT9(OldDataList.get(i));
					}
					upOffExMov = oidbutabRepository.updateOffenderExternalMovements(offBookObj);
					for (int i = 0; i < OldDataList.size(); i++) {
						MovementReasons oldMovReason = new MovementReasons();
						oldMovReason.setMovementType(OldDataList.get(i).getMovementType());
						oldMovReason.setMovementReasonCode(OldDataList.get(i).getMovementReasonCode());
						MovementReasons newMovReason = new MovementReasons();
						newMovReason.setMovementType(OldDataList.get(i).getMovementType());
						newMovReason.setMovementReasonCode(OldDataList.get(i).getMovementReasonCode());
						newMovReason.setModifyUserId(offBookObj.getModifyUserId());
						offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldMovReason,
								newMovReason, offBookObj.getOffenderBookId(), UPDATE);
						offenderExternalMovementsT5Service.offenderExternalMovementsT5(OldDataList.get(i));
						offenderExternalMovementT13Service.OffenderExternalMovementT1(OldDataList.get(i));
					}
					if (upOffBook == 1 && upOffExMov == 1) {
						updateReturn = updateReturn + 1;
					}
				}
			}
		}

		
		final List<OffenderExternalMovements> offExMovList = new ArrayList<>();
		if (VHeaderBlockList.size() > 0) {
			for (final VHeaderBlock insertBean : VHeaderBlockList) {
				vnameObj.setOffenderIdDisplay(insertBean.getOffenderIdDisplay());
				vnameObj.setAgyLocId(insertBean.getAgyLocId());
				vnameObj.setLivingUnitDescription(
						(insertBean.getLivingUnitId() == null) ? "" : insertBean.getLivingUnitId().toString());
				vnameObj.setCreateUserId(commitBean.getCreateUserId());
				msgIndex = oidbutabRepository.whenValidateItem(vnameObj);
				if (msgIndex > 0 && insertBean.getInOutStatus().equals("OUT")) {
					return 101;
				}
				offExMovObj.setOffenderBookId(Long.valueOf(insertBean.getOffenderBookId().toString()));
				offExMovObjSeq = oidbutabRepository.generateMovementSeq(offExMovObj);
				offExMovObj.setMovementType(offExMovObjSeq.getMovementType());
				offExMovObj.setMovementReasonCode(offExMovObjSeq.getMovementReasonCode());
				offExMovObj.setMovementSeq(offExMovObjSeq.getMovementSeq());
				offExMovObj.setMovementTime(insertBean.getActiveDatetime());
				offExMovObj.setMovementDate(insertBean.getActiveDatetime());
				if (insertBean.getInOutStatus().equals("OUT")) {
					offExMovObj.setDirectionCode(insertBean.getInOutStatus());
					offExMovObj.setMovementType("TAP");
					offExMovObj.setMovementReasonCode(insertBean.getMovementReason());
					offExMovObj.setFromAgyLocId(insertBean.getAgyLocId());
					offExMovObj.setFromCity(null);
					offExMovObj.setToAgyLocId(insertBean.getLocationCode());
					offExMovObj.setToCity(insertBean.getEthnicity());
				} else {
					offExMovObj.setDirectionCode(insertBean.getInOutStatus());
					offExMovObj.setToAgyLocId(insertBean.getLocationCode());
					offExMovObj.setToCity(null);
					offExMovObj.setFromCity(insertBean.getIntakeAgyLocId().toString());
				}
				offExMovObj.setActiveFlag("Y");
				offExMovObj.setCommentText(insertBean.getStatusReason());
				offExMovObj.setCreateUserId(insertBean.getCreateUserId());
				offExMovObj.setModifyUserId(commitBean.getCreateUserId());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExMovObj);
				inOffExMov = oidbutabRepository.insertOffenderExternalMovements(offExMovObj);
				MovementReasons oldMovReason = new MovementReasons();
				MovementReasons newMovReason = new MovementReasons();
				newMovReason.setModifyUserId(commitBean.getCreateUserId());
				newMovReason.setMovementType(offExMovObj.getMovementType());
				newMovReason.setMovementReasonCode(offExMovObj.getMovementReasonCode());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldMovReason, newMovReason,
						offExMovObj.getOffenderBookId(), INSERTING);
				Offenders offenders = new Offenders();
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(offExMovObjSeq, offenders);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExMovObj);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(offExMovObj);
				offenderExternalMovementsT8Service.updateObligationWR(offExMovObj.getOffenderBookId(), INSERTING,commitBean.getCreateUserId());
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExMovObj);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(offExMovObj);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(offExMovObj);
				if (inOffExMov > 0) {
					updateReturn = updateReturn + inOffExMov;
				}
				offExMovList.add(offExMovObj);
			}
		}

		return updateReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgInstitutionRecordGroup(final String caseloadId) {
		List<AgencyLocations> resultList = oidbutabRepository.rgInstitutionRecordGroup(caseloadId);
		return filterAgyLocOnActiveFlag(resultList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgActiveAgencyRecordGroup(final String agyLocId) {
		List<AgencyLocations> resultList =  oidbutabRepository.rgActiveAgencyRecordGroup(agyLocId);
		return filterAgyLocOnActiveFlag(resultList);
	}

	private List<AgencyLocations> filterAgyLocOnActiveFlag(List<AgencyLocations> resultList) {
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
	public List<AgencyLocations> rgActiveAgencyRecordGroupForGrid(final String agyLocId) {
		return oidbutabRepository.rgActiveAgencyRecordGroupForGrid(agyLocId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgLuLevel1RecordGroup(final String agyLocId) {
		List<LivingUnits> livUnitList = oidbutabRepository.rgLuLevel1RecordGroup(agyLocId);
		return filterLivingUnits(livUnitList);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgLuLevel2RecordGroup(final String agyLocId, final String livingUnitId) {
		 List<LivingUnits> livUnitList = oidbutabRepository.rgLuLevel2RecordGroup(agyLocId, livingUnitId);
		return filterLivingUnits(livUnitList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgLuLevel3RecordGroup(final String agyLocId, final String livingUnitId) {
		 List<LivingUnits> livUnitList = oidbutabRepository.rgLuLevel3RecordGroup(agyLocId, livingUnitId);
		return filterLivingUnits(livUnitList);
	}

	private List<LivingUnits> filterLivingUnits(List<LivingUnits> livUnitList) {
		if(Optional.ofNullable(livUnitList).isPresent()) {
			livUnitList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return livUnitList;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * 
	 */
	public List<ReferenceCodes> rgCityRecordGroup() {
		return oidbutabRepository.rgCityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * 
	 */
	public List<MovementReasons> rgReasonRecordGroup() {
		 List<MovementReasons> returnList = oidbutabRepository.rgReasonRecordGroup();
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	public Integer whenValidateItem(final VNameSearch vnamesearch) {
		return oidbutabRepository.whenValidateItem(vnamesearch);
	}

	@Override
	public List<ReferenceCodes> rgDirectionRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		ReferenceCodes directionInBean = new ReferenceCodes();
		directionInBean.setCode("IN");
		directionInBean.setDescription("IN");
		returnList.add(directionInBean);
		ReferenceCodes directionOutBean = new ReferenceCodes();
		directionOutBean.setCode("OUT");
		directionOutBean.setDescription("OUT");
		returnList.add(directionOutBean);
		return returnList;
	}
}