package net.syscon.s4.sa.recordmaintenance.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.CmdModules;
import net.syscon.s4.sa.recordmaintenance.CmdworkRepository;
import net.syscon.s4.sa.recordmaintenance.CmdworkService;
import net.syscon.s4.sa.recordmaintenance.ModuleTriggers;
import net.syscon.s4.sa.recordmaintenance.ProsmainRepository;
import net.syscon.s4.sa.recordmaintenance.WorkItems;
import net.syscon.s4.sa.recordmaintenance.WorkItemsCommitBean;

@Service
public class CmdworkServiceImpl implements CmdworkService{
	@Autowired
	private CmdworkRepository cmdworkRepository;
	@Autowired
	private ProsmainRepository prosmainRepository;

	@Override
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		return cmdworkRepository.rgWorkTypeRecordGroup();
	}

	@Override
	public List<CmdModules> rgModulesRecordGroup(String columnName) {
		return cmdworkRepository.rgModulesRecordGroup(columnName);
		
	}

	@Override
	public List<BpmnProcess> rgProcessRecordGroup() {
		return cmdworkRepository.rgProcessRecordGroup();
	}

	@Override
	public List<WorkItems> workItemsExecuteQuery() {
		return cmdworkRepository.workItemsExecuteQuery();
	}

	@Transactional
	public Integer commitWorkItems(WorkItemsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = cmdworkRepository.insertWorkItems(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = cmdworkRepository.updateWorkItems(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = cmdworkRepository.deleteWorkItems(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<ModuleTriggers> rgModuleTriggersRecordGroup() {
		List<ModuleTriggers> moduleTriggersList = new ArrayList<ModuleTriggers>();
		moduleTriggersList = cmdworkRepository.rgModuleTriggersRecordGroup();
		for(ModuleTriggers moduleTriggers : moduleTriggersList) {
			List<String> variableList = new ArrayList<String>();
			Object dtoClass = null;
			if(!("".equals(moduleTriggers.getDtoName()))) {
				try {
					dtoClass = Class.forName(moduleTriggers.getDtoName()).newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				for(Field field : dtoClass.getClass().getDeclaredFields()) {
					variableList.add(field.getName());
				}
			}
			moduleTriggers.setVariableList(variableList);
			if(moduleTriggers.getTriggerName() != null) {
				moduleTriggers.setDescription(moduleTriggers.getModuleDescription() + " - " + moduleTriggers.getTriggerName());
			} else {
				moduleTriggers.setDescription(moduleTriggers.getModuleDescription());
			}
		}
		return moduleTriggersList;
	}

	@Override
	public Integer deleteTrigger(BpmnProcess bpmnProcess) {
		int result = 0;
		result = cmdworkRepository.deleteTrigger(bpmnProcess.getTriggerId(), bpmnProcess.getModifyUserId());
		if(result != 0) {
			return prosmainRepository.updateTrigger(bpmnProcess.getProcessId());
		} else {
			return result;
		}
	}

	@Override
	public List<BpmnProcess> validateDeploy(BpmnProcess bpmnProcess) {
		return cmdworkRepository.validateDeploy(bpmnProcess);
	}

	@Override
	public List<ReferenceCodes> getModuleTriggerData(String triggerId) {
		List<ReferenceCodes> variableList = new ArrayList<ReferenceCodes>();
		ModuleTriggers triggerData=cmdworkRepository.getModuleTriggerData(triggerId);
		Object dtoClass = null;
		if(triggerData!=null && !("".equals(triggerData.getDtoName())))
		{
				try {
					dtoClass = Class.forName(triggerData.getDtoName()).newInstance();
				} catch (Exception e) {
				} 
				for(Field field : dtoClass.getClass().getDeclaredFields()) {
					ReferenceCodes obj=new ReferenceCodes();
					obj.setDescription(field.getName());
					obj.setCode(field.getName());
					variableList.add(obj);
				}
			}
			
		
		
		return variableList;
	}
	
	@Override
	public String getTriggerName(String triggerId) {
		ModuleTriggers triggerData = cmdworkRepository.getModuleTriggerData(triggerId);
		return triggerData.getModuleDescription();
	}

}
