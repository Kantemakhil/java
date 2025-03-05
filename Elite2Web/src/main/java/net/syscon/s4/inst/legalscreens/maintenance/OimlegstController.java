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
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;

@EliteController
public class OimlegstController {
	@Autowired
	private OimlegstService oimlegstService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimlegstController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link LegalUpdateReasons}
	 * @return a list of the LegalUpdateReasons {@link LegalUpdateReasons} for the
	 *         matched LegalUpdateReasons
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegst/updateReasonsExecuteQuery", method = RequestMethod.POST)
	public List<LegalUpdateReasons> updateReasonsExecuteQuery(@RequestBody final LegalUpdateReasons searchBean) {
		List<LegalUpdateReasons> searchResult = new ArrayList<>();
		try {
			searchResult = oimlegstService.updateReasonsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimlegst/updateReasonsCommit", method = RequestMethod.POST)
	public @ResponseBody List<LegalUpdateReasons> updateReasonsCommit(
			@RequestBody final LegalUpdateReasonsCommitBean commitBean) {
		List<LegalUpdateReasons> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimlegstService.updateReasonsCommit(commitBean);
		} catch (Exception e) {
			final LegalUpdateReasons error = new LegalUpdateReasons();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCat LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegst/rgCatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCatRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimlegstService.rgCatRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in rgCatRecordGroup : Oimlegst:", e);
		}
		return recordList;
	}

	/**
	 * getting rgStat LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegst/rgStatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oimlegstService.rgStatRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oimlegst:", e);
		}
		return recordList;
	}

	/**
	 * Performing the if the record is having parent data or not
	 *
	 * @param searchBean {@link LegalUpdateReasons}
	 * @return a list of the LegalUpdateReasons {@link LegalUpdateReasons} for the
	 *         matched LegalUpdateReasons
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimlegst/getDeleteRecordOrNot", method = RequestMethod.POST)
	public Integer getDeleteRecordOrNot(@RequestBody LegalUpdateReasons searchBean) {
		Integer lireturn = 0;
		try {
			lireturn = oimlegstService.getDeleteRecordOrNot(searchBean);
		} catch (Exception e) {
			logger.error("Exception : Oimlegst:", e);
		}
		return lireturn;
	}
}