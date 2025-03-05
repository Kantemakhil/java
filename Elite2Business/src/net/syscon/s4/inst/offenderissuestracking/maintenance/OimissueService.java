package net.syscon.s4.inst.offenderissuestracking.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.GrievanceReasons;
import net.syscon.s4.im.beans.GrievanceReasonsCommitBean;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;

/**
 * Interface OimissueService
 */
public interface OimissueService {
	List<GrievanceReasons> grievanceTypesOnCheckDeleteMaster(GrievanceReasons paramBean);

	List<GrievanceTypes> grievanceTypesCommit(GrievanceTypesCommitBean commitBean);

	List<GrievanceReasons> grievanceReasonsCommit(GrievanceReasonsCommitBean commitBean);

	List<GrievanceTypes> grievanceTypesExecuteQuery(GrievanceTypes objGrievanceTypes);

	List<GrievanceTxns> grievanceTxnsExecuteQuery(GrievanceTxns objGrievanceTxns);

	List<GrievanceTxns> grievanceTxnsCommit(GrievanceTxnsCommitBean commitBean);

	List<GrievanceReasons> grievanceReasonsExecuteQuery(GrievanceReasons object);

	GrievanceTxns cgrichkMovementReasonsDeleteCheck(GrievanceTxns searchBean);

	GrievanceReasons onDeleteReasons(final GrievanceReasons paramBean);

	GrievanceTxns getTabSecuityEnable(final String userName);

}
