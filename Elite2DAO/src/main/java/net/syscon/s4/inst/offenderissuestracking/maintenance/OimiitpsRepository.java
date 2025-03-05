package net.syscon.s4.inst.offenderissuestracking.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.GrievanceTypes;

public interface OimiitpsRepository {

	List<GrievanceTypes> grievencePermissionExecuteQuery(GrievanceTypes searchBean);

	List<ReferenceCodes> grievenceReasonRecordGroup(String grievType);

	List<ReferenceCodes> grievenceTypeRecordGroup();

	Integer grievencePermissionInseting(GrievanceTypes ob);

	Integer grievencePermissionUpdating(GrievanceTypes ob);


}
