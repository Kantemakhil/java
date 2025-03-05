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
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.VTrustHeader;

@EliteController
public class OtusubadController {
	@Autowired
	private OtusubadService otusubadService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtusubadController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/otusubad/vThaExecuteQuery", method = RequestMethod.POST)
	public List<VTrustHeader> vThaExecuteQuery(@RequestBody final VTrustHeader searchBean) {
		List<VTrustHeader> searchResult = new ArrayList<>();
		try {
			searchResult = otusubadService.vThaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vThaExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otusubad/getRootOffenderId", method = RequestMethod.POST)
	public List<VTrustHeader> getRootOffenderId(@RequestBody final VTrustHeader searchBean) {
		List<VTrustHeader> searchResult = new ArrayList<VTrustHeader>();
		try {
			searchResult = otusubadService.getRootOffenderId(searchBean);
		} catch (Exception e) {
			logger.error("offSasExecuteQuery", e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otusubad/offSasExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSubAcShadows> offSasExecuteQuery(@RequestBody final OffenderSubAcShadows searchBean) {
		List<OffenderSubAcShadows> searchResult = new ArrayList<>();
		try {
			searchResult = otusubadService.offSasExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offSasExecuteQuery", e);
		}
		return searchResult;
	}

}