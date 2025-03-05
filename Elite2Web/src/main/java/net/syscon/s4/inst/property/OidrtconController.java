package net.syscon.s4.inst.property;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;

/**
 * Class OidrtconController
 */
@EliteController
public class OidrtconController {
	@Autowired
	private OidrtconService oidrtconService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrtconController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrtcon/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidrtconService.offConExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("", e);
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
	@RequestMapping(value = "/oidrtcon/offConCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderPptyContainers offConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean) {
		OffenderPptyContainers returnObj=new OffenderPptyContainers();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnObj = oidrtconService.offConCommit(commitBean);
		} catch (Exception e) {
			returnObj.setSealFlag("0");
			logger.error("OidrtconController :: Error in offConCommit"+ e.getMessage());
		}
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrtcon/recievedFromLov", method = RequestMethod.GET)
	public List<AgencyLocations> oidrtconRecievedFromLov() {
		List<AgencyLocations> searchResult = null;
		try {
			searchResult = oidrtconService.oidrtconRecievedFromLov();
		} catch (Exception e) {
			logger.error("offConExecuteQuery", e);
		}
		return searchResult;
	}

}