package net.syscon.s4.inst.offenderspecific;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * class OiiemoveController 
 */
@EliteController
public class OiiemoveController {
@Autowired
private OiiemoveService oiiemoveService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiiemoveController.class.getName());
	/**
	 *getting rgOffEmMovementReasonCo LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEmMovementReasonCoRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEmMovementReasonCoRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEmMovementReasonCoRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEmMovementReasonCoRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgOffEmMovementType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEmMovementTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEmMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEmMovementTypeRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEmMovementTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgOffEm1DirectionCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEm1DirectionCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEm1DirectionCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEm1DirectionCodeRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEm1DirectionCodeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgOffEm1MovementType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEm1MovementTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEm1MovementTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEm1MovementTypeRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEm1MovementTypeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgOffEm1MovementReasonC LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEm1MovementReasonCRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEm1MovementReasonCRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEm1MovementReasonCRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEm1MovementReasonCRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *getting rgOffEmDirectionCode LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/rgOffEmDirectionCodeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgOffEmDirectionCodeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiiemoveService.rgOffEmDirectionCodeRecordGroup();
		} catch(Exception e){
			logger.error("In rgOffEmDirectionCodeRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/offEmExecuteQuery", method=RequestMethod.POST)
	public List<OffenderExternalMovements> offEmExecuteQuery(@RequestBody final OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<OffenderExternalMovements>();
		try {
			searchResult = oiiemoveService.offEmExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offEmExecuteQuery method : ", e);
		}
		return searchResult;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiemove/offEm1ExecuteQuery", method=RequestMethod.POST)
	public List<OffenderExternalMovements> offEm1ExecuteQuery(@RequestBody final OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<OffenderExternalMovements>();
		try {
			searchResult = oiiemoveService.offEm1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In offEm1ExecuteQuery method : ", e);
		}
		return searchResult;
	}
	
	/**
	 * getting alAgyLocIdRg LOV values
	 *
	 * @return List<AgencyLocations>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiemove/alAgyLocIdRgRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		final AgencyLocations bean = new AgencyLocations();
		try {
			recordList = oiiemoveService.alAgyLocIdRgRecordGroup();
		} catch (Exception e) {
			logger.error("In alAgyLocIdRgRecordGroup method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

}