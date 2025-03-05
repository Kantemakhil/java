package net.syscon.s4.pkgs.ocdpayob.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.pkgs.ocdpayob.OcdpayobPkgService;

@Service
public class OcdpayobPkgServiceImpl implements OcdpayobPkgService {

	private static final String OCDPAYOB = "OCDPAYOB";
	private final Logger logger = LogManager.getLogger(OcdpayobPkgServiceImpl.class.getName());

	@Override
	public String setDefaultWhereOffBnc(final OffenderBeneficiaries obj) {
		String lvSubWhere = null;
		String lvDefWhere = null;
		try {
			lvDefWhere = "offender_deduction_id in ( select vod.offender_deduction_id from offender_deduction_receipts odr, deduction_types dt, account_codes ac, v_offender_deductions vod left outer join offender_mon_deductions omd on (vod.offender_deduction_id = omd.offender_deduction_id) where vod.profile_caseload_id = :caseload_id and odr.offender_deduction_id = vod.offender_deduction_id /*--Commented by Anurag Ref HPQC#16684 AND vod.adjustment_reason_code IS NULL*/ and date_trunc('day', vod.effective_date) <= date_trunc('day', localtimestamp) and coalesce(vod.delay_recapture, 0) * interval '1 day' <= date_trunc('day', localtimestamp) - date_trunc('day', vod.effective_date) and vod.deduction_status = 'A' and vod.account_code = ac.account_code and ac.caseload_type = caseload_type and vod.deduction_type = dt.deduction_type and dt.deduction_category not in ( 'ALCN', 'DTF', 'CTF' ) and dt.caseload_code in ( 'BOTH', caseload_type ) and ( ( (vod.max_monthly_amount is not null and vod.max_monthly_amount::text <> '') and ( omd.monthly_deduction_date = ( select MAX(omd4.monthly_deduction_date) from offender_mon_deductions omd4 where omd4.offender_deduction_id = vod.offender_deduction_id ) or not exists ( select '1' from offender_mon_deductions omd4 where omd4.offender_deduction_id = vod.offender_deduction_id ) ) ) or ( coalesce(vod.max_monthly_amount::text, '') = '' and coalesce(vod.max_recursive_amount::text, '') = '' ) or ( (vod.max_recursive_amount is not null and vod.max_recursive_amount::text <> '') and ( not exists ( select '1' from offender_mon_deductions omd1 where omd1.offender_deduction_id = vod.offender_deduction_id ) or exists ( select '1' from offender_mon_deductions omd1 where omd1.offender_deduction_id = vod.offender_deduction_id and omd1.monthly_deduction_date = ( select MAX(omd2.monthly_deduction_date) from offender_mon_deductions omd2 where omd2.offender_deduction_id = omd1.offender_deduction_id ) ) and omd.offender_deduction_id = vod.offender_deduction_id and omd.monthly_deduction_date = ( select MAX(omd3.monthly_deduction_date) from offender_mon_deductions omd3 where omd3.offender_deduction_id = omd.offender_deduction_id ) ) ) ) and (( coalesce(vod.deduction_amount, 0) + coalesce(vod.adjustment_amount, 0) < coalesce(vod.max_total_amount, 0) or coalesce(vod.max_total_amount::text, '') = '' )/*End added by Anurag Ref HPQC#16684*/ or coalesce(vod.max_monthly_amount, 0) > coalesce(( select SUM(case when bt.txn_post_usage = 'CR' then bt.txn_entry_amount else - bt.txn_entry_amount end ) from beneficiary_transactions bt where bt.offender_deduction_id = vod.offender_deduction_id and bt.txn_entry_date between date_trunc('month', localtimestamp) and date_trunc('month', localtimestamp) + '1 month'::interval - 1 * interval '1 day' ), 0) or coalesce(vod.max_recursive_amount, 0) > coalesce(omd.deduction_amount, 0) or vod.unlimited_deduction = 'Y' ) and vod.offender_id = :root_offender_id and odr.receipt_txn_type in ( select txn_type from transaction_operations where module_name = 'OCDPAYOB' and caseload_id = :caseload_id ) ";
			if (obj.getInformationNumber() != null) {
				lvDefWhere = lvDefWhere + "AND vod.information_number = :ovr_information_number";
			}
			if (obj.getModuleName()!= null?obj.getModuleName().equals(OCDPAYOB):false) {
				lvSubWhere = "AND vod.offender_id = :root_offender_id AND odr.receipt_txn_type IN (SELECT txn_type FROM transaction_operations WHERE module_name ='OCDPAYOB' and caseload_id = :caseload_id))";
			} else {
				lvSubWhere = "AND vod.offender_id = :OVR_OFFENDER_ID  AND odr.receipt_txn_type = :receipt_txn_type )";
			}
		} catch (Exception e) {
			logger.error("setDefaultWhereOffBnc :" + e);
		}
		return lvDefWhere + lvSubWhere;
	}
}
