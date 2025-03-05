package net.syscon.s4.inst.offenderissuestracking.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.GrievanceReasons;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;

/**
 * Interface OimissueRepository
 */
public interface OimissueRepository {

	Integer grievanceTypesInsertGrievanceTypes(List<GrievanceTypes> lstGrievanceTypes);

	Integer grievanceTxnsInsertGrievanceTxns(List<GrievanceTxns> lstGrievanceTxns);

	List<GrievanceReasons> grievanceTypesOnCheckDeleteMaster(GrievanceReasons paramBean);

	List<GrievanceTxns> grievanceTypesOnCheckDeleteMaster(GrievanceTxns paramBean);

	Integer grievanceTypesUpdateGrievanceTypes(List<GrievanceTypes> lstGrievanceTypes);

	Integer grievanceReasonsUpdateGrievanceReasons(List<GrievanceReasons> object);

	Integer grievanceReasonsDeleteGrievanceReasons(List<GrievanceReasons> object);

	Integer grievanceTxnsDeleteGrievanceTxns(List<GrievanceTxns> lstGrievanceTxns);

	List<GrievanceTypes> grievanceTypesExecuteQuery(GrievanceTypes objGrievanceTypes);

	List<GrievanceTxns> grievanceTxnsExecuteQuery(GrievanceTxns objGrievanceTxns);

	List<GrievanceReasons> grievanceReasonsExecuteQuery(GrievanceReasons object);

	Integer grievanceTxnsUpdateGrievanceTxns(List<GrievanceTxns> lstGrievanceTxns);

	Integer grievanceReasonsInsertGrievanceReasons(List<GrievanceReasons> object);

	Integer cgrichkMovementReasonsScheduleCheck(GrievanceTxns paramBean);

	Integer onDeleteReasons(final GrievanceReasons grievenceTxns);

	String getTabSecuityEnable(String inparam);

}
