package net.syscon.s4.inst.systemsearch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OsihrsumRepository
 */
public interface OsihrsumRepository {

	List<OmsModules> cgfkFafDestinationFormRecordGroup();

	List<VHistoricalBookings> vHisBooExecuteQuery(String rootOffenderId);

	List<FormAccessibleForms> fafExecuteQuery(String userName);

	FormAccessibleForms fafInsertFormAccessibleForms(List<FormAccessibleForms> lstFormAccessibleForms);

	FormAccessibleForms fafDeleteFormAccessibleForms(List<FormAccessibleForms> lstFormAccessibleForms);

	Images getImageData(VHistoricalBookings searchBean);

	Map<String, Object> checkDataAvaliable(String pOrigForm, String destinationForm, BigDecimal offenderBookId,
			BigDecimal rootOffenderId);

	String getCommentCur(int offenderBookId, int inMovementSeq);

	String getCommCommentCur(VHistoricalBookings vHistoricalBookings, String additionDate, String additionTime);

	String closeCommentCur(VHistoricalBookings vHistoricalBookings, String additionDate, String additionTime);
	
	String getOffenderReleaseComment(int offenderBookId,int movementSeq);

}
