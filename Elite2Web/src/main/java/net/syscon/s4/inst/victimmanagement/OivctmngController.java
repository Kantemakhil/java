package net.syscon.s4.inst.victimmanagement;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogs;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogsCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferences;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferencesCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffenders;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffendersCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecordsCommitBean;

@EliteController
public class OivctmngController {

	@Autowired
	private OivctmngService oivctmngService;
	
	@Autowired
	private OsiosearService osiosearService;
	
	@Autowired
	private OcdxprogService ocdxprogService;

	private static Logger logger = LogManager.getLogger(OivctmngController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/getAllVictimRecords", method = RequestMethod.GET)
	public List<VictimRecords> getAllVictimRecords() {
		List<VictimRecords> liReturn = new ArrayList<VictimRecords>();
		try {
			liReturn = oivctmngService.getAllVictimRecords();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllVictimRecords  : " + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/saveVictimRecords", method = RequestMethod.POST)
	public Integer saveVictimRecords(@RequestBody final VictimRecordsCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			liReturn = oivctmngService.saveVictimRecords(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in saveVictimRecords :: " + e);
		}

		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/getAllVictimLinkedOffenders", method = RequestMethod.GET)
	public List<VictimLinkedOffenders> getAllVictimLinkedOffenders(
			@RequestParam(value = "victimId") final Integer victimId) {
		List<VictimLinkedOffenders> liReturn = new ArrayList<VictimLinkedOffenders>();
		try {
			liReturn = oivctmngService.getAllVictimLinkedOffenders(victimId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllVictimLinkedOffenders  : " + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/saveVictimLinkedOffenders", method = RequestMethod.POST)
	public Integer saveVictimLinkedOffenders(@RequestBody final VictimLinkedOffendersCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			liReturn = oivctmngService.saveVictimLinkedOffenders(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in saveVictimLinkedOffenders :: " + e);
		}

		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/getAllVictimContactLogs", method = RequestMethod.GET)
	public List<VictimContactLogs> saveVictimContactLogs(@RequestParam(value = "victimId") final Integer victimId) {
		List<VictimContactLogs> liReturn = new ArrayList<VictimContactLogs>();
		try {
			liReturn = oivctmngService.getAllVictimContactLogs(victimId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllVictimContactLogs  : " + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/saveVictimContactLogs", method = RequestMethod.POST)
	public Integer saveVictimContactLogs(@RequestBody final VictimContactLogsCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			liReturn = oivctmngService.saveVictimContactLogs(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in saveVictimContactLogs :: " + e);
		}

		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/getAllvictimContactPreferences", method = RequestMethod.GET)
	public List<VictimContactPreferences> getAllvictimContactPreferences(
			@RequestParam(value = "victimId") final Integer victimId) {
		List<VictimContactPreferences> liReturn = new ArrayList<VictimContactPreferences>();
		try {
			liReturn = oivctmngService.getAllvictimContactPreferences(victimId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllvictimContactPreferences  : " + e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/saveVictimContactPreferences", method = RequestMethod.POST)
	public Integer saveVictimContactPreferences(@RequestBody final VictimContactPreferencesCommitBean commitBean) {
		Integer liReturn = 0;

		try {
			liReturn = oivctmngService.saveVictimContactPreferences(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in saveVictimContactPreferences :: " + e);
		}

		return liReturn;
	}
	
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/getVictimId", method = RequestMethod.GET)
	public Integer getVictimId() {
		Integer victimId = 0;
		try {
			victimId = oivctmngService.getVictimId();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getVictimId :: " + e);
		}
		return victimId;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			}
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oivctmng/rgOffenderSentencesRecordGroupBothCustAndNonCust", method = RequestMethod.GET)
	public List<OffenderSentences> rgOffenderSentencesRecordGroupBothCustAndNonCust(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		List<OffenderSentences> recordList = new ArrayList<>();
		try {
			recordList = ocdxprogService.rgOffenderSentencesRecordGroupBothCustAndNonCust(offenderBookId);
		} catch (Exception e) {
			logger.error("rgOffenderSentencesRecordGroupBothCustAndNonCust : Ocdprogr:", e);
		}
		return recordList;
	}
}
