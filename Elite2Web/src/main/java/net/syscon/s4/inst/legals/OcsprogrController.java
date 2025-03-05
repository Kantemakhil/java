package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.beans.OffenderSentences;

@EliteController
public class OcsprogrController {

	private static Logger logger = LogManager.getLogger(OcsprogrController.class.getName());

	@Autowired
	private OcsprogrService ocsprogrService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/rgRefCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocsprogrService.rgRefCodeRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in Class " + this.getClass().getName() + "rgRefCodeRecordGroup :" + e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/rgAccProgramRecordGroup", method = RequestMethod.GET)
	public List<ProgramServices> rgAccProgramRecordGroup() {
		List<ProgramServices> recordList = new ArrayList<ProgramServices>();
		try {
			recordList = ocsprogrService.rgAccProgramRecordGroup();
		} catch (Exception e) {
			ProgramServices obj = new ProgramServices();
			logger.error("Exception in Class " + this.getClass().getName() + "rgAccProgramRecordGroup :" + e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/rgRefCodeProviderGroup", method = RequestMethod.GET)
	public List<TeamMembers> rgRefCodeProviderGroup(String userId) {
		List<TeamMembers> recordList = new ArrayList<TeamMembers>();
		try {
			recordList = ocsprogrService.rgRefCodeProviderGroup(userId);
		} catch (Exception e) {
			TeamMembers obj = new TeamMembers();
			logger.error("Exception in Class " + this.getClass().getName() + "rgRefCodeProviderGroup :" + e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/rgProviderRecordGroupTeam", method = RequestMethod.GET)
	public List<VProgramProviders> rgProviderRecordGroupTeam() {
		List<VProgramProviders> recordList = new ArrayList<VProgramProviders>();
		try {
			recordList = ocsprogrService.rgProviderRecordGroupTeam();
		} catch (Exception e) {
			final VProgramProviders obj = new VProgramProviders();
			logger.error("Exception in Class " + this.getClass().getName() + "rgProviderRecordGroupTeam :" + e.getMessage());
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj); 
		}
		return recordList;
	}
	

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/offExecQuery", method = RequestMethod.GET)
	public List<OffenderBookings> offExecQuery(@RequestParam List<String> status, @RequestParam String programId,
			@RequestParam String intProviderPartyId, @RequestParam String extProviderPartyId, @RequestParam String currentCaseload) {
		List<OffenderBookings> recordList = new ArrayList<OffenderBookings>();
		try { 
			recordList = ocsprogrService.offExecQuery(status, programId,intProviderPartyId,extProviderPartyId,currentCaseload);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "offExecQuery :" + e.getMessage());
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocsprogr/rgOffenderSentencesRecordGroupComm", method = RequestMethod.POST)
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(
			@RequestBody OffenderBookings searchBean) {
		List<OffenderSentences> recordList = new ArrayList<OffenderSentences>();
		try {
			recordList = ocsprogrService.rgOffenderSentencesRecordGroup(searchBean);
		} catch (Exception e) {
			logger.error("Exception : Ocdprogr:", e);
		}
		return recordList;
	}
	 

}
