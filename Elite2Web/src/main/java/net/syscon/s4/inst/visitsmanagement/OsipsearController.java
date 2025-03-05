package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.globalconfiguration.OumsypflService;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.PersonProfilesCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersonsCommiBean;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OsipsearController {
	
	@Autowired
	private OsipsearService osipsearService;

	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OumsypflService oumsypflservice;
	
	@Autowired
	private OcucnperService ocucnperService;
	
	@Autowired
	private OcuperprService ocuperprService;
	
	@Autowired
	private CommonService commonService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OsipsearController.class.getName());
	/**
	 *getting rgLanguageCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/rgLanguageCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = osipsearService.rgLanguageCodeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgLanguageCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgMaritalStatus LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/rgMaritalStatusRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = osipsearService.rgMaritalStatusRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgMaritalStatusRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgSexCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/rgSexCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = osipsearService.rgSexCodeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSexCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgSearchType LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/rgSearchTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = osipsearService.rgSearchTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgSearchTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgIdentifierType LOV values 
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/rgIdentifierTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = osipsearService.rgIdentifierTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("rgIdentifierTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/personsExecuteQuery", method = RequestMethod.POST)
	public List<TagPersonSearchGetPersons> personsExecuteQuery(@RequestBody TagPersonSearchGetPersons searchBean,
			@RequestHeader HttpHeaders headers) {
		List<TagPersonSearchGetPersons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			//searchBean.setIntCorrelationId(osiosearService.getIntCorrelationIdSeq());
			//logger.error(this.getClass().getName()+" getIntCorrelationIdSeq");
			searchResult = osipsearService.personsExecuteQuery(searchBean);
			SystemProfiles bean = new SystemProfiles();
			bean.setProfileCode("PIN");
			bean.setProfileType("CLIENT");
			List<SystemProfiles> sysList = oumsypflservice.getSystemProfileRecords(bean);
			if (searchResult != null && searchResult.size() == 0 && sysList!=null && ApplicationConstants.YES.equalsIgnoreCase(sysList.get(0).getProfileValue())) {
				List<String> authorizationList = headers.get("Authorization");
				String authorization = authorizationList.get(0).split(",")[0];
				TagPersonSearchGetPersonsCommiBean commitBean = new TagPersonSearchGetPersonsCommiBean();
				List<TagPersonSearchGetPersons> list = new ArrayList<TagPersonSearchGetPersons>();
				list.add(searchBean);
				commitBean.setInsertList(list);
				prosmainService.enableTriggers(commitBean, authorization, "125");
			}
		} catch (Exception e) {
			TagPersonSearchGetPersons bean = new TagPersonSearchGetPersons();
			logger.error("personsExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perAddrExecuteQuery", method=RequestMethod.POST)
	public List<VPersonAddress> perAddrExecuteQuery(@RequestBody VPersonAddress searchBean) {
		List<VPersonAddress> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.perAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			VPersonAddress bean = new VPersonAddress();
			logger.error("perAddrExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perIdentExecuteQuery", method=RequestMethod.POST)
	public List<PersonIdentifiers> perIdentExecuteQuery(@RequestBody PersonIdentifiers searchBean) {
		List<PersonIdentifiers> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.perIdentExecuteQuery(searchBean);
		} catch (Exception e) {
			PersonIdentifiers bean = new PersonIdentifiers();
			logger.error("perIdentExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perIdentCommit",method=RequestMethod.POST)
	public @ResponseBody Integer perIdentCommit(@RequestBody PersonIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = osipsearService.perIdentCommit(commitBean);
		}catch(Exception e){

			logger.error("perIdentCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/imageExecuteQuery", method=RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			Images bean = new Images();
			logger.error("imageExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perInfoExecuteQuery", method=RequestMethod.POST)
	public List<Persons> perInfoExecuteQuery(@RequestBody Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.perInfoExecuteQuery(searchBean);
		} catch (Exception e) {
			Persons bean = new Persons();
			logger.error("perInfoExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perInfoCommit",method=RequestMethod.POST)
	public @ResponseBody Integer perInfoCommit(@RequestBody PersonsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = osipsearService.perInfoCommit(commitBean);
		}catch(Exception e){

			logger.error("osipsear/perInfoCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perEmpExecuteQuery", method=RequestMethod.POST)
	public List<PersonEmployments> perEmpExecuteQuery(@RequestBody PersonEmployments searchBean) {
		List<PersonEmployments> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.perEmpExecuteQuery(searchBean);
		} catch (Exception e) {
			PersonEmployments bean = new PersonEmployments();
			logger.error("perEmpExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/perEmpCommit",method=RequestMethod.POST)
	public @ResponseBody Integer perEmpCommit(@RequestBody PersonEmploymentsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = osipsearService.perEmpCommit(commitBean);
		}catch(Exception e){

			logger.error("osipsear/perEmpCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/psPersonNameExecuteQuery", method=RequestMethod.POST)
	public List<TagPersonSearchGetPartialSoundexPersons> psPersonNameExecuteQuery(@RequestBody TagPersonSearchGetPartialSoundexPersons searchBean) {
		List<TagPersonSearchGetPartialSoundexPersons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osipsearService.psPersonNameExecuteQuery(searchBean);
		} catch (Exception e) {
			TagPersonSearchGetPartialSoundexPersons bean = new TagPersonSearchGetPartialSoundexPersons();
			logger.error("psPersonNameExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osipsear/psPersonNameCommit",method=RequestMethod.POST)
	public @ResponseBody Integer psPersonNameCommit(@RequestBody TagPersonSearchGetPartialSoundexPersonsCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = osipsearService.psPersonNameCommit(commitBean);
		}catch(Exception e){
			logger.error("psPersonNameCommit", e);
		}
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/getAdditionalNames", method = RequestMethod.GET)
	public List<Offenders> getAdditionalNames(@RequestParam Long personId) {
		List<Offenders> list = new ArrayList<Offenders>();
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			list = osipsearService.getAdditionalNames(personId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAdditionalNames"+ e);
		}
		return list;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/personAddNamesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer personAddNamesCommit(@RequestBody OffendersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = osipsearService.personAddNamesCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " personAddNamesCommit" + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/getDataFromJisCommonSystemForPerson", method = RequestMethod.GET)
	public List<TagPersonSearchGetPersons> getDataFromJisCommonSystemForPerson(
			@RequestParam("intCorrelationId") Long intCorrelationId, @RequestParam("nameSearchType") String nameSearchType ,@RequestParam("moduleName") String moduleName) {
		List<TagPersonSearchGetPersons> list = new ArrayList<TagPersonSearchGetPersons>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			list = osipsearService.getDataFromJisCommonSystemForPerson(intCorrelationId,nameSearchType,moduleName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getDataFromJisCommonSystem : ", e);
			return null;
		}	
		return list;
	}
	
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/personsExecuteQuery1", method = RequestMethod.POST)
	public List<Persons> personsExecuteQuery1(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocucnperService.personsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Persons bean = new Persons();
			logger.error("In method personsExecuteQuery", e);
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
	//@PreAuthorize("hasEliteRole('read')")
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/personsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer personsCommit(@RequestBody final PersonsCommitBean commitBean) {
		int liReturn = 0;
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = ocucnperService.personsCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method personsCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/rgSexCodeRecordGroup1", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup1() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocucnperService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSexCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/profilesExecuteQuery", method = RequestMethod.POST)
	public List<PersonProfiles> profilesExecuteQuery(@RequestBody final PersonProfiles searchBean) {
		List<PersonProfiles> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuperprService.profilesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("profilesExecuteQuery",e);
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
	@RequestMapping(value = "/osipsear/profilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer profilesCommit(@RequestBody final PersonProfilesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = ocuperprService.profilesCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting rgProfileCode LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/rgProfileCodeRecordGroup", method = RequestMethod.GET)
	public List<ProfileCodes> rgProfileCodeRecordGroup(@RequestParam (value="profileType") final String profileType) {
		List<ProfileCodes> recordList = new ArrayList<ProfileCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocuperprService.rgProfileCodeRecordGroup(profileType);
		} catch (Exception e) {
			logger.error("rgProfileCodeRecordGroup",e);
		}
		return recordList;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osipsear/insertProfilesTypes", method = RequestMethod.GET)
	public List<PersonProfiles> insertProfilesTypes(@RequestParam (value="personId") final Integer personId) {
		List<PersonProfiles> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = ocuperprService.insertProfilesTypes(personId,userName);
		} catch (Exception e) {
			logger.error("profilesExecuteQuery",e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OSIPSEAR");
	}
}