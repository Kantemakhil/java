package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

import net.syscon.s4.sa.recordmaintenance.ProsmainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;

/**
 * Class OcdalertController
 */
@EliteController
public class OcdalertController {

	@Autowired
	private OcdalertService ocdalertsevice;

	@Autowired
	private ProsmainService prosmainService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdalertController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderAlerts>
	 * @Param offenderAlerts
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAlerts> alertExecuteQuery(@RequestBody final OffenderAlerts offenderAlerts) {
		List<OffenderAlerts> searchResult = new ArrayList<>();
		try {
			searchResult = ocdalertsevice.searchOffenderAlerts(offenderAlerts);
		} catch (Exception e) {
			logger.error("alertExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdalert/alertCommit", method = RequestMethod.POST)
	public @ResponseBody String alertCommit(@RequestBody final OffenderAlertsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		String liReturn = "0";
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdalertsevice.insertUpdateDeleteOffenderAlerts(commitBean);
			if("1".equals(liReturn)) {
				prosmainService.enableTriggers(commitBean, authorization, "11");
			}
		} catch (Exception e) {
			logger.error("alertCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<OffenderAlerts>
	 * @param offenderBookId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertSearchOffenderAlerts", method = RequestMethod.GET)
	public @ResponseBody List<OffenderAlerts> alertSearchOffenderAlerts(
			@RequestParam(value = "offenderBookId") final String offenderBookId) {
		List<OffenderAlerts> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.alertSearchOffenderAlerts(offenderBookId);
		} catch (Exception e) {
			logger.error("alertSearchOffenderAlerts: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<SystemProfiles>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/sysPflSearchSystemProfiles", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflSearchSystemProfiles(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdalertsevice.sysPflSearchSystemProfiles(searchBean);
		} catch (Exception e) {
			logger.error("sysPflSearchSystemProfiles: ", e);
		}
		return searchResult;
	}

	/**
	 * method for preInsert
	 * 
	 * @return Integer
	 * @param offenderBookId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertPreInsertc", method = RequestMethod.GET)
	public @ResponseBody Integer alertPreInsertc(@RequestParam(value = "offenderBookId") final String offenderBookId) {
		Integer listOfRecords = null;
		try {
			listOfRecords = ocdalertsevice.alertPreInsertc(offenderBookId);
		} catch (Exception e) {
			logger.error("alertPreInsertc: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param modulePrivileges
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertPostQueryvarificationCurr", method = RequestMethod.POST)
	public @ResponseBody Integer alertPostQueryvarificationCurr(@RequestBody final ModulePrivileges modulePrivileges) {
		Integer dataObj = null;
		try {
			dataObj = ocdalertsevice.alertPostQueryvarificationCurr(modulePrivileges);
		} catch (Exception e) {
			logger.error("alertPostQueryvarificationCurr: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @param mode
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgfkchkAlertAlertRefAlertc", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> cgfkchkAlertAlertRefAlertc(
			@RequestParam(value = "alertCode") final String alertCode,
			@RequestParam(value = "mode") final String mode) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfkchkAlertAlertRefAlertc(alertCode, mode);
		} catch (Exception e) {
			logger.error("cgfkchkAlertAlertRefAlertc: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param String
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgfklkpAlertAlertRefAlertc", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> cgfklkpAlertAlertRefAlertc(
			@RequestParam(value = "dspDescription") final String dspDescription,
			@RequestParam(value = "dspDescription1") final String dspDescription1,
			@RequestParam(value = "dspDescription2") final String dspDescription2,
			@RequestParam(value = "alertType") final String alertType,
			@RequestParam(value = "added") final String added, @RequestParam(value = "mode") final String mode) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfklkpAlertAlertRefAlertc(alertType, dspDescription2, dspDescription1,
					dspDescription);
		} catch (Exception e) {
			logger.error("cgfklkpAlertAlertRefAlertc: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param code,domain
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/getAlertReferencesTable", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getAlertReferencesTable(@RequestParam(value = "code") final String code,
			@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfkchkAlertAlertRefAlc(code, domain);
		} catch (Exception e) {
			logger.error("getAlertReferencesTable: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param code,domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/findDescriptionAndCodeAndDomain", method = RequestMethod.POST)
	public @ResponseBody List<ReferenceCodes> findDescriptionAndCodeAndDomain(
			@RequestParam(value = "code") final String code, @RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfkchkAlertAlertRefAlc(code, domain);
		} catch (Exception e) {
			logger.error("findDescriptionAndCodeAndDomain: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 * @param code,
	 *            description, domain
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/getCodeDetails", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getCodeDetails(@RequestParam(value = "code") final String code,
			@RequestParam(value = "description") final String description,
			@RequestParam(value = "parentCode") final String parentCode,
			@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			bean.setCode(code);
			bean.setDescription(description);
			bean.setParentCode(parentCode);
			bean.setDomain(domain);
			listOfRecords = ocdalertsevice.findDescriptionbyDescriptionCodeAndParentCode(bean);
		} catch (Exception e) {
			logger.error("getCodeDetails: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param cost0
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cguvchkOffAlertUkc", method = RequestMethod.GET)
	public @ResponseBody List<OffenderAlerts> cguvchkOffAlertUkc(final @RequestBody OffenderAlerts paramBean) {
		List<OffenderAlerts> listOfRecords = new ArrayList<OffenderAlerts>();
		try {
			listOfRecords = ocdalertsevice.cguvchkOffAlertUkc(paramBean);
		} catch (Exception e) {
			logger.error("cguvchkOffAlertUkc: ", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 * 
	 * @param const0
	 * @return OffenderBookings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/getBookingBeginDate", method = RequestMethod.POST)
	public @ResponseBody OffenderBookings getBookingBeginDate(@RequestParam(value = "const0") final String const0) {
		OffenderBookings dataObj = new OffenderBookings();
		try {
			dataObj = ocdalertsevice.getBookingBeginDate(const0);
		} catch (Exception e) {
			logger.error("getBookingBeginDate: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/findDescriptionbyCode", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> findDescriptionbyCode() {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.findDescriptionbyCode();
		} catch (Exception e) {
			logger.error("findDescriptionbyCode: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param offenderId
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/offbkgsExecuteQuery", method = RequestMethod.POST)
	public @ResponseBody Integer offbkgsExecuteQuery(@RequestParam(value = "offenderId") final String offenderId) {
		Integer count = 0;
		try {
			count = ocdalertsevice.countOffenderBookings(offenderId);
		} catch (Exception e) {
			logger.error("offbkgsExecuteQuery: ", e);
		}
		return count;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param sysDual
	 * @return List<Dual>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgwhenNewFormInstancec", method = RequestMethod.GET)
	public @ResponseBody List<Dual> cgwhenNewFormInstancec(final SysDual sysDual) {
		List<Dual> returnList = new ArrayList<>();
		try {
			returnList = ocdalertsevice.cgwhenNewFormInstancec(sysDual);
		} catch (Exception e) {
			logger.error("cgwhenNewFormInstancec: ", e);
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param code, description, domain
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/findDescriptionbyDomain", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> findDescriptionbyDomain(
			@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		final ReferenceCodes referenceCodesBean = new ReferenceCodes();
		referenceCodesBean.setDomain(domain);
		try {
			listOfRecords = ocdalertsevice.findDescriptionbyDomain(referenceCodesBean);
		} catch (Exception e) {
			logger.error("findDescriptionbyDomain: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param code, description, domain
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgfklkpAlertAlertRefAlc", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> cgfklkpAlertAlertRefAlc(
			@RequestParam(value = "domain") final String domain, @RequestParam(value = "code") final String code,
			@RequestParam(value = "description") final String description,
			@RequestParam(value = "parentCode") final String parentCode) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		final ReferenceCodes referenceCodesBean = new ReferenceCodes();
		referenceCodesBean.setCode(code);
		referenceCodesBean.setDomain(domain);
		referenceCodesBean.setDescription(description);
		referenceCodesBean.setParentCode(parentCode);
		try {
			listOfRecords = ocdalertsevice.cgfklkpAlertAlertRefAlc(referenceCodesBean);
		} catch (Exception e) {
			logger.error("cgfklkpAlertAlertRefAlc: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/findAlertStatusList", method = RequestMethod.GET)
	public @ResponseBody List<String> findAlertStatusList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = ocdalertsevice.findAlertStatusList();
		} catch (Exception e) {
			logger.error("findAlertStatusList: ", e);
		}
		return listOfRecords;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdalert/workFlCommit", method = RequestMethod.POST)
	public @ResponseBody Integer workFlCommit(@RequestBody final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdalertsevice.workFlCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method workFlCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 * 
	 * @param mode
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgfklkpAlertAlertRefCodes", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> cgfklkpAlertAlertRefCodes() {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfklkpAlertAlertRefCodes();
		} catch (Exception e) {
			logger.error("cgfklkpAlertAlertRefCodes: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/cgfklkpAlertAlertCode", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> cgfklkpAlertAlertCode(@RequestParam(value = "alertType") final String alertType) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocdalertsevice.cgfklkpAlertAlertCode(alertType);
		} catch (Exception e) {
			logger.error("cgfklkpAlertAlertRefCodes: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertDeleteChecking", method = RequestMethod.GET)
	public @ResponseBody String  alertdeletechecking() {
		String listOfRecords = null;
		try {
			listOfRecords = ocdalertsevice.alertDeleteChecking();
		} catch (Exception e) {
			logger.error("alertdeletechecking: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalert/alertCodeChecking", method = RequestMethod.GET)
	public @ResponseBody String  alertCodechecking() {
		String listOfRecords = null;
		try {
			listOfRecords = ocdalertsevice.alertCodeChecking();
		} catch (Exception e) {
			logger.error("alertdeletechecking: ", e);
		}
		return listOfRecords;
	}

}