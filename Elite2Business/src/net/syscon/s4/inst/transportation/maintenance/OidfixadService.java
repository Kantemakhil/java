package net.syscon.s4.inst.transportation.maintenance ;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssets;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssetsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.OidfixadCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Vehicles;
import net.syscon.s4.inst.transportation.maintenance.beans.VehiclesCommitBean;
/**
 * Interface OidfixadService 
 */
public interface OidfixadService  {

	List<FixedAssets> faExecuteQuery(FixedAssets objFixedAssets) ;

	List<Vehicles> vehExecuteQuery(Vehicles objVehicles) ;

	Integer faCommit(FixedAssetsCommitBean CommitBean) ;

	Integer vehCommit(VehiclesCommitBean commitBean) ;

	Integer oidfixadCommonSave(OidfixadCommitBean commitBean);

}
