package net.syscon.s4.pkgs.merge_offender_core;

import java.util.List;

import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.sa.recordmaintenance.MergeOffenderTables;

public interface MergeOffenderCoreRepository {

	List<MergeOffenderTables> mergeTableListCurser(String applnCode);
	
	List<MergeOffenderTables> mergeTableRelatedData(String tableName);
	
	String extraPkColumn(String tableName);
	
	Integer insertMergeOffenderSqls(String pSqlDml);
	
	OffenderFingerprints getOffFingerprintsDet(String pSqlDml);
	
	Integer updateOffFingrPrnt(String pSqlDml);
	
	Long getLvMaxSeq(String query);
	

}
