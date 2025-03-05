package net.syscon.s4.globalconfiguration;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.CaseloadsCommitBean;

/**
 * class OumacaseController
 */
@EliteController
public class OumacaseController {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumacaseController.class.getName());

	@Autowired
	private OumacaseService oumacaseService;

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean Caseloads
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/executeQuery", method = RequestMethod.POST)
	public List<Caseloads> executeQuery(@RequestBody final Caseloads searchBean) {
		List<Caseloads> searchResult = new ArrayList<>();
		final Caseloads bean = new Caseloads();
		try {
			searchResult = oumacaseService.executeQuery(searchBean);
		} catch (Exception e) {
			logger.error("In executeQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean CaseloadsCommitBean
	 *
	 * @return Integer
	 *
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumacase/csldCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldCommit(@RequestBody final CaseloadsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumacaseService.csldCommit(commitBean);
		} catch (Exception e) {
			logger.error("In csldCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * getting payrollTrustRg LOV values
	 *
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/payrollTrustRgRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> payrollTrustRgRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		final Caseloads bean = new Caseloads();
		try {
			recordList = oumacaseService.payrollTrustRgRecordGroup();
		} catch (Exception e) {
			logger.error("In payrollTrustRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting commissaryTrustRg LOV values
	 *
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/commissaryTrustRgRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> commissaryTrustRgRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		final Caseloads bean = new Caseloads();
		try {
			recordList = oumacaseService.commissaryTrustRgRecordGroup();
		} catch (Exception e) {
			logger.error("In commissaryTrustRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting trustCommissaryRg LOV values
	 *
	 * @return List<Caseloads>
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/trustCommissaryRgRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> trustCommissaryRgRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		final Caseloads bean = new Caseloads();
		try {
			recordList = oumacaseService.trustCommissaryRgRecordGroup();
		} catch (Exception e) {
			logger.error("In trustCommissaryRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting communityTrustRg LOV values
	 *
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/communityTrustRgRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> communityTrustRgRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		final Caseloads bean = new Caseloads();
		try {
			recordList = oumacaseService.communityTrustRgRecordGroup();
		} catch (Exception e) {
			logger.error("In communityTrustRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting caseloadTypeRg LOV values
	 *
	 * @return List<Caseloads>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/typeRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> typeRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final Caseloads bean = new Caseloads();
		try {
			recordList = oumacaseService.typeRgRecordGroup();
		} catch (Exception e) {
			logger.error("In typeRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting alAgyLocIdRg LOV values
	 *
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/alAgyLocIdRgRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			recordList = oumacaseService.alAgyLocIdRgRecordGroup();
		} catch (Exception e) {
			logger.error("In alAgyLocIdRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean CaseloadAgencyLocations
	 * @return List<CaseloadAgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/alExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadAgencyLocations> alExecuteQuery(@RequestBody final CaseloadAgencyLocations searchBean) {
		List<CaseloadAgencyLocations> searchResult = new ArrayList<>();
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			searchResult = oumacaseService.alExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In alExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean CaseloadAgencyLocationsCommitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumacase/alCommit", method = RequestMethod.POST)
	public Integer alCommit(@RequestBody final CaseloadAgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumacaseService.alCommit(commitBean);
		} catch (Exception e) {
			logger.error("In alCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * This method executes when trigger event is called
	 * 
	 * @param paramBean
	 *            CaseloadAgencyLocations
	 *
	 * @return List<Object>
	 *
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumacase/checkAgency", method = RequestMethod.POST)
	public List<Object> checkAgency(@RequestBody final CaseloadAgencyLocations paramBean) {
		List<Object> lstCaseLoads = new ArrayList<>();
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			lstCaseLoads = oumacaseService.checkAgency(paramBean);
		} catch (Exception e) {

			logger.error("In checkAgency method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return lstCaseLoads;
	}

}