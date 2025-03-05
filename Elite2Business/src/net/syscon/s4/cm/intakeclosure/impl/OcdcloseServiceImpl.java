package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.intakeclosure.OcdcloseRepository;
import net.syscon.s4.cm.intakeclosure.OcdcloseService;
//import net.syscon.s4.cm.intakeclosure.OcdsupstService;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
//import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
//import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.pkgs.tag_termination.TagTerminationService;
import net.syscon.s4.sa.recordmaintenance.CmdactionRepository;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffirService;

/**
 * Class OcdcloseServiceImpl
 */
@Service
public class OcdcloseServiceImpl extends BaseBusiness implements OcdcloseService {

	@Autowired
	private OcdcloseRepository ocdcloseRepository;
	
	@Autowired
	private TagTerminationService tagTerminationService;
	
	@Autowired
	private PimsFileTrackingService pimsFileTrackingService;
	
	@Autowired
	private CasePlansT1Service casePlansT1Service;
	
	@Autowired
	private CasePlansT3Service casePlansT3Service;
	
	@Autowired
	private CasePlansT2Service casePlansT2Service;
	
	@Autowired
	private CasePlansTwfService casePlansTwfService;
	
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingAgyLocsT1Service offenderBookingAgyLocsT1Service;
	
	@Autowired
	private OmtoffirService omtoffirService;
	
	@Autowired
	private OcdexpowRepository ocdexpowRepository;
	
	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service ;
	
	@Autowired
	private CmdactionRepository cmdactionRepository;
	
