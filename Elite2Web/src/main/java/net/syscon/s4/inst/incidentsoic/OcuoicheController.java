package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResultsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcuoicheController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicheController.class.getName());

	@Autowired
	private OcuoicheService ocuoicheService;
	
	@Autowired
	private CommonService commonService;

	/**
	 * method for query calling
	 * 
	 * @return List<OicHearings>
	 * @param OicHearings
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oicHearExecuteQuery", method = RequestMethod.POST)
	public List<OicHearings> oicHearSearchOicHearings(@RequestBody OicHearings searchBean) {
		List<OicHearings> searchResult = new ArrayList<>();
		final OicHearings bean = new OicHearings();
		try {
			searchResult = ocuoicheService.oicHearSearchOicHearings(searchBean);
		} catch (Exception e) {
			logger.error("ExecuteQuery:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param OicHearingsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oicHearCommit", method = RequestMethod.POST)
	public @ResponseBody List<OicHearings> oicHearCommit(@RequestBody final OicHearingsCommitBean commitBean) {
		List<OicHearings> liReturn = new ArrayList<>();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = ocuoicheService.oicHearCommit(commitBean);
		} catch (Exception e) {
			final OicHearings error = new OicHearings();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * method for query calling
	 * 
	 * @return ReferenceCodes
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgOffenceCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffenceCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgOffenceCodeRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            caseloadId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgAgyIncpStaffIdRecordGroup", method = RequestMethod.POST)
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(@RequestBody final StaffMembers staffMemberBean) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocuoicheService.rgAgyIncpStaffIdRecordGroup(staffMemberBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgHearingTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgHearingTypeRecordGroup();
		} catch (Exception e) {
			logger.error("", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            caseloadId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgInternalLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		final AgencyInternalLocations obj = new AgencyInternalLocations();
		try {
			recordList = ocuoicheService.rgInternalLocationsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            agencyIncidentId,String partySeq
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgIncidentChargesRecordGroup", method = RequestMethod.POST)
	public List<OicOffences> rgIncidentChargesRecordGroup(@RequestBody final VOicIncidents searchBean) {
		List<OicOffences> recordList = new ArrayList<OicOffences>();
		final OicOffences obj = new OicOffences();
		try {
			recordList = ocuoicheService.rgIncidentChargesRecordGroup(searchBean);
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgFindingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFindingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgFindingRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/rgPleaRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPleaRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgPleaRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OicHearingResults>
	 * @Param OicHearingResults searchBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oicHearResExecuteQuery", method = RequestMethod.POST)
	public List<OicHearingResults> oicHearResSearchOicHearingResults(@RequestBody final OicHearingResults searchBean) {
		List<OicHearingResults> searchResult = new ArrayList<>();
		final OicHearingResults bean = new OicHearingResults();
		try {
			searchResult = ocuoicheService.oicHearResSearchOicHearingResults(searchBean);
		} catch (Exception e) {
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param OicHearingResultsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oicHearResCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oicHearResCommit(@RequestBody final OicHearingResultsCommitBean commitBean) {
		int liReturn = 0;
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = ocuoicheService.oicHearResCommit(commitBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * method for query calling
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oichearprequery", method = RequestMethod.POST)
	public @ResponseBody CaseloadAgencyLocations oicHearPreQuery(@RequestBody final CaseloadAgencyLocations paramBean) {
		CaseloadAgencyLocations dataObj = null;
		try {
			dataObj = ocuoicheService.oicHearPreQuery(paramBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @Param String OicHearingId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/oichearoncheckdeletemasteroichearrescur", method = RequestMethod.POST)
	public @ResponseBody List<Object> oicHearOnCheckDeleteMasteroicHearResCur(
			@RequestParam(value = "OicHearingId") final String OicHearingId) {
		List<Object> dataObj = null;
		try {
			dataObj = ocuoicheService.oicHearOnCheckDeleteMasteroicHearResCur(OicHearingId);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}

	/**
	 * method for query calling
	 * 
	 * @Param String OicHearingId,String ResultSeq
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoiche/ocuoichekeydelrecoicsanccur", method = RequestMethod.POST)
	public @ResponseBody List<Object> ocuoicheKeyDelrecoicSancCur(
			@RequestParam(value = "OicHearingId") final String OicHearingId,
			@RequestParam(value = "ResultSeq") final String ResultSeq) {
		List<Object> dataObj = null;
		try {
			dataObj = ocuoicheService.ocuoicheKeyDelrecoicSancCur(OicHearingId, ResultSeq);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUOICHN");
	}

}