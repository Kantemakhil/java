package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumtagreRepository;
import net.syscon.s4.sa.admin.OumtagreService;
import net.syscon.s4.sa.admin.beans.ModuleTabColumns;
import net.syscon.s4.sa.admin.beans.ModuleTabColumnsCommitBean;
import net.syscon.s4.sa.admin.beans.ModuleTables;
import net.syscon.s4.sa.admin.beans.ModuleTablesCommitBean;

/**
 * Class OumtagreServiceImpl
 */
@Service
public class OumtagreServiceImpl extends BaseBusiness implements OumtagreService {

    @Autowired
    private OumtagreRepository oumtagreRepository;

    public OumtagreServiceImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
    @Override
    public List<OmsModules> rleInarcExecuteQuery(final OmsModules searchRecord) {
        return oumtagreRepository.rleInarcExecuteQuery(searchRecord);

    }

    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
	@Override
    public AuditLog moduleTablesExecuteQuery(final ModuleTables searchRecord) {
		AuditLog returnObj = new AuditLog();
		 List<ModuleTables> moduleTables = oumtagreRepository.moduleTablesExecuteQuery(searchRecord);
		 returnObj.setModuleTableList(moduleTables);
		 returnObj.setViewAuditFlag(oumtagreRepository.getViewAuditFlag(searchRecord.getModuleName()));
		 return returnObj;

    }

