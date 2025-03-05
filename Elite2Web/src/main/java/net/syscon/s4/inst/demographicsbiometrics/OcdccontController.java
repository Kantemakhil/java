package net.syscon.s4.inst.demographicsbiometrics;

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
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcdccontController
 */
@EliteController
public class OcdccontController {

	@Autowired
	private OcdccontService ocdccontService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdccontController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdccont/phonesExecuteQuery", method = RequestMethod.POST)
	public List<Phones> phonesExecuteQuery(@RequestBody final Phones searchBean) {
		final Phones bean = new Phones();
		List<Phones> searchResult = new ArrayList<>();
		try {
			searchResult = ocdccontService.phonesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("phonesExecuteQuery: ", e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdccont/phonesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer phonesCommit(@RequestBody final PhonesCommitBean commitBean) {
		int liReturn = 0;
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdccontService.phonesCommit(commitBean);
		} catch (Exception e) {
			logger.error("phonesCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting rgPhoneType LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdccont/rgPhoneTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		final ReferenceCodes obj = new ReferenceCodes();
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdccontService.rgPhoneTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgPhoneTypeRecordGroup: ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCDCCONT");
	}

}