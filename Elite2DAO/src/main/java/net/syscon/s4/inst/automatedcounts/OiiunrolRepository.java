package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.im.beans.VLivingUnitOffenders;

/**
 * Interface OiiunrolRepository
 */
public interface OiiunrolRepository {
	List<VLivingUnitOffenders> rollListExecuteQuery(VLivingUnitOffenders object);

	String getExtLocation(VLivingUnitOffenders obj);

}
