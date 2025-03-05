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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.ModuleTabColumns;
import net.syscon.s4.sa.admin.beans.ModuleTabColumnsCommitBean;
import net.syscon.s4.sa.admin.beans.ModuleTables;
import net.syscon.s4.sa.admin.beans.ModuleTablesCommitBean;

@EliteController
public class OumtagreController {
    @Autowired
    private OumtagreService oumtagreService;
    /**
     * Logger object used to print the log in the file
     */
     private static Logger logger = LogManager.getLogger(OumtagreController.class.getName());

    /**
     * getting rgModuleName LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/rgModuleNameRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> rgModuleNameRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumtagreService.rgModuleNameRecordGroup();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * getting rgObjectName LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/rgObjectNameRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> rgObjectNameRecordGroup() {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumtagreService.rgObjectNameRecordGroup();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * getting rgColumnName LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/rgColumnNameRecordGroup", method = RequestMethod.GET)
    public List<ReferenceCodes> rgColumnNameRecordGroup(@RequestParam(value = "tableName") final String objectName) {
        List<ReferenceCodes> recordList = new ArrayList<>();
        try {
            recordList = oumtagreService.rgColumnNameRecordGroup(objectName);
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
            recordList.add(obj);
        }
        return recordList;
    }

    /**
     * getting rgSetupModule LOV values
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/rgSetupModuleRecordGroup", method = RequestMethod.GET)
    public List<OmsModules> rgSetupModuleRecordGroup() {
        List<OmsModules> recordList = new ArrayList<>();
        try {
            recordList = oumtagreService.rgSetupModuleRecordGroup();
        } catch (Exception e) {
            final OmsModules obj = new OmsModules();
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
    @RequestMapping(value = "/oumtagre/rleInarcExecuteQuery", method = RequestMethod.POST)
    public List<OmsModules> rleInarcExecuteQuery(@RequestBody final OmsModules searchBean) {
        List<OmsModules> searchResult = new ArrayList<>();
        try {
            searchResult = oumtagreService.rleInarcExecuteQuery(searchBean);
        } catch (Exception e) {
            final OmsModules bean = new OmsModules();
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
    @RequestMapping(value = "/oumtagre/moduleTablesExecuteQuery", method = RequestMethod.POST)
    public AuditLog moduleTablesExecuteQuery(@RequestBody final ModuleTables searchBean) {
    	AuditLog searchResult = new AuditLog();
        try {
            searchResult = oumtagreService.moduleTablesExecuteQuery(searchBean);
        } catch (Exception e) {
            logger.error("Exception :", e.getMessage());
            searchResult.setErrorMessage(e.getMessage());
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
    @RequestMapping(value = "/oumtagre/moduleTablesCommit", method = RequestMethod.POST)
    public @ResponseBody
    ModuleTables moduleTablesCommit(@RequestBody final ModuleTablesCommitBean commitBean) {
        ModuleTables returnObj=new ModuleTables();
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
        try {
        	returnObj = oumtagreService.moduleTablesCommit(commitBean);
        } catch (Exception e) {

            logger.error("Exception in moduleTablesCommit:"+ e.getMessage());
        }
        return returnObj;
    }

    /**
     * Fetching the record from database table
     *
     * @Param searchRecord
     */
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/moduleTabColumnsExecuteQuery", method = RequestMethod.POST)
    public List<ModuleTabColumns> moduleTabColumnsExecuteQuery(@RequestBody final ModuleTabColumns searchBean) {
        List<ModuleTabColumns> searchResult = new ArrayList<>();
        try {
            searchResult = oumtagreService.moduleTabColumnsExecuteQuery(searchBean);
        } catch (Exception e) {
            final ModuleTabColumns bean = new ModuleTabColumns();
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
    @RequestMapping(value = "/oumtagre/moduleTabColumnsCommit", method = RequestMethod.POST)
    public @ResponseBody
    Integer moduleTabColumnsCommit(@RequestBody final ModuleTabColumnsCommitBean commitBean) {
        int liReturn = 0;
    	String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
        try {
            liReturn = oumtagreService.moduleTabColumnsCommit(commitBean);
        } catch (Exception e) {

            logger.error("Exception :", e);
        }
        return liReturn;
    }
    
    
    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtagre/validateTriggerExist", method = RequestMethod.GET)
	public @ResponseBody Integer validateTriggerExist(@RequestParam(value = "tableName") final String tableName) {
		Integer triggerCount = null;
		try {
			triggerCount = oumtagreService.validateTriggerExist(tableName);
		} catch (Exception e) {
			logger.error("error in validateTriggerExist: "+e.getMessage());
		}
		return triggerCount;
	}
    @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtagre/getModulesAssociatedWithTable", method = RequestMethod.POST)
	public @ResponseBody String getModulesAssociatedWithTable(@RequestBody final ModuleTables obj) {
    	String retrunValue = null;
		try {
			retrunValue = oumtagreService.getModuleAssociatedWithTable(obj);
		} catch (Exception e) {
			logger.error("error in getModulesAssociatedWithTable: "+e.getMessage());
		}
		return retrunValue;
	}
    
    @PreAuthorize("hasEliteRole('read')")
    @RequestMapping(value = "/oumtagre/getTablesDescription", method = RequestMethod.GET)
    public List<ModuleTables> getTablesDescription() {
        List<ModuleTables> searchResult = new ArrayList<>();
        try {
            searchResult = oumtagreService.getTablesDescription();
        } catch (Exception e) {
            final ModuleTables bean = new ModuleTables();
            logger.error("OumtagreController : Error in getTablesDescription :"+ e.getMessage());
            bean.setErrorMessage(e.getMessage());
            searchResult.add(bean);
        }
        return searchResult;
    }
    
    
    

}