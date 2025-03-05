package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OiunonasService
 */
public interface OiunonasService {
	List<ReferenceCodes> rgNonAssoTypeRecordGroup();

	List<AgyIntLocProfiles> intLocProfExecuteQuery(AgyIntLocProfiles object);

	Integer intLocProfCommit(AgyIntLocProfilesCommitBean commitBean);

}
