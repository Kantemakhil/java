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
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;

/**
 * class OcipbeneController
 */
@EliteController
public class OcipbeneController {
	@Autowired
	private OcipbeneService ocipbeneService;
	/**
	 * Logger object used to print the log the file
	 */
	private static Logger logger = LogManager.getLogger(OcipbeneController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipbene/perExecuteQuery", method = RequestMethod.POST)
	public List<Persons> perExecuteQuery(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		try {
			searchResult = ocipbeneService.perExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("perExecuteQuery method : ", e);
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
	@RequestMapping(value = "/ocipbene/perCommit", method = RequestMethod.POST)
	public @ResponseBody Integer perCommit(@RequestBody final PersonsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocipbeneService.perCommit(commitBean);
		} catch (Exception e) {
			logger.error("perCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipbene/offBncExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBeneficiaries> offBncExecuteQuery(@RequestBody final OffenderBeneficiaries searchBean) {
		List<OffenderBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocipbeneService.offBncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offBncExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipbene/sysPflExecuteQuery", method = RequestMethod.POST)
	public SystemProfiles sysPflExecuteQuery(@RequestBody final Persons searchBean) {
		SystemProfiles searchResult = null;
		try {
			searchResult = ocipbeneService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery method : ", e);
		}
		return searchResult;
	}

}