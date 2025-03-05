package net.syscon.s4.pkgs.ocmsuwpj.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.pkgs.ocmsuwpj.OcmsuwpjPkgRepository;

@Repository
public class OcmsuwpjPkgRepositoryImpl extends RepositoryBase implements OcmsuwpjPkgRepository {
	
	private static Logger logger = LogManager.getLogger(OcmsuwpjPkgRepositoryImpl.class);

	
//This procedure is used to get_placement_det_cur from database
	@Override
	public String getPlacementCur(final BigDecimal corporateId) {
		final String sql = getQuery("GET_PLACEMENT_DET_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_corporate_id", corporateId),
				String.class);
	}
	//This procedure is used to get_placement_det_curfrom database
	@Override
	public VCorporateAddresses getPlacementAddressCur(final BigDecimal corporateId, final BigDecimal serviceaddressId) {
		final String sql = getQuery("GET_PLACEMENT_ADDRESS_DET_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_corporate_id", corporateId, "p_service_address_id", serviceaddressId),
				BeanPropertyRowMapper.newInstance(VCorporateAddresses.class));
	}
	
	@Override
	public VCorporateAddresses getPlacementAddressCurAylocId(String agyLocId, BigDecimal serviceaddressId) {
		final String sql = getQuery("GET_PLACEMENT_ADDRESS_FOR_AGYLOCID");
		VCorporateAddresses returBean = new VCorporateAddresses();
		try {
			returBean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agylocId", agyLocId, "addressId", serviceaddressId),
					BeanPropertyRowMapper.newInstance(VCorporateAddresses.class));
		} catch (Exception e) {
			returBean = null;
			logger.error(this.getClass().getName() + " getPlacementAddressCurAylocId " + e);
		}
		return returBean;
	}

}
