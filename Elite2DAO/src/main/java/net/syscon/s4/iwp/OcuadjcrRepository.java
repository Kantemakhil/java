package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.List;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.OcdlegloSanctionHty;
import net.syscon.s4.inst.legals.beans.OffenderSentencesHty;

/**
 * Interface OcuadjcrRepository
 */
public interface OcuadjcrRepository {
	Integer ctlBlkInsertOffenderSentenceHty(OffenderSentencesHty lstOffenderSentenceHty);

	List<OffenderSentencesHty> ctlBlkExecuteQuery(OffenderSentencesHty objOffenderSentenceHty);

	List<ReferenceCodes> rgReasonRecordGroup();
 
	String getStaffName(BigDecimal staffId); 
	
	BigDecimal getStaffId() ;
	
	BigDecimal preInsert();
	
	Integer postInsert(long counter,long offendreBookId,long sentenceSeq );

	List<OcdlegloSanctionHty> ocdlegloSenHtyExecuteQuery(OffenderSentencesHty searchBean);
	
	BigDecimal getSanctionHtyId();
	
	Integer ocdlegloSentCommit(OcdlegloSanctionHty commitBean); 
	
	BigDecimal getStaffId(String userId);

}
