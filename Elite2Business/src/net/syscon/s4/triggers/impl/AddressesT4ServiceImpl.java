package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.triggers.AddressesT4Repository;
import net.syscon.s4.triggers.AddressesT4Service;
@Service
public class AddressesT4ServiceImpl implements AddressesT4Service{
@Autowired
private AddressesT4Repository addresesT4Repository;
	@Override
	public void AddresesT4Trigger(final Addresses object) {
		final Addresses databaseObject=new Addresses();
		if(databaseObject!=null && object!=null) {
			if((databaseObject.getCityCode()!=null && object.getCityCode()!=null  && databaseObject.getCityCode()!= object.getCityCode()) ||
					(databaseObject.getCityName()!=null && object.getCityName()!=null  && databaseObject.getCityName()!= object.getCityName()) ||
					(databaseObject.getProvStateCode()!=null && object.getProvStateCode()!=null  && databaseObject.getProvStateCode()!= object.getProvStateCode()) ||
					(databaseObject.getStreet()!=null && object.getStreet()!=null  && databaseObject.getStreet()!= object.getStreet()) ||
					(databaseObject.getStreetDirection()!=null && object.getStreetDirection()!=null  && databaseObject.getStreetDirection()!= object.getStreetDirection()) ||
					(databaseObject.getStreetNumber()!=null && object.getStreetNumber()!=null  && databaseObject.getStreetNumber()!= object.getStreetNumber()) ||
					(databaseObject.getSuiteNumber()!=null && object.getSuiteNumber()!=null  && databaseObject.getSuiteNumber()!= object.getSuiteNumber())){
				addresesT4Repository.deleteReleasePlansOp(object);
			}
		}
		
	}

}
