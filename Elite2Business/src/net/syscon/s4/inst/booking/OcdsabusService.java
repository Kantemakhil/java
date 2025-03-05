package net.syscon.s4.inst.booking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetailsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatmentsCommitBean;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUses;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUsesCommitBean;

/**
 * Interface OcdsabusService
 */
public interface OcdsabusService {
	List<OffenderSubstanceTreatments> offStExecuteQuery(OffenderSubstanceTreatments objOffenderSubstanceTreatments);

	List<ReferenceCodes> cgfklkpOffSuOffSuRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffSdOffSdRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffStOffStRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> ageRecordGroup();

	List<OffenderSubstanceTreatments> offStPreInsert(OffenderSubstanceTreatments paramBean);

	List<ReferenceCodes> cgfkOffSuDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfkOffStDspDescriptionRecordGroup();

	List<OffenderSubstanceDetails> offSdExecuteQuery(OffenderSubstanceDetails objOffenderSubstanceDetails);

	List<OffenderSubstanceDetails> cguvchkOffenderSubstanceDe(OffenderSubstanceDetails paramBean);

	List<ReferenceCodes> cgfklkpOffSdOffSdRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffStOffStRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffSdDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfkchkOffStOffStRef(ReferenceCodes paramBean);

	OffenderSubstanceTreatments offStCommit(OffenderSubstanceTreatmentsCommitBean commitBean);

	List<OffenderSubstanceUses> offSuExecuteQuery(OffenderSubstanceUses objOffenderSubstanceUses);

	List<ReferenceCodes> cgfkchkOffStOffStRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffStDspDescription3RecordGroup();

	OffenderSubstanceUses offSuCommit(OffenderSubstanceUsesCommitBean commitBean);

	List<ReferenceCodes> lSourceInfoRecordGroup();

	OffenderSubstanceDetails offSdCommit(OffenderSubstanceDetailsCommitBean commitBean);

	List<OffenderSubstanceUses> cguvchkOffenderSubstanceUs(OffenderSubstanceUses paramBean);
	
	OffenderSubstanceUses onDeleteOfSuAbHistory(OffenderSubstanceUses obj);

}
