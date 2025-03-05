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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legals.beans.SentenceAdjustmentCommitBean;

/**
 * Class OimsatypController
 */
@EliteController
public class OimsatypController {
	@Autowired
	private OimsatypService oimsatypService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsatypController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsatyp/sentence_adjustmentsExecuteQuery", method = RequestMethod.POST)
	public List<SentenceAdjustment> sentenceAdjustmentsExecuteQuery(@RequestBody final SentenceAdjustment searchBean) {
		List<SentenceAdjustment> searchResult = new ArrayList<>();
		try {
			searchResult = oimsatypService.sentenceAdjustmentsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oimsatyp/sentence_adjustmentsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sentenceAdjustmentsCommit(@RequestBody final SentenceAdjustmentCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsatypService.sentenceAdjustmentsCommit(commitBean);
		} catch (Exception e) {
			if(e!=null && e.getMessage().contains("offender_legal_adjustments_fk2")) {
				return 18;	
			}
			logger.error("Exception : Oimsatyp", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkSentenceAdjustmentDsp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsatyp/cgfkSentenceAdjustmentDspRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSentenceAdjustmentDspRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsatypService.cgfkSentenceAdjustmentsDspRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimsatyp:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkSentenceAdjustmentUsag LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsatyp/cgfkSentenceAdjustmentUsagRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkSentenceAdjustmentUsagRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimsatypService.cgfkSentenceAdjustmentsUsagRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimsatyp:", e);
		}
		return recordList;
	}

}