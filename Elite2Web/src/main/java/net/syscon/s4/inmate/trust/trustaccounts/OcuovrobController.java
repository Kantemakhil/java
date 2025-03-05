package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;

/**
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@EliteController
public class OcuovrobController {
@Autowired
private OcuovrobService ocuovrobService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcuovrobController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocuovrob/offBncExecuteQuery", method=RequestMethod.POST)
	public List<OffenderBeneficiaries> offBncExecuteQuery(@RequestBody final OffenderBeneficiaries searchBean) {
		List<OffenderBeneficiaries> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = ocuovrobService.offBncExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderBeneficiaries bean = new OffenderBeneficiaries();
			logger.error(e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocuovrob/offBncCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offBncCommit(@RequestBody final OffenderBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocuovrobService.offBncCommit(commitBean);
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
	@RequestMapping(value="/ocuovrob/sysPflExecuteQuery", method=RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocuovrobService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ocuovrob/sysPflCommit",method=RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = ocuovrobService.sysPflCommit(commitBean);
		}catch(Exception e){

			logger.error(e);
		}
		return liReturn;
	}

}