package net.syscon.s4.inmate.trust.generalledger;

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
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;

/**
 * class OtiglbalController
 */
@EliteController
public class OtiglbalController {
	@Autowired
	private OtiglbalService otiglbalService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtiglbalController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otiglbal/csldCaExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(@RequestBody final CaseloadCurrentAccounts searchBean) {
		List<CaseloadCurrentAccounts> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = otiglbalService.csldCaExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In csldCaExecuteQuery method : ", e);
		}
		return searchResult;
	}

}