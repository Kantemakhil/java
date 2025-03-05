package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;

/**
 * Interface OcucnperService
 */
public interface OcucnperService {
	List<ReferenceCodes> rgSexCodeRecordGroup();

	Integer personsCommit(PersonsCommitBean commitBean);

	List<Persons> personsExecuteQuery(Persons objPersons);

	Object personsPreInsert();

}
