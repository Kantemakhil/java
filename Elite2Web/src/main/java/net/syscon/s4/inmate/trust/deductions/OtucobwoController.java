package net.syscon.s4.inmate.trust.deductions;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OtucobwoController {
	@Autowired
	private OtucobwoService otucobwoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtucobwoController.class.getName());

	/**
	 * getting cgfkCobwoAdjustmentReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otucobwo/cgfkCobwoAdjustmentReasoRecordGroup", method = RequestMethod.GET)
	public List<TransactionOperation> cgfkCobwoAdjustmentReasoRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<TransactionOperation> recordList = new ArrayList<TransactionOperation>();
		try {
			recordList = otucobwoService.cgfkCobwoAdjustmentReasoRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	} 
	
	/**
	 * getting cgfkCobwoAdjustmentReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otucobwo/offenderDeductionCur", method = RequestMethod.GET)
	public List<OffenderDeductions> offenderDeductionCur(@RequestParam(value = "deductionId") final Long deductionId) {
		List<OffenderDeductions> recordList = new ArrayList<OffenderDeductions>();
		try {
			recordList = otucobwoService.offenderDeductionCur(deductionId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	} 
	/**
	 * getting cgfkCobwoAdjustmentReaso LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otucobwo/save", method = RequestMethod.POST)
	public List<OffenderAdjustmentTxns> save(@RequestBody final OffenderAdjustmentTxns bean ) {
		List<OffenderAdjustmentTxns> recordList = new ArrayList<OffenderAdjustmentTxns>();
		try {
			recordList = otucobwoService.save(bean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	} 
}