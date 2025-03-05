package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;

/**
 * Interface OidmbrvlService
 */
public interface OidmbrvlService {
	Date validateValDate(OffenderStgDetails paramBean);

	BigDecimal offenderStgDetailsPreInsert(OffenderStgDetails paramBean);

	List<ReferenceCodes> rgActionRecordGroup();

	Integer offenderStgDetailsCommit(OffenderStgDetailsCommitBean commitBean);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<OffenderStgDetails> offenderStgDetailsExecuteQuery(OffenderStgDetails objOffenderStgDetails);

}
