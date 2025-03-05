package net.syscon.s4.sa.admin;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.im.beans.Offenders;

/**
 * Interface UpdoffidRepository
 */
public interface UpdoffidRepository {

	Integer checkOffenderIdDisplay(String offIdDisplay);

	Integer updateOffIdDisplay(String offIdDisplay, BigDecimal rootOffId , String modifyUserId);

	Integer insertOffIdentifiers(OffenderIdentifier searchBean);

	String getoffenderSeq(BigDecimal offenderId);

	String getProfileValue();
	
	List<Offenders> getOldOffender(final BigDecimal offIdDisplay);

}
