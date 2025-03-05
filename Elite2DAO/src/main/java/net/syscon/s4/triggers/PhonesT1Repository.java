package net.syscon.s4.triggers;

public interface PhonesT1Repository {

	Integer offendersRecord(Long ownerId);

	Integer personRecord(Long ownerId);

	Integer corporateRecord(Long ownerId);

	Integer staffRecord(Long ownerId);

	Integer locationRecord(String ownerId);

	Integer offenderEducationRecord(Long ownerId, Long ownerSeq);

	Integer offenderEmploymentRecord(Long ownerId, Long ownerSeq);

	Integer personEmploymentRecord(Long ownerId, Long ownerSeq);

	Integer addressRecord(Long ownerId);
}
