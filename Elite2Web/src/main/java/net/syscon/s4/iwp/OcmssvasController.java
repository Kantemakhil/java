package net.syscon.s4.iwp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.CourseActivityAreasCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OcmssvasController
 */
@EliteController
public class OcmssvasController {
	@Autowired
	private OcmssvasService ocmssvasService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmssvasController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvas/cActAExecuteQuery", method = RequestMethod.POST)
	public List<CourseActivityAreas> cActAExecuteQuery(@RequestBody final CourseActivityAreas searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CourseActivityAreas> searchResult = new ArrayList<>();
		try {
			 searchResult = ocmssvasService.cActAExecuteQuery(searchBean);
		} catch (Exception e) {
			final CourseActivityAreas bean = new CourseActivityAreas();
			logger.error("cActAExecuteQuery :", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvas/cActACommit", method = RequestMethod.POST)
	public @ResponseBody CourseActivityAreas cActACommit(@RequestBody final CourseActivityAreasCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		CourseActivityAreas returnData = new CourseActivityAreas();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			returnData = ocmssvasService.cActACommit(commitBean);
		} catch (Exception e) {

			logger.error("cActACommit :", e);
		}
		return returnData;
	}

	/**
	 * getting rgAreaClass LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvas/rgAreaClassRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAreaClassRecordGroup() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmssvasService.rgAreaClassRecordGroup();
		} catch (Exception e) {
			logger.error("rgAreaClassRecordGroup :", e);
		}
		return recordList;
	}

	/**
	 * getting rgAreaRegion LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmssvas/rgAreaRegionRecordGroup", method = RequestMethod.GET)
	public List<Areas> rgAreaRegionRecordGroup(@RequestParam("caseLoadType") final String caseLoadType,
			@RequestParam("nbtAreaType") final String nbtAreaType) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<Areas> recordList = new ArrayList<>();
		try {
			recordList = ocmssvasService.rgAreaRegionRecordGroup(caseLoadType, nbtAreaType);
		} catch (Exception e) {
			final Areas obj = new Areas();
			logger.error("rgAreaRegionRecordGroup :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSSVAS");
	}

}