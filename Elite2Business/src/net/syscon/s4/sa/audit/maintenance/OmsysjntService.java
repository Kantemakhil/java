package net.syscon.s4.sa.audit.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.dao.RecordGroup;

/**
 * Interface OmsysjntService
 */
public interface OmsysjntService {
	List<RecordGroup> tableRgRecordGroup();

	Integer createTr(Boolean insertTrigger);

	Integer createOneTr(String tableName, Boolean insertTrigger);
}
