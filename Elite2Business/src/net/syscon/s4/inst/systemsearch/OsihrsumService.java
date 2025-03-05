package net.syscon.s4.inst.systemsearch;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OsihrsumService
 */
public interface OsihrsumService {

	FormAccessibleForms fafCommit(FormAccessibleFormsCommitBean commitBean);

	List<OmsModules> cgfkFafDestinationFormRecordGroup();

	List<FormAccessibleForms> fafExecuteQuery(VHeaderBlock2 searchBean);

	List<VHistoricalBookings> vHisBooExecuteQuery(String rootOffenderId);

	Images getImageData(VHistoricalBookings searchBean);

}
