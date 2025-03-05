package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.Charges;
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistory;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistoryCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetChargesCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetCommitBean;

public interface OidhwdetService {
	
	List<HoldWarrentDetainer> searchHoldWarrentDetainer(Long offenderBookId);
	
	List<Charges> searchCharges(Long hwdId);
	
	Integer insertUpdateDeleteCourtReport(HwdetCommitBean hwDetCommitBean);
	
	Integer insertUpdateDeleteHwdetCharges(HwdetChargesCommitBean hwdetChargesCommitBean);

	List<HoldsWarantsHistory> populateHistory(Long hwdId);

	int updateHwdetHistory(HoldsWarantsHistoryCommitBean historyCommitBean);
	
	
}
