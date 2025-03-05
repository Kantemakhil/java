package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.inst.casemanagement.beans.OffApV2CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditionsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeedsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.VSummaryCasePlans;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/** Represents an Ocdi Controller.
 */
@EliteController
public class OcdiplanController {
	@Autowired
	private OcdiplanService ocdiplanService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdiplanController.class.getName());

	/**
	 * getting rgCaseplanAss LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgCaseplanAssRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCaseplanAssRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdiplanService.rgCaseplanAssRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCaseInfo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgCaseInfoRecordGroup", method = RequestMethod.GET)
	public List<Dual> rgCaseInfoRecordGroup() {
		List<Dual> recordList = new ArrayList<Dual>();
		try {
			recordList = ocdiplanService.rgCaseInfoRecordGroup();
		} catch (Exception e) {
			final Dual obj = new Dual();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCasPlnDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/cgfkCasPlnDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCasPlnDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdiplanService.cgfkCasPlnDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCasPlnDspDescription4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/cgfkCasPlnDspDescription4RecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkCasPlnDspDescription4RecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdiplanService.cgfkCasPlnDspDescription4RecordGroup();
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCrimNeedsSts LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgCrimNeedsStsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCrimNeedsStsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdiplanService.rgCrimNeedsStsRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCasework LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgCaseworkRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCaseworkRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdiplanService.rgCaseworkRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPrgCategory LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgPrgCategoryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPrgCategoryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdiplanService.rgPrgCategoryRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProgramId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgProgramIdRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramIdRecordGroup(@RequestParam(value = "programCategory") final String programCategory) {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocdiplanService.rgProgramIdRecordGroup(programCategory);
		} catch (Exception e) {
			final ProgramServices obj = new ProgramServices();
			logger.error(e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProgramId2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/rgProgramId2RecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramId2RecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocdiplanService.rgProgramId2RecordGroup();
		} catch (Exception e) {
			final ProgramServices obj = new ProgramServices();
			logger.error(e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CasePlans}
	 * @return a list of the CasePlans {@link CasePlans} for the matched CasePlans
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/casPlnExecuteQuery", method = RequestMethod.POST)
	public List<CasePlans> casPlnExecuteQuery(@RequestBody final CasePlans searchBean) {
		List<CasePlans> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.casPlnExecuteQuery(searchBean);
		} catch (Exception e) {
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/casPlnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer casPlnCommit(@RequestBody final CasePlansCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdiplanService.casPlnCommit(commitBean);
		} catch (Exception e) {

			logger.error("casPlnCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderCriminogenicNeeds}
	 * @return a list of the OffenderCriminogenicNeeds {@link OffenderCriminogenicNeeds} for the matched agency location Id
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offCriNeedsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCriminogenicNeeds> offCriNeedsExecuteQuery(
			@RequestBody final OffenderCriminogenicNeeds searchBean) {
		List<OffenderCriminogenicNeeds> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.offCriNeedsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderCriminogenicNeeds bean = new OffenderCriminogenicNeeds();
			logger.error("offCriNeedsExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offCriNeedsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCriNeedsCommit(@RequestBody final OffenderCriminogenicNeedsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdiplanService.offCriNeedsCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "37");
			}
		} catch (Exception e) {
			logger.error("offCriNeedsCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link String}
	 * @return a list of the OffApV1 {@link OffApV1} for the matched OffApV1
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offActionPlansV1ExecuteQuery", method = RequestMethod.POST)
	public List<OffApV1> offActionPlansV1ExecuteQuery(@RequestBody final OffApV1 searchBean) {
		List<OffApV1> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.offActionPlansV1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffApV1 bean = new OffApV1();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offActionPlansV1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offActionPlansV1Commit(@RequestBody final OffApV1CommitBean commitBean,  @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdiplanService.offActionPlansV1Commit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "38");
			}
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderCaseConditions}
	 * @return a list of the OffenderCaseConditions {@link OffenderCaseConditions} for the matched OffenderCaseConditions
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offCaseCondsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCaseConditions> offCaseCondsExecuteQuery(@RequestBody final OffenderCaseConditions searchBean) {
		List<OffenderCaseConditions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.offCaseCondsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderCaseConditions bean = new OffenderCaseConditions();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offCaseCondsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offCaseCondsCommit(@RequestBody final OffenderCaseConditionsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdiplanService.offCaseCondsCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "39");
			}
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffApV2}
	 * @return a list of the OffApV2 {@link OffApV2} for the matched OffApV2
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offActionPlansV2ExecuteQuery", method = RequestMethod.POST)
	public List<OffApV2> offActionPlansV2ExecuteQuery(@RequestBody final OffApV2 searchBean) {
		List<OffApV2> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.offActionPlansV2ExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffApV2 bean = new OffApV2();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/offActionPlansV2Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offActionPlansV2Commit(@RequestBody final OffApV2CommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdiplanService.offActionPlansV2Commit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "40");
			}
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VSummaryCasePlans}
	 * @return a list of the VSummaryCasePlans {@link VSummaryCasePlans} for the matched VSummaryCasePlans
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/vSummaryCasePlanExecuteQuery", method = RequestMethod.POST)
	public List<VSummaryCasePlans> vSummaryCasePlanExecuteQuery(@RequestBody final VSummaryCasePlans searchBean) {
		List<VSummaryCasePlans> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.vSummaryCasePlanExecuteQuery(searchBean);
		} catch (Exception e) {
			final VSummaryCasePlans bean = new VSummaryCasePlans();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CasePlans}
	 * @return a list of the CasePlans {@link CasePlans} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/insertUpdateFlagQuery", method = RequestMethod.POST)
	public Integer insertUpdateFlagQuery(
			@RequestBody final CasePlans searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = ocdiplanService.insertUpdateFlagQuery(searchBean);
		} catch (Exception e) {
			logger.error("insertUpdateFlagQuery: ", e);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CasePlans}
	 * @return a list of the WorkFlowLogs {@link WorkFlowLogs} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/workFlowExecuteQuery", method = RequestMethod.POST)
	public List<WorkFlowLogs> workFlExecuteQuery(@RequestBody final CasePlans searchBean) {
		List<WorkFlowLogs> searchResult = new ArrayList<>();
		try {
			searchResult = ocdiplanService.workFlExecuteQuery(searchBean);
		} catch (Exception e) {
			final WorkFlowLogs bean = new WorkFlowLogs();
			logger.error("workFlowExecuteQuery: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdiplan/getStaffName", method = RequestMethod.GET)
	public String getStaffName(@RequestParam(value = "userId") final String userId) {
		String staffName = null;
		try {
			staffName = ocdiplanService.getStaffName(userId);
		} catch (Exception e) {
			logger.error(e);

		}
		return staffName;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdiplan/getUserIdOfAssignedStaff", method = RequestMethod.POST)
	public List<String> getUserIdOfAssignedStaff(@RequestBody CasePlans searchBean) {
		List<String> userIdList = new ArrayList<String>();
		try {
			userIdList = ocdiplanService.getUserIdOfAssignedStaff(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getUserIdOfAssignedStaff method call "+e);
		}
		return userIdList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdiplan/getUserIdOfAssignedStaffForCpOwn", method = RequestMethod.POST)
	public List<String> getUserIdOfAssignedStaffForCpOwn(@RequestBody CasePlans searchBean) {
		List<String> userIdList = new ArrayList<String>();
		try {
			userIdList = ocdiplanService.getUserIdOfAssignedStaffForCpOwn(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getUserIdOfAssignedStaffForCpOwn method call "+e);
		}
		return userIdList;
	}
}

