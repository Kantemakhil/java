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
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;


/**
 * OuimtstpController
 */
@EliteController
public class OuimtstpController {
@Autowired
private OuimtstpService ouimtstpService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OuimtstpController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouimtstp/mergTxnProcExecuteQuery", method=RequestMethod.POST)
	public List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(@RequestBody final VMergeTransactionProcesses searchBean) {
		List<VMergeTransactionProcesses> searchResult = new ArrayList<>();
		try {
			searchResult = ouimtstpService.mergTxnProcExecuteQuery(searchBean);
		} catch (Exception e) {
			final VMergeTransactionProcesses bean = new VMergeTransactionProcesses();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		for (VMergeTransactionProcesses result : searchResult) {
			if(result.getBeginDate() == null && result.getEndDate() == null && "Y".equals(result.getTimeframeFlag())) {
				result.setTransferFlag("N");
			}
		}
		return searchResult;
	}

}
