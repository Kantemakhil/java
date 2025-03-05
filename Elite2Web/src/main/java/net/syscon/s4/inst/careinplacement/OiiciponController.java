package net.syscon.s4.inst.careinplacement;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;

@EliteController
public class OiiciponController {

	private static Logger logger = LogManager.getLogger(OiiciponController.class.getName());

	@Autowired
	private OiiciponService oiiciponService;

	/**
	 * getting rgAgyLocs LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicipon/rgAgyLocsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocsRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiiciponService.rgAgyLocsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("In rgAgyLocsRecordGroup method : ", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicipon/offCipDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderCipDetails> offCipDetailsExecuteQuery(@RequestBody final OffenderCipDetails searchBean) {
		List<OffenderCipDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oiiciponService.offCipDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderCipDetails bean = new OffenderCipDetails();
			logger.error("In offCipDetailsExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

}
