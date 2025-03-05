package net.syscon.s4.inst.classification.assessmentmaintenance;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.AssessSectionNotificationsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.Assessments;



@EliteController
public class OimsenotController {
@Autowired
private OimsenotService oimsenotService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OimsenotController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimsenot/assessmentsExecuteQuery", method=RequestMethod.POST)
	public List<Assessments> assessmentsExecuteQuery(@RequestBody final  Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = oimsenotService.assessmentsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments bean = new Assessments();
			logger.error("Exception  : assessmentsExecuteQuery",e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *getting cgfkNextSection LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')") 
	@RequestMapping(value="/oimsenot/cgfkNextSectionRecordGroup",method=RequestMethod.GET)
	public List<Assessments> cgfkNextSectionRecordGroup(@RequestParam(value = "parentField1") final String parentField1) {
		List<Assessments> recordList =new ArrayList<Assessments>();
		try {
			recordList = oimsenotService.cgfkNextSectionRecordGroup(parentField1);

		} catch(Exception e){
			final Assessments obj = new Assessments();
			logger.error("Exception : cgfkNextSectionRecordGroup",e);
			recordList.add(obj);
		}
		return recordList;
	}

	
	/**
	 *getting cgfkSectionCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')") 
	@RequestMapping(value="/oimsenot/cgfkSectioncodeRecordGroup",method=RequestMethod.GET)
	public List<Assessments> cgfkSectionCodeRecordGroup(@RequestParam("assessmentId") final Long assessmentId) {
		List<Assessments> recordList =new ArrayList<Assessments>();
		try {
			recordList = oimsenotService.cgfkSectionCodeRecordGroup(assessmentId);
		} catch(Exception e){
			final Assessments obj = new Assessments();
			logger.error("Exception : cgfkSectioncodeRecordGroup",e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkScoreType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimsenot/cgfkScoreTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkScoreTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsenotService.cgfkScoreTypeRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkScoreTypeRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkNextSectionFlag LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')") 
	@RequestMapping(value="/oimsenot/cgfkNextSectionFlagRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkNextSectionFlagRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimsenotService.cgfkNextSectionFlagRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : cgfkNextSectionFlagRecordGroup",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimsenot/assessSectionNotificationsExecuteQuery", method=RequestMethod.POST)
	public List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(@RequestBody final AssessSectionNotifications searchBean) {
		List<AssessSectionNotifications> searchResult = new ArrayList<>();
		try {
			searchResult = oimsenotService.assessSectionNotificationsExecuteQuery(searchBean);
		} catch (Exception e) {
			final AssessSectionNotifications bean = new AssessSectionNotifications();
			logger.error("Exception : assessSectionNotificationsExecuteQuery",e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimsenot/assessSectionNotificationsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer assessSectionNotificationsCommit(@RequestBody final AssessSectionNotificationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimsenotService.assessSectionNotificationsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : assessSectionNotificationsCommit",e);
		}
		return liReturn;
	}

}