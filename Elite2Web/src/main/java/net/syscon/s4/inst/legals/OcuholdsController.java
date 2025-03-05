package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.HoldStatus;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.OrderType;

@EliteController
public class OcuholdsController {

	@Autowired
	private OcuholdsService ocuholdsService;

	private static Logger logger = LogManager.getLogger(OcuholdsController.class.getName());

	/***
	 * fetch data for holds from Order table
	 *
	 * @param eventId {@link Integer}
	 * @return a list of the Holds {@link Holds} for the matched eventId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuholds/populateHoldsData", method = RequestMethod.GET)
	public List<Holds> populateHoldsData(@RequestParam final Integer eventId) {
		List<Holds> result = new ArrayList<Holds>();
		try {
			result = ocuholdsService.populateHoldsData(eventId);
		} catch (Exception e) {
			logger.error("populateHoldsData", e);
		}
		return result;
	}

	/***
	 * genrate lov of order_type
	 * 
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuholds/orderType", method = RequestMethod.GET)
	public List<OrderType> orderType() {
		List<OrderType> result = new ArrayList<OrderType>();
		try {
			result = ocuholdsService.orderType();
		} catch (Exception e) {
			logger.error("orderType", e);
		}
		return result;
	}

	/***
	 * Fetching court data
	 * 
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuholds/populateCourtData", method = RequestMethod.GET)
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		try {
			courtList = ocuholdsService.populateCourtData();
		} catch (Exception e) {
			logger.error("populateCourtData", e);
		}
		return courtList;
	}

	/*** 
	 * generate holds_status
	 * 
	 * @return
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuholds/populateHoldStatus", method = RequestMethod.GET)
	public List<HoldStatus> populateHoldStatus() {
		List<HoldStatus> caseStatusList = new ArrayList<HoldStatus>();
		try {
			caseStatusList = ocuholdsService.populateHoldStatus();
		} catch (Exception e) {
			logger.error("populateHoldStatus", e);
		}
		return caseStatusList;
	}

	/***
	 * function for insert,update or delete
	 * 
	 * @param holdsBeanCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocuholds/updateHoldData", method = RequestMethod.POST)
	public @ResponseBody Integer updateHoldData(@RequestBody HoldsCommitBean holdsBeanCommit) {
		Integer result = 0;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			result = ocuholdsService.updateHoldData(authentication.getName(), holdsBeanCommit);
		} catch (Exception e) {
			logger.error("updateHoldData", e);
		}
		return result;
	}

}
