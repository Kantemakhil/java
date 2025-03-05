package net.syscon.s4.iwp;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CourtReportCharges;
import net.syscon.s4.common.beans.OrderPpslCondActivities;
import net.syscon.s4.common.beans.OrderProposalConditions;
import net.syscon.s4.common.beans.OrderProposals;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.beans.OrdersHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;

public interface OcdpsrepRepository {
	
	List<Orders> ordExecuteQuery(final String offenderBookId) ;
	
	String orderStatus(final String code);
	 
	 List<ReferenceCodes> rgOrderTypeRecordGroup() ;
	 
	 List<ReferenceCodes> rgCourtAgyLocDescRecordGroup(final String caseloadId) ;
	 
	 List<ReferenceCodes> rgReportProposalRecordGroup() ;
	 
	 List<ReferenceCodes> rgProposedSentenceRecordGroup(final String sentenceCategory) ;
	 
	 List<ReferenceCodes> rgProposedRequirementRecordGroup(final String sentenceCategory) ;
	 
	 List<ReferenceCodes> rgProposedCategoryRecordGroup() ;
	 
	
	 void createReportDone(Orders orders);
	 
	 List<OrderProposals> ordProposalsExecuteQuery(final BigDecimal orderId) ;
	 
	 List<OrderProposalConditions> ordPropCondsExecuteQuery(final OrderProposals proposals);
	 
	 List<OrderPpslCondActivities> ordPpslCondActExecuteQuery(final BigDecimal orderPropCondId) ;
	 
	 Integer ordUpdateOrders(final List<Orders> lstOrders) ;
	 
	 Integer ordProposalsInsert(final List<OrderProposals> lstOrderProposals) ;
	 
	 Integer ordProposalsUpdate(final List<OrderProposals> lstOrderProposals) ;
	 
	 Integer ordProposalsDelete(final List<OrderProposals> lstOrderProposals) ;
	 
	 Long propCondsPreInsert() ;
	
	 Integer ordPropCondsInsertOrderProposalConditions(final List<OrderProposalConditions> lstOrdPropConds) ;
	 
	 Integer ordPropCondsUpdateOrderProposalConditions(final List<OrderProposalConditions> lstOrdPropConds) ;
	 
	 Integer ordPropCondsDeleteOrderProposalConditions(final List<OrderProposalConditions>lstOrdPropConds) ;
	 
	 Integer ordPpslCondActInsertOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) ;
	 
	 Integer ordPpslCondActUpdateOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) ;
	 
	 Integer ordPpslCondActDeleteOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) ;

	Long ordPpslcondActpreInnsert();
	
	 void ordUpdateStatus(final BigDecimal orderId,String orderStatus);
	 
	 Integer ordInsertOrders(final List<Orders> lstOrders) ;

	List<Teams> rgTeamRecordGroup(Long offenderBookId, String userId);

	List<ReferenceCodes> rgStaffMembersRecordGroup(String caseLoadId,String teamResponsible);
	
	Integer ordInsertOrdersHty(final List<OrdersHty> lstOrders) ;

	List<OrdersHty> ordHistoryExecuteQuery(BigDecimal orderId);
	
   	List<ReferenceCodes> rgAccreditedProgramsRecordGroup();
   	
   	BigDecimal getOrdersSeq();
   	
   	Integer ordAuthorCommit(final Orders CommitBean) ;
   	
	Orders getOldRecordToCompareTeamAndStaff(BigDecimal orderId);

	List<CourtReportCharges> chargesExecuteQuery(final CourtReportCharges searchBean);
    
	Integer insertCharges(final List<CourtReportCharges> lstCourtReportCharges) ;
	 
	Integer deleteCharges(final List<CourtReportCharges> lstCourtReportCharges) ;
}
