package net.syscon.s4.sa.audit;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagAuditFormGettabledetail;

/**
 * Interface OuiadactService
 */
public interface OuiadactService {
	List<ReferenceCodes> rgTableNameRecordGroup();

	List<TagAuditFormGettabledetail> getTableDetailExecuteQuery(TagAuditFormGettabledetail object);

	TagAuditFormGettabledetail getStaffName(final TagAuditFormGettabledetail object);

}
