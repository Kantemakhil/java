package net.syscon.s4.sa.admin.integratedwordprocessing.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.iwp.base.impl.EoffenderDocumentRepositoryImpl;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParametersCommitBean;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpBookmarksCommitBean;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookmarkCommitBean;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumbmarkRepository;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumbmarkService;

/**
 * Class OumbmarkServiceImpl
 */
@Service
public class OumbmarkServiceImpl extends BaseBusiness implements OumbmarkService {

	@Autowired
	private OumbmarkRepository oumbmarkRepository;
	
	@Autowired
	private EoffenderDocumentRepositoryImpl eoffenderDocumentRepositoryImpl;
	
	

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarkParameters> aIwpBookmarksOnCheckDeleteMaster(final IwpBookmarks paramBean) {
		return oumbmarkRepository.aIwpBookmarksOnCheckDeleteMaster(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpParameterMappings> CheckForMapping(final IwpParameterMappings paramBean) {
		return oumbmarkRepository.checkForMapping(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<IwpBookmarks> DuplicateBookmarkName(final IwpBookmarks paramBean) {
		return oumbmarkRepository.duplicateBookmarkName(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<IwpBookmarks> aIwpBookmarksExecuteQuery(final IwpBookmarks searchRecord) {
		List<IwpBookmarks> returnList = oumbmarkRepository.aIwpBookmarksExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			if (ele.getUserCreated() != null) {
				String data = oumbmarkRepository.oumbmarkGetStaffName(ele.getUserCreated());
				if (data != null) {
					ele.setSealFlag(data);
				}
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstA_IWP_BOOKMARKS
	 *
	 */
	@Transactional
	public Integer aIwpBookmarksCommit(final IwpBookmarksCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (IwpBookmarks obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setUserCreated(commitBean.getCreateUserId());
				if (obj.getBookmarkName() != null) {
					List<IwpBookmarkParameters> returnData = oumbmarkRepository.aIwpBookmarksOnCheckDeleteMaster(obj);
					if (returnData.size() > 0) {
						liReturn = 3;
						return liReturn;
					}
				}
			}
			liReturn = oumbmarkRepository.aIwpBookmarksInsertIwpBookmarks(commitBean.getInsertList());
			for(IwpBookmarks iwpBookmarks : commitBean.getInsertList()) {
				insertBookmarkOutParams(commitBean, iwpBookmarks);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IwpBookmarks obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setUserCreated(commitBean.getCreateUserId());
				if("COMP".equals(obj.getBookmarkType())) {
					List<IwpBookmarks> iwpBookmark = oumbmarkRepository.getIwpBookmarksSQLText(obj.getBookmarkName());
					if(!obj.getSqlText().equalsIgnoreCase(iwpBookmark.get(0).getSqlText())) {
						deleteBookmarkOutParams(obj);
						insertBookmarkOutParams(commitBean, obj);
					}
				}
			}
			liReturn = oumbmarkRepository.aIwpBookmarksUpdateIwpBookmarks(commitBean.getUpdateList());
		}
		return liReturn;
		
	}
	
	

	private void deleteCompositeOutParamBookmarks(IwpBookmarks iwpBookmarks) {
		oumbmarkRepository.deleteCompositeOutParamBookmarks(iwpBookmarks);
		
	}

	private void deleteBookmarkOutParams(IwpBookmarks iwpBookmarks) {
		oumbmarkRepository.deleteBookmarkOutParams(iwpBookmarks);
	}
	
	private void insertBookmarkOutParams(final IwpBookmarksCommitBean commitBean, IwpBookmarks iwpBookmarks) {
		if("COMP".equals(iwpBookmarks.getBookmarkType())) {
			List<Map<String, Object>> outParams = getOutParamLov(iwpBookmarks);
			List<IwpBookmarkParameters> IwpBookmarkOutParameters = new ArrayList<>();
			outParams.forEach(op -> {
				IwpBookmarkParameters ibp = new IwpBookmarkParameters();
				ibp.setParameterName(String.valueOf(op.get("outParam")));
				ibp.setParameterDesc(String.valueOf(op.get("outParamDesc")));
				ibp.setBookmarkName(iwpBookmarks.getBookmarkName());
				ibp.setCreateUserId(commitBean.getCreateUserId());
				IwpBookmarkOutParameters.add(ibp);
				
			});
			insertOutParams(IwpBookmarkOutParameters);		
		}
	}
	
	public List<IwpBookmarkParameters> oumbmarkIwpBookmarksSqlText(final IwpBookmarks object) {
		List<IwpBookmarkParameters> count = new ArrayList<IwpBookmarkParameters>();
		Map<String, String> bookmarkResolvedSqlMap = new HashMap<>();
		bookmarkResolvedSqlMap.put("P_SQLTEXT", object.getSqlText());
		 String userName = oumbmarkRepository.oumbmarkGetUserId(object.getCreateUserId());
		final Map<String, List<String>> outMap = eoffenderDocumentRepositoryImpl.getBookmarkValueMap(bookmarkResolvedSqlMap, userName);
		return count;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<IwpBookmarkParameters> aIwpParametersExecuteQuery(final IwpBookmarkParameters searchRecord) {
		return oumbmarkRepository.aIwpParametersExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstA_IWP_PARAMETERS
	 *
	 */
	@Transactional
	public Integer aIwpParametersCommit(final IwpBookmarkParametersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (IwpBookmarkParameters obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setUserCreated(commitBean.getCreateUserId());
			}
			liReturn = oumbmarkRepository.aIwpParametersInsertIwpBookmarkParameters(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IwpBookmarkParameters obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumbmarkRepository.aIwpParametersUpdateIwpBookmarkParameters(commitBean.getUpdateList());
		}
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> rgBmTypeRecordGroup() {
		List<ReferenceCodes> list = new ArrayList<>();
		ReferenceCodes object = new ReferenceCodes();
		object.setCode("BINARY");
		object.setDescription("Binary data");
		list.add(object);
		ReferenceCodes obj = new ReferenceCodes();
		obj.setCode("TEXT");
		obj.setDescription("Textual data");
		list.add(obj);
		ReferenceCodes composite = new ReferenceCodes();
		composite.setCode("COMP");
		composite.setDescription("Composite");
		list.add(composite);
		return list;
	}

	public List<ReferenceCodes> rgParamDataTypeRecordGroup() {
		List<ReferenceCodes> list = new ArrayList<>();
		ReferenceCodes obj = new ReferenceCodes();
		obj.setCode("T");
		obj.setDescription("Text");
		list.add(obj);
		ReferenceCodes object = new ReferenceCodes();
		object.setCode("N");
		object.setDescription("Number");
		list.add(object);
		ReferenceCodes objectOne = new ReferenceCodes();
		objectOne.setCode("D");
		objectOne.setDescription("Date");
		list.add(objectOne);
		return list;
	}

	public List<ReferenceCodes> rgParamTypeRecordGroup() {
		List<ReferenceCodes> list = new ArrayList<>();
		ReferenceCodes obj = new ReferenceCodes();
		obj.setCode("S");
		obj.setDescription("System defined");
		list.add(obj);
		ReferenceCodes object = new ReferenceCodes();
		object.setCode("U");
		object.setDescription("User defined");
		list.add(object);
		ReferenceCodes objectOne = new ReferenceCodes();
		objectOne.setCode("C");
		objectOne.setDescription("Context defined");
		list.add(objectOne);
		return list;
	}

	@Override
	public List<Map<String, Object>> getOutParamLov(IwpBookmarks searchBean) {
		return oumbmarkRepository.getOutParamLov(searchBean);
	}
	
	private Integer insertOutParams(List<IwpBookmarkParameters> outParams) {
		return oumbmarkRepository.insertOutParams(outParams);
	}

	@Override
	public List<IwpBookmarkParameters> getOutParams(IwpBookmarks searchBean) {
		return oumbmarkRepository.getOutParams(searchBean);
	}

	@Override
	public Integer outParametersUpdate(IwpCompositeBookmarkCommitBean commitBean) {
		int liReturn = 0;
		if(commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn =  oumbmarkRepository.outParametersUpdate(commitBean.getUpdateList());
		}	
		return liReturn;
	}

}