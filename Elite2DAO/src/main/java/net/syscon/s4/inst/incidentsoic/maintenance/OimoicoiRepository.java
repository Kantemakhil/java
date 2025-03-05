package net.syscon.s4.inst.incidentsoic.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
/**
 * Interface OimoicoiRepository
 */
public interface OimoicoiRepository {
	OmsModules createFormGlobals(OmsModules paramBean);

	List<ReferenceCodes> rgOicOffenceIndicatorsRecordGroup() ;

	Integer oicOffenceIndicatorsUpdateOicOffenceIndicators(List<OicOffenceIndicators> lstOicOffenceIndicators) ;

	List<OicOffences> oicOfnExecuteQuery(OicOffences objOicOffences) ;

	Integer oicOfnInsertOicOffences(List<OicOffences> lstOicOffences) ;

	List<ReferenceCodes> rgOicOffenceCategRecordGroup() ;

	List<OicOffenceIndicators> oicOffenceIndicatorsExecuteQuery(OicOffenceIndicators objOicOffenceIndicators) ;

	Integer oicOffenceIndicatorsDeleteOicOffenceIndicators(List<OicOffenceIndicators> lstOicOffenceIndicators) ;

	Integer oicOffenceIndicatorsPreInsert();

	List<ReferenceCodes> rgOicOffenceTypeRecordGroup() ;

	Integer oicOffenceIndicatorsInsertOicOffenceIndicators(List<OicOffenceIndicators> lstOicOffenceIndicators) ;

	Integer oicOfnUpdateOicOffences(List<OicOffences> lstOicOffences) ;
	
	Integer getNextOicOffenceId();
	
	Integer checkOffenseCodeExist(OicOffences object);
	
	List<OicOffences> oicOfnCheckoverLapping(OicOffences objSearchDao);

}
