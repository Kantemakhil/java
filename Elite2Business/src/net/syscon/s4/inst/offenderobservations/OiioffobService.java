package net.syscon.s4.inst.offenderobservations;

import java.util.List;

import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationInquiry;

public interface OiioffobService {

	List<OffenderObservationInquiry> getOffenderPeriodInquiryQuery(OffenderObservationInquiry searchBean);

}
