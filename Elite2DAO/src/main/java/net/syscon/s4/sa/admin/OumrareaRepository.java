package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;

/**
 * Interface OumrareaRepository
 */
public interface OumrareaRepository {
	List<Areas> maintRegOnCheckDeleteMaster(Areas paramBean);

	List<Areas> maintAreaExecuteQuery(Areas objAreas);

	Integer maintAreaInsertAreas(List<Areas> lstAreas);

	Areas maintAreaUpdateAreas(List<Areas> lstAreas);

	List<Areas> maintAreaOnCheckDeleteMaster(Areas paramBean);

	List<Areas> maintRegExecuteQuery(Areas objAreas);

	Areas maintRegUpdateAreas(List<Areas> lstAreas);

	Integer maintRegInsertAreas(List<Areas> lstAreas);

	  List<Areas> maintSubAreaExecuteQuery(Areas objSearchDao);

	Integer maintSubAreaInsertAreas(final List<Areas> lstAreas);

	Areas maintSubAreaUpdateAreas(final List<Areas> lstAreas);

	List<ReferenceCodes> rgAreaTypeRecordGroup();

}
