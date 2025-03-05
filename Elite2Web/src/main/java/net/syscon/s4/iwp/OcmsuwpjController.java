package net.syscon.s4.iwp;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;

/**
 * @version 1.0
 */
@EliteController
public class OcmsuwpjController {
	@Autowired
	private OcmsuwpjService ocmsuwpjService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsuwpjController.class.getName());

	/**
	 * getting rgTeam LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgTeamRecordGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecordGroup() {
		List<Teams> recordList = new ArrayList<Teams>();
		try {
			recordList = ocmsuwpjService.rgTeamRecordGroup(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			Teams obj = new Teams();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgBeneficiaryType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgBeneficiaryTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBeneficiaryTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmsuwpjService.rgBeneficiaryTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPlacementName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgPlacementNameRecordGroup", method = RequestMethod.GET)
	public List<Corporates> rgPlacementNameRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = ocmsuwpjService.rgPlacementNameRecordGroup();
		} catch (Exception e) {
			Corporates obj = new Corporates();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPlacementAddresses LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgPlacementAddressesRecordGroup", method = RequestMethod.GET)
	public List<VCorporateAddresses> rgPlacementAddressesRecordGroup(
			@RequestParam("placementCorporateId") BigDecimal placementCorporateId) {
		List<VCorporateAddresses> recordList = new ArrayList<VCorporateAddresses>();
		try {
			recordList = ocmsuwpjService.rgPlacementAddressesRecordGroup(placementCorporateId);
		} catch (Exception e) {
			VCorporateAddresses obj = new VCorporateAddresses();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocmsuwpj/courseActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> courseActExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsuwpjService.courseActExecuteQuery(searchBean);
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
	@RequestMapping(value = "/ocmsuwpj/courseActCommit", method = RequestMethod.POST)
	public @ResponseBody CourseActivities courseActCommit(@RequestBody CourseActivitiesCommitBean commitBean) {
		CourseActivities liReturn = new CourseActivities();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmsuwpjService.courseActCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/placementExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> placementExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsuwpjService.placementExecuteQuery(searchBean);
		} catch (Exception e) {
			CourseActivities bean = new CourseActivities();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgProviderRecordGroup", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroup(@RequestParam("caseLoadId") final String caseLoadId,
			@RequestParam("caseLoadType") final String caseLoadType,
			@RequestParam("providerType") final String providerType) {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			recordList = ocmsuwpjService.rgProviderRecordGroup(caseLoadId, caseLoadType, providerType,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			final VProgramProviders obj = new VProgramProviders();
			logger.error("rgProviderRecordGroup : ocmsuwpj:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgIntLocRecordGroup", method = RequestMethod.GET)
	public List<IntLocUsageLocations> rgIntLocRecordGroup(@RequestParam("agyLocId") final String agyLocId) {
		List<IntLocUsageLocations> recordList = new ArrayList<IntLocUsageLocations>();
		try {
			recordList = ocmsuwpjService.rgIntLocRecordGroup(agyLocId);
		} catch (Exception e) {
			final IntLocUsageLocations obj = new IntLocUsageLocations();
			logger.error("rgIntLocRecordGroup : ocmsuwpj:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgProgramTypeRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramTypeRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocmsuwpjService.rgProgramTypeRecordGroup();
		} catch (Exception e) {
			final ProgramServices obj = new ProgramServices();
			logger.error("rgProgramTypeRecordGroup : ocmsuwpj:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList; 
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/rgProviderRecordGroupTeam", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroupTeam(@RequestParam("caseLoadId") final String caseLoadId,
			@RequestParam("caseLoadType") final String caseLoadType,
			@RequestParam("providerType") final String providerType) {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			recordList = ocmsuwpjService.rgProviderRecordGroupTeam(caseLoadId, caseLoadType, providerType,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			final VProgramProviders obj = new VProgramProviders();
			logger.error("rgProviderRecordGroupTeam : ocmsuwpj:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmsuwpj/getPlacementLocations", method = RequestMethod.GET)
	public List<Corporates> getPlacementLocationByCaseload(@RequestParam("caseLoadId") final String caseLoadId) {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = ocmsuwpjService.getPlacementLocationByCaseload(caseLoadId);
		} catch (Exception e) {
			logger.error("getPlacementLocationByCaseload : ocmsuwpj:", e);

		}
		return recordList;
	}

}