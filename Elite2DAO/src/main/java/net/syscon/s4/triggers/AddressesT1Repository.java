package net.syscon.s4.triggers;

import java.math.BigDecimal;

public interface AddressesT1Repository {
	Integer offendersRecord(Long ownerId);

	Integer personRecord(Long ownerId);

	Integer corporateRecord(Long ownerId);

	Integer staffRecord(Long ownerId);

	Integer locationRecord(String ownerCode);

	Integer offenderEducationRecord(Long ownerId, BigDecimal ownerSeq);

	Integer offenderEmploymentRecord(Long ownerId, BigDecimal ownerSeq);

	Integer personEmploymentRecord(Long ownerId, BigDecimal ownerSeq);

}
