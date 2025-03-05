package net.syscon.s4.cf.deductions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfilesCommitBean;
import net.syscon.s4.cf.deductions.beans.OcmofaccCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * class OtdocfeeController
 *
 */
@EliteController
public class OcdofaccController {
	@Autowired
	private OcdofaccService ocdofaccService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdofaccController.class.getName());

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocdofacc/feeActTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> freeActTypeRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdofaccService.feeActTypeRecordGroup(caseloadId);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/caseloadDedProfExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(
			@RequestBody final CaseloadDeductionProfiles searchBean) {
		List<CaseloadDeductionProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.caseloadDedProfExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionProfiles bean = new CaseloadDeductionProfiles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/offDedExecuteQuery", method = RequestMethod.POST)
	public List<FeeAccountProfiles> offDedExecuteQuery(@RequestBody final FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.offDedExecuteQuery(searchObject);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}


	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/caseloadDedBenficExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDedBeneficiaries> caseloadDedBenficExecuteQuery(
			@RequestBody final CaseloadDedBeneficiaries searchBean) {
		List<CaseloadDedBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.caseloadDedbenficExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDedBeneficiaries bean = new CaseloadDedBeneficiaries();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/caseloadDedDetExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(
			@RequestBody final CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.caseloadDedDetExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionDetails bean = new CaseloadDeductionDetails();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/ocmofaccPersons", method = RequestMethod.GET)
	public @ResponseBody Persons ocmofaccPersons(@RequestParam(value = "personId") final Long personId) {
		Persons result = new Persons();
		try {
			result = ocdofaccService.ocmofaccPersons(personId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OcmofaccController ocmofaccPersons  unable to call the service", e);
		}
		return result;
	}


	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/ocmofaccCorporates", method = RequestMethod.GET)
	public @ResponseBody Corporates ocmofaccCorporates(@RequestParam(value = "corporateId") final BigDecimal corporateId) {
		Corporates result = new Corporates();
		try {
			result = ocdofaccService.ocmofaccCorporates(corporateId);
		} catch (Exception e) {
			result.setErrorMessage("Error : " + e.getMessage());
			logger.error("Exception : OcmofaccController ocmofaccCorporates  unable to call the service", e);
		}
		return result;
	}


	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocdofacc/reciptTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> reciptTypeRecordGroup(@RequestParam(value = "caseloadType") final String caseloadType) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdofaccService.reciptTypeRecordGroup(caseloadType);
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdofacc/ocmofaccCommit", method = RequestMethod.POST)
	public Integer ocmofaccCommit(@RequestBody final OcmofaccCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdofaccService.ocmofaccCommit(commitBean);
		} catch (Exception e) {

			if (e.getMessage().contains("OMS_OWNER.OFF_FEE_ACCOUNT_PROFILE_UK1")) {
				liReturn = 2;
			}
			if (e.getMessage().contains("OMS_OWNER.OFF_FEE_DED_BENEFICIARIES_UK1")) {
				liReturn = 22;
			}
			if(e.getMessage().contains("benetotisnteqal")){
				liReturn =  101;
			}
			if (e.getMessage().contains("OMS_OWNER.OFF_FEE_DED_RECEIPTS_UK1")) {
				liReturn = 222;
			}
			if (e.getMessage().contains("OMS_OWNER.OFF_FEE_ACCOUNT_OVERRIDES_FK1")) {
				liReturn = 223;
			}	
			if (e.getMessage().contains("OMS_OWNER.OFF_FEE_BILLS_FK1")) {
				liReturn = 224;
			}			
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/feeOverrideDetailsExecuteQuery", method = RequestMethod.GET)
	public Long feeOverrideDetailsExecuteQuery(@RequestParam(value = "feeId") final Long feeId) {
		Long retValue = null;
		try {
			retValue = ocdofaccService.feeOverrideDetailsExecuteQuery(feeId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return retValue;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/alAgyLocIdRgRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> alAgyLocIdRgRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = ocdofaccService.alAgyLocIdRgRecordGroup();
		} catch (Exception e) {
			logger.error("In alAgyLocIdRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/getlongSupvDate", method = RequestMethod.GET)
	public Map<String, Object> getlongSupvDate(@RequestParam(value = "caseloadId") final String caseloadId) {
		Map<String, Object> returnMap = new HashMap();
		try {
			returnMap = ocdofaccService.getlongSupvDate(caseloadId);
		} catch (Exception e) {
			logger.error("In getlongSupvDate method : ", e);
		}
		return returnMap;
	}



	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/caseloadFeeDedBenficExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDedBeneficiaries> caseloadFeeDedBenficExecuteQuery(
			@RequestBody final CaseloadDedBeneficiaries searchBean) {
		List<CaseloadDedBeneficiaries> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.caseloadFeeDedBenficExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDedBeneficiaries bean = new CaseloadDedBeneficiaries();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}



	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/caseloadFeeDetExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadDeductionDetails> caseloadFeeDetExecuteQuery(
			@RequestBody final CaseloadDeductionDetails searchBean) {
		List<CaseloadDeductionDetails> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.caseloadFeeDetExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadDeductionDetails bean = new CaseloadDeductionDetails();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdofacc/ocdofaccBenficiaryCommit", method = RequestMethod.POST)
	public Integer ocdofaccBenficiaryCommit(@RequestBody final CaseloadDedBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdofaccService.cslddbenCommitBean(commitBean, userName);
		} catch (Exception e) {

			if (e.getMessage().contains("OMS_OWNER.CSLD_DBEN_UK1")) {
				liReturn = 22;
			}
			if(e.getMessage().contains("benetotisnteqal")){
				liReturn =  101;
			}
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdofacc/ocdofaccDeductionCommit", method = RequestMethod.POST)
	public Integer ocdofaccDeductionCommit(@RequestBody final CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdofaccService.csldddCommitBean(commitBean, userName);
		} catch (Exception e) {

			if (e.getMessage().contains("OMS_OWNER.CSLD_DD_PK")) {
				liReturn = 222;
			}
			logger.error("Exception :", e);
		}
		return liReturn;
	}



	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdofacc/ocdofaccFeeAccountCommit", method = RequestMethod.POST)
	public Integer ocdofaccFeeAccountCommit(@RequestBody final FeeAccountProfilesCommitBean commitBean) {
		int liReturn = 0;
		String excludeFlag="N";
		try {
			FeeAccountProfiles longestSupvDate = new FeeAccountProfiles();
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdofaccService.offdedCommitBean(commitBean,longestSupvDate,excludeFlag,userName);
		} catch (Exception e) {

			if (e.getMessage().contains("OMS_OWNER.CSLD_DD_PK")) {
				liReturn = 222;
			}
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdofacc/offdedPreInsert", method = RequestMethod.GET)
	public BigDecimal offdedPreInsert() {
		BigDecimal liReturn = BigDecimal.ZERO;
		try {
			liReturn = ocdofaccService.offdedPreInsert();
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}


	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdofacc/offdedPrevExecteQuery", method = RequestMethod.POST)
	public List<FeeAccountProfiles> offdedPrevExecteQuery(@RequestBody final FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.offdedPrevExecteQuery(searchObject);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdofacc/sysPflExecuteQuery", method=RequestMethod.GET)
	public List<SystemProfiles> sysPflExecuteQuery() {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.sysPflExecuteQuery();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdofacc/sysLongSupPflExecuteQuery", method=RequestMethod.POST)
	public List<FeeAccountProfiles> sysLongSupPflExecuteQuery(@RequestBody final FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.sysLongSupPflExecuteQuery(searchObject);
		} catch (Exception e) {
			final FeeAccountProfiles bean = new FeeAccountProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@Scheduled(cron = "0 5 23 * *  ?")  //  cron = "0 0/1 * 1/1 * ?"
	public void updateStatusForLongSupvDate() {
		try {
			ocdofaccService.updateStatusForLongSupvDate();

		} catch (Exception e) {
			logger.error("Exception in updateStatusForLongSupvDate:",e);
		}
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdofacc/sysPflDedExecuteQuery", method=RequestMethod.GET)
	public List<SystemProfiles> sysPflDedExecuteQuery() {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdofaccService.sysPflDedExecuteQuery();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdofacc/getOffenderEventDate", method=RequestMethod.GET)
	public Date getOffenderEventDate(@RequestParam Long offenderBookId) {
		try {
			return ocdofaccService.getOffenderEventDate(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception :",e);
			return null;
		}
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="ocdofacc/offFeeBillsTrigger", method=RequestMethod.POST)
	public Map<String, Object> offFeeBillsTrigger(@RequestBody String feeBillsBean) {
		Map<String, Object> respone = new HashMap<String, Object>();
		try {
			respone = ocdofaccService.offFeeBillsTrigger(feeBillsBean);
		}
		catch (Exception e) {
			logger.error(this.getClass().getName()+" offFeeBillsTrigger method call "+e);
			respone.put("Response", e.getMessage());
		}
		return respone;
	}

}
