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
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inmate.beans.DeductionTypes;

/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@EliteController
public class OtmalproController {
@Autowired
private OtmalproService otmalproService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtmalproController.class.getName());
	/**
	 *getting cgfkCsldDpAccountCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/cgfkCsldDpAccountCodeRecordGroup",method=RequestMethod.GET)
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(@RequestParam (value="caseloadType") final String caseloadType ) {
		List<AccountCodes> recordList =new ArrayList<AccountCodes>();
		try {
			recordList = otmalproService.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		} catch(Exception e){
			AccountCodes obj = new AccountCodes();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkCsldDdReceiptTxnType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/cgfkCsldDdReceiptTxnTypeRecordGroup",method=RequestMethod.GET)
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(@RequestParam (value="caseloadType") final String caseloadType ) {
		List<TransactionTypes> recordList =new ArrayList<TransactionTypes>();
		try {
			recordList = otmalproService.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);
		} catch(Exception e){
			TransactionTypes obj = new TransactionTypes();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkCsldDpDeductionType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/cgfkCsldDpDeductionTypeRecordGroup",method=RequestMethod.GET)
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(@RequestParam (value="caseloadType") final String caseloadType ) {
		List<DeductionTypes> recordList =new ArrayList<DeductionTypes>();
		try {
			recordList = otmalproService.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);
		} catch(Exception e){
			DeductionTypes obj = new DeductionTypes();
			logger.error("Exception :",e);
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
	@RequestMapping(value="/otmalpro/csldDpExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(@RequestBody CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmalproService.csldDpExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception :",e);
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
	@RequestMapping(value="/otmalpro/csldDpCommit",method=RequestMethod.POST)
	public @ResponseBody String csldDpCommit(@RequestBody CaseloadDeductionProfilesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmalproService.csldDpCommit(commitBean);
		}catch(Exception e){
			liReturn = e.getMessage();
			logger.error("csldDpCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/csldDdExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadDeductionDetails> csldDdExecuteQuery(@RequestBody CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = otmalproService.csldDdExecuteQuery(searchBean);
		} catch (Exception e) {
			CaseloadDeductionDetails bean = new CaseloadDeductionDetails();
			logger.error("Exception :",e);
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
	@RequestMapping(value="/otmalpro/csldDdCommit",method=RequestMethod.POST)
	public @ResponseBody Integer csldDdCommit(@RequestBody CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmalproService.csldDdCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception :",e);
		}
		return liReturn;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/getfromBalDesc", method=RequestMethod.GET)
	public String getfromBalDesc(@RequestParam (value="deductionType") final String deductionType ) {
		String searchResult = null;
		try {
			searchResult = otmalproService.getfromBalDesc(deductionType);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/compareEffectiveDate", method=RequestMethod.GET)
	public Integer compareEffectiveDate(@RequestParam (value="effectiveDate") final String effectiveDate ) {
		Integer searchResult = null;
		try {
			searchResult = otmalproService.compareEffectiveDatec(effectiveDate);
		} catch (Exception e) {
		}
		return searchResult;
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/checkExists", method=RequestMethod.GET)
	public List<CaseloadDeductionProfiles> checkExists(@RequestParam (value="caseloadId") final String caseloadId ,
			@RequestParam (value="deductionType") final String deductionType) {
		List<CaseloadDeductionProfiles> searchResult = null;
		try {
			searchResult = otmalproService.checkExists(caseloadId,deductionType);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmalpro/getfromBalDesc1", method=RequestMethod.GET)
	public String getfromBalTypes(@RequestParam (value="deductionType") final String deductionType ) {
		String searchResult = null;
		try {
			searchResult = otmalproService.getfromBalTypes(deductionType);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmalpro/allocTypeValidation", method = RequestMethod.GET)
	public String allocTypeValidation(@RequestParam("allocType") final String allocType, @RequestParam("caseloadId")String caseloadId) {
		String recordList = null;
		try {
			recordList = otmalproService.allocTypeValidation(allocType,caseloadId);
		} catch (Exception e) {
			logger.error("allocTypeValidation", e);
		}
		return recordList;
	}

}