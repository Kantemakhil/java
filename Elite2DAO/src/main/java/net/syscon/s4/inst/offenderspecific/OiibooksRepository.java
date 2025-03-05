package net.syscon.s4.inst.offenderspecific;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Interface OiibooksRepository
 */
public interface OiibooksRepository {
	String bookDetailPostQuery(String paramBean);

	Integer checkFormAccess(FormAccessibleForms searchBean);

	List<OffenderBookings> offBooksExecuteQuery(OffenderBookings objOffenderBookings);

	List<FormAccessibleForms> bookDetailExecuteQuery(FormAccessibleForms objFormAccessibleForms);

	String getDataAvail(FormAccessibleForms objFormAccessibleForms);

	VHeaderBlock getOffenderObject(VHeaderBlock paramBean);
}
