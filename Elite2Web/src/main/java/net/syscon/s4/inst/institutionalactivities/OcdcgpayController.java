package net.syscon.s4.inst.institutionalactivities;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VOffenderCourseAttendancesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.OimallowService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdcgpayController
 */
@EliteController
public class OcdcgpayController {
	
	
	@Autowired
	private OcdcgpayService ocdcgpayService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OimallowService oimallowService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdcgpayController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcgpay/unpaidAttendanceExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(
			@RequestBody VOffenderCourseAttendances searchBean) {
		List<VOffenderCourseAttendances> searchResult = new ArrayList<>();
		try {
			searchResult = ocdcgpayService.unpaidAttendanceExecuteQuery(searchBean);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdcgpay/generatePay", method = RequestMethod.POST)
	public @ResponseBody Integer generatePay(@RequestBody List<VOffenderCourseAttendances> updateList,@RequestHeader HttpHeaders headers) {
		JSONObject returnObj;
		Integer liReturn=0;
		VOffenderCourseAttendancesCommitBean commitBean=new VOffenderCourseAttendancesCommitBean();
		VOffenderCourseAttendances insertObj=new VOffenderCourseAttendances();
		List<VOffenderCourseAttendances> insertList=new ArrayList<>();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			updateList.forEach(attendence->attendence.setCreateUserId(userName));
			returnObj = ocdcgpayService.generatePay(updateList);
			commitBean.setCreateUserId(userName);
			
			if(returnObj!=null && returnObj.has("liReturn")) {
				liReturn= returnObj.getInt("liReturn");
			}
			if(liReturn>1) {
				if(returnObj!=null && returnObj.has("payRunDetails")) {
					insertObj.setPayRunDetails(returnObj.get("payRunDetails").toString());
					insertList.add(insertObj);
					commitBean.setInsertList(insertList);
				}
				prosmainService.enableTriggers(commitBean, authorization, "122");
			}
		} catch (Exception e) {

			logger.error("Exception in generatePay :", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdcgpay/getFromToDates", method = RequestMethod.GET)
	public @ResponseBody List<OffenderCourseAttendance> getFromToDates() {
		List<OffenderCourseAttendance> liReturn = new ArrayList<>();
		try {
			liReturn = ocdcgpayService.getFromToDates();
		} catch (Exception e) {
			logger.error("Exception in getFromToDates :", e);
		}
		return liReturn;
	}
}
