package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;

/**
 * Interface OcuavlocService
 */
public interface OcuavlocService {
	List<VOcuavlocUnavailable> avlLocPreQuery(VOcuavlocUnavailable paramBean);

	List<VOcuavlocAvailable> avlLocExecuteQuery(VOcuavlocAvailable obj);

	List<VOcuavlocUnavailable> fboLocExecuteQuery(VOcuavlocUnavailable obj);

	List<VOcuavlocAvailable> getOcuavlocAvailable(VOcuavlocAvailable searchBean);

	List<VOcuavlocUnavailable> getOcuavlocUnAvailable(VOcuavlocUnavailable searchBean);

	Integer reCheckTimeSlot(VOcuavlocAvailable searchBean);

}
