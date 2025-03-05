package net.syscon.s4.cf.offendertransactions;

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

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlanCommitBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.cf.offendertransactions.beans.VOffenderPaymentSchedule;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.OffenderDeductions;

@EliteController
public class OcupayplController {
	@Autowired
	private OcupayplService ocupayplService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcupayplController.class.getName());

	/**
	 * getting cgfkPayPlnInformationNumbe LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/cgfkPayPlnInformationNumbeRecordGroup", method = RequestMethod.GET)
	public List<OffenderDeductions> cgfkPayPlnInformationNumbeRecordGroup(
			@RequestParam("offenderId") final Long offenderId, @RequestParam("caseLoadId") final String caseloadId) {
		List<OffenderDeductions> recordList = new ArrayList<OffenderDeductions>();
		try {
			recordList = ocupayplService.cgfkPayPlnInformationNumbeRecordGroup(offenderId,caseloadId);
		} catch (Exception e) {
			final OffenderDeductions obj = new OffenderDeductions();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkPayPlnDspDescription4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/cgfkPayPlnDspDescription4RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkPayPlnDspDescription4RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupayplService.cgfkPayPlnDspDescription4RecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkPayPlnDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/cgfkPayPlnDspDescription3RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkPayPlnDspDescription3RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupayplService.cgfkPayPlnDspDescription3RecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkPayPlnDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/cgfkPayPlnDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkPayPlnDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocupayplService.cgfkPayPlnDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/payPlnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPaymentPlan> payPlnExecuteQuery(@RequestBody final OffenderPaymentPlan searchBean) {
		List<OffenderPaymentPlan> searchResult = new ArrayList<>();
		try {
			searchResult = ocupayplService.payPlnExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocupaypl/payPlnCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderPaymentPlan> payPlnCommit(
			@RequestBody final OffenderPaymentPlanCommitBean commitBean) {
		List<OffenderPaymentPlan> liReturn = new ArrayList<>();
		try {
			if(commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocupayplService.payPlnCommit(commitBean);
		} catch (Exception e) {

			final OffenderPaymentPlan error = new OffenderPaymentPlan();
			final String errorMsg = "Error : ocupaypl  payPlnCommit" + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception : payPlnCommit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocupaypl/keyCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderPaymentPlan keyCommit(@RequestBody final OffenderPaymentPlan offenderPlan) {
		System.out.println(" keyCommit method satrt in controller  ");
		OffenderPaymentPlan liReturn = new OffenderPaymentPlan();
		try {
			liReturn = ocupayplService.keyCommit(offenderPlan);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		System.out.println(" keyCommit method end  in controller  ");
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocupaypl/adjustForRoundoffs", method = RequestMethod.POST)
	public @ResponseBody OffenderPaymentPlan adjustForRoundoffs(@RequestBody final OffenderPaymentPlan offenderPlan) {
		System.out.println(" adjustForRoundoffs  method satrt   in controller  ");
		OffenderPaymentPlan liReturn = new OffenderPaymentPlan();
		try {
			liReturn = ocupayplService.adjustForRoundoffs(offenderPlan);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		System.out.println(" adjustForRoundoffs  method end  in controller   ");
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/paySchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderPaymentSchedule> paySchExecuteQuery(@RequestBody final VOffenderPaymentSchedule searchBean) {
		List<VOffenderPaymentSchedule> searchResult = new ArrayList<>();
		try {
			searchResult = ocupayplService.paySchExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderPaymentSchedule bean = new VOffenderPaymentSchedule();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/ppTxnExecuteQuery", method = RequestMethod.POST)
	public List<PaymentPlanTransaction> ppTxnExecuteQuery(@RequestBody final PaymentPlanTransaction searchBean) {
		List<PaymentPlanTransaction> searchResult = new ArrayList<>();
		try {
			searchResult = ocupayplService.ppTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			final PaymentPlanTransaction bean = new PaymentPlanTransaction();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocupayplService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/whenValidateItem", method = RequestMethod.POST)
	public OffenderPaymentPlan whenValidateItem(@RequestBody final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan searchResult = new OffenderPaymentPlan();

		try {
			searchResult = ocupayplService.whenValidateItem(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}


		return searchResult;
	}


	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/whenNewRecordInsatnce", method = RequestMethod.POST)
	public OffenderPaymentPlan whenNewRecordInsatnce(@RequestBody final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan retData = new OffenderPaymentPlan();
		try {
			retData = ocupayplService.whenNewRecordInsatnce(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return retData;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/postBlockPlan", method = RequestMethod.POST)
	public OffenderPaymentPlan postBlockPlan(@RequestBody final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan totalArrears = new OffenderPaymentPlan();
		try {
			totalArrears = ocupayplService.postBlockPlan(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return totalArrears;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/whenNewBlockInstanceSch", method = RequestMethod.POST)
	public Integer whenNewBlockInstanceSch(@RequestBody final OffenderPaymentPlan searchBean) {
		int lvMostCurrent = 0;
		try {
			lvMostCurrent = ocupayplService.whenNewBlockInstanceSch(searchBean.getPaymentPlanId());
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return lvMostCurrent;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/whenCheckboxChanged", method = RequestMethod.POST)
	public OffenderPaymentPlan WhenCheckboxChanged(@RequestBody final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan returnData = new OffenderPaymentPlan();
		if(searchBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setModifyUserId(userName);
		}
		try {
			returnData = ocupayplService.whenCheckboxChanged(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return returnData;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocupaypl/keyListVal", method = RequestMethod.POST)
	public OffenderPaymentPlan keyListVal(@RequestBody final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan plan = new OffenderPaymentPlan();
		try {
			plan = ocupayplService.keyListVal(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return plan;
	}

	/**
	 * When validate item
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/printPlan", method = RequestMethod.POST)
	public ReportBean printPlan(@RequestBody final OffenderPaymentPlan searchBean) {
		ReportBean returnData = new ReportBean();
		try {
			returnData = ocupayplService.printPlan(searchBean);
		} catch (Exception e) {
			final OffenderPaymentPlan bean = new OffenderPaymentPlan();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
		}
		return returnData;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocupaypl/gettingGroupId", method = RequestMethod.POST)
	public Long gettingGroupId(@RequestBody final OffenderPaymentPlan offenderPaymentPlan) {
		Long returnData = null;
		try {
			returnData = ocupayplService.gettingGroupId(offenderPaymentPlan.getOffenderId(), offenderPaymentPlan.getInformationNumber(),offenderPaymentPlan.getCaseLoadId());
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnData;
	}

}