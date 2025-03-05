package net.syscon.s4.pkgs.oms_stg;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.StgRelationships;

public interface OmsStgRepository {
	List<StgRelationships> stgIdCur(final Long stgId, final BigDecimal relatedStgId);

	Integer seqCur(final Long stgId);

	Integer omsStgInsert(final StgRelationships data);
}
