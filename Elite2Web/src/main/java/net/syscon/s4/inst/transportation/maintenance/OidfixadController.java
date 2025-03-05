package net.syscon.s4.inst.transportation.maintenance;

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
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssets;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssetsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.OidfixadCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Vehicles;
import net.syscon.s4.inst.transportation.maintenance.beans.VehiclesCommitBean;


@EliteController
public class OidfixadController {

	@Autowired
	private OidfixadService oidfixadService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidfixadController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidfixad/faExecuteQuery", method = RequestMethod.POST)
	public List<FixedAssets> faExecuteQuery(@RequestBody FixedAssets searchBean) {
		List<FixedAssets> searchResult = new ArrayList<>();
		try {
			searchResult = oidfixadService.faExecuteQuery(searchBean);
		} catch (Exception e) {
			FixedAssets bean = new FixedAssets();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidfixad/faCommit", method = RequestMethod.POST)
	public @ResponseBody Integer faCommit(@RequestBody FixedAssetsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidfixadService.faCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidfixad/vehExecuteQuery", method = RequestMethod.POST)
	public List<Vehicles> vehExecuteQuery(@RequestBody Vehicles searchBean) {
		List<Vehicles> searchResult = new ArrayList<>();
		try {
			searchResult = oidfixadService.vehExecuteQuery(searchBean);
		} catch (Exception e) {
			Vehicles bean = new Vehicles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidfixad/vehCommit", method = RequestMethod.POST)
	public @ResponseBody Integer vehCommit(@RequestBody VehiclesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidfixadService.vehCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

	// write commonSave api
	/**
	 * This method will call common Save Service
	 * 
	 * @param commitBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidfixad/oidfixadCommonSave", method = RequestMethod.POST)
	public Integer oidfixadCommonSave(@RequestBody final OidfixadCommitBean commitBean) {
		int liReturn = 0;
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			
			liReturn = oidfixadService.oidfixadCommonSave(commitBean);

		} catch (Exception e) {
			logger.error("Exception raised in oidfixadCommonSave", e);
		}
		return liReturn;
	}

}