package net.syscon.s4.pkgs.omkcopy;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;

public interface OmkcopyRepository {

	List<CopyTables> copyTablesCur(final String pMoveType, final String pMoveReason, final String vParent);

	List<AllTabColumns> columnListCur(final String pTableName);

	String getWrongColumnNameFlag(final String pTableName);

	List<CopyTables> getColSeqNames(final String columnName, final String vMoveType, final String vMoveReason);

	Integer getVpkColCount(final String columnName, final String pTableName);

	Integer vSelectStringOne(final String columnName, final String pParentTable, final Long vOldBookId,
			final Long vNewBookId);

	Integer vSelectStringTwo(final String columnName, final String pParentTable, final Long vOldBookId,
			final Long vNewBookId);

	void insertSqlOne(final String vInsColList, final String vSelColList, final String lvActiveOnly,
			final String pTableName, final Long vNewBookId, final Long vOldBookId);

	void insertSqlTwo(final String vInsColList, final String vSelColList, final String lvActiveOnly,
			final String pTableName, final Long vNewBookId, final Long vOldBookId);

	List<WorkFlows> workFlowCur(final Long pOldBookId);

	Long getIdCur();

	void insertWorkFlows(final Long lvWorkFlowId, final String objectCode, final Long pNewBookId,
			final BigDecimal objectSeq, final String userName);

	void insertworkFlowLogs(final Long lvWorkFlowId, final String userName);

}
