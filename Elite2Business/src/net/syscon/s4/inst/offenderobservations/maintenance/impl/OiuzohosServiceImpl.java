package net.syscon.s4.inst.offenderobservations.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.offenderobservations.maintenance.OiuzohosRepository;
import net.syscon.s4.inst.offenderobservations.maintenance.OiuzohosService;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;

@Service
public class OiuzohosServiceImpl extends BaseBusiness implements OiuzohosService {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuzohosServiceImpl.class.getName());
	
	@Autowired
	private OiuzohosRepository oiuzohosRepository;

	@Override
	public List<ReferenceCodes> rgUnitTypeRecordGroup() {
		return oiuzohosRepository.rgUnitTypeRecordGroup();
	}

	@Override
	public List<LivingUnits> rgLevel1LovData(final String unitTypeValue,  final String facility) {
		return oiuzohosRepository.rgLevel1LovData(unitTypeValue,facility);
	}

	@Override
	public List<LivingUnits> rgLevel2LovData(Integer livigUnitId) {
		return oiuzohosRepository.rgLevel2LovData(livigUnitId);
	}

	@Override
	public List<LivingUnits> rgLevel3LovData(Integer parentLivigUnitId) {
		return oiuzohosRepository.rgLevel3LovData(parentLivigUnitId);
	}

	@Override
	public List<LivingUnits> rgLevel4LovData(Integer parentLivigUnitId) {
		return oiuzohosRepository.rgLevel3LovData(parentLivigUnitId);
	}

	@Override
	public List<LivingUnits> zonehousingSeleExecuteQuery(LivingUnits searchBean) {
		List<LivingUnits> housingList = new ArrayList<LivingUnits>();
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		 housingList = oiuzohosRepository.housingDetailsExecuteQuery(searchBean);
		 if(housingList != null && !housingList.isEmpty()){
			 for(LivingUnits e : housingList) {
				Integer childCount =  housingList.stream().filter(o -> o.getParentLivingUnitId() != null && (o.getParentLivingUnitId().compareTo(e.getLivingUnitId()) == 0)).collect(Collectors.toList()).size();
				if(childCount == 0) {
					returnList.add(e);
				}
			 }
		 }
		 return returnList;
		 
		
	}

	@Override
	public Integer getZoneAssignedCount(OffObsZoneDetails searchBean) {
		return oiuzohosRepository.getZoneAssignedCount(searchBean);
	}

}
