package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.Corporates;

/**
 * Class OtucpayeController
 */
@EliteController
public class OtucpayeController {
	@Autowired
	private OtucpayeService otucpayeService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtucpayeController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otucpaye/corpExecuteQuery", method = RequestMethod.POST)
	public List<Corporates> corpExecuteQuery(@RequestBody final Corporates searchBean) {
		List<Corporates> searchResult = new ArrayList<>();
		try {
			searchResult = otucpayeService.corpExecuteQuery(searchBean);
		} catch (Exception e) {
			final Corporates bean = new Corporates();
			logger.error("In method corpExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}