package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FindFacilityExecuteQueryBean;
import net.syscon.s4.common.beans.FindHousingExecuteQueryBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderAttributes;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.ProfileCodes;

@EliteController
public class OidarfplController {

	@Autowired
	private OidarfplService oidarfplservice;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidarfplController.class.getName());

	// @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidarfpl/getOffenderCaseDetails", method = RequestMethod.GET)
	public List<OffenderAttributes> offenderCaseDetails(
			@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		List<OffenderAttributes> caseResult = new ArrayList<>();
		try {
			caseResult = oidarfplservice.offenderCaseDetails(offenderBookId);
		} catch (Exception e) {
			logger.error("offenderCaseDetails", e);
		}
		return caseResult;
	}

	// @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidarfpl/getoffenderSentenceDetails", method = RequestMethod.GET)
	public List<OffenderAttributes> offenderSentenceDetails(
			@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		List<OffenderAttributes> sentResult = new ArrayList<>();
		try {
			sentResult = oidarfplservice.offenderSentenceDetails(offenderBookId);
		} catch (Exception e) {
			logger.error("offenderSentenceDetails", e);
		}
		return sentResult;
	}

	// @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidarfpl/getOffenderPersonalAtt", method = RequestMethod.GET)
	public List<ProfileCodes> getOffenderPersonalAtt(
			@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		List<ProfileCodes> caseResult = new ArrayList<>();
		try {
			caseResult = oidarfplservice.getOffenderPersonalAtt(offenderBookId);
		} catch (Exception e) {
			logger.error("offenderCaseDetails", e);
		}
		return caseResult;
	}

	@PostMapping("/oidarfpl/findFacilityExecuteQuery")
	public @ResponseBody List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(
			@RequestBody FindFacilityExecuteQueryBean findFacilityExecuteQueryBean) {
		List<OmuavbedLivUnitsQuery> searchResult = new ArrayList<>();
		try {
			searchResult = oidarfplservice.findFacilityExecuteQuery(findFacilityExecuteQueryBean);
		} catch (Exception e) {
			logger.error("findFacilityExecuteQuery", e);
		}
		return searchResult;
	}

	// @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidarfpl/getMovementReasonCode", method = RequestMethod.GET)
	public List<ProfileCodes> getMovementReasonCode() {
		List<ProfileCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oidarfplservice.getMovementReasonCode();
		} catch (Exception e) {
			logger.error("getMovementReasonCode", e);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oidarfpl/offenderAttributeExecuteQuery", method = RequestMethod.POST)
	public @ResponseBody List<OffenderAttributes> offenderAttributeExecuteQuery(
			@RequestBody VHeaderBlock vheaderBlock) {
		List<OffenderAttributes> searchResult = new ArrayList<>();
		try {
			searchResult = oidarfplservice.offenderAttributeExecuteQuery((vheaderBlock.getOffenderId()).longValue(),
					(vheaderBlock.getOffenderBookId()).longValue());
		} catch (Exception e) {
			logger.error("offenderAttributeExecuteQuery", e);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oidarfpl/offenderHousingExecuteQuery", method = RequestMethod.POST)
	public @ResponseBody List<OmuavbedLivUnitsQuery> offenderHousingExecuteQuery(
			@RequestBody FindHousingExecuteQueryBean findHousingExecuteQueryBean) {
		List<OmuavbedLivUnitsQuery> searchResult = new ArrayList<>();
		try {
			searchResult = oidarfplservice.offenderHousingExecuteQuery(findHousingExecuteQueryBean);
		} catch (Exception e) {
			logger.error("offenderHousingExecuteQuery", e);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oidarfpl/offenderPersonalDetails", method = RequestMethod.GET)
	public CourseActivities offenderPersonalDetails(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId,
			@RequestParam(value = "caseloadType") final String caseloadType) {
		CourseActivities returnData = new CourseActivities();
		try {
			returnData = oidarfplservice.offenderPersonalDetails(offenderBookId, caseloadType);
		} catch (Exception e) {
			logger.error("offenderSentenceDetails", e);
		}
		return returnData;
	}

}
