package net.syscon.s4.cm.courtcasesandorders.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceCalcTypescommitBean;
import net.syscon.s4.common.beans.SentenceCustodyStatus;
import net.syscon.s4.common.beans.SentenceCustodyStatusCommitBean;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.SentenceTermscommitBean;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.common.beans.SentenceUpdateReasonscommitBean;

/**
 * Interface OimsreqsService
 */
public interface OimsreqsService {
	List<ReferenceCodes> rgCatRecordGroup();

	List<LegalUpdateReasons> rgReasonRecordGroup();

	List<SentenceTerms> senTermsExecuteQuery(SentenceTerms objSentenceTerms);

	List<SentenceCalcTypes> senCalcCommit(SentenceCalcTypescommitBean commitBean);

	List<ReferenceCodes> rgFunctionTypeRecordGroup();

	List<SentenceUpdateReasons> senUpdExecuteQuery(SentenceUpdateReasons paremBean);

	List<SentenceTerms> senTermsCommit(SentenceTermscommitBean commitBean);

	List<ReferenceCodes> rgSentRecordGroup();

	List<SentenceCalcTypes> senCalcExecuteQuery(SentenceCalcTypes paremBean);

	List<ReferenceCodes> rgTermCodeRecordGroup();

	List<SentenceUpdateReasons> senUpdCommit(SentenceUpdateReasonscommitBean commitBean);

	List<ReferenceCodes> rgSvcOblRecordGroup();

	SentenceUpdateReasons getNbtStatusValue(SentenceUpdateReasons paremBean);

	Integer senCalcKeyDelrec(SentenceCalcTypes paramBean);

	List<ReferenceCodes> getOrderStatus();

	List<ReferenceCodes> getCustodyStatus();
	
	Integer custodyCommit(SentenceCustodyStatusCommitBean commitBean);
	
	List<SentenceCustodyStatus> fetchCustodyStatus(SentenceCustodyStatus status);

}
