package net.syscon.s4.inst.legalscreens.maintenance;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;

@EliteController
public class OimlegsuController {
	@Autowired
	private OimlegsuService oimlegsuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimlegsuController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegsu/lglUpdUsagesExecuteQuery", method = RequestMethod.POST)
	public List<LegalUpdateUsages> lglUpdUsagesExecuteQuery(@RequestBody final LegalUpdateUsages searchBean) {
		List<LegalUpdateUsages> searchResult = new ArrayList<>();
		try {
			searchResult = oimlegsuService.lglUpdUsagesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("lglUpdUsagesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimlegsu/lglUpdUsagesCommit", method = RequestMethod.POST)
	public @ResponseBody LegalUpdateUsages lglUpdUsagesCommit(
			@RequestBody final LegalUpdateUsagesCommitBean commitBean) {
		LegalUpdateUsages liReturn = new LegalUpdateUsages();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimlegsuService.lglUpdUsagesCommit(commitBean);
		} catch (Exception e) {
			liReturn.setErrorMessage(e.getMessage().toUpperCase());
			logger.error("lglUpdUsagesCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgLegalClass LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegsu/rgLegalClassRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLegalClassRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimlegsuService.rgLegalClassRecordGroup();
		} catch (Exception e) {
			logger.error("rgLegalClassRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgUpdateReasonCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegsu/rgUpdateReasonCodeRecordGroup", method = RequestMethod.GET)
	public List<LegalUpdateReasons> rgUpdateReasonCodeRecordGroup() {
		List<LegalUpdateReasons> recordList = new ArrayList<LegalUpdateReasons>();
		try {
			recordList = oimlegsuService.rgUpdateReasonCodeRecordGroup();
		} catch (Exception e) {
			logger.error("rgUpdateReasonCodeRecordGroup :", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegsu/postQueryData", method = RequestMethod.GET)
	public @ResponseBody LegalUpdateUsages postQueryData(
			@RequestParam("updateReasonCode") final String updateReasonCode) {
		LegalUpdateUsages liReturn = null;
		try {
			liReturn = oimlegsuService.postQueryData(updateReasonCode);
		} catch (Exception e) {

			logger.error("postQueryData :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegsu/deleteKeyDelRec", method = RequestMethod.POST)
	public Integer deleteKeyDelRec(@RequestBody final LegalUpdateUsages searchBean) {
		Integer searchResult = null;
		try {
			searchResult = oimlegsuService.deleteKeyDelRec(searchBean);
		} catch (Exception e) {
			logger.error("deleteKeyDelRec :", e);
		}
		return searchResult;
	}
}