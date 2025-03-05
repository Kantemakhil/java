package net.syscon.s4.inst.booking;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceDetails;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceTreatments;
import net.syscon.s4.inst.booking.beans.OffenderSubstanceUses;

/**
 * Interface OcdsabusRepository
 */
public interface OcdsabusRepository {
	List<ReferenceCodes> ageRecordGroup();

	Integer offSdInsertOffenderSubstanceDetails(List<OffenderSubstanceDetails> lstOffenderSubstanceDetails);

	List<ReferenceCodes> cgfkOffSuDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfkOffStDspDescriptionRecordGroup();

	List<ReferenceCodes> lSourceInfoRecordGroup();

	List<ReferenceCodes> cgfkchkOffStOffStRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffSdOffSdRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffStOffStRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffSdOffSdRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffSuOffSuRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffSdDspDescriptionRecordGroup();

	List<ReferenceCodes> cgfklkpOffStOffStRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfklkpOffSuOffSuRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkchkOffStOffStRefCod(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffStDspDescription3RecordGroup();

	List<OffenderSubstanceDetails> offSdExecuteQuery(OffenderSubstanceDetails objOffenderSubstanceDetails);

	Integer offSdDeleteOffenderSubstanceDetails(List<OffenderSubstanceDetails> lstOffenderSubstanceDetails);

	List<OffenderSubstanceTreatments> offStPreInsert(OffenderSubstanceTreatments paramBean);

	Integer offSuUpdateOffenderSubstanceUses(List<OffenderSubstanceUses> lstOffenderSubstanceUses);

	Integer offStUpdateOffenderSubstanceTreatments(List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments);

	Integer offStInsertOffenderSubstanceTreatments(List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments);

	List<OffenderSubstanceUses> offSuExecuteQuery(OffenderSubstanceUses objOffenderSubstanceUses);

	List<Integer> offStOnCheckDeleteMaster(OffenderSubstanceUses paramBean);

	List<OffenderSubstanceTreatments> offStExecuteQuery(OffenderSubstanceTreatments objOffenderSubstanceTreatments);

	List<OffenderSubstanceDetails> cguvchkOffenderSubstanceDe(OffenderSubstanceDetails paramBean);

	Integer offSuInsertOffenderSubstanceUses(List<OffenderSubstanceUses> lstOffenderSubstanceUses);

	Integer offSdUpdateOffenderSubstanceDetails(List<OffenderSubstanceDetails> lstOffenderSubstanceDetails);

	Integer offSuDeleteOffenderSubstanceUses(List<OffenderSubstanceUses> lstOffenderSubstanceUses);

	List<OffenderSubstanceUses> cguvchkOffenderSubstanceUs(OffenderSubstanceUses paramBean);

	List<Integer> offSuOnCheckDeleteMaster(OffenderSubstanceUses paramBean);

	Integer offStDeleteOffenderSubstanceTreatments(List<OffenderSubstanceTreatments> lstOffenderSubstanceTreatments);

	String getoffSdSequence(long offenderBookId, String substanceType);

	String getOffStSequnce(long offenderBookId, String substanceType);

}
