package net.syscon.s4.inst.incidentsoic;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcuoicchController
 */
@EliteController
public class OcuoicchController {
	@Autowired
	private OcuoicchService ocuoicchService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicchController.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * 
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicch/agyInciChgExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentCharges> agyInciChgSearchAgencyIncidentCharges(
			@RequestBody final AgencyIncidentCharges searchBean) {
		List<AgencyIncidentCharges> searchResult = new ArrayList<>();
		final AgencyIncidentCharges bean = new AgencyIncidentCharges();
		try {
			searchResult = ocuoicchService.agyInciChgExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In this method agyInciChgSearchAgencyIncidentCharges"+e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * 
	 * @return liReturn
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicch/agyInciChgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agyInciChgCommit(@RequestBody final AgencyIncidentChargesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuoicchService.agyInciChgCommit(commitBean);
			if(liReturn != 0) {
				prosmainService.enableTriggers(commitBean, authorization, "106");
			}
		} catch (Exception e) {
			logger.error("In this method agyInciChgCommit"+e);
		}
		return liReturn;
	}

	/**
	 * getting rgOffenceCode LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicch/rgOffenceCodeRecordGroup", method = RequestMethod.POST)
	public List<OicOffences> rgOffenceCodeRecordGroup(@RequestBody final VOicIncidents bean) {
		List<OicOffences> recordList = new ArrayList<>();
		final OicOffences obj = new OicOffences();
		
		try {
			recordList = ocuoicchService.rgOffenceCodeRecordGroup(bean.getIncidentDate() );
		} catch (Exception e) {
			logger.error("In this method rgOffenceCodeRecordGroup"+e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @param paramBean
	 * 
	 * @return dataObj
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicch/agyInciChgPostQuery", method = RequestMethod.POST)
	public @ResponseBody OicOffences agyInciChgPostQuery(@RequestBody final OicOffences paramBean) {
		OicOffences dataObj = new OicOffences();
		try {
			dataObj = ocuoicchService.agyInciChgPostQuery(paramBean);
		} catch (Exception e) {
			logger.error("In this method agyInciChgPostQuery"+e);
		}
		return dataObj;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * 
	 * @return searchResult
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocuoicch/oichearingSearchResults", method = RequestMethod.POST)
	public List<OicHearingResults> oichearingSearchResults(
			@RequestBody final OicHearingResults searchBean) {
		List<OicHearingResults> searchResult = new ArrayList<>();
		try {
			searchResult = ocuoicchService.oichearingSearchResults(searchBean);
		} catch (Exception e) {
			logger.error("In this method oichearingSearchResults"+e);
		}
		return searchResult;
	}
}