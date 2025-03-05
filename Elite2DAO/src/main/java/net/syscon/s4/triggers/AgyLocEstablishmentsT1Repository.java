package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.AgyLocEstablishments;

public interface AgyLocEstablishmentsT1Repository {
	AgyLocEstablishments getAgyLocEstablishments(String agyLocId, String establishType);

}
