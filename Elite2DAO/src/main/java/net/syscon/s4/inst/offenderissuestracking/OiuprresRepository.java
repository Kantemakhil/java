package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.im.beans.OffenderGrievanceTxns;

/**
 * Interface OiuprresRepository
 */
public interface OiuprresRepository {
	Integer prresInsertOffenderGrievanceTxns(List<OffenderGrievanceTxns> lstOffenderGrievanceTxns);

	List<OffenderGrievanceTxns> prresExecuteQuery(OffenderGrievanceTxns objOffenderGrievanceTxns);

	Integer prresUpdateOffenderGrievanceTxns(List<OffenderGrievanceTxns> updateList);

}
