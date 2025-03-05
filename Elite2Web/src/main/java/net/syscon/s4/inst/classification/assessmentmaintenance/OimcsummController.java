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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssOffNeedsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VAssTreatProtsCommitBean;

/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@EliteController
public class OimcsummController {
@Autowired
private OimcsummService oimcsummService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OimcsummController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oimcsumm/assessmentsExecuteQuery", method=RequestMethod.POST)
	public List<Assessments> assessmentsExecuteQuery(@RequestBody Assessments searchBean) {
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
	@RequestMapping(value="/oimcsumm/rgCaseworkRecordGroup",method=RequestMethod.GET)
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
	@RequestMapping(value="/oimcsumm/rgProgramIdRecordGroup",method=RequestMethod.GET)
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
	@RequestMapping(value="/oimcsumm/rgCaseplanAssRecordGroup",method=RequestMethod.GET)
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
	@RequestMapping(value="/oimcsumm/cgfkNextSectionRecordGroup",method=RequestMethod.GET)
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
	@RequestMapping(value="/oimcsumm/cgfkSectionCodeRecordGroup",method=RequestMethod.GET)
	public List<Assessments> cgfkSectionCodeRecordGroup(@RequestParam("assessmentId") final Long assessmentId) {
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
	@RequestMapping(value="/oimcsumm/cgfkScoreTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkScoreTypeRecordGroup() {
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
	@RequestMapping(value="/oimcsumm/rgPrgCategoryRecordGroup",method=RequestMethod.GET)
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
	@RequestMapping(value="/oimcsumm/vAssOffNeedsExecuteQuery", method=RequestMethod.POST)
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
	@RequestMapping(value="/oimcsumm/vAssOffNeedsCommit",method=RequestMethod.POST)
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
	@RequestMapping(value="/oimcsumm/vAssTreatProtsExecuteQuery", method=RequestMethod.POST)
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
	@RequestMapping(value="/oimcsumm/vAssTreatProtsCommit",method=RequestMethod.POST)
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

	
	
}