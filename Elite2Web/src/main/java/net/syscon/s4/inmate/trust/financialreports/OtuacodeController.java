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
import net.syscon.s4.im.beans.AccountCodes;

/**
 * OtuacodeController
 */
@EliteController
public class OtuacodeController {
	@Autowired
	private OtuacodeService otuacodeService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtuacodeController.class.getName());

	/**
	 * Fetching the record from database table method:acCodeExecuteQuery
	 *
	 * @param searchBean {@link String}
	 * @return a list of the AccountCodes {@link AccountCodes} for the matched agency location Id
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otuacode/acCodeExecuteQuery", method = RequestMethod.POST)
	public List<AccountCodes> acCodeExecuteQuery(@RequestBody AccountCodes searchBean) {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = otuacodeService.acCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

}