package net.syscon.s4.sa.audit;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TagAuditFormGettabledetail;

/**
 * Interface OuiadactRepository
 */
public interface OuiadactRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<ReferenceCodes> rgTableNameRecordGroup();

	List<TagAuditFormGettabledetail> getTableDetailExecuteQuery(TagAuditFormGettabledetail object);

	String getStaffName(TagAuditFormGettabledetail object);

}
