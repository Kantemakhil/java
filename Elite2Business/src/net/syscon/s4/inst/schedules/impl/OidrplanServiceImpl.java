package net.syscon.s4.inst.schedules.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderChecklistDetails;
import net.syscon.s4.im.beans.OffenderChecklistDetailsCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.beans.ReleasePlansCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.property.bean.Persons;
import net.syscon.s4.inst.schedules.OidrplanRepository;
import net.syscon.s4.inst.schedules.OidrplanService;

/**
 * Class OidrplanServiceImpl
 */
@Service
public class OidrplanServiceImpl extends BaseBusiness implements OidrplanService {

	@Autowired
	private OidrplanRepository oidrplanRepository;

	/**
	 * Creates new OidrplanServiceImpl class Object
	 */
	public OidrplanServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 */
	public List<ReleasePlans> offBkgOnCheckDeleteMaster(final ReleasePlans paramBean) {
		return oidrplanRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 */
	public VAddresses releasePlansPreInsert(final VAddresses paramBean) {
		return oidrplanRepository.releasePlansPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 */
	public Object releasePlansPreRecord(final SystemProfiles paramBean) {
		return oidrplanRepository.releasePlansPreRecord(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Object releasePlansWhenNewRecordInstance(final StaffMembers paramBean) {
		return oidrplanRepository.releasePlansWhenNewRecordInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<OffenderChecklistDetails> releasePlansKeyDelrec(final OffenderChecklistDetails paramBean) {
		return oidrplanRepository.releasePlansKeyDelrec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public ProfileTypes offChecklistDetPostQuery(final ProfileTypes paramBean) {
		return oidrplanRepository.offChecklistDetPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public ProfileTypes offChecklistDetWhenNewRecordInstance(final ProfileTypes paramBean) {
		return oidrplanRepository.offChecklistDetWhenNewRecordInstance(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<ReleasePlans> releasePlansExecuteQuery(final ReleasePlans searchRecord) {
		List<ReleasePlans> returnList = new ArrayList<>();
		returnList = oidrplanRepository.releasePlansExecuteQuery(searchRecord);
		for (final ReleasePlans obj : returnList) {
			StaffMembers stfmbrs = new StaffMembers();
			if (obj.getCaseManagerId() != null) {
				stfmbrs.setStaffId(Integer.valueOf(obj.getCaseManagerId().intValue()));
				stfmbrs = oidrplanRepository.getDescriptionOfStaffId(stfmbrs.getStaffId());
				obj.setCaseManagerId(Long.valueOf(stfmbrs.getStaffId()));
			}
			if (obj.getParoleAgentId() != null) {
				stfmbrs.setStaffId(Integer.valueOf(obj.getParoleAgentId().intValue()));
				stfmbrs = oidrplanRepository.getDescriptionOfStaffId(stfmbrs.getStaffId());
				obj.setParoleAgentId(Long.valueOf(stfmbrs.getStaffId()));
			}
			if (obj.getAssessmentSeq() == null && obj.getPlanStatus() !=null && "INPRG".equals(obj.getPlanStatus())) {
				List<ReleasePlans> offAssments = oidrplanRepository
						.releasePlansOffenderassesmentsPostQuery(obj.getOffenderBookId());
				for (ReleasePlans off : offAssments) {
					if (off.getAssessmentDate() != null) {
						obj.setAssessmentDate(off.getAssessmentDate());
					}
					if (off.getReviewSupLevelType() != null) {
						obj.setReviewSupLevelType(off.getReviewSupLevelType());
					}
				}
			} else if (obj.getAssessmentSeq() != null) {
				List<ReleasePlans> offAssmentSeq = oidrplanRepository
						.releasePlansAssesmentsPostQuery(obj.getOffenderBookId(), obj.getAssessmentSeq());
				for (ReleasePlans offRelease : offAssmentSeq) {
					if (offRelease.getAssessmentDate() != null) {
						obj.setAssessmentDate(offRelease.getAssessmentDate());
					}
					if (offRelease.getReviewSupLevelType() != null) {
						obj.setReviewSupLevelType(offRelease.getReviewSupLevelType());
					}
				}
			}
			if (obj.getPlanStatus() !=null && "INPRG".equals(obj.getPlanStatus())) {
				if (obj.getAddressId() != null) {
					String proposedHousingValues = oidrplanRepository.releasePlansPostQueryAddress(obj.getAddressId());
					obj.setProposedHousing(proposedHousingValues);
				} else {
					ReleasePlans proposedHousing = oidrplanRepository
							.releasePlansPostQueryHousing(obj.getRootOffenderId());
					if (proposedHousing != null) {
						obj.setProposedHousing(proposedHousing.getProposedHousing());
						obj.setAddressId(proposedHousing.getAddressId());
					}
				}
				String addressUsages = oidrplanRepository.releasePlansPreInsert(obj.getAddressId());
				obj.setAddressType((addressUsages == null) ? "" : addressUsages);
				if (obj.getEmploySeq() != null) {
					String proposedEmployments = oidrplanRepository
							.releasePlansPostQueryProposedEmployments(obj.getOffenderBookId(), obj.getEmploySeq());
					obj.setProposedEmployment((proposedEmployments == null) ? "" : proposedEmployments);
				}

			}
			if (obj.getOffenderBookId() != null && obj.getReleasePlanId() != null) {
				String conditionCheck = oidrplanRepository.releasePlansPostQueryCondition(obj);
				if (conditionCheck != null) {
					obj.setConditions(conditionCheck);
				}
			}

		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstRELEASE_PLANS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer releasePlansCommit(final ReleasePlansCommitBean commitBean) {
		int liReturn = 0;
		Integer releasePlanId = null;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<ReleasePlans> recordSavingList = new ArrayList<>();
			if (commitBean.getUpdateList().size() > 0) {
				for (final ReleasePlans offenderPropertyItemObj : commitBean.getUpdateList()) {
					offenderPropertyItemObj.setCaseManagerId(Long.valueOf(offenderPropertyItemObj.getCaseManagerId()));
					if (offenderPropertyItemObj.getParoleAgentId() != null) {
						offenderPropertyItemObj
								.setParoleAgentId(Long.valueOf(offenderPropertyItemObj.getParoleAgentId()));
					}
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					recordSavingList.add(offenderPropertyItemObj);
					liReturn = oidrplanRepository.releasePlansUpdateReleasePlans(recordSavingList);
				}
			}
		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<ReleasePlans> recordSavingList = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (final ReleasePlans offenderPropertyItemObj : commitBean.getInsertList()) {
					offenderPropertyItemObj.setCaseManagerId(Long.valueOf(offenderPropertyItemObj.getCaseManagerId()));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					if (offenderPropertyItemObj.getParoleAgentId() != null) {
						offenderPropertyItemObj
								.setParoleAgentId(Long.valueOf(offenderPropertyItemObj.getParoleAgentId()));
					}
					releasePlanId = oidrplanRepository.releasePlanPreInsertc();
					offenderPropertyItemObj.setReleasePlanId(Long.valueOf(releasePlanId));
					if (offenderPropertyItemObj.getRootOffenderId() != null) {
						List<VAddresses> vAddressList = new ArrayList<>();
						VAddresses vAddresses = new VAddresses();
						vAddresses.setAddressId(offenderPropertyItemObj.getRootOffenderId());
						vAddressList = oidrplanRepository
								.rgProposedHousingRecordGroup(vAddresses.getAddressId().toString());

						for (final VAddresses vAddressesBean : vAddressList) {
							if (vAddressesBean.getAddr() != null) {
								offenderPropertyItemObj.setAddressId(vAddressesBean.getAddressId());
								offenderPropertyItemObj.setProposedHousing(vAddressesBean.getAddr());
								String addressUsages = oidrplanRepository
										.releasePlansPreInsert(offenderPropertyItemObj.getAddressId());

								offenderPropertyItemObj.setAddressType((addressUsages == null) ? "" : addressUsages);
							}
						}
					}
					recordSavingList.add(offenderPropertyItemObj);
					for (ReleasePlans data : recordSavingList) {
						liReturn = oidrplanRepository.releasePlansInsertReleasePlans(data);
					}
					Integer CheckUpdate = oidrplanRepository.existsReleasePlansInprgress(offenderPropertyItemObj);
					if (CheckUpdate != null && CheckUpdate == 1) {
						liReturn = oidrplanRepository.updateReleasePlans(recordSavingList);
					}
					if (offenderPropertyItemObj.getReleasePlanId() != null) {
						oidrplanRepository.offenderCheckListInsertOffenderCheckList(offenderPropertyItemObj);
					}
				}
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			List<ReleasePlans> recordSavingList = new ArrayList<>();
			if (commitBean.getDeleteList().size() > 0) {
				for (final ReleasePlans offenderPropertyItemObj : commitBean.getDeleteList()) {
					if (offenderPropertyItemObj != null) {
						offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
						recordSavingList.add(offenderPropertyItemObj);
						liReturn = oidrplanRepository.releasePlansPreDelete(recordSavingList);
					}
					liReturn = oidrplanRepository.releasePlansDeleteReleasePlans(recordSavingList);
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderChecklistDetails> offChecklistDetExecuteQuery(final OffenderChecklistDetails searchRecord) {
		List<OffenderChecklistDetails> returnList = new ArrayList<>();
		returnList = oidrplanRepository.offChecklistDetExecuteQuery(searchRecord);
		for (final OffenderChecklistDetails obj : returnList) {
			ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType(obj.getProfileType());
			profileTypes = oidrplanRepository.offChecklistDetPostQuery(profileTypes);
			obj.setDescription(profileTypes.getDescription());
			obj.setCodevalueType(profileTypes.getCodeValueType());
			obj.setUpdateAllowedFlag(profileTypes.getUpdatedAllowedFlag());
			obj.setProfileCodeVal(obj.getProfileCode());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CHECKLIST_DET
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offChecklistDetCommit(final OffenderChecklistDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidrplanRepository.offChecklistDetUpdateOffenderChecklistDetails(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgCaseManagersRecordGroup(final String caseLoadId) {
		List<StaffMembers> returnList = new ArrayList<>();
		returnList = oidrplanRepository.rgCaseManagersRecordGroup(caseLoadId);
		for (final StaffMembers stfmbrs : returnList) {
			stfmbrs.setCode(stfmbrs.getStaffId());
			stfmbrs.setDescription(stfmbrs.getStaffName());
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgParoleAgentsRecordGroup(final String caseLoadId) {
		List<StaffMembers> returnList = new ArrayList<>();
		returnList = oidrplanRepository.rgParoleAgentsRecordGroup(caseLoadId);
		for (final StaffMembers stfmbrs : returnList) {
			stfmbrs.setCode(stfmbrs.getStaffId());
			stfmbrs.setDescription(stfmbrs.getStaffName());
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPlanStatusRecordGroup(final String userName) {
		return oidrplanRepository.rgPlanStatusRecordGroup(userName);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEmploymentStatusRecordGroup() {
		return oidrplanRepository.rgEmploymentStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VAddresses> rgProposedHousingRecordGroup(final String rootOffenderId) {
		List<VAddresses> returnList = new ArrayList<>();
		returnList = oidrplanRepository.rgProposedHousingRecordGroup(rootOffenderId);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderEmployments> rgProposedEmploymentRecordGroup(final Long offenderBookId) {
		return oidrplanRepository.rgProposedEmploymentRecordGroup(offenderBookId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProfileCodes> rgChecklistAnsRecordGroup(final String profileType) {
		List<ProfileCodes> returnList = new ArrayList<>();
		returnList = oidrplanRepository.rgChecklistAnsRecordGroup(profileType);
		for (final ProfileCodes obj : returnList) {
			if (obj.getProfileCode() != null) {
				obj.setCode(obj.getProfileCode());
			}
		}
		return returnList;
	}

	public ReferenceCodes rpReadyForApproval(final ReleasePlans releasePlan) {
		String chkConditionExistsCur = oidrplanRepository.rpReadyChkConditionExistsCur(releasePlan);
		List<Persons> chkPrimaryOccExistsCur = oidrplanRepository.rpReadyChkPrimaryOccExistsCur(releasePlan);
		String chkPrimOccContactedCur = oidrplanRepository.rpReadyChkPrimOccContactedCur(releasePlan);
		List<ProfileTypes> chkAllMandQuesCur = oidrplanRepository.rpReadyChkAllMandQuesCur(releasePlan);
		ReferenceCodes referenceCode = new ReferenceCodes();

		if (chkConditionExistsCur.equals("Y") 
				&& (chkPrimaryOccExistsCur != null && chkPrimaryOccExistsCur.size() > 0)) {
			referenceCode.setCode(null);
			referenceCode.setDescription(null);
		} else {
			String vConditionExists = "N";
			String vOtherOccExists = "N";
			String vPrimOccExists = "N";
			String vPrimOccContacted = "N";
			String vReadyForApproval = "Y";
			String vContactName = null;
			String vMessage = "This release plan cannot be approved because the following elements are not complete \n";

			vConditionExists = chkConditionExistsCur;
			if (vConditionExists.equals("N")) {
				vReadyForApproval = "N";
				vMessage = vMessage + "* Conditions - Release Conditions not populated. \n";
			}

			if (vOtherOccExists.equals("Y")) {
				for (Persons persons : chkPrimaryOccExistsCur) {
					vPrimOccExists = persons.getEmployer();
					vContactName = persons.getDspName();

				}

				if (vPrimOccExists != null && vPrimOccExists.equals("N")) {
					vReadyForApproval = "N";
					vMessage = vMessage + "* No Primary Other Occupant indicated. \n";
				} else {
					vPrimOccContacted = chkPrimOccContactedCur;
					if (vPrimOccContacted.equals("N")) {
						vReadyForApproval = "N";
						vMessage = vMessage + " * Other Occupant - " + vContactName + " not contacted. \n";
					}
				}
			}

			for (ProfileTypes profileType : chkAllMandQuesCur) {
				vReadyForApproval = "N";
				vMessage = vMessage + "* Checklist - " + profileType.getDescription() + "." + "\n";
			}

			if (releasePlan.getAddressId() == null) {
				vReadyForApproval = "N";
				vMessage = vMessage + "* No Housing specified. \n";
			}

			if (releasePlan.getEmploySeq() == null) {
				vReadyForApproval = "N";
				vMessage = vMessage + "* No Employment specified. \n";
			}
			if (releasePlan.getEmploymentStatus() == null) {
				vReadyForApproval = "N";
				vMessage = vMessage + "* Employment Status field not populated. \n";
			}

			if (vReadyForApproval.equals("N")) {
				vMessage = vMessage
						+ " \n Please ensure that all required release plan content is present and try again.";
			}
			referenceCode.setCode(vReadyForApproval);
			referenceCode.setDescription(vMessage);
		}
		return referenceCode;
	}

}