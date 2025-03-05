package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Repository;
import net.syscon.s4.triggers.OffenderBeneficiariesT2Service;

@Service
public class OffenderBeneficiariesT2ServiceImpl extends BaseBusiness implements OffenderBeneficiariesT2Service {

	@Autowired
	private OffenderBeneficiariesT2Repository repository;

	@Override
	public OffenderBeneficiaries offenderBeneficiariesT2Trigger(OffenderBeneficiaries obj, String opearionType) {
		BigDecimal lvMaxTotalAmount = null;
		BigDecimal lvMaxMonthlyAmount = null;
		String existFlag = null;
		BigDecimal lvMaxRecursiveAmount = null;

		OffenderDeductions offded = repository.dedAmountType(obj.getOffenderDeductionId());
		if (offded != null) {
			lvMaxTotalAmount = offded.getMaxTotalAmount();
			lvMaxMonthlyAmount = offded.getMaxMonthlyAmount();
			lvMaxRecursiveAmount = offded.getMaxRecursiveAmount();
		}

		if (opearionType.equalsIgnoreCase("INSERT")) {
			if (lvMaxMonthlyAmount != null) {
				obj.setMonthlyAmount(obj.getAmount());
				obj.setAmount(new BigDecimal(999999999.99));
				obj.setRecursiveAmount(null);
			} else if (lvMaxRecursiveAmount != null) {
				obj.setRecursiveAmount(obj.getAmount());
				obj.setAmount(new BigDecimal(999999999.99));
				obj.setMonthlyAmount(null);
			} else {
				obj.setMonthlyAmount(null);
				obj.setRecursiveAmount(null);
			}
		} else if (opearionType.equalsIgnoreCase("UPDATE")) {
			if (lvMaxMonthlyAmount != null) {
				obj.setMonthlyAmount(obj.getAmount());
				obj.setAmount(new BigDecimal(999999999.99));
				obj.setRecursiveAmount(null);
			} else if (lvMaxRecursiveAmount != null) {
				obj.setRecursiveAmount(obj.getAmount());
				obj.setAmount(new BigDecimal(999999999.99));
				obj.setMonthlyAmount(null);
			} else {
				obj.setMonthlyAmount(null);
				obj.setRecursiveAmount(null);
			}
		}

		return obj;
	}

}
