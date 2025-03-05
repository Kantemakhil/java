package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;

/**
 * Class OcicbeneController
 */
@EliteController
public class OcicbeneController {
	@Autowired
	private OcicbeneService ocicbeneService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcicbeneController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicbene/vCorpExecuteQuery", method = RequestMethod.POST)
	public List<CorporateAddressV> vCorpExecuteQuery(@RequestBody final CorporateAddressV searchBean) {
		List<CorporateAddressV> searchResult = new ArrayList<>();
		try {
			searchResult = ocicbeneService.vCorpExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vCorpExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicbene/offBncExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBeneficiaries> offBncExecuteQuery(@RequestBody final OffenderBeneficiaries searchBean) {
		List<OffenderBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocicbeneService.offBncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offBncExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocicbene/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocicbeneService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form function i.e. update into the database table
	 * 
	 * @Param beanObj
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocicbene/vCorpCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vCorpCommit(@RequestBody final Corporates beanObj) {
		int liReturn = 0;
		try {
			liReturn = ocicbeneService.vCorpCommit(beanObj);
		} catch (Exception e) {
			logger.error("vCorpCommit: ", e);
		}
		return liReturn;
	}
}