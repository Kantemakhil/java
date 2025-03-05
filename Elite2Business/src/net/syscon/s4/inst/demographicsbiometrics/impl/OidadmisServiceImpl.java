package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.beans.dao.OidadmisCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
import net.syscon.s4.inst.movements.maintenance.OimmholoService;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;
import net.syscon.s4.pkgs.oidadmis.OidadmisPkgService;
import net.syscon.s4.pkgs.omkcopy.OmkcopyService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT1Service;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT3Service;
import net.syscon.s4.triggers.OffenderBookingsT4Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderBookingsT8Service;
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
 * Class OidadmisServiceImpl
 */
@Service
public class OidadmisServiceImpl extends BaseBusiness implements OidadmisService {

	private static Logger logger = LogManager.getLogger(OidadmisServiceImpl.class.getName());

	@Autowired
	private DeductionsService deductionsService;

	@Autowired
	private OidadmisRepository oidadmisRepositoryImpl;

	@Autowired
	private OmkcopyService omkcopyService;
	

	@Autowired
	private OidadmisPkgService oidadmisService;

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
	private OffenderBookingsT7Service offenderBookingsT7Service;

	@Autowired
	private OffenderBookingsT8Service offenderBookingsT8Service;

	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;

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
	private OcdintakRepository ocdintakRepository;
	
	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	
	@Autowired
	private NonAssociationRepository nonAssociationRepository;
	@Autowired
	private OimmholoService oimmholoService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OcmpconfService ocmpconfService;
	
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OumsysetService oumsysetService;
	
	/**
	 * Creates new OidadmisBusiness class Object
	 */
	public OidadmisServiceImpl() {
	}