    /**
     * Insert the records from database table
     *
     * @param lstMODULE_TABLES
     * @throws SQLException
     */
	@Override
    @Transactional
     public ModuleTables moduleTablesCommit(final ModuleTablesCommitBean commitBean) {
        ModuleTables returnObj=new ModuleTables();
        String warningMessage=null;
        if(commitBean.getContinueFlag()!= null && commitBean.getContinueFlag().equals("N")) {
        	 warningMessage=validateModuleAssociation(commitBean);
        }
        if(warningMessage!=null&& !warningMessage.equals("")) {
        	returnObj.setWarnMessage(warningMessage);
        	return returnObj;
        }
        if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
            for (final ModuleTables obj : commitBean.getInsertList()) {
            	obj.setCreateUserId(commitBean.getCreateUserId());
                Long moduleTabId = oumtagreRepository.moduleTabPreInsert();
                obj.setModuleTabId(moduleTabId++);
            }
            returnObj = oumtagreRepository.moduleTablesInsertModuleTables(commitBean.getInsertList());
            if(returnObj.getSealFlag().equals("1")) {
           	 for (final ModuleTables obj : commitBean.getInsertList()) {
           		returnObj=maintainAuditTableData(obj,returnObj);
           	 }
           	
           }
        }
        // updateRecords
        if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
        	for (ModuleTables obj : commitBean.getUpdateList()) {
        		obj.setModifyUserId(commitBean.getCreateUserId());
			}
        	returnObj = oumtagreRepository.moduleTablesUpdateModuleTables(commitBean.getUpdateList());
        	if(returnObj.getSealFlag().equals("1")) {
           	 for (final ModuleTables obj : commitBean.getUpdateList()) {
           		returnObj=maintainAuditTableData(obj,returnObj);
           	 }
           	
           }
        }
        if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
        	commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
        	returnObj = oumtagreRepository.moduleTablesDeleteModuleTables(commitBean.getDeleteList());
        	if(returnObj.getSealFlag().equals("1")) {
                for (final ModuleTables obj : commitBean.getDeleteList()) {
                	if(obj.getAuditFlag().equals("Y")) {
                		obj.setAuditFlag("N");
                		returnObj=oumtagreRepository.updateTableAuditData(obj);
              			if(returnObj.getSealFlag().equals("1")) {
             				returnObj=oumtagreRepository.deleteAuditOnTable(obj.getObjectName());
             			}
                  		
                  	}
                  }
            	 }
            
        }
        
		if (commitBean.getAuditLog() != null && commitBean.getAuditLog().getViewAuditFlag() != null
				&& !commitBean.getAuditLog().getViewAuditFlag().equals("")) {
			int updAuditFLag = oumtagreRepository.updateViewAuditFlag(commitBean.getAuditLog());
			returnObj.setSealFlag(updAuditFLag == 1 ? "1" : "0");
		}

        return returnObj;
    }

	
	private ModuleTables maintainAuditTableData(ModuleTables obj,ModuleTables returnObj) {
		int triggerOnTable=0;
		int tableAuditRecordCount=0;
		tableAuditRecordCount=oumtagreRepository.getTableAuditRecord(obj.getObjectName());
		triggerOnTable=oumtagreRepository.validateTriggerExist(obj.getObjectName());
		if(obj.getAuditFlag().equals("Y")) {
            if(tableAuditRecordCount >0) {
            	returnObj=oumtagreRepository.updateTableAuditData(obj);
            }else {
            	returnObj=oumtagreRepository.insertTableAuditData(obj);
            }
     		if(triggerOnTable == 0) {
     			if(returnObj.getSealFlag().equals("1")) {
     				returnObj=oumtagreRepository.enableAuditOnTable(obj.getObjectName());
     			}
     		}
     		
     	}else {
     		if(tableAuditRecordCount >0) {
     		returnObj=oumtagreRepository.updateTableAuditData(obj);
     		}
  			if(triggerOnTable > 0) {
 				returnObj=oumtagreRepository.deleteAuditOnTable(obj.getObjectName());
     		
     		 }
     	}
		
		return returnObj;
	}
	
    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
	@Override
    public List<ModuleTabColumns> moduleTabColumnsExecuteQuery(final ModuleTabColumns searchRecord) {
        return oumtagreRepository.moduleTabColumnsExecuteQuery(searchRecord);

    }

    /**
     * Insert the records from database table
     *
     * @param lstMODULE_TAB_COLUMNS
     * @throws SQLException
     */
	@Override
    @Transactional
    public Integer moduleTabColumnsCommit(final ModuleTabColumnsCommitBean commitBean) {
        int liReturn = 0;
        if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
        	for (ModuleTabColumns obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
            Long moduleTabSeq = oumtagreRepository
                    .moduleTabColumnsPreInsert(commitBean.getInsertList().get(0).getModuleTabId());
            for (final ModuleTabColumns obj : commitBean.getInsertList()) {
                obj.setModuleTabSeq(moduleTabSeq++);
            }

            liReturn = oumtagreRepository.moduleTabColumnsInsertModuleTabColumns(commitBean.getInsertList());

        }
        if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
        	for (ModuleTabColumns obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
            liReturn = oumtagreRepository.moduleTabColumnsUpdateModuleTabColumns(commitBean.getUpdateList());
        }
        if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
        	commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
            liReturn = oumtagreRepository.moduleTabColumnsDeleteModuleTabColumns(commitBean.getDeleteList());
        }

        return liReturn;
    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
	@Override
    public List<ReferenceCodes> rgModuleNameRecordGroup() {
		List<ReferenceCodes> moduleList=null;
		moduleList=oumtagreRepository.rgModuleNameRecordGroup();
		if(moduleList!=null && !moduleList.isEmpty()) {
			for(ReferenceCodes obj:moduleList) {
				if(obj.getDescription()!=null) {
					obj.setDescription(obj.getDescription().concat("-").concat(obj.getCode()));
				}
				
			}
		}
		
		
		return moduleList;

    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
	@Override
    public List<ReferenceCodes> rgObjectNameRecordGroup() {
        return oumtagreRepository.rgObjectNameRecordGroup();

    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
	@Override
    public List<ReferenceCodes> rgColumnNameRecordGroup(final String objectName) {
        String[] strArr = null;
        List<ReferenceCodes> colList = new ArrayList<>();
        if (objectName.contains("-")) {
            strArr = objectName.split("-");
            colList = oumtagreRepository.rgColumnNameRecordGroup(strArr[0], strArr[1]);
        }
        for (final ReferenceCodes obj : colList) {
            if ("Y".equals(obj.getActiveFlag())) {
                obj.setCanDisplay(true);
            } else {
                obj.setCanDisplay(false);
            }
        }
        return colList;

    }

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
	@Override
    public List<OmsModules> rgSetupModuleRecordGroup() {
        return oumtagreRepository.rgSetupModuleRecordGroup();

    }

	

	@Override
	public Integer validateTriggerExist(String objectName) {
		return oumtagreRepository.validateTriggerExist(objectName);
	}

	@Override
	public String getModuleAssociatedWithTable(ModuleTables objModuleTables) {
		List<ModuleTables> moduleList= oumtagreRepository.getModuleNamesAssociatedWithTable(objModuleTables);
		String warnMsg=null;
		List<String> moduleData=new ArrayList<>();
		if(moduleList!=null && !moduleList.isEmpty()) {
			for (ModuleTables obj : moduleList) {
				if(!obj.getModuleName().equals(objModuleTables.getModuleName())) {
					moduleData.add(obj.getModuleName());
				}
				
			}
			if(moduleData!=null && !moduleData.isEmpty()) {
				 warnMsg="Table ".concat(objModuleTables.getObjectName()).concat(" ").concat("already associated with the module ").concat(String.join(",", moduleData)).concat(". ");
			}
			
			
		}
		return warnMsg;
	}
	
	private String addMsg(String message, final String addMsg) {
		if (message != null) {
			message = message.concat(addMsg);
		} else {
			message = addMsg;
		}
		return message;
	}
	
	private String validateModuleAssociation(ModuleTablesCommitBean commitBean) {
	   String msg = "";
	   if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
		   for (ModuleTables obj : commitBean.getInsertList()) {
			   msg= validateModuleAssociationData(obj,"N",msg);
				}
		   
	   }
		  if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			  for (ModuleTables obj : commitBean.getUpdateList()) {
				  msg= validateModuleAssociationData(obj,"N",msg);
				}
	   }
		  if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			  for (ModuleTables obj : commitBean.getDeleteList()) {
				  msg= validateModuleAssociationData(obj,"Y",msg);
				}
		   }
		
		return msg;
		
	}

	
	private String validateModuleAssociationData(ModuleTables obj,String flag,String msg) {
		int triggerOnTable=0;
   		 triggerOnTable=oumtagreRepository.validateTriggerExist(obj.getObjectName());
   		 if(triggerOnTable > 0) {
   			if(obj.getAuditFlag().equals(flag)) {
   			 String warnMsg=getModuleAssociatedWithTable(obj);
        		if(warnMsg!=null) {
        			msg=addMsg(msg,warnMsg);
        		}
   		 }
   		}
		
		return msg;
	}

	@Override
	public List<ModuleTables> getTablesDescription() {
		return oumtagreRepository.getTablesDescription();
	}
	
	@Override
	public String getViewAuditFlag(String moduleName) {
		return oumtagreRepository.getViewAuditFlag(moduleName);
	}
	

}