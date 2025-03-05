package net.syscon.s4.triggers.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgyLocEstablishments;
import net.syscon.s4.pkgs.oumagyht.OumagyhtPkgService;
import net.syscon.s4.triggers.AgyLocEstablishmentsT1Service;
/* ===========================================================
Below comments are copied from AGY_LOC_ESTABLISHMENTS_T1 Trigger
============================================================== */

/* MODIFICATION HISTORY
Person      Date            Version       Comments
---------   ------       ------------  ------------------------------
Patrick     27-JUN-2005    	    1.0   Initial Version

*/
@Service
public class AgyLocEstablishmentsT1ServiceImpl implements AgyLocEstablishmentsT1Service {
	Logger logger = LogManager.getLogger(AgyLocEstablishmentsT1ServiceImpl.class.getName());


	@Autowired
	net.syscon.s4.triggers.AgyLocEstablishmentsT1Repository agyLocEstablishmentsT1Repository;
	@Autowired
	OumagyhtPkgService oumagyhtService; 
	@Transactional
	@Override
	public void agyLocEstablishmentsT1(final AgyLocEstablishments newAgyLocEstablishments,String opeartionType,AgyLocEstablishments oldAgyLocEstablishments) {
		if(Objects.nonNull(newAgyLocEstablishments)) { 
			if(Objects.nonNull(oldAgyLocEstablishments) && Objects.isNull(newAgyLocEstablishments.getSealFlag()) || StringUtils.equals(oldAgyLocEstablishments != null ? oldAgyLocEstablishments.getSealFlag():null, newAgyLocEstablishments != null ? newAgyLocEstablishments.getSealFlag():null)) {
				AgencyLocationAmendments agencyLocationAmendments=new AgencyLocationAmendments();
				if(oumagyhtService.checkChanged(oldAgyLocEstablishments != null ? oldAgyLocEstablishments.getAgyLocId():null, newAgyLocEstablishments.getAgyLocId())) {
					agencyLocationAmendments.setAgyLocId(newAgyLocEstablishments.getAgyLocId());
					agencyLocationAmendments.setpColName("AGY_LOC_ID");
					agencyLocationAmendments.setpOldValue(oldAgyLocEstablishments != null ? oldAgyLocEstablishments.getAgyLocId():null);
					agencyLocationAmendments.setpNewValue(newAgyLocEstablishments.getAgyLocId());
					agencyLocationAmendments.setpDomain("");
					agencyLocationAmendments.setpDescType("");
					agencyLocationAmendments.setAmendUser(newAgyLocEstablishments.getCreateUserId());
					agencyLocationAmendments.setCreateUserId(newAgyLocEstablishments.getCreateUserId());
					if(!opeartionType.equals("INSERT") && !opeartionType.equals("DELETE")) {
						oumagyhtService.insertIntoAgyLocAmendments(agencyLocationAmendments);
					}
					
				}
				if(oumagyhtService.checkChanged( oldAgyLocEstablishments != null ?oldAgyLocEstablishments.getEstablishmentType():null, newAgyLocEstablishments.getEstablishmentType())) {
					if(true) { 
						agencyLocationAmendments.setAgyLocId(newAgyLocEstablishments.getAgyLocId());
						agencyLocationAmendments.setpColName("ESTABLISHMENT_TYPE");
						agencyLocationAmendments.setpOldValue(oldAgyLocEstablishments != null ?oldAgyLocEstablishments.getEstablishmentType():null);
						agencyLocationAmendments.setpNewValue(newAgyLocEstablishments.getEstablishmentType());
						agencyLocationAmendments.setpDomain("ESTAB_TYPE");
						agencyLocationAmendments.setpDescType("REF_CODE");
						agencyLocationAmendments.setAmendUser(newAgyLocEstablishments.getCreateUserId());
						agencyLocationAmendments.setCreateUserId(newAgyLocEstablishments.getCreateUserId());
						if(opeartionType.equals("INSERT")) {
							try {
							oumagyhtService.insertIntoAgyLocAmendments(agencyLocationAmendments);
							}catch (Exception e) {
								logger.error(e);
							}
						}
					}
					if(true) { 
						agencyLocationAmendments.setAgyLocId(oldAgyLocEstablishments != null ? oldAgyLocEstablishments.getAgyLocId():null);
						agencyLocationAmendments.setpColName("ESTABLISHMENT_TYPE");
						agencyLocationAmendments.setpOldValue(oldAgyLocEstablishments != null ?oldAgyLocEstablishments.getEstablishmentType():null);
						agencyLocationAmendments.setpNewValue(newAgyLocEstablishments.getEstablishmentType());
						agencyLocationAmendments.setpDomain("ESTAB_TYPE");
						agencyLocationAmendments.setpDescType("REF_CODE");
						agencyLocationAmendments.setAmendUser(newAgyLocEstablishments.getCreateUserId());
						agencyLocationAmendments.setCreateUserId(newAgyLocEstablishments.getCreateUserId());
						if(opeartionType.equals("DELETE")) {
							oumagyhtService.insertIntoAgyLocAmendments(agencyLocationAmendments);
						}
					}
				}

			}
		}
	}

}
