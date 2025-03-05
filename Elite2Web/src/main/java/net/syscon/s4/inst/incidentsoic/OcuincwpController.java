package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeaponsCommitBean;


@EliteController
public class OcuincwpController {
	
	@Autowired(required = true)
	private OcuincwpService ocuincwpService;
	
	private static Logger logger = LogManager.getLogger(OcuincwpController.class);
	
	
	@RequestMapping(value = "ocuincwp/offednerweaponsAllData", method = RequestMethod.POST)
	public List<OffenderWeapons> offenderWeaponsData(@RequestBody final OffenderWeapons objSearchDao) {
		List<OffenderWeapons> searchResult = new ArrayList<>();
		try {
			searchResult = ocuincwpService.offenderWeaponsData( objSearchDao);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsExecuteQuery", e);
		}
		return searchResult;
	}
	
	@RequestMapping(value = "ocuincwp/offednerweaponsInsertCommit", method = RequestMethod.POST)
	public Integer offednerWeaponsInsertQuery(@RequestBody final OffenderWeaponsCommitBean commitBean) {
		int liReturn = 0;
		try {
			commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = ocuincwpService.offednerWeaponsInsertQuery(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	

	

}
