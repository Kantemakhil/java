package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;

public interface OcdpersoService {
	List<OffenderContactPersons> offBkgOnCheckDeleteMaster(OffenderContactPersons paramBean);

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<ReferenceCodes> rgLanguageCodeRecordGroup();

	List<VPersonAddress> perAddrExecuteQuery(VPersonAddress objVPersonAddresses);

	Integer offCntPerCommit(OffenderContactPersonsCommitBean CommitBean);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	List<OffenderContactPersons> offCntPerExecuteQuery(OffenderContactPersons objOffenderContactPersons);

	List<ReferenceCodes> rgContactTypeRecordGroup();

	List<OffenderContactPersons> ChangeActiveFlag(OffenderContactPersons paramBean);

	Integer perIdentCommit(PersonIdentifiersCommitBean CommitBean);

	Integer perEmpCommit(PersonEmploymentsCommitBean CommitBean);

	List<Persons> perInfoExecuteQuery(Persons objPersons);

	List<PersonEmployments> perEmpExecuteQuery(PersonEmployments objPersonEmployments);

	List<ReferenceCodes> rgMaritalStatusRecordGroup();

	List<ReferenceCodes> rgSearchTypeRecordGroup();

	List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers objPersonIdentifiers);

	List<ReferenceCodes> rgRelTypeRecordGroup(String contactType);

	Integer perInfoCommit(PersonsCommitBean commitBean);

	Integer copyOffAddress(BigDecimal rootOffId, Long personId, String userNamae);

	Integer checkChildRecords(Long offenderBookId, Integer personId);

	Integer checkFutureVisits(Long offenderBookId, Integer personId);

	void cancelFutureVisit(Long offenderBookId, Integer personId, String userName);

}
