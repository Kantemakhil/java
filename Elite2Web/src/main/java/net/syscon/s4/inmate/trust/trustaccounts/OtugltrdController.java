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
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Class OtugltrdController
 */
@EliteController
public class OtugltrdController {
	@Autowired
	private OtugltrdService otugltrdService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtugltrdController.class.getName());

	/**
	 * Fetching the record from database table
	 * Method glTxnExecuteQuery
	 * @Param searchRecord
	 * return List<GlTransactions>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otugltrd/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otugltrdService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in glTxnExecuteQuery:", e);
		}
		return searchResult;
	}

}