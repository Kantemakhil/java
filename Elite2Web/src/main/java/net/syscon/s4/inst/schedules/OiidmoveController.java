package net.syscon.s4.inst.schedules;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VOffExtMovements;
import net.syscon.s4.inst.schedules.bean.VOffExtMovementsCommitBean;

 @EliteController
public class OiidmoveController {
	 
@Autowired
private OiidmoveService oiidmoveService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiidmoveController.class.getName());
	/**
	 * Fetching the record from database table
	 * @param searchBean {@link VOffExtMovements}
	 * @return a list of the VOffExtMovements {@link VOffExtMovements} for the matched VOffExtMovements
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiidmove/offEmExecuteQuery", method=RequestMethod.POST)
	public List<VOffExtMovements> offEmExecuteQuery(@RequestBody VOffExtMovements searchBean) {
		List<VOffExtMovements> searchResult = new ArrayList<>();
		try {
			searchBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oiidmoveService.offEmExecuteQuery(searchBean);
			
		} catch (Exception e) {
			logger.error("offEmExecuteQuery ",e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 * @param commitBean {@link VOffExtMovementsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiidmove/offEmCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody VOffExtMovementsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			
			liReturn = oiidmoveService.offEmCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 *getting cgfkOffEmMovementReasonCo LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiidmove/cgfkOffEmMovementReasonCoRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiidmoveService.cgfkOffEmMovementReasonCoRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *getting cgfkOffEmMovementType LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiidmove/cgfkOffEmMovementTypeRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiidmoveService.cgfkOffEmMovementTypeRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}