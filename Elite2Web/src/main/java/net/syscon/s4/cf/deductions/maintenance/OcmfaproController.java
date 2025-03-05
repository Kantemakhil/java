package net.syscon.s4.cf.deductions.maintenance;

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

import net.syscon.s4.cf.deductions.beans.OcmfaproCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

@EliteController
public class OcmfaproController {
	@Autowired
	private OcmfaproService ocmfaproService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmfaproController.class.getName());

	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/freeActTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> freeActTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.feeActTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/locationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> locationRecordGroup(@RequestParam(value = "agyLocType") final String agyLocType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.locationRecordGroup(agyLocType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/creditDedToRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> creditDedToRecordGroup(@RequestParam("caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.creditDedToRecordGroup(caseloadType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/frequencyRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> frequencyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.frequencyRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/codeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> codeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.codeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * getting cgfkGrpObDeductionType LOV values
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmfapro/reciptTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> reciptTypeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmfaproService.reciptTypeRecordGroup(caseloadType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmfapro/cgfkchkCsldDbenCsldDben", method = RequestMethod.GET)
	public @ResponseBody Persons cgfkchkCsldDbenCsldDben(@RequestParam(value = "personId") final Long personId) {
		Persons result = new Persons();
		try {
			result = ocmfaproService.cgfkchkCsldDbenCsldDben(personId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OtmfoproController cgfkchkCsldDbenCsldDben  unable to call the service", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmfapro/cgfkchkCsldDbenCsldDbenCorp", method = RequestMethod.GET)
	public @ResponseBody Corporates cgfkchkCsldDbenCsldDbenC(
			@RequestParam(value = "corporateId") final BigDecimal corporateId) {
		Corporates result = new Corporates();
		try {
			result = ocmfaproService.cgfkchkCsldDbenCsldDben(corporateId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OtmfoproController cgfkchkCsldDbenCsldDbenC  unable to call the service", e);
		}
		return result;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/caseloadDedProfExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(@RequestBody final CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmfaproService.caseloadDedProfExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception :",e);
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
	@RequestMapping(value = "/ocmfapro/csldDpCommit", method = RequestMethod.POST)
	public @ResponseBody
	Integer csldDpCommit(@RequestBody final CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocmfaproService.csldDpCommit(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("OMS_OWNER.DEDUCTION_PROFILES_PK")) {
				liReturn = 2;
			}
			logger.error("Exception", e.getMessage());
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/caseloadDedBenficExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(@RequestBody final CaseloadDedBeneficiaries searchBean) {
		List<CaseloadDedBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocmfaproService.caseloadDedbenficExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDedBeneficiaries bean = new CaseloadDedBeneficiaries();
			logger.error("Exception :",e);
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
	@RequestMapping(value = "/ocmfapro/csldDbCommit", method = RequestMethod.POST)
	public @ResponseBody
	Integer csldDbCommit(@RequestBody final CaseloadDedBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocmfaproService.csldDbCommit(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("OMS_OWNER.CSLD_DBEN_UK1")) {
				liReturn = 2;
			}
			if(e.getMessage().contains("benetotisnteqal")){
				return 101;
			}
			logger.error("Exception", e.getMessage());
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/caseloadDedDetExecuteQuery", method=RequestMethod.POST)
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(@RequestBody final CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = ocmfaproService.caseloadDedDetExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionDetails bean = new CaseloadDeductionDetails();
			logger.error("Exception :",e);
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
	@RequestMapping(value = "/ocmfapro/csldDdCommit", method = RequestMethod.POST)
	public @ResponseBody
	Integer csldDdCommit(@RequestBody final CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocmfaproService.csldDdCommit(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("OMS_OWNER.CSLD_DD_PK")) {
				liReturn = 2;
			}
			logger.error("Exception", e.getMessage());
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmfapro/singleCommit", method = RequestMethod.POST)
	public @ResponseBody
	String singleCommit(@RequestBody final OcmfaproCommitBean commitBean) {
		String liReturn = "";
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmfaproService.singleCommit(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("deduction_profiles_pk")) {
				liReturn = "2";
			}
			else if (e.getMessage().contains("offAssigned")) {
				liReturn = "102";
			}
			else if (e.getMessage().contains("csld_dben_uk1")) {
				liReturn = "22";
			}
			else if(e.getMessage().contains("benetotisnteqal")){
				liReturn =  "101";
			}
			else if (e.getMessage().contains("csld_dd_pk")) {
				liReturn = "222";
			}
			else {
				liReturn = "404";
			}
			logger.error("Exception", e.getMessage());
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/sysPflExecuteQuery", method=RequestMethod.GET)
	public List<SystemProfiles> sysPflExecuteQuery() {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmfaproService.sysPflExecuteQuery();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/sysPfl2ExecuteQuery", method=RequestMethod.GET)
	public List<SystemProfiles> sysPfl2ExecuteQuery() {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocmfaproService.sysPfl2ExecuteQuery();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocmfapro/calculateOn", method=RequestMethod.GET)
	public String calculateOn(@RequestParam(value = "deductionType") final String deductionType) {
		String returnVal = null;
		try {
			returnVal = ocmfaproService.calculateOn(deductionType);
		} catch (Exception e) {
			logger.error("Exception :",e);
		}
		return returnVal;
	}
}
