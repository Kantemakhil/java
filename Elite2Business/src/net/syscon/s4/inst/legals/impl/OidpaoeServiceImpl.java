package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.genericservices.ErrorCodes;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.OidpaoeRepository;
import net.syscon.s4.inst.legals.OidpaoeService;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEventResponse;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;

@Service
public class OidpaoeServiceImpl implements OidpaoeService {

    private static Logger logger = LogManager.getLogger(OidpaoeServiceImpl.class.getName());

    @Autowired
    OidpaoeRepository oidpaoeRepository;

    @Override
    public List<OffenderPayrolEvent> searchByOffebderBookId(Long offenderBookId) throws CustomException {
        List<OffenderPayrolEvent> listEvents = null;
        try {
            listEvents = oidpaoeRepository.findEventByOffebderBookId(offenderBookId);
            if(listEvents!= null && !listEvents.isEmpty()) {
            	List<OffenderSentenceAdjustment> offenderAdjustments = getOffenderAllAdjusments(offenderBookId);
            	for(OffenderPayrolEvent obj : listEvents) {
            		List<OffenderSentenceAdjustment> parEventAdjustment = offenderAdjustments.stream().filter(i-> i.getObjectType().equalsIgnoreCase("PAR") && i.getObjectId().equals(obj.getParoleEventId())).collect(Collectors.toList());
            		if(parEventAdjustment!= null && !parEventAdjustment.isEmpty()) {
            			obj.setListOffenderSentenceAdjustment(parEventAdjustment);
            		}
            	}
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
            throw new CustomException(HttpStatus.OK, ErrorCodes.REQUEST_INPUT_ERROR);
        }

        return listEvents;
    }

    @Override
    public List<OffenderPayrolEventResponse> addUpdateDeleteEvent(List<OffenderPayrolEvent> events)
            throws CustomException {
        List<OffenderPayrolEventResponse> addUpdateStatus = new ArrayList<>(1);
        try {
            addUpdateStatus = oidpaoeRepository.insertUpdateDeletePayrolEvent(events);
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCodes.REQUEST_INPUT_ERROR, e.getMessage());
        }
        return addUpdateStatus;
    }

    @Override
    public List<OffenderSentenceAdjustment> getEventAdjustment(Long offenderBookId, Long objectId, String objectType) throws CustomException {

        List<OffenderSentenceAdjustment> listEvents = null;
        try {
            listEvents = oidpaoeRepository.findAdjustmentByOffBookIdObjIdObjType(offenderBookId,objectId,objectType);
        } catch (Exception e) {
            logger.error("Exception :", e);
            e.printStackTrace();
            throw new CustomException(HttpStatus.OK, ErrorCodes.REQUEST_INPUT_ERROR);
        }

        return listEvents;
    
    }

	@Override
	public List<ReferenceCodes> getAdjustmentTyep() {
		List<ReferenceCodes> refList = oidpaoeRepository.getAdjustmentTyep();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

	@Override
	public List<OffenderSentenceAdjustment> getOffenderAllAdjusments(Long offenderBookId) throws CustomException {
		 List<OffenderSentenceAdjustment> listEvents = null;
	        try {
	            listEvents = oidpaoeRepository.findAdjustmentByOffBookId(offenderBookId);
	        } catch (Exception e) {
	            logger.error("Exception :", e);
	            e.printStackTrace();
	            throw new CustomException(HttpStatus.OK, ErrorCodes.REQUEST_INPUT_ERROR);
	        }
	        return listEvents;
	    
	}

}
