package net.syscon.s4.inst.classification;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;

@EliteController
public class OiiclassController {
	@Autowired
	private OiiclassService oiiclassService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiclassController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/sysProfExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysProfExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oiiclassService.sysProfExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("sysProfExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting cgfkSearchType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkSearchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSearchTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiiclassService.cgfkSearchTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("cgfkSearchTypeRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkAssessmentType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkAssessmentTypeRecordGroup", method = RequestMethod.GET)
	public List<Assessments> cgfkAssessmentTypeRecordGroup() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<Assessments> recordList = new ArrayList<Assessments>();
		try {
			recordList = oiiclassService.cgfkAssessmentTypeRecordGroup(userName);
		} catch (Exception e) {
			logger.error("cgfkAssessmentTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiiclassService.cgfkAgyLocIdRecordGroup(userName);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("cgfkAgyLocIdRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkHousingLevel1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiiclassService.cgfkHousingLevel1RecordGroup(agyLocId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("cgfkHousingLevel1RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkHousingLevel2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "livingUnitId") final String livingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiiclassService.cgfkHousingLevel2RecordGroup(agyLocId, livingUnitId);
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("cgfkHousingLevel2RecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/cgfkHousingLevel3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevel3RecordGroup() {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiiclassService.cgfkHousingLevel3RecordGroup();
		} catch (Exception e) {
			final LivingUnits obj = new LivingUnits();
			logger.error("cgfkHousingLevel3RecordGroup", e);
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
	@RequestMapping(value = "/oiiclass/livUnitExecuteQuery", method = RequestMethod.POST)
	public List<LivingUnits> livUnitExecuteQuery(@RequestBody final LivingUnits searchBean) {
		List<LivingUnits> searchResult = new ArrayList<>();
		try {
			searchResult = oiiclassService.livUnitExecuteQuery(searchBean);
		} catch (Exception e) {
			final LivingUnits bean = new LivingUnits();
			logger.error("livUnitExecuteQuery", e);
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
	@RequestMapping(value = "/oiiclass/livUnitCommit", method = RequestMethod.POST)
	public @ResponseBody Integer livUnitCommit(@RequestBody final LivingUnits commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiiclassService.livUnitCommit(commitBean);
		} catch (Exception e) {

			logger.error("livUnitCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiclass/oiiclassExecuteQuery", method = RequestMethod.POST)
	public List<OiiclassClassInquiry> oiiclassExecuteQuery(@RequestBody final OiiclassClassInquiry searchBean, @RequestHeader HttpHeaders headers) {
		List<OiiclassClassInquiry> searchResult = new ArrayList<>();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oiiclassService.oiiclassExecuteQuery(searchBean);
		} catch (Exception e) {
			final OiiclassClassInquiry bean = new OiiclassClassInquiry();
			logger.error("oiiclassExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}