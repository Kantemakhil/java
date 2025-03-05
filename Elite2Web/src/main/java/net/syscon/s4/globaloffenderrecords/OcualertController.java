package net.syscon.s4.globaloffenderrecords;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderAlerts;

/**
 * Class OcualertController
 */
@EliteController
public class OcualertController {
	@Autowired
	private OcualertService ocualertService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcualertController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocualert/alertExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAlerts> alertExecuteQuery(@RequestBody final OffenderAlerts searchBean) {
		List<OffenderAlerts> searchResult = new ArrayList<>();
		try {
			searchResult = ocualertService.alertExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In this method alertExecuteQuery:"+e);
		}
		return searchResult;
	}
	
	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocualert/rgAlertDescription", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTownRecordGroup(@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocualertService.rgAlertDescription(domain);
		} catch (Exception e) {
			logger.error("In rgTownRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}


}