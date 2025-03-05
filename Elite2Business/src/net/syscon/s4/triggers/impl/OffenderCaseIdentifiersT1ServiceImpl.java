package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.triggers.OffenderCaseIdentifiersT1Repository;
import net.syscon.s4.triggers.OffenderCaseIdentifiersT1Service;

@Service
public class OffenderCaseIdentifiersT1ServiceImpl implements OffenderCaseIdentifiersT1Service {
	Logger logger = LogManager.getLogger(OffenderCaseIdentifiersT1ServiceImpl.class.getName());
	@Autowired
	OffenderCaseIdentifiersT1Repository offenderCaseIdentifiersT1Repository;

	@Override
	public Integer getOffenderCaseIdentifiers(final List<OffenderCaseIdentifiers> offenderCaseIdentifiersList)
			throws CustomException {
		if (!offenderCaseIdentifiersList.isEmpty()) {
			for (final OffenderCaseIdentifiers newObj : offenderCaseIdentifiersList) {
				final OffenderCaseIdentifiers old = offenderCaseIdentifiersT1Repository.getOffenderCaseIdentifiers(newObj);
				if (newObj.getSealFlag() == null || (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
					if ("PNC".equals(newObj.getIdentifierType())) {
						final Integer vNumrows = offenderCaseIdentifiersT1Repository.vNumrows(newObj);
						if (vNumrows > 0) {
							throw new CustomException("The PNC number exists in the system. Please check.");
						}
					}
				}
			}
		}
		return null;
	}

}
