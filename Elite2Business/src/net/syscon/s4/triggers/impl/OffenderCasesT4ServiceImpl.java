package net.syscon.s4.triggers.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.AddressesT2Repository;
import net.syscon.s4.triggers.AddressesT2Service;
import net.syscon.s4.triggers.OffenderCasesT3Repository;
import net.syscon.s4.triggers.OffenderCasesT3Service;
import net.syscon.s4.triggers.OffenderCasesT4Service;

@Service
public class OffenderCasesT4ServiceImpl implements OffenderCasesT4Service {
	Logger logger = LogManager.getLogger(OffenderCasesT4ServiceImpl.class.getName());


	@Autowired
	net.syscon.s4.triggers.OffenderCasesT4Repository OffenderCasesT4Repository;

	@Override
	public OffenderCases offenderCasesT3(final OffenderCases newOffenderCases) {
//		OffenderCases oldOffenderCases = OffenderCasesT4Repository.getOffenderCases(newOffenderCases.getCaseId());
		
		//TODO:IF NVL (SYS_CONTEXT ('NOMIS', 'AUDIT_MODULE_NAME', 50), 'X') <>
        //'CREATE_CASES'
		
//		if((Objects.nonNull(oldOffenderCases) && Objects.isNull(newOffenderCases))) {
		if((Objects.isNull(newOffenderCases))) {
			newOffenderCases.setLidsCaseNumber(OffenderCasesT4Repository.getLidsCaseNumber(newOffenderCases.getOffenderBookId()));
		}
		return newOffenderCases;
	}
}
