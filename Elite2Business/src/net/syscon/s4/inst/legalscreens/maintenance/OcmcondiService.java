package  net.syscon.s4.inst.legalscreens.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.bean.CommunityConditionsCommitBean;
/**
 * Interface OcmcondiService 
 */
public interface OcmcondiService  {
	List<CommunityConditions> comCondCommit(CommunityConditionsCommitBean commitBean) ;

	List<ReferenceCodes> rgCatRecordGroup() ;

	List<ReferenceCodes> rgUnitRecordGroup() ;

	List<ReferenceCodes> rgTypeRecordGroup() ;

	List<ReferenceCodes> rgFunctionTypeRecordGroup() ;

	List<CommunityConditions> comCondExecuteQuery(CommunityConditions object) ;

	List<ReferenceCodes> rgSvcOblRecordGroup() ;

	CommunityConditions getDeleteRecordOrNot(CommunityConditions searchRecord);

	List<CommunityConditions> comCondFilteredData(OffenderSentConditions searchRecord);
}
