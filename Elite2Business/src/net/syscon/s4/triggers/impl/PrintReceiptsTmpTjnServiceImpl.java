package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.triggers.PrintReceiptsTmpTjnRepository;
import net.syscon.s4.triggers.PrintReceiptsTmpTjnService;

@Service
public class PrintReceiptsTmpTjnServiceImpl implements PrintReceiptsTmpTjnService {

	@Autowired
	private PrintReceiptsTmpTjnRepository printReceiptsTmpTjnRepository;

	@Override
	public Integer printReceiptsTmpTjn(final PrintReceiptsTmp newRecord, final PrintReceiptsTmp oldRecord,
			final String operation) {
		Integer val = 0;
		if (operation.equalsIgnoreCase("INSERT")) {
			val= printReceiptsTmpTjnRepository.insertPrintReceiptsTmpTjn(newRecord);
		} else if (operation.equalsIgnoreCase("UPDATE")) {
			val = printReceiptsTmpTjnRepository.updatePrintReceiptsTmpTjn(oldRecord);
		} else if (operation.equalsIgnoreCase("DELETE")) {
			val = printReceiptsTmpTjnRepository.deletePrintReceiptsTmpTjn(oldRecord);
		}
		return val;
	}
}
