package net.syscon.s4.inst.automatedcounts;

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
import net.syscon.s4.im.beans.VIntLocOffenders;


@EliteController
public class OiiinrolController {
@Autowired
private OiiinrolService oiiinrolService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiiinrolController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiiinrol/rollListExecuteQuery", method=RequestMethod.GET)
	public List<VIntLocOffenders> rollListExecuteQuery(@RequestParam(value="agyLocId") final String agyLocId, 
								@RequestParam(value="internalLocationId") final Integer internalLocationId ) {
		List<VIntLocOffenders> searchResult = new ArrayList<>();
		try {
			searchResult = oiiinrolService.rollListExecuteQuery(agyLocId,internalLocationId);
		} catch (Exception e) {
			VIntLocOffenders bean = new VIntLocOffenders();
			logger.error("rollListExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}