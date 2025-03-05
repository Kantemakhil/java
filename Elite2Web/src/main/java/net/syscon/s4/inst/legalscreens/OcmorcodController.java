package net.syscon.s4.inst.legalscreens;

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
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.OffenceResultCodesCommitBean;

@EliteController
public class OcmorcodController {
	@Autowired
	private OcmorcodService ocmorcodService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmorcodController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmorcod/resCodExecuteQuery", method = RequestMethod.POST)
	public List<OffenceResultCodes> resCodExecuteQuery(@RequestBody final OffenceResultCodes searchBean) {
		List<OffenceResultCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmorcodService.resCodExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/ocmorcod/resCodCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenceResultCodes> resCodCommit(@RequestBody final OffenceResultCodesCommitBean commitBean) {
		List<OffenceResultCodes> returnList = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			returnList = ocmorcodService.resCodCommit(commitBean);
		} catch (Exception e) {
			final OffenceResultCodes error = new OffenceResultCodes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			returnList.add(error);
			logger.error("Exception : Ocmorcod", e);
		}
		return returnList;
	}
}