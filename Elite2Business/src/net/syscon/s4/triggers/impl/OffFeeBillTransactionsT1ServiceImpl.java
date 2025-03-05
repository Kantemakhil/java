package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;

@Service
public class OffFeeBillTransactionsT1ServiceImpl implements OffFeeBillTransactionsT1Service {

	@Autowired
	private CommonService commonService;
	
	final private String AR = "AR";
	final private String LD_PP = "LD_PP";
	final private String LD_EXI = "LD_EXI";

	@Override
	public void offFeeBillTransactionsT1(OffFeeBillTransactions newBean) throws Exception {
		String flag=commonService.billStatementExists(newBean.getBillId());
		if ("Y".equals(flag)) {
			if ((AR.equalsIgnoreCase(newBean.getBillStatus()) || LD_PP.equalsIgnoreCase(newBean.getBillStatus())
					|| LD_EXI.equalsIgnoreCase(newBean.getBillStatus())) && newBean.getBillAgingStartDate() == null) {
				throw new Exception(
						"Bill aging start date is mandatory for bills that have the statement generated and status AR, LD_PP, LD_EXI");
			}

			if ((AR.equalsIgnoreCase(newBean.getBillStatus())
					|| LD_PP.equalsIgnoreCase(newBean.getBillStatus()) && newBean.getBillAgingEndDate() == null)) {
				throw new Exception(
						"Bill aging end date is mandatory for bills that have the statement generated and status AR, LD_PP, LD_EXI");
			}

			if (newBean.getBillAgingStartDate() != null || newBean.getBillAgingEndDate() != null) {
				throw new Exception(
						"Bill aging start date and end date are not avalailable for bills that did not have the statement generated yet");
			}
		}
	}

}
