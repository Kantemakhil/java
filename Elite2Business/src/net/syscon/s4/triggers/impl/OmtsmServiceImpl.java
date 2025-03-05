package net.syscon.s4.triggers.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.triggers.OmtsmService;
/* =========================================================
Below comments are copied from OMTSM Trigger
========================================================= */

/* MODIFICATION HISTORY
Person       Date            Version         Comments
---------    ------------    ------------    -----------------------------------------------
Surya        127-Jun-2005    1.1             Tr#554: Added NVL built in for the status, as it
                                                   was not working properly.
*/
@Service
public class OmtsmServiceImpl implements OmtsmService {
	@Override   
	public StaffMembers getOmtsam(StaffMembers newObject,StaffMembers oldObject,String operation) {
		if(operation.equalsIgnoreCase("Inserting") || operation.equalsIgnoreCase("Updating")) {
			if(!newObject.getStatus().equals(oldObject != null && oldObject.getStatus() != null? oldObject.getStatus():"N")) {
				if (newObject.getStatus().equals("ACTIVE")) {
					newObject.setSuspendedFlag("N");
				}else {
					newObject.setSuspendedFlag("Y");
				}
			}else if(!newObject.getSuspendedFlag().equals(oldObject != null && oldObject.getSuspendedFlag() != null?  oldObject.getSuspendedFlag():"N")) {
				if(newObject.getSuspendedFlag().equals("Y")) {
					newObject.setStatus("INACTIVE");
				}else {
					newObject.setStatus("ACTIVE");
				}
			}
		}
		
		if(operation.equalsIgnoreCase("Inserting") || ((operation.equalsIgnoreCase("Updating") &&  !newObject.getStatus().equals(oldObject.getStatus()))) ||
				((operation.equalsIgnoreCase("Updating") &&  !newObject.getSuspendedFlag().equals(oldObject.getSuspendedFlag())))){
			newObject.setAsOfDate(new Date());
		}
		return newObject;
	}

}
