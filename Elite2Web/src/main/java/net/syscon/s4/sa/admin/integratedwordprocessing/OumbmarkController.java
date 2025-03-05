
package net.syscon.s4.sa.admin.integratedwordprocessing;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParametersCommitBean;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpBookmarksCommitBean;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookMarks;
import net.syscon.s4.sa.admin.beans.IwpCompositeBookmarkCommitBean;

/**
 * class OumbmarkController
 * 
 */
@EliteController
public class OumbmarkController {
	@Autowired
	private OumbmarkService oumbmarkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumbmarkController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbmark/aIwpBookmarksExecuteQuery", method = RequestMethod.POST)
	public List<IwpBookmarks> aIwpBookmarksExecuteQuery(@RequestBody final IwpBookmarks searchBean) {
		List<IwpBookmarks> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = oumbmarkService.aIwpBookmarksExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpBookmarks bean = new IwpBookmarks();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumbmark/aIwpBookmarksCommit", method = RequestMethod.POST)
	public @ResponseBody Integer aIwpBookmarksCommit(@RequestBody  final IwpBookmarksCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumbmarkService.aIwpBookmarksCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting rgBmType LOV values
	 */
	 @PreAuthorize("hasEliteRole('read')")
	 @RequestMapping(value="/oumbmark/rgBmTypeRecordGroup",method=RequestMethod.GET)
	 public List<ReferenceCodes> rgBmTypeRecordGroup() {
	 List<ReferenceCodes> recordList =new ArrayList<>();
	 try {
	 recordList = oumbmarkService.rgBmTypeRecordGroup();
	 } catch(Exception e){
	
	 logger.error("Exception :",e);
	 }
	 return recordList;
	 }
	/**
	 * getting rgParamDataType LOV values
	 */
	 @PreAuthorize("hasEliteRole('read')")
	 @RequestMapping(value="/oumbmark/rgParamDataTypeRecordGroup",method=RequestMethod.GET)
	 public List<ReferenceCodes> rgParamDataTypeRecordGroup() {
	 List<ReferenceCodes> recordList =new ArrayList<>();
	 try {
	 recordList = oumbmarkService.rgParamDataTypeRecordGroup();
	 } catch(Exception e){
	 logger.error("Exception :",e);
	 }
	 return recordList;
	 }

	/**
	 * getting rgParamType LOV values
	 */
	 @PreAuthorize("hasEliteRole('read')")
	 @RequestMapping(value="/oumbmark/rgParamTypeRecordGroup",method=RequestMethod.GET)
	 public List<ReferenceCodes> rgParamTypeRecordGroup() {
	 List<ReferenceCodes> recordList =new ArrayList<>();
	 try {
	 recordList = oumbmarkService.rgParamTypeRecordGroup();
	 } catch(Exception e){
	 logger.error("Exception :",e);
	 }
	 return recordList;
	 }

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbmark/aIwpParametersExecuteQuery", method = RequestMethod.POST)
	public List<IwpBookmarkParameters> aIwpParametersExecuteQuery(@RequestBody final IwpBookmarkParameters searchBean) {
		List<IwpBookmarkParameters> searchResult = new ArrayList<>();
		try {
			searchResult = oumbmarkService.aIwpParametersExecuteQuery(searchBean);
		} catch (Exception e) {
			IwpBookmarkParameters bean = new IwpBookmarkParameters();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumbmark/aIwpParametersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer aIwpParametersCommit(@RequestBody final IwpBookmarkParametersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumbmarkService.aIwpParametersCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbmark/oumbmarkIwpBookmarksSqlText", method = RequestMethod.POST)
	public List<IwpBookmarkParameters> oumbmarkIwpBookmarksSqlText(@RequestBody final IwpBookmarks searchBean) {
		List<IwpBookmarkParameters> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			 searchResult = oumbmarkService.oumbmarkIwpBookmarksSqlText(searchBean);
		} catch (Exception e) {
			IwpBookmarkParameters bean = new IwpBookmarkParameters();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * Fetching the column keys from a query
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbmark/getOutParamLov", method = RequestMethod.POST)
	public List<IwpBookmarkParameters> getOutParamLov(@RequestBody final IwpBookmarks searchBean) {
		List<IwpBookmarkParameters> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			 searchResult = oumbmarkService.getOutParams(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
			e.printStackTrace();
		}
		return searchResult;
	}
	/**
	 * Fetching the column keys from a query
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbmark/getOutParams", method = RequestMethod.POST)
	public List<IwpBookmarkParameters> getOutParams(@RequestBody final IwpBookmarks searchBean) {
		List<IwpBookmarkParameters> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			 searchResult = oumbmarkService.getOutParams(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
			e.printStackTrace();
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumbmark/outParametersUpdate", method = RequestMethod.POST)
	public @ResponseBody Integer IwpCompositeUpdateParametersCommit(@RequestBody final IwpCompositeBookmarkCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(commitBean.getUpdateList() != null) {
			for (IwpCompositeBookMarks iwpComposite:commitBean.getUpdateList()) {
				iwpComposite.setModifyUserId(userName);
			}
		}
		try {
			liReturn = oumbmarkService.outParametersUpdate(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
}