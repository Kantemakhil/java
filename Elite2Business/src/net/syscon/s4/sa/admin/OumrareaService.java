package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.beans.AreasCommitBean;

/**
 * s Interface OumrareaService
 */
public interface OumrareaService {

	  List<Areas> maintSubAreaCommit(AreasCommitBean commitBean);

	 List<Areas> maintRegCommit(AreasCommitBean commitBean);

	List<Areas> maintAreaCommit(AreasCommitBean commitBean);

	List<Areas> maintRegExecuteQuery(Areas objAreas);

	List<Areas> maintAreaExecuteQuery(Areas objAreas);

	List<ReferenceCodes> rgAreaTypeRecordGroup();

	List<Areas> maintSubAreaExecuteQuery(Areas searchRecord);

}
