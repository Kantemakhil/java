package net.syscon.s4.sa.admin;

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
import net.syscon.s4.common.beans.MenuSecuritiesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VMenuSecs;

/**
 * Class OummenusController
 */
@EliteController
public class OummenusController {
@Autowired
private OummenusService oummenusService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OummenusController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oummenus/vMenuSecsExecuteQuery", method=RequestMethod.POST)
	public List<VMenuSecs> vMenuSecsExecuteQuery(@RequestBody final VMenuSecs searchBean) {
		List<VMenuSecs> searchResult = new ArrayList<>();
		try {
			searchResult = oummenusService.vMenuSecsExecuteQuery(searchBean);
		} catch (Exception e) {
			final VMenuSecs bean = new VMenuSecs();
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
	@RequestMapping(value="/oummenus/vMenuSecsCommit",method=RequestMethod.POST)
	public @ResponseBody Integer vMenuSecsCommit(@RequestBody final MenuSecuritiesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oummenusService.vMenuSecsCommit(commitBean);
		}catch(Exception e){

			logger.error("Exception : Oummenus",e);
		}
		return liReturn;
	}

	/**
	 *getting rgMenuSecDesc LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oummenus/rgMenuSecDescRecordGroup",method=RequestMethod.GET)
	public List<OmsModules> rgMenuSecDescRecordGroup() {
		List<OmsModules> recordList =new ArrayList<>();
		try {
			recordList = oummenusService.rgMenuSecDescRecordGroup();
		} catch(Exception e){
			final OmsModules obj = new OmsModules();
			logger.error("Exception : Oummenus:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}