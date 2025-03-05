package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransferCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssigned;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOffenderAssignedCommitBean;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.iwp.OcdexpowRepository;
import net.syscon.s4.iwp.OcdexpowService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;

/**
 * Class OcdexpowServiceImpl
 */
@Service
public class OcdexpowServiceImpl extends BaseBusiness implements OcdexpowService {

	@Autowired
	private OcdexpowRepository ocdexpowRepository;
	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	private static String YFLAG = "Y";
	public static final String CONSTANTVALUE = "SUPV";
	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;

	private static Logger logger = LogManager.getLogger(OcdexpowServiceImpl.class.getName());

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * @param params
	 * @throws Exception
	 */
	public List<AgencyLocations> cgfkchkExtOtExtOtAgyLoc(String agylocidfrom) {
		return ocdexpowRepository.cgfkchkExtOtExtOtAgyLoc(agylocidfrom);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 * @param params
	 * @throws Exception
	 */
	public List<SystemProfiles> GetProfileValue(SystemProfiles paramBean) {
		return null;

	}

	/**
	 * Fetch the records from database table
	 * @param searchRecord
	 * @throws SQLException
	 */
	public List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer searchRecord) {
		return ocdexpowRepository.extOtExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 * @return Integer
	 * @param commitBean
	 */
	@Transactional
	public Integer extOtCommit(ExtOwnershipTransferCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		Long extTrnferid = null;
		List<ExtOwnershipTransfer> recordSavingObject = new ArrayList<>();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ExtOwnershipTransfer offenderAlerts : commitBean.getInsertList()) {
				extTrnferid = offenderAlerts.getOffenderBookId();
				commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
				commitBean.getInsertList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			}
			if (commitBean.getUpdateList().size() > 0) {
				for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Long valueAlertSeq = ocdexpowRepository.getExtTransferId(extTrnferid);
					final ExtOwnershipTransfer offenderAlertObj = commitBean.getUpdateList().get(i);
					if (YFLAG.equals(offenderAlertObj.getTransferFlag())) {
						offenderAlertObj.setExtTransferId(valueAlertSeq);
						recordSavingObject.add(offenderAlertObj);
						liReturn = ocdexpowRepository.extOtInsertextOt(recordSavingObject);
					}
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 * @param searchRecord
	 * @return List<VOffenderAssigned>
	 */
	public List<VOffenderAssigned> vOffenderAssignedExecuteQuery(VOffenderAssigned searchRecord) {
		return ocdexpowRepository.vOffenderAssignedExecuteQuery(searchRecord);

	}

	/**
	 * Update the records from database table
	 *
	 * @param commitBean
	 * @return Integer
	 * @throws Exception 
	 */
	@Transactional
	public Integer vOffenderAssignedCommit(VOffenderAssignedCommitBean commitBean) throws Exception {
		int liReturn = 0;
		List<VOffenderAssigned> offListToUpdate = new ArrayList();
		Long extTrnferid = null;
		List<VOffenderAssigned> recordSavingObject = new ArrayList<>();
		try {
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			if (commitBean.getUpdateList().size() > 0) {
				for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					extTrnferid = commitBean.getUpdateList().get(i).getOffenderBookId();
					offListToUpdate = commitBean.getUpdateList();
					final Long valueAlertSeq = ocdexpowRepository.getExtTransferId(extTrnferid);
					final VOffenderAssigned offenderAlertObj = commitBean.getUpdateList().get(i);
					if (YFLAG.equals(offenderAlertObj.getTransferFlag())) {
						offenderAlertObj.setExtTransferId(valueAlertSeq);
						offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
						recordSavingObject.add(offenderAlertObj);
						liReturn = ocdexpowRepository.vOffenderAssignedUpdateVOffenderAssigned(recordSavingObject);
					}
				}
			}
		}
		if(!offListToUpdate.isEmpty()) {
			for (VOffenderAssigned obj : offListToUpdate) {
				if (obj.getTransferFlag().equals("Y")) {
					List<FeeAccountProfiles> offList = ocdexpowRepository.getFeeActsDet(obj);
					List<CaseloadDeductionProfiles> profileDataTemp = new ArrayList<CaseloadDeductionProfiles>();
					List<String> toAgyLoIdList = ocdexpowRepository.getCaseLoadType(obj);
					if(!toAgyLoIdList.isEmpty() && toAgyLoIdList.size() > 0) {
						String toAgyLoId = toAgyLoIdList.get(0);
						List<CaseloadDeductionProfiles> profileData = ocdsupstRepository.caseloadDedProfExecuteQuery(toAgyLoId);
						profileDataTemp.addAll(profileData);
						OffSupervisionStsHty bean = new OffSupervisionStsHty();
						bean.setCaseloadId(toAgyLoId);
						bean.setOffenderId(Long.valueOf(obj.getOffenderIdDisplay()));
						bean.setOffenderBookId(obj.getOffenderBookId().intValue());

						if (!offList.isEmpty()) {
							List<FeeAccountProfiles> feeUpdatelist = new ArrayList<>();
							for (FeeAccountProfiles fap : offList) {
								fap.setCreateUserId(commitBean.getCreateUserId());
								fap.setModifyUserId(commitBean.getCreateUserId());
								List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(fap.getFeeCode(),fap.getCaseloadId());
								boolean pushToUpdateList = false;
								if(!returnData.isEmpty() && "Y".equals(returnData.get(0).getNonBillableStatus())) {
									if (fap.getFeeActStatus().equals("A") || fap.getFeeActStatus().equals("P")){
										fap.setFeeActStatus("S");
										pushToUpdateList = true;
									} 
								} else {

									if (fap.getFeeActStatus().equals("A") || fap.getFeeActStatus().equals("P") || fap.getFeeActStatus().equals("S")){
										fap.setFeeActStatus("C");
										pushToUpdateList = true;
									}
								}
								if(pushToUpdateList) {
									feeUpdatelist.add(fap);
								}
							}

							if (!feeUpdatelist.isEmpty()) {
								ocdexpowRepository.updateFeeAcntCaseLoad(feeUpdatelist);
								// TRIGGER OFF_FEE_ACCOUNT_PROFILE_T2
								for (FeeAccountProfiles fup : feeUpdatelist) {
									offFeeAccountProfileT2Service.offFeeAccountProfileT2(fup);
								}
							}
						}


					}
				}
			}
		}
	} catch(Exception e) {
		logger.error("Exception in OcdexpowServiceImpl class vOffenderAssignedCommit : ", e);
	}



		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 * @return List<AgencyLocations>
	 * @param agylocidfrom
	 */
	public List<AgencyLocations> cgfkExtOtAgyLocIdToRecordGroup(final String agylocidfrom) {
		return ocdexpowRepository.cgfkExtOtAgyLocIdToRecordGroup(agylocidfrom);
	}

	/**
	 * This method is used to execute a record group
	 * @param agylocidfrom
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup(final String agylocidfrom) {
		return ocdexpowRepository.rgStaffMembersRecordGroup(agylocidfrom);
	}

	/**
	 * This method is used to execute a record group
	 * @return List<AgencyLocations>
	 * @param caseloadid
	 */
	public List<AgencyLocations> cgfkExtOtAgyLocIdFromRecordGroup(final String caseloadid) {
		return ocdexpowRepository.cgfkExtOtAgyLocIdFromRecordGroup(caseloadid);
	}


}