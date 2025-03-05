package net.syscon.s4.globalconfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;

import net.syscon.s4.common.beans.FormBuilderBean;
import net.syscon.s4.common.beans.OcmpconfUiBean;
import net.syscon.s4.common.beans.OdynfrmCommitBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

/**
 * Interface OcmpconfService
 */
public interface OcmpconfService {
	
	List<OcmpconfUiBean> loadData();
	
	Integer saveData(List<OcmpconfUiBean> compData);

	Integer saveFormbuilderData(FormBuilderBean formBean);

	List<FormBuilderBean> loadFormbuilderData();

	Integer commitformData(OdynfrmCommitBean odynfrmCommitBean);

	Integer updateFormbuilderData(FormBuilderBean formBean);

	OdynfrmSubmitDataBean getFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	Integer submitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<Map<String, Object>> getFormLovData();

	Map<String, Object> getFormData(String formId) throws JsonParseException, IOException;

	List<FormBuilderBean> getFormDataBasedOnModName(String formModuleName);
	
	List<Map<String, Object>> dynamicGridConfig();
	
	Integer verification(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<StaffMemberRoles> getStaffRoles(String userId);

	List<OdynfrmSubmitDataBean> getOcdleglsHytData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);
	
	List<ReferenceCodes>rgConditionCategory(String orderType);

	List<LegalUpdateReasons> rgOrderStatus(String orderType);
	
	void insertOcdLeglsHyt(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	OdynfrmSubmitDataBean revokeParOrder(Map<String, Object>  odynfrmSubmitDataBean, boolean timerFlag);

	String getAutomaticStatusFlag(String autoUpdateCode);

	void setOcdleglsStatus(List<OdynfrmSubmitDataBean> ordersUpdated);

	String deleteOrderFlag(String code, String userId);

	List<CourtEvents> crtEveExecuteQuery(CourtEvents searchBean);

	Integer deleteParoleEvents(List<OffenderPayrolEvent> paroleEvents);
	
	void insOcdleglsHtyStatus( OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<OdynfrmSubmitDataBean> getOffenderOrders(OdynfrmSubmitDataBean searchBean);

	Boolean checkOrderDependency(Integer offenderBookId, String screenId, String displayNo);

	String transformJson(String queryData,Boolean failedAppStatus);

	List<Map<String, Object>> populateSentType(String sentCategory);

	Integer sentenceEngineOffline(OdynfrmSubmitDataBean odynfrmSubmitDataBean);
	
	Boolean isRfcOrderDependent(OdynfrmSubmitDataBean odynfrmSubmitDataSummary);

	void updateHoldExpiryOrders(List<OdynfrmSubmitDataBean> holdOrdersUpated, String authorization);
	
	Map<String,String> getResultingStatusMap();

	List<ReferenceCodes> getCommenceType(String orderType);
}
