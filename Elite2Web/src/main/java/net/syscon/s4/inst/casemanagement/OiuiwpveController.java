package net.syscon.s4.inst.casemanagement;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpDocumentsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * class OiuiwpveController
 */
@EliteController
public class OiuiwpveController {
	@Autowired
	private OiuiwpveService oiuiwpveService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuiwpveController.class.getName());

	/**
	 * getting rgStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiuiwpve/rgStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oiuiwpveService.rgStatusRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgStatusRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgTemplate LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuiwpve/rgTemplateRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTemplateRecordGroup(
			@RequestParam(value = "offenderBookId") final String offenderBookId,
			@RequestParam(value = "moduleName") final String moduleName,
			@RequestParam(value = "pObjectType") final String pObjectType,
			@RequestParam(value = "pType") final String pType,
			@RequestParam(value = "pSubType") final String pSubType,
			@RequestParam(value = "pObjectId") final String pObjectId,
			@RequestParam(value = "blockName") final String blockName) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oiuiwpveService.rgTemplateRecordGroup(offenderBookId, moduleName, pObjectType, pType, pSubType, pObjectId, blockName,userName);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgTemplateRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link IwpDocuments}
	 * @return a list of the IwpDocuments {@link IwpDocuments} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuiwpve/iwpDocExecuteQuery", method = RequestMethod.POST)
	public List<IwpDocuments> iwpDocExecuteQuery(@RequestBody final IwpDocuments searchBean) {
		List<IwpDocuments> searchResult = new ArrayList<>();
		try {
			searchResult = oiuiwpveService.iwpDocExecuteQuery(searchBean);
		} catch (Exception e) {
			final IwpDocuments bean = new IwpDocuments();
			logger.error("In iwpDocExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into the
	 * database table
	 * 
	 * @param commitBean  {@link IwpDocumentsCommitBean}
	 * @return the integer value of the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiuiwpve/iwpDocCommit", method = RequestMethod.POST)
	public @ResponseBody Integer iwpDocCommit(@RequestBody final IwpDocumentsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oiuiwpveService.iwpDocCommit(commitBean);
		} catch (Exception e) {
			logger.error("In iwpDocCommit method : ", e);
		}
		return liReturn;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIUIWPVE");
	}
}