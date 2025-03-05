package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * OtuholdrController Class
 */
@EliteController
public class OtuholdrController {
@Autowired
private OtuholdrService otuholdrService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtuholdrController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otuholdr/offTxnExecuteQuery", method=RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otuholdrService.offTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error("Exception offTxnExecuteQuery :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/otuholdr/offTxnCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offTxnCommit(@RequestBody OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otuholdrService.offTxnCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception offTxnCommit :",e);
		}
		return liReturn;
	}
	
	/**
	 *Perfomring basic Query the database table
	 *@Param getVHoldClearFlag
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otuholdr/getVHoldClearFlag",method=RequestMethod.POST)
	public @ResponseBody String getVHoldClearFlag(@RequestBody OffenderTransactions paramBean) {
		String liReturn = "";
		try {
			liReturn = otuholdrService.getVHoldClearFlag(paramBean);
		}catch(Exception e){

			logger.error("Exception getVHoldClearFlag :",e);
		}
		return liReturn;
	}
	
	/**
	 *Perfomring basic Query the database table
	 *@Param getVHoldClearFlag
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/otuholdr/onInsert",method=RequestMethod.POST)
	public @ResponseBody String onInsert(@RequestBody OffenderTransactions paramBean) {
		String liReturn = "";
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			paramBean.setCreateUserId(userName);
			liReturn = otuholdrService.onInsert(paramBean);
		}catch(Exception e){
			liReturn = e.getMessage();
			logger.error("Exception onInsert :",e);
		}
		return liReturn;
	}
	

}