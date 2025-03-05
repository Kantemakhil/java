package net.syscon.s4.inst.visitsmanagement;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons;

public interface OsipsearRepository {
	List<ReferenceCodes> rgSexCodeRecordGroup();

	Integer perIdentInsertPersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	Integer perIdentUpdatePersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	List<ReferenceCodes> rgLanguageCodeRecordGroup();

	List<Images> imageExecuteQuery(Images objImages);

	Integer perIdentDeletePersonIdentifiers(List<PersonIdentifiers> lstPersonIdentifiers);

	List<TagPersonSearchGetPersons> personsExecuteQuery(TagPersonSearchGetPersons objTagPersonSearchGetPersons);

	List<Images> personsOnCheckDeleteMaster(Images paramBean);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	PersonEmployments personsOnCheckDeleteMaster(PersonEmployments paramBean);

	Integer psPersonNameDeleteTagPersonSearchGetPartialSoundexPersons(
			List<TagPersonSearchGetPartialSoundexPersons> lstTagPersonSearchGetPartialSoundexPersons);

	List<PersonEmployments> perEmpExecuteQuery(PersonEmployments objPersonEmployments);

	List<VPersonAddress> personsOnCheckDeleteMaster(VPersonAddress paramBean);

	List<ReferenceCodes> rgSearchTypeRecordGroup();

	Integer perInfoUpdatePersons(List<Persons> lstPersons);

	List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers objPersonIdentifiers);

	Integer perEmpInsertPersonEmployments(List<PersonEmployments> lstPersonEmployments);

	List<TagPersonSearchGetPartialSoundexPersons> psPersonNameExecuteQuery(
			TagPersonSearchGetPartialSoundexPersons objTagPersonSearchGetPartialSoundexPersons);

	List<VPersonAddress> perAddrExecuteQuery(VPersonAddress objVPersonAddresses);

	Persons personsOnCheckDeleteMaster(Persons paramBean);

	List<Persons> perInfoExecuteQuery(Persons objPersons);

	PersonIdentifiers personsOnCheckDeleteMaster(PersonIdentifiers paramBean);

	List<ReferenceCodes> rgMaritalStatusRecordGroup();

	Integer perEmpDeletePersonEmployments(List<PersonEmployments> lstPersonEmployments);

	Integer perEmpUpdatePersonEmployments(List<PersonEmployments> lstPersonEmployments);

	List<Offenders> getAdditionalNames(Long personId);
	
	Integer offInsertOffenders(final List<Offenders> lstOffenders) ;
	
	Integer updateOffenders(final List<Offenders> lstOffenders);
	
	Integer deleteOffenders(final List<Offenders> lstOffenders);
	
	TagPersonSearchGetPersons getDataFromJisCommonSystem(Long intCorrelationId);
	
	String getIdentifierData(BigDecimal personId);
	
	List<TagPersonSearchGetPersons> getAdditionaNamesAsParent(TagPersonSearchGetPersons searchRecord);
	
	TagPersonSearchGetPersons getgetAdditionaNamesDetails(BigDecimal personId);
}
