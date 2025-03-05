package net.syscon.s4.inst.movements;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;

/**
 * class OiintlocController
 */
@EliteController
public class OiintlocController {
	@Autowired
	private OiintlocService oiintlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiintlocController.class.getName());

	/**
	 * getting rgUsages LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiintloc/rgUsagesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUsagesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = oiintlocService.rgUsagesRecordGroup();
		} catch (Exception e) {
			logger.error("rgUsagesRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VIntLocUsageLocations}
	 * @return a list of the VIntLocUsageLocations {@link VIntLocUsageLocations} for the matched VIntLocUsageLocations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiintloc/intLocExecuteQuery", method = RequestMethod.POST)
	public List<VIntLocUsageLocations> intLocExecuteQuery(@RequestBody final VIntLocUsageLocations searchBean) {
		List<VIntLocUsageLocations> searchResult = new ArrayList<>();
		final VIntLocUsageLocations bean = new VIntLocUsageLocations();
		try {
			searchResult = oiintlocService.intLocExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("intLocExecuteQuery: ", e);
			searchResult.add(bean);
		}
		return searchResult;
	}

}