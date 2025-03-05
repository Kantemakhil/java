package net.syscon.s4.iwp;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.OcdlegloSanctionHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHtyCommitBean;
/** 
 * Interface OcuadjcrService 
 */
public interface OcuadjcrService  {
	Integer offSenHtyCommit(OffenderSentencesHtyCommitBean commitBean) ; 

	Integer ctlBlkCommit(OffenderSentencesHty commitBean) ;

	List<OffenderSentencesHty> ctlBlkExecuteQuery(OffenderSentencesHty objOffenderSentenceHty) ;

	List<ReferenceCodes> rgReasonRecordGroup() ;

	String staffName();

	List<OcdlegloSanctionHty> ocdlegloSenHtyExecuteQuery(OffenderSentencesHty searchBean);

	Integer ocdlegloSentCommit(OcdlegloSanctionHty commitBean); 
	
	

}
