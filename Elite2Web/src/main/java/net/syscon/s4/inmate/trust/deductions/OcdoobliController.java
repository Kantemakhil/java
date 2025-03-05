package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

@EliteController
public class OcdoobliController {
	@Autowired
	private OcdoobliService ocdoobliService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdoobliController.class.getName());

	/**
	 * getting cgfkOffDedDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffDedDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup() {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdoobliService.cgfkOffDedDeductionTypeRecordGroup(userName);
		} catch (final Exception e) {
			logger.error("cgfkOffDedDeductionTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkGroupOblGroupId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkGroupOblGroupIdRecordGroup", method = RequestMethod.GET)
	public List<ObligationGroups> cgfkGroupOblGroupIdRecordGroup(@RequestParam("deductionType") final String deductionType) {
		List<ObligationGroups> recordList = new ArrayList<ObligationGroups>();
		try {
			recordList = ocdoobliService.cgfkGroupOblGroupIdRecordGroup(deductionType);
		} catch (final Exception e) {
			logger.error("cgfkGroupOblGroupIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDedDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffDedDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoobliService.cgfkOffDedDspDescriptionRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkOffDedDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDed1AdjustmentReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffDed1AdjustmentReasoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffDed1AdjustmentReasoRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdoobliService.cgfkOffDed1AdjustmentReasoRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkOffDed1AdjustmentReasoRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDed1AdjustmentReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkchkOffDedOffDedRef", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkchkOffDedOffDedRef() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdoobliService.cgfkchkOffDedOffDedRef();
		} catch (final Exception e) {
			logger.error("cgfkOffDed1AdjustmentReasoRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDrReceiptTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffDrReceiptTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup() {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdoobliService.cgfkOffDrReceiptTxnTypeRecordGroup(userName);
		} catch (final Exception e) {
			logger.error("cgfkOffDrReceiptTxnTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffDedCaseInfoNumber LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffDedCaseInfoNumberRecordGroup", method = RequestMethod.GET)
	public List<OffenderCases> cgfkOffDedCaseInfoNumberRecordGroup() {
		List<OffenderCases> recordList = new ArrayList<OffenderCases>();
		try {
			recordList = ocdoobliService.cgfkOffDedCaseInfoNumberRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkOffDedCaseInfoNumberRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffBncPersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffBncPersonIdRecordGroup", method = RequestMethod.GET)
	public List<Persons> cgfkOffBncPersonIdRecordGroup() {
		List<Persons> recordList = new ArrayList<Persons>();
		try {
			recordList = ocdoobliService.cgfkOffBncPersonIdRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkOffBncPersonIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffBncCorporateId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkOffBncCorporateIdRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkOffBncCorporateIdRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = ocdoobliService.cgfkOffBncCorporateIdRecordGroup();
		} catch (final Exception e) {
			logger.error("cgfkOffBncCorporateIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdoobli/offDedCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offDedCommit(@RequestBody final OffenderDeductionsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdoobliService.offDedCommit(commitBean);
		} catch (final Exception e) {
			if(e.getMessage().contains("offender_deductions_u1")) {
				liReturn=10;
			}

			logger.error("offDedCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/offBncExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBeneficiaries> offBncExecuteQuery(@RequestBody final OffenderBeneficiaries searchBean) {
		List<OffenderBeneficiaries> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = ocdoobliService.offBncExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("offBncExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/cgfkchkOffDedOffDedCsld", method = RequestMethod.GET)
	public CaseloadDeductionProfiles cgfkchkOffDedOffDedCsld(
			@RequestParam("deductionType") final String deductionType) {
		CaseloadDeductionProfiles searchResult = new CaseloadDeductionProfiles();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchResult = ocdoobliService.cgfkchkOffDedOffDedCsld(deductionType,userName);
		} catch (final Exception e) {
			logger.error("cgfkchkOffDedOffDedCsld", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdoobli/offBncCommit", method = RequestMethod.POST)
	public @ResponseBody String offBncCommit(@RequestBody final OffenderBeneficiariesCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdoobliService.offBncCommit(commitBean);
		} catch(Exception e){
			liReturn = e.getMessage();
			logger.error("offBncCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/offDrExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductionReceipts> offDrExecuteQuery(@RequestBody final OffenderDeductionReceipts searchBean) {
		List<OffenderDeductionReceipts> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = ocdoobliService.offDrExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("offDrExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdoobli/offDrCommit", method = RequestMethod.POST)
	public @ResponseBody String offDrCommit(@RequestBody final OffenderDeductionReceiptsCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdoobliService.offDrCommit(commitBean);
		} catch(Exception e){
			liReturn = e.getMessage().toUpperCase();
			logger.error("offDrCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/offDedExecuteQuery", method = RequestMethod.POST)
	public List<OffenderDeductions> offDedExecuteQuery(@RequestBody final OffenderDeductions searchBean) {
		List<OffenderDeductions> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ocdoobliService.offDedExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("offDedExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdoobliService.sysPflExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdoobli/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdoobliService.sysPflCommit(commitBean);
		} catch (final Exception e) {

			logger.error("sysPflCommit", e);
		}
		return liReturn;
	}

	@RequestMapping(value = "/ocdoobli/gettCount", method = RequestMethod.GET)
	public Integer getTCount(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "deductionType") final String deductionType,
			@RequestParam(value = "deductionPriority") final String deductionPriority) {
		Integer tcount = 0;
		try {
			tcount = ocdoobliService.getTCount(offenderId, caseloadId, deductionType, deductionPriority);
		} catch (final Exception e) {
			logger.error("getTCount", e);
		}
		return tcount;
	}

	@RequestMapping(value = "/ocdoobli/checkCrTpe", method = RequestMethod.GET)
	public String checkCrTpe(@RequestParam(value = "deductionType") final String deductionType) {
		String checkCrtype = null;
		try {
			checkCrtype = ocdoobliService.checkCrTpe(deductionType);
		} catch (final Exception e) {
			logger.error("checkCrTpe", e);
		}
		return checkCrtype;
	}

	@RequestMapping(value = "/ocdoobli/cgfklkpOffDedCaseNumber", method = RequestMethod.GET)
	public Integer cgfklkpOffDedCaseNumber(@RequestParam(value = "rootOffenderId") final Long rootOffenderId,
			final String informationNumber) {
		Integer caseId = 0;
		try {

			caseId = ocdoobliService.cgfklkpOffDedCaseNumber(rootOffenderId, informationNumber);
		} catch (final Exception e) {
			logger.error("cgfklkpOffDedCaseNumber", e);
		}

		return caseId;

	}

	@RequestMapping(value = "/ocdoobli/setJsCondition", method = RequestMethod.GET)
	public Integer setJsCondition(@RequestParam(value = "caseId") final Integer caseId) {
		Integer count = 0;
		try {
			count = ocdoobliService.setJsCondition(caseId);
		} catch (final Exception e) {
			logger.error("setJsCondition", e);
		}

		return count;

	}

	@RequestMapping(value = "/ocdoobli/omsUtilcomareDateTime", method = RequestMethod.GET)
	public Integer omsUtilcomareDateTime(@RequestParam(value = "effectiveDate") final String effectiveDate,
			@RequestParam(value = "dspEffectiveDate") final String dspEffectiveDate) {
		Integer count = 0;
		try {
			count = ocdoobliService.omsUtilcomareDateTime(effectiveDate, dspEffectiveDate);
		} catch (final Exception e) {
			logger.error("omsUtilcomareDateTime", e);
		}

		return count;

	}

	@RequestMapping(value = "/ocdoobli/displayErrorMessage", method = RequestMethod.GET)
	public String displayErrorMessage() {
		String msgText = null;
		try {
			msgText = ocdoobliService.displayErrorMessage();
		} catch (final Exception e) {
			logger.error("displayErrorMessage", e);
		}

		return msgText;

	}

	@RequestMapping(value = "/ocdoobli/profilePlanFlag", method = RequestMethod.GET)
	public String profilePlanFlag() {
		String msgText = null;
		try {
			msgText = ocdoobliService.profilePlanFlag();
		} catch (final Exception e) {
			logger.error("profilePlanFlag", e);
		}

		return msgText;

	}

	@RequestMapping(value = "/ocdoobli/getCheckOne", method = RequestMethod.GET)
	public Integer getCheckOne(@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "informationNumber") final String informationNumber,
			@RequestParam(value = "groupId") final BigDecimal groupId) {
		Integer caseId = 0;
		try {

			caseId = ocdoobliService.getCheckOne(offenderId, informationNumber, groupId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return caseId;

	}

	@RequestMapping(value = "/ocdoobli/getvsDamtCur", method = RequestMethod.GET)
	public OffenderDeductions getvsDamtCur(@RequestParam(value = "deductionId") final Integer deductionId) {
		OffenderDeductions bean = new OffenderDeductions();
		try {

			bean = ocdoobliService.getvsDamtCur(deductionId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return bean;

	}

	@RequestMapping(value = "/ocdoobli/getDeductionAmnt", method = RequestMethod.GET)
	public BigDecimal getDeductionAmnt(@RequestParam(value = "deductionId") final Integer deductionId) {
		BigDecimal bean = null;
		try {

			bean = ocdoobliService.getDeductionAmnt(deductionId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return bean;

	}

	@RequestMapping(value = "/ocdoobli/getLastFirstNames", method = RequestMethod.GET)
	public Persons getLastFirstNames(@RequestParam(value = "personId") final Long personId) {
		Persons bean = new Persons();
		try {

			bean = ocdoobliService.getLastFirstNames(personId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
			bean.setErrorMessage("ocdoobli.personnamenotfound");
		}

		return bean;

	}

	@RequestMapping(value = "/ocdoobli/getPerExists", method = RequestMethod.GET)
	public Integer getPerExists(@RequestParam(value = "deductionId") final Integer deductionId,
			@RequestParam(value = "personId") final Integer personId) {
		Integer count = 0;
		try {

			count = ocdoobliService.getPerExists(deductionId, personId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return count;

	}

	@RequestMapping(value = "/ocdoobli/updateBenficiaryTransactions", method = RequestMethod.GET)
	public Integer updateBenficiaryTransactions(@RequestBody final OffenderBeneficiaries updateBean) {
		Integer count = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			updateBean.setCreateUserId(userName);
			count = ocdoobliService.updateBenficiaryTransactions(updateBean);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return count;

	}

	@RequestMapping(value = "/ocdoobli/getCorpExists", method = RequestMethod.GET)
	public Integer getCorpExists(@RequestParam(value = "deductionId") final Integer deductionId,
			@RequestParam(value = "corporateId") final Integer corporateId) {
		Integer count = 0;
		try {

			count = ocdoobliService.getCorpExists(deductionId, corporateId);
		} catch (final Exception e) {
			logger.error("getCheckOne", e);
		}

		return count;

	}

	@RequestMapping(value = "/ocdoobli/getreciepttxnType", method = RequestMethod.GET)
	public List<OffenderDeductionReceipts> getreciepttxnType(@RequestBody final OffenderDeductionReceipts searchBean) {
		List<OffenderDeductionReceipts> returnList = null;
		try {

			returnList = ocdoobliService.getreciepttxnType(searchBean);
		} catch (final Exception e) {
			logger.error("getreciepttxnType", e);
		}

		return returnList;

	}

	@RequestMapping(value = "/ocdoobli/getMonths", method = RequestMethod.GET)
	public Integer getMonths(@RequestParam(value = "vEffDate") final Date vEffDate) {
		Integer mnths = null;
		try {

			mnths = ocdoobliService.getMonths(vEffDate);
		} catch (final Exception e) {
			logger.error("getreciepttxnType", e);
		}

		return mnths;

	}

	@RequestMapping(value = "/ocdoobli/updateOffenders", method = RequestMethod.GET)
	public Integer updateOffenders(@RequestParam(value = "oblFlg") final String oblFlg,
			@RequestParam(value = "rootOffenderId") final Long rootOffenderId) {
		Integer update = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			update = ocdoobliService.updateOffenders(oblFlg, userName, rootOffenderId);
		} catch (final Exception e) {
			logger.error("updateOffenders", e);
		}

		return update;

	}
	
	@RequestMapping(value = "/ocdoobli/getDescriptionforReciptType", method = RequestMethod.GET)
	public String getDescriptionforReciptType(@RequestParam(value = "code") final String code) {
		String desc = null;
		try {

			desc = ocdoobliService.getDescriptionforReciptType(code);
		} catch (final Exception e) {
			logger.error("getDesc", e);
		}

		return desc;

	}
	
	@RequestMapping(value = "/ocdoobli/cgfkchkOffDedOffDedT", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkchkOffDedOffDedT(@RequestParam(value = "deductionType") final String deductionType,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<DeductionTypes> returnList = null;
		try {

			returnList = ocdoobliService.cgfkchkOffDedOffDedT(deductionType,caseloadId);
		} catch (final Exception e) {
			logger.error("cgfkchkOffDedOffDedT", e);
		}

		return returnList;

	}
	@RequestMapping(value = "/ocdoobli/checkprevDedTxnAndCheckpreviousBenrcvied", method = RequestMethod.POST)
	public List<OffenderTransactions> checkprevDedTxnAndCheckpreviousBenrcvied(@RequestBody  final OffenderDeductions bean) {
		List<OffenderTransactions> returnList = null;
		try {

			returnList = ocdoobliService.checkprevDedTxnAndCheckpreviousBenrcvied(bean);
		} catch (final Exception e) {
			logger.error("cgfkchkOffDedOffDedT", e);
		}

		return returnList;

	}
	@RequestMapping(value = "/ocdoobli/getcorpName", method = RequestMethod.GET)
	public Corporates getCorporateName(@RequestParam(value = "corpId") final Long corpId) {
		Corporates returnList = null;
		try {

			returnList = ocdoobliService.getCorporateName(corpId);
		} catch (final Exception e) {
			logger.error("cgfkchkOffDedOffDedT", e);
		}

		return returnList;

	}
	@RequestMapping(value = "/ocdoobli/checkinformationandDeductionType", method = RequestMethod.GET)
	public Integer checkinformationandDeductionType(@RequestParam(value = "offId") final Integer offId,
			@RequestParam(value = "dedType") final String dedType,
			@RequestParam(value = "info") final String info) {
		Integer checkOne = null;
		try {

			checkOne = ocdoobliService.checkinformationandDeductionType(offId,dedType,info);
		} catch (final Exception e) {
			logger.error("cgfkchkOffDedOffDedT", e);
		}

		return checkOne;

	}
	
	/**
	 * getting cgfkGroupOblGroupId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdoobli/getDisabledButton", method = RequestMethod.GET)
	public String getDisabledButton(@RequestParam("parentId") final BigDecimal parentId) {
		String recordList = null;
		try {
			recordList = ocdoobliService.getDisabledButton(parentId);
		} catch (final Exception e) {
			logger.error("cgfkGroupOblGroupIdRecordGroup", e);
		}
		return recordList;
	}
	
}
