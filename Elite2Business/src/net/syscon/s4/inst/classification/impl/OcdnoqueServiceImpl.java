package net.syscon.s4.inst.classification.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.OcdnoqueRepository;
import net.syscon.s4.inst.classification.OcdnoqueService;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.StaffMembersV1;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.inst.classification.beans.VOffassAssCommitBean;
import net.syscon.s4.triggers.OffenderAssessmentsT1Service;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Service;
import net.syscon.s4.triggers.OffenderAssessmentsTwfService;

/**
 * Class OcdnoqueServiceImpl
 */
@Service
public class OcdnoqueServiceImpl extends BaseBusiness implements OcdnoqueService {

	private static Logger logger = LogManager.getLogger(OcdnoqueServiceImpl.class.getName());
	
	@Autowired
	private OcdnoqueRepository ocdnoqueRepository;

	@Autowired
	private OffenderAssessmentsTwf1Service offenderAssessmentsTwf1Service;

	@Autowired
	private OffenderAssessmentsTwfService offenderAssessmentsTwfService;

	@Autowired
	private OffenderAssessmentsT1Service offenderAssessmentsT1Service;

	/**
	 * Creates new OcdnoqueServiceImpl class Object
	 */
	public OcdnoqueServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VOffassAss> offBkgOnCheckDeleteMaster(final VOffassAss paramBean) {
		final List<VOffassAss> vOffassAssList = ocdnoqueRepository.offBkgOnCheckDeleteMaster(paramBean);
		return vOffassAssList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer offAssPostQuery(final OffenderAssessments paramBean) {
		return ocdnoqueRepository.offAssPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Assessments offAss1PostQuery(final OffenderAssessments paramBean) {
		final Assessments assessments = ocdnoqueRepository.offAss1PostQuery(paramBean);
		return assessments;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer offAss1PreInsert(final OffenderAssessments paramBean) {
		return ocdnoqueRepository.offAss1PreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Assessments cgfkchkAssessmentTypeId1(final Assessments paramBean) {
		final Assessments assessments = ocdnoqueRepository.cgfkchkAssessmentTypeId1(paramBean);
		return assessments;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations cgfkchkAgyLocId(final AgencyLocations paramBean) {
		final AgencyLocations agencyLocations = ocdnoqueRepository.cgfkchkAgyLocId(paramBean);
		return agencyLocations;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public StaffMembers cgfkchkNbtStaffId(final StaffMembers paramBean) {
		final StaffMembers staffMembers = ocdnoqueRepository.cgfkchkNbtStaffId(paramBean);
		return staffMembers;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VOffassAss updatePrevass(final VOffassAss paramBean) {
		VOffassAss returnObj = new VOffassAss();
		final List<VOffassAss> vOffassAss = ocdnoqueRepository.updatePrevass(paramBean);
		returnObj = vOffassAss.get(0);
		return returnObj;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<StaffMembersV1> getStaffId(final StaffMembersV1 paramBean) {
		final List<StaffMembersV1> staffMemV1List = ocdnoqueRepository.getStaffId(paramBean);
		return staffMemV1List;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<CaseloadAgencyLocations> setCasenoteAccess(final CaseloadAgencyLocations paramBean) {
		final List<CaseloadAgencyLocations> returnList = new ArrayList<>();
		final CaseloadAgencyLocations caseloadList = ocdnoqueRepository.setCasenoteAccess(paramBean);
		returnList.add(caseloadList);
		return returnList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles getCnoteAllupdProfile(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = ocdnoqueRepository.getCnoteAllupdProfile(paramBean);
		return systemProfiles;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Assessments postQueryForOffAss(final Assessments paramBean) {
		return paramBean;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public CaseloadAgencyLocations securityCheckNew(final CaseloadAgencyLocations paramBean) {
		final CaseloadAgencyLocations caseloadAgyLocs = ocdnoqueRepository.securityCheckNew(paramBean);
		return caseloadAgyLocs;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations postQueryForOffAss1(final AgencyLocations paramBean) {
		final AgencyLocations agencyLocations = ocdnoqueRepository.postQueryForOffAss1(paramBean);
		return agencyLocations;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffassAss> offAssExecuteQuery(final VOffassAss searchRecord) {
		final List<VOffassAss> returnList = ocdnoqueRepository.offAssExecuteQuery(searchRecord);
		for (final VOffassAss obj : returnList) {
			final Assessments returnObj = ocdnoqueRepository.postQueryForOffAss(obj);
			obj.setAssessmentTypeDesc(returnObj.getDescription());
			obj.setAssessmentTypeCode(returnObj.getCode());
		}
		List<VOffassAss> finalList = returnList.stream()
				.sorted(Comparator.comparing(VOffassAss::getAssessmentTypeCode).reversed())
				.collect(Collectors.toList());
		return finalList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ASS
	 *
	 * @
	 */
	@Transactional
	public Integer offAssCommit(final VOffassAssCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderAssessments> offAss1ExecuteQuery(final OffenderAssessments searchRecord) {
		List<OffenderAssessments> returnList = ocdnoqueRepository.offAss1ExecuteQuery(searchRecord);
		for (final OffenderAssessments obj : returnList) {
			final Assessments assReturnObj = ocdnoqueRepository.offAss1PostQuery(obj);
			obj.setRequireApprovalFlag(assReturnObj.getRequireApprovalFlag());
			obj.setAssessmentTypeCode(assReturnObj.getAssessmentCode());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ASS1
	 *
	 * @
	 */
	@Transactional
	public Integer offAss1Commit(final OffenderAssessmentsCommitBean commitBean) {
		int liReturn = 0;
		final List<OffenderAssessments> commitList = new ArrayList<>();
		List<OffenderAssessments> updateList = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderAssessments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				Integer seq = ocdnoqueRepository.offAss1PreInsert(obj);
				obj.setAssessmentSeq(Long.valueOf(seq));
				final StaffMembers staffMemObj = ocdnoqueRepository
						.offAss1PreInsertGetStaffId(commitBean.getCreateUserId());
				obj.setAssessStaffId(BigDecimal.valueOf(staffMemObj.getStaffId()));
				final Assessments assReturnObj = ocdnoqueRepository.offAss1PostQuery(obj);
				if (ApplicationConstants.YFLAG.equals(assReturnObj.getDetermineSupLevelFlag())
						&& ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag())) {
					obj.setAssessStatus("A");
					obj.setEvaluationResultCode("APP");
					obj.setEvaluationDate(obj.getAssessmentDate());
					obj.setReviewPlaceAgyLocId(obj.getPlaceAgyLocId());
					obj.setReviewSupLevelType(obj.getCalcSupLevelType());
					obj.setModifyUserId(commitBean.getCreateUserId());
					updateList = new ArrayList<>();
					updateList.add(obj);
					for (OffenderAssessments Offass : updateList) {
						offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(Offass);
					}
					ocdnoqueRepository.offAss1UpdateOffenderAssessments(updateList);
					for (OffenderAssessments Offass : updateList) {
						OffenderAssessment newRef = new OffenderAssessment();
						try {
							BeanUtils.copyProperties(newRef, Offass);
						} catch (Exception e) {
							e.printStackTrace();
						}
						offenderAssessmentsTwf1Service.OffenderAssessmentsTwf1(newRef);
					}
				} else {
					obj.setReviewSupLevelType(null);
					obj.setAssessStatus("P");
				}
				commitList.add(obj);
				for (OffenderAssessments Offass : commitList) {
					offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(Offass);
				}
				liReturn = ocdnoqueRepository.offAss1InsertOffenderAssessments(commitList);
				for (OffenderAssessments Offass : commitList) {
					offenderAssessmentsTwfService.offenderAssessmentsTwf(Offass);
				}

			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderAssessments obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				final Assessments assReturnObj = ocdnoqueRepository.offAss1PostQuery(obj);
				if (ApplicationConstants.YFLAG.equals(assReturnObj.getDetermineSupLevelFlag())
						&& ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag())) {
					if (obj.getOverridedSupLevelType() != null) {
						obj.setReviewSupLevelType(obj.getOverridedSupLevelType());
					} else {
						obj.setReviewSupLevelType(obj.getCalcSupLevelType());
					}
				} else {
					obj.setReviewSupLevelType(null);
				}
				commitList.add(obj);
				for (OffenderAssessments Offass : commitList) {
					offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(Offass);
				}
				liReturn = ocdnoqueRepository.offAss1UpdateOffenderAssessments(commitList);
				for (OffenderAssessments Offass : commitList) {
					OffenderAssessment newRef = new OffenderAssessment();
					try {
						BeanUtils.copyProperties(newRef, Offass);
						newRef.setAssessmentTypeCode(Offass.getAssessmentTypeCode());
						newRef.setCalcSupLevelTypeDesc(Offass.getCalcSupLevelTypeDesc());
						offenderAssessmentsTwf1Service.OffenderAssessmentsTwf1(newRef);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Assessments> rgAssessmentTypeIdRecordGroup(final BigDecimal programid, final String userId) {
		final List<Assessments> returnList = ocdnoqueRepository.rgAssessmentTypeIdRecordGroup(programid, userId);
		for (final Assessments obj : returnList) {
			if (ApplicationConstants.YFLAG.equals(obj.getSealFlag())) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgAssessCommitteCodeRecordGroup(final String userId) {
		final List<ReferenceCodes> returnList = ocdnoqueRepository.rgAssessCommitteCodeRecordGroup(userId);
		for (final ReferenceCodes obj : returnList) {
			if (obj.getSeqValue() == 1) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String userId) {
		final List<AgencyLocations> returnList = ocdnoqueRepository.rgAgencyLocationsRecordGroup(userId);
		for (final AgencyLocations obj : returnList) {
			if (obj.getListSeq() == 1) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		final List<StaffMembers> returnList = ocdnoqueRepository.rgStaffMembersRecordGroup();
		for (final StaffMembers obj : returnList) {
			obj.setCode(obj.getStaffId());
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AssessmentResults> rgOverridedSupLevelTypeRecordGroup(final Integer assessmentId) {
		return ocdnoqueRepository.rgOverridedSupLevelTypeRecordGroup(assessmentId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgPlaceAgyLocIdRecordGroup(final String userId) {
		return ocdnoqueRepository.rgPlaceAgyLocIdRecordGroup(userId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgOverrideReasonRecordGroup() {
		return ocdnoqueRepository.rgOverrideReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public String getMaxAssessmentDateCur(final OffenderAssessments searchBean) {
		return ocdnoqueRepository.getMaxAssessmentDateCur(searchBean);

	}

	public List<Assessments> rgAssessmentTypeIdRecordGroupWithoutProgramid(final String userId) {
		String caseLoadType = ocdnoqueRepository.getWorkingCaseLoadType(userId);
		final List<Assessments> returnList = ocdnoqueRepository.rgAssessmentTypeIdRecordGroupWithoutProgramid(userId);
		for (final Assessments obj : returnList) {
			if (ApplicationConstants.YFLAG.equals(obj.getActiveFlag())
					&& (caseLoadType.equalsIgnoreCase(obj.getCaseloadType())
							|| "BOTH".equalsIgnoreCase(obj.getCaseloadType()))) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		
		}
		return returnList;
	}

	@Override
	public List<AssessmentSupervisions> scoreRange() {
		return ocdnoqueRepository.scoreRange();
	}
	
	@Override
	public List<ReferenceCodes> assessmentDetailsAuthority(String parentCode) {
		List<ReferenceCodes> returnlist = new ArrayList<ReferenceCodes>();
		try {
			returnlist = ocdnoqueRepository.assessmentDetailsAuthority(parentCode);
			returnlist.forEach(ele -> {
				if (ele.getActiveFlag()!=null && ele.getActiveFlag().equals(ApplicationConstants.YES)) {
					ele.setCanDisplay(true);
				} else {
					ele.setCanDisplay(false);
				}
			});
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " assessmentDetailsAuthority: "+e.getMessage());
		}
		return returnlist;
	}

	@Override
	public List<Assessments> rgAssessmentTypeEVALRecordGroup() {
		final List<Assessments> returnList = ocdnoqueRepository.rgAssessmentTypeEVALRecordGroup();
		for (final Assessments obj : returnList) {
			if (ApplicationConstants.YFLAG.equals(obj.getSealFlag()) && obj.getScreenCode().equals("EVAL") || obj.getScreenCode().equals("BOTH")) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;
	}
}
