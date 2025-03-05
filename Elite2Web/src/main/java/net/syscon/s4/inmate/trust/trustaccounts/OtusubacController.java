package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

@EliteController
public class OtusubacController {
	@Autowired
	private OtusubacService otusubacService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtusubacController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otusubac/offNameExecuteQuery", method = RequestMethod.POST)
	public List<Offenders> offNameExecuteQuery(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<>();
		try {
			searchResult = otusubacService.offNameExecuteQuery(searchBean);
		} catch (Exception e) {
			Offenders bean = new Offenders();
			logger.error(this.getClass().getName()+"In method offNameExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otusubac/offSubaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAccounts> offSubaExecuteQuery(@RequestBody OffenderSubAccounts searchBean) {
		List<OffenderSubAccounts> searchResult = new ArrayList<>();
		try {
			searchResult = otusubacService.offSubaExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderSubAccounts bean = new OffenderSubAccounts();
			logger.error(this.getClass().getName()+"In method offSubaExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otusubac/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otusubacService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+"In method sysPflExecuteQuery error", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}