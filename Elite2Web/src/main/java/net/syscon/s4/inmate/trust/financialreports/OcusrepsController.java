package net.syscon.s4.inmate.trust.financialreports;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OmsModules;

@EliteController
public class OcusrepsController {
	@Autowired
	private OcusrepsService ocusrepsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcusrepsController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OmsModules}
	 * @return a list of the OmsModules {@link OmsModules} for the matched OmsModules
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocusreps/omsModulesExecuteQuery", method = RequestMethod.POST)
	public List<OmsModules> omsModulesExecuteQuery(@RequestBody final OmsModules searchBean) {
		List<OmsModules> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocusrepsService.omsModulesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("omsModulesExecuteQuery", e);
		}
		return searchResult;
	}

}