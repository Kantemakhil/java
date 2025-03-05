package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;

public interface OcucalcrRepository {

	List<LovList> populateCalculationReasonList();

	Integer generateStaffId(String username);

	String populateStaffName(Integer staffId);

	String calExpDate(SentenceCalculation sentenceCalc);

	List<LovList> getStaffMembers();
	

	
}
