package net.syscon.s4.pkgs.tag_person_search;

import java.util.List;

import net.syscon.s4.inst.booking.beans.Persons;

public interface TagPersonSearchRepository {
	Integer insertPersonProfileTypes(final Long personId, final String userName);

	List<Persons> pResultSet(final Persons persons);

	List<Persons> pResultSetOne(final Persons persons);

	List<Persons> pResultSetOneFP(final Persons persons);

	List<Persons> pResultSetTwo(final Long personId);

	List<Persons> pResultSetThree(final String identifierType, final String identifier);

	List<Persons> pResultSetFour(final String identifierType, final String identifier);

	Long getNextIdSeq(final Long pPersonId);

	Long getNextEmpSeq(final Long pPersonId);

}