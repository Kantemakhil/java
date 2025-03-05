package net.syscon.s4.inst.movements.proposedmovements;

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
import net.syscon.s4.common.beans.OffenderOicSanctions;

@EliteController
public class OiusanctController {

	@Autowired
	private OiusanctService oiusanctService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiusanctController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiusanct/offenderOicSanctionsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderOicSanctions> offenderOicSanctionsExecuteQuery(@RequestBody OffenderOicSanctions searchBean) {
		List<OffenderOicSanctions> searchResult = new ArrayList<>();
		try {
			searchResult = oiusanctService.offenderOicSanctionsExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderOicSanctions bean = new OffenderOicSanctions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
