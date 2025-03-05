package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
/**
 * Interface OcmcondiRepository
 */
public interface OcmcondiRepository {
	CommunityConditions comCondDeleteCommunityConditions(List<CommunityConditions> object) ;

	List<ReferenceCodes> rgCatRecordGroup() ;

	Integer comCondUpdateCommunityConditions(List<CommunityConditions> object) ;

	Integer comcondInsertCommunityConditions(List<CommunityConditions> object) ;

	List<ReferenceCodes> rgUnitRecordGroup() ;

	List<ReferenceCodes> rgTypeRecordGroup() ;

	List<ReferenceCodes> rgFunctionTypeRecordGroup() ;

	List<CommunityConditions> comCondExecuteQuery(CommunityConditions object) ;

	List<ReferenceCodes> rgSvcOblRecordGroup() ;

	List<TableColumnNameBean> getTableColumNames();

	Integer getDeleteRecordOrNot(CommunityConditions searchRecord,String tableName);

	List<CommunityConditions> comCondFilteredData(OffenderSentConditions objSearchDao);

}
