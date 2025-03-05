package net.syscon.s4.triggers;

import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

public interface OmtocontRepository {
	OffenderPptyContainers getOffenderPptyContainers(Integer propContaiId);
}
