package net.syscon.s4.inst.legals;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceHistory;
@EliteController
public class OidsenhyController {
	@Autowired
	private OidsenhyService oidsenhyService;
	private static Logger logger = LogManager.getLogger(OidsenhyController.class.getName());
	
	/***
	 * fetch data for grid from OFFENDER_SENT_CALCULATIONS table
	 * @param paramBean
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenhy/populateSentenceHistoryData", method = RequestMethod.POST)
	public List<SentenceHistory> populateSentenceHistoryData(@RequestBody final CourtCases offenderCases) {
			List<SentenceHistory> Result = new ArrayList<SentenceHistory>();
		try {		
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Result = oidsenhyService.populateSentenceHistoryData(offenderCases,authentication.getName());
			}
		catch (Exception e) {
			logger.error("populateSentenceHistoryData", e.getMessage());
			
		}
		return Result;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenhy/populateKeyDatesValues", method = RequestMethod.POST)
	public List<KeyDates> populateKeyDatesValues(@RequestBody List<KeyDates> keyDatesLablesList) {
		List<KeyDates> keyDatesValue = new ArrayList<KeyDates>();
			try {
				keyDatesValue = oidsenhyService.populateKeyDatesValues(keyDatesLablesList);
			}
		catch (Exception e) {
			logger.error("populateKeyDates:", e.getMessage());
		}
		return keyDatesValue;
	}
	

}
