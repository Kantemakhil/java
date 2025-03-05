package net.syscon.s4.inst.automatedcounts;

import java.util.List;

import net.syscon.s4.im.beans.VLivingUnitOffenders;
import net.syscon.s4.im.beans.VLivingUnitOffendersCommitBean;

/**
 * Interface OiiunrolService
 */
public interface OiiunrolService {
	Integer rollListCommit(VLivingUnitOffendersCommitBean commitBean);

	List<VLivingUnitOffenders> rollListExecuteQuery(VLivingUnitOffenders object);

}
