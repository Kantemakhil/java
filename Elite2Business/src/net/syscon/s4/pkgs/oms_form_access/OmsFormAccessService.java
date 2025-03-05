package net.syscon.s4.pkgs.oms_form_access;

import net.syscon.s4.common.beans.FormAccessibleForms;

public interface OmsFormAccessService {

	String checkDataAvailable(final FormAccessibleForms accForms);

	Boolean checkDataAvailableStg(final String orgForm, final String destForm, final Long stdId);
}