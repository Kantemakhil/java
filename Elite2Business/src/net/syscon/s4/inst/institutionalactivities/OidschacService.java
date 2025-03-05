package net.syscon.s4.inst.institutionalactivities;
import java.util.List;

import net.syscon.s4.inst.institutionalactivities.beans.VSchdPrisonActivities;

/**
 * Interface OidschacService 
 */
public interface OidschacService  {

	List<VSchdPrisonActivities> schActExecuteQuery(VSchdPrisonActivities obj) ;

}
