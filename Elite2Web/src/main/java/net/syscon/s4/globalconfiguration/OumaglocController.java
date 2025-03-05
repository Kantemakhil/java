package net.syscon.s4.globalconfiguration;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyLocationsCommitBean;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.im.beans.AgyLocEstablishmentsCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

/**
 * class OumaglocController
 */
@EliteController
public class OumaglocController {
@Autowired
private OumaglocService oumaglocService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OumaglocController.class.getName());
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchBean AgencyLocations
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/agyLocExecuteQuery", method=RequestMethod.POST)
	public List<AgencyLocations> agyLocExecuteQuery(@RequestBody final AgencyLocations searchBean) {
		List<AgencyLocations> searchResult = new ArrayList<>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			searchResult = oumaglocService.agyLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In agyLocExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @Param commitBean AgencyLocationsCommitBean
	 *
	 * @return Integer
	 *
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumagloc/agyLocCommit",method=RequestMethod.POST)
	public @ResponseBody Integer agyLocCommit(@RequestBody final AgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumaglocService.agyLocCommit(commitBean);
		}catch(Exception e){
			logger.error("In agyLocCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *getting rgPhoneType LOV values 
	 *
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgPhoneTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgPhoneTypeRecordGroup();
		} catch(Exception e){
			logger.error("In rgPhoneTypeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgYnFlag LOV values 
	 *
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgYnFlagRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgYnFlagRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgYnFlagRecordGroup();
		} catch(Exception e){
			logger.error("In rgYnFlagRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgAgencyLocationType LOV values
	 *
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgAgencyLocationTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgAgencyLocationTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgAgencyLocationTypeRecordGroup();
		} catch(Exception e){
			logger.error("In rgAgencyLocationTypeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgJurisdiction LOV values 
	 *
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgJurisdictionRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgJurisdictionRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgJurisdictionRecordGroup();
		} catch(Exception e){
			logger.error("In rgJurisdictionRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgDisabilityAccessCode LOV values 
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgDisabilityAccessCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgDisabilityAccessCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgDisabilityAccessCodeRecordGroup();
		} catch(Exception e){
			logger.error("In rgDisabilityAccessCodeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgHousingLevelCodes LOV values 
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgHousingLevelCodesRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgHousingLevelCodesRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgHousingLevelCodesRecordGroup();
		} catch(Exception e){
			logger.error("In rgHousingLevelCodesRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgEstablishmentType LOV values 
	 *
	 *@return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/rgEstablishmentTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgEstablishmentTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			recordList = oumaglocService.rgEstablishmentTypeRecordGroup();
		} catch(Exception e){
			logger.error("In rgEstablishmentTypeRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	 *@return List<VAgencyAddresses>
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/vAgyAddrExecuteQuery", method=RequestMethod.POST)
	public List<VAgencyAddresses> vAgyAddrExecuteQuery(@RequestBody final VAgencyAddresses searchBean) {
		 List<VAgencyAddresses> searchResult = new ArrayList<>();
		 final VAgencyAddresses bean = new VAgencyAddresses();
		try {
			searchResult = oumaglocService.vAgyAddrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In vAgyAddrExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord <Phones>
	 *@return List<Phones>
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/phonesExecuteQuery", method=RequestMethod.POST)
	public List<Phones> phonesExecuteQuery(@RequestBody final Phones searchBean) {
		List<Phones> searchResult = new ArrayList<>();
		final Phones bean = new Phones();
		try {
			searchResult = oumaglocService.phonesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In phonesExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean <PhonesCommitBean>
	 *@return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumagloc/phonesCommit",method=RequestMethod.POST)
	public @ResponseBody Integer phonesCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumaglocService.phonesCommit(commitBean);
		}catch(Exception e){
			logger.error("In phonesCommit method : ", e);
		}
		return liReturn;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchBean <AgyLocEstablishments>
	 *@return List<AgyLocEstablishments>
	 *
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumagloc/agyLocEstExecuteQuery", method=RequestMethod.POST)
	public List<AgyLocEstablishments> agyLocEstExecuteQuery(@RequestBody final AgyLocEstablishments searchBean) {
		List<AgyLocEstablishments> searchResult = new ArrayList<>();
		final AgyLocEstablishments bean = new AgyLocEstablishments();
		try {
			searchResult = oumaglocService.agyLocEstExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In agyLocEstExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean <AgyLocEstablishmentsCommitBean>
	 *@return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oumagloc/agyLocEstCommit",method=RequestMethod.POST)
	public @ResponseBody Integer agyLocEstCommit(@RequestBody final AgyLocEstablishmentsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumaglocService.agyLocEstCommit(commitBean);
		}catch(Exception e){
			logger.error("In agyLocEstCommit method : ", e);
		}
		return liReturn;
	}
	
	
	@RequestMapping(value="/oumagloc/iepLevelCommit",method=RequestMethod.POST)
	public @ResponseBody Integer iepLevelCommit(@RequestBody final IEPLevelCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumaglocService.iepLevelCommit(commitBean);
		}catch(Exception e){
			logger.error("In iepLevelCommit method : ", e);
		}
		return liReturn;
	}
	
	@GetMapping("/oumagloc/getIepDetails")
	public IepLevelBean  fetchIepLevels(String agyLocId) {
		IepLevelBean profiles=new IepLevelBean();
		try {
			profiles=oumaglocService.getIepdetails(agyLocId);
		}catch (Exception e) {
			logger.error("Exception :", e);
		}
		return profiles;
	}

}