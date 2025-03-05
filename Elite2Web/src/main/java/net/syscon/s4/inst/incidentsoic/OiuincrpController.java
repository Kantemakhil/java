package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.inst.incidentsoic.impl.OiuincrpServiceImpl;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OiuincrpController {
	
	@Autowired
	private OiuincrpServiceImpl oiuincrpsevice;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuincrpController.class.getName());

	/**
	 * Creates new OiuincrpController class Object
	 */
	public OiuincrpController() {
		
	}

	/**
	 * @return List<AgencyIncidentRepairs>
	 * 
	 * @param AgencyIncidentRepairs
	 *            searchBean
	 */
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiuincrp/agyIncExecuteQuery", method = RequestMethod.POST)
	public List<AgencyIncidentRepairs> agyIncExecuteQuery(@RequestBody final AgencyIncidentRepairs searchBean) {
		List<AgencyIncidentRepairs> searchResult = new ArrayList<AgencyIncidentRepairs>();
		final AgencyIncidentRepairs bean = new AgencyIncidentRepairs();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiuincrpsevice.agyIncExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("agyIncExecuteQuery",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIUINCRP");
	}
}