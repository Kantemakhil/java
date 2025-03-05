package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.Offenders;

public interface OffenderExternalMovementsT3Service {

	String offenderExternalMovementsT3Trigger(OffenderExternalMovements offenExternalMov,Offenders offenders);

}
