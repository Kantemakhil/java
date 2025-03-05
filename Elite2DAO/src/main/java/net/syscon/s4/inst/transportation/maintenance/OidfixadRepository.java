package net.syscon.s4.inst.transportation.maintenance;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssets;
import net.syscon.s4.inst.transportation.maintenance.beans.Vehicles;
/**
 * Interface OidfixadRepository
 */
public interface OidfixadRepository {

	Integer faInsertFixedAssets(List<FixedAssets> lstFixedAssets) ;

	Integer faDeleteFixedAssets(List<FixedAssets> lstFixedAssets) ;

	List<FixedAssets> faExecuteQuery(FixedAssets objFixedAssets) ;

	List<Vehicles> vehExecuteQuery(Vehicles objVehicles) ;

	Integer vehUpdateVehicles(List<Vehicles> lstVehicles) ;

	Integer faUpdateFixedAssets(List<FixedAssets> lstFixedAssets) ;

	Integer vehInsertVehicles(List<Vehicles> lstVehicles) ;
	
	Long genAssetId(String seqId);

	Integer vehicleIdCount(Long vehicleId);


}
