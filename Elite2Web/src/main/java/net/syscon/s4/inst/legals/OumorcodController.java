package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.OffenseResultCodes;


@EliteController
public class OumorcodController {
	
	@Autowired
	private OumorcodService oumorcodService;
	
	private static Logger logger = LogManager.getLogger(OumorcodController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumorcod/offencesResultsCodes", method = RequestMethod.GET)
	public List<OffenseResultCodes> offencesResultsCodes() {
		List<OffenseResultCodes> offencesResultsCodesData = new ArrayList<OffenseResultCodes>();
		try {			
			offencesResultsCodesData = oumorcodService.offencesResultsCodes();
			}
			
		catch (Exception e) {
			logger.error("searchCourtCases", e);
		}
		return offencesResultsCodesData;
	}
	
}

