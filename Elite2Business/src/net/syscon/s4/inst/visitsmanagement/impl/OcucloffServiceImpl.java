package net.syscon.s4.inst.visitsmanagement.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VDistinctLinkedOffenders;
import net.syscon.s4.inst.visitsmanagement.OcucloffRepository;
import net.syscon.s4.inst.visitsmanagement.OcucloffService;

/**
 * Class OcucloffServiceImpl
 */
@Service
public class OcucloffServiceImpl extends BaseBusiness implements OcucloffService {

	@Autowired
	private OcucloffRepository ocucloffRepository;

	/**
	 * Creates new OcucloffServiceImpl class Object
	 */
	public OcucloffServiceImpl() {
		// OcucloffServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VDistinctLinkedOffenders> contactsExecuteQuery(final VDistinctLinkedOffenders searchRecord) {
		return ocucloffRepository.contactsExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup() {
		return ocucloffRepository.rgRelationshipTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		return ocucloffRepository.rgContactTypeRecordGroup();

	}

}