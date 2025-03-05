package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.HoldStatus;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.OrderType;

public interface OcuholdsService { 

	List<Holds> populateHoldsData(Integer eventId);

	List<OrderType> orderType();

	List<HoldStatus> populateHoldStatus();

	List<Court> populateCourtData();

	Integer updateHoldData(String userId, HoldsCommitBean holdsBeanCommit);

}
