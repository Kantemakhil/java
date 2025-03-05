package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.VPersonAddress;

public interface OcdpersoRepository {
	List<OffenderContactPersons> changeActiveFlag(OffenderContactPersons paramBean);

	List<ReferenceCodes> rgSexCodeRecordGroup();

	Integer perIdentInsertPersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	Integer perIdentUpdatePersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	List<ReferenceCodes> rgLanguageCodeRecordGroup();

	Integer perIdentDeletePersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	List<OffenderContactPersons> checkDuplicateContacts(OffenderContactPersons paramBean);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	List<OffenderContactPersons> offCntPerExecuteQuery(OffenderContactPersons objOffenderContactPersons);

	List<ReferenceCodes> rgContactTypeRecordGroup();

	Integer offCntPerInsertOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);

	List<PersonEmployments> perEmpExecuteQuery(PersonEmployments objPersonEmployments);

	List<OffenderContactPersons> offBkgOnCheckDeleteMaster(OffenderContactPersons paramBean);

	List<ReferenceCodes> rgSearchTypeRecordGroup();

	Integer perInfoUpdatePersons(List<Persons> lstPersons);

	List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers objPersonIdentifiers);

	Integer offCntPerDeleteOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);

	List<ReferenceCodes> rgRelTypeRecordGroup(String contactType);

	Integer perEmpInsertPersonEmployments(List<PersonEmployments> lstPersonEmployments);

	List<VPersonAddress> perAddrExecuteQuery(VPersonAddress objVPersonAddresses);

	Integer offCntPerUpdateOffenderContactPersons(List<OffenderContactPersons> lstOffenderContactPersons);

	List<Persons> perInfoExecuteQuery(Persons objPersons);

	List<ReferenceCodes> rgMaritalStatusRecordGroup();

	Integer perEmpDeletePersonEmployments(List<PersonEmployments> lstPersonEmployments);

	Integer perEmpUpdatePersonEmployments(List<PersonEmployments> lstPersonEmployments);

	OffenderContactPersons getpersonnames(Integer personId);

	Integer copyOffAddr(Integer rootOffId, Integer personId);

	Integer checkChildRecordsCurOne(Long offenderBookId, Integer personId);

	Integer checkChildRecordsCurTwo(Long offenderBookId, Integer personId);

	Integer checkFutureVisits(Long offenderBookId, Integer personId);

	void cancelFutureVisits(Long offenderBookId, Integer personId);

}
