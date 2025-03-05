package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.au.OffenderRequests;
import net.syscon.s4.triggers.OmtoreqdRepository;
import net.syscon.s4.triggers.OmtoreqdService;

@Service
public class OmtoreqdServiceImpl implements OmtoreqdService {
	private final Logger logger = LogManager.getLogger(OmtoreqdServiceImpl.class.getName());
	@Autowired
	OmtoreqdRepository omtoreqdRepository;

	@Override
	public Integer save(final List<OffenderRequests> offenderRequestsList) {
		Integer result = 0;
		if (!offenderRequestsList.isEmpty()) {
			try {
				for (final OffenderRequests newObj : offenderRequestsList) {
					final OffenderRequests old = omtoreqdRepository.getOffenderRequests(newObj);
					if (Optional.ofNullable(old).isPresent()) {
						result = omtoreqdRepository.save(old);
					}
				}
			} catch (final Exception e) {
				result = 0;
				logger.error("save");
			}
		}
		return result;
	}

}
