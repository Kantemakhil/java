package net.syscon.s4.inst.movementexternal;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2CommitBean;

/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@EliteController
public class OiiwltwjController {
@Autowired
private OiiwltwjService oiiwltwjService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiiwltwjController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiwltwj/vTwlExecuteQuery", method=RequestMethod.GET)
	public List<VTransferWaitingLists2> vTwlExecuteQuery(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<VTransferWaitingLists2> searchResult = new ArrayList<>();
		VTransferWaitingLists2 bean = new VTransferWaitingLists2();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oiiwltwjService.vTwlExecuteQuery(caseLoadId,userName);
		} catch (Exception e) {
			
			logger.error(e);
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
	@RequestMapping(value="/oiiwltwj/vTwlCommit",method=RequestMethod.POST)
	public @ResponseBody String vTwlCommit(@RequestBody VTransferWaitingLists2CommitBean commitBean) {
		String liReturn = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oiiwltwjService.vTwlCommit(commitBean);
		}catch(Exception e){

			logger.error("vTwlCommit",e);
		}
		return liReturn;
	}

	/**
	 *getting cgfkVTwlDspDescription LOV values 
	 */
@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiwltwj/cgfkVTwlDspDescriptionRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkVTwlDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiiwltwjService.cgfkVTwlDspDescriptionRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkVTwlAgencyLocationTo LOV values 
	 */
@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiwltwj/cgfkVTwlAgencyLocationToRecordGroup",method=RequestMethod.GET)
	public List<AgencyLocations> cgfkVTwlAgencyLocationToRecordGroup() {
		List<AgencyLocations> recordList =new ArrayList<AgencyLocations>();
		try {
			recordList = oiiwltwjService.cgfkVTwlAgencyLocationToRecordGroup();
		} catch(Exception e){
			AgencyLocations obj = new AgencyLocations();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkVTwlDspDescription3 LOV values 
	 */
@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiwltwj/cgfkVTwlDspDescription3RecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkVTwlDspDescription3RecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiiwltwjService.cgfkVTwlDspDescription3RecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting rgCancelReason LOV values 
	 */
@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiwltwj/rgCancelReasonRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiiwltwjService.rgCancelReasonRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@RequestMapping(value="/oiiwltwj/cgfkchkVTwlVTwlAgyLoc",method=RequestMethod.GET)
	public AgencyLocations cgfkchkVTwlVTwlAgyLoc(@RequestParam(value = "agencyLocId") final String agencyLocId) {
		AgencyLocations recordList =new AgencyLocations();
		try {
			recordList = oiiwltwjService.cgfkchkVTwlVTwlAgyLoc(agencyLocId);
		} catch(Exception e){
			logger.error("cgfkchkVTwlVTwlAgyLoc",e);
		}
		return recordList;
	}
	
	
	@RequestMapping(value="/oiiwltwj/CgfkchkVTwlVTwlVOffBkg",method=RequestMethod.GET)
	public VHeaderBlock CgfkchkVTwlVTwlVOffBkg(@RequestParam(value = "offenderBookId") final Long offenderBookId) {
		VHeaderBlock recordList =new VHeaderBlock();
		try {
			recordList.setOffenderBookId(BigDecimal.valueOf(offenderBookId));
			recordList = oiiwltwjService.CgfkchkVTwlVTwlVOffBkg(recordList);
		} catch(Exception e){
			logger.error("CgfkchkVTwlVTwlVOffBkg",e);

		}
		return recordList;
	}

}