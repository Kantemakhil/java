package net.syscon.s4.sa.admin;

import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Interface UpdoffidService
 */
public interface UpdoffidService {

	Integer checkOffenderIdDisplay(String offIdDisplay);

	Integer updateOffIdDisplay(VHeaderBlock searchBean);

}
