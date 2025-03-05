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
public class OumemoveController {
	@Autowired
	private OumemoveService oumemoveService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumemoveController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumemove/moveRsnExecuteQuery", method = RequestMethod.POST)
	public List<MovementReasons> moveRsnExecuteQuery(@RequestBody final MovementReasons searchBean) {
		List<MovementReasons> searchResult = new ArrayList<>();
		try {
			searchResult = oumemoveService.moveRsnExecuteQuery(searchBean);
		} catch (Exception e) {
			final MovementReasons bean = new MovementReasons();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumemove/moveRsnCommit", method = RequestMethod.POST)
	public @ResponseBody List<MovementReasons> moveRsnCommit(@RequestBody final MovementReasonsCommitBean commitBean) {
		List<MovementReasons> liReturn = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oumemoveService.moveRsnCommit(commitBean);
		} catch (Exception e) {
			final MovementReasons error = new MovementReasons();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkMoveRsnMovementReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumemove/cgfkMoveRsnMovementReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkMoveRsnMovementReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumemoveService.cgfkMoveRsnMovementReasonRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oumemove:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkMoveRsnMovementType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumemove/cgfkMoveRsnMovementTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkMoveRsnMovementTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumemoveService.cgfkMoveRsnMovementTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oumemove:", e);
		}
		return recordList;
	}

	/**
	 * This method is used to find the child records count
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumemove/cgrichkMovementReasonsDeleteCheck", method = RequestMethod.POST)
	public MovementReasons cgrichkMovementReasonsDeleteCheck(@RequestBody final MovementReasons searchBean) {
		MovementReasons searchBeanData = null;
		try {
			searchBeanData = oumemoveService.cgrichkMovementReasonsDeleteCheck(searchBean);
		} catch (Exception e) {
			logger.error("Exception : Oimlegst:", e);
		}
		return searchBeanData;
	}

}