package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetails;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetailsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetailsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncident;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncidentCommitBean;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidincdeController
 */
@EliteController
public class OidincdeController {
	@Autowired(required = true)
	private OidincdeService oidincdeService;
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OiurepinService oiurepinsevice;
	
	@Autowired
	private OsiosearService osiosearService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidincdeController.class);

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param AgencyIncidentParties
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agyIncPartiesOffenderExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentParties> agyIncPartiesOffenderExecuteQuery(
			@RequestBody final AgencyIncidentParties searchBean) {
		List<AgencyIncidentParties> searchResult = new ArrayList<>();
		try {
			searchResult = oidincdeService.agyIncPartiesOffenderExecuteQuery(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception raised in agyIncPartiesOffenderExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param AgencyIncidentParties
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agyIncPartiesstaffExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentParties> agyIncPartiesStaffExecuteQuery(
			@RequestBody final AgencyIncidentParties searchBean) {
		List<AgencyIncidentParties> searchResult = new ArrayList<>();
		try {
			searchResult = oidincdeService.agyIncPartiesstaffExecuteQuery(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception raised in agyIncPartiesOffenderExecuteQuery", e);
		}
		return searchResult;
	}

	
	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agyIncPartiesOffenderCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyIncPartiesOffenderCommit(
			@RequestBody final AgencyIncidentPartiesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		logger.info("agyIncPartiesOffenderCommit authorization : {}", authorization);
		try {
			liReturn = oidincdeService.agyIncPartiesOffenderCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "16");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agyIncstaffCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyIncstaffCommit(
			@RequestBody final AgencyIncidentPartiesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidincdeService.agyIncStaffCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "17");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderCommit", e);
		}
		return liReturn;
	}
	
	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgAgyLocIdsRecordGroup", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> rgAgyLocIdsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId, @RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgAgyLocIdsRecordGroup(caseloadId, caseLoadType);
		} catch (Exception e) {
			logger.error("Exception raised in rgAgyLocIdsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgAgyLocIdsRecord", method = RequestMethod.GET)
	public List<CaseloadAgencyLocations> rgAgyLocIdsRecord(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAgencyLocations> recordList = new ArrayList<>();
		String caseloadType=null;
		try {
			recordList = oidincdeService.rgAgyLocIdsRecordGroup(caseloadId,caseloadType);
		} catch (Exception e) {
			logger.error("Exception raised in rgAgyLocIdsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgIncidentTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIncidentTypesRecordGroup(@RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		final String domain = "INC_TYPE";
		try {
			recordList = oidincdeService.rgIncidentTypesRecordGroup(domain,caseLoadType);
		} catch (Exception e) {
			logger.error("Exception raised in rgIncidentTypesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyInternalLocations>
	 * @param String
	 *            agyLocId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgLevelInternalLocationIdsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgLevelInternalLocationIdsRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("Exception raised in rgLevelInternalLocationIdsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgOffInvActionCodesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffInvActionCodesRecordGroup(@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgOffInvActionCodesRecordGroup(domain);
		} catch (Exception e) {
			logger.error("Exception raised in rgOffInvActionCodesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgOffInvIncidentRolesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffInvIncidentRolesRecordGroup(@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgOffInvIncidentRolesRecordGroup(domain);
		} catch (Exception e) {
			logger.error("Exception Raised in rgOffInvIncidentRolesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            startDate, String endDate
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgOicOffenceCodesRecordGroup", method = RequestMethod.GET)
	public List<OicOffences> rgOicOffenceCodesRecordGroup(@RequestParam(value = "startDate") final String startDate,
			@RequestParam(value = "endDate") final String endDate) {
		List<OicOffences> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgOicOffenceCodesRecordGroup(startDate, endDate);
		} catch (Exception e) {
			logger.error("Exception raised in rgOicOffenceCodesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgRepairTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRepairTypesRecordGroup(@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgRepairTypesRecordGroup(domain);
		} catch (Exception e) {
			logger.error("Exception raised in rgRepairTypesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            caseloadId
	 */
	@RequestMapping(value = "/oidincde/rgReportedStaffIdsRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgReportedStaffIdsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgReportedStaffIdsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception raised in rgReportedStaffIdsRecordGroup", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgReportedStaffIdsRecord", method = RequestMethod.GET)
	public List<StaffMembers> rgReportedStaffIdsRecord(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgReportedStaffIdsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception raised in rgReportedStaffIdsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param String
	 *            domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgStaffInvIncidentRolesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffInvIncidentRolesRecordGroup(
			@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgStaffInvIncidentRolesRecordGroup(domain);
		} catch (Exception e) {
			logger.error("Exception raised in rgStaffInvIncidentRolesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentCharges>
	 * @param AgencyIncidentCharges
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentChargesExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentCharges> chargesExecuteQuery(@RequestBody final AgencyIncidentCharges searchBean) {

		List<AgencyIncidentCharges> searchResult = new ArrayList<>();
		try {
			searchResult = oidincdeService.agencyIncidentChargesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentChargesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentChargesCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentChargesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyIncidentChargesCommit(
			@RequestBody final AgencyIncidentChargesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidincdeService.agencyIncidentChargesCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentChargesCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agyIncPartiesStaffCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyIncPartiesStaffCommit(
			@RequestBody final AgencyIncidentPartiesCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		int liReturn = 0;
		try {
			liReturn = oidincdeService.agyIncPartiesStaffCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentRepairs>
	 * @param AgencyIncidentRepairs
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentRepairsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentRepairs> agencyIncidentRepairsExecuteQuery(
			@RequestBody final AgencyIncidentRepairs searchBean) {
		List<AgencyIncidentRepairs> searchResult = new ArrayList<>();
		try {
			searchResult = oidincdeService.agencyIncidentRepairsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param AgencyIncidentRepairsCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agencyIncidentRepairsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyIncidentRepairsCommit(
			@RequestBody final AgencyIncidentRepairsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidincdeService.agencyIncidentRepairsCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "18");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            agencyIncidentId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur", method = RequestMethod.GET)
	public @ResponseBody List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId) {
		List<Object> dataObj = null;
		try {
			dataObj = oidincdeService.agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(agencyIncidentId);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            agencyIncidentId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur", method = RequestMethod.GET)
	public @ResponseBody List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId) {
		List<Object> dataObj = null;
		try {
			dataObj = oidincdeService.agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(agencyIncidentId);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            agencyIncidentId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur", method = RequestMethod.GET)
	public @ResponseBody List<Object> agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId) {
		List<Object> dataObj = null;
		try {
			dataObj = oidincdeService.agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(agencyIncidentId);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @param String
	 *            agencyIncidentId, String partySeq
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur", method = RequestMethod.GET)
	public @ResponseBody List<Object> agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId,
			@RequestParam(value = "partySeq") final String partySeq) {
		List<Object> dataObj = null;
		try {
			dataObj = oidincdeService.agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(agencyIncidentId,
					partySeq);
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentParties>
	 * @param String
	 *            offenderBookId, String agencyIncidentId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agyIncPartiesOffenderPreInsert", method = RequestMethod.POST)
	public @ResponseBody List<AgencyIncidentParties> agyIncPartiesOffenderPreInsert(
			@RequestParam(value = "offenderBookId") final String offenderBookId,
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId) {
		List<AgencyIncidentParties> listOfRecords = null;
		try {
			listOfRecords = oidincdeService.agyIncPartiesOffenderPreInsert(offenderBookId, agencyIncidentId);
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesOffenderPreInsert", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OicOffences>
	 * @param String
	 *            const0
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentChargesPostQuery", method = RequestMethod.POST)
	public @ResponseBody List<OicOffences> agencyIncidentChargesPostQuery(
			@RequestParam(value = "const0") final String const0) {
		List<OicOffences> dataObj = new ArrayList<OicOffences>();
		try {
			dataObj = oidincdeService.agencyIncidentChargesPostQuery(const0);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentChargesPostQuery", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMembers>
	 * @param String
	 *            const0
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/setGlobalCaseloadIdworkingCaseloadCur", method = RequestMethod.GET)
	public @ResponseBody List<StaffMembers> setGlobalCaseloadIdworkingCaseloadCur(
			@RequestParam(value = "const0") final String const0) {
		List<StaffMembers> dataObj = new ArrayList<>();
		try {
			dataObj = oidincdeService.setGlobalCaseloadIdworkingCaseloadCur(const0);
		} catch (Exception e) {
			logger.error("Exception raised in setGlobalCaseloadIdworkingCaseloadCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return Object
	 * @param String
	 *            const0
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/oidincdeStateadmUsrCsr", method = RequestMethod.GET)
	public @ResponseBody Object oidincdeStateadmUsrCsr(@RequestParam(value = "const0") final String const0) {
		Object dataObj = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			dataObj = oidincdeService.oidincdeStateadmUsrCsr(const0, userName);
		} catch (Exception e) {
			logger.error("Exception raised in oidincdeStateadmUsrCsr", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<CaseloadAgencyLocations>
	 * @param String
	 *            caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/oidincdeState", method = RequestMethod.GET)
	public @ResponseBody List<CaseloadAgencyLocations> oidincdeState(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<CaseloadAgencyLocations> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidincdeService.oidincdeState(caseloadId);
		} catch (Exception e) {
			logger.error("Exception raised in oidincdeState", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param String
	 *            agencyIncidentId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/setUpdatedIncidentDetailsupdIncDetCur", method = RequestMethod.GET)
	public @ResponseBody List<AgencyIncidents> setUpdatedIncidentDetailsupdIncDetCur(
			@RequestParam(value = "agencyIncidentId") final String agencyIncidentId) {
		List<AgencyIncidents> dataObj = null;
		try {
			dataObj = oidincdeService.setUpdatedIncidentDetailsupdIncDetCur(agencyIncidentId);
		} catch (Exception e) {
			logger.error("Exception raised in setUpdatedIncidentDetailsupdIncDetCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<SystemProfiles>
	 * @param String
	 *            const0
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/checkUnlockAccessspProfileCur", method = RequestMethod.GET)
	public @ResponseBody List<SystemProfiles> checkUnlockAccessspProfileCur(
			@RequestParam(value = "const0") final String const0) {
		List<SystemProfiles> listOfRecords = null;
		try {
			listOfRecords = oidincdeService.checkUnlockAccessspProfileCur(const0);
		} catch (Exception e) {
			logger.error("Exception raised in checkUnlockAccessspProfileCur", e);
		}
		return listOfRecords;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidents agencyIncidentsBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agencyIncidentsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyIncidentsCommit(@RequestBody final AgencyIncidentsCommitBean agencyIncidentsBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		agencyIncidentsBean.setCreateUserId(userName);
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidincdeService.agencyIncidentsCommit(agencyIncidentsBean);
			if(liReturn != 0) {
				prosmainService.enableTriggers(agencyIncidentsBean, authorization, "15");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentsCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param AgencyIncidents
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencyIncidentsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(@RequestBody final AgencyIncidents searchBean) {
		List<AgencyIncidents> listOfRecords = new ArrayList<AgencyIncidents>();
		try {
			listOfRecords = oidincdeService.agencyIncidentsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentsExecuteQuery", e);
		}
		return listOfRecords;

	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param AgencyIncidentPartiesCommitBean commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/agencyIncidentPartiesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencyIncidentPartiesCommit(
			@RequestBody final AgencyIncidentPartiesCommitBean commitBean) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		int liReturn = 0;
		try {
			liReturn = oidincdeService.agencyIncidentPartiesCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentPartiesCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<StaffMemberRoles>
	 * @param String
	 *            pValue3
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/checkUnlockAccesscheckUnlockAccess", method = RequestMethod.POST)
	public @ResponseBody List<StaffMemberRoles> checkUnlockAccesscheckUnlockAccess(
			@RequestParam(value = "pValue3") final String pValue3) {
		List<StaffMemberRoles> dataObj = null;
		try {
			dataObj = oidincdeService.checkUnlockAccesscheckUnlockAccess(pValue3);
		} catch (Exception e) {
			logger.error("Exception raised in checkUnlockAccesscheckUnlockAccess", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OmsRoles>
	 * @param String
	 *            pValue, String pValue2
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/checkUnlockAccesscheckRoleAccess", method = RequestMethod.GET)
	public @ResponseBody List<OmsRoles> checkUnlockAccesscheckRoleAccess(
			@RequestParam(value = "pValue") final String pValue,
			@RequestParam(value = "pValue2") final String pValue2) {
		List<OmsRoles> dataObj = null;
		try {
			dataObj = new ArrayList<OmsRoles>();
			dataObj = oidincdeService.checkUnlockAccesscheckRoleAccess(pValue, pValue2);
		} catch (Exception e) {
			logger.error("Exception raised in checkUnlockAccesscheckRoleAccess", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidentCharges>
	 * @param AgencyIncidentCharges
	 *            searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/agencystaffModelExecuteQuery", method = RequestMethod.GET)
	public List<StaffMembers> staffidExecuteQuery(@RequestParam(value = "userId") final String userId) {

		List<StaffMembers> searchResult = new ArrayList<StaffMembers>();
		try {
			searchResult = oidincdeService.staffidExecuteQuery(userId);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentChargesExecuteQuery", e);
		}
		return searchResult;
	}
	
	@RequestMapping(value = "/oidincde/sigificantIncidentExecuteQuery", method = RequestMethod.POST)
	public List<SignificantIncident> sigificantIncidentExecuteQuery(
			@RequestBody final SignificantIncident commitBean) {
		List<SignificantIncident> result= new ArrayList<>();
		try {
			result = oidincdeService.sigificantIncidentExecuteQuery(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentPartiesCommit", e);
		}
		return result;
	}
	
	@RequestMapping(value = "oidincde/sigificantIncidentCommit", method = RequestMethod.POST)
	public Integer sigificantIncidentCommmit(@RequestBody final SignificantIncidentCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			liReturn = oidincdeService.sigificantIncidentCommmit(commitBean);
			if(liReturn != 0) {
				prosmainService.enableTriggers(commitBean, authorization, "19");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/getEnhancedStaffReporter", method = RequestMethod.GET)
	public Boolean getEnhancedStaffReporter(@RequestParam("staffId") final Integer staffId) {
		Boolean result=false;
		try {
			result=oidincdeService.getEnhancedStaffReporter(staffId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getEnhancedStaffReporter "+e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/incidentFollowUpcommit", method = RequestMethod.POST)
	public @ResponseBody List<IncidentFollowUpDetails> incidentFollowUpcommit(@RequestBody final IncidentFollowUpDetailsCommitBean commitBean) {
		List<IncidentFollowUpDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidincdeService.incidentFollowUpcommit(commitBean);
		} catch (Exception e) {
			final IncidentFollowUpDetails error = new IncidentFollowUpDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/getIncidentFollowUpDetails", method = RequestMethod.POST)
	public List<IncidentFollowUpDetails> getIncidentFollowUpDetails(@RequestBody final IncidentFollowUpDetails searchBean) {
		List<IncidentFollowUpDetails> recordList = new ArrayList<IncidentFollowUpDetails>();
		try {
			recordList = oidincdeService.getIncidentFollowUpDetails(searchBean);
		} catch (Exception e) {
			final IncidentFollowUpDetails error = new IncidentFollowUpDetails();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/checkPermisionForTabAccess", method = RequestMethod.GET)
	public Map<String, Boolean> checkPermisionForLov() {
	    Map<String, Boolean> permissionMap = new HashMap<>();
	    String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	    try {
	        permissionMap = oidincdeService.checkPermisionForTabAccess(userName);
	    } catch (Exception e) {
	        logger.error(this.getClass().getName() + " checkPermisionForTabAccess " + e);
	    }
	    return permissionMap;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/rgRoleStaffIdsRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgRoleStaffIdsRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId,@RequestParam(value = "agyLocId") final String agyLocId) {
		List<StaffMembers> recordList = new ArrayList<>();
		try {
			recordList = oidincdeService.rgRoleStaffIdsRecordGroup(caseloadId,agyLocId);
		} catch (Exception e) {
			logger.error("Exception raised in rgReportedStaffIdsRecordGroup", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/getReportDetailsExecuteQuery", method = RequestMethod.POST)
	public List<ReportableIncedentDetails> getReportDetailsExecuteQuery(@RequestBody final ReportableIncedentDetails searchBean) {
		List<ReportableIncedentDetails> searchResult = new ArrayList<ReportableIncedentDetails>();
		final ReportableIncedentDetails bean = new ReportableIncedentDetails();
		try {
			searchResult = oiurepinsevice.getReportDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("getReportDetailsExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidincde/reportableIncedentDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody List<ReportableIncedentDetails> reportableIncedentDetailsCommit(
			@RequestBody final ReportableIncedentDetailsCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		List<ReportableIncedentDetails> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oiurepinsevice.reportableIncedentDetailsCommit(commitBean);
			if(liReturn.size()>0) {
				prosmainService.enableTriggers(commitBean, authorization, "121");
			}
		} catch (Exception e) {
			final ReportableIncedentDetails error = new ReportableIncedentDetails();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);

		}
		return liReturn;
	}
	
	
	@RequestMapping(value = "/oidincde/getUserNameLog", method = RequestMethod.GET)
	public String getUserNameLog() {
		String userName=null;
		try {
			String userNameLog = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userName = oiurepinsevice.getUserNameLog(userNameLog);
		} catch (Exception e) {
			logger.error("getReportDetailsExecuteQuery",e);
		}
		return userName;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidincde/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			}
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
}
