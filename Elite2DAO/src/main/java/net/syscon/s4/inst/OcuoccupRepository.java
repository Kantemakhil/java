package net.syscon.s4.inst;

import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;

public interface OcuoccupRepository {

	List<Phones> rpOtherOccupantsPostQuery(Phones paramBean);

	List<RpOtherOccupants> ocuoccupPostFormsCommit(RpOtherOccupants paramBean);

	List<RpOtherOccupants> ocuoccupKeyExit(RpOtherOccupants paramBean);

	List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(RpOtherOccupants searchRecord);

	Integer rpotheroccupantsInsertRpOtherOccupants(List<RpOtherOccupants> insertList);

	Integer rpOtherOccupantsUpdateRpOtherOccupants(List<RpOtherOccupants> updateList);

	Integer rpOtherOccupantsDeleteRpOtherOccupants(List<RpOtherOccupants> deleteList);

	List<OffenderContactPersons> rgPersonNameRecordGroup(String offenderBookId);

	List<ReferenceCodes> rgContactTypesRecordGroup();

	List<ReferenceCodes> rgRelationshipsRecordGroup(String contactCode);

	Addresses preFindAddressValues(VAddresses vaddressGetVal);

	Integer preAddressInsertRpOtherOccupants(List<Addresses> addressInsertList);

	String preFindAddressFlag(VAddresses vaddressGetVal);

	Integer preOffenderContactsInsertRpOtherOccupants(List<OffenderContactPersons> offContPerList);

	Long findNextContactPersonId();


}