	@Transactional
	public Integer oidadmisCommit(final OidadmisCommitBean oidadmisCommitBean) {
		final Integer returnValue = 0;
		if (oidadmisCommitBean != null && oidadmisCommitBean.getOffenderBookingsCommitBean() != null) {
		}
		return returnValue;
	}

//	@Transactional
	public OffenderBookings offemCommit(final OffenderExternalMovementsCommitBean commitBean) {
		OffenderBookings offenderBookings = new OffenderBookings();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderExternalMovements data : commitBean.getInsertList()) {
				data.setAuthorization(commitBean.getAuthorization());
	     		String trustFlagData = oidadmisRepositoryImpl.chkTrustFlag(data.getCaseloadId());
				if (trustFlagData != null && ApplicationConstants.YFLAG.equalsIgnoreCase(trustFlagData)) {
					String status = oidadmisRepositoryImpl.ChkAccountStatus(data.getCaseloadId(),data.getOffenderId().toString());
					if (status != null && (ApplicationConstants.YFLAG.equalsIgnoreCase(status) || ApplicationConstants.NFLAG.equalsIgnoreCase(status))) {
						TransactionOperation checkingDrCrAccount = oidadmisRepositoryImpl
								.drAccountCodeCrAccountCode(data.getCaseloadId());
						if (checkingDrCrAccount == null) {
							offenderBookings.setSealFlag(ApplicationConstants.HUNDEREDONE);
							return offenderBookings;
						}
					} else if (status != null && (ApplicationConstants.XFLAG.equalsIgnoreCase(status) || ApplicationConstants.MFLAG.equalsIgnoreCase(status))) {
						TransactionOperation checkingDrCrAccount = oidadmisRepositoryImpl
								.drAccountCodeCrAccountCode(data.getCaseloadId());
						if (checkingDrCrAccount == null) {
							offenderBookings.setSealFlag(ApplicationConstants.HUNDEREDONE);
							return offenderBookings;
						}
					}
				}
			}
			offenderBookings = offemInsertOffenderExternalMovements(commitBean.getInsertList(), commitBean.getCreateUserId());
		}
		return offenderBookings;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemgetCapacityCur(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.dspDescriptionWhenValidateItemgetCapacityCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemlivDesc(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.dspDescriptionWhenValidateItemlivDesc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public OffenderBookings nbtActiveFlagWhenValidateItembookNoCrsr(final OffenderBookings dataObj) {
		return oidadmisRepositoryImpl.nbtActiveFlagWhenValidateItembookNoCrsr(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public String offEmWhenNewBlockInstanceoffAlertCur(final Integer dataObj) {
		return oidadmisRepositoryImpl.offEmWhenNewBlockInstanceoffAlertCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public Integer offEmWhenNewBlockInstancecasAgyCur(final CaseloadAgencyLocations bean) {
		return oidadmisRepositoryImpl.offEmWhenNewBlockInstancecasAgyCur(bean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public SystemMessages offEmWhenNewBlockInstance(final SystemMessages bean) {
		return oidadmisRepositoryImpl.offEmWhenNewBlockInstance(bean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public LivingUnits offEmWhenNewRecordInstancegetCapacityCur(final LivingUnits bean) {
		return oidadmisRepositoryImpl.offEmWhenNewRecordInstancegetCapacityCur(bean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public OffenderExternalMovements offEmWhenNewRecordInstanceadm(final OffenderExternalMovements dataObj) {
		return oidadmisRepositoryImpl.offEmWhenNewRecordInstanceadm(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param pMvtReason
	 *
	 * @param params
	 *
	 * @
	 */
	public CaseloadAgencyLocations offEmWhenNewRecordInstance(final CaseloadAgencyLocations dataobj) {
		return oidadmisRepositoryImpl.offEmWhenNewRecordInstance(dataobj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> dspDescriptionWhenValidateItemvsLcdCur(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.dspDescriptionWhenValidateItemvsLcdCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public StaffMembers cgfkchkOffBkgsOffBkgStafc(final StaffMembers dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffBkgsOffBkgStafc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefCodc(final ReferenceCodes dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffEmOffEmRefCodc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public ReferenceCodes cgfkkpOffEmOffEmRefCodc(final ReferenceCodes dataObj) {
		return oidadmisRepositoryImpl.cgfkkpOffEmOffEmRefCodc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefc(final ReferenceCodes dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffEmOffEmRefc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public MovementReasons cgfkchkOffEmOffEmMoveRsc(final MovementReasons dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffEmOffEmMoveRsc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public MovementReasons cgfklkpOffEmOffEmMoveRsc(final MovementReasons dataObj) {
		return oidadmisRepositoryImpl.cgfklkpOffEmOffEmMoveRsc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyLocc(final AgencyLocations dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffEmOffEmAgyLocc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public AgencyLocations cgfklkpOffEmOffEmAgyLocc(final AgencyLocations dataObj) {
		return oidadmisRepositoryImpl.cgfklkpOffEmOffEmAgyLocc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyc(final AgencyLocations dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffEmOffEmAgyc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<AgencyLocations> cgfklkpOffEmOffEmAgyc(final AgencyLocations dataObj) {
		return oidadmisRepositoryImpl.cgfklkpOffEmOffEmAgyc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> cgfkchkBedAhBedAhLivUnic(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.cgfkchkBedAhBedAhLivUnic(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public LivingUnits cgfklkpBedAhBedAhLivUnic(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.cgfklkpBedAhBedAhLivUnic(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public ReferenceCodes cgfkchkOffBkgsOffBkgRef(final ReferenceCodes dataObj) {
		return oidadmisRepositoryImpl.cgfkchkOffBkgsOffBkgRef(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public TransactionTypes insertMasterRecTransTypes(final TransactionTypes dataObj) {
		return oidadmisRepositoryImpl.insertMasterRecTransTypes(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public TransactionOperation storGlobalsgetCrAccount(final TransactionOperation dataObj) {
		return oidadmisRepositoryImpl.storGlobalsgetCrAccount(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public TransactionTypes storGlobals(final TransactionTypes dataObj) {
		return oidadmisRepositoryImpl.storGlobals(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<String> checkActiveYnactCurr(final OffenderExternalMovements bean) {
		return oidadmisRepositoryImpl.checkActiveYnactCurr(bean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public OffenderBookings checkPrevBkgClosedchekCrsr(final OffenderBookings dataObj) {
		return oidadmisRepositoryImpl.checkPrevBkgClosedchekCrsr(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public StaffMembers getStaffNamedefStaffCur(final StaffMembers dataObj) {
		return (StaffMembers) oidadmisRepositoryImpl.getStaffNamedefStaffCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public Long callThePreInsertc(final BedAssignmentHistories dataObj) {
		return oidadmisRepositoryImpl.callThePreInsertc(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> checkCapacityvsLcdCur(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.checkCapacityvsLcdCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> populateGlobalCapacityvsLcdCur(final LivingUnits dataObj) {
		return oidadmisRepositoryImpl.populateGlobalCapacityvsLcdCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public CaseloadAdmOtherProfiles getCountOfAgyInCasegetAgyCur(final CaseloadAdmOtherProfiles dataObj) {
		return oidadmisRepositoryImpl.getCountOfAgyInCasegetAgyCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public String getCountOfAgyInCaseagyLocCur(final CaseloadAgencyLocations dataObj) {
		return oidadmisRepositoryImpl.getCountOfAgyInCaseagyLocCur(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public AgencyLocations populateDspDescriptionRg(final AgencyLocations dataObj) {
		return oidadmisRepositoryImpl.populateDspDescriptionRg(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public Long validateMovementDatetimemaxBookId(final OffenderBookings dataObj) {
		return oidadmisRepositoryImpl.validateMovementDatetimemaxBookId(dataObj);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public Long validateMovementDatemaxBookId(final OffenderBookings paramBean) {
		return oidadmisRepositoryImpl.validateMovementDatemaxBookId(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public OffenderExternalMovements validateMovementDatemaxDate(final Integer offenderBookId) {
		return oidadmisRepositoryImpl.validateMovementDatemaxDate(offenderBookId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public CaseloadAdmOtherProfiles getLivingUnitIdgetLivUnitId(final CaseloadAdmOtherProfiles paramBean) {
		return oidadmisRepositoryImpl.getLivingUnitIdgetLivUnitId(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public OffenderExternalMovements deactivateActiveOffExmRecsetLockCur(final OffenderExternalMovements paramBean) {
		return oidadmisRepositoryImpl.deactivateActiveOffExmRecsetLockCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public AgencyBillingProfiles createUpdateOffBillingProfgetAgyProfInfoCur(final AgencyBillingProfiles paramBean) {
		return oidadmisRepositoryImpl.createUpdateOffBillingProfgetAgyProfInfoCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @
	 */
	public AgencyBillingProfiles createUpdateOffBillingProf(final AgencyBillingProfiles paramBean) {
		return oidadmisRepositoryImpl.createUpdateOffBillingProf(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public WorkflowScreens workflowDownFormcountCur(final WorkflowScreens paramBean) {
		return oidadmisRepositoryImpl.workflowDownFormcountCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public WorkflowScreens workflowDownFormseqNoCur(final WorkflowScreens paramBean) {
		return oidadmisRepositoryImpl.workflowDownFormseqNoCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<String> workflowDownFormcallFormCur(final WorkflowScreens paramBean) {
		return oidadmisRepositoryImpl.workflowDownFormcallFormCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> chkAssignedLochouUnTypeCur(final LivingUnits paramBean) {
		return oidadmisRepositoryImpl.chkAssignedLochouUnTypeCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public String checkBedAhBlkNavcasAgyCur(final CaseloadAgencyLocations paramBean) {
		return oidadmisRepositoryImpl.checkBedAhBlkNavcasAgyCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @
	 */
	public List<LivingUnits> validateLivingUnitsvsLcdCur(final LivingUnits paramBean) {
		return oidadmisRepositoryImpl.validateLivingUnitsvsLcdCur(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VHeaderBlock2> offbkgExecuteQuery(final VHeaderBlock2 searchRecord) {
		return oidadmisRepositoryImpl.offbkgExecuteQuery(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderBookings> offBkgsSearchOffenderBookings(final OffenderBookings searchRecord) {
		List<OffenderBookings> offBookObj = new ArrayList<OffenderBookings>();
		offBookObj = oidadmisRepositoryImpl.offBkgsSearchOffenderBookings(searchRecord);
		offBookObj.forEach(listval -> {
			Integer staffId = oidadmisRepositoryImpl.getStaffId(listval.getCreateUserId());
			if (listval.getAssignedStaffId() != null) {
				listval.setDspFirstName(staffId.toString());
			}
			if (listval.getBookingStatus() != null) {
				listval.setCgnbtBookingStatus(listval.getBookingStatus());
			}
		});

		return offBookObj;
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
		return oidadmisRepositoryImpl.offEmSearchOffenderExternalMovements(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param offenderExternalMovements
	 *
	 * @return OffenderBookings
	 */
	@Transactional
	public OffenderBookings offemInsertOffenderExternalMovements(
			final List<OffenderExternalMovements> offenderExternalMovements, final String userName) {
		Integer returnValue = 0;
		boolean flag = false;
		String athorization=null;
		final OffenderBookings offBook = new OffenderBookings();
		final OffenderBookings offBookings = new OffenderBookings();
		final List<OffenderExternalMovements> offExtrlMovntList = new ArrayList<>();
		OffenderExternalMovements offExMntSeq = new OffenderExternalMovements();
		LivingUnits livingUnits = new LivingUnits();
		LivingUnits livingUnitSeq = new LivingUnits();
		BedAssignmentHistories bedAhHistories = new BedAssignmentHistories();
		List<BedAssignmentHistories> bedAhList = new ArrayList<>();
		BedAssignmentHistories bedAhHistory = new BedAssignmentHistories();
		for (OffenderExternalMovements offExMnts : offenderExternalMovements) {
			if (offExMnts.getOffenderBookId() != null && offExMnts.getOffenderBookId() != 0L) {
				if (offExMnts.getBookingStatus().equalsIgnoreCase(ApplicationConstants.OFLAG)) {
					flag = true;
					offBook.setOffenderBookId(offExMnts.getOffenderBookId());
				} else if (!offExMnts.isNewBookingFlag() && offExMnts.getBookingStatus().equalsIgnoreCase(ApplicationConstants.CFLAG)) {
					flag = true;
					offBook.setOffenderBookId(offExMnts.getOffenderBookId());
				}
			}
			offBook.setBookingBeginDate(offExMnts.getMovementDate());
			offBook.setDisclosureFlag(offExMnts.getActiveFlag());
			offBook.setInOutStatus(offExMnts.getDirectionCode());
			offBook.setYouthAdultCode(offExMnts.getActiveFlag() == null ? ApplicationConstants.YFLAG : offExMnts.getActiveFlag());
			offBook.setOffenderId(offExMnts.getOffenderId());
			offBook.setAssignedStaffId(offExMnts.getAssignedStaffId());
			offBook.setCreateUserId(userName);
			offBook.setModifyUserId(offExMnts.getModifyUserId());
			offBook.setCreateDatetime(offExMnts.getCreateDatetime());
			offBook.setModifyDatetime(offExMnts.getModifyDatetime());
			offBook.setStatusReason(offExMnts.getMovementReasonCode());
			offBook.setRootOffenderId(offExMnts.getRootOffenderId());
			offBook.setLivUnitDesc(offExMnts.getLivUnitDesc());
			livingUnits.setDescription(offExMnts.getLivUnitDesc());
			livingUnitSeq = oidadmisRepositoryImpl.cgfklkpBedAhBedAhLivUnic(livingUnits);
			offBook.setLivingUnitId(livingUnitSeq.getLivingUnitId());
			offBook.setAgyLocId(offExMnts.getToAgyLocId());
			offBook.setCreateAgyLocId(offExMnts.getToAgyLocId());
			offBook.setBookingType(ApplicationConstants.INST_CASELOAD);
			bedAhHistories.setAssignmentDate(offExMnts.getMovementDate());
			bedAhHistories.setAssignmentTime(offExMnts.getMovementTime());
			bedAhHistories.setLivingUnitId(Integer.parseInt(offBook.getLivingUnitId().toString()));
			bedAhHistories.setCreateUserId(offExMnts.getCreateUserId());
			bedAhHistories.setModifyUserId(offExMnts.getModifyUserId());
			bedAhHistories.setCreateDatetime(offExMnts.getCreateDatetime());
			bedAhHistories.setModifyDatetime(offExMnts.getModifyDatetime());
			athorization=offExMnts.getAuthorization();
		}
		if (flag == true) {
			try {
				offenderBookingsT2Service.offenderBookingsT2(offBook, offBookings);
				Integer staffId = ocdintakRepository.oldContactGetStaffId(userName);
				returnValue = oidadmisRepositoryImpl.updateOffenderBookings(offBook, staffId);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, offBookings,ApplicationConstants.UPDATING);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offBook,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(offBook);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
			}
			if (returnValue != null) {
				offBookings.setOffenderBookId(offBook.getOffenderBookId());
				if (offBookings.getOffenderBookId() != null && offBook.getStatusReason().equals(ApplicationConstants.RECA)) {
					OffenderEscape offenderEscapeBean = new OffenderEscape();
					offenderEscapeBean.setEscapeId(oidadmisRepositoryImpl.getEscSeq(offBookings));
					offenderEscapeBean.setOffenderBookId(Integer.valueOf(offBookings.getOffenderBookId().toString()));
					offenderEscapeBean.setRecaptureMovementReason(offBook.getStatusReason());
					offenderEscapeBean.setReadmissAgyLocId(offBook.getAgyLocId());
					offenderEscapeBean.setReadmissionDate(bedAhHistories.getAssignmentDate());
					offenderEscapeBean.setReadmissionTime(bedAhHistories.getAssignmentTime());
					offenderEscapeBean.setModifyUserId(userName);
					offenderEscapesT1Service.offenderEscapesT1(offenderEscapeBean);
					oidadmisRepositoryImpl.updateOffenderEscapes(offenderEscapeBean);
					try {
						Integer upd = oidadmisRepositoryImpl.updateCustodyAdjustments(offenderEscapeBean.getReadmissionDate(),offenderEscapeBean.getOffenderBookId()!=null?offenderEscapeBean.getOffenderBookId().longValue():null);
						//authorization
						if(upd == 1) {
							OdynfrmSubmitDataBean ocdleglsObj =  new OdynfrmSubmitDataBean();
							String searchString = "{\"offenderBookId\":\""+offenderEscapeBean.getOffenderBookId().toString()+"\"}";
							ocdleglsObj.setSearchString(searchString);
							ocdleglsObj.setFormName("OCDLEGLS");
							ocdleglsObj = ocmpconfService.getFormData(ocdleglsObj);
							ocdleglsObj.setActionType("Modification");
							if(ocdleglsObj!= null && (ocdleglsObj.getFormIdentifier()== null || ocdleglsObj.getFormIdentifier().trim().isEmpty())) {
								ocdleglsObj.setFormIdentifier(searchString);
								ocdleglsObj.setActionType("Data Entry");
							}
							Map<String,Object>  calcReason = new HashMap<String, Object>();
							calcReason.put("staffName","System-populated");
							calcReason.put("calculationReason","ESCP_RECAP");
							calcReason.put("calcCode","ESCP_RECAP");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
							String currTimeStamp = sdf.format(eliteDateService.getDBTime());
							calcReason.put("sentDate",currTimeStamp);
							calcReason.put("sentTime",currTimeStamp);
							String automationUser = "";
							List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
							if(returnList!=null && !returnList.isEmpty()) {
								for(Map<String,Object> object:returnList) {
									if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("AUTOMATION_ELITE_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
										automationUser = object.get("VALUE").toString();
									}
								}
							}
							ocdleglsObj.setCalcReason(new ObjectMapper().writeValueAsString(calcReason));
							prosmainService.enableTriggers(ocdleglsObj, athorization, "61");
						}
					}catch (Exception e) {
						logger.error("Error in custodyAdjusments"+e);
					}
				}
				offBookings.setLivUnitDesc(offBook.getLivUnitDesc());
				offBookings.setStatusReason(offBook.getStatusReason());
				bedAhHistories.setOffenderBookId(offBookings.getOffenderBookId().intValue());
				bedAhHistory = oidadmisRepositoryImpl.bedAhPreInsertc(bedAhHistories);
				bedAhHistories.setBedAssignSeq(bedAhHistory.getBedAssignSeq());
				bedAhList.add(bedAhHistories);
				try {
					returnValue = oidadmisRepositoryImpl.bedAhInsertBedAssignmentHistories(bedAhList);
				} catch (Exception e) {
					logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
					return offBook;
				}
			}
		} else {
			final Integer offBookId = Integer.parseInt(oidadmisRepositoryImpl.offbkgPreInsertc() + "");
			offBook.setOffenderBookId(Long.valueOf(offBookId));
			offBook.setBookingNo(oidadmisRepositoryImpl.oidadmisgetnewbookingno());
			Integer staffId = ocdintakRepository.oldContactGetStaffId(userName);
			offBook.setAssignedStaffId(BigDecimal.valueOf(staffId));
			offBook.setDisclosureFlag(ApplicationConstants.YFLAG);
			offBook.setCreateUserId(offenderExternalMovements.get(0).getCreateUserId());
			try {
				offenderBookingsT1Service.offenderBookingsT1(offBook);
				String systemGeneratedStaffName =oidadmisRepositoryImpl.getSystemGeneratedUser();
				if(systemGeneratedStaffName!=null) {
					offBook.setSystemGeneratedStaff(systemGeneratedStaffName);
				} else {
					offBook.setSystemGeneratedStaff(offenderExternalMovements.get(0).getCreateUserId());
				}
				String iepLevel=null;
				boolean iepflag=false;

					iepLevel=oimmholoService.getAdmisionIepCode(bedAhHistories.getLivingUnitId()!=null?bedAhHistories.getLivingUnitId().longValue():null, offBook.getAgyLocId());

				if(iepLevel!=null) {
					offBook.setIepLevel(iepLevel);
				}
				returnValue = oidadmisRepositoryImpl.insertOffenderBookings(offBook);
				if(returnValue==18) {
					offBook.setSealFlag("18");
				}
				offenderBookingsT4Service.offenderBookingsT4(offBook.getOffenderBookId(),offBook.getCreateUserId());
				offenderBookingsT3Service.offenderBookingsT3(offBookings);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(null, offBookings, ApplicationConstants.INSERTING);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offBook,ApplicationConstants.UPDATING);
				offenderBookingsT8Service.offenderBookingsT8(offBook.getOffenderBookId(),userName);
				offenderBookingsT7Service.offenderBookingsT7Trigger(offBook);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
			}
			if (returnValue != null) {
				offBookings.setOffenderBookId(offBook.getOffenderBookId());
				offBookings.setLivUnitDesc(offBook.getLivUnitDesc());
				offBookings.setStatusReason(offBook.getStatusReason());
			}
			bedAhHistories.setOffenderBookId(offBookId);
			bedAhHistory = oidadmisRepositoryImpl.bedAhPreInsertc(bedAhHistories);
			bedAhHistories.setBedAssignSeq(bedAhHistory.getBedAssignSeq());
			bedAhHistories.setCreateUserId(offenderExternalMovements.get(0).getCreateUserId());
			bedAhList.add(bedAhHistories);
			try {
				returnValue = oidadmisRepositoryImpl.bedAhInsertBedAssignmentHistories(bedAhList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
				return offBook;
			}
		}
		for (OffenderExternalMovements offExMnts : offenderExternalMovements) {
			offExMnts.setOffenderBookIdTemp(offExMnts.getOffenderBookId());
			offExMnts.setOffenderBookId(offBook.getOffenderBookId());
			MovementReasons mov = new MovementReasons();
			try {
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExMnts);
				returnValue = oidadmisRepositoryImpl.offEmUpdateOffenderExternalMovements(offExMnts);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExMnts);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExMnts);
				BeanUtils.copyProperties(offExMnts, mov);
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(mov, mov,
						offExMnts.getOffenderBookId(), ApplicationConstants.UPDATING);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
				logger.error(e);
			}
			offExMnts.setMovementReasonCode(offBook.getStatusReason());
			offExMnts.setMovementType(ApplicationConstants.ADM);
			offExMnts.setDirectionCode(ApplicationConstants.IN);
			offExMntSeq = oidadmisRepositoryImpl.offEmPreInsertc(offExMnts);
			offExMnts.setMovementSeq(offExMntSeq.getMovementSeq());
			offExMnts.setActiveFlag(ApplicationConstants.YES);
			offExtrlMovntList.add(offExMnts);
			try {
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExMntSeq);
				returnValue = oidadmisRepositoryImpl.offemInsertOffenderExternalMovements(offExtrlMovntList);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExMntSeq);
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExMntSeq);
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, mov,
						offExMntSeq.getOffenderBookId(), ApplicationConstants.INSERTING);
				offenderExternalMovementsT8Service.updateObligationWR(offExMntSeq.getOffenderBookId(),
						offExMntSeq.getMovementType(),offExMnts.getCreateUserId());
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(offExMntSeq);
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(offExtrlMovntList.get(0), null);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(offExMntSeq);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(offExMntSeq);
				VHeaderBlock2 searchBean =  new VHeaderBlock2();
				searchBean.setOffenderBookId(new BigDecimal(offExMnts.getOffenderBookIdTemp())); 
				searchBean.setBookingType("INST");

			if(ApplicationConstants.NEW.equals(offExMnts.getAdmissionType()) && offBookings.getOffenderBookId() == 0l) {
				
				callCopyOver(offExMnts);					
				}
											
			else if(ApplicationConstants.NEW.equals(offExMnts.getAdmissionType())   && offBook.getOffenderBookId() != null ) {
				callCopyOver(offExMnts);
				
			}else if("READM".equals(offExMnts.getAdmissionType()) || "RECAP".equals(offExMnts.getAdmissionType()) ||  "TRANS".equals(offExMnts.getAdmissionType())) {
				callCopyOver(offExMnts);		 		
			}
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offemInsertOffenderExternalMovements " + e);
			}
		}
		return offBook;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderExternalMovements
	 *
	 * @
	 */
	@Transactional
	public Integer offEmUpdateOffenderExternalMovements(final OffenderExternalMovements lstOffenderExternalMovements) {
		Integer returnValue = null;
		try {
			offenderExternalMovementsT9Service.offenderExternalMovementsT9(lstOffenderExternalMovements);
			returnValue = oidadmisRepositoryImpl.offEmUpdateOffenderExternalMovements(lstOffenderExternalMovements);
			offenderExternalMovementsT5Service.offenderExternalMovementsT5(lstOffenderExternalMovements);
			offenderExternalMovementT13Service.OffenderExternalMovementT1(lstOffenderExternalMovements);
			MovementReasons mov = new MovementReasons();
			BeanUtils.copyProperties(lstOffenderExternalMovements, mov);
			offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(mov, mov,
					lstOffenderExternalMovements.getOffenderBookId(), ApplicationConstants.UPDATING);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offEmUpdateOffenderExternalMovements " + e);
		}
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<BedAssignmentHistories> oidadmisBedAhSearchBedAssignmentHistories(
			final BedAssignmentHistories searchRecord) {
		return oidadmisRepositoryImpl.bedAhSearchBedAssignmentHistories(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBedAssignmentHistories
	 *
	 * @
	 */
	@Transactional
	public Integer bedahInsertBedAssignmentHistories(final List<BedAssignmentHistories> lstBedAssignmentHistories) {
		return oidadmisRepositoryImpl.bedAhInsertBedAssignmentHistories(lstBedAssignmentHistories);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderTransactions> offTxnSearchOffenderTransactions(final OffenderTransactions searchRecord) {
		List<OffenderTransactions> returnList = oidadmisRepositoryImpl.offTxnSearchOffenderTransactions(searchRecord);
		for (OffenderTransactions offenderTransactions : returnList) {
			if (offenderTransactions.getDeductionFlag() != null) {
				String dedFlsg = oidadmisRepositoryImpl.chkOffenderDeductions(searchRecord);
				offenderTransactions.setDeductionFlag(dedFlsg);
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderTransactions
	 *
	 * @
	 */
	@Transactional
	public Integer offtxnInsertOffenderTransactions(final List<OffenderTransactions> lstOffenderTransactions) {
		return oidadmisRepositoryImpl.offtxnInsertOffenderTransactions(lstOffenderTransactions);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles searchRecord) {
		return oidadmisRepositoryImpl.sysPflSearchSystemProfiles(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSystemProfiles
	 *
	 * @
	 */
	@Transactional
	public Integer syspflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		Integer retVal = oidadmisRepositoryImpl.syspflInsertSystemProfiles(lstSystemProfiles);
		return retVal;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSystemProfiles
	 *
	 * @
	 */
	@Transactional
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		return oidadmisRepositoryImpl.sysPflDeleteSystemProfiles(lstSystemProfiles);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<MovementReasons> cgfkOffEmDspDescriptionMrRecordGroup(String movementReasonCode) {
		List<MovementReasons> movementReasons = new ArrayList<>();
		movementReasons = oidadmisRepositoryImpl.cgfkOffEmDspDescriptionMrRecordGroup(movementReasonCode);
		for (MovementReasons mmtReasonCode : movementReasons) {
			mmtReasonCode.setCode(mmtReasonCode.getMovementReasonCode());
		}
		movementReasons.forEach(result -> {
			if (result.getListSeq() != null && result.getListSeq() == 0) {
				result.setCanDisplay(false);
			}
		});
		if(Optional.ofNullable(movementReasons).isPresent()) {
			movementReasons.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return movementReasons;
	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionRGroup() {
		return oidadmisRepositoryImpl.cgfkOffEmDspDescriptionRGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRecordGroup(final String systemMode) {
		return oidadmisRepositoryImpl.cgfkOffEmDspDescriptionRecordGroup(systemMode);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkBedAhDspDescriptionRecordGroup(final String systemMode) {
		return oidadmisRepositoryImpl.cgfkBedAhDspDescriptionRecordGroup(systemMode);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionCaseloadIdRecordGroup(final String systemMode) {
		List<AgencyLocations> agyLocations = null;
		agyLocations = oidadmisRepositoryImpl.cgfkOffEmDspDescriptionCaseloadIdRecordGroup(systemMode);
		for (final AgencyLocations loadAgency : agyLocations) {
			loadAgency.setCode(loadAgency.getAgyLocId());
		}
		if(Optional.ofNullable(agyLocations).isPresent()) {
			agyLocations.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return agyLocations;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRcRecordGroup(final String systemMode) {
		return oidadmisRepositoryImpl.cgfkOffEmDspDescriptionRcRecordGroup(systemMode);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup() {
		final List<AgencyLocations> agencyLocations = oidadmisRepositoryImpl
				.cgfkOffEmDspDescriptionAgyLocIdRecordGroup();
		for (AgencyLocations agyLoc : agencyLocations) {
			agyLoc.setCode(agyLoc.getAgyLocId());
		}
		if(Optional.ofNullable(agencyLocations).isPresent()) {
			agencyLocations.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return agencyLocations;
	}

	@Transactional
	public Integer offBookingUpdateOffenderExternalMovements(final VHeaderBlock2 commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			liReturn = oidadmisRepositoryImpl.offBookingUpdateOffenderExternalMovements(commitBean);
		}
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> rgReferenceCodesStatus() {
		return oidadmisRepositoryImpl.rgReferenceCodesStatus();
	}

	@Override
	public List<ReferenceCodes> getSaffmembersDescription() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = oidadmisRepositoryImpl.getSaffmembersDescription();
		return returnList;
	}

	public List<Caseloads> caseloadIdValue(final BigDecimal offenderId,String userName) {
		return oidadmisRepositoryImpl.caseloadIdValue(offenderId,userName);
	}

	/**
	 * This method is used to execute procedure default value
	 *
	 */
	public OffenderBookings dafaultLocationData(final String agyLocId) {
		Map<String, Object> returnObject = null;
		final OffenderBookings bean = new OffenderBookings();
		returnObject = oidadmisService.getDefaults(agyLocId);
		bean.setAgyLocId(returnObject.get("P_AGY_LOC_ID") != null ? returnObject.get("P_AGY_LOC_ID").toString() : null);
		bean.setLivingUnitId(returnObject.get("P_LIVING_UNIT_ID") != null
				? new BigDecimal(returnObject.get("P_LIVING_UNIT_ID").toString())
				: null);
		return bean;

	}

	@Override
	public String getAdmissionType(VHeaderBlock2 searchBean) {
		return oidadmisService.getAdmissionType(searchBean);

	}

	public OffenderBookings getConflictEvent(OffenderExternalMovements bean) {
		OffenderBookings bkgBean = new OffenderBookings();
		List<String> list = nonAssociationRepository.getOffendersDetailsByLoc(bean.getOffenderId(),
				Integer.parseInt(bean.getLivingUnitId()));
		String nonAssDetails = "";
		if (list != null && list.size() > 0) {

			for (String str : list)
				nonAssDetails = nonAssDetails + str+"\n";

		}
		
		if (nonAssDetails.length() > 0)
			bkgBean.setWarningMsg(nonAssDetails);
		else
			bkgBean.setWarningMsg(ApplicationConstants.EMPTYDATA);
		
		String details=oidadmisRepositoryImpl.overridingDetails(bean.getOffenderId());
		
		bkgBean.setWarningPrompt(details);

		return bkgBean;
	}

	public String chkTrustFlag(final String caseloadId) {
		return oidadmisService.getTrustFlag(caseloadId);
	}

	public String chkOffenderDeductions(final OffenderTransactions searchBean) {
		return deductionsService.chkOffenderDeductions(searchBean.getCaseloadId(), searchBean.getRootOffenderId(), ApplicationConstants.AD,
				null);
	}

	@Override
	public Integer updateCustodyStatus(Long offenderBookId, boolean newBooking, String userName) {
		logger.info(this.getClass().getName()+ " updateCustodyStatus offenderBookId : " + offenderBookId);
		int result = 0;
		String resultingStatus = "";
		String custodyStatus = "";
		OffenderCustodyStatus offenderCustodyStatus = new OffenderCustodyStatus();
		offenderCustodyStatus.setOffenderBookId(offenderBookId);
		offenderCustodyStatus.setCreateUserId(userName);
		offenderCustodyStatus.setModifyUserId(userName);
		try {
			logger.info(this.getClass().getName()+ " updateCustodyStatus newBooking : " + newBooking);
			if(newBooking) {
				custodyStatus = ocdintakRepository.getCustodyStatus();
				offenderCustodyStatus.setCustodyStatus(custodyStatus);
				result = ocdintakRepository.insertCustodyStatus(offenderCustodyStatus);
			} else {
				String custFormIdentifier = "{\"offenderBookId\":\""+offenderBookId+"\",\"orderType\":\"CUST\"}";
				byte[] legalsData = ocdintakRepository.getSentences(custFormIdentifier);
				if(legalsData != null) {
					Map<String,List<Map<String,Object>>> formInfoJson = new ObjectMapper().readValue(legalsData, HashMap.class);
					List<Map<String,Object>> myJsonRowData = formInfoJson.get("myJsonRowData");
					for (Map<String,Object> order : myJsonRowData) {
						resultingStatus = ocmpconfRepository.getResultingStatus((String) order.get("status"));
						if(ApplicationConstants.A.equals(resultingStatus)) {
							break;
						}
					}
				}
				if(!ApplicationConstants.A.equals(resultingStatus)) { // need to check functionally and code
					custodyStatus = ocdintakRepository.getCustodyStatus();
					offenderCustodyStatus.setCustodyStatus(custodyStatus);
					List<OffenderCustodyStatus> offenderCustodyStatusList = new ArrayList<OffenderCustodyStatus>();
					offenderCustodyStatusList.add(offenderCustodyStatus);
					result = ocdintakRepository.updateCustodyStatus(offenderCustodyStatusList);
				} else {
					custodyStatus = calculateCustodyStatus(offenderBookId);
					offenderCustodyStatus.setCustodyStatus(custodyStatus);
					List<OffenderCustodyStatus> offenderCustodyStatusList = new ArrayList<OffenderCustodyStatus>();
					offenderCustodyStatusList.add(offenderCustodyStatus);
					result = ocdintakRepository.updateCustodyStatus(offenderCustodyStatusList);
				}
			}
		} catch(Exception e) {
			logger.error( "In updateCustodyStatus: ", e);
		}
		return result;
	}

	@Override
	public String calculateCustodyStatus(Long offenderBookId) {
		logger.info(this.getClass().getName()+ " calculateCustodyStatus offenderBookId : " + offenderBookId);
		String custodyStatus = "";
		String custFormIdentifier = "{\"offenderBookId\":\""+offenderBookId+"\",\"orderType\":\"CUST\"}";
		List<String> statusList = new ArrayList<String>();
		List<LegalCustodyStatuses> legalCustodyStatusesList = new ArrayList<LegalCustodyStatuses>();
		try {
			byte[] legalsData = ocdintakRepository.getSentences(custFormIdentifier);
			if(legalsData != null) {
				Map<String,List<Map<String,Object>>> formInfoJson = new ObjectMapper().readValue(legalsData, HashMap.class);
				List<Map<String,Object>> myJsonRowData = formInfoJson.get("myJsonRowData");
				for (Map<String,Object> order : myJsonRowData) {
					List<String> status = new ArrayList<String>();
					status = ocdintakRepository.getCustodyStatusForOrder(order.get("status").toString(), order.get("sentenceCalcType").toString());
					if(status.size() > 0)  {
						statusList.addAll(status);
					}
				}
				if(statusList.size() > 0) {
					legalCustodyStatusesList = ocdintakRepository.getLegalCustodyStatuses(statusList);
				}
				if(legalCustodyStatusesList.size() > 0) {

					for (LegalCustodyStatuses legalCustodyStatuses : legalCustodyStatusesList) {
						if(!"Y".equals(legalCustodyStatuses.getAlwaysDisplayFlag())) {
							if(custodyStatus.trim().equals("")) {
								custodyStatus = legalCustodyStatuses.getStatusCode();
							} else if(legalCustodyStatuses.getStatusRank().compareTo(legalCustodyStatusesList.get(0).getStatusRank()) < 0) {
								custodyStatus = legalCustodyStatuses.getStatusCode() + " - " +  custodyStatus;
							}
							break;
						}
						custodyStatus = custodyStatus.trim().equals("")?legalCustodyStatuses.getStatusCode() : custodyStatus + " - " + legalCustodyStatuses.getStatusCode();
					}
				} else {
					custodyStatus = ocdintakRepository.getCustodyStatus();
				}
			} 
		} catch (Exception e) {
			logger.error( "In calculateCustodyStatus: ", e);
		}
		return custodyStatus;
	}

	@Override
	public List<String> getOffenderAlertMsg(VHeaderBlock2 searchBean) {
 		List<String> alertMsg = oidadmisRepositoryImpl.getAlertMsgForReleaseOffender(searchBean);
		return alertMsg;

	}

	private Integer callCopyOver(OffenderExternalMovements offExMnts) {
		OffenderBookings offBook = new OffenderBookings();
		offBook.setRootOffenderId(offExMnts.getRootOffenderId());
		Integer returnVal = null;
		

		if ( offExMnts.isNewBookingFlag() && offExMnts.getBookingStatus().equalsIgnoreCase(ApplicationConstants.OFLAG)) {
			returnVal= omkcopyService.copyBookingData(ApplicationConstants.ADM,offExMnts.getMovementReasonCode(), (long) offExMnts.getOffenderBookIdTemp(),(long) offExMnts.getOffenderBookId(), offExMnts.getCreateUserId());

			
        } else if (offExMnts.isNewBookingFlag() && offExMnts.getBookingStatus().equalsIgnoreCase(ApplicationConstants.CFLAG)) {
        	 returnVal = omkcopyService.copyBookingData(ApplicationConstants.ADM,offExMnts.getMovementReasonCode(), (long) offExMnts.getOffenderBookIdTemp(),(long) offExMnts.getOffenderBookId(), offExMnts.getCreateUserId());

           
        }
		return returnVal;
			
	}
}
