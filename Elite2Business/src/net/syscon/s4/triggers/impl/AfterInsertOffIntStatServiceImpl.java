package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.AfterInsertOffIntStatRepository;
import net.syscon.s4.triggers.AfterInsertOffIntStatService;
import net.syscon.s4.triggers.OffenderInstTequests;

@Service
public class AfterInsertOffIntStatServiceImpl implements AfterInsertOffIntStatService {
	@Autowired
	AfterInsertOffIntStatRepository afterInsertOffIntStatRepository;

	@Override
	public void AfterInsertOffIntStatTrigger(final OffenderInstTequests offenderInstTequests) {
		if (Optional.ofNullable(offenderInstTequests).isPresent()
				&& Optional.ofNullable(offenderInstTequests.getInternalstatus()).isPresent()
				&& Optional.ofNullable(offenderInstTequests.getReviewdate()).isPresent()) {
			afterInsertOffIntStatRepository.save(offenderInstTequests);
		}
	}

}
