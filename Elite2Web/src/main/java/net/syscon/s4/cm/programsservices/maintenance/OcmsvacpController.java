package net.syscon.s4.cm.programsservices.maintenance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferingsCommitBean;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Class OcmsvacpController
 */
@EliteController
public class OcmsvacpController {
	@Autowired
	private OcmsvacpService ocmsvacpService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvacpController.class.getName());

	/**
	 * getting rgRefCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgRefCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmsvacpService.rgRefCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProvider LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgProviderRecordGroup", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroup(
			@RequestParam(value = "providerType") final String providerType,
			@RequestParam(value = "caseloadType") final String caseLoadType,
			@RequestParam(value = "caseloadId") final String caseLoadId) {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = ocmsvacpService.rgProviderRecordGroup(providerType, caseLoadType, caseLoadId, userName);
		} catch (Exception e) {
			VProgramProviders obj = new VProgramProviders();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTeamAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgTeamAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgTeamAgyLocsRecordGroup(@RequestParam String caseLoadId) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocmsvacpService.rgTeamAgyLocsRecordGroup(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(), caseLoadId);
		} catch (Exception e) {
			TeamMembers obj = new TeamMembers();
			logger.error("Exception", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgCorpLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgCorpLocsRecordGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgCorpLocsRecordGroup() {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocmsvacpService.rgCorpLocsRecordGroup();
		} catch (Exception e) {
			TeamMembers obj = new TeamMembers();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocmsvacpService.rgAgyLocsRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAccProgram LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgAccProgramRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgAccProgramRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocmsvacpService.rgAccProgramRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIntLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgIntLocationRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgIntLocationRecordGroup(
			@RequestParam(value = "providerPartyCode") final String providerPartyCode) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocmsvacpService.rgIntLocationRecordGroup(providerPartyCode);
		} catch (Exception e) {
			logger.error("Exception : Ocmsvacp:", e);
			
		}
		return recordList;
	}

	/**
	 * getting rgAddress LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgAddressRecordGroup", method = RequestMethod.GET)
	public List<VAddresses> rgAddressRecordGroup(@RequestParam(value = "providerPartyId") final Long providerPartyId) {
		List<VAddresses> recordList = new ArrayList<VAddresses>();
		try {
			recordList = ocmsvacpService.rgAddressRecordGroup(providerPartyId);
		} catch (Exception e) {
			VAddresses obj = new VAddresses();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgyAddress LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgAgyAddressRecordGroup", method = RequestMethod.GET)
	public List<VAddresses> rgAgyAddressRecordGroup(
			@RequestParam(value = "providerPartyCode") final String providerPartyCode) {
		List<VAddresses> recordList = new ArrayList<VAddresses>();
		try {
			recordList = ocmsvacpService.rgAgyAddressRecordGroup(providerPartyCode);
		} catch (Exception e) {
			VAddresses obj = new VAddresses();
			logger.error("Exception : Ocmsvacp:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAllAgyAddress LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/rgAllAgyAddressRecordGroup", method = RequestMethod.GET)
	public List<VAddresses> rgAllAgyAddressRecordGroup(
			@RequestParam(value = "providerPartyId") final Long providerPartyId) {
		List<VAddresses> recordList = new ArrayList<VAddresses>();
		try {
			recordList = ocmsvacpService.rgAllAgyAddressRecordGroup(providerPartyId);
		} catch (Exception e) {
			VAddresses obj = new VAddresses();
			logger.error("Exception", e);
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
	@RequestMapping(value = "/ocmsvacp/crsActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> crsActExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvacpService.crsActExecuteQuery(searchBean);
		} catch (Exception e) {
			CourseActivities bean = new CourseActivities();
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
	@RequestMapping(value = "/ocmsvacp/crsActCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crsActCommit(@RequestBody CourseActivitiesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmsvacpService.crsActCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Ocmsvacp", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/vCrsPhsExecuteQuery", method = RequestMethod.POST)
	public List<VCoursePhaseOfferings> vCrsPhsExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<VCoursePhaseOfferings> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvacpService.vCrsPhsExecuteQuery(searchBean);
		} catch (Exception e) {
			VCoursePhaseOfferings bean = new VCoursePhaseOfferings();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/addressExecuteQuery", method = RequestMethod.POST)
	public List<VCoursePhaseOfferings> addressExecuteQuery(@RequestBody VCoursePhaseOfferings searchBean) {
		List<VCoursePhaseOfferings> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvacpService.addressExecuteQuery(searchBean);
		} catch (Exception e) {
			VCoursePhaseOfferings bean = new VCoursePhaseOfferings();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsvacp/addressExecuteQueryDialog", method = RequestMethod.POST)
	public List<VAddresses> addressExecuteQueryDialog(@RequestBody CourseActivities searchBean) {
		List<VAddresses> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvacpService.addressExecuteQueryDialog(searchBean);
		} catch (Exception e) {
			VAddresses bean = new VAddresses();
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
	@RequestMapping(value = "/ocmsvacp/vCrsPhsCommit", method = RequestMethod.POST)
	public @ResponseBody VCoursePhaseOfferings vCrsPhsCommit(@RequestBody VCoursePhaseOfferingsCommitBean commitBean) {
		VCoursePhaseOfferings liReturn = new VCoursePhaseOfferings();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmsvacpService.vCrsPhsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Ocmsvacp", e);
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				final String tableName = ocmsvacpService.errorNameValidation(error.substring(1, error.length()));

				VCoursePhaseOfferings v=new VCoursePhaseOfferings();
				v.setSealFlag(tableName.toUpperCase());
				v.setListSeq(new BigDecimal(2292));
				return v;
				
		}
	}
		return liReturn;

	}
}