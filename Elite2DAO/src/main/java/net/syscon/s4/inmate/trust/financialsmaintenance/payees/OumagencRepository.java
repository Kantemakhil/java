package net.syscon.s4.inmate.trust.financialsmaintenance.payees;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;

/**
 * Interface OumagencRepository
 */
public interface OumagencRepository {
	List<Addresses> addrOnCheckDeleteMaster(Addresses paramBean);

	List<Phones> corpOnCheckDeleteMaster(Phones paramBean);

	List<InternetAddresses> iAddExecuteQuery(InternetAddresses objInternetAddresses);

	Integer iAddDeleteInternetAddresses(List<InternetAddresses> lstInternetAddresses);

	Integer iAddInsertInternetAddresses(List<InternetAddresses> lstInternetAddresses);
	
	Integer addressesUpdateAddresses(List<Addresses> lstAddresses);

	List<ReferenceCodes> rgIclassRecordGroup();

	Integer addPhUpdatePhones(List<Phones> lstPhones);

	List<Phones> addrOnCheckDeleteMaster(Phones paramBean);

	Integer addPhInsertPhones(List<Phones> lstPhones);

	List<Caseloads> rgCaseloadRecordGroup();

	Integer addPhDeletePhones(List<Phones> lstPhones);

	List<VCorporateAddresses> addrExecuteQuery(VCorporateAddresses objVCorporateAddresses);

	List<Phones> addPhExecuteQuery(Phones objPhones);

	List<ReferenceCodes> rgTypeRecordGroup();

	Integer corpUpdateCorporates(List<Corporates> lstCorporates);

	Integer iAddUpdateInternetAddresses(final List<InternetAddresses> lstInternetAddresses);

	List<VCorporateAddresses> corpOnCheckDeleteMaster(VCorporateAddresses paramBean);

	List<CorporateTypes> oumagencKeyExit(CorporateTypes paramBean);

	List<InternetAddresses> corpOnCheckDeleteMaster(InternetAddresses paramBean);

	Integer addressesDeleteAddresses(List<Addresses> lstAddresses);

	Integer corpInsertphones(List<Phones> list);

	List<Caseloads> corpPostQuery(Caseloads paramBean);

	List<Addresses> addressesExecuteQuery(Addresses objAddresses);

	List<Corporates> corpExecuteQuery(Corporates objCorporates);

	Integer corpDeleteCorporates(List<Corporates> lstCorporates);
	
	Integer corpInsertCorporates(List<Corporates> lstCorporates);

	List<Phones> corPhoneExecuteQuery(Phones searchRecord);

	BigDecimal getCorporateChilds(BigDecimal corporateId);

	BigDecimal getCorporateId();
	
	Phones getphonesoldRecord(Long phoneId);
	
	Addresses getAddressoldRecord(Long addressId);
	

}
