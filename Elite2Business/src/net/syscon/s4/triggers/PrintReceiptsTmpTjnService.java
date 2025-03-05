package net.syscon.s4.triggers;

import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;

public interface PrintReceiptsTmpTjnService {
	
	Integer printReceiptsTmpTjn(final PrintReceiptsTmp newRecord, final PrintReceiptsTmp oldRecord, final String operation);
}
