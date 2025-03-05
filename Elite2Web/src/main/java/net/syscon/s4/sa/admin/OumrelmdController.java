package net.syscon.s4.sa.admin;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

@EliteController
public class OumrelmdController {

	@Autowired
	private OumrelmdService oumrelmdService;
	@Autowired
	private OumsylabService oumsylabService;
	@Autowired
	private OummenusService oummenusService;
	private static List<OmsModules> moduleList=null;

	private final static Logger logger = LogManager.getLogger(OumrelmdController.class.getName());

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumrelmd/getRelatedModulesByModuleName", method = RequestMethod.GET)
	public List<OmsModules> getRelatedModulesByModuleName(@RequestParam(value = "moduleName") final String moduleName) {
		List<OmsModules> relatedModulesList = new ArrayList<>();
		try {
			relatedModulesList = oumrelmdService.getRelatedModulesByModuleName(moduleName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getRelatedModulesByModuleName " + e);
		}
		return relatedModulesList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumrelmd/getRelatedModulesLov", method = RequestMethod.GET)
	public List<OmsModules> getRelatedModulesLov(@RequestParam(value = "moduleName") final String moduleName) {
		List<OmsModules> relatedModulesList = new ArrayList<>();
		try {
			relatedModulesList = oumrelmdService.getRelatedModulesLov(moduleName);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getRelatedModulesLov " + e);
		}
		return relatedModulesList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumrelmd/insertUpdateDelete", method = RequestMethod.POST)
	public Integer insertUpdateDelete(@RequestBody OmsModulesCommitBean commitBean) {
		Integer result = 0;
		try {
			if (commitBean != null) {
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			result = oumrelmdService.insertUpdateDelete(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertUpdateDelete " + e);
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumrelmd/rleInarcExecuteQuery", method = RequestMethod.POST)
    public List<OmsModules> rleInarcExecuteQuery(@RequestBody final OmsModules searchBean) {
        List<OmsModules> searchResult = new ArrayList<>();
        try {
            searchResult = oumsylabService.rleInarcExecuteQuery(searchBean);
            moduleList=searchResult;
        } catch (Exception e) {
            final OmsModules bean = new OmsModules();
            logger.error("Exception :", e);
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oumrelmd/rgMenuSecDescRecordGroup",method=RequestMethod.GET)
	public List<OmsModules> rgMenuSecDescRecordGroup() {
		List<OmsModules> recordList =new ArrayList<>();
		try {
			recordList = oummenusService.rgMenuSecDescRecordGroup();
		} catch(Exception e){
			final OmsModules obj = new OmsModules();
			logger.error("Exception : Oummenus:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}


}
