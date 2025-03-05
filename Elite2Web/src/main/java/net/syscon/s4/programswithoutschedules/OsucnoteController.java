package net.syscon.s4.programswithoutschedules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderAlert;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.programswithoutschedules.OsucnoteService;


@EliteController
public class OsucnoteController {
	@Autowired
	private OsucnoteService osucnoteService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsucnoteController.class.getName());

	/**
	 *getting offender fname lname
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osucnote/getDisplayAuto",method=RequestMethod.GET)
	public String getDisplayAuto(@RequestParam(value = "offenderBookId") final String offenderBookId) {
		String offendername = null;
		try {
			offendername = osucnoteService.getDisplayAuto(offenderBookId);
		} catch(Exception e){
			logger.error("Exception :",e);
			return e.getMessage();
		}
		return offendername;
	}

	/**
	 *getting rgWorks LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osucnote/rgWorksRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgWorksRecordGroup(@RequestParam(value = "caseLoadType") final String caseLoadType) {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = osucnoteService.rgWorksRecordGroup(caseLoadType);
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *save data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/osucnote/submitAdhocWorkflow",method=RequestMethod.POST)
	public @ResponseBody List<TagWorkflowBrowseQueue> submitAdhocWorkflow(@RequestBody final TagWorkflowBrowseQueue newMemoModel) {
		List<TagWorkflowBrowseQueue> liReturn = new ArrayList<>();
		try {
			liReturn = osucnoteService.submitAdhocWorkflow(newMemoModel);

		} catch (Exception e) {
			final TagWorkflowBrowseQueue error = new TagWorkflowBrowseQueue();
			logger.error("Exception : submitAdhocWorkflow", e);
			error.setErrorMessage(e.getMessage());
			liReturn.add(error);
		}

		return liReturn;
	}

	RestTemplate restTemplate = new RestTemplate();
	@RequestMapping(value="/osucnote/getCaseNoteData",method=RequestMethod.POST)
	public @ResponseBody String getCaseNoteData(@RequestBody final String offenderAlert) {
		String caseNoteData = null;
		Map<String, Object> casenote = new HashMap<String, Object>();
		OffenderAlert offAlert = new OffenderAlert();

		ObjectMapper mapper = new ObjectMapper();
		try {
			offAlert = mapper.readValue(offenderAlert, OffenderAlert.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		casenote.put("offenderBookId" , offAlert.getOffenderBookId());
		casenote.put("msgId" , "INST");
		casenote.put("messageText" , "Case note Boss Search");
		casenote.put("senderId" , "OMS_OWNER");
		casenote.put("firstName" , "6442 STAG, QA (0001026492)");
		casenote.put("workType" , "CNOTE");
		casenote.put("workSubType" , "BOSS");
		casenote.put("enableOrDisable" , 0);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			caseNoteData = ow.writeValueAsString(casenote);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return caseNoteData;
	}

	@RequestMapping(value="/osucnote/submitAdhocWorkflowCmd",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> createCaseNote(@RequestBody final String offenderCaseNote) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Map<String, Object> respone = new HashMap<String, Object>();
		TagWorkflowBrowseQueue newMemoModel = new TagWorkflowBrowseQueue();
		ObjectMapper mapper = new ObjectMapper();
		try {
			newMemoModel = mapper.readValue(offenderCaseNote, TagWorkflowBrowseQueue.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String firstName = osucnoteService.getDisplayAuto(newMemoModel.getOffenderBookId().toString());
		newMemoModel.setFirstName(firstName);
		newMemoModel.setEnableOrDisable(0);
		newMemoModel.setSenderId(userName);
		newMemoModel.setMsgId("AUTO");

		List<TagWorkflowBrowseQueue> liReturn = new ArrayList<>();
		try {
			liReturn = osucnoteService.submitAdhocWorkflow(newMemoModel);
		} catch (Exception e) {
			final TagWorkflowBrowseQueue error = new TagWorkflowBrowseQueue();
			logger.error("Exception : submitAdhocWorkflow", e);
			error.setErrorMessage(e.getMessage());
			respone.put("Response", "Case Note Creation Faild");
		}
		respone.put("Response", "Case Note Created");
		return respone;
	}

}