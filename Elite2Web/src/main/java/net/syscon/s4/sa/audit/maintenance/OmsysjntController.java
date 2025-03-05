package net.syscon.s4.sa.audit.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.dao.RecordGroup;

/**
 * Class OmsysjntController
 */
@EliteController
public class OmsysjntController {
@Autowired
private OmsysjntService omsysjntService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OmsysjntController.class.getName());
	/**
	 *getting tableRg LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/omsysjnt/tableRgRecordGroup",method=RequestMethod.GET)
	public List<RecordGroup> tableRgRecordGroup() {
		List<RecordGroup> recordList = new ArrayList<>();
		try {
			recordList = omsysjntService.tableRgRecordGroup();
		} catch(Exception e){
			logger.error("Exception in tableRgRecordGroup: Omsysjnt:",e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omsysjnt/createOneTr", method = RequestMethod.GET)
	public Integer createOneTr(@RequestParam(value = "tableName") final String tableName,
			@RequestParam(value = "insertTrigger") final Boolean insertTrigger) {
		Integer returnValue = 0;
		try {
			returnValue = omsysjntService.createOneTr(tableName, insertTrigger);
		} catch (Exception e) {
			logger.error("Exception in createOneTr: Omsysjnt:", e);
		}
		return returnValue;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omsysjnt/createTr", method = RequestMethod.GET)
	public Integer createTr(@RequestParam(value = "tableName") final String tableName,
			@RequestParam(value = "insertTrigger") final Boolean insertTrigger) {
		Integer returnValue = 0;
		try {
			returnValue = omsysjntService.createOneTr(tableName, insertTrigger);
		} catch (Exception e) {
			logger.error("Exception in createTr: Omsysjnt:", e);
		}
		return returnValue;
	}
	/**
	 *getting tableRg LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/omsysjnt/tableNamesRgRecordGroup",method=RequestMethod.GET)
	public List<RecordGroup> tableNamesRgRecordGroup() {
		List<RecordGroup> recordList = new ArrayList<>();
		try {
			recordList = omsysjntService.tableRgRecordGroup();
		} catch(Exception e){
			logger.error("Exception in tableRgRecordGroup: Omsysjnt:",e);
		}
		return recordList;
	}
}