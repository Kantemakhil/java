package net.syscon.s4.inst.legals;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEventResponse;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;

public interface OidpaoeService {

    List<OffenderPayrolEvent> searchByOffebderBookId(Long offenderBookId) throws CustomException;

    List<OffenderPayrolEventResponse> addUpdateDeleteEvent(List<OffenderPayrolEvent> events) throws CustomException;

    List<OffenderSentenceAdjustment> getEventAdjustment(Long offenderBookId, Long objectId, String objectType) throws CustomException ;

	List<ReferenceCodes> getAdjustmentTyep();

	List<OffenderSentenceAdjustment> getOffenderAllAdjusments(Long offenderBookId) throws CustomException;

}
