package net.syscon.s4.iwp;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomesCommitBean;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasuresCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * @version 1.0
 */
@EliteController
public class OcmeventController {
	@Autowired
	private OcmeventService ocmeventService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmeventController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmevent/scheduleExecuteQuery", method = RequestMethod.GET)
	public List<EventMeasures> scheduleExecuteQuery() {
		List<EventMeasures> searchResult = new ArrayList<>();
		try {
			searchResult = ocmeventService.scheduleExecuteQuery();
		} catch (Exception e) {
			EventMeasures bean = new EventMeasures();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocmevent/scheduleCommit", method = RequestMethod.POST)
	public @ResponseBody Integer scheduleCommit(@RequestBody final EventMeasuresCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmeventService.scheduleCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmevent/rgTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmeventService.rgTypeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSubType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmevent/rgSubTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTypeRecordGroup(@RequestParam final String eventType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmeventService.rgSubTypeRecordGroup(eventType);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStatic LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmevent/rgStaticRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStaticRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmeventService.rgStaticRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOutcomeCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmevent/rgOutcomeCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOutcomeCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmeventService.rgOutcomeCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocmevent/outcomeExecuteQuery", method = RequestMethod.POST)
	public List<EventMeasureOutcomes> outcomeExecuteQuery(@RequestBody final EventMeasureOutcomes searchBean) {
		List<EventMeasureOutcomes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmeventService.outcomeExecuteQuery(searchBean);
		} catch (Exception e) {
			EventMeasureOutcomes bean = new EventMeasureOutcomes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocmevent/outcomeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer outcomeCommit(@RequestBody final EventMeasureOutcomesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmeventService.outcomeCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}

}