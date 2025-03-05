package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.Charges;
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistory;

public interface OidhwdetRepository {

	List<HoldWarrentDetainer> searchHoldWarrentDetainer(Long offenderBookId);
	
	List<Charges> searchCharges(Long hwdId);
	
	Integer insertHwDetdata(List<HoldWarrentDetainer> insertedRecord);

	Integer updateHwDetdata(List<HoldWarrentDetainer> updatedRecords);

	Integer insertHwdetCharges(List<Charges> insertHwdetChargesList);

	Integer updateHwDetCharges(List<Charges> updatedHwdetChargesList);

	Integer deleteHwdetCharges(List<Charges> deletedHwdetChargesList);
	
	List<HoldsWarantsHistory> populateHistory(Long hwdId);

	int insertHistoryRecord(List<HoldsWarantsHistory> insertList);

	int updateHistoryRecord(List<HoldsWarantsHistory> updateList);

	int deleteHistoryRecord(List<HoldsWarantsHistory> deleteList);

	int deleteHwDetdata(List<HoldWarrentDetainer> deleteList);
}
