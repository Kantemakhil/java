package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.legals.beans.UpdateReason;
import net.syscon.s4.inst.legals.beans.UpdateUser;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.inst.legals.beans.StatusUpdate;

@EliteController
public class OculegstController {

	@Autowired
	OculegstService oculegstService;

	private static Logger logger = LogManager.getLogger(OculegstController.class.getName());

	/***
	 * method written for Update Court Case
	 *
	 * @return a list of the UpdateReason {@link UpdateReason} from the DB.
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegst/reasonUpdateStatus", method = RequestMethod.GET)
	public List<UpdateReason> getUpdateCaseReason() {
		List<UpdateReason> result = new ArrayList<UpdateReason>();
		try {
			result = oculegstService.getUpdateCaseReason();
		} catch (Exception e) {
			logger.error("getUpdateCaseReason", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculegst/populateUpdateReason", method = RequestMethod.GET)
	public List<Sentences> populateUpdateReason(@RequestParam(value = "category") String category,
			@RequestParam(value = "sentenceCalcType") String sentenceCalcType) {
		List<Sentences> Result = new ArrayList<Sentences>();
		try {
			Result = oculegstService.populateUpdateReason(category, sentenceCalcType);
		} catch (Exception e) {
			logger.error("populateUpdateReason", e);
		}
		return Result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculegst/reasonUpdateStatus", method = RequestMethod.GET)
	public List<UpdateReason> getUpdateConditionReason() {
		List<UpdateReason> result = new ArrayList<UpdateReason>();
		try {
			result = oculegstService.getUpdateConditionReason();
		} catch (Exception e) {
			logger.error("getUpdateConditionReason", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oculegst/getUpdateUser", method = RequestMethod.GET)
	public UpdateUser getUpdateUser() {
		UpdateUser result = new UpdateUser();
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			result = oculegstService.getUpdateUser(authentication.getName());
		} catch (Exception e) {
			logger.error("getUpdateUser", e);
		}
		return result;
	}
}
