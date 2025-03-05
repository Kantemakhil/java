package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.MinimumPayableBalances;
import net.syscon.s4.common.beans.MinimumPayableBalancescommitBean;
import net.syscon.s4.im.beans.AccountCodes;


@EliteController
public class OcmmpbalController {
@Autowired
private OcmmpbalService ocmmpbalService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcmmpbalController.class.getName());
	/**
	 *getting cgfkMinPbAccountCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmmpbal/cgfkMinPbAccountCodeRecordGroup",method=RequestMethod.GET)
	public List<AccountCodes> cgfkMinPbAccountCodeRecordGroup(@RequestParam(value="caseloadType") final String caseloadType) {
		List<AccountCodes> recordList =new ArrayList<>();
		try {
			recordList = ocmmpbalService.cgfkMinPbAccountCodeRecordGroup(caseloadType);
		} catch(Exception e){
			AccountCodes obj = new AccountCodes();
			logger.error("OcmmpbalController  cgfkMinPbAccountCodeRecordGroup Error while calling service : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 *getting cgfkMinPbAccountCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmmpbal/minPbAccountCodeRecordGroup",method=RequestMethod.GET)
	public List<AccountCodes> minPbAccountCodeRecordGroup(@RequestParam(value="caseloadType") final String caseloadType) {
		List<AccountCodes> recordList =new ArrayList<>();
		try {
			recordList = ocmmpbalService.minPbAccountCodeRecordGroup(caseloadType);
		} catch(Exception e){
			AccountCodes obj = new AccountCodes();
			logger.error("OcmmpbalController  minPbAccountCodeRecordGroup Error while calling service : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmmpbal/minPbExecuteQuery", method=RequestMethod.POST)
	public List<MinimumPayableBalances> minPbExecuteQuery(@RequestBody MinimumPayableBalances searchBean) {
		List<MinimumPayableBalances> searchResult = new ArrayList<>();
		try {
			searchResult = ocmmpbalService.minPbExecuteQuery(searchBean);
		} catch (Exception e) {
			MinimumPayableBalances bean = new MinimumPayableBalances();
			logger.error("OcmmpbalController  minPbExecuteQuery Error while calling service :  ", e);
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
	@RequestMapping(value="/ocmmpbal/minPbCommit",method=RequestMethod.POST)
	public @ResponseBody String minPbCommit(@RequestBody MinimumPayableBalancescommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmmpbalService.minPbCommit(commitBean);
		}catch(Exception e){
			liReturn = e.getMessage();
			logger.error("OcmmpbalController  minPbCommit Error while calling service : ", e);
		}
		return liReturn;
	}

}