package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons;

public interface OsipsearService {
	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<ReferenceCodes> rgLanguageCodeRecordGroup();

	List<Images> imageExecuteQuery(Images objImages);

	Integer personsCommit(TagPersonSearchGetPersons CommitBean);

	List<VPersonAddress> perAddrExecuteQuery(VPersonAddress objVPersonAddresses);

	List<TagPersonSearchGetPersons> personsExecuteQuery(TagPersonSearchGetPersons objTagPersonSearchGetPersons);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	Integer perAddrCommit(AddressesCommitBean CommitBean);

	Integer imageCommit(ImagesCommitBean commitBean);

	Integer perIdentCommit(PersonIdentifiersCommitBean CommitBean);

	Integer perEmpCommit(PersonEmploymentsCommitBean CommitBean);

	List<Persons> perInfoExecuteQuery(Persons objPersons);

	List<PersonEmployments> perEmpExecuteQuery(PersonEmployments objPersonEmployments);

	List<VAddresses> personsOnCheckDeleteMaster(VAddresses paramBean);

	Integer psPersonNameCommit(TagPersonSearchGetPartialSoundexPersonsCommitBean CommitBean);

	List<ReferenceCodes> rgMaritalStatusRecordGroup();

	List<ReferenceCodes> rgSearchTypeRecordGroup();

	List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers objPersonIdentifiers);

	Integer perInfoCommit(PersonsCommitBean commitBean);

	List<TagPersonSearchGetPartialSoundexPersons> psPersonNameExecuteQuery(
			TagPersonSearchGetPartialSoundexPersons objTagPersonSearchGetPartialSoundexPersons);

	List<Offenders> getAdditionalNames(Long personId);
	
	Integer personAddNamesCommit(OffendersCommitBean commitBean);
	
	List<TagPersonSearchGetPersons> getDataFromJisCommonSystemForPerson(Long intCorrelationId,String nameSearchType,String moduleName);


}
