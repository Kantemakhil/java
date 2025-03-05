package net.syscon.s4.inst.offenderobservations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationInquiry;

@EliteController
public class OiioffobController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiioffobController.class.getName());
	
	@Autowired
	private OiioffobService oiioffobService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioffob/getOffenderPeriodInquiryQuery", method = RequestMethod.POST)
	public List<OffenderObservationInquiry> getOffenderPeriodInquiryQuery(@RequestBody final OffenderObservationInquiry searchBean) {
		List<OffenderObservationInquiry> recordList = new ArrayList<OffenderObservationInquiry>();
		final OffenderObservationInquiry bean = new OffenderObservationInquiry();
		try {
			recordList = oiioffobService.getOffenderPeriodInquiryQuery(searchBean);
		} catch (Exception e) {
			final OffenderObservationInquiry error = new OffenderObservationInquiry();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
}