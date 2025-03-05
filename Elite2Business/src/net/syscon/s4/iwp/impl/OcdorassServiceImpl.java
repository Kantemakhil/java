package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.community_supervision_tiers.OcdonostRepository;
import net.syscon.s4.cf.deductions.OcdofaccRepository;
//import net.syscon.s4.cf.deductions.OcufovdtRepository;
//import net.syscon.s4.cf.deductions.OcufovdtService;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
//import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
//import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetailsCommitBean;
//import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembersCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.community_supervision_tiers.WlOfficerNonOffSpecificTasks;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
//import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacService;
import net.syscon.s4.inst.casemanagement.OcdiplacRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanService;
import net.syscon.s4.inst.casemanagement.OcustfasRepository;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.impl.OcdiplacRepositoryImpl;
import net.syscon.s4.inst.legals.OcondawaitRepository;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
//import net.syscon.s4.inmate.trust.financialsmaintenance.OtdcntacService;
//import net.syscon.s4.iwp.OcdclogsRepository;
import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.iwp.OcdorassRepository;
import net.syscon.s4.iwp.OcdorassService;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgService;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;
import net.syscon.s4.pkgs.tag_workflow_adm.TagWorkflowAdmService;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;
import net.syscon.s4.triggers.OffFeeAccountProfileT1AndT2Service;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT2Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffirService;

/**
 * Class OcdorassServiceImpl
 */
@Service
public class OcdorassServiceImpl extends BaseBusiness implements OcdorassService {

	@Autowired
	private OcdorassRepository ocdorassRepository;
	@Autowired
	private ComunityPkgService comunityPkgService;

	@Autowired
	private TagWorkflowAdmService tagWorkflowAdmService;

	@Autowired
	private PimsWeightService pimsWeightService;

	@Autowired
	private PimsFileTrackingService pimsFileTrackingService;

	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;

	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;

	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;

	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private CasePlansT1Service casePlansT1Service;
	
	@Autowired
	private CasePlansT2Service casePlansT2Service;
	
	@Autowired
	private CasePlansT3Service casePlansT3Service;
	
	@Autowired
	private CasePlansTwfService casePlansTwfService;
	
	@Autowired
	private OffenderBookingAgyLocsT1Service offenderBookingAgyLocsT1Service;
	
	@Autowired
	private OffenderBookingAgyLocsT2Service offenderBookingAgyLocsT2Service;
	
	@Autowired
	private OmtoffirService omtoffirService;
	
	@Autowired
	private OcdonostRepository ocdonostRepository;

	@Autowired
	private OcdexpowRepository ocdexpowRepository;
	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	
	@Autowired
	private OcdofaccRepository ocdofaccRepository;

	@Autowired
	private OtdcntacService otdcntacService;
	
	@Autowired
	private OffFeeAccountProfileT1AndT2Service offFeeAccountProfileT1AndT2Service;
	
	@Autowired
    private TrustService trustService;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;
	
	@Autowired
	private OcdiplanRepository ocdiplanRepository;
	
	@Autowired
	private OcdiplanService ocdiplanservice;
	
	@Autowired
	private OcustfasRepository ocustfasRepository;
	
	@Autowired
	private OcdiplacRepositoryImpl ocdiplacRepositoryImpl;
	
	@Autowired
	private OcdiplacRepository ocdiplacRepository;
	
	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private OcondawaitRepository ocondawaitRepository;
	
	
	
