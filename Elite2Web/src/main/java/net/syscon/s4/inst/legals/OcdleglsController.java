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
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;

@EliteController
public class OcdleglsController {
	
	private static Logger logger = LogManager.getLogger(OumorcodController.class.getName());
	
	
	@Autowired
	private OidhwdetService oidhwdetService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegls/searchHoldWarrentDetainer", method = RequestMethod.GET)
	public List<HoldWarrentDetainer> holdWarrentDetainerData(@RequestParam final Long offenderBookId) {
		List<HoldWarrentDetainer> holdWarrentDetainerData = new ArrayList<HoldWarrentDetainer>();
		try {			
			holdWarrentDetainerData = oidhwdetService.searchHoldWarrentDetainer(offenderBookId);
			}
			
		catch (Exception e) {
			logger.error("Legal Summary holdWarrentDetainerData", e);
			e.printStackTrace();
		}
		return holdWarrentDetainerData;
	}

}
