package net.syscon.s4.inst;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupantsCommitBean;

public interface OcuoccupService {
	List<OmsModules> CreateFormGlobals(OmsModules paramBean);

	Integer rpOtherOccupantsCommit(RpOtherOccupantsCommitBean CommitBean);

	List<RpOtherOccupants> ocuoccupKeyExit(RpOtherOccupants paramBean);

	Phones rpOtherOccupantsPostQuery(Phones paramBean);

	List<RpOtherOccupants> ocuoccupPostFormsCommit(RpOtherOccupants paramBean);

	List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(RpOtherOccupants searchBean);

	List<ReferenceCodes> rgContactedRecordGroup();

	List<OffenderContactPersons> rgPersonNameRecordGroup(String offenderBookId);

	List<ReferenceCodes> rgContactTypesRecordGroup();

	List<ReferenceCodes> rgRelationshipsRecordGroup(String contactCode);

}
