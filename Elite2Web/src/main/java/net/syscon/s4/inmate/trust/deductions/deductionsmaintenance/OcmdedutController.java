package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.DeductionTypesCommitBean;

@EliteController
public class OcmdedutController {
	@Autowired
	private OcmdedutService ocmdedutService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmdedutController.class.getName());

	/**
	 * getting cgfkDedTypeCaseloadCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/cgfkDedTypeCaseloadCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkDedTypeCaseloadCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmdedutService.cgfkDedTypeCaseloadCodeRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkDedTypeDeductionCatego LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/cgfkDedTypeDeductionCategoRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkDedTypeDeductionCategoRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmdedutService.cgfkDedTypeDeductionCategoRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkDedTypeFromBalanceTyp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/cgfkDedTypeFromBalanceTypRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkDedTypeFromBalanceTypRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmdedutService.cgfkDedTypeFromBalanceTypRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgParentDeductionType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/rgParentDeductionTypeRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> rgParentDeductionTypeRecordGroup(
			@RequestParam("deductionType") final String deductionType) {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = ocmdedutService.rgParentDeductionTypeRecordGroup(deductionType);
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/dedTypeExecuteQuery", method = RequestMethod.POST)
	public List<DeductionTypes> dedTypeExecuteQuery(@RequestBody final DeductionTypes searchBean) {
		List<DeductionTypes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmdedutService.dedTypeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
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
	@RequestMapping(value = "/ocmdedut/dedTypeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer dedTypeCommit(@RequestBody final DeductionTypesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocmdedutService.dedTypeCommit(commitBean);
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return liReturn;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/dedCodeValidation", method = RequestMethod.GET)
	public String dedCodeValidation(@RequestParam("dedCode") final String dedCode) {
		String recordList = null;
		try {
			recordList = ocmdedutService.dedCodeValidation(dedCode);
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmdedut/deleteDedTypeValidation", method = RequestMethod.GET)
	public Integer deleteDedTypeValidation(@RequestParam("dedCode") final String dedCode) {
		Integer recordList = null;
		try {
			recordList = ocmdedutService.deleteDedTypeValidation(dedCode);
		} catch (Exception e) {
			logger.error("deleteDedTypeValidation", e);
		}
		return recordList;
	}

}