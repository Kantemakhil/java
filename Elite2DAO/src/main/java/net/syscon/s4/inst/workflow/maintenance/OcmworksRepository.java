package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunction;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;

/**
 * Interface OcmworksRepository
 */
public interface OcmworksRepository {
	List<WorkFunction> wfFunctionsExecuteQuery(WorkFunction object);

	List<InternetAddresses> wfEmailRecipientsExecuteQuery(InternetAddresses object);

	Integer wfEmailRecipientsUpdateInternetAddresses(List<InternetAddresses> object);

	Integer wfFunctionsDeleteWorkFunctions(List<WorkFunction> object);

	List<InternetAddresses> recipients(InternetAddresses paramBean);

	String wfWorkTypesInsertWorks(List<Work> object);

	List<ReferenceCodes> rgFunctionRecordGroup();

	Integer wfTriggersUpdateWorkTriggers(List<WorkTrigger> object);

	Integer wfWorkEmailInsertWorks(List<Work> lstWorks);

	Integer wfWorkTypesUpdateWorks(List<Work> lstWorks);

	Integer wfWorkEmailDeleteWorks(List<Work> lstWorks);

	String wfIwpTemplatesInsertWorkIwpTemplates(List<WorkIwpTemplate> object);

	Integer wfWorkEmailUpdateWorks(List<Work> lstWorks);

	List<WorkTrigger> wfWorkTypesOnCheckDeleteMaster(WorkTrigger paramBean);

	List<InternetAddresses> wfWorkTypesOnCheckDeleteMaster(InternetAddresses paramBean);

	List<Work> wfWorkTypesExecuteQuery(Work objWorks);

	Integer wfTriggersDeleteWorkTriggers(List<WorkTrigger> lstWorkTriggers);

	List<OmsModules> wfWorkTypesPostQuery(OmsModules paramBean);

	List<WorkFunction> wfWorkTypesOnCheckDeleteMaster(WorkFunction paramBean);

	String wfFunctionsInsertWorkFunctions(List<WorkFunction> object);

	List<ReferenceCodes> rgTriggerNameRecordGroup();

	Integer wfIwpTemplatesUpdateWorkIwpTemplates(List<WorkIwpTemplate> object);

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	Integer wfFunctionsUpdateWorkFunctions(List<WorkFunction> object);

	String wfTriggersInsertWorkTriggers(List<WorkTrigger> object);

	List<InternetAddresses> wfEmailReturnPreInsert(InternetAddresses paramBean);

	List<IwpTemplates> rgTemplatesRecordGroup();

	List<WorkIwpTemplate> wfWorkTypesOnCheckDeleteMaster(WorkIwpTemplate paramBean);

	String wfEmailRecipientsInsertInternetAddresses(List<InternetAddresses> object);

	List<ReferenceCodes> rgWorkSubTypeRecordGroup(String workType);

	List<Work> wfWorkEmailExecuteQuery(Work objWorks);

	Integer wfIwpTemplatesDeleteWorkIwpTemplates(List<WorkIwpTemplate> object);

	Integer wfWorkTypesDeleteWorks(List<Work> lstWorks);

	List<ReferenceCodes> rgWorkflowTypeRecordGroup();

	List<ReferenceCodes> rgAgyLocTypeRecordGroup();

	List<WorkIwpTemplate> wfIwpTemplatesExecuteQuery(WorkIwpTemplate object);

	Integer wfEmailRecipientsDeleteInternetAddresses(List<InternetAddresses> object);

	List<WorkTrigger> wfTriggersExecuteQuery(WorkTrigger object);

	List<OmsModules> rgModulesRecordGroup();

	List<InternetAddresses> wfEmailReturnExecuteQuery(InternetAddresses searchRecord);

	Integer getCountOffenderAssociated(String workType, String workSubType);

	Integer getCountIwpDocuments(WorkIwpTemplate bean);

	Integer checkdays(WorkTrigger bean);

	Work onCheckdeleteMaster(Work bean);

}
