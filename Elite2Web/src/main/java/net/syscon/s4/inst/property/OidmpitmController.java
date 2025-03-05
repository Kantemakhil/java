package net.syscon.s4.inst.property;

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
import net.syscon.s4.inst.property.bean.Group;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @Class OidmpitmController
 */
@EliteController
public class OidmpitmController {

	@Autowired
	private OidmpitmService oidmpitmService;
	
	@Autowired
	private OidrpitmService oidrpitmService;
	
	@Autowired
	private OidmpconService oidmpconService;
	
	@Autowired
	private OidtpritService oidtpritService;
	
	@Autowired
	private ProsmainService prosmainService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrpitmController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */

	@RequestMapping(value = "/oidmpitm/offPiExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyItems> offPiExecuteQuery(@RequestBody final OffenderPptyItems searchBean) {
		List<OffenderPptyItems> searchResult = new ArrayList<>();
		final OffenderPptyItems bean = new OffenderPptyItems();
		try {
			searchResult = oidmpitmService.offPiExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offPiExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oidmpitm/fetchGroupData", method = RequestMethod.GET)
	public List<Group> fetchGroupNames(@RequestParam final String caseloadId) {
		List<Group> groupNames = new ArrayList<Group>();
		try {
			groupNames = oidmpitmService.fetchGroupNames(caseloadId);
		} catch (Exception e) {
			logger.error("fetchGroupNames", e);
		}
		return groupNames;
	}

	@RequestMapping(value = "/oidmpitm/getDefaultValuesForSelecteGroup", method = RequestMethod.GET)
	public List<OffenderPptyItems> getDefaultValuesForSelecteGroup(@RequestParam final String groupId) {
		List<OffenderPptyItems> defaultResultSet = new ArrayList<>();
		try {
			defaultResultSet = oidmpitmService.getDefaultValuesForSelecteGroup(groupId);
		} catch (Exception e) {
			logger.error("getDefaultValuesForSelecteGroup", e);
		}
		return defaultResultSet;
	}

	@RequestMapping(value = "/oidmpitm/setpropDescForPropertyAttr", method = RequestMethod.POST)
	public List<OffenderPptyItems> setpropDescForPropertyAttr(
			@RequestBody final List<OffenderPptyItems> propertyItems) {
		List<OffenderPptyItems> defaultResultSet = new ArrayList<>();
		try {
			defaultResultSet = oidmpitmService.setpropDescForPropertyAttr(propertyItems);
		} catch (Exception e) {
			logger.error("setpropDescForPropertyAttr", e);
		}
		return defaultResultSet;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @return List<OffenderPptyItems>
	 * @Param offenderPptyItems
	 */
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmpitm/offPiSearchOffenderPptyItemsForcontainer", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offPiSearchOffenderPptyItemsForcontainer(
			@RequestBody final List<OffenderPptyContainers> containersList) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			searchResult = oidmpitmService.offPiSearchOffenderPptyItemsForcontainer(containersList);
		} catch (Exception e) {
			logger.error("offPiSearchOffenderPptyItemsForcontainer", e);
		}
		return searchResult;
	}

	@RequestMapping(value = "/oidmpitm/isRegisterProOrContainerExist", method = RequestMethod.GET)
	public boolean isRegisterProOrContainerExist(@RequestParam final Integer offenderBookId) {
		boolean Return = false;
		try {
			Return = oidmpitmService.isRegisterProOrContainerExist(offenderBookId);
		} catch (Exception e) {
			logger.error("isRegisterProOrContainerExist", e);
		}
		return Return;
	}

	@RequestMapping(value = "/oidmpitm/deactivateContainer", method = RequestMethod.POST)
	public Integer deactivateContainer(@RequestBody final OffenderPptyItems property) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		Integer returnValue = 0;
		try {
			if (property != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				property.setCreateUserId(userName);
			}
			returnValue = oidmpitmService.deactivateContainer(property);
		} catch (Exception e) {
			logger.error("offPiSearchOffenderPptyItemsForcontainer", e);
		}
		return returnValue;
	}
	
	/**
	 *Performing basic Oracle form functions i.e. insert,delete, update int the database table
	 *@Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidmpitm/offPiCommit",method=RequestMethod.POST)
	public @ResponseBody Integer offPiCommit(@RequestBody final OffenderPptyItemsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidrpitmService.offPiCommit(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "51");
			}
		}catch(Exception e){
			logger.error("offPiCommit",e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmpitm/offConCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidmpconService.offConCommit(commitBean);
		} catch (Exception e) {
			logger.error("offConCommit: ", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidmpitm/updateConatinerIntLocation",method=RequestMethod.POST)
	public @ResponseBody Integer updateConatinerIntLocation(@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			offenderPptyContainers.setCreateUserId(userName);
			offenderPptyContainers.setModifyUserId(userName);
			liReturn = oidmpconService.updateConatinerIntLocation(offenderPptyContainers);
		}catch(Exception e){
			logger.error("insertContainerImg",e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value="/oidmpitm/insertContainerImg",method=RequestMethod.POST)
	public @ResponseBody Integer insertContainerImg(@RequestBody final OffenderPptyContainers offenderPptyContainers) {
		int liReturn = 0;
		try {
			liReturn = oidmpconService.insertContainerImg(offenderPptyContainers);
		}catch(Exception e){
			logger.error("insertContainerImg",e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmpitm/offContainerConCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offContainerConCommit(@RequestBody final OffenderPptyContainersCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidmpconService.offConCommit(commitBean);
		} catch (Exception e) {
			logger.error("offConCommit: ", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmpitm/offPiOidtpritCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPiCommit(@RequestBody final OffenderPptyItemsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			if ((commitBean.getUpdateList() != null) && (commitBean.getUpdateList().size() > 0)) {
				for (OffenderPptyItems bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					
				}
				liReturn = oidtpritService.offPiUpdateOffenderPptyItems(commitBean.getUpdateList());
			}
		} catch (Exception e) {
			logger.error("offPiCommit", e);
		}
		return liReturn;
	}
	
}
