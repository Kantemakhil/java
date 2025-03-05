package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEventResponse;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;

public interface OidpaoeRepository {

    List<OffenderPayrolEvent> findEventByOffebderBookId(Long offenderBookId);

    String insertUpdatePayrolEvent(OffenderPayrolEvent event);

    List<OffenderPayrolEventResponse> insertUpdateDeletePayrolEvent(List<OffenderPayrolEvent> events) throws CustomException;

    List<OffenderSentenceAdjustment> findAdjustmentByOffBookIdObjIdObjType(Long offenderBookId, Long objectId, String objectType);

	List<ReferenceCodes> getAdjustmentTyep();

	List<OffenderSentenceAdjustment> findAdjustmentByOffBookId(Long offenderBookId);

}
