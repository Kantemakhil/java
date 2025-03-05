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
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.StatutesCommitBean;

@EliteController
public class OimstatuController {
	@Autowired
	private OimstatuService oimstatuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimstatuController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimstatu/statExecuteQuery", method = RequestMethod.POST)
	public List<Statutes> statExecuteQuery(@RequestBody final Statutes searchBean) {
		List<Statutes> searchResult = new ArrayList<>();

		try {
			searchResult = oimstatuService.statExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimstatu/statCommit", method = RequestMethod.POST)
	public @ResponseBody List<Statutes> statCommit(@RequestBody final StatutesCommitBean commitBean) {
		List<Statutes> list = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			list = oimstatuService.statCommit(commitBean);
		} catch (final Exception e) {
			logger.error("Exception  : oimprfca", e);
		}
		return list;
	}

}