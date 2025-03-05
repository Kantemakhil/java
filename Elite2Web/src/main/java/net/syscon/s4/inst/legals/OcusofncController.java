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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;


	@EliteController
	public class OcusofncController {
		
		@Autowired
		private OcusofncService ocusofncService;
		
		private static Logger logger = LogManager.getLogger(OcuccideController.class.getName());
		
		/***
		 * method written for Court Case Identifiers
		 * @param type
		 * @return
		 */
	/*	@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/ocusofnc/fetechOffensesdialogData", method = RequestMethod.POST)
		public List<OffensesOutcome> fetechOffensesdialogData(@RequestBody Sentences selectedSentenceData) {
				List<OffensesOutcome> offensesDailogList = new ArrayList<OffensesOutcome>();
			try {			
				offensesDailogList = ocusofncService.fetechOffensesdialogData(selectedSentenceData);
				}
			catch (Exception e) {
				logger.error("fetechOffensesdialogData", e);
			}
			return offensesDailogList;
		}*/
		
		//@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "ocusofnc/fetechOffensesdialogData", method = RequestMethod.POST)
		public List<OffensesOutcome> fetechOffensesdialogData(@RequestBody OffensesOutcome selectedSentenceData) {
				List<OffensesOutcome> offensesDailogList = new ArrayList<OffensesOutcome>();
			try {			
				offensesDailogList = ocusofncService.fetechOffensesdialogData(selectedSentenceData);
				}
			catch (Exception e) {
				logger.error("", e);
			}
			return offensesDailogList;
		}

		

}
