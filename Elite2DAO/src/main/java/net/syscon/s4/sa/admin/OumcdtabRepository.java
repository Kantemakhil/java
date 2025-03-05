package net.syscon.s4.sa.admin;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;
/**
 * Interface OumcdtabRepository
 */
public interface OumcdtabRepository {
	List<AllTabColumns> lovColumnNameRecordGroup(String tableName) ;

	 List<CopyTables> modifyTabExecuteQuery(CopyTables objCopyTables) ;

	Integer modifyTabInsertCopyTables(List<CopyTables> lstCopyTables) ;

	List<AllTabColumns> lovSeqNameRecordGroup() ;

	Integer modifyTabUpdateCopyTables(List<CopyTables> lstCopyTables) ;

	List<AllTabColumns> lovParentTableRecordGroup() ;

	Integer modifyTabDeleteCopyTables(List<CopyTables> lstCopyTables) ;

	List<ReferenceCodes> cgfkModifyTabMovementTypeRecordGroup() ;

	List<MovementReasons> cgfkModifyTabMovementReasoRecordGroup(String movementType) ;

	List<AllTabColumns> lovTableNameRecordGroup() ;

}
