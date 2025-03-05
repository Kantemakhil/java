package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OidmbrvlRepository
 */
public interface OidmbrvlRepository {
	OmsModules createFormGlobals(OmsModules paramBean);

	BigDecimal offenderStgDetailsPreInsertPreInsert(OffenderStgDetails paramBean);

	List<ReferenceCodes> rgActionRecordGroup();

	Integer offenderStgDetailsInsertOffenderStgDetails(List<OffenderStgDetails> lstOffenderStgDetails);

	Date validateValDate(OffenderStgDetails paramBean);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<OffenderStgDetails> offenderStgDetailsExecuteQuery(OffenderStgDetails objOffenderStgDetails);

	void updateOffenderStgAffiliationsAppealDate(OffenderStgDetails paramBean);

}
