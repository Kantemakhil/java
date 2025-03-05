package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;

/**
 * Interface OcuavlocRepository
 */
public interface OcuavlocRepository {
	List<VOcuavlocAvailable> avlLocExecuteQuery(VOcuavlocAvailable obj);

	List<VOcuavlocUnavailable> avlLocPreQuery(VOcuavlocUnavailable paramBean);

	List<VOcuavlocUnavailable> fboLocExecuteQuery(VOcuavlocUnavailable obj);

	List<VOcuavlocAvailable> getOcuavlocAvailable(VOcuavlocAvailable objSearchDao);

	List<VOcuavlocUnavailable> getOcuavlocUnAvailable(VOcuavlocUnavailable objSearchDao);

	VOcuavlocAvailable reCheckTimeSlot(VOcuavlocAvailable searchRecord);

	VOcuavlocAvailable reCheckTimeSlotCursorcVis(VOcuavlocAvailable searchRecord);

	VOcuavlocAvailable reCheckTimeSlotCursorcAll(VOcuavlocAvailable searchRecord);

}
