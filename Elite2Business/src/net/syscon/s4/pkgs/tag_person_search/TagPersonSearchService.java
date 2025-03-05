package net.syscon.s4.pkgs.tag_person_search;

import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.inst.booking.beans.Persons;

public interface TagPersonSearchService {
	List<PersonProfiles> insertPersonProfileTypes(final Long personId, final String userName);

	List<Persons> getPartialSoundexPersons(final Persons persons);

	Date[] getDateRange(final Integer birthYear, final Integer birthRange);

	List<Persons> getPersons(final Persons persons) throws Exception;

	Long getNextIdSeq(final Long pPersonId);

	Long getNextEmpSeq(final Long pPersonId);

}