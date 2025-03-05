package net.syscon.s4.inst.incidentsoic;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffenses;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffensesCommitBean;

@EliteController
public class OcucieidController {

	private static Logger logger = LogManager.getLogger(OcucieidController.class.getName());

	@Autowired
	private OcucieidService ocucieidService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucieid/checkInsertUpdateDelete", method = RequestMethod.GET)
	public ExternalInvestigationOffenses checkForInsertOrUpdateAndDeleteExternalInvst() {
		ExternalInvestigationOffenses extInvOff = new ExternalInvestigationOffenses();
		try {
			extInvOff = ocucieidService.checkForInsertOrUpdateAndDeleteExternalInvst(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in checkForInsertOrUpdateAndDeleteExternalInvst  : " + e);
		}
		return extInvOff;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucieid/getAllExternalInvstDetails", method = RequestMethod.POST)
	public List<ExternalInvestigationOffenses> getAllExternalInvstDetails(
			@RequestBody ExternalInvestigationOffenses externalInvestigationOffenses) {
		List<ExternalInvestigationOffenses> returnList = new ArrayList<ExternalInvestigationOffenses>();
		try {
			returnList = ocucieidService.getAllExternalInvstDetails(externalInvestigationOffenses);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllExternalInvstDetails  : " + e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucieid/inserUpdateDeleteExternalInvst", method = RequestMethod.POST)
	public Integer inserUpdateDeleteExternalInvst(
			@RequestBody final ExternalInvestigationOffensesCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean != null) {
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			liReturn = ocucieidService.insertOrUpdateOrDelete(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in inserUpdateDeleteExternalInvst :: " + e);
		}
		return liReturn;
	}

}
