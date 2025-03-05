package net.syscon.s4.sa.admin;

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
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.sa.admin.beans.LockedModulesCommitBean;

/**
 * OtmlockrController
 */
@EliteController
public class OtmlockrController {
	@Autowired
	private OtmlockrService otmlockrService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmlockrController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmlockr/lockModExecuteQuery", method = RequestMethod.POST)
	public List<LockedModules> lockModExecuteQuery(@RequestBody final LockedModules searchBean) {
		List<LockedModules> searchResult = new ArrayList<>();
		try {
			searchResult = otmlockrService.lockModExecuteQuery(searchBean);
		} catch (final Exception e) {
			final LockedModules bean = new LockedModules();
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
	@RequestMapping(value = "/otmlockr/lockModCommit", method = RequestMethod.POST)
	public @ResponseBody Integer lockModCommit(@RequestBody final LockedModulesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otmlockrService.lockModCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

}