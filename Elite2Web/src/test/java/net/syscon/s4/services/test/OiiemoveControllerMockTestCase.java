package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 *class OiiemoveControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiiemoveControllerMockTestCase extends AbstractMockTestCase  {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderExternalMovementsExecuteQueryTestMethod() {
	try {
		final OffenderExternalMovements searchResult = new OffenderExternalMovements();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(searchResult);
        ResultActions resultActions = this.mockMvc
		.perform(post("/api/oiiemove/OffEmExecuteQuery", searchResult).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("offenderExternalMovementsExecuteQueryTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEmMovementReasonCoRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEmMovementReasonCoRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEmMovementReasonCoRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEmMovementTypeRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEmMovementTypeRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEmMovementTypeRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEm1DirectionCodeRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEm1DirectionCodeRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEm1DirectionCodeRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEm1MovementTypeRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEm1MovementTypeRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEm1MovementTypeRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEm1MovementReasonCRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEm1MovementReasonCRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEm1MovementReasonCRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgOffEmDirectionCodeRecordGroupTestMethod() {
	try {
		final ReferenceCodes obj = new ReferenceCodes();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiiemove/rgOffEmDirectionCodeRecordGroup", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("rgOffEmDirectionCodeRecordGroupTestMethod", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderExternalMovementsExecute1QueryTestMethod() {
	try {
		final OffenderExternalMovements obj = new OffenderExternalMovements();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
        ResultActions resultActions = this.mockMvc
		.perform(post("/api/oiiemove/offEm1ExecuteQuery", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("offenderExternalMovementsExecute1QueryTestMethod", e);
		}
		}
}