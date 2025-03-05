package net.syscon.s4.inst.offenderspecific;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Interface OiibooksService
 */
public interface OiibooksService {

	List<OffenderBookings> offBooksExecuteQuery(OffenderBookings objOffenderBookings);

	List<FormAccessibleForms> bookDetailExecuteQuery(FormAccessibleForms objFormAccessibleForms);

	String checkFormAccess(FormAccessibleForms searchBean);

	VHeaderBlock getOffenderObject(VHeaderBlock paramBean);

}
