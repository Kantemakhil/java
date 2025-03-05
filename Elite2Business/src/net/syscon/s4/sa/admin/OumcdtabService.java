package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;
import net.syscon.s4.sa.admin.beans.CopyTablesCommitBean;

/**
 * Interface OumcdtabService
 */
public interface OumcdtabService {
	List<AllTabColumns> lovColumnNameRecordGroup(String tableName);

	  List<CopyTables> modifyTabExecuteQuery(CopyTables objCopyTables);

	List<AllTabColumns> lovSeqNameRecordGroup();

	List<AllTabColumns> lovParentTableRecordGroup();

	List<CopyTables> modifyTabCommit(CopyTablesCommitBean commitBean);

	List<ReferenceCodes> cgfkModifyTabMovementTypeRecordGroup();

	List<MovementReasons> cgfkModifyTabMovementReasoRecordGroup(String movementType);

	List<AllTabColumns> lovTableNameRecordGroup();

}
