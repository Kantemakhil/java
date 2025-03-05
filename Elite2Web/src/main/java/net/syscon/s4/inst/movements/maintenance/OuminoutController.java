package net.syscon.s4.inst.movements.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.MovementReasonsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;

@EliteController
public class OuminoutController {
	@Autowired
	private OuminoutService ouminoutService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuminoutController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouminout/moveRsnExecuteQuery", method = RequestMethod.POST)
	public List<MovementReasons> moveRsnExecuteQuery(@RequestBody final MovementReasons searchBean) {
		List<MovementReasons> searchResult = new ArrayList<>();
		try {
			searchResult = ouminoutService.moveRsnExecuteQuery(searchBean);
		} catch (Exception e) {
			final MovementReasons error = new MovementReasons();
			final String errorMsg = "Error in : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			searchResult.add(error);
			logger.error("Exception in moveRsnExecuteQuery: Ouminout:", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ouminout/moveRsnCommit", method = RequestMethod.POST)
	public @ResponseBody List<MovementReasons> moveRsnCommit(@RequestBody final MovementReasonsCommitBean commitBean) {
		List<MovementReasons> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ouminoutService.moveRsnCommit(commitBean);
		} catch (Exception e) {
			final MovementReasons error = new MovementReasons();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception in moveRsnCommit: Ouminout:", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkMoveRsnInMovementReas LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouminout/cgfkMoveRsnInMovementReasRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkMoveRsnInMovementReasRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ouminoutService.cgfkMoveRsnInMovementReasRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes error = new ReferenceCodes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			recordList.add(error);
			logger.error("Exception : Ouminout:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouminout/cgrichkMovementReasonsDeleteCheck", method = RequestMethod.POST)
	public MovementReasons cgrichkMovementReasonsDeleteCheck(@RequestBody final MovementReasons searchBean) {
		MovementReasons searchBeanData = null;
		try {
			searchBeanData = ouminoutService.cgrichkMovementReasonsDeleteCheck(searchBean);
		} catch (Exception e) {
			logger.error("Exception : Ouminout:", e);
		}
		return searchBeanData;
	}

}