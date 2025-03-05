package net.syscon.s4.pkgs.tag_service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousRepository;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;
import net.syscon.s4.pkgs.tag_service.TagServiceRepository;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/*
 * Below comments are copied from package TAG_SERVICE
 * -- MODIFICATION HISTORY
-- Person      Date        Version Comments
-- ---------   ------      ------- ------------------------------------------
-- Surya       08/07/2007  2.30    User Admin Security - Modified get_comm_default,
-- 			   			   		   get_working_caseload.
--                                 Removed version history from spec as it is not required.
-- Neil        15/12/2006  2.29    D#4193. Peer review changes.
-- Neil        28/11/2006  2.28    D#4193. Added check_unique_list_seq.
-- Johnson     07-Nov-2006 2.27    Added function is_course_phase_exists.
-- Johnson     03-Nov-2006 2.26    Added functions delete_course_activity_parties, delete_course_activity_prof
-- GJC         14-Oct-2006 2.25    SHOW_VERSION changed from procedure to functions
-- Neil        19/09/2006  2.24    Added obligation_summary_status for evaluation measures.
-- D Rice      21/08/2006  2.23    ACP. Iteration 2. Defect# 4038 - New Function: get_max_schedule_date
--				   Retrieves the max schedule date for the latest module selected in screen: OCUSMODU
-- Claus       03/08/2006  2.22    ACP. Iteration 2. Added get_event_id.
-- Neil        03/08/2006  2.21    ACP. Iteration 2. Added get_crs_details.
-- Claus       18/07/2006  2.20    D# 3408. Added get_next_prg_srv_list_seq and check_next_prg_srv_seq_unique.
-- Neil        11/07/2006  2.19    Added update of capcity in create_all_course_phases
-- Neil        30/06/2006  2.18    Added update_crs_acty_checksum
-- Neil        29/06/2006  2.17    Added internal_location_id to create_all_course_phases
-- Neil        29/06/2006  2.16    Added internal_location_id to create_all_course_phases
-- Neil        29/06/2006  2.15    Added get_team_agy_loc
-- Neil        27/06/2006  2.14    Added various procedures for accredited programmes
-- Erin        25/05/2006  2.12    Change hardcoded value 'UNPAID_WK' to 'UW'
-- GJC         28/03/2006  2.11    Area data model change
-- GJC         03/02/2006  2.10    Defect 444 rework
-- GJC         02/02/2006  2.9     Defect 444, change in AREA implementation
-- Krishna     31/01/2006  2.8     Added sort_earliest_weekday function
-- GJC         24/01/2006  2.7     Defect 292, added check_program_code
-- Neil        17/01/2006  2.6     Added check_unpaid_wk_exists
-- GJC         16/01/2006  2.5     Changed program_class from SERV to PRG
-- Neil        13/01/2006  2.4     Tidied get_offering details.
-- GJC         12/01/2006          Add function get_agency_description
-- Neil        14/06/2005          Initial Draft
-- Krishna     16/12/2005          Added get_program_id function
*/
@Service
public class TagServiceServiceImpl implements TagServiceService {

	@Autowired
	private TagServiceRepository tagServiceRepository;

	@Autowired
	private TagPrgService tagPrgservice;

	@Autowired
	private OmsMiscellaneousRepository omsMiscellaneousRepository;

	private static final String UW = "UW";
	private static final String TRUE = "TRUE";
	
	@Override
	public Integer checkProgramCode(final String programCode) {
		return tagServiceRepository.chkCode(programCode);
	}

	@Override
	public Integer checkProgramServiceUpdate(final Long programId) {
		return tagServiceRepository.cChk(programId);
	}
	
	@Override
	@Transactional
	public Long doUpdateOnCrsPhase(final Long pCrsActyId, final Long pTotal, final String userName) {
		return tagServiceRepository.updateCourseActivities(pCrsActyId, pTotal, userName);

	}
	@Override
	@Transactional
	public Integer doUpdateOnPhase(final BigDecimal pProgramPhaseId, final BigDecimal pTotal) {
		return tagServiceRepository.updateVProgramPhases(pProgramPhaseId, pTotal);
	}

	@Override
	@Transactional
	public Integer deleteCourseActivityAreas(final Long crsActyId,String modifyUserId) {
		return tagServiceRepository.deleteCourseActivityAreasDeleteOperation(crsActyId,modifyUserId);
	}

	@Override
	public Assessments getAssessmentDetails(final Long assessmentId) {
		return tagServiceRepository.getAssessmentDetails(assessmentId);

	}

