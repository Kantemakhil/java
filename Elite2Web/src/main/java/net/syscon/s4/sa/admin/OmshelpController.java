package net.syscon.s4.sa.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsModulesHelp;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;
import net.syscon.s4.sa.admin.beans.OmsModulesHelpCommitBean;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumdtempService;

@EliteController
public class OmshelpController {
 
    
    @Autowired
    private OmshelpService omshelpService;  
    @Autowired
	private OumcdtabService oumcdtabService;
    @Autowired
    private OumdtempService oumdtempService;


    /**
     * Logger object used to print the log in the file
     */
    private final static Logger logger = LogManager.getLogger(OmshelpController.class.getName());

    
	
	
	
	@RequestMapping(value = "omshelp/moduleHelpExecuteQuery", method = RequestMethod.GET)
    public List<OmsModulesHelp> moduleHelpExecuteQuery() {
        List<OmsModulesHelp> recordList = new ArrayList<>();
        try {
            recordList = omshelpService.moduleHelpExecuteQuery();
        } catch (Exception e) {
            final ReferenceCodes obj = new ReferenceCodes();
            logger.error("Exception :", e);
            obj.setErrorMessage(e.getMessage());
//            recordList.add(obj);
        }
        return recordList;
    }
	
	 @RequestMapping(value = "omshelp/moduleHelpCommit", method = RequestMethod.POST)
	   public @ResponseBody Integer moduleHelpCommit(@RequestBody final OmsModulesHelpCommitBean commitBean) {
	       int liReturn = 0;
	       final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
	       try {
	           liReturn = omshelpService.moduleHelpCommit(commitBean);
	       } catch (Exception e) {

	           logger.error("Exception :", e);
	           if (e instanceof DuplicateKeyException && e.getMessage().contains("ROLE_INACCESSIBLE_REF_CODES_PK")) {
	               liReturn = 2;
	           }

	       }
	       return liReturn;
	   }
	 
	 @RequestMapping(value = "omshelp/insertBaseUrl", method = RequestMethod.POST)
	 public  Integer insertBaseUrl(@RequestBody final BaseHelpUrl commitBean) {
		 int liReturn = 0;
	       try {
	    	   String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
	           liReturn = omshelpService.insertBaseUrl(commitBean);
	       } catch (Exception e) {
	           logger.error("Exception :", e);
	           if (e instanceof DuplicateKeyException && e.getMessage().contains("ROLE_INACCESSIBLE_REF_CODES_PK")) {
	               liReturn = 2;
	           }
	       }
	       return liReturn;
	 }
	 
	 @RequestMapping(value = "omshelp/urlExecuteQuery", method = RequestMethod.GET)
	    public BaseHelpUrl urlExecuteQuery() {
		 	BaseHelpUrl recordList = new BaseHelpUrl();
	        try {
	            recordList = omshelpService.urlExecuteQuery();
	        } catch (Exception e) {
	            final ReferenceCodes obj = new ReferenceCodes();
	            logger.error("Exception :", e);
	            obj.setErrorMessage(e.getMessage());
//	            recordList.add(obj);
	        }
	        return recordList;
	    }
	 
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/omshelp/modifyTabExecuteQuery", method = RequestMethod.POST)
		public List<CopyTables> modifyTabExecuteQuery(@RequestBody final CopyTables searchBean) {
			List<CopyTables> searchResult = new ArrayList<>();
			try {
				searchResult = oumcdtabService.modifyTabExecuteQuery(searchBean);
			} catch (Exception e) {
				final CopyTables bean = new CopyTables();
				logger.error("Exception :", e);
				bean.setErrorMessage(e.getMessage());
				searchResult.add(bean);
			}
			return searchResult;
		}

		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/omshelp/lovParentTableRecordGroup", method = RequestMethod.GET)
		public List<AllTabColumns> lovParentTableRecordGroup() {
			List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
			try {
				recordList = oumcdtabService.lovParentTableRecordGroup();
			} catch (Exception e) {
				final AllTabColumns obj = new AllTabColumns();
				logger.error("Exception : Oumcdtab:", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/omshelp/lovTableNameRecordGroup", method = RequestMethod.GET)
		public List<AllTabColumns> lovTableNameRecordGroup() {
			List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
			try {
				recordList = oumcdtabService.lovTableNameRecordGroup();
			} catch (Exception e) {
				final AllTabColumns obj = new AllTabColumns();
				logger.error("Exception : Oumcdtab:", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/omshelp/lovColumnNameRecordGroup", method = RequestMethod.GET)
		public List<AllTabColumns> lovColumnNameRecordGroup(@RequestParam("tableName") final String tableName) {
			List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
			try {
				recordList = oumcdtabService.lovColumnNameRecordGroup(tableName);
			} catch (Exception e) {
				final AllTabColumns obj = new AllTabColumns();
				logger.error("Exception : Oumcdtab:", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/omshelp/lovSeqNameRecordGroup", method = RequestMethod.GET)
		public List<AllTabColumns> lovSeqNameRecordGroup() {
			List<AllTabColumns> recordList = new ArrayList<AllTabColumns>();
			try {
				recordList = oumcdtabService.lovSeqNameRecordGroup();
			} catch (Exception e) {
				final AllTabColumns obj = new AllTabColumns();
				logger.error("Exception : Oumcdtab:", e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value="/omshelp/rgOmsModuleRecordGroup",method=RequestMethod.GET)
		public List<OmsModules> rgOmsModuleRecordGroup() {
			List<OmsModules> recordList =new ArrayList<OmsModules>();
			try {
				recordList = oumdtempService.rgOmsModuleRecordGroup();
			} catch(Exception e){
				OmsModules obj = new OmsModules();
				logger.error("Exception : Oumdtemp:",e);
				obj.setErrorMessage(e.getMessage());
				recordList.add(obj);
			}
			return recordList;
		}

}