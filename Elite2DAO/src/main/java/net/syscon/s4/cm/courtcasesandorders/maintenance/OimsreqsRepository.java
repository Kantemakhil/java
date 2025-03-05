package net.syscon.s4.cm.courtcasesandorders.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceCustodyStatus;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.SentenceUpdateReasons;

/**
 * Interface OimsreqsRepository
 */
public interface OimsreqsRepository {
	Integer senTermsInsertSentenceTerms(List<SentenceTerms> lstSentenceTerms);

	List<ReferenceCodes> rgCatRecordGroup();

	List<LegalUpdateReasons> rgReasonRecordGroup();

	Integer senCalcInsertSentenceCalcTypes(List<SentenceCalcTypes> lstSentCalcTypes);

	List<SentenceTerms> senTermsExecuteQuery(SentenceTerms objSentenceTerms);

	Integer senTermsUpdateSentenceTerms(List<SentenceTerms> lstSentenceTerms);

	List<ReferenceCodes> rgFunctionTypeRecordGroup();

	Integer senUpdInsertSentenceUpdateReasons(List<SentenceUpdateReasons> lstSentCalcTypes);

	List<SentenceTerms> senCalcOnCheckDeleteMaster(SentenceTerms paramBean);

	List<SentenceUpdateReasons> senCalcOnCheckDeleteMaster(SentenceUpdateReasons paramBean);

	Integer senCalcUpdateSentenceCalcTypes(List<SentenceCalcTypes> lstSentCalcTypes);

	Integer senTermsDeleteSentenceTerms(List<SentenceTerms> lstSentenceTerms);

	List<SentenceUpdateReasons> senUpdExecuteQuery(SentenceUpdateReasons lstSentCalcTypes);

	Integer senUpdUpdateSentenceUpdateReasons(List<SentenceUpdateReasons> lstSentCalcTypes);

	Integer senUpdDeleteSentenceUpdateReasons(List<SentenceUpdateReasons> lstSentCalcTypes);

	Integer senCalcDeleteSentenceCalcTypes(List<SentenceCalcTypes> lstSentCalcTypes);

	List<ReferenceCodes> rgSentRecordGroup();

	List<SentenceCalcTypes> senCalcExecuteQuery(SentenceCalcTypes lstSentCalcTypes);

	List<ReferenceCodes> rgTermCodeRecordGroup();

	List<ReferenceCodes> rgSvcOblRecordGroup();

	SentenceUpdateReasons getNbtStatus(SentenceUpdateReasons lstSentCalcTypes);

	SentenceUpdateReasons getResDescValues(SentenceUpdateReasons objSearchDao);

	Integer senCalcKeyDelrec(SentenceCalcTypes paramBean);
	
	List<ReferenceCodes> getOrderStatus();
	
	List<ReferenceCodes> getCustodyStatus();
	
	Integer saveCustodyStatus(List<SentenceCustodyStatus> saveReasons);
	
	Integer updateCustodyStatus(List<SentenceCustodyStatus> UpdateReasons);
	
	Integer deleteCustodyStatus(List<SentenceCustodyStatus> lstSentenceUpdateReasons);
	
	List<SentenceCustodyStatus> fetchCustodyStatus(SentenceCustodyStatus status);

}
