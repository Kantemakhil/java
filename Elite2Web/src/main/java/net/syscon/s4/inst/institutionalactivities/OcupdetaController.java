package net.syscon.s4.inst.institutionalactivities;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcupdetaController
 */
@EliteController
public class OcupdetaController {
	@Autowired
	private OcupdetaService ocupdetaService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcupdetaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupdeta/unpaidAttendanceExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(
			@RequestBody VOffenderCourseAttendances searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VOffenderCourseAttendances> searchResult = new ArrayList<>();
		try {
			searchResult = ocupdetaService.unpaidAttendanceExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in unpaidAttendanceExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocupdeta/generatePay", method = RequestMethod.POST)
	public @ResponseBody Integer generatePay(@RequestBody List<VOffenderCourseAttendances> updateList) {
		Integer liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			updateList.forEach(data->data.setModifyUserId(userName));
			liReturn = ocupdetaService.generatePay(updateList);
		} catch (Exception e) {

			logger.error("Exception in generatePay :", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUPDETA");
	}
}
