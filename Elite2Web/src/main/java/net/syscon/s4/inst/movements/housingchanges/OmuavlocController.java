package net.syscon.s4.inst.movements.housingchanges;

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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.inst.movements.housingchanges.beans.VLivUnits;

/**
 * Class OmuavlocController
 */
@EliteController
public class OmuavlocController {
	@Autowired
	private OmuavlocService omuavlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuavlocController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omuavloc/livUnitExecuteQuery", method = RequestMethod.POST)
	public List<VLivUnits> livUnitExecuteQuery(@RequestBody final CaseloadAgencyLocations searchBean) {
		List<VLivUnits> searchResult = new ArrayList<>();
		try {
			searchResult = omuavlocService.livUnitExecuteQuery(searchBean);
		} catch (Exception e) {
			final VLivUnits bean = new VLivUnits();
			logger.error("livUnitExecuteQuery:", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

}