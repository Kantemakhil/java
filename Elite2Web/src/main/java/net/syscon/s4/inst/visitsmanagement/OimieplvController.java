package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

@EliteController
public class OimieplvController {

	@Autowired
	private OimieplvService oimieplvService;

	private static Logger logger = LogManager.getLogger(OimieplvController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimieplv/iepLevelCommit", method = RequestMethod.POST)
	public Integer iepLevelCommit(@RequestBody final IEPLevelCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oimieplvService.commitBeanIepLevels(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in iepLevelCommit :: " + e);
		}

		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimieplv/getAllIepLevelCodes", method = RequestMethod.GET)
	public List<IepLevelBean> getAllIepLevelCodes() {
		List<IepLevelBean> liReturn = new ArrayList<IepLevelBean>();
		try {
			liReturn = oimieplvService.getAllIepLevelCodes();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllIepLevelCodes  : "+ e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimieplv/getSystemProfileValue", method = RequestMethod.GET)
	public String getSystemProfileValue() {
		String searchResult = null;
		try {
			searchResult = oimieplvService.getSystemProfileValue();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getSystemProfileValue  : "+ e);
		}
		return searchResult;
	}

}
