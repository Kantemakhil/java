package net.syscon.s4.common.beans.dao;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;

@XmlRootElement
public class OidadmisCommitBean {
	
private OffenderBookingsCommitBean offenderBookingsCommitBean;

private OffenderExternalMovementsCommitBean offenderExternalMovementsCommitBean;

public OffenderBookingsCommitBean getOffenderBookingsCommitBean() {
	return offenderBookingsCommitBean;
}

public void setOffenderBookingsCommitBean(OffenderBookingsCommitBean offenderBookingsCommitBean) {
	this.offenderBookingsCommitBean = offenderBookingsCommitBean;
}

public OffenderExternalMovementsCommitBean getOffenderExternalMovementsCommitBean() {
	return offenderExternalMovementsCommitBean;
}

public void setOffenderExternalMovementsCommitBean(
		OffenderExternalMovementsCommitBean offenderExternalMovementsCommitBean) {
	this.offenderExternalMovementsCommitBean = offenderExternalMovementsCommitBean;
}

}
