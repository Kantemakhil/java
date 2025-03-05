package net.syscon.s4.pkgs.oumpurge;

import net.syscon.s4.common.beans.VHeaderBlock;

public interface OumpurgePkgService {

	String chkValueExists(final VHeaderBlock bean);
	
	String purgeOffenders(Long pRootOffenderId,Long pOffBookId,String pDelType,String modifyUserId);
	String sealingOffenders(Long offenderId,Long offenderBookId,String pSealType,String pSealFlag, String userName)throws Exception;
	
}