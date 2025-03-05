package net.syscon.s4.cm.demographics.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.CommunityHeaderStatuses;

/**
 * Interface OcmohstaRepository
 */
public interface OcmohstaRepository {
	Integer comHdrStDeleteCommunityHeaderStatuses(List<CommunityHeaderStatuses> object);

	Integer comHdrStUpdateCommunityHeaderStatuses(List<CommunityHeaderStatuses> object);

	List<CommunityHeaderStatuses> comHdrStExecuteQuery(CommunityHeaderStatuses object);

	Integer comHdrStInsertCommunityHeaderStatuses(List<CommunityHeaderStatuses> object);

	Integer gethierarchyCount(List<BigDecimal> hirarchyObj);

	Integer hirarchyDupValidationWhenUpdate(List<BigDecimal> hirarchyObj,List<String> rowId);
	
	Integer getStatuscodeCount(List<String> statusObj);

}
