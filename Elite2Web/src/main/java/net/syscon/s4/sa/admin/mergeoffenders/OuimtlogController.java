package net.syscon.s4.sa.admin.mergeoffenders;

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
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

/**
 * OuimtlogController
 */
@EliteController
public class OuimtlogController {
	@Autowired
	private OuimtlogService ouimtlogService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuimtlogController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouimtlog/mergeLogExecuteQuery", method = RequestMethod.POST)
	public List<MergeTransactionLogs> mergeLogExecuteQuery(@RequestBody final MergeTransactionLogs searchBean) {
		List<MergeTransactionLogs> searchResult = new ArrayList<>();
		try {
			searchResult = ouimtlogService.mergeLogExecuteQuery(searchBean);
		} catch (Exception e) {
			final MergeTransactionLogs bean = new MergeTransactionLogs();
			logger.error("Exception : mergeLogExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}