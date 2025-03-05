package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VDistinctLinkedOffenders;

/**
 * Interface OcucloffRepository
 */
public interface OcucloffRepository {
	List<VDistinctLinkedOffenders> contactsExecuteQuery(VDistinctLinkedOffenders objVDistinctLinkedOffenders);

	List<ReferenceCodes> rgRelationshipTypeRecordGroup();

	List<ReferenceCodes> rgContactTypeRecordGroup();

}
