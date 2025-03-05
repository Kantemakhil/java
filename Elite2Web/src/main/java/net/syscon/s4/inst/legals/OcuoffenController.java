package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.Offenses;


@EliteController
public class OcuoffenController {
	
	@Autowired
	private OcuoffenService ocuoffenService;
	
	private static Logger logger = LogManager.getLogger(OcuoffenController.class.getName());
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoffen/offencesAgainstOrders", method = RequestMethod.GET)
	public List<Offenses> offencesAgainstOrdersData() {
		List<Offenses> offensesSearchResult = new ArrayList<Offenses>();
		try {			
			offensesSearchResult = ocuoffenService.offencesAgainstOrdersData();
			}
			
		catch (Exception e) {
			logger.error("searchCourtCases", e);
		}
		return offensesSearchResult;
	}
	
}
