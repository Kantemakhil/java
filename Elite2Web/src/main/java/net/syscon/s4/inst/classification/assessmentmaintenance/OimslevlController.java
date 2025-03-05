package net.syscon.s4.inst.classification.assessmentmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.AssessSectionNotificationsCommitBean;
import net.syscon.s4.common.beans.AssessmentSupervisionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssOffNeedsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VAssTreatProtsCommitBean;

/**
 * @version 1.0
 */
@EliteController
public class OimslevlController {
	@Autowired
	private OimslevlService oimslevlService;
	
	@Autowired
	private OimcsummService oimcsummService;
	
	@Autowired
	private OimsenotService oimsenotService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimslevlController.class.getName());

	/**
	 * 
	 * checking Lov values based on Active checkbox
	 */

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimslevl/checkLovData", method = RequestMethod.GET)
	public @ResponseBody String checkLovData(@RequestParam("assessmentId") final Long assessmentId) {
		String liReturn = null;
		try {
			liReturn = oimslevlService.checkLovData(assessmentId);
		} catch (Exception e) {

			logger.error("Exception : checkLovData", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/assTypeExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assTypeExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oimslevlService.assTypeExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments error = new Assessments();
			final String errorMsg = "Error : oimslevl assTypeExecuteQuery " + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception : assTypeExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/assTypeAssSectExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assTypeAssSectExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = oimslevlService.assTypeAssSectExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments error = new Assessments();
			final String errorMsg = "Error : oimslevl  assTypeAssSectExecuteQuery" + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception : assTypeAssSectExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting rgAssessmentSections LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/rgAssessmentSectionsRecordGroup", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentSectionsRecordGroup() {
		List<Assessments> recordList = new ArrayList<>();
		try {
			recordList = oimslevlService.rgAssessmentSectionsRecordGroup();
		} catch (Exception e) {
			final Assessments error = new Assessments();
			final String errorMsg = "Error : oimslevl rgAssessmentSectionsRecordGroup " + e.getMessage();
			error.setErrorMessage(errorMsg);
			recordList.add(error);
			logger.error("Exception : rgAssessmentSectionsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgAssessmentTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/rgAssessmentTypesRecordGroup", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentTypesRecordGroup() {
		List<Assessments> recordList = new ArrayList<Assessments>();
		try {
			String userNameOne = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oimslevlService.rgAssessmentTypesRecordGroup(userNameOne);
		} catch (Exception e) {
			final Assessments error = new Assessments();
			final String errorMsg = "Error : oimslevl  rgAssessmentTypesRecordGroup" + e.getMessage();
			error.setErrorMessage(errorMsg);
			recordList.add(error);
			logger.error("Exception : rgAssessmentTypesRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgAssessmentResults LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/rgAssessmentResultsRecordGroup", method = RequestMethod.GET)
	public List<AssessmentResults> rgAssessmentResultsRecordGroup(
			@RequestParam("assessmentId") final Long assessmentId) {
		List<AssessmentResults> recordList = new ArrayList<>();
		try {
			recordList = oimslevlService.rgAssessmentResultsRecordGroup(assessmentId);
		} catch (Exception e) {
			final AssessmentResults error = new AssessmentResults();
			final String errorMsg = "Error : oimslevl  rgAssessmentResultsRecordGroup" + e.getMessage();
			error.setErrorMessage(errorMsg);
			recordList.add(error);
			logger.error("Exception : rgAssessmentResultsRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/typeAssSupExecuteQuery", method = RequestMethod.POST)
	public List<AssessmentSupervisions> typeAssSupExecuteQuery(@RequestBody final AssessmentSupervisions searchBean) {
		List<AssessmentSupervisions> searchResult = new ArrayList<>();
		try {
			searchResult = oimslevlService.typeAssSupExecuteQuery(searchBean);
		} catch (Exception e) {
			final AssessmentSupervisions error = new AssessmentSupervisions();
			final String errorMsg = "Error : oimslevl typeAssSupExecuteQuery " + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception : typeAssSupExecuteQuery", e);

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
	@RequestMapping(value = "/oimslevl/typeAssSupCommit", method = RequestMethod.POST)
	public @ResponseBody List<AssessmentSupervisions> typeAssSupCommit(
			@RequestBody final AssessmentSupervisionsCommitBean commitBean) {
		List<AssessmentSupervisions> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimslevlService.typeAssSupCommit(commitBean);

		} catch (Exception e) {
			final AssessmentSupervisions error = new AssessmentSupervisions();
			final String errorMsg = "Error : oimslevl  typeAssSupCommit" + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			if(errorMsg != null && errorMsg.contains("assessment_supversions_pk")) {
				error.setErrorMessage(errorMsg.replace("assessment_supversions_pk", "ASSESSMENT_SUPVERSIONS_PK"));
			}
			liReturn.add(error);
			logger.error("Exception : typeAssSupCommit", e);
		}

		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/assSectExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assSectExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = oimslevlService.assSectExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments error = new Assessments();
			final String errorMsg = "Error : assSectExecuteQuery " + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception : oimslevl", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/secAssSupExecuteQuery", method = RequestMethod.POST)
	public List<AssessmentSupervisions> secAssSupExecuteQuery(@RequestBody final AssessmentSupervisions searchBean) {
		List<AssessmentSupervisions> searchResult = new ArrayList<>();
		try {
			searchResult = oimslevlService.secAssSupExecuteQuery(searchBean);
		} catch (Exception e) {
			final AssessmentSupervisions error = new AssessmentSupervisions();
			final String errorMsg = "Error :  secAssSupExecuteQuery" + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception : oimslevl", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimslevl/secAssSupCommit", method = RequestMethod.POST)
	public @ResponseBody List<AssessmentSupervisions> secAssSupCommit(
			@RequestBody final AssessmentSupervisionsCommitBean commitBean) {
		List<AssessmentSupervisions> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimslevlService.secAssSupCommit(commitBean);
		} catch (Exception e) {
			final AssessmentSupervisions error = new AssessmentSupervisions();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			if(errorMsg != null && errorMsg.contains("assessment_supversions_pk")) {
				error.setErrorMessage(errorMsg.replace("assessment_supversions_pk", "OMS_OWNER.ASSESSMENT_SUPVERSIONS_PK"));
			}
			liReturn.add(error);
			logger.error("Exception : oimslevl", e);

		}
		return liReturn;
	}
	
	@GetMapping("/oimslevl/updateEnforcementFlag")
	public Integer saveIepSecData(@RequestParam("assessmentId") final Long assessmentId,@RequestParam("enforcementFalg") final String enforcementFalg) {
		Integer returnData=null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnData=oimslevlService.updateEnforceFlag(assessmentId, enforcementFalg,userName);
		} catch (Exception e) {
			returnData=0;
			logger.error("Exception : updateEnforcementFlag", e);		}
		return returnData;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimslevl/getEnforcementFlag", method = RequestMethod.GET)
	public String getEnforcementFlag(
			@RequestParam("assessmentId") final Long assessmentId) {
		String returnData=null;
		try {
			returnData=oimslevlService.getEnforcementFlag(assessmentId);
		} catch (Exception e) {
			logger.error("Exception : updateEnforcementFlag", e);
		}
		return returnData;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimslevl/updateSectionsDetails", method = RequestMethod.POST)
	public Integer updateSectionsDetails(@RequestBody AssessmentsCommitBean commitBean) {
		Integer result = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			result = oimslevlService.updateSectionsDetails(commitBean);
		} catch (Exception e) {
			logger.error("Exception : updateSectionsDetails", e);
		}
		return result;
	}
	
	// copied form Oimcsumm Controller  for security reasons 
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/assessmentsExecuteQueryass", method=RequestMethod.POST)
	public List<Assessments> assessmentsExecuteQueryass(@RequestBody Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = oimcsummService.assessmentsExecuteQuery(searchBean);
		} catch (Exception e) {
			Assessments bean = new Assessments();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *getting rgCasework LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/rgCaseworkRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgCaseworkRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimcsummService.rgCaseworkRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgProgramId LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/rgProgramIdRecordGroup",method=RequestMethod.GET)
	public List<ProgramServices> rgProgramIdRecordGroup(@RequestParam("programCategory") final String programCategory) {
		List<ProgramServices> recordList =new ArrayList<ProgramServices>();
		try {
			recordList = oimcsummService.rgProgramIdRecordGroup(programCategory);
		} catch(Exception e){
			ProgramServices obj = new ProgramServices();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgCaseplanAss LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/rgCaseplanAssRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgCaseplanAssRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimcsummService.rgCaseplanAssRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkNextSection LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/cgfkNextSectionRecordGroup",method=RequestMethod.GET)
	public List<Assessments> cgfkNextSectionRecordGroup() {
		List<Assessments> recordList =new ArrayList<Assessments>();
		try {
			recordList = oimcsummService.cgfkNextSectionRecordGroup();
		} catch(Exception e){
			Assessments obj = new Assessments();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkSectionCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/cgfkSectionCodeRecordGroupass",method=RequestMethod.GET)
	public List<Assessments> cgfkSectionCodeRecordGroupass(@RequestParam("assessmentId") final Long assessmentId) {
		List<Assessments> recordList =new ArrayList<Assessments>();
		try {
			recordList = oimcsummService.cgfkSectionCodeRecordGroup(assessmentId);
		} catch(Exception e){
			Assessments obj = new Assessments();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkScoreType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/cgfkScoreTypeRecordGroupass",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkScoreTypeRecordGroupass() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimcsummService.cgfkScoreTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimcsumm:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgPrgCategory LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/rgPrgCategoryRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgPrgCategoryRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oimcsummService.rgPrgCategoryRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oimcsumm:",e);
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
	@RequestMapping(value="/oimslevl/vAssOffNeedsExecuteQuery", method=RequestMethod.POST)
	public List<VAssOffNeeds> vAssOffNeedsExecuteQuery(@RequestBody VAssOffNeeds searchBean) {
		List<VAssOffNeeds> searchResult = new ArrayList<>();
		try {
			searchResult = oimcsummService.vAssOffNeedsExecuteQuery(searchBean);
		} catch (Exception e) {
			VAssOffNeeds bean = new VAssOffNeeds();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimslevl/vAssOffNeedsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer vAssOffNeedsCommit(@RequestBody VAssOffNeedsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcsummService.vAssOffNeedsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oimcsumm",e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/vAssTreatProtsExecuteQuery", method=RequestMethod.POST)
	public List<VAssTreatProts> vAssTreatProtsExecuteQuery(@RequestBody VAssTreatProts searchBean) {
		List<VAssTreatProts> searchResult = new ArrayList<>();
		try {
			searchResult = oimcsummService.vAssTreatProtsExecuteQuery(searchBean);
		} catch (Exception e) {
			VAssTreatProts bean = new VAssTreatProts();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oimslevl/vAssTreatProtsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer vAssTreatProtsCommit(@RequestBody VAssTreatProtsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimcsummService.vAssTreatProtsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oimcsumm",e);
		}
		return liReturn;
	}

	//copied from OimsenotController file 
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimslevl/assessmentsExecuteQueryOimsenot", method=RequestMethod.POST)
	public List<Assessments> assessmentsExecuteQueryOimsenot(@RequestBody final  Assessments searchBean) {
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
	@RequestMapping(value="/oimslevl/cgfkNextSectionRecordGroupOimsenot",method=RequestMethod.GET)
	public List<Assessments> cgfkNextSectionRecordGroupOimsenot(@RequestParam(value = "parentField1") final String parentField1) {
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
	@RequestMapping(value="/oimslevl/cgfkSectioncodeRecordGroupOimsenot",method=RequestMethod.GET)
	public List<Assessments> cgfkSectionCodeRecordGroupOimsenot(@RequestParam("assessmentId") final Long assessmentId) {
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
	@RequestMapping(value="/oimslevl/cgfkScoreTypeRecordGroupOimsenot",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkScoreTypeRecordGroupOimsenot() {
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
	@RequestMapping(value="/oimslevl/cgfkNextSectionFlagRecordGroupOimsenot",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkNextSectionFlagRecordGroupOimsenot() {
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
	@RequestMapping(value="/oimslevl/assessSectionNotificationsExecuteQueryOimsenot", method=RequestMethod.POST)
	public List<AssessSectionNotifications> assessSectionNotificationsExecuteQueryOimsenot(@RequestBody final AssessSectionNotifications searchBean) {
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
	@RequestMapping(value="/oimslevl/assessSectionNotificationsCommitOimsenot",method=RequestMethod.POST)
	public @ResponseBody Integer assessSectionNotificationsCommitOimsenot(@RequestBody final AssessSectionNotificationsCommitBean commitBean) {
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