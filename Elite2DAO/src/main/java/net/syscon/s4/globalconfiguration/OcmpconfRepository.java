package net.syscon.s4.globalconfiguration;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.FormBuilderBean;
import net.syscon.s4.common.beans.OcmpconfUiBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

/**
 * Interface OcmpconfRepository
 */
public interface OcmpconfRepository {

	List<OcmpconfUiBean> loadData();
	
	Integer saveData(List<OcmpconfUiBean> compData);

	Integer saveFormbuilderData(FormBuilderBean formBean);

	List<FormBuilderBean> loadFormbuilderData();

	int insertOdynFrmData(List<FormBuilderBean> insertList);

	int updateOdynFrmData(List<FormBuilderBean> updateList);

	int createTable(String formName);

	OdynfrmSubmitDataBean getFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	Integer submitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	Integer updateSubmitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	Integer getFormSeqID(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<Map<String, Object>> getFormLovData();

	Map<String, Object> getFormData(String formId);

	Integer createModule(FormBuilderBean formBuilderBean);

	List<FormBuilderBean> getFormDataBasedOnModName(String formModuleName);
	
	List<Map<String, Object>> dynamicGridConfig();
	
	Integer updateActionType(OdynfrmSubmitDataBean odynfrmSubmitDataBean);
	
	Integer insertOcdLeglsHyt(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<StaffMemberRoles> getStaffRoles(String userId);

	List<OdynfrmSubmitDataBean> getOcdleglsHytData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);
	
	List<ReferenceCodes>rgConditionCategory(String orderType);

	List<LegalUpdateReasons> rgOrderStatus(String orderType);
	
	List<OdynfrmSubmitDataBean> getLegalsData();
	
	Integer updateLegalsData(List<OdynfrmSubmitDataBean> legalsData);
	
	String getStatusFlag(String code);
	
	String getResultingStatus(String status);
	
	List<OdynfrmSubmitDataBean> getLegalSummaryData();
	
	List<OffenderSentConditions> getConditionsData();
	
	Integer updateConditions(List<OffenderSentConditions> condData);

	List<LegalUpdateUsages> legalUpdateUsages(String legalClass);

	String deleteOrderFlag(String code, String userId);

	Integer deleteSubmitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean);

	List<CourtEvents> crtEveExecuteQuery(CourtEvents searchBean);
	
	String getCustodyStatus(Long offenderBookId);
	
	String getStatusDesc(String status);

	Integer deleteParoleEvents(List<OffenderPayrolEvent> paroleEvents);

	List<OdynfrmSubmitDataBean> getOffenderOrders(OdynfrmSubmitDataBean odynfrmSubmitDataBean);
	
	List<IwpDocuments> checkOrderDependency(Integer offenderBookId, String screenId, String displayNo);

	Integer getOcdleglsSequenceID();

	List<ReferenceCodes> getCommenceType();
}
