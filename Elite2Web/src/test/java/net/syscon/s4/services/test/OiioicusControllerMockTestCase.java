package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.VOffenderOicSanctions;
import net.syscon.s4.im.incidentsoic.beans.VOicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.VOicHearings;
import net.syscon.s4.im.incidentsoic.beans.VOicIncidents;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OiioicusControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OiioicusControllerMockTestCase extends AbstractMockTestCase {
		/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicIncidentsExecuteQueryTestMethod() {
		final VOicIncidents vOicIncidents = new VOicIncidents();
		vOicIncidents.setAgencyIncidentId(BigDecimal.valueOf(5078));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicIncidents);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicInciExecuteQuery", vOicIncidents)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicIncidentsRecordGroup() {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(null);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiioicus/rgOicHearingTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("APP")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicIncidentsRecordgroupTestMethod() {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(null);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiioicus/rgIncidentTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("BEH")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicIncidentsRecordGroupTest() {
		final OicOffences oicObj = new OicOffences();
		oicObj.setIncidentDate(new Date());
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(oicObj);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiioicus/rgOffenceTypeRecordGroup", oicObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("aaa")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicIncidentsRecordGroupTestMethod() {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(null);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiioicus/rgSanctionCodeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("CUST")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicHearingsExecuteQueryTestMethod() {
		final VOicHearings vOicHearingObj = new VOicHearings();
		vOicHearingObj.setOicHearingId(BigDecimal.valueOf(2264));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicHearingObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicHearSearchVOicHearings", vOicHearingObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicHearingType", is("DISC")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOicHearingResultsExecuteQueryTestMethod() {
		final VOicHearingResults vOicHearingResultsObj = new VOicHearingResults();
		vOicHearingResultsObj.setOicHearingId(BigDecimal.valueOf(2264));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicHearingResultsObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicHearResSearchVOicHearingResults", vOicHearingResultsObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicOffenceCode", is("15.1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOffenderOicSanctionsExecuteQueryTestMethod() {
		final VOffenderOicSanctions vOffOicSanObj = new VOffenderOicSanctions();
		vOffOicSanObj.setOffenderBookId(BigDecimal.valueOf(22300));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffOicSanObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOffOicSanctSearchVOffenderOicSanctions", vOffOicSanObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicIncidentId", is(2945)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiioicusOffbkgoncheckdeletemastervoicincicur() {
		final VOicIncidents vOicIncidentObj = new VOicIncidents();
		vOicIncidentObj.setOffenderBookId(BigDecimal.valueOf(18773));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicIncidentObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/offBkgOnCheckDeleteMastervOicInciCur", vOicIncidentObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiioicusVoicincioncheckdeletemastervoichearcur() {
		final VOicHearings vOicHearingsObj = new VOicHearings();
		vOicHearingsObj.setOicIncidentId(BigDecimal.valueOf(2821));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicHearingsObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicInciOnCheckDeleteMastervOicHearCur", vOicHearingsObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiioicusVoichearoncheckdeletemastervoichearrescur() {
		final VOicHearingResults vOicHearingResultsObj = new VOicHearingResults();
		vOicHearingResultsObj.setOicHearingId(BigDecimal.valueOf(2264));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOicHearingResultsObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicHearOnCheckDeleteMastervOicHearResCur", vOicHearingResultsObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiioicusVoichearresoncheckdeletemastervoffoicsanctcur() {
		final VOffenderOicSanctions vOffOicSanObj = new VOffenderOicSanctions();
		vOffOicSanObj.setOicHearingId(BigDecimal.valueOf(2264));
		vOffOicSanObj.setResultSeq(BigDecimal.valueOf(1));
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffOicSanObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiioicus/vOicHearResOnCheckDeleteMastervOffOicSanctCur", vOffOicSanObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void oiioicusGetprofilevaluevsprofvalcur() {
		final SystemProfiles systemProfObj = new SystemProfiles();
		systemProfObj.setProfileType("BIO");
		systemProfObj.setProfileCode("1021756");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(systemProfObj);
			final ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiioicus/getProfileValuevsProfvalCur", systemProfObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue2", is("19888")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	
}