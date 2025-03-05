package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.SentenceAggregates;

@EliteController
public class OidsenkdController {

	private static Logger logger = LogManager.getLogger(OcdcCasesController.class.getName());
	
	@Autowired
	OidsenkdService oidsenkdService; 
	/**
	 * To populate Sentence Aggregate Data.
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidsenkd/populateSentenceAggregateData", method = RequestMethod.GET)
	public List<SentenceAggregates> populateAggregatesData(@RequestParam("offenderBookId") Long offenderBookId) {
		
		List<SentenceAggregates> sentenceAggregateList = new ArrayList<SentenceAggregates>();
		try {			
			sentenceAggregateList = oidsenkdService.populateAggregatesData(offenderBookId);
		} catch (Exception e) {
			logger.error("populateCourtData", e);
		}
		return sentenceAggregateList;
	}
	
}
