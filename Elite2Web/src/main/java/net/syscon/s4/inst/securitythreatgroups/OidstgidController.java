package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.StgIdentifiers;
import net.syscon.s4.im.beans.StgIdentifiersCommitBean;
import net.syscon.s4.im.beans.StgIdentifyingWords;
import net.syscon.s4.im.beans.StgIdentifyingWordsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * class OidstgidController
 */
@EliteController
public class OidstgidController {
	@Autowired
	private OidstgidService oidstgidService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgidController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<StgIdentifiers>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/stgIdentifiersExecuteQuery", method = RequestMethod.POST)
	public List<StgIdentifiers> stgIdentifiersExecuteQuery(@RequestBody final StgIdentifiers searchBean) {
		List<StgIdentifiers> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgidService.stgIdentifiersExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("stgIdentifiersExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/stgIdentifiersCommit", method = RequestMethod.POST)
	public Integer stgIdentifiersCommit(@RequestBody final StgIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidstgidService.stgIdentifiersCommit(commitBean);
		} catch (Exception e) {

			logger.error("stgIdentifiersCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgProfileType LOV values
	 * 
	 * @return List<ProfileTypes>
	 * @param stgId
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/rgProfileTypeRecordGroup", method = RequestMethod.GET)
	public List<ProfileTypes> rgProfileTypeRecordGroup(@RequestParam(value = "stgId") final Long stgId) {
		List<ProfileTypes> recordList = new ArrayList<ProfileTypes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgidService.rgProfileTypeRecordGroup(stgId);
		} catch (Exception e) {
			logger.error("rgProfileTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<Images>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/image1ExecuteQuery", method = RequestMethod.GET)
	public List<Images> image1ExecuteQuery(@RequestParam(value = "stgId")final  Long stgId,
			@RequestParam(value = "identifierSeq") final Long identifierSeq) {
		List<Images> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgidService.image1ExecuteQuery(stgId, identifierSeq);
		} catch (Exception e) {
			logger.error("image1ExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/image1Commit", method = RequestMethod.POST)
	public Integer image1Commit(@RequestBody final ImagesCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		
		try {
			liReturn = oidstgidService.image1Commit(commitBean);
		} catch (Exception e) {

			logger.error("image1Commit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<StgIdentifyingWords>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/stgIdentifyingWordsExecuteQuery", method = RequestMethod.POST)
	public List<StgIdentifyingWords> stgIdentifyingWordsExecuteQuery(@RequestBody final StgIdentifyingWords stgId) {
		List<StgIdentifyingWords> searchResult = new ArrayList<StgIdentifyingWords>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgidService.stgIdentifyingWordsExecuteQuery(stgId);
		} catch (Exception e) {
			logger.error("stgIdentifyingWordsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/stgIdentifyingWordsCommit", method = RequestMethod.POST)
	public Integer stgIdentifyingWordsCommit(@RequestBody final StgIdentifyingWordsCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidstgidService.stgIdentifyingWordsCommit(commitBean);
		} catch (Exception e) {

			logger.error("stgIdentifyingWordsCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param stgId
	 * @return String
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgid/oidstgidGetGlobalStgDescription", method = RequestMethod.GET)
	public String oidstgidGetGlobalStgDescription(@RequestParam(value = "stgId") final BigDecimal stgId) {
		String recordValue = "";
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordValue = oidstgidService.oidstgidGetGlobalStgDescription(stgId);
		} catch (Exception e) {
			logger.error("oidstgidGetGlobalStgDescription", e);
		}
		return recordValue;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGID");
	}
}