	private static Logger logger = LogManager.getLogger(OcdcloseServiceImpl.class);
	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer obePreInsert(final OffenderBookingEvent paramBean) {

		return ocdcloseRepository.obePreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfkchkObeObeRefCodeF2(final ReferenceCodes paramBean) {
		return ocdcloseRepository.cgfkchkObeObeRefCodeF2(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> cgfklkpObeObeRefCodeF2(final ReferenceCodes paramBean) {
		return ocdcloseRepository.cgfklkpObeObeRefCodeF2(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderBookingEvent> obeExecuteQuery(final OffenderBookingEvent searchRecord) {
		return ocdcloseRepository.obeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOBE
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer obeCommit(final OffenderBookingEventCommitBean commitBean, final String authorization) {
		OffenderBookingEvent bean = new OffenderBookingEvent();
		bean = commitBean.getInsertList().get(0);
		Integer liReturn = 0;
		Integer vCount = 0;
		Integer vCountOne;
		Integer vCountTwo = 0;
		String operation = "UPDATING";
		List<CaseloadAgencyLocations> vAgyLocOne;
		List<CaseloadAgencyLocations> vAgyLocTwo;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (final OffenderBookingEvent obj : commitBean.getInsertList()) {
		obj.setCreateUserId(commitBean.getCreateUserId());
		obj.setModifyUserId(commitBean.getCreateUserId());
				// preInsert2
				obj.setBookingEventCode("CAC");
				obj.setEventUser(commitBean.getCreateUserId());
				final Integer eventSeq = obePreInsert(obj);
				if (eventSeq != 0) {
					obj.setEventSeq(eventSeq);
				}
				// KEY_COMMIT_2
				// check_caseload_access
				vAgyLocOne = ocdcloseRepository.checkCaseloadAccessAgyLocCur(obj);
				vAgyLocTwo = ocdcloseRepository.checkCaseloadAccessAgyLocCurTwo(obj);
				vCountTwo = ocdcloseRepository.checkCaseloadAccessCountTwo(obj);
				for (final CaseloadAgencyLocations object : vAgyLocTwo) {
					vCountOne = ocdcloseRepository.checkCaseloadAccessCountOne(obj);
					for (final CaseloadAgencyLocations agyObj : vAgyLocOne) {
						if (object.getAgyLocId().equals(agyObj.getAgyLocId())) {
							vCount = vCount + 1;
						}
						vCountOne = vCountOne - 1;
						if (vCountOne == 0) {
							break;
						}
					}
					vCountTwo = vCountTwo - 1;
					if (vCountTwo == 0) {
						break;
					}
				}
				vCountTwo = ocdcloseRepository.checkCaseloadAccessCountTwo(obj);
				if (vCount != vCountTwo && obj.getSealFlag() == null) {
					return 5;
				}
				// check_external_transfer
				final Integer curExttran = ocdcloseRepository.checkExternalTransfer(obj);
				if (curExttran == 1) {
					return 4;
				}

				Integer preInsertVal = obePreInsertTwo(obj);
				if (preInsertVal != null && preInsertVal == 0) {
					return 0;
				}
				// update_offender_bookings
				if ("N".equals(obj.getCheckFlag()) || obj.getCheckFlag() == null) {
					OffenderBookings old = new OffenderBookings();
					old = ocdcloseRepository.aluesOffenderBookings(obj.getOffenderBookId());
					old.setModifyUserId(commitBean.getCreateUserId());
					OffenderBookings objnew = new OffenderBookings();
					objnew.setOffenderBookId(obj.getOffenderBookId());
					objnew.setActivityDate(obj.getEventDate());
					offenderBookingsT2Service.offenderBookingsT2(objnew, old);
					ocdcloseRepository.updateOffenderBookings(obj);
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(old,ApplicationConstants.INSERTING);
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(old, objnew, operation);
					offenderBookingsT7Service.offenderBookingsT7Trigger(old);
				} else if ("Y".equals(obj.getCheckFlag())) {
					OffenderBookings old = new OffenderBookings();
					old = ocdcloseRepository.aluesOffenderBookings(obj.getOffenderBookId());
					OffenderBookings objnew = new OffenderBookings();
					objnew.setOffenderBookId(obj.getOffenderBookId());
					objnew.setActivityDate(obj.getEventDate());
					offenderBookingsT2Service.offenderBookingsT2(objnew, old);
					ocdcloseRepository.updateOffenderBookingsy(obj);
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(old,ApplicationConstants.INSERTING);
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(old, objnew, operation);
					offenderBookingsT7Service.offenderBookingsT7Trigger(old);
				}
				// caseplan_and_paperfile
				final CasePlans returnObj = ocdcloseRepository.caseplanAndPaperfileCasePlanCur(obj);
				returnObj.setCreateUserId(commitBean.getCreateUserId());
				returnObj.setModifyUserId(commitBean.getCreateUserId());
				if (returnObj.getCasePlanId() != null) {
					ocdcloseRepository.casePlanUpdate(returnObj);
					//Adding Trigger Call
					casePlansT1Service.casePlansT1(returnObj);
				}
				if (returnObj.getCasePlanId() != null && returnObj.getInstActiveFlag() != null && "Y".equals(returnObj.getInstActiveFlag())  && returnObj.getInstSacStaffId() != null
						&& returnObj.getInstSacStaffId() != null) {
					casePlansTwfService.casePlansTwf(returnObj);
					ocdcloseRepository.casePlanInsert(returnObj);
					//Adding Trigger Call
					casePlansT1Service.casePlansT1(returnObj);
					casePlansT3Service.casePlansT3(returnObj, obj.getCreateUserId());
					casePlansT2Service.casePlansT2(returnObj, obj.getCreateUserId());
				}

				final String vagyLocTo = ocdcloseRepository.vagyLocTo();
				final String profileValue = ocdcloseRepository.vProfileValue();
				if ("Y".equals(profileValue) && vagyLocTo != null) {
					final List<OffenderCommunityFile> returnobj = ocdcloseRepository
							.caseplanAndPaperfileVsPaperCur(obj);
					for (final OffenderCommunityFile object : returnobj) {
						object.setHoldingAgyLocId(vagyLocTo);
						// add trigger
						ocdcloseRepository.ocdcloseTriggerDelete();
						ocdcloseRepository.offenderCommunityFilesUpdate(object);
						List<OffenderCommunityFiles>  offenderCommunityFiles = ocdcloseRepository.offenderCommunityFiles(obj.getOffenderBookId());
						if (offenderCommunityFiles.size() > 0) {
							omtoffirService.omtoffirTgr(offenderCommunityFiles);
						}
						if (object != null) {
							OffenderFileTransactions objData = new OffenderFileTransactions();
							objData.setOffenderFileSeq(object.getOffenderFileSeq());
							objData.setOffenderId(object.getOffenderId());
							objData.setConfirmed("Y");
							objData.setNonOfficerTo(object.getNonOfficerStatus());
							pimsFileTrackingService.insertTrans(objData, commitBean.getCreateUserId());
						}
						String pNonOfficerTo = "INT";
						String pFileTransComment =null;
						Long pStaffIdTo = null;
						Integer returnVal =null;
						try {
							pimsFileTrackingService.transferFile(Long.valueOf(object.getOffenderFileSeq()).intValue(),
									object.getOffenderId(), pFileTransComment, "", "",
									object.getHoldingStaffId().longValue(), pStaffIdTo, object.getNonOfficerStatus(),
									pNonOfficerTo, commitBean.getCreateUserId());
						} catch (Exception e) {
							e.printStackTrace();
							returnVal=0;
						}
						returnVal=2;
					}
				}


			}
			
			liReturn = ocdcloseRepository.obeInsertOffenderBookingEvents(commitBean.getInsertList());
			if (liReturn == 1) {
				// postInsert2
				postInsert(bean, commitBean.getCreateUserId());
			}
			
			List<ReferenceCodes> returnList = ocdcloseRepository.getListOfReasonRefCode();
			for (final OffenderBookingEvent obj : commitBean.getInsertList()) {
				if (!returnList.isEmpty()) {
					for (ReferenceCodes object : returnList) {
						if (obj.getReasonCode().equals(object.getCode())) {
							List<FeeAccountProfiles> feeUpdatelist = new ArrayList<>();
							ExtOwnershipTransfer beanObject = new ExtOwnershipTransfer();
							beanObject.setOffenderBookId(obj.getOffenderBookId());
							List<FeeAccountProfiles> feelist = ocdexpowRepository.getSupData(beanObject);
							for (FeeAccountProfiles feeBean : feelist) {
								boolean pushToUpdateList = false;
								logger.info("Input fee code"+feeBean.getFeeCode()+"Input caseload id"+feeBean.getCaseloadId());
								List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(feeBean.getFeeCode(), feeBean.getCaseloadId());
								logger.info("Output of returndata fee code"+returnData.get(0).getDeductionType()+"Output of returndata Non billable status"+returnData.get(0).getNonBillableStatus() +"Output of returndata Fee Code"+returnData.get(0).getDeductionType());
								if (!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())) {
									if ("OC".equals(object.getParentCode())) {
										feeBean.setFeeActStatus("S");
										pushToUpdateList = true;
									} else if ("PC".equals(object.getParentCode())) {
										feeBean.setFeeActStatus("C");
										pushToUpdateList = true;
									}

								} else {
									if ("N".equals(returnData.get(0).getNonBillableStatus())
											&& ("A".equals(feeBean.getFeeActStatus())
													|| "P".equals(feeBean.getFeeActStatus())
													|| "S".equals(feeBean.getFeeActStatus()))) {
										feeBean.setFeeActStatus("C");
										pushToUpdateList = true;
									}
								}
								if (pushToUpdateList) {
									feeUpdatelist.add(feeBean);
								}

							}
							if (!feeUpdatelist.isEmpty()) {
								for (FeeAccountProfiles referenceCodes : feeUpdatelist) {
									if(referenceCodes.getFeeCode()!=null) {									
										logger.info("the update fee list data is fee code is" +referenceCodes.getFeeCode() +"caselod id is "+referenceCodes.getCaseloadId() +" fee account status"+ referenceCodes.getFeeActStatus());
									}
								}							
								ocdexpowRepository.updateFeeAccounts(feeUpdatelist);
								// OFF_FEE_ACCOUNT_PROFILE_T2 TRIGGER CALL
								feeUpdatelist.forEach(feeListBean->{offFeeAccountProfileT2Service.offFeeAccountProfileT2(feeListBean);});
							}
						}
					}
				}

			}
		}
		return liReturn;
	}

	@Transactional
	public Integer obePreInsertTwo(final OffenderBookingEvent obj) {
		Integer returnObj = 0;
		String operation = "UPDATING";
		if ("Y".equals(obj.getCheckFlag())) {
			OffenderBookings old = new OffenderBookings();
			old = ocdcloseRepository.aluesOffenderBookings(obj.getOffenderBookId());
			old.setModifyUserId(obj.getCreateUserId());
			// pre_insert2
			// 1.CHECK_INSTITUTION
			OffenderBookings objnew = new OffenderBookings();
			objnew.setOffenderBookId(obj.getOffenderBookId());
			objnew.setActivityDate(obj.getEventDate());
			objnew.setAgyLocId(obj.getToAgyLocId());
			objnew.setOffenderId(BigDecimal.valueOf(obj.getOffenderId()));
			offenderBookingsT2Service.offenderBookingsT2(objnew, old);
			returnObj = ocdcloseRepository.checkInstUpdateOffenderBookings(obj);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(old,ApplicationConstants.UPDATING);
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(old, objnew, operation);
			offenderBookingsT7Service.offenderBookingsT7Trigger(old);
			
			returnObj = ocdcloseRepository.checkInstUpdateOffenderBookingAgyLoc(obj);
			OffenderBookingAgyLocs obal = new OffenderBookingAgyLocs();
			obal.setOffenderBookId(obj.getOffenderBookId());
			obal.setRemovedDate(obj.getEventDate());
			obal.setRemovedReasonCode(obj.getReasonCode());
			obal.setAgyLocId(obj.getToAgyLocId());
			obal.setCreateUserId(obj.getCreateUserId());
			obal.setCreateDatetime(obj.getCreateDatetime());
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obal, operation);
		} else {
			// 2.CHECK_MULTY_CASELOAD
			OffenderBookings old = new OffenderBookings();
			old = ocdcloseRepository.aluesOffenderBookings(obj.getOffenderBookId());
			OffenderBookings objnew = new OffenderBookings();
			objnew.setOffenderBookId(obj.getOffenderBookId());
			objnew.setOffenderId(BigDecimal.valueOf(obj.getOffenderId()));
			objnew.setActivityDate(obj.getEventDate());
			objnew.setAgyLocId(obj.getToAgyLocId());
			objnew.setOffenderId(BigDecimal.valueOf(obj.getOffenderId()));
			offenderBookingsT2Service.offenderBookingsT2(objnew, old);
			returnObj = ocdcloseRepository.checkMultyCsldUpdateOffenderBookings(obj);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(old,ApplicationConstants.UPDATING);
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(old, objnew, operation);
			offenderBookingsT7Service.offenderBookingsT7Trigger(old);
			
			returnObj = ocdcloseRepository.checkMultyCsldUpdateOffenderBookingAgyLoc(obj);
			OffenderBookingAgyLocs obal = new OffenderBookingAgyLocs();
			obal.setOffenderBookId(obj.getOffenderBookId());
			obal.setRemovedDate(obj.getEventDate());
			obal.setRemovedReasonCode(obj.getReasonCode());
			obal.setAgyLocId(obj.getToAgyLocId());
			obal.setCreateUserId(obj.getCreateUserId());
			obal.setCreateDatetime(obj.getCreateDatetime());
			offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obal, operation);
		}
		return returnObj;

	}

	@Transactional
	public Integer postInsert(final OffenderBookingEvent obj, final String userName) {
		Integer returnVal = 0;
		final String profileVal = ocdcloseRepository.getvProfileValue();
		if (profileVal != null && !("C".equals(profileVal))) {
			final String vno = ocdcloseRepository.getvNo();
			//Additional Trigger OMTOFSB
			ocdcloseRepository.ocdcloseTriggerDelete();
			returnVal = ocdcloseRepository.updateOffenderComm(vno, obj.getOffenderId());
			List<OffenderCommunityFiles>  offenderCommunityFiles = ocdcloseRepository.offenderCommunityFiles(obj.getOffenderBookId());
			if (offenderCommunityFiles.size() > 0) {
				omtoffirService.omtoffirTgr(offenderCommunityFiles);
			}
			final List<OffenderCommunityFile> returnobj = ocdcloseRepository
					.caseplanAndPaperfileVsPaperCur(obj);
			for (OffenderCommunityFile data : returnobj) {
				OffenderFileTransactions objData = new OffenderFileTransactions();
				objData.setOffenderFileSeq(data.getOffenderFileSeq());
				objData.setOffenderId(data.getOffenderId());
				objData.setConfirmed("Y");
				pimsFileTrackingService.insertTrans(objData, userName);
			}
		}
		return returnVal;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkObeDspDescriptionRecordGroup() {
		return ocdcloseRepository.cgfkObeDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> agencyLocationRecordGroup(final Integer offenderBookId, final String caseloadId) {
		return ocdcloseRepository.agencyLocationRecordGroup(offenderBookId, caseloadId);

	}

	@Override
	public Integer checkInstitution(final OffenderBookingEvent object) {
		Integer returnObj = 0;
		String operation = "UPDATING";
		OffenderBookings old = new OffenderBookings();
		old = ocdcloseRepository.aluesOffenderBookings(object.getOffenderBookId());
		// pre_insert2
		// 1.CHECK_INSTITUTION
		OffenderBookings objnew = new OffenderBookings();
		objnew.setOffenderBookId(object.getOffenderBookId());
		objnew.setActivityDate(object.getEventDate());
		objnew.setAgyLocId(object.getToAgyLocId());
		objnew.setOffenderId(BigDecimal.valueOf(object.getOffenderId()));
		offenderBookingsT2Service.offenderBookingsT2(objnew, old);
		returnObj = ocdcloseRepository.checkInstUpdateOffenderBookings(object);
		offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(old,ApplicationConstants.UPDATING);
		offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(old, objnew, operation);
		offenderBookingsT7Service.offenderBookingsT7Trigger(old);
		//return ocdcloseRepository.checkInstUpdateOffenderBookings(object);
		return returnObj;
	}

	@Override
	public Integer checkMultyCaseload(final OffenderBookingEvent object) {
		Integer returnObj = 0;
		String sqlOperation = "UPDATING";
		returnObj = ocdcloseRepository.checkInstUpdateOffenderBookingAgyLoc(object);
		OffenderBookingAgyLocs obal = new OffenderBookingAgyLocs();
		obal.setOffenderBookId(object.getOffenderBookId());
		obal.setRemovedDate(object.getEventDate());
		obal.setRemovedReasonCode(object.getReasonCode());
		 offenderBookingAgyLocsT1Service.offenderBookingAgyLocsT1Triger(obal, sqlOperation);
		 return returnObj;
	}

	@Override
	public OffenderBookingEvent getBokingBeginDate(final VHeaderBlock object) {
		return ocdcloseRepository.getBokingBeginDate(object);
	}
	@Override
	public Integer tagTerminationChkTasks(final OffenderBookingEvent object) {
		final String tagTermValue = tagTerminationService.chkTasks(object.getOffenderBookId());
		if ("Y".equals(tagTermValue)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateSentStatus(OffenderBookingEvent obj) {
		Integer result = 0;
		OffenderBookings offenderBookings = new OffenderBookings();
		List<OffenderBookings> offenderBookingsList = new ArrayList<OffenderBookings>();
		offenderBookings.setCommStatus("");
		offenderBookingsList.add(offenderBookings);
		result = cmdactionRepository.updateSentStatus(offenderBookingsList);
		return result;
	}
	
	@Override
	public Boolean isActiveOrderPresent(Integer offenderBookId) {
		Boolean isDependent = false;
		isDependent = ocdcloseRepository.isActiveOrderPresent(offenderBookId);
		if(!isDependent) {
			isDependent = ocdcloseRepository.isActiveCourtReportPresent(offenderBookId);
		}
		return isDependent;
	}
}