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
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodesCommitBean;

@EliteController
public class OumhocodController {
	@Autowired
	private OumhocodService oumhocodService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumhocodController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhocod/hoCodesExecuteQuery", method = RequestMethod.POST)
	public List<HoCodes> hoCodesExecuteQuery(@RequestBody final HoCodes searchBean) {
		List<HoCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oumhocodService.hoCodesExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oumhocod/hoCodesCommit", method = RequestMethod.POST)
	public @ResponseBody List<HoCodes> hoCodesCommit(@RequestBody final HoCodesCommitBean commitBean) {
		List<HoCodes> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumhocodService.hoCodesCommit(commitBean);
		} catch (Exception e) {
			final HoCodes error = new HoCodes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Performing the if the record is having parent data or not
	 * 
	 * @Param commitBean
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhocod/hoCodesCheckDeleteMaster", method = RequestMethod.POST)
	public Integer hoCodesCheckDeleteMaster(@RequestBody final HoCodes searchBean) {
		Integer searchResult = 0;
		try {
			searchResult = oumhocodService.hoCodesCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}