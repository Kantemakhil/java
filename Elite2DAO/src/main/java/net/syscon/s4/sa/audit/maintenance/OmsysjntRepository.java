package net.syscon.s4.sa.audit.maintenance;

import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

import java.util.List;

/**
 * Interface OmsysjntRepository
 */
public interface OmsysjntRepository {
	List<RecordGroup> tableRgRecordGroup();

	List<TableColumnNameBean> createTr();

	Integer createOneTr(String tableName);

	List<userTabColumns> colLenCur(String tableName);

	String checkSynonymCur(String tableNamejn);

	Integer createTable(String vDdlStmt);

	Integer synonyms(String synonym);

	Integer grantQuery(String grantqry);

	Integer createTriggers(String vDdlStmt);

}
