package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.PriorHistory;;

public interface OidojoinRepository {
	 List<PriorHistory> populateGridData(CourtCases offenderCases);
	 List<BailStatus> populateOrderType();
	 List<BailStatus> populateStatus();
	 List<BailStatus> populateSource();
	 List<BailStatus> populateCounty();
	 List<BailStatus> populateState();
	 Integer insertPriorHistoryData(List<PriorHistory> insertList);
	 Integer updatePriorHistoryData(List<PriorHistory> updateList);
	 Integer deletePriorHistoryData(List<PriorHistory> deleteList);
	 Integer getPreInsertOffenseSeq(Long offenderBookId);
	 
}
