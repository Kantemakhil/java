package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.OtmtfproFmbBean;
import net.syscon.s4.common.beans.OtmtfproFmbBeanCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;


@EliteController
public class OtmtfproController {
@Autowired
private OtmtfproService otmtfproService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtmtfproController.class.getName());
	/**
	 *getting cgfkCsldDpPayeeCorporateI LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/cgfkCsldDpPayeeCorporateIRecordGroup",method=RequestMethod.GET)
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		List<Corporates> recordList =new ArrayList<Corporates>();
		try {
			recordList = otmtfproService.cgfkCsldDpPayeeCorporateIRecordGroup();
		} catch(Exception e){
			Corporates obj = new Corporates();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkCsldDdReceiptTxnType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/cgfkCsldDdReceiptTxnTypeRecordGroup",method=RequestMethod.GET)
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(@RequestParam(value="caseloadType") final String caseloadType) {
		List<TransactionTypes> recordList =new ArrayList<TransactionTypes>();
		try {
			recordList = otmtfproService.cgfkCsldDdReceiptTxnTypeRecordGroup(caseloadType);
		} catch(Exception e){
			logger.error("OtmtfproController cgfkCsldDpDeductionTypeRecordGroup unable to call service", e);
		}
		return recordList;
	}

	/**
	 *getting cgfkCsldDpDeductionType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/cgfkCsldDpDeductionTypeRecordGroup",method=RequestMethod.GET)
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(@RequestParam(value="caseloadType") final String caseloadType) {
		List<DeductionTypes> recordList =new ArrayList<DeductionTypes>();
		try {
			recordList = otmtfproService.cgfkCsldDpDeductionTypeRecordGroup(caseloadType);
		} catch(Exception e){
			logger.error("OtmtfproController cgfkCsldDpDeductionTypeRecordGroup unable to call service", e);
		}
		return recordList;
	}

	/**
	 *getting cgfkCsldDpAccountCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/cgfkCsldDpAccountCodeRecordGroup",method=RequestMethod.GET)
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(@RequestParam(value="caseloadType") final String caseloadType) {
		List<AccountCodes> recordList =new ArrayList<AccountCodes>();
		try {
			recordList = otmtfproService.cgfkCsldDpAccountCodeRecordGroup(caseloadType);
		} catch(Exception e){
			logger.error("OtmtfproController cgfkCsldDpAccountCodeRecordGroup unable to call service", e);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/csldDpExecuteQuery", method=RequestMethod.POST)
	public List<OtmtfproFmbBean> csldDpExecuteQuery(@RequestBody OtmtfproFmbBean searchBean) {
		List<OtmtfproFmbBean> searchResult = new ArrayList<>();
		try {
			searchResult = otmtfproService.csldDpExecuteQuery(searchBean);
		} catch (Exception e) {
			OtmtfproFmbBean bean = new OtmtfproFmbBean();
			logger.error("OtmtfproController csldDpExecuteQuery unable to call service : ", e);
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
	@RequestMapping(value="/otmtfpro/csldDpCommit",method=RequestMethod.POST)
	public @ResponseBody Integer csldDpCommit(@RequestBody OtmtfproFmbBeanCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = otmtfproService.csldDpCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/csldDdExecuteQuery", method=RequestMethod.POST)
	public List<OtmtfproFmbBean> csldDdExecuteQuery(@RequestBody OtmtfproFmbBean searchBean) {
		List<OtmtfproFmbBean> searchResult = new ArrayList<>();
		try {
			searchResult = otmtfproService.csldDdExecuteQuery(searchBean);
		} catch (Exception e) {
			OtmtfproFmbBean bean = new OtmtfproFmbBean();
			logger.error("OtmtfproController csldDdExecuteQuery unable to call query : ", e);
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
	@RequestMapping(value="/otmtfpro/csldDdCommit",method=RequestMethod.POST)
	public @ResponseBody Integer csldDdCommit(@RequestBody OtmtfproFmbBeanCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = otmtfproService.csldDdCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/sysPflExecuteQuery", method=RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmtfproService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param msgNo
	 *@Param applnCode
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/omsUtilsDisplayUserMessage", method=RequestMethod.GET)
	public String omsUtilsDisplayUserMessage(@RequestParam(value="msgNo") final BigDecimal msgNo, @RequestParam(value="applnCode") final String applnCode) {
		String message = "";
		try {
			message = otmtfproService.omsUtilsDisplayUserMessage(msgNo, applnCode);
		} catch(Exception e) {
			logger.error("OtmtfproController omsUtilsDisplayUserMessage unable to call service : ", e);
		}
		
		return message;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param caseloadId
	 *@Param deductionType
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/chkDuplicateDedType", method=RequestMethod.GET)
	List<CaseloadDeductionProfiles> chkDuplicateDedType(@RequestParam(value="caseloadId") final String caseloadId, @RequestParam(value="deductionType") final String deductionType) {
		List<CaseloadDeductionProfiles> result = new ArrayList<>();
		try {
			result = otmtfproService.chkDuplicateDedType(caseloadId, deductionType);
		} catch(Exception e) {
			logger.error("OtmtfproController chkDuplicateDedType unable to call service : ", e);
		}
		
		return result;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param paramBean
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/otmtfpro/cgfkchkCsldDpDedprofCorp", method=RequestMethod.POST)
	public List<Corporates> cgfkchkCsldDpDedprofCorp(@RequestBody final Corporates paramBean) {
		List<Corporates> result = new ArrayList<>();
		try {
			result = otmtfproService.CgfkchkCsldDpDedprofCorp(paramBean);
		}catch (Exception e) {
			logger.error("OtmtfproController cgfkchkCsldDpDedprofCorp unable to call service : ", e);
		}
		return result;
	}

}