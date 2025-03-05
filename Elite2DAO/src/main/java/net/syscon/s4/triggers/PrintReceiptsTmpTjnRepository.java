package net.syscon.s4.triggers;

import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;

public interface PrintReceiptsTmpTjnRepository {
	
	Integer insertPrintReceiptsTmpTjn(final PrintReceiptsTmp obj);
	
	Integer updatePrintReceiptsTmpTjn(final PrintReceiptsTmp obj);
	
	Integer deletePrintReceiptsTmpTjn(final PrintReceiptsTmp obj);
}
