package net.syscon.s4.inst.movements.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OiunonasRepository
 */
public interface OiunonasRepository {
	List<ReferenceCodes> rgNonAssoTypeRecordGroup();

	List<AgyIntLocProfiles> intLocProfExecuteQuery(AgyIntLocProfiles object);

	Integer intLocProfInsertAgyIntLocProfiles(List<AgyIntLocProfiles> object);

	Integer intLocProfDeleteAgyIntLocProfiles(List<AgyIntLocProfiles> object);

	Integer intLocProfUpdateAgyIntLocProfiles(List<AgyIntLocProfiles> object);

	Integer preInsert(List<Long> intLocId,List<String> intLocProfileCode);

}
