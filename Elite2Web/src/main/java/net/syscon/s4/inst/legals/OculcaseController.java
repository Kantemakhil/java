package net.syscon.s4.inst.legals;

import java.util.ArrayList;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;


@EliteController
public class OculcaseController {
	
	@Autowired
	private OculcaseService  oculcaseService;
	
	private static Logger logger = LogManager.getLogger(OcuholdsController.class.getName());
	
	/***
	 * populateLinkCase method for fetch data of link case block
	 * @param courtCases
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculcase/populateLinkCase", method = RequestMethod.POST)
	public List<CourtCases> populateLinkCase(@RequestBody final CourtCases courtCases) {
		List<CourtCases> result = new ArrayList<CourtCases>();
		try {
			result = oculcaseService.populateLinkCase(courtCases);
		} catch (Exception e) {
			logger.error("populateLinkCase", e);
		}
		return result;
	}
			
	/***
	 *  populateLinkLovType method for Lov
	 * @param courtCases
	 * @return
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculcase/populateLinkLovType", method = RequestMethod.POST)
	public List<CourtCases> populateLinkLovType(@RequestBody final CourtCases courtCases) {
		List<CourtCases> result = new ArrayList<CourtCases>();
		try {
			result = oculcaseService.populateLinkLovType(courtCases);
		} catch (Exception e) {
			logger.error("populateLinkLovType", e);
		}
		return result;
	}
		
	/***
	 * populateSelectHearing method for fetching data of Select Hearing block
	 * @param courtEvent
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculcase/populateSelectHearing", method = RequestMethod.POST)
	public List<CourtEvent> populateSelectHearing(@RequestBody final CourtEvent courtEvent) {
		List<CourtEvent> result = new ArrayList<CourtEvent>();
		try {
			result = oculcaseService.populateSelectHearing(courtEvent);
		} catch (Exception e) {
			logger.error("populateSelectHearing", e);
		}
		return result;
	}
		
	/***
	 * method for liking case to other cases 	
	 * @param courtEvent
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oculcase/linkCase", method = RequestMethod.POST)
	public List<CourtEvent>linkCase(@RequestBody final CourtEvent courtEvent) {
		List<CourtEvent> result = new ArrayList<CourtEvent>();
		try {
			result = oculcaseService.linkCase(courtEvent);
		} catch (Exception e) {
			logger.error("linkCase", e);
		}
		return result;
	}
	
	/***
	 * method for unlink the link cases
	 * @param courtEvent
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oculcase/unLinkCase", method = RequestMethod.POST)
	public List<CourtEvent> unLinkCase(@RequestBody final CourtEvent courtEvent) {
		List<CourtEvent> result = new ArrayList<CourtEvent>();
		try {
			result = oculcaseService.unLinkCase(courtEvent);
		} catch (Exception e) {
			logger.error("linkCase", e);
		}
		return result;
	}
	
	/***
	 * 
	 * @param caseId
	 * @param offenderBookId
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculcase/chkSentences", method = RequestMethod.GET)
	public boolean chkSentences(@RequestParam Integer caseId, @RequestParam Integer offenderBookId) {
		boolean result = false;
		try {
			result = oculcaseService.chkSentences(caseId, offenderBookId);

		} catch (Exception e) {
			logger.error("chkSentences", e.getMessage());
		}
		return result;
	}

}
