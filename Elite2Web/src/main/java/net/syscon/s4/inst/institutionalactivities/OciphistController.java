package net.syscon.s4.inst.institutionalactivities;

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
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.beans.prgPayBatchesBean;

/**
 * Class OciphistController
 */
@EliteController
public class OciphistController {
	@Autowired
	private OciphistService ociphistService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcgpayController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociphist/prgPayBatchesExecuteQuery", method = RequestMethod.POST)
	public List<prgPayBatchesBean> prgPayBatchesExecuteQuery(@RequestBody prgPayBatchesBean searchBean) {
		List<prgPayBatchesBean> searchResult = new ArrayList<>();
		try {
			searchResult = ociphistService.prgPayBatchesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in prgPayBatchesExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociphist/payDetailsExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseAttendances> payDetailsExecuteQuery(@RequestBody prgPayBatchesBean searchBean) {
		List<VOffenderCourseAttendances> searchResult = new ArrayList<>();
		try {
			searchResult = ociphistService.payDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in payDetailsExecuteQuery:", e);
		}
		return searchResult;
	}

}
