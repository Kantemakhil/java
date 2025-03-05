package net.syscon.s4.inmate;

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
import net.syscon.s4.im.beans.VTrustHeader;

@EliteController
public class OtinamesController {
	@Autowired
	private OtinamesService otinamesService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtinamesController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otinames/vThaExecuteQuery", method = RequestMethod.POST)
	public List<VTrustHeader> vThaExecuteQuery(@RequestBody final VTrustHeader searchBean) {
		List<VTrustHeader> searchResult = new ArrayList<>();
		try {
			searchResult = otinamesService.vThaExecuteQuery(searchBean);
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
	@RequestMapping(value = "/otinames/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otinamesService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

}