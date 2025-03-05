package net.syscon.s4.inst.transportation.maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import net.syscon.s4.inst.transportation.maintenance.OimsglenService;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengths;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengthsCommitBean;

/**
 * Class OimsglenController
 */
@EliteController
public class OimsglenController {

	@Autowired
	private OimsglenService oimsglenService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsglenController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimsglen/agencySegmentLengthsExecuteQuery", method = RequestMethod.POST)
	public List<AgencySegmentLengths> agencySegmentLengthsExecuteQuery(@RequestBody AgencySegmentLengths searchBean) {
		List<AgencySegmentLengths> searchResult = new ArrayList<>();
		try {
			searchResult = oimsglenService.agencySegmentLengthsExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencySegmentLengths bean = new AgencySegmentLengths();
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
	@RequestMapping(value = "/oimsglen/agencySegmentLengthsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer agencySegmentLengthsCommit(@RequestBody AgencySegmentLengthsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsglenService.agencySegmentLengthsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e); 
		}
		return liReturn;
	}

}