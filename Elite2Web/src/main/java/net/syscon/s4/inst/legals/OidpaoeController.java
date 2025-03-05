package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEventResponse;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidpaoeController {

    @Autowired
    private OidpaoeService oidpaoeService;
    
    @Autowired
	private ProsmainService prosmainService;
    
    @Autowired
	private OcmpconfService ocmpconfService;
	
	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OcipenscService ocipenscService;

    @RequestMapping(value = "/parol/event", method = RequestMethod.GET)
    public ResponseEntity<List<OffenderPayrolEvent>> getEvents(@RequestParam("offender_book_id") Long offenderBookId)
            throws CustomException {
        List<OffenderPayrolEvent> listEvents = null;
        listEvents = oidpaoeService.searchByOffebderBookId(offenderBookId);
        return new ResponseEntity<>(listEvents, HttpStatus.OK);
    }

    @RequestMapping(value = "/parol/events", method = RequestMethod.POST)
    public ResponseEntity<List<OffenderPayrolEventResponse>> addUpdateEventWithAdjurtment(
            @RequestBody List<OffenderPayrolEvent> events,  @RequestHeader HttpHeaders headers) throws CustomException {
        List<OffenderPayrolEventResponse> addUpdateStatus = new ArrayList<>(1);
        List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        events.stream().forEach(e -> e.setCreateUserId(userName));
        addUpdateStatus = oidpaoeService.addUpdateDeleteEvent(events);
        if(addUpdateStatus!= null && addUpdateStatus.get(0)!=null && addUpdateStatus.get(0).getEventResponse()!=null && addUpdateStatus.get(0).getEventResponse().equals("Success") && events.get(0).getCalcReason()!= null && !events.get(0).getCalcReason().trim().isEmpty()) {
        	// To call sentence calculation process
        	OdynfrmSubmitDataBean odynfrmSubmitDataBean = new OdynfrmSubmitDataBean();
			String formIdentifier = "{\"offenderBookId\":\""+events.get(0).getOffenderBookId()+"\"}";
			odynfrmSubmitDataBean.setSearchString(formIdentifier);
			odynfrmSubmitDataBean.setFormIdentifier(formIdentifier);
			odynfrmSubmitDataBean.setActionType("Modification");
			odynfrmSubmitDataBean.setCalcReason(events.get(0).getCalcReason());
			odynfrmSubmitDataBean.setCreateUserId(userName);
			Map<String,Object> applicationStatus = omss40Service.getApplicationStatus();
			List<OdynfrmSubmitDataBean> getoffenderPendEvents = ocipenscService.getPendingSentenceCalcEvents(odynfrmSubmitDataBean);
			if((getoffenderPendEvents != null && getoffenderPendEvents.size()>0) || (!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
				odynfrmSubmitDataBean.setModuleName("OIDPAROE");
				Integer returnValue = ocmpconfService.sentenceEngineOffline(odynfrmSubmitDataBean);
				if(returnValue == 2) {
					if(ApplicationConstants.YES_FLAG.equalsIgnoreCase(ocmpconfService.getAutomaticStatusFlag("SENTCALWARNFLAG"))) {
						if((!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
							returnValue = 3;
						}
					} else {
						returnValue = 4;
					}
				}
				addUpdateStatus.get(0).setEventResponse(returnValue.toString());
			} else {
				prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "61");
			}
        }

        return new ResponseEntity<>(addUpdateStatus, HttpStatus.OK);
    }
    
    @GetMapping(value = "/parol/event/adjusment")
    public ResponseEntity<List<OffenderSentenceAdjustment>> getOffenderEventAdjusments(
            @RequestParam("offender_book_id") Long offenderBookId,@RequestParam("object_id") Long objectId,@RequestParam("object_type") String objectType) throws CustomException {
        List<OffenderSentenceAdjustment> adjurstmentList = new ArrayList<>();

        adjurstmentList = oidpaoeService.getEventAdjustment(offenderBookId,objectId,objectType);

        return new ResponseEntity<>(adjurstmentList, HttpStatus.OK);
    }
    
    
	@RequestMapping(value = "/parole/adjustmentTyep", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidpaoeService.getAdjustmentTyep();
		} catch (Exception e) {
						
		}
		return recordList;
	}
	
	  @GetMapping(value = "/parole/adjustments")
	    public ResponseEntity<List<OffenderSentenceAdjustment>> getOffenderAllAdjusments(
	            @RequestParam("offender_book_id") Long offenderBookId) throws CustomException {
	        List<OffenderSentenceAdjustment> adjurstmentList = new ArrayList<>();

	        adjurstmentList = oidpaoeService.getOffenderAllAdjusments(offenderBookId);

	        return new ResponseEntity<>(adjurstmentList, HttpStatus.OK);
	    }

}
