package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;


/*===========================================================================================================
Below comments are copied from OFFENDER_EXTERNAL_MOVEMENTS_T8 Trigger
=============================================================================================================*/
/*
============================================================
  MODIFICATION HISTORY
   Person       Date                     version      Comments
 -----------  -------------------------- -----------  -------------------------------
  MANJUL       SEP 30, 2009	         1.1          Used modified proc update_obligation_WR
  MANJUL       AUG 13, 2009	         1.0          New trigger created
*/
@Service
public class OffenderExternalMovementsT8ServiceImpl implements OffenderExternalMovementsT8Service {
	Logger logger = LogManager.getLogger(OffenderExternalMovementsT8ServiceImpl.class);
	@Autowired
	TagProgrammesService tagProgrammesService;

	@Override
	public Integer updateObligationWR(final Long offenderBookId, final String movementType,String user) {
		Integer result = 0;
		try {
			if ("REL".equals(movementType)) {
				//Updating Procedure TagProgrammes 
				result = tagProgrammesService.updateObligationWr(offenderBookId,user);
			}
		} catch (final Exception e) {
			logger.error("updateObligationWR", e);
			return null;
		}
		return result;
	}

}
