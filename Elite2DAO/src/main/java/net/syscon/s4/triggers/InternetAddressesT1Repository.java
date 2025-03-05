package net.syscon.s4.triggers;

public interface InternetAddressesT1Repository {

	Integer offendersRecord(Long ownerId);

	Integer addressRecord(Long ownerId);

	Integer personRecord(Long ownerId);

	Integer corporateRecord(Long ownerId);

	Integer staffRecord(Long ownerId);

	Integer locationRecord(String ownerCode);

	Integer offenderEducationRecord(Long ownerId, Long ownerSeq);

	Integer offenderEmploymentRecord(Long ownerId, Long ownerSeq);

	Integer personEmploymentRecord(Long ownerId, Long ownerSeq);

	Integer workflowRecord(Long ownerId);

}
