package net.syscon.s4.inst.visitsmanagement;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;

import java.util.List;

/**
 * Interface OcucnperRepository
 */
public interface OcucnperRepository {
	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<Persons> personsExecuteQuery(Persons objPersons);

	Long personsPreInsert();

	Integer personsInsertPersons(List<Persons> lstPersons);

}
