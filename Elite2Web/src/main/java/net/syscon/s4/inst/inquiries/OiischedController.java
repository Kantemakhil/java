package net.syscon.s4.inst.inquiries;

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
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.workflow.managingteams.OcittaskService;

/**
 * class OiischedController
 */
@EliteController
public class OiischedController {
	@Autowired
	private OiischedService oiischedService;
	
	@Autowired
	private OcittaskService ocittaskService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiischedController.class.getName());

	/**
	 * getting rgSchFilter LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiisched/rgSchFilterRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSchFilterRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiischedService.rgSchFilterRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiisched/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup(@RequestParam("scheduleFilter") final String scheduleFilter) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiischedService.rgTypeRecordGroup(scheduleFilter);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSubtype LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiisched/rgSubtypeRecordGroup", method = RequestMethod.GET)
	public List<InternalScheduleReasons> rgSubtypeRecordGroup(
			@RequestParam("scheduleFilter") final String scheduleFilter,
			@RequestParam("scheduleType") final String scheduleType) {
		List<InternalScheduleReasons> recordList = new ArrayList<>();
		try {
			recordList = oiischedService.rgSubtypeRecordGroup(scheduleFilter, scheduleType);
		} catch (final Exception e) {
			final InternalScheduleReasons obj = new InternalScheduleReasons();
			logger.error("rgSubtypeRecordGroup :", e);
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
	@RequestMapping(value = "/oiisched/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		try {
			searchResult = oiischedService.offSchExecuteQuery(searchBean);
		} catch (final Exception e) {
			final VOffenderAllSchedules bean = new VOffenderAllSchedules();
			logger.error("offSchExecuteQuery :", e);
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * getting rgTaskSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiisched/getOffenderBookId", method = RequestMethod.GET)
	public VPimsNameSearch getOffenderBookId(@RequestParam("offenderIdDisplay") final String offenderIdDisplay,
			@RequestParam("caseloadId") final String caseloadId) {
		VPimsNameSearch vPimsNameSearch = new VPimsNameSearch();
		try {
			vPimsNameSearch = ocittaskService.getOffenderBookId(offenderIdDisplay, caseloadId);
		} catch (final Exception e) {
			final VPimsNameSearch obj = new VPimsNameSearch();
			logger.error("rgTaskSubTypeRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
		}
		return vPimsNameSearch;
	}

}