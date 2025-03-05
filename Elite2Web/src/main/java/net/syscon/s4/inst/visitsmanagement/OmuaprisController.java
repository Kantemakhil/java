package net.syscon.s4.inst.visitsmanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitorsCommitBean;

/**
 */
@EliteController
public class OmuaprisController {
	@Autowired
	private OmuaprisService omuaprisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuaprisController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuapris/vOffAuthVisExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(@RequestBody final VOffenderAuthorisedVisitors searchBean) {
		List<VOffenderAuthorisedVisitors> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = omuaprisService.vOffAuthVisExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("vOffAuthVisExecuteQuery",e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuapris/vOffAuthVisCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vOffAuthVisCommit(@RequestBody final VOffenderAuthorisedVisitorsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = omuaprisService.vOffAuthVisCommit(commitBean);
		} catch (Exception e) {
			logger.error("vOffAuthVisCommit",e);
		}
		return liReturn;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuapris/getOffenderRestrcitions", method = RequestMethod.GET)
	public List<OffenderRestrictions> getOffenderRestrcitions(@RequestParam(value="vstOffIdDisplay") final String vstOffIdDisplay,@RequestParam(value="visitdDate") final Long visitdDate) {
		List<OffenderRestrictions> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = omuaprisService.getOffenderRestrcitions(vstOffIdDisplay,new Date(visitdDate),userName);
		} catch (Exception e) {
			logger.error("vOffAuthVisExecuteQuery",e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/omuapris/getOffenderBookId", method = RequestMethod.GET)
	public BigDecimal getOffenderBookId(@RequestParam(value="vstOffIdDisplay") final String vstOffIdDisplay) {
		BigDecimal searchResult =null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = omuaprisService.getOffenderBookId(vstOffIdDisplay,userName);
		} catch (Exception e) {
			logger.error("getOffenderBookId",e);
		}
		return searchResult;
	}
	
}