package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

/**
 * Interface OimlegstRepository
 */
public interface OimlegstRepository {
	List<ReferenceCodes> rgStatRecordGroup();

	Integer updateReasonsInsertLegalUpdateReasons(List<LegalUpdateReasons> paremBean);

	List<ReferenceCodes> rgCatRecordGroup();

	Integer updateReasonsDeleteLegalUpdateReasons(List<LegalUpdateReasons> paremBean);

	List<LegalUpdateReasons> updateReasonsExecuteQuery(LegalUpdateReasons paremBean);

	Integer updateReasonsUpdateLegalUpdateReasons(List<LegalUpdateReasons> paremBean);

	LegalUpdateReasons getNbtReasonCategory(LegalUpdateReasons legalUpdateReason);

	LegalUpdateReasons getNbtActiveType(LegalUpdateReasons legalUpdateReason);

	List<TableColumnNameBean> getTableColumNames();

	Integer senCalcKeyDelrec(final String tableName, final String columnName, final String updateReasonCode);

}
