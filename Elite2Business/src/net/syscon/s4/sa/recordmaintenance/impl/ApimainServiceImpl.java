package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.recordmaintenance.ActionApi;
import net.syscon.s4.sa.recordmaintenance.ActionApiCommitBean;
import net.syscon.s4.sa.recordmaintenance.ApimainRepository;
import net.syscon.s4.sa.recordmaintenance.ApimainService;
import net.syscon.s4.sa.recordmaintenance.AutomationApiQuery;
import net.syscon.s4.sa.recordmaintenance.AutomationQueryParameters;
import net.syscon.s4.sa.recordmaintenance.CmdactionService;

@Service
public class ApimainServiceImpl implements ApimainService{
	@Autowired
	private ApimainRepository apimainRepository;
	
	@Autowired
	private CmdactionService cmdactionService;
	
	private static Logger logger = LogManager.getLogger(ApimainServiceImpl.class.getName());

	@Override
	public List<ActionApi> apimainExecuteQuery() {
		return  apimainRepository.apimainExecuteQuery();
	}

	@Transactional
	public Integer apimainCommit(ActionApiCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = apimainRepository.insertActionapi(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = apimainRepository.updateActionApi(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = apimainRepository.deleteActionApi(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> rgQueryKeyRecordGroup() {
		return  apimainRepository.rgQueryKeyRecordGroup();
	}

	@Override
	public List<ActionApi> getQuickActions() {
		List<ActionApi> actionApiList = null;
		AutomationApiQuery automationApiQuery = new AutomationApiQuery();
		actionApiList = apimainRepository.getQuickActions();
		for(ActionApi actionApi : actionApiList) {
			automationApiQuery.setQueryKey(actionApi.getQueryKey());
			List<AutomationQueryParameters> paramList= cmdactionService.parametersExecuteQuery(automationApiQuery);
			actionApi.setParamList(paramList);
		}
		return actionApiList;
	}

	@Override
	public List<ReferenceCodes> rgUrlKeyRecordGroup() {
		List<ReferenceCodes> urlList = new ArrayList<ReferenceCodes>();
		String[] codes = {"Elite2Web/api/cmdaction/executeSelectQuery",
				"Elite2Web/api/cmdaction/executeUpdateQuery",
				"Elite2Web/api/cmdaction/batchUpdateQuery",
				"Elite2Web/api/cmdaction/getSeqQuery",
				"Elite2Web/api/prosmain/postSms",
				"Elite2Web/oauth/token",
				"Elite2Web/api/serviceBusController/convertJson",
				"Elite2Web/api/externalsystem/connectExternalSystem",
				"Elite2Web/api/cmdaction/templateText",
				"Elite2Web/api/jobs/tagschedulejob",
				"Elite2Web/api/Oiiobalx/MaintainOffenderExternalBalance",
				"Elite2Web/api/ocmpconf/getOcdleglsData",
				"Elite2Web/api/ocdofacc/offFeeBillsTrigger",
				"Elite2Web/api/ocipensc/deletePendingSentenceCalcEvents",
				"Elite2Web/api/cmdaction/remissionDueNotify",
				"Elite2Web/api/cmdaction/saveOffAllowPayDet"};
		String[] descriptions = {"Select",
				"Update",
				"Batch Update",
				"Sequence",
				"Send SMS",
				"Syscon Auth",
				"Connect Service Bus",
				"Connect External System Service",
				"Template Text",
				"Update Course Schedule",
				"Update Account Balance",
				"Get Legal Summary Data",
				"Offender Fee Bill Triggers",
				"Delete Pending Calculation Events",
				"Remission Due Notify",
				"Offender Allowances Pay"};
		for(int i=0; i<codes.length; i++) {
			ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode(codes[i]);
			referenceCodes.setDescription(descriptions[i]);
			urlList.add(referenceCodes);
		}
		return urlList;
	}
}
