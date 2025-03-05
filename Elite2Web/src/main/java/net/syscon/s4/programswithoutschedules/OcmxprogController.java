package net.syscon.s4.programswithoutschedules;

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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.programswithoutschedules.OcmxprogService;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;

/**
 * Class OcmxprogController
 */
@EliteController
public class OcmxprogController {
	@Autowired
	private OcmxprogService ocmxprogService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmxprogController.class.getName());

	/**
	 * getting rgPsProvType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxprog/rgPsProvTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPsProvTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmxprogService.rgPsProvTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgPsProvTypeRecordGroup : Ocmxprog:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProvider LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxprog/rgProviderRecordGroup", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroup(@RequestParam("caseLoadId") final String caseLoadId,
			@RequestParam("caseLoadType") final String caseLoadType,
			@RequestParam("providerType") final String providerType) {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			recordList = ocmxprogService.rgProviderRecordGroup(caseLoadId, caseLoadType, providerType,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			final VProgramProviders obj = new VProgramProviders();
			logger.error("rgProviderRecordGroup : Ocmxprog:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxprog/rgProviderRecordGroupTeam", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroupTeam(@RequestParam("caseLoadId") final String caseLoadId,
			@RequestParam("caseLoadType") final String caseLoadType,
			@RequestParam("providerType") final String providerType) {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			recordList = ocmxprogService.rgProviderRecordGroupTeam(caseLoadId, caseLoadType, providerType,SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			final VProgramProviders obj = new VProgramProviders();
			logger.error("rgProviderRecordGroupTeam : Ocmxprog:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProgramType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxprog/rgProgramTypeRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProgramTypeRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocmxprogService.rgProgramTypeRecordGroup();
		} catch (Exception e) {
			final ProgramServices obj = new ProgramServices();
			logger.error("rgProgramTypeRecordGroup : Ocmxprog:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgIntLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmxprog/rgIntLocRecordGroup", method = RequestMethod.GET)
	public List<IntLocUsageLocations> rgIntLocRecordGroup(@RequestParam("agyLocId") final String agyLocId) {
		List<IntLocUsageLocations> recordList = new ArrayList<IntLocUsageLocations>();
		try {
			recordList = ocmxprogService.rgIntLocRecordGroup(agyLocId);
		} catch (Exception e) {
			final IntLocUsageLocations obj = new IntLocUsageLocations();
			logger.error("rgIntLocRecordGroup : Ocmxprog:", e);
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
	@RequestMapping(value = "/ocmxprog/crsActExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> crsActExecuteQuery(@RequestBody final CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = ocmxprogService.crsActExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseActivities bean = new CourseActivities();
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
	@RequestMapping(value = "/ocmxprog/crsActCommit", method = RequestMethod.POST)
	public @ResponseBody List<CourseActivities> crsActCommit(@RequestBody final CourseActivitiesCommitBean commitBean) {
		List<CourseActivities> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmxprogService.crsActCommit(commitBean);
		} catch (Exception e) {
			final CourseActivities bean = new CourseActivities();
			logger.error("crsActCommit : Ocmxprog", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
		}
		return liReturn;
	}

}