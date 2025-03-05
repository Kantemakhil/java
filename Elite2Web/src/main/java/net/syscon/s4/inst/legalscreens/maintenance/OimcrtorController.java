package net.syscon.s4.inst.legalscreens.maintenance;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplateObjectsCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OrderTypes;
import net.syscon.s4.im.beans.OrderTypesCommitBean;

@EliteController
public class OimcrtorController {
	@Autowired
	private OimcrtorService oimcrtorService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimcrtorController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OrderTypes}
	 * @return a list of the OrderTypes {@link OrderTypes} for the matched OrderTypes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcrtor/orderTypesExecuteQuery", method = RequestMethod.POST)
	public List<OrderTypes> orderTypesExecuteQuery(@RequestBody final OrderTypes searchBean) {
		List<OrderTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimcrtorService.orderTypesExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OrderTypesCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcrtor/orderTypesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer orderTypesCommit(@RequestBody final OrderTypesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcrtorService.orderTypesCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Oimcrtor", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link IwpTemplateObjects}
	 * @return a list of the IwpTemplateObjects {@link IwpTemplateObjects} for the matched IwpTemplateObjects
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcrtor/iwpTemplateObjectsExecuteQuery", method = RequestMethod.POST)
	public List<IwpTemplateObjects> iwpTemplateObjectsExecuteQuery(@RequestBody final IwpTemplateObjects searchBean) {
		List<IwpTemplateObjects> searchResult = new ArrayList<>();
		try {
			searchResult = oimcrtorService.iwpTemplateObjectsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final IwpTemplateObjects bean = new IwpTemplateObjects();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link IwpTemplateObjectsCommitBean}
	 * @return success/failure of the insert/update {@link Integer} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcrtor/iwpTemplateObjectsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer iwpTemplateObjectsCommit(@RequestBody final IwpTemplateObjectsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oimcrtorService.iwpTemplateObjectsCommit(commitBean);
		} catch (final Exception e) {
			logger.error("Exception : Oimcrtor", e);
		}
		return liReturn;
	}

	/**
	 * getting rgTemplate LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimcrtor/rgTemplateRecordGroup", method = RequestMethod.GET)
	public List<IwpTemplates> rgTemplateRecordGroup() {
		List<IwpTemplates> recordList = new ArrayList<>();
		try {
			recordList = oimcrtorService.rgTemplateRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oimcrtor:", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimcrtor/iwpTempOnCheckDeleteMaster", method = RequestMethod.GET)
	public Integer iwpTempOnCheckDeleteMaster(@RequestParam final BigDecimal templateId) {
		Integer count = 0;
		try {
			count = oimcrtorService.iwpTempOnCheckDeleteMaster(templateId);
		} catch (final Exception e) {
			logger.error("Exception : Oimcrtor:", e);
		}
		return count;
	}

}