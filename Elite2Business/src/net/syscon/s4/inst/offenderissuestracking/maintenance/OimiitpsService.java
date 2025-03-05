package net.syscon.s4.inst.offenderissuestracking.maintenance;

import java.util.List;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;

public interface OimiitpsService {

	List<GrievanceTypes> grievencePermissionExecuteQuery(GrievanceTypes searchBean);

	List<ReferenceCodes> grievenceTypeRecordGroup();

	List<ReferenceCodes> grievenceReasonRecordGroup(String grievType);

	Integer  grievencePermissionCommit(GrievanceTypesCommitBean commitBean);


}
