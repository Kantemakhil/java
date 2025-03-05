package net.syscon.s4.globalrbac;

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
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;

/**
 * Class OumrolesService
 * 
 * @version 1.0
 */
@EliteController
public class OumrolesController {
	@Autowired
	private OumrolesService oumrolesService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumrolesController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumroles/omsRoleExecuteQuery", method = RequestMethod.POST)
	public List<OmsRoles> omsRoleExecuteQuery(@RequestBody OmsRoles searchBean) {
		List<OmsRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumrolesService.omsRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			OmsRoles bean = new OmsRoles();
			logger.error("In this method omsRoleExecuteQuery :" + e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumroles/omsRoleCommit", method = RequestMethod.POST)
	public @ResponseBody Integer omsRoleCommit(@RequestBody OmsRolesCommitBean commitBean) {
		int liReturn = 0;

		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumrolesService.omsRoleCommit(commitBean);
		} catch (Exception e) {

			logger.error("In this method omsRoleCommit :" + e);
		}
		return liReturn;
	}

}