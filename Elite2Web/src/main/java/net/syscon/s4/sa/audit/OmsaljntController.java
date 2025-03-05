package net.syscon.s4.sa.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;

@EliteController
public class OmsaljntController {
	@Autowired
	private OmsaljntService omsaljntService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmsaljntController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omsaljnt/journalTableViewExecuteQuery", method = RequestMethod.POST)
	public List<JournalTableView> journalTableViewExecuteQuery(@RequestBody final JournalTableView searchBean) {
		List<JournalTableView> searchResult = new ArrayList<>();
		try {
			searchResult = omsaljntService.journalTableViewExecuteQuery(searchBean);
		} catch (Exception e) {
			JournalTableView bean = new JournalTableView();
			logger.error("Exception :", e);
			 bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/omsaljnt/journalTableViewCommit", method = RequestMethod.POST)
	public @ResponseBody Integer journalTableViewCommit(@RequestBody final JournalTableViewCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = omsaljntService.journalTableViewCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Omsaljnt", e);
		}
		return liReturn;
	}

}