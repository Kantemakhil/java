package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OtdagjtrService;
import net.syscon.s4.inst.booking.beans.Persons;


@EliteController
public class OtdagjtrController {
@Autowired
private OtdagjtrService otdagjtrService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtdagjtrController.class.getName());
	/**
	 *getting cgfkGlTxnTxnType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/cgfkGlTxnTxnTypeRecordGroup",method=RequestMethod.GET)
	public List<TransactionTypes> cgfkGlTxnTxnTypeRecordGroup(@RequestParam(value="caseloadId") final String caseloadId,
			@RequestParam(value="caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList =new ArrayList<TransactionTypes>();
		try {
			recordList = otdagjtrService.cgfkGlTxnTxnTypeRecordGroup(caseloadId, caseloadType);
		} catch(Exception e){
			logger.error("OtdagjtrController cgfkGlTxnTxnTypeRecordGroup Exception while calling Txn Type LOV data : ", e);
		}
		return recordList;
	}

	/**
	 *getting cgfkGlTxnPayeePersonId LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/cgfkGlTxnPayeePersonIdRecordGroup",method=RequestMethod.GET)
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup(@RequestParam(value="personId") final Integer personId) {
		List<Persons> recordList =new ArrayList<Persons>();
		try {
			recordList = otdagjtrService.cgfkGlTxnPayeePersonIdRecordGroup(personId);
		} catch(Exception e){
			Persons obj = new Persons();
			logger.error("OtdagjtrController cgfkGlTxnPayeePersonIdRecordGroup Exception while calling service : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkGlTxnPayeeCorporateId LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/cgfkGlTxnPayeeCorporateIdRecordGroup",method=RequestMethod.GET)
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup(@RequestParam(value="corporateId") final Integer corporateId) {
		List<Corporates> recordList =new ArrayList<Corporates>();
		try {
			recordList = otdagjtrService.cgfkGlTxnPayeeCorporateIdRecordGroup(corporateId);
		} catch(Exception e){
			logger.error("OtdagjtrController cgfkGlTxnPayeeCorporateIdRecordGroup Exception while calling service : ", e);
					}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/glTxnExecuteQuery", method=RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdagjtrService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			GlTransactions bean = new GlTransactions();
			searchResult.add(bean);
			logger.error("OtdagjtrController glTxnExecuteQuery Exception while calling service : ", e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/glTxnCommit",method=RequestMethod.POST)
	public @ResponseBody Integer glTxnCommit(@RequestBody GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			liReturn = otdagjtrService.glTxnCommit(commitBean);
			}
		}catch(Exception e){
			logger.error("OtdagjtrController glTxnCommit Exception while calling service : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/glTxn1ExecuteQuery", method=RequestMethod.POST)
	public List<GlTransactions> glTxn1ExecuteQuery(@RequestBody GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = otdagjtrService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("OtdagjtrController glTxn1ExecuteQuery Exception while calling service : ", e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/glTxn1Commit",method=RequestMethod.POST)
	public @ResponseBody Integer glTxn1Commit(@RequestBody GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdagjtrService.glTxn1Commit(commitBean);
		}catch(Exception e){
			logger.error("OtdagjtrController glTxn1Commit Exception while calling service : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/sysPflExecuteQuery", method=RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdagjtrService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error("OtdagjtrController sysPflExecuteQuery Exception while calling service : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/sysPflCommit",method=RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdagjtrService.sysPflCommit(commitBean);
		}catch(Exception e){
			logger.error("OtdagjtrController sysPflCommit Exception while calling service : ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/trustGLReopenClosedPeriod",method=RequestMethod.GET)
	public Date trustGLReopenClosedPeriod(@RequestParam(value="caseloadId") final String caseloadId) {
		Date liReturn = null;
		try {
			liReturn = otdagjtrService.trustGLReopenClosedPeriod(caseloadId);
		}catch(Exception e){

			logger.error("OtdagjtrController trustGLReopenClosedPeriod Exception in Calling TRUST_GJ.REOPEN_CLOSED_PERIOD");
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/onTxnEntryDateBlur",method=RequestMethod.GET)
	public Map<String, Object> onTxnEntryDateBlur(@RequestParam(value="caseloadId") final String caseloadId, 
			@RequestParam(value="txnDate") final Long txnDate) {
		Map<String, Object> liReturn = new HashMap<String, Object>();
		try {
		liReturn = otdagjtrService.onTxnEntryDateBlur(caseloadId, txnDate);
		}catch (Exception e) {
			logger.error("OtdagjtrController onTxnEntryDateBlur Exception in On TXN_ENTRY_DATE Blur Event");
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/onTxnTypeValueChange",method=RequestMethod.GET)
	public Map<String, Object> onTxnTypeValueChange(@RequestParam(value="caseloadId") final String caseloadId, @RequestParam(value="caseloadType") final String caseloadType,
			@RequestParam(value="txnType") final String txnType) {
		Map<String, Object> liReturn = new HashMap<String, Object>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		liReturn = otdagjtrService.onTxnTypeValueChange(caseloadId, caseloadType, txnType, userName);
		}catch (Exception e) {
			logger.error("OtdagjtrController onTxnTypeValueChange Exception in On TXN_TYPE CHANGE Event");
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otdagjtr/prGetOffsetAccounts",method=RequestMethod.POST)
	public List<GlTransactions> prGetOffsetAccounts(@RequestBody final GlTransactions paramBean) {
		List<GlTransactions> resultList = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			paramBean.setCreateUserId(userName);
			resultList = otdagjtrService.prGetOffsetAccounts(paramBean);
		}catch (Exception e) {
			logger.error("OtdagjtrController prGetOffsetAccounts Exception in On PR_GET_OFFSET_ACCOUNTS Service");
		}
		return resultList;
	}
	
}