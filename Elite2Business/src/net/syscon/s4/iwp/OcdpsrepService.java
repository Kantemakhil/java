package net.syscon.s4.iwp;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.CourtReportCharges;
import net.syscon.s4.common.beans.CourtReportChargesCommitBean;
import net.syscon.s4.common.beans.OrderPpslCondActivities;
import net.syscon.s4.common.beans.OrderPpslCondActivitiesCommitBean;
import net.syscon.s4.common.beans.OrderProposalConditions;
import net.syscon.s4.common.beans.OrderProposalConditionsCommitBean;
import net.syscon.s4.common.beans.OrderProposals;
import net.syscon.s4.common.beans.OrderProposalsCommitBean;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.beans.OrdersCommitBean;
import net.syscon.s4.common.beans.OrdersHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;

public interface OcdpsrepService  {
	
	 
	  List<ReferenceCodes> rgOrderTypeRecordGroup() ;
	  
	  List<ReferenceCodes> rgCourtAgyLocDescRecordGroup(final String caseloadId) ;
	  
	  List<ReferenceCodes> rgReportProposalRecordGroup() ;
	  
	  List<ReferenceCodes> rgProposedSentenceRecordGroup(final String sentenceCategory) ;
	  
	  List<ReferenceCodes> rgProposedRequirementRecordGroup(final String sentenceCategory) ;
	  
	  List<ReferenceCodes> rgProposedCategoryRecordGroup() ;
	  
	  List<Orders> ordExecuteQuery(final String offenderBookId) ;
	  
	  List<OrderProposals> ordProposalsExecuteQuery(final BigDecimal  orderId) ;
	  
	  List<OrderProposalConditions> ordPropCondsExecuteQuery(final OrderProposals proposals);
	  
	  List<OrderPpslCondActivities> ordPpslCondActExecuteQuery(final BigDecimal orderProposalConditionId);
	  
	  Integer ordCommit(final OrdersCommitBean CommitBean) ;
	 
	 Integer ordProposalsCommit(final OrderProposalsCommitBean CommitBean) ;
	 
	 Integer ordPropCondsCommit(final OrderProposalConditionsCommitBean CommitBean) ;
	 
	 Integer ordPpslCondActCommit(final OrderPpslCondActivitiesCommitBean commitBean) ;

	 List<Teams> rgTeamRecordGroup(Long offenderBookId, String userId);

	 List<ReferenceCodes> rgStaffMembersRecordGroup(String caseLoadId,String teamResponsible);

   	List<OrdersHty> ordHistoryExecuteQuery(BigDecimal orderId);
   	
   	List<ReferenceCodes> rgAccreditedProgramsRecordGroup();
   	
    Integer ordAuthorCommit(final Orders CommitBean) ;
    
    List<Orders> getComparedTeamAndStaff(final List<Orders> list);
    
    List<CourtReportCharges> chargesExecuteQuery(final CourtReportCharges searchBean);
    
    Integer chargesCommit(final CourtReportChargesCommitBean commitBean);
}
