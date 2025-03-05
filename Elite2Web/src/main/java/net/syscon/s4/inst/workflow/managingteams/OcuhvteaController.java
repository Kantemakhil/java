package net.syscon.s4.inst.workflow.managingteams;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.beans.VOffenderTeamAssignHty;


/**
 * class OcuhvteaController 
 */
@EliteController
public class OcuhvteaController {
@Autowired
private OcuhvteaService ocuhvteaService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcuhvteaController.class.getName());
	/**
	 *getting rgFunction LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocuhvtea/rgFunctionRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ocuhvteaService.rgFunctionRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocuhvtea:",e);
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
	@RequestMapping(value="/ocuhvtea/offVteamHtyExecuteQuery", method=RequestMethod.POST)
	public List<VOffenderTeamAssignHty> offVteamHtyExecuteQuery(@RequestBody final  VOffenderTeamAssignHty searchBean) {
		List<VOffenderTeamAssignHty> searchResult = new ArrayList<>();
		try {
			searchResult = ocuhvteaService.offVteamHtyExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderTeamAssignHty bean = new VOffenderTeamAssignHty();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}