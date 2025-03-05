package net.syscon.s4.inst.offenderissuestracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OffenderGrievancesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.automatedcounts.OidverccService;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidissueController {
	@Autowired
	private OidissueService oidissueService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OidverccService oidverccService;
	@Autowired
	private OiustinvService oiustinvService;
	@Autowired
	private OiuincreService oiuincreService;
	@Autowired
	private OiuprresService oiuprresService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidissueController.class.getName());

	/**
	 * getting rgAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidissueService.rgAgyLocRecordGroup();
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgAgyLocAllRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocAllRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidissueService.rgAgyLocAllRecordGroup();
		} catch (Exception e) {
			logger.error("rgAgyLocAllRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgGrievType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgGrievTypeRecordGroup", method = RequestMethod.GET)
	public List<GrievanceTypes> rgGrievTypeRecordGroup(String user) {
		List<GrievanceTypes> recordList = new ArrayList<GrievanceTypes>();
		try {
			 user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			recordList = oidissueService.rgGrievTypeRecordGroup(user);
		} catch (Exception e) {
			logger.error("rgGrievTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgGrievReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgGrievReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgGrievReasonRecordGroup( String grievType , String user) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgGrievReasonRecordGroup(grievType ,user );
		} catch (Exception e) {
			logger.error("rgGrievReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<GrievanceTxns> rgTxnTypeRecordGroup(@RequestParam(value = "grievType") final String grievType) {
		List<GrievanceTxns> recordList = new ArrayList<GrievanceTxns>();
		try {
			recordList = oidissueService.rgTxnTypeRecordGroup(grievType);
		} catch (Exception e) {
			logger.error("rgTxnTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTxnTypeAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgTxnTypeAllRecordGroup", method = RequestMethod.GET)
	public List<GrievanceTxns> rgTxnTypeAllRecordGroup(@RequestParam(value = "grievType") final String grievType) {
		List<GrievanceTxns> recordList = new ArrayList<GrievanceTxns>();
		try {
			recordList = oidissueService.rgTxnTypeAllRecordGroup(grievType);
		} catch (Exception e) {
			logger.error("rgTxnTypeAllRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgStaffRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = oidissueService.rgStaffRecordGroup(userName);
		} catch (Exception e) {
			logger.error("rgStaffRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgStaffAllRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffAllRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidissueService.rgStaffAllRecordGroup();
		} catch (Exception e) {
			logger.error("rgStaffAllRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgFinding LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgFindingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFindingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgFindingRecordGroup();
		} catch (Exception e) {
			logger.error("rgFindingRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgFindingAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgFindingAllRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFindingAllRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgFindingAllRecordGroup();
		} catch (Exception e) {
			logger.error("rgFindingAllRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLevel LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLevelRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgLevelRecordGroup();
		} catch (Exception e) {
			logger.error("rgLevelRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgLevelAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgLevelAllRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLevelAllRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgLevelAllRecordGroup();
		} catch (Exception e) {
			logger.error("rgLevelAllRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidissueService.rgStatusRecordGroup();
		} catch (Exception e) {
			logger.error("rgStatusRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievancesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderGrievances> offenderGrievancesExecuteQuery(@RequestBody final OffenderGrievances searchBean) {
		List<OffenderGrievances> searchResult = new ArrayList<>();
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userId);
		try {
			searchResult = oidissueService.offenderGrievancesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offenderGrievancesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievancesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderGrievancesCommit(@RequestBody final OffenderGrievancesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidissueService.offenderGrievancesCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "50");
				if(commitBean.getOffenderGrievanceTxnsList().size() > 0) {
					OffenderGrievanceTxnsCommitBean txnsCommitBean = new OffenderGrievanceTxnsCommitBean();
					txnsCommitBean.setInsertList(commitBean.getOffenderGrievanceTxnsList());
					prosmainService.enableTriggers(txnsCommitBean, authorization, "22");
				}
			}
		} catch (Exception e) {
			logger.error("offenderGrievancesCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievanceTxnsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderGrievanceTxns> offenderGrievanceTxnsExecuteQuery(
			@RequestBody final OffenderGrievanceTxns searchBean) {
		List<OffenderGrievanceTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oidissueService.offenderGrievanceTxnsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offenderGrievanceTxnsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievanceTxnsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderGrievanceTxnsCommit(
			@RequestBody final OffenderGrievanceTxnsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidissueService.offenderGrievanceTxnsCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "22");
			}
		} catch (Exception e) {
			logger.error("offenderGrievanceTxnsCommit", e);
		}
		return liReturn;
	}

	/**
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = oidissueService.offbkgGlobalQuery(searchBean);
		} catch (Exception e) {
			logger.error("offbkgGlobalQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting rgTxnTypeAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievancesPostQueryReportDate", method = RequestMethod.GET)
	public String offenderGrievancesPostQueryReportDate(@RequestParam(value = "agyLocId") final String agyLocId) {
		String recordList = null;
		try {
			recordList = oidissueService.offenderGrievancesPostQueryReportDate(agyLocId);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTxnTypeAll LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/daysRespondData", method = RequestMethod.GET)
	public Long daysRespondData(@RequestParam(value = "grievType") final String grievType,
			@RequestParam(value = "txnType") final String txnType) {
		Long recordList = null;
		try {
			recordList = oidissueService.daysRespondData(grievType, txnType);
		} catch (Exception e) {
			logger.error("daysRespondData", e);
		}
		return recordList;
	}

	/**
	 * getting rgProposedEmployment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/maxGrievanceIdComparison", method = RequestMethod.GET)
	public List<OffenderGrievances> maxGrievanceIdComparison(@RequestParam("grievanceId") final Long grievanceId) {
		List<OffenderGrievances> recordList = new ArrayList<OffenderGrievances>();
		try {
			recordList = oidissueService.maxGrievanceIdComparison(grievanceId);
		} catch (Exception e) {
			logger.error("movementDateComparison", e);
		}
		return recordList;
	}
	
	/**
	 * getting rgProposedEmployment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/validationStaff", method = RequestMethod.GET)
	public Integer validationStaff(@RequestParam("grievanceId") final Long grievanceId) {
		Integer recordList = 0;
		try {
			recordList = oidissueService.validationStaff(grievanceId);
		} catch (Exception e) {
			logger.error("movementDateComparison", e);
		}
		return recordList;
	}

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/oidissueWhenNewFormInstance", method = RequestMethod.GET)
	public Integer oidissueWhenNewFormInstance() {
		Integer returnData=0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnData = oidissueService.oidissueWhenNewFormInstance(userName);
		} catch (Exception e) {
			logger.error("oidissueWhenNewFormInstance", e);
		}
		return returnData;
	}

	@RequestMapping(value = "/oidissue/getUserNameByCreatedUserId", method = RequestMethod.GET)
	public String getUserName( final String createUserId) {
		return oidverccService.getUserName(createUserId);
		
	}
	// copied from oiustinvController
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievStaffsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(
			@RequestBody final OffenderGrievStaffs searchBean) {
		List<OffenderGrievStaffs> searchResult = new ArrayList<>();
		try {
			searchResult = oiustinvService.offenderGrievStaffsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OffenderGrievStaffs bean = new OffenderGrievStaffs();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidissue/offenderGrievStaffsCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderGrievStaffs> offenderGrievStaffsCommit(
			@RequestBody final OffenderGrievStaffsCommitBean commitBean) {
		List<OffenderGrievStaffs> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oiustinvService.offenderGrievStaffsCommit(commitBean);
		} catch (final Exception e) {
			final OffenderGrievStaffs error = new OffenderGrievStaffs();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
		}
		return liReturn;
	}

	/**
	 * getting rgStaff LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/rgStaffRecordGroupone", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaffRecordGroupOne() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiustinvService.rgStaffRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/offenderGrievStaffsPostQuery", method = RequestMethod.POST)
	public List<StaffMembers> offenderGrievStaffsPostQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oiustinvService.offenderGrievStaffsPostQuery(searchBean);
		} catch (final Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	// copied from OiuincreController
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/agencyIncidentsExecuteQuery", method = RequestMethod.GET)
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(
			@RequestParam(value = "rootOffenderId") final Integer rootOffenderId) {
		List<AgencyIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oiuincreService.agencyIncidentsExecuteQuery(rootOffenderId);
		} catch (Exception e) {
			final AgencyIncidents bean = new AgencyIncidents();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	// copied from OiuprresController
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidissue/prresExecuteQuery", method = RequestMethod.POST)
	public List<OffenderGrievanceTxns> prresExecuteQuery(@RequestBody final OffenderGrievanceTxns searchBean) {
		List<OffenderGrievanceTxns> searchResult = new ArrayList<>();
		try {
			searchResult = oiuprresService.prresExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidissue/prresCommit", method = RequestMethod.POST)
	public @ResponseBody Integer prresCommit(@RequestBody final OffenderGrievanceTxnsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oiuprresService.prresCommit(commitBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}
}