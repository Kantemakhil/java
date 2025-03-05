package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ResponseEntityBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdaliasController
 */
@EliteController
public class OcdaliasController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaliasController.class);

	@Autowired
	private OcdaliasService ocdaliasService;
	
	@Autowired
	private ProsmainService prosmainService;

	/**
	 * Fetching the Offenders record from database table
	 * 
	 * @return List<Offenders>
	 * @Param Offenders searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/offNameSearchOffenders", method = RequestMethod.POST)
	public List<Offenders> offNameSearchOffenders(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<Offenders>();
		final Offenders bean = new Offenders();
		try {
			searchResult = ocdaliasService.offNameSearchOffenders(searchBean);
		} catch (Exception e) {
			logger.error("offNameSearchOffenders:", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return VHeaderBlock
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/getWorkingNameOffenderID", method = RequestMethod.POST)
	public VHeaderBlock getWorkingNameOffenderID(@RequestBody final Offenders searchBean) {
		VHeaderBlock searchResult = new VHeaderBlock();
		try {
			searchResult = ocdaliasService.getWorkingNameOffenderID(searchBean);
		} catch (Exception e) {
			logger.error("getWorkingNameOffenderID: ", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderIdentifier>
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/offIdSearchOffenderIdentifiers", method = RequestMethod.POST)
	public List<OffenderIdentifier> offIdSearchOffenderIdentifiers(@RequestBody final Offenders searchBean,
			@RequestHeader HttpHeaders headers) {
		List<OffenderIdentifier> searchResult = null;
		try {
			List<String> authorizationList = headers.get("Authorization");
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = ocdaliasService.offIdSearchOffenderIdentifiers(searchBean);
		} catch (Exception e) {
			logger.error("offIdSearchOffenderIdentifiers: ", e);
		}
		return searchResult;
	}

	/**
	 * fetch records from OffednerIdentifiers
	 * 
	 * @param searchBean
	 * @return List<OffenderIdentifier>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/offIdAllSearchOffenderIdentifiers", method = RequestMethod.POST)
	public List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(@RequestBody final Offenders searchBean) {
		List<OffenderIdentifier> searchResult = null;
		try {
			searchResult = ocdaliasService.offIdAllSearchOffenderIdentifiers(searchBean);
		} catch (Exception e) {
			logger.error("offIdAllSearchOffenderIdentifiers: ", e);
		}
		return searchResult;
	}

	/**
	 * method for query calling
	 * 
	 * @return String
	 * @Param param
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/offIdPreInsert", method = RequestMethod.POST)
	public @ResponseBody String offIdPreInsert(@RequestParam(value = "param") final String param) {
		String dataObj = null;
		try {
			dataObj = ocdaliasService.offIdPreInsert(param);
		} catch (Exception e) {
			logger.error("offIdPreInsert: ", e);
		}
		return dataObj;
	}

	/**
	 * To performing insert, delete, update of Offender
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdalias/offNameCommit", method = RequestMethod.POST)
	public @ResponseBody ResponseEntityBean offNameCommit(@RequestBody final OffendersCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		ResponseEntityBean responseEntityBean = new ResponseEntityBean();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			responseEntityBean = ocdaliasService.offNameCommit(commitBean);
			if(responseEntityBean.getLiReturn() == 1) {
				prosmainService.enableTriggers(commitBean, authorization, "102");
			}
		} catch (Exception e) {
			logger.error("offNameCommit: ", e);
		}
		return responseEntityBean;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert, delete, update int
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdalias/offIdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offIdCommit(@RequestBody final OffenderIdentifiersCommitBean commitBean) {
		Integer liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdaliasService.offIdCommit(commitBean);
		} catch (Exception e) {
			logger.error("offIdCommit: ", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/getGenderDescription", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getGenderDescription() {
		List<ReferenceCodes> returnList = null;
		try {
			returnList = ocdaliasService.getGenderDescription();
		} catch (Exception e) {
			logger.error("getGenderDescription: ", e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/offNameOnCheckDeleteMasteroffIdCur", method = RequestMethod.POST)
	public @ResponseBody Integer offNameOnCheckDeleteMasteroffIdCur(@RequestBody final Offenders offIdentsearch) {
		int liReturn = 0;
		try {
			liReturn = ocdaliasService.offNameOnCheckDeleteMasteroffIdCur(offIdentsearch);
		} catch (Exception e) {
			logger.error("preDeleteOffender: ", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdalias/vsRangeCursor", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles vsRangeCursor() {
		SystemProfiles returnBean = new SystemProfiles();
		try {
			returnBean = ocdaliasService.vsRangeCursor();
		} catch (Exception e) {
			logger.error("vsRangeCursor: ", e);
		}
		return returnBean;
	}
}