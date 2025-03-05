package net.syscon.s4.sa.audit.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

@EliteController
public class OumauditController {
	@Autowired
	private OumauditService oumauditService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumauditController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link AllAuditPolicies}
	 * @return a list of the AllAuditPolicies {@link AllAuditPolicies} for the matched AllAuditPolicies
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/allAuditPoliciesExecuteQuery", method = RequestMethod.POST)
	public List<AllAuditPolicies> allAuditPoliciesExecuteQuery(@RequestBody final AllAuditPolicies searchBean) {
		List<AllAuditPolicies> searchResult = new ArrayList<>();
		try {
			searchResult = oumauditService.allAuditPoliciesExecuteQuery(searchBean);
		} catch (final Exception e) {
			final AllAuditPolicies bean = new AllAuditPolicies();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting rgDbObjects LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/rgDbObjectsRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDbObjectsRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumauditService.rgDbObjectsRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oumaudit:", e);
		}
		return recordList;
	}

	/**
	 * getting rgDbObjects LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/getAllTableNames", method = RequestMethod.GET)
	public List<ReferenceCodes> getAllTableNames() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumauditService.getAllTableNames();
		} catch (final Exception e) {
			logger.error("Exception : Oumaudit:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link String}
	 * @return a list of the AllAuditPolicies {@link AllAuditPolicies} for the matched AllAuditPolicies
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/enableOrDisablePolicy", method = RequestMethod.POST)
	public int enableOrDisablePolicy(@RequestBody final AllAuditPolicies searchBean) {
		int result = 0;
		try {
			result = oumauditService.enableOrDisablePolicy(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link AllAuditPolicies}
	 * @return the AllAuditPolicies {@link AllAuditPolicies} for the matched AllAuditPolicies
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/createPolicy", method = RequestMethod.POST)
	public AllAuditPolicies createPolicy(@RequestBody AllAuditPolicies searchBean) {
		try {
			searchBean = oumauditService.createPolicy(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchBean;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link AllAuditPolicies}
	 * @return success/failure of the drop policy {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/dropPolicy", method = RequestMethod.POST)
	public int dropPolicy(@RequestBody final AllAuditPolicies searchBean) {
		int result = 0;
		try {
			result = oumauditService.dropPolicy(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/disableAll", method = RequestMethod.GET)
	public int disableAll() {
		int result = 0;
		try {
			result = oumauditService.disableAll();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/enableAll", method = RequestMethod.GET)
	public int enableAll() {
		int result = 0;
		try {
			result = oumauditService.enableAll();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/dropAll", method = RequestMethod.GET)
	public int dropAll() {
		int result = 0;
		try {
			result = oumauditService.dropAll();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumaudit/createAll", method = RequestMethod.GET)
	public int createAll() {
		int result = 0;
		try {
			result = oumauditService.createAll();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

}