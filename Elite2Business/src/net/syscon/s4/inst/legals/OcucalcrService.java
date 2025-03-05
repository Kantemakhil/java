package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;

public interface OcucalcrService {

	List<LovList> populateCalculationReasonList();

	String populateStaffName(String userName);

	String calExpDate(SentenceCalculation sentenceCalc);

	List<LovList> getStaffMembers();	
	
	

}