	private static Logger logger = LogManager.getLogger(OcdorassServiceImpl.class.getName());
	private static String YFLAG = "Y";
	private static String NFLAG = "N";
	private static String UNASSIGNED = "UNASSIGNED";
	private static String ACTIVE = "ACTIVE";
	private static String UPDATING = "UPDATING";
	private static String INSERTING = "INSERTING";
	public static final String CONSTANTVALUE = "SUPV";
	public static int assignResult = 0;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderBookings> offBkg1ExecuteQuery(final OffenderBookings searchRecord) {
		List<OffenderBookings> returnList = new ArrayList<>();
		returnList = ocdorassRepository.offBkg1ExecuteQuery(searchRecord);
		for (final OffenderBookings object : returnList) {
			if (object.getRootOffenderId() != null) {
				final Offenders returnObj = ocdorassRepository.offenderBookingsPostQuery(object.getRootOffenderId());
				List<Integer> workedStaffMembers = ocdorassRepository.getWorkedStaffMembers(object.getOffenderBookId());
				object.setWorkedStaffMembers(workedStaffMembers);
				Integer offUnits = ocdorassRepository.getOffTierLevelWlUnits(object.getOffenderBookId());
				if (offUnits != null) {
					object.setWorkloadUnits(offUnits);
				} else {
					object.setWorkloadUnits(null);
				}
				object.setDspLastName(returnObj.getLastName());
				object.setDspFirstName(returnObj.getFirstName());
				object.setpOffIdDisp(returnObj.getOffenderIdDisplay());
				if (object.getAssignedStaffId() != null) {
					object.setStaffId(object.getAssignedStaffId().longValue());
				}
				offBkgAssaignCaseOfficer(object);
			}
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKG1
	 *
	 */
	@Transactional
	public Integer offBkg1Commit(final OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		OffenderBookings oldBean = null;

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderBookings newOffBkg : commitBean.getUpdateList()) {
				oldBean = ocdorassRepository.getOffBkgsOldRec(newOffBkg);
				offenderBookingsT2Service.offenderBookingsT2(newOffBkg, oldBean);
			}
			liReturn = ocdorassRepository.offBkg1UpdateOffenderBookings(commitBean.getUpdateList());
			for (OffenderBookings newOffBkg : commitBean.getUpdateList()) {
				newOffBkg.setModifyUserId(commitBean.getCreateUserId());
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldBean, newOffBkg, UPDATING);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(newOffBkg,ApplicationConstants.UPDATING);
				offenderBookingsT7Service.offenderBookingsT7Trigger(newOffBkg);
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<ExtOwnershipTransfer> extOtExecuteQuery(final ExtOwnershipTransfer searchRecord) {
		final List<ExtOwnershipTransfer> returnList = ocdorassRepository.extOtExecuteQuery(searchRecord);
		for (ExtOwnershipTransfer extOwnershipTransfer : returnList) {
			if (extOwnershipTransfer.getAssStaffId() != null) {
				List<ExtOwnershipTransfer> data = ocdorassRepository
						.ocdorassExtotGetLastNameFirstName(extOwnershipTransfer);
				if (data != null) {
					extOwnershipTransfer.setDspLastName(data.get(0).getDspLastName());
					extOwnershipTransfer.setDspFirstName(data.get(0).getDspFirstName());
				}
			}
			if (extOwnershipTransfer.getAgyLocIdFrom() != null) {
				String dataOne = ocdorassRepository.ocdorassExtotGetAgyLocDesc(extOwnershipTransfer);
				extOwnershipTransfer.setSealFlag(dataOne);
			}
			if (extOwnershipTransfer.getOffenderBookId() != null) {
				final Offenders returnObj = tagWorkflowAdmService
						.getOffDetailsTrans(extOwnershipTransfer.getOffenderBookId());
				extOwnershipTransfer.setDspLastNameTwo(returnObj.getLastName());
				extOwnershipTransfer.setDspFirstNameTwo(returnObj.getFirstName());
				extOwnershipTransfer.setpOffIdDisp(returnObj.getOffenderIdDisplay());
				List<Integer> workedWithStaffMembeList = ocdorassRepository.getWorkedStaffMembers(extOwnershipTransfer.getOffenderBookId());
				extOwnershipTransfer.setWorkedStaffMembers(workedWithStaffMembeList);
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstEXT_OT
	 *
	 */
	@Transactional
	public Integer extOtCommit(final ExtOwnershipTransferCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocdorassRepository.extOtUpdateExtOwnershipTransfer(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOmTeamMembers> vOffDetExecuteQuery(final VOmTeamMembers searchRecord) {
		final List<VOmTeamMembers> returnList = ocdorassRepository.vOffDetExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			VStaffLocation vStaffLoc = new VStaffLocation();
			vStaffLoc.setCalAgyLocId(ele.getCalAgyLocId());
			vStaffLoc.setStaffId(ele.getStaffId() != null ? ele.getStaffId().intValue() : null);
			vStaffLoc.setPosition(ele.getPosition());
			vStaffLoc.setRole(ele.getRole());
			vStaffLoc.setFromDate(ele.getFromDate());
			final Long data = comunityPkgService.getOfficerPo(vStaffLoc);
			if (data != null) {
				ele.setNbtNoOffender(data);
				ocdorassRepository.getCgnbtSkillSubTypeVOmTeamMembers(ele.getStaffId());

			}
			WlOfficerNonOffSpecificTasks obj = new WlOfficerNonOffSpecificTasks();
			obj.setStaffLocRoleId(ele.getStaffLocRoleId());
			obj.setAgyLocId(ele.getCalAgyLocId());
			obj.setStaffPosition(ele.getPosition());
			List<WlOfficerNonOffSpecificTasks> list = ocdonostRepository.getOfficeNonOffSpeTask(obj);
			Integer defUnit = ocdonostRepository.getDefaultUnit(obj.getStaffPosition());
			if (list.size() > 0) {
				ele.setRemainingWlUnits(list.get(0).getAvailableUnits());
			}	
			else if(defUnit!=null) {
				ele.setRemainingWlUnits(BigDecimal.valueOf(defUnit));
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_DET
	 *
	 */
	/**
	 * @throws Exception
	 *
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer vOffDetCommit(final VOmTeamMembersCommitBean commitBean) throws Exception {
		int liReturn = 0;
		if (commitBean.getOffbkg1UpdatetList() != null && commitBean.getOffbkg1UpdatetList().size() > 0
				&& commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getOffbkg1UpdatetList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			commitBean.getUpdateList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			commitOffbkg(commitBean.getOffbkg1UpdatetList(), commitBean.getUpdateList());
		}
		if (commitBean.getExtotUpdatetList() != null && commitBean.getExtotUpdatetList().size() > 0
				&& commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getExtotUpdatetList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			commitBean.getUpdateList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			commitProcessExtot(commitBean.getExtotUpdatetList(), commitBean.getUpdateList());
			for (ExtOwnershipTransfer object : commitBean.getExtotUpdatetList()) {
				try {
					autoCreateFeeAccounts(object.getOffenderBookId(), object.getAgyLocIdTo(), object.getOffenderId(), object.getCreateUserId());
				} catch (Exception e) {
					logger.error("Error in Class " + this.getClass().getName() + " vOffDetCommit error :: " + e);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final VOmTeamMembers obj : commitBean.getUpdateList()) {
				liReturn = postrecord(obj);
				if (liReturn == 2) {
					return liReturn;
				}
				final Boolean ocdorassGetOmTeamMand = ocdorassRepository.ocdorassGetOmTeamMand();
				obj.setOmTeamFlag(ocdorassGetOmTeamMand);

			}

		}
		if (commitBean.getUpdateList() != null) {
			WlOfficerNonOffSpecificTasks obj = new WlOfficerNonOffSpecificTasks();
			commitBean.getUpdateList().forEach(ele -> {
				obj.setAgyLocId(ele.getCalAgyLocId());
				obj.setStaffPosition(ele.getPosition());
				obj.setStaffLocRoleId(ele.getStaffLocRoleId());
				obj.setAvailableUnits(ele.getRemainingWlUnits());
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			ocdonostRepository.updateAvailableUnits(obj);
		}
		return assignResult;
	}

	private void autoCreateFeeAccounts(Long offenderBookId, String agyLocId, Long offenderId, String user ) throws Exception {
		ReferenceCodes returnObj = new ReferenceCodes();
		FeeAccountProfiles feeBean = new FeeAccountProfiles();
		try {

			feeBean.setOffenderBookId(offenderBookId);
			List<OffSupervisionStsHty> activeList = ocdsupstRepository.getActiveSUpvStHstryRecord(feeBean);
			if (!activeList.isEmpty()) {
				returnObj = ocdsupstRepository.getBillableFlag(activeList.get(0).getSupStatus());
			}
			if (returnObj.getParentCode() != null) {
				VOffenderAssigned vOffenderAssigned = new VOffenderAssigned();
				vOffenderAssigned.setAgyIocIdTo(agyLocId);
				List<String> toAgyLoIdList = ocdexpowRepository.getCaseLoadType(vOffenderAssigned);
				if(!toAgyLoIdList.isEmpty() && toAgyLoIdList.size() > 0) {
					String toAgyLoId = toAgyLoIdList.get(0);
					OffSupervisionStsHty supbean = new OffSupervisionStsHty();
					supbean.setOffenderId(offenderId);
					supbean.setCaseloadId(toAgyLoId);
					supbean.setOffenderBookId(offenderBookId.intValue());
					supbean.setCreateUserId(user);
					createTrustAccount(supbean);
					List<FeeAccountProfiles> longSupvData = new ArrayList<FeeAccountProfiles>();
					FeeAccountProfiles longSupvObj = new FeeAccountProfiles();
					longSupvObj.setOffenderBookId(offenderBookId);
					longSupvData = ocdofaccRepository.sysLongSupPflExecuteQuery(longSupvObj);
					List<CaseloadDeductionProfiles> profileData = ocdsupstRepository.caseloadDedProfExecuteQuery(toAgyLoId);
					if (!profileData.isEmpty()) {
						for (CaseloadDeductionProfiles profBean : profileData) {
							List<FeeAccountProfiles> feeAccountCaseloadList = new ArrayList<FeeAccountProfiles>();
							feeBean.setFeeCode(profBean.getDeductionType());
							feeBean.setCaseloadId(toAgyLoId);
							feeAccountCaseloadList = ocdofaccRepository.getFeeAccountListCaseloadBased(feeBean);
							if (!feeAccountCaseloadList.isEmpty()) {
								List<FeeAccountProfiles> feeUpdatelist = new ArrayList<>();
								for (FeeAccountProfiles fap : feeAccountCaseloadList) {
									fap.setModifyUserId(user);
									List<CaseloadDeductionProfiles> returnData = ocdofaccRepository
											.getBackBill(fap.getFeeCode(), fap.getCaseloadId());
									boolean pushToUpdateList = false;
									if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())) {
										if (fap.getFeeActStatus().equals("S")) {
											fap.setFeeActStatus("A");
											if (returnData.get(0).getDayOfMonth() != null) {
												feeBean.setDayOfMonth(returnData.get(0).getDayOfMonth().intValue());
											}
											fap.setEffectiveDate(new Date());
											if (returnData.get(0).getDayOfMonth() != null) {
												feeBean.setDayOfMonth(returnData.get(0).getDayOfMonth().intValue());
											}
											pushToUpdateList = true;
										}
									} else {

										if (fap.getFeeActStatus().equals("C")) {
											fap.setFeeActStatus("A");
											if (returnData.get(0).getDayOfMonth() != null) {
												feeBean.setDayOfMonth(returnData.get(0).getDayOfMonth().intValue());
											}
											pushToUpdateList = true;
										}
									}
									if (pushToUpdateList) {
										feeUpdatelist.add(fap);
									}
								}

								if (!feeUpdatelist.isEmpty()) {
									ocdexpowRepository.updateFeeAcntCaseLoad(feeUpdatelist);
									//OFF_FEE_ACCOUNT_PROFILE_T2 
									feeUpdatelist.forEach(bean->{
										offFeeAccountProfileT2Service.offFeeAccountProfileT2(bean);});
								}
							} else {
								FeeAccountProfiles beanObj = new FeeAccountProfiles();
								CaseloadDedBeneficiaries dedBen = new CaseloadDedBeneficiaries();
								CaseloadDeductionDetails dedRec = new CaseloadDeductionDetails();
								List<FeeAccountProfiles> feeInsertlist = new ArrayList<>();
								BigDecimal offenderFeeId = ocdofaccRepository.offdedPreInsert();
								beanObj.setOffenderFeeId(offenderFeeId);
								beanObj.setCaseloadId(toAgyLoId);
								beanObj.setOffenderBookId(Long.valueOf(offenderBookId));
								beanObj.setFeeCode(profBean.getDeductionType());
								beanObj.setFeeActStatus("A");
								beanObj.setCreateUserId(user);
								if (profBean.getMaxTotalAmount() == null) {
									beanObj.setAmount(BigDecimal.ZERO);
								} else {
									beanObj.setAmount(profBean.getMaxTotalAmount());
								}
								if (profBean.getDayOfMonth() != null) {
									beanObj.setDayOfMonth(Integer.valueOf(profBean.getDayOfMonth().toString()));
								}
								if(profBean.getFrequencyType() != null && "ONE".equals(profBean.getFrequencyType())) {
									beanObj.setExpiryDate(eliteDateService.getDBTime());	
								}
								beanObj.setStartDate(eliteDateService.getDBTime());
								beanObj.setOdp(ocdsupstRepository.getMaxOdp(offenderBookId.intValue(),
										profBean.getDeductionType(), profBean.getCaseloadId()));
								beanObj.setStatusEffectiveDate(eliteDateService.getDBTime());
								if ("Y".equals(profBean.getNonBillableStatus())) {
									beanObj.setEffectiveDate(eliteDateService.getDBTime());
									if (!longSupvData.isEmpty() && longSupvData.get(0).getLongestSupvExpDate() != null) {
										beanObj.setExpiryDate(longSupvData.get(0).getLongestSupvExpDate());
									}

								}
								feeInsertlist.add(beanObj);
								dedBen.setCaseloadId(toAgyLoId);
								dedBen.setDeductionType(profBean.getDeductionType());
								dedRec.setCaseloadId(toAgyLoId);
								dedRec.setDeductionType(profBean.getDeductionType());
								List<CaseloadDedBeneficiaries> benfList = ocdofaccRepository
										.caseloadDedBenficExecuteQuery(dedBen);
								
								List<CaseloadDeductionDetails> dedRecList = ocdofaccRepository
										.caseloadDedDetExecuteQuery(dedRec);
								for (CaseloadDedBeneficiaries benfBean : benfList) {
									benfBean.setCreateUserId(user);
									benfBean.setOffenderFeeId(offenderFeeId);
								}
								for (CaseloadDeductionDetails dedRecBean : dedRecList) {
									dedRecBean.setCreateUserId(user);
									dedRecBean.setOffenderFeeId(offenderFeeId);
								}
								if (!feeInsertlist.isEmpty()) {
									Integer feeIntRetVal=ocdofaccRepository.offdedInsertQuery(feeInsertlist);
									//  Trigger Call OFF_FEE_ACCOUNT_PROFILE_T1
									feeInsertlist.forEach(feeProfilesBean->{offFeeAccountProfileT1AndT2Service.offFeeAccountProfileT1AndT2(feeProfilesBean);});
									ocdofaccRepository.cslddbenInsertQuery(benfList);
									ocdofaccRepository.csldddInsertQuery(dedRecList);
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.error("Exception in OcdorassServiceImpl class autoCreateFeeAccounts : ", e);
		}

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public Boolean ocdorassGetOmTeamMand() {
		return ocdorassRepository.ocdorassGetOmTeamMand();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseLoadId) {
		return ocdorassRepository.rgAgyLocIdRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocdorassRepository.rgPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocdorassRepository.rgRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		return ocdorassRepository.rgScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return ocdorassRepository.rgSexCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Teams> rgTeamRecordGroup(final String sealFlag) {
		String position = null;
		String role = null;
		Long staffid = null;
		final String[] inputArray = sealFlag.split("-");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				position = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				role = inputArray[1];
			if (inputArray.length > 2 && inputArray[2] != null && !inputArray[2].equals(""))
				staffid = Long.parseLong(inputArray[2]);
		}
		return ocdorassRepository.rgTeamRecordGroup(position, role, staffid);
	}

	public VOmTeamMembers whenCheckboxChanged(final VOmTeamMembers parambean) {
		Integer getTeamId = 0;
		String getTeamdesc = null;
		if (YFLAG.equals(parambean.getNbtStaffId())) {
			if (parambean.getStaffId() != null) {
				getTeamId = tagWorkflowAdmService.defaultTeam(BigDecimal.valueOf(parambean.getStaffId()));
				parambean.setNbtTeamId(getTeamId);
				if (getTeamId != null) {
					Map<String, Object> mapObj = tagWorkflowAdmService.getTeamDesc(BigDecimal.valueOf(parambean.getNbtTeamId()));
					parambean.setTeamDesc(
							mapObj.get("P_TEAM_NAME") != null ? mapObj.get("P_TEAM_NAME").toString() : null);
				}
			} else {
				getTeamId = null;
				getTeamdesc = null;
			}
		}
		return parambean;
	}

	public Integer postrecord(final VOmTeamMembers parambean) {
		Integer returnVal = 0;
		if (YFLAG.equals(parambean.getTeamReqFlag())) {
			if (YFLAG.equals(parambean.getNbtStaffId()) && parambean.getStaffId() != null
					&& parambean.getTeamDesc() == null) {
				returnVal = 2;
			}
		}
		return returnVal;

	}

	public OffenderBookings offBkgAssaignCaseOfficer(final OffenderBookings paramBean) {
		Integer lvCaseOfficerId;
		long lvOffenderBookId = 0;
		lvOffenderBookId = paramBean.getOffenderBookId();
		lvCaseOfficerId = ocdorassRepository.ocdorassGetCaseOfficerId(lvOffenderBookId);
		if (lvCaseOfficerId == 0) {
			paramBean.setStaffIdFlag(YFLAG);
		} else {
			paramBean.setStaffIdFlag(NFLAG);
		}
		return paramBean;
	}

	public ExtOwnershipTransfer assignProc(final ExtOwnershipTransfer paramBean) {
		final String pAssigned = YFLAG;
		Long pBookId = null;
		if (paramBean.getpOffenderBookId() != null) {
		}
		pBookId = paramBean.getOffenderBookId();

		String vAgyLocId = paramBean.getAgyLocIdTo();
		final VOmTeamMembers objectOne = new VOmTeamMembers();
		objectOne.setOffenderBookId(pBookId);
		objectOne.setAgyLocId(vAgyLocId);
		objectOne.setPosition(paramBean.getPosition());
		objectOne.setRole(paramBean.getRole());
		objectOne.setStaffId(paramBean.getStaffId());
		objectOne.setCaseLoadId(paramBean.getCaseLoadId());
		objectOne.setCalAgyLocId(vAgyLocId);
		objectOne.setModifyUserId(paramBean.getModifyUserId());
		objectOne.setCreateUserId(paramBean.getCreateUserId());

		if (paramBean.getOffenderBookId() != null && YFLAG.equals(paramBean.getChkSelect())) {
			if (YFLAG.equals(pAssigned)) {
				insertCasePlans(objectOne);
				objectOne.setOffenderBookId(paramBean.getOffenderBookId());
				transferAssignmentInpow(objectOne);
				updateOwnership(paramBean);
				maintainOffBookingAgyLoc(paramBean);
				maintainOffBookingEvents(paramBean);
				if (paramBean.getNbtTeamId() != null) {
					tagWorkflowAdmService.assignOffender(paramBean.getOffenderBookId(), paramBean.getNbtTeamId(),
							paramBean.getCreateUserId());
				}
			}
		}
		ExtOwnershipTransfer bean=new ExtOwnershipTransfer();
		bean.setOffenderBookId(paramBean.getOffenderBookId());

		return paramBean;
	}

	public VOmTeamMembers insertCasePlans(final VOmTeamMembers searchBean) {
		String getSupervisionLevel;
		String vAgyLocId = null;
		long vId = 0;
		vAgyLocId = searchBean.getAgyLocId();
		searchBean.setAgyLocId(vAgyLocId);
		getSupervisionLevel = pimsWeightService.getSupLevel(searchBean.getOffenderBookId(),searchBean.getCreateUserId());
		if (getSupervisionLevel != null) {
			searchBean.setvSupervisionLevel(getSupervisionLevel);
		} 
		
		else {
			String superVisionLevelTest = ocustfasRepository.getCasePlanPreInsertCommClass();
			searchBean.setvSupervisionLevel(superVisionLevelTest);
		}
		
		if(searchBean.getvSupervisionLevel()!=null) {
			final Integer getReviewCode = ocdorassRepository
					.getReviewPeriodCaseReviewPeriods(searchBean.getvSupervisionLevel());
			if(getReviewCode!=null) {			
				final Date cSysdate = new Date();
				final Date addedDate1 = addDays(cSysdate, getReviewCode);
				searchBean.setvNextReviewDate(addedDate1);
			}
		}
		vId = ocdorassRepository.getMaxCasePlanId(searchBean.getOffenderBookId());
		if (vId != 0) {
			searchBean.setCasePlanId(vId);
			
			
		} else {
			searchBean.setCasePlanId(0L);
		}
		final Date vFromDate = ocdorassRepository.getFromDateStaffLocationRoles(searchBean);
		if (vFromDate != null) {
			searchBean.setFromDate(vFromDate);
		}
		final CasePlans bean = new CasePlans();
		bean.setCasePlanId(vId);
		bean.setModifyUserId(searchBean.getCreateUserId());
		bean.setCreateUserId(searchBean.getCreateUserId());
		bean.setOffenderBookId(searchBean.getOffenderBookId());
		try {
			
			List<CasePlans> vInstFromDate = ocdiplanRepository.maxCaseplanIdRecord(bean);
			for (CasePlans casePlans : vInstFromDate) {
				searchBean.setvFromDate(casePlans.getInstFromDate());
				searchBean.setvInstPosition(casePlans.getInstPosition());
				searchBean.setvInstRole(casePlans.getInstRole());
				searchBean.setvInstSacStaffId(casePlans.getInstSacStaffId());
				searchBean.setvInstCalAgyLocId(casePlans.getInstCalAgyLocId());
				searchBean.setvAutoAssessModify(casePlans.getAutoAssessModifyDatetime());
				searchBean.setModifyDatetime(casePlans.getAutoConditionModifyDatetime());			
				casePlans.setOffenderBookId(searchBean.getOffenderBookId());
				casePlans.setCalAgyLocId(searchBean.getCalAgyLocId());
				casePlans.setPosition(searchBean.getPosition());
				casePlans.setRole(searchBean.getRole());
			}
		if (vId == 0) {
			searchBean.setCreateUserId(searchBean.getCreateUserId());
			
			searchBean.setCasePlanId(vId+1);
			searchBean.setCasePlanStatus(ACTIVE);
			bean.setAgyLocId(vAgyLocId);
			casePlansTwfService.casePlansTwf(bean);
			int result = ocdorassRepository.ocdorassInsertCasePlans(searchBean);				
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
			bean.setCasePlanId(searchBean.getCasePlanId());
			// calling CASE_PLANS_T1 trigger
			casePlansT1Service.casePlansT1(bean);
			//calling CASE_PLANS_T2
			casePlansT2Service.casePlansT2(bean, searchBean.getCreateUserId());
			//calling CASE_PLANS_T3
			casePlansT3Service.casePlansT3(bean, searchBean.getCreateUserId());
		} else {
			String getUser = ocdorassRepository.getCreationUserCasePlans(searchBean.getOffenderBookId(), vId);
			if (getUser != null) {
				searchBean.setCreateUserId(getUser);
			}
			
		    ocdiplanRepository.casePlanOldRecUpdate(vInstFromDate);	
			
		    List<CasePlanStaff> staffPlanList=ocdiplacRepository.getAllStaffMembersList(searchBean.getOffenderBookId().intValue(), Long.valueOf(vId).intValue());		
			for (CasePlans casePlanObj : vInstFromDate) {
				List<CasePlans> insertListCase=new ArrayList<CasePlans>();
				casePlanObj.setCasePlanId(vId+1);
				casePlanObj.setSacStaffId(BigDecimal.valueOf(searchBean.getStaffId()));
				casePlanObj.setCasePlanStatus(ACTIVE);
				casePlanObj.setFromDate(searchBean.getFromDate());
				casePlanObj.setOffenderBookId(searchBean.getOffenderBookId());
				casePlanObj.setCalAgyLocId(searchBean.getCalAgyLocId());
				casePlanObj.setPosition(searchBean.getPosition());
				casePlanObj.setRole(searchBean.getRole());
				casePlanObj.setAgyLocId(vAgyLocId);
				casePlanObj.setCreateUserId(searchBean.getCreateUserId());
				casePlanObj.setModifyUserId(searchBean.getCreateUserId());
				casePlanObj.setVerifiedFlag("N");
				casePlanObj.setSupervisionLevel(searchBean.getvSupervisionLevel());;
				insertListCase.add(casePlanObj);
				ocdiplanRepository.casePlanInsert(insertListCase);
				// calling CASE_PLANS_T1 trigger
				casePlansT1Service.casePlansT1(casePlanObj);
				//calling CASE_PLANS_T2
				casePlansT2Service.casePlansT2(casePlanObj, searchBean.getCreateUserId());
				//calling CASE_PLANS_T3
				casePlansT3Service.casePlansT3(casePlanObj, searchBean.getCreateUserId());
				
			}		
			for (CasePlanStaff casePlanStaff : staffPlanList) {
				casePlanStaff.setCasePlanId((Long.valueOf(vId).intValue())+1);
			}		
			ocdiplacRepository.insertCasePlanStaffMemberDetails(staffPlanList);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return searchBean;
	}

	private Date addDays(Date cSysdate, Integer getReviewCode) {
		cSysdate.setTime(cSysdate.getTime() + getReviewCode * 1000 * 60 * 60 * 24);
		return cSysdate;
	}

	public VOmTeamMembers transferAssignmentInpow(VOmTeamMembers searchBean) {
		final long vBookId = searchBean.getOffenderBookId();
		YFLAG.equals(searchBean.getNbtStaffId());
		final Date vFromDate = ocdorassRepository.getFromDateStaffLocationRoles(searchBean);
		if (vFromDate != null) {
			final VOmTeamMembers vsGetPrevAssignCur = ocdorassRepository.getPreviousOffenderWorkAssignments(vBookId);
			if (vsGetPrevAssignCur != null && vsGetPrevAssignCur.getAsstraId() != null) {
				final Long asstraSeq = ocdorassRepository.getAsstraSeqNextval();
				searchBean.setAsstraId(asstraSeq);
				int result = ocdorassRepository.ocdorassInsertAssignmentTransfers(searchBean);
				if (result == 1) {
					result = ocdorassRepository.ocdorassUpdateAssignmentTransfers(searchBean);
				} 
				if(result == 1){
					assignResult = 1;
				} else {
					assignResult = 0;
				}
			}
		}
		return searchBean;
	}

	public ExtOwnershipTransfer updateOwnership(final ExtOwnershipTransfer parambean) {
		int result = ocdorassRepository.updateExtOwnershipTransfer(parambean);
		if (result == 1) {
			assignResult = 1;
		} else {
			assignResult = 0;
		}
		return parambean;
	}

	public ExtOwnershipTransfer maintainOffBookingAgyLoc(final ExtOwnershipTransfer parambean) {
		final OffenderBookings obj = new OffenderBookings();
		obj.setOffenderBookId(parambean.getOffenderBookId());
		obj.setAgyLocId(parambean.getAgyLocIdTo());
		obj.setIntakeCaseloadId(parambean.getCaseLoadId());
		obj.setCreateUserId(parambean.getCreateUserId());
		obj.setModifyUserId(parambean.getModifyUserId());
		final Long lvChk = ocdorassRepository.countOffenderBookingAgyLocs(obj.getOffenderBookId(), obj.getAgyLocId());
		if (lvChk == 0) {
			
			//OFFENDER_BOOKING_AGY_LOCS_T3 trigger implemented directly in the query
			int result = ocdorassRepository.insertOffenderBookingAgyLocs(obj);
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
			OffenderBookingAgyLocs offBkAgyLoc = new OffenderBookingAgyLocs();
			offBkAgyLoc.setOffenderBookId(parambean.getOffenderBookId());
			offBkAgyLoc.setAgyLocId(parambean.getAgyLocIdTo());
			offBkAgyLoc.setModifyUserId(parambean.getCreateUserId());
			offBkAgyLoc.setCreateUserId(parambean.getCreateUserId());
			//calling OFFENDER_BOOKING_AGY_LOCS_T1
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(offBkAgyLoc, INSERTING);
			//calling OFFENDER_BOOKING_AGY_LOCS_T2
			offenderBookingAgyLocsT2Service.offenderBookingAgyLocsT2Triger(offBkAgyLoc);
			
		}
		obj.setAgyLocId(parambean.getAgyLocIdFrom());
		ocdorassRepository.updateOffenderBookingAgyLocs(obj);
		
		//calling OFFENDER_BOOKING_AGY_LOCS_T1
		OffenderBookingAgyLocs offBkAgyLocForUp = new OffenderBookingAgyLocs();
		offBkAgyLocForUp.setOffenderBookId(obj.getOffenderBookId());
		offBkAgyLocForUp.setAgyLocId(obj.getAgyLocId() != null ? obj.getAgyLocId().toString() : null);
		offBkAgyLocForUp.setRemovedDate(parambean.getTransferDate());
		offBkAgyLocForUp.setCreateUserId(parambean.getCreateUserId());
		offBkAgyLocForUp.setModifyUserId(parambean.getCreateUserId());
		offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(offBkAgyLocForUp, UPDATING);
		return parambean;

	}

	public ExtOwnershipTransfer maintainOffBookingEvents(final ExtOwnershipTransfer parambean) {
		final Long lEventSeq = ocdorassRepository.getEventSeqOffenderBookingsEvents(parambean.getOffenderBookId());
		if (lEventSeq != null) {
			parambean.setEventSeq(lEventSeq);
			
			//OFFENDER_BOOKING_EVENTS_T1 trigger implemented directly in the sql query
			int result = ocdorassRepository.insertIntoOffenderBookingEvents(parambean);
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
		}
		return parambean;
	}

	public OffenderBookings closeIntakeLocation(final OffenderBookings searchBean) {
		final Long lvCheck = ocdorassRepository.countOffenderBookingAgyLocs(searchBean.getOffenderBookId(),
				searchBean.getAgyLocId());
		if (lvCheck == 0) {
			ocdorassRepository.getUpdateOffenderBookingAgyLocs(searchBean);
			//calling OFFENDER_BOOKING_AGY_LOCS_T1
			OffenderBookingAgyLocs offBkAgyLocForUpTwo = new OffenderBookingAgyLocs();
			offBkAgyLocForUpTwo.setOffenderBookId(searchBean.getOffenderBookId());
			offBkAgyLocForUpTwo.setAgyLocId(searchBean.getAgyLocId() != null ? searchBean.getAgyLocId().toString() : null);
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(offBkAgyLocForUpTwo, UPDATING);//Found
			
			//OFFENDER_BOOKING_AGY_LOCS_T3 trigger implemented directly in the query
			int result = ocdorassRepository.insertOffenderBookingAgyLocs(searchBean);
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
			
			OffenderBookingAgyLocs offBkAgyLoc = new OffenderBookingAgyLocs();
			offBkAgyLoc.setOffenderBookId(searchBean.getOffenderBookId());
			offBkAgyLoc.setAgyLocId(searchBean.getAgyLocId() != null ? searchBean.getAgyLocId().toString() : null);
			//calling OFFENDER_BOOKING_AGY_LOCS_T1
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(offBkAgyLoc, INSERTING);
			//calling OFFENDER_BOOKING_AGY_LOCS_T2
			offenderBookingAgyLocsT2Service.offenderBookingAgyLocsT2Triger(offBkAgyLoc);
		}
		return searchBean;
	}

	public List<OffenderBookings> commitOffbkg(final List<OffenderBookings> list, final List<VOmTeamMembers> listTeam) {
		VOmTeamMembers objectOne = new VOmTeamMembers();
		for (final VOmTeamMembers bean : listTeam) {
			objectOne = bean;
		}
		for (final OffenderBookings searchBean : list) {
			if (YFLAG.equals(searchBean.getSealFlag())) {
				objectOne.setOffenderBookId(searchBean.getOffenderBookId());
				objectOne.setCalAgyLocId(searchBean.getAgyLocId());
				objectOne.setAgyLocId(searchBean.getAgyLocId());
				objectOne.setCaseLoadId(searchBean.getIntakeCaseloadId());
				objectOne.setOffenderId(searchBean.getOffenderId().longValue());
				insertCasePlans(objectOne);
				transferAssignmentAtpow(objectOne);
				transferFiles(objectOne);
				closeIntakeLocation(searchBean);
			}
		}

		return list;

	}

	public VOmTeamMembers transferAssignmentAtpow(final VOmTeamMembers searchBean) {
		Date vFromDate = ocdorassRepository.getFromDateStaffLocationRoles(searchBean);
		try {
		if (vFromDate != null) {
			ocdorassRepository.getOffenderWorkAssignmentsOffBkgCommit(searchBean.getOffenderBookId());
		}
		if (searchBean.getNbtTeamId() != null) {
			tagWorkflowAdmService.assignOffender(searchBean.getOffenderBookId(), searchBean.getNbtTeamId(),
					searchBean.getCreateUserId());
		}
		}catch (Exception e) {
			return searchBean;
		}

		return searchBean;
	}

	public VOmTeamMembers transferFiles(final VOmTeamMembers searchBean) {
		String lvNonOfficerStatus = null;
		Long lvOffenderFileSeq = null;
		String lvProfileValue = ocdorassRepository.getProfileValueSystemProfiles();
		if (YFLAG.equals(lvProfileValue)) {
			lvOffenderFileSeq = ocdorassRepository.getOffenderFileSeqOffenderCommunityFiles(searchBean.getOffenderId());
		}
		if (lvOffenderFileSeq != null && UNASSIGNED.equals(lvNonOfficerStatus)) {
			//Calling OMTOFSB trigger
			ocdorassRepository.executingOmtofsbTrigger(searchBean.getModifyUserId());
			int result = ocdorassRepository.getUpdateOffenderCommunityFiles(searchBean);
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
			//Calling OMTOFFIR trigger
			OffenderCommunityFiles newBean = new OffenderCommunityFiles();
			newBean.setHoldingAgyLocId(searchBean.getAgyLocId());
			newBean.setHoldingStaffId(searchBean.getStaffId());
			newBean.setCreateUserId(searchBean.getCreateUserId());
			List<OffenderCommunityFiles> list = new ArrayList<OffenderCommunityFiles>();
			list.add(newBean);
			omtoffirService.omtoffirTgr(list);
			
		}
		return searchBean;
	}

	public List<ExtOwnershipTransfer> commitProcessExtot(final List<ExtOwnershipTransfer> list,
			final List<VOmTeamMembers> teamList) {
		VOmTeamMembers obj = new VOmTeamMembers();
		for (final VOmTeamMembers bean : teamList) {
			obj = bean;
		}
		for (final ExtOwnershipTransfer searchBean : list) {
			if (YFLAG.equals(searchBean.getChkSelect())) {
				obj.setOffenderBookId(searchBean.getOffenderBookId());
				obj.setAgyLocId(searchBean.getAgyLocIdTo());
				assignProc(searchBean);
				transferOffenderFiles(searchBean);
				updateOrderCondition(searchBean);

			}
		}

		return list;
	}
	private ExtOwnershipTransfer updateOrderCondition(ExtOwnershipTransfer searchBean) {
	    Long vBookId = searchBean.getOffenderBookId();
	    List<OffenderCondTransfer> conditionsList = ocdorassRepository.getOffenderCondition(vBookId); 
	    
	    if (conditionsList != null) {
	    	List<OffenderCondTransfer> updateConditionlist = new ArrayList<OffenderCondTransfer>();
	        for (OffenderCondTransfer conditionId : conditionsList) { 
	            	OffenderCondTransfer updateCondition = new OffenderCondTransfer();
	                updateCondition.setOffenderBookId(BigDecimal.valueOf(vBookId));
	                updateCondition.setOffenderSentConditionId(conditionId.getOffenderSentConditionId()); 
	                updateCondition.setToAgyLocId(searchBean.getAgyLocIdTo());
	                updateCondition.setRcvdFromLoc(searchBean.getAgyLocIdFrom());
	                updateCondition.setParentCondTransferId(conditionId.getConTransferId());
	                updateCondition.setCreateUserId(searchBean.getCreateUserId());
	                updateCondition.setCondiStatus("TRANSFERRED");
	                updateCondition.setSentenceSeq(conditionId.getSentenceSeq());	                
	            	updateConditionlist.add(updateCondition);        	
                          
	        	            }
	        
	        ocondawaitRepository.offenderCondTransferInsert(updateConditionlist);
	        
	    }
	    
	    return searchBean;
	}





	public ExtOwnershipTransfer transferOffenderFiles(final ExtOwnershipTransfer searchBean) {
		String vNonOfficerStatusFrom = null;
		Long vBookId = searchBean.getOffenderBookId();
		Long vOffenderId = ocdorassRepository.getOffenderIdOffenderBookings(vBookId);
		List<OffenderCommunityFile> communityList = null;
		if (vOffenderId != null) {
			searchBean.setOffenderId(vOffenderId);
			communityList = ocdorassRepository.getDetailsOffenderCommunityFiles(searchBean);
		}
		if (communityList.size() > 0 && communityList.get(0).getOffenderFileSeq() != 0
				&& UNASSIGNED.equals(vNonOfficerStatusFrom)) {
			//Calling OMTOFSB trigger
			ocdorassRepository.executingOmtofsbTrigger(searchBean.getModifyUserId());
			
			int result = ocdorassRepository.getUpdateOffenderCommunityFilesTwo(searchBean);
			if (result == 1) {
				assignResult = 1;
			} else {
				assignResult = 0;
			}
			//Calling OMTOFFIR trigger
			OffenderCommunityFiles newBean = new OffenderCommunityFiles();
			newBean.setHoldingAgyLocId(searchBean.getAgyLocId() != null ? searchBean.getAgyLocId().toString() : null);
			//newBean.setNonOfficerStatus(searchBean.getoff);
			newBean.setHoldingStaffId(searchBean.getStaffId());
			newBean.setCreateUserId(searchBean.getCreateUserId());
			List<OffenderCommunityFiles> list = new ArrayList<OffenderCommunityFiles>();
			list.add(newBean);
			omtoffirService.omtoffirTgr(list);
			pimsFileTrackingService.transferFile(
					searchBean.getOffenderFileSeq() != null ? searchBean.getOffenderFileSeq().intValue() : null,
					searchBean.getOffenderId(), null, searchBean.getAgyLocIdFrom(), searchBean.getAgyLocIdFrom(), null,
					searchBean.getAssStaffId(), searchBean.getNonOfficerStatus(), null, searchBean.getCreateUserId());
		}
		return searchBean;
	}
	public void createTrustAccount(final OffSupervisionStsHty bean) throws Exception {
		List<OffenderTransactions> offTxnInsertList = new ArrayList<>();
		OffenderTransactionsCommitBean commitBean = new OffenderTransactionsCommitBean();
		OffenderTransactions offtxnModel = new OffenderTransactions();
		Map<String, Object> trustAccStatus = new HashMap<String, Object>();
		trustAccStatus = trustService.chkAccountStatus(bean.getCaseloadId(),new BigDecimal(bean.getOffenderId()));
		if ("X".equals(trustAccStatus.get("P_OPEN_AN_ACCOUNT"))) {
			offtxnModel.setOffenderId(bean.getOffenderId());
			offtxnModel.setOffenderBookId(Long.valueOf(bean.getOffenderBookId()));
			offtxnModel.setCaseloadId(bean.getCaseloadId());
			offtxnModel.setTxnPostingType("CR");
			offtxnModel.setSlipPrintedFlag("N");
			offtxnModel.setReceiptPrintedFlag("Y");
			offtxnModel.setDeductionFlag("Y");
			offtxnModel.setTxnAdjustedFlag("Y");
			offtxnModel.setHoldClearFlag("Y");
			offtxnModel.setAdjustTxnEntryId(99);
			offtxnModel.setTxnEntryDate(eliteDateService.getDBTime());
			offtxnModel.setModifyDate(eliteDateService.getDBTime());
			offtxnModel.setCreateDatetime(eliteDateService.getDBTime());
			offtxnModel.setTxnEntryAmount(0.00);
			offtxnModel.setCreateUserId(bean.getCreateUserId());
			offTxnInsertList.add(offtxnModel);
			commitBean.setInsertList(offTxnInsertList);
			OffenderTransactions offTxn = otdcntacService.offTxnCommit(commitBean);
			if(offTxn.getSealFlag() != null && offTxn.getSealFlag() != "1") {
				throw new Exception();
			}
		}
	}


}
