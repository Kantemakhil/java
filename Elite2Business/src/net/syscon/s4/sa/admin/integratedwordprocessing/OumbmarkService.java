package net.syscon.s4.sa.admin.integratedwordprocessing;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParametersCommitBean;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpBookmarksCommitBean;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookmarkCommitBean;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;

/**
 * Interface OumbmarkService
 * 
 */
public interface OumbmarkService {
	List<IwpBookmarkParameters> aIwpParametersExecuteQuery(IwpBookmarkParameters objIwpBookmarkParameters);

	Integer aIwpBookmarksCommit(IwpBookmarksCommitBean commitBean);

	Integer aIwpParametersCommit(IwpBookmarkParametersCommitBean commitBean);

	List<IwpBookmarkParameters> aIwpBookmarksOnCheckDeleteMaster(IwpBookmarks paramBean);

	List<IwpBookmarks> DuplicateBookmarkName(IwpBookmarks paramBean);

	List<IwpBookmarks> aIwpBookmarksExecuteQuery(IwpBookmarks objIwpBookmarks);

	List<IwpParameterMappings> CheckForMapping(IwpParameterMappings paramBean);
	
	List<ReferenceCodes> rgBmTypeRecordGroup();
	
	List<ReferenceCodes> rgParamDataTypeRecordGroup();
	
	List<ReferenceCodes> rgParamTypeRecordGroup();

	List<IwpBookmarkParameters> oumbmarkIwpBookmarksSqlText(IwpBookmarks searchBean);

	List<Map<String, Object>> getOutParamLov(IwpBookmarks searchBean);

	List<IwpBookmarkParameters> getOutParams(IwpBookmarks searchBean);

	Integer outParametersUpdate(IwpCompositeBookmarkCommitBean commitBean);


}
