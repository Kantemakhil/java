package net.syscon.s4.sa.admin.integratedwordprocessing;

import java.util.List;
import java.util.Map;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookMarks;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;

/**
 * Interface OumbmarkRepository
 * 
 */
public interface OumbmarkRepository {
	List<IwpBookmarkParameters> aIwpParametersExecuteQuery(IwpBookmarkParameters objIwpBookmarkParameters);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer aIwpParametersUpdateIwpBookmarkParameters(List<IwpBookmarkParameters> lstIwpBookmarkParameters);

	Integer aIwpBookmarksInsertIwpBookmarks(List<IwpBookmarks> lstIwpBookmarks);

	List<IwpParameterMappings> checkForMapping(IwpParameterMappings paramBean);

	List<IwpBookmarks> duplicateBookmarkName(IwpBookmarks paramBean);

	Integer aIwpBookmarksUpdateIwpBookmarks(List<IwpBookmarks> lstIwpBookmarks);

	Integer aIwpParametersInsertIwpBookmarkParameters(List<IwpBookmarkParameters> lstIwpBookmarkParameters);

	List<IwpBookmarks> aIwpBookmarksExecuteQuery(IwpBookmarks objIwpBookmarks);

	List<IwpBookmarkParameters> aIwpBookmarksOnCheckDeleteMaster(IwpBookmarks paramBean);

	String oumbmarkGetStaffName(String staffName );
	
	 String oumbmarkGetUserId(String createUserId);

	List<Map<String, Object>> getOutParamLov(IwpBookmarks searchBean);

	Integer insertOutParams(List<IwpBookmarkParameters> outParams);

	List<IwpBookmarkParameters> getOutParams(IwpBookmarks searchBean);

	Integer deleteBookmarkOutParams(IwpBookmarks iwpBookmarks);

	Integer outParametersUpdate(List<IwpCompositeBookMarks> list);

	List<IwpBookmarks> getIwpBookmarksSQLText(String bookmarkName);

	Integer deleteCompositeOutParamBookmarks(IwpBookmarks iwpBookmarks);

}
