package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunction;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkFunctionCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplateCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTriggerCommitBean;

/**
 * Interface OcmworksService
 */
public interface OcmworksService {
	List<WorkFunction> wfFunctionsExecuteQuery(WorkFunction object);

	List<InternetAddresses> wfEmailRecipientsExecuteQuery(InternetAddresses object);

	List<ReferenceCodes> rgFunctionRecordGroup();

	Work wfWorkTypesCommit(WorkCommitBean commitBean);

	List<WorkIwpTemplate> wfWorkTypesOnCheckDeleteMaster(WorkIwpTemplate paramBean);

	WorkTrigger wfTriggersCommit(WorkTriggerCommitBean commitBean);

	List<Work> wfWorkTypesExecuteQuery(Work objWorks);

	WorkFunction wfFunctionsCommit(WorkFunctionCommitBean commitBean);

	List<ReferenceCodes> rgTriggerNameRecordGroup();

	OmsModules wfWorkTypesPostQuery(OmsModules paramBean);

	List<ReferenceCodes> rgWorkTypeRecordGroup();

	List<InternetAddresses> wfEmailReturnPreInsert(InternetAddresses paramBean);

	InternetAddresses wfEmailRecipientsCommit(InternetAddressesCommitBean commitBean);

	List<IwpTemplates> rgTemplatesRecordGroup();

	List<ReferenceCodes> rgWorkSubTypeRecordGroup(String workType);

	List<Work> wfWorkEmailExecuteQuery(Work objWorks);

	List<ReferenceCodes> rgWorkflowTypeRecordGroup();

	List<ReferenceCodes> rgAgyLocTypeRecordGroup();

	Integer wfWorkEmailCommit(WorkCommitBean commitBean);

	List<WorkIwpTemplate> wfIwpTemplatesExecuteQuery(WorkIwpTemplate object);

	List<WorkTrigger> wfTriggersExecuteQuery(WorkTrigger object);

	List<OmsModules> rgModulesRecordGroup();

	WorkIwpTemplate wfIwpTemplatesCommit(WorkIwpTemplateCommitBean commitBean);

	List<InternetAddresses> wfEmailReturnExecuteQuery(InternetAddresses searchBean);

	Integer checkdays(WorkTrigger commitBean);

}
