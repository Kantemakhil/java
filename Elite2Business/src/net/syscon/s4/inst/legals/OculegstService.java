package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.UpdateReason;
import net.syscon.s4.inst.legals.beans.UpdateUser;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.StatusUpdate;

public interface OculegstService {
	List<UpdateReason> getUpdateCaseReason();
	
	List<Sentences> populateUpdateReason(String category, String sentenceCalcType);

	UpdateUser getUpdateUser(String name);

	List<UpdateReason> getUpdateConditionReason();

}