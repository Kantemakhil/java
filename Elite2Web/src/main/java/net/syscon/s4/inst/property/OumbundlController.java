package net.syscon.s4.inst.property;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.sa.admin.beans.PropertyBundleCommitBean;
import net.syscon.s4.sa.admin.beans.PropertyBundleItems;
import net.syscon.s4.sa.admin.beans.PropertyBundleItemsCommitBean;
import net.syscon.s4.sa.admin.beans.PropertyBundles;

@EliteController
public class OumbundlController {
	
	@Autowired
	private OumbundlService oumbundlService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumbundlController.class.getName());

	
	/**
	 * retrieving list of property bundles 
	 * @return property bundles 
	 */
	
	@RequestMapping(value = "oumbundl/getPropertyBundles", method = RequestMethod.GET)
	public List<PropertyBundles> getPropertyGroups() {
		List<PropertyBundles> propertyGroups = new ArrayList<>();
		try {
			propertyGroups = oumbundlService.getPropertyGroups();
		}catch(Exception e) {
			logger.error("retrieve list of propertyGroups: ", e);
		}
		
		return propertyGroups;
		
	}
	
	/**
	 * retrieving list of caseload
	 * @param searchBean
	 * @return
	 */
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "oumbundl/getCaseLoads", method = RequestMethod.GET)
	public @ResponseBody List<Caseloads> getCaseLoads(final StaffMembers searchBean) {
		List<Caseloads> dataObj = new ArrayList<Caseloads>();
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userId);
			dataObj = oumbundlService.getCaseloads(searchBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}
	
	/**
	 * retrieving list of property bundle items 
	 * @param groupId
	 * @return propertyItems
	 */

	@RequestMapping(value = "oumbundl/getPropertyItems", method = RequestMethod.GET)
	public List<PropertyBundleItems> getPropertyItems(@RequestParam("groupId") String groupId) {
		List<PropertyBundleItems> propertyItems = new ArrayList<>();
		try {
			propertyItems = oumbundlService.getPropertyItems(groupId);
		}catch(Exception e) {
			logger.error("retrieve list of propertyItems: ", e);
		}
		
		return propertyItems;
	}
	
	/**
	 * insert, update, delete property bundles 
	 * @param commitBean
	 * @return
	 */
	
	@RequestMapping(value = "oumbundl/updatePropertyBundles", method = RequestMethod.POST)
	public @ResponseBody Integer propertyBundlesCommit(@RequestBody final PropertyBundleCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			 liReturn  = oumbundlService.insertUpdateDeletePropertyBundles(commitBean);
			
		} catch (Exception e) {
			logger.error("propertyBundlesCommit: ", e);
		}
		return liReturn;
	}
	
	/**
	 * insert, update, delete property bundle items
	 * @param commitBean
	 * @return
	 */
	
	@RequestMapping(value = "oumbundl/updatePropertyBundleItems", method = RequestMethod.POST)
	public @ResponseBody Integer propertyBundleItemsCommit(@RequestBody final PropertyBundleItemsCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean != null) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
		}
		try {
			 liReturn  = oumbundlService.insertUpdateDeletePropertyItems(commitBean);
			
		} catch (Exception e) {
			logger.error("propertyBundlesCommit: ", e);
		}
		return liReturn;
	}
}