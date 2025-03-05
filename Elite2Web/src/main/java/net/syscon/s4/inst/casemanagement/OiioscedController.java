package net.syscon.s4.inst.casemanagement;

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
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * @author class OiioscedController
 */
@EliteController
public class OiioscedController {
	@Autowired
	private OiioscedService oiioscedService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiioscedController.class.getName());

	/**
	 * getting rgSchType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiosced/rgSchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSchTypeRecordGroup(@RequestParam(value = "domain") final String domain) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiioscedService.rgSchTypeRecordGroup(domain);
		} catch (Exception e) {
			logger.error("rgSchTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSchReaExt LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiosced/rgSchReaExtRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgSchReaExtRecordGroup(@RequestParam(value = "schTypeCode") final String schTypeCode) {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		final MovementReasons obj = new MovementReasons();
		try {
			recordList = oiioscedService.rgSchReaExtRecordGroup(schTypeCode);
		} catch (Exception e) {
			logger.error("rgSchReaExtRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSchReaInt LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiosced/rgSchReaIntRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgSchReaIntRecordGroup(
			@RequestParam(value = "schTypeCode") final String schTypeCode) {
		List<InternalScheduleReasons> recordList = new ArrayList<InternalScheduleReasons>();
		final InternalScheduleReasons obj = new InternalScheduleReasons();
		try {
			recordList = oiioscedService.rgSchReaIntRecordGroup(schTypeCode);
		} catch (Exception e) {
			logger.error("rgSchReaIntRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting rgSchReaInt LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiosced/rgSchFilterRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSchFilterRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiioscedService.rgSchFilterRecordGroup();
		} catch (Exception e) {
			logger.error("rgSchReaIntRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiosced/vOffenderAllSchedulesExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> vOffenderAllSchedulesExecuteQuery(
			@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		try {
			searchResult = oiioscedService.vOffenderAllSchedulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vOffenderAllSchedulesExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

}