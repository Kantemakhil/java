package net.syscon.s4.pkgs.merge_booking_utils.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.merge_booking_utils.MergeBookingUtilsRepository;
import net.syscon.s4.pkgs.merge_booking_utils.MergeBookingUtilsService;

@Service
public class MergeBookingUtilsServiceImpl implements MergeBookingUtilsService {

	private static Logger logger = LogManager.getLogger(MergeBookingUtilsServiceImpl.class.getName());

	@Autowired
	private MergeBookingUtilsRepository mergeBookingUtilsRepository;

}
