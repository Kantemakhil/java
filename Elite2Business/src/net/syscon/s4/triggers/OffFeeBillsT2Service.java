package net.syscon.s4.triggers;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;

public interface OffFeeBillsT2Service {
	
	String offFeeBillsT2(OffFeeBillTransactions newBean, OffFeeBills oldBean) throws Exception;

	
	

}
