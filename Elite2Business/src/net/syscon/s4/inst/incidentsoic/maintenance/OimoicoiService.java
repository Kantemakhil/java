package net.syscon.s4.inst.incidentsoic.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
/**
 * Interface OimoicoiService 
 */
public interface OimoicoiService  {
	List<ReferenceCodes> rgOicOffenceIndicatorsRecordGroup() ;

	List<OicOffences> oicOfnExecuteQuery(OicOffences objOicOffences) ;

	List<OicOffences> oicOfnCommit(OicOffencesCommitBean commitBean) ;

	List<ReferenceCodes> rgOicOffenceCategRecordGroup() ;

	Object oicOffenceIndicatorsPreInsert()  ;

	List<OicOffenceIndicators> oicOffenceIndicatorsExecuteQuery(OicOffenceIndicators objOicOffenceIndicators) ;

	Integer oicOffenceIndicatorsCommit(OicOffenceIndicatorsCommitBean commitBean) ;

	List<ReferenceCodes> rgOicOffenceTypeRecordGroup() ;

	OicOffences oicOfnCheckoverLapping(OicOffences objSearchDao);
}
