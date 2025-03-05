package net.syscon.s4.sa.recordmaintenance;

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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.VBookAdmin;

/**
 * class OumbadmiController
 * 
 */
@EliteController
public class OumbadmiController {
	@Autowired
	private OumbadmiService oumbadmiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());

	/**
	 * getting cgfkOffContactsBookingStat LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbadmi/cgfkOffContactsBookingStatRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffContactsBookingStatRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oumbadmiService.cgfkOffContactsBookingStatRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbadmi/vBookAdmExecuteQuery", method = RequestMethod.POST)
	public List<VBookAdmin> vBookAdmExecuteQuery(@RequestBody VBookAdmin searchBean) {
		List<VBookAdmin> searchResult = new ArrayList<>();
		try {
			searchBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oumbadmiService.vBookAdmExecuteQuery(searchBean);
		} catch (Exception e) {
			VBookAdmin bean = new VBookAdmin();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumbadmi/offContactsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offContactsExecuteQuery(@RequestBody OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			searchResult = oumbadmiService.offContactsExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderBookings bean = new OffenderBookings();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oumbadmi/offContactsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offContactsCommit(@RequestBody OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumbadmiService.offContactsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception :", e);
		}
		return liReturn;
	}

}