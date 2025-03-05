package net.syscon.s4.cm.programsservices.maintenance;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

@EliteController
public class OimworkrController {

	@Autowired
	private OimworkrService oimworkrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimworkrController.class.getName());

	/**
	 * getting rgProjectType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimworkr/rgProjectTypeRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgProjectTypeRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = oimworkrService.rgProjectTypeRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProvider LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimworkr/rgProviderRecordGroup", method = RequestMethod.GET)
	public List<Corporates> rgProviderRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = oimworkrService.rgProviderRecordGroup();
		} catch (Exception e) {
			Corporates obj = new Corporates();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgProjectLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimworkr/rgProjectLocationRecordGroup", method = RequestMethod.GET)
	public List<VAddresses> rgProjectLocationRecordGroup(@RequestParam("providerPartyId") String providerPartyId) {
		List<VAddresses> recordList = new ArrayList<VAddresses>();
		try {
			Integer providerId = null;
			if (providerPartyId != null) {
				providerId = Integer.parseInt(providerPartyId);
			}
			recordList = oimworkrService.rgProjectLocationRecordGroup(providerId);
		} catch (Exception e) {
			VAddresses obj = new VAddresses();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgencyLocation LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimworkr/rgAgencyLocationRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationRecordGroup(@RequestParam("caseLoadId") String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oimworkrService.rgAgencyLocationRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
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
	@RequestMapping(value = "/oimworkr/crsActyExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivities> crsActyExecuteQuery(@RequestBody CourseActivities searchBean) {
		List<CourseActivities> searchResult = new ArrayList<>();
		try {
			searchResult = oimworkrService.crsActyExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oimworkr/crsActyCommit", method = RequestMethod.POST)
	public @ResponseBody Integer crsActyCommit(@RequestBody CourseActivitiesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimworkrService.crsActyCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

}