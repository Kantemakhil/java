package net.syscon.s4.globaloffenderrecords;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderNonAssociationsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidononaController
 */
@EliteController
public class OidononaController {
	@Autowired
	private OidononaService oidononaService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidononaController.class.getName());

	/**
	 * getting cgfkOffNaDspOffenderIdDi LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/cgfkOffNaDspOffenderIdDiRecordGroup", method = RequestMethod.GET)
	public List<VHeaderBlock> cgfkOffNaDspOffenderIdDiRecordGroup() {
		List<VHeaderBlock> recordList = new ArrayList<VHeaderBlock>();
		try {
			recordList = oidononaService.cgfkOffNaDspOffenderIdDiRecordGroup();
		} catch (final Exception e) {
			final VHeaderBlock obj = new VHeaderBlock();
			logger.error("Exception : Oidonona:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffNadDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/cgfkOffNadDspDescription3RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffNadDspDescription3RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidononaService.cgfkOffNadDspDescription3RecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidonona:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting offNaDspRecipRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/offNaDspRecipRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> offNaDspRecipRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidononaService.offNaDspRecipRsnRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidonona:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffNadDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/cgfkOffNadDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffNadDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidononaService.cgfkOffNadDspDescriptionRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidonona:", e);
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
	@RequestMapping(value = "/oidonona/offNaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderNonAssociations> offNaExecuteQuery(@RequestBody final OffenderNonAssociations searchBean) {
		List<OffenderNonAssociations> searchResult = new ArrayList<>();
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = oidononaService.offNaExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("offNaExecuteQuery :", e);
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
	@RequestMapping(value = "/oidonona/offNaCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNaCommit(@RequestBody final OffenderNonAssociationsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidononaService.offNaCommit(commitBean);
			if(0 != liReturn ) {
				prosmainService.enableTriggers(commitBean, authorization, "92");
			}
		} catch (final Exception e) {
			if(e.getMessage().contains("OMS_OWNER.OFFENDER_NON_ASSOCIATIONS_PK"))
				return 3;
			logger.error("Exception : Oidonona", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/offNadExecuteQuery", method = RequestMethod.POST)
	public List<OffenderNaDetails> offNadExecuteQuery(@RequestBody final OffenderNaDetails searchBean) {
		List<OffenderNaDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oidononaService.offNadExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oidonona/offNadCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offNadCommit(@RequestBody final OffenderNaDetailsCommitBean commitBean) {
		int liReturn = 0;
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidononaService.offNadCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Oidonona", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/stgRelationshipsExecuteQuery", method = RequestMethod.POST)
	public List<StgRelationships> stgRelationshipsExecuteQuery(@RequestBody final StgRelationships searchBean) {
		List<StgRelationships> searchResult = new ArrayList<>();
		try {
			searchResult = oidononaService.stgRelationshipsExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidonona/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidononaService.sysPflExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidonona/compareEffectiveDate", method=RequestMethod.GET)
	public Integer compareEffectiveDate(@RequestParam (value="effectiveDate") final String effectiveDate ) {
		Integer searchResult = null;
		try {
			searchResult = oidononaService.compareEffectiveDatec(effectiveDate);
		} catch (Exception e) {
		}
		return searchResult;
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidonona/getMaxVal", method=RequestMethod.GET)
	public Integer getMaxVal(@RequestParam (value="rootOffenderId") final Long rootOffenderId,
			@RequestParam (value="nsOffenderId") final Long nsOffenderId) {
		Integer searchResult = null;
		try {
			searchResult = oidononaService.getMaxVal(rootOffenderId,nsOffenderId);
		} catch (Exception e) {
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidonona/getlastFirstName", method=RequestMethod.GET)
	public List<VHeaderBlock> getlastFirstName(@RequestParam(value="nsOffId") final String nsOffId, @RequestParam(value="offId")  final Long offId) {
		List<VHeaderBlock> result = new ArrayList<>();
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			result = oidononaService.getlastFirstName(nsOffId, offId,userName);
		} catch (Exception e) {
			logger.error("oidonona : getlastFirstName : ", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidonona/getActiveStaffMembers", method=RequestMethod.GET)
	public List<ReferenceCodes> getActiveStaffMembers() {
		List<ReferenceCodes> codeList = null;
		try {
			codeList = oidononaService.getActiveStaffMembers();
		} catch (Exception e) {
		}
		return codeList;
	}

}