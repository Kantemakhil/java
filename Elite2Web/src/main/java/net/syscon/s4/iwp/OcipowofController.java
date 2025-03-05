package net.syscon.s4.iwp;

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

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VAssignedOffenders;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;

@EliteController
public class OcipowofController {
	@Autowired
	private OcipowofService ocipowofService;

	private static Logger logger = LogManager.getLogger(OcipowofController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowof/staffExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> staffExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(user);
		try {
			searchResult = ocipowofService.staffExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in staffExecuteQuery Ocipowof :", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowof/vAssOffExecuteQuery", method = RequestMethod.POST)
	public List<VAssignedOffenders> vAssOffExecuteQuery(@RequestBody final VAssignedOffenders searchBean) {
		List<VAssignedOffenders> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocipowofService.vAssOffExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in vAssOffExecuteQuery Ocipowof:", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocipowof/vswaExecuteQuery", method = RequestMethod.POST)
	public List<StaffWorkAssignmentsV1> vswaExecuteQuery(@RequestBody final StaffWorkAssignmentsV1 searchBean) {
		List<StaffWorkAssignmentsV1> searchResult = new ArrayList<>();
		try {
			searchResult = ocipowofService.vswaExecuteQuery(searchBean);
		} catch (Exception e) {

			logger.error("Exception in vswaExecuteQuery Ocipowof :", e);

		}
		return searchResult;
	}

}