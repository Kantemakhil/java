package net.syscon.s4.inst.demographicsbiometrics;

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
import net.syscon.s4.im.beans.OffenderLanguages;
import net.syscon.s4.im.beans.OffenderLanguagesCommitBean;

/**
 * class OcdlangsController
 */
@EliteController
public class OcdlangsController {
@Autowired
private OcdlangsService ocdlangsService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcdlangsController.class.getName());
	/**
	 *getting rgLangSkills LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/rgLangSkillsRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgLangSkillsRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdlangsService.rgLangSkillsRecordGroup();
		} catch(Exception e){
			logger.error(e);
		}
		return recordList;
	}

	/**
	 *getting rgPrefLang LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/rgPrefLangRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgPrefLangRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdlangsService.rgPrefLangRecordGroup();
		} catch(Exception e){
			logger.error(e);
		}
		return recordList;
	}

	/**
	 *getting rgSecLang LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/rgSecLangRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgSecLangRecordGroup(@RequestParam(value = "langCode") final String langCode
			, @RequestParam(value = "langCode1") final String langCode1) {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdlangsService.rgSecLangRecordGroup(langCode,langCode1);
		} catch(Exception e){
			logger.error(e);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/offPrimLangExecuteQuery", method=RequestMethod.POST)
	public List<OffenderLanguages> offPrimLangExecuteQuery(@RequestBody final OffenderLanguages searchBean) {
		List<OffenderLanguages> searchResult = new ArrayList<>();
		try {
			searchResult = ocdlangsService.offPrimLangExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdlangs/offPrimLangCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offPrimLangCommit(@RequestBody final OffenderLanguagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdlangsService.offPrimLangCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/prefLangWriteExecuteQuery", method=RequestMethod.POST)
	public List<OffenderLanguages> prefLangWriteExecuteQuery(@RequestBody final OffenderLanguages searchBean) {
		List<OffenderLanguages> searchResult = new ArrayList<>();
		try {
			searchResult = ocdlangsService.prefLangWriteExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdlangs/prefLangWriteCommit",method=RequestMethod.POST)
	public @ResponseBody Integer prefLangWriteCommit(@RequestBody final OffenderLanguagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdlangsService.prefLangWriteCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/prefLangSpeakExecuteQuery", method=RequestMethod.POST)
	public List<OffenderLanguages> prefLangSpeakExecuteQuery(@RequestBody final OffenderLanguages searchBean) {
		List<OffenderLanguages> searchResult = new ArrayList<>();
		try {
			searchResult = ocdlangsService.prefLangSpeakExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/offSecLangExecuteQuery", method=RequestMethod.POST)
	public List<OffenderLanguages> offSecLangExecuteQuery(@RequestBody final OffenderLanguages searchBean) {
		List<OffenderLanguages> searchResult = new ArrayList<>();
		try {
			searchResult = ocdlangsService.offSecLangExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/ocdlangs/offSecLangCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offSecLangCommit(@RequestBody final OffenderLanguagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdlangsService.offSecLangCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/offBkgOnCheckDeleteMaster", method=RequestMethod.POST)
	public List<Object> offBkgOnCheckDeleteMaster(@RequestBody final OffenderLanguages searchBean) {
		List<Object> searchResult = new ArrayList<>();
		try {
			searchResult = ocdlangsService.offBkgOnCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 *getting Values from DB 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocdlangs/getPreferredDefault",method=RequestMethod.GET)
	public ReferenceCodes getPreferredDefault() {
		ReferenceCodes recordList =new ReferenceCodes();
		try {
			recordList = ocdlangsService.getPreferredDefault();
		} catch(Exception e){
			logger.error(e);
		}
		return recordList;
	}
}