package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderExtMovementsTwfRepository;
@Repository
public class OffenderExtMovementsTwfRepositoryImpl extends RepositoryBase implements OffenderExtMovementsTwfRepository{
	Logger logger = LogManager.getLogger(OffenderExtMovementsTwfRepositoryImpl.class.getName());
}
