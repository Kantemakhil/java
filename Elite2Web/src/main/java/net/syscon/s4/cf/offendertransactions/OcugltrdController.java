package net.syscon.s4.cf.offendertransactions;

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

@EliteController
public class OcugltrdController {
	@Autowired
	private OcugltrdService ocugltrdService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcugltrdController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocugltrd/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocugltrdService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			GlTransactions bean = new GlTransactions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}