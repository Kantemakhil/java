package net.syscon.s4.triggers;

import net.syscon.s4.inst.property.bean.OffenderPptyItems;

public interface OffenderPptyItemsT1Repository {

	Integer insert(final OffenderPptyItems old,final OffenderPptyItems newbean,final Integer vPropertyContainerId,final String lAgyLocId,final Integer vDisposedToPersonId, String user);

	
}
