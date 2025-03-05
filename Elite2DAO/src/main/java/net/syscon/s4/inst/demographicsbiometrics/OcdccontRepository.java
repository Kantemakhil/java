package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcdccontRepository
 * 
 */
public interface OcdccontRepository {
	Long phonesPreInsert();

	Integer phonesUpdatePhones(List<Phones> phones);

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Integer phonesInsertPhones(List<Phones> phones);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

	Integer phonesDeletePhones(List<Phones> phones);

}