	@Override
	public OffenderProgramProfiles getAllocationInfo(final Long crsActyId) {
		final CourseActivities courseActy = tagServiceRepository.getAllocationInfo(crsActyId);
		final OffenderProgramProfiles returnObj = new OffenderProgramProfiles();
		String providerName = null;
		if (courseActy != null) {
			providerName = getProviderNameBasedOnProPrtCls(courseActy.getProviderPartyClass(),
					courseActy.getProviderPartyId(), courseActy.getProviderPartyCode());
			returnObj.setProviderName(providerName);
			returnObj.setOccuranceCode(courseActy.getCode());
		}
		return returnObj;
	}

	String getProviderNameBasedOnProPrtCls(final String providerPartyClass, final Long providerPartyId,
			final String providerPartyCode) {
		return tagPrgservice.getProviderName(providerPartyClass, providerPartyId, providerPartyCode);
	}

	@Override
	public String getCourseActivityAreaDesc(final String areaCode) {
		Areas areaCoAndCls;
		String areaClass = null, areaTypeDesc = null;

		areaCoAndCls = tagServiceRepository.cArea(areaCode);

		if (areaCoAndCls != null) {
			areaClass = areaCoAndCls.getAreaClass();
		}
		if (areaClass != null) {
			areaTypeDesc = omsMiscellaneousRepository.getDescCode(areaClass);
		}

		if (areaTypeDesc == null) {
			areaTypeDesc = TRUE;
		}
		return areaTypeDesc;
	}

	@Override
	public ReferenceCodes getCommDefault(final String userId) {
		final ReferenceCodes objRef = new ReferenceCodes();
		final TeamMembers team = tagServiceRepository.getCommDefault(userId);
		if (team != null) {
			objRef.setCode(team.getDescription());
			objRef.setDescription(team.getAgyLocId());
		}
		return objRef;
	}
	@Override
	public Date getLastSchedDate(final Long pCrsActyId) {
		return tagServiceRepository.getLastSchedDate(pCrsActyId);
	}

	@Override
	public List<CourseActivities> getCaDates(final Long pCrsActyId) {
		return	tagServiceRepository.getCaDates(pCrsActyId);
		 
	}
	@Override
	public CourseActivities getCrsDetails(final Long crsActyId) {
		return tagServiceRepository.getCrsDetails(crsActyId);
	}

	@Override
	public List<CourseActivities> getCrsDetails(final Long crsActyId, final Date startDate, final Date endDate,
			final Long ProgramId) {
		return tagServiceRepository.getCrsDetailsTwo(crsActyId);
	}

	@Override
	public ProgramServices getPrgSrvDetails(final Long programId) {
		return tagServiceRepository.getPrgSrvDetails(programId);
	}
	@Override
	public Integer getNextPrgSrvListSeq(final Long pParentProgId) {
		return tagServiceRepository.getNextPrgSrvListSeq(pParentProgId);
	}

	@Override
	public Integer checkNextPrgSrvSeqUnique(final Integer pParentProgId, final BigDecimal pListSeq) {
		return tagServiceRepository.checkNextPrgSrvSeqUnique(pParentProgId, pListSeq);
	}
	@Override
	public Integer getProgramIdSeq() {
		return tagServiceRepository.getProgramIdSeq();
	}

	@Override
	public Long getCrsSessionCount(final Long pCrsActyId) {
		return tagServiceRepository.getCrsSessionCount(pCrsActyId);
	}
	@Override
	public Integer getCrsActyChecksum(final Long pCrsActyId) {
		return tagServiceRepository.getCrsActyChecksum(pCrsActyId);
	}
	@Override
	public Long getNextCsRuleSeq() {
		return tagServiceRepository.getNextCsRuleSeq();

	}
	@Override
	public Long PreInsertProgramService(final ProgramServices bean) {
		Long count = null;
		if (bean.getProgramCategory().equals(UW) && bean.getActiveFlag().equals("Y")) {
			count = tagServiceRepository.preInsertProgramService(bean);
			if (count!=null && count == 1) {
				return null;
			}
		}
		return (long) getProgramIdSeq();
	}
	@Override
	public Long getNextCsSeq() {
		return tagServiceRepository.getNextCsSeq();
	}

	@Override
	public Caseloads getWorkingCaseload(final String user) {
		return tagServiceRepository.getWorkingCaseload(user);
	}

	@Override
	@Transactional
	public Integer deleteCourseActivityParties(Long crsActyId,String modifyUserId) {
		return tagServiceRepository.deleteCourseActivityParties(crsActyId,modifyUserId);
	}

	@Override
	@Transactional
	public Integer deleteCourseActivityProf(Long crsActyId,String modifyUserId) {
		return tagServiceRepository.deleteCourseActivityProf(crsActyId,modifyUserId);
	}
	
	@Override
	public Areas cArea(String areaCode) {
		return tagServiceRepository.cArea(areaCode);
	}

}