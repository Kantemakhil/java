package net.syscon.s4.inst.visitsmanagement;

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
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;

@EliteController
public class OidviresController {
@Autowired
private OidviresService oidviresService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidviresController.class.getName());
	/**
	 *getting rgAuthPriRelationshipType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/rgAuthPriRelationshipTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgAuthPriRelationshipTypeRecordGroup(@RequestParam(value="contactType")final String contactType) {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidviresService.rgAuthPriRelationshipTypeRecordGroup(contactType);
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgAuthVisRelationshipType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/rgAuthVisRelationshipTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgAuthVisRelationshipTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidviresService.rgAuthVisRelationshipTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgAuthVisContactType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/rgAuthVisContactTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgAuthVisContactTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidviresService.rgAuthVisContactTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgStaffId LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/rgStaffIdRecordGroup",method=RequestMethod.GET)
	public List<StaffMembers> rgStaffIdRecordGroup(@RequestParam(value="agyLocId") final String agyLocId) {
		List<StaffMembers> recordList =new ArrayList<StaffMembers>();
		try {
			recordList = oidviresService.rgStaffIdRecordGroup(agyLocId);
		} catch(Exception e){
			StaffMembers obj = new StaffMembers();
			logger.error("", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgOffRestrictionType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/rgOffRestrictionTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffRestrictionTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oidviresService.rgOffRestrictionTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("", e);
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
	@RequestMapping(value="/oidvires/offVisitRestExecuteQuery", method=RequestMethod.POST)
	public List<OffenderRestrictions> offVisitRestExecuteQuery(@RequestBody OffenderRestrictions searchBean) {
		List<OffenderRestrictions> searchResult = new ArrayList<>();
		try {
			searchResult = oidviresService.offVisitRestExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderRestrictions bean = new OffenderRestrictions();
			logger.error("", e);
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
	@RequestMapping(value="/oidvires/offVisitRestCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offVisitRestCommit(@RequestBody OffenderRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidviresService.offVisitRestCommit(commitBean);
		}catch(Exception e){

			logger.error("offVisitRestCommit", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/offAuthVisitorsExecuteQuery", method=RequestMethod.POST)
	public List<OffenderContactPersons> offAuthVisitorsExecuteQuery(@RequestBody final OffenderContactPersons searchBean) {
		List<OffenderContactPersons> searchResult = new ArrayList<>();
		try {
			searchResult = oidviresService.offAuthVisitorsExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderContactPersons bean = new OffenderContactPersons();
			logger.error("", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/offVisitingExecuteQuery", method=RequestMethod.POST)
	public List<OffenderContactPersons> offVisitingExecuteQuery(@RequestBody final OffenderContactPersons searchBean) {
		List<OffenderContactPersons> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidviresService.offVisitingExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderContactPersons bean = new OffenderContactPersons();
			logger.error("offVisitingExecuteQuery", e);
			searchResult.add(bean);
		}
		return searchResult;
	}
	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidvires/offAuthVisitorsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offAuthVisitorsCommit(@RequestBody final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidviresService.offAuthVisitorsCommit(commitBean);
		}catch(Exception e){

			logger.error("", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/imageVisitExecuteQuery", method=RequestMethod.GET)
	public List<Images> imageVisitExecuteQuery(@RequestParam(value = "imageObjectId") final Long imageObjectId,
			@RequestParam(value = "type") final String type) {
		List<Images> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oidviresService.imageVisitExecuteQuery(imageObjectId,type,userName);
		} catch (Exception e) {
			Images bean = new Images();
			logger.error("", e);
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
	@RequestMapping(value="/oidvires/imageVisitCommit",method=RequestMethod.POST)
	public @ResponseBody Integer imageVisitCommit(@RequestBody final ImagesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			liReturn = oidviresService.imageVisitCommit(commitBean);
		}catch(Exception e){

			logger.error("", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/offAuthVisitOffExecuteQuery", method=RequestMethod.POST)
	public List<OffenderContactPersons> offAuthVisitOffExecuteQuery(@RequestBody final OffenderContactPersons searchBean) {
		List<OffenderContactPersons> searchResult = new ArrayList<>();
		try {
			searchResult = oidviresService.offAuthVisitorsExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderContactPersons bean = new OffenderContactPersons();
			logger.error("", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Perfomring basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidvires/offAuthVisitOffCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offAuthVisitOffCommit(@RequestBody final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidviresService.offAuthVisitOffCommit(commitBean);
		}catch(Exception e){

			logger.error("", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/imagesOffExecuteQuery", method=RequestMethod.GET)
	public List<Images> imagesOffExecuteQuery(@RequestParam(value = "imageObjectId") final Long imageObjectId,
			@RequestParam(value = "type") final String type) {
		List<Images> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oidviresService.imageVisitExecuteQuery(imageObjectId,type,userName);
		} catch (Exception e) {
			Images bean = new Images();
			logger.error("", e);
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
	@RequestMapping(value="/oidvires/imagesOffCommit",method=RequestMethod.POST)
	public @ResponseBody Integer imagesOffCommit(@RequestBody final ImagesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidviresService.imagesOffCommit(commitBean);
		}catch(Exception e){

			logger.error("", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/oidviresFindTagVisitsGetStaffId",method=RequestMethod.GET)
	public Integer oidviresFindTagVisitsGetStaffId() {
	Integer liReturn = 0;
	try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		liReturn = oidviresService.oidviresFindTagVisitsGetStaffId(userName);
	}catch(Exception e){

		logger.error("oidviresFindTagVisitsGetStaffId", e);
	}
	return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/oidviresIsOffenderBanRestriction",method=RequestMethod.GET)
	public List<String> oidviresIsOffenderBanRestriction(@RequestParam(value="offenderBookId") Long offenderBookId) {
	List<String> liReturn = new ArrayList<>();
	try {
		liReturn = oidviresService.oidviresIsOffenderBanRestriction(offenderBookId);
	}catch(Exception e){

		logger.error("oidviresIsOffenderBanRestriction", e);
	}
	return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidvires/oidviresIsPersonBanRestriction",method=RequestMethod.GET)
	public List<ReferenceCodes> oidviresIsPersonBanRestriction(@RequestParam(value="personId") Long personId) {
	List<ReferenceCodes> liReturn = new ArrayList<>();
	try {
		liReturn = oidviresService.oidviresIsPersonBanRestriction(personId);
	}catch(Exception e){

		logger.error("oidviresIsPersonBanRestriction", e);
	}
	return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidvires/chkNaBetweenOffenders", method = RequestMethod.GET)
	public String chkNaBetweenOffenders(@RequestParam(value = "glbOffBkgId") Long glbOffBkgId,
			@RequestParam(value = "visOffBkgId") Long visOffBkgId) {
		String retValue = "N";
		try {
			retValue = oidviresService.chkNaBetweenOffenders(glbOffBkgId, visOffBkgId);
		} catch (Exception e) {
			retValue = "N";
			logger.error("chkNaBetweenOffenders", e);
		}
		return retValue;
	}
	
}