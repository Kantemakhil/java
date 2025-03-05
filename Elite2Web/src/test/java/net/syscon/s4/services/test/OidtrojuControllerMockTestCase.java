package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidtrojuControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtrojuControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderExternalMovementsExecuteQueryTestMethod() {
		try {
			final OffenderExternalMovements object = new OffenderExternalMovements();
			object.setOffenderBookId(Long.valueOf(20444));
			object.setMovementSeq(Long.valueOf(3));
			final String jsonInString = new ObjectMapper().writeValueAsString(object);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtroju/offEmExecuteQuery", object).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].commentText", is("Hello")));
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
	public void offenderExternalMovementsCommitTestMethod() {
		try {
			final OffenderExternalMovementsCommitBean object = new OffenderExternalMovementsCommitBean();
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(22389));
			offenderExternalMovements.setMovementSeq(Long.valueOf(94));
			offenderExternalMovements.setMovementDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setMovementTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setInternalScheduleType("APP");
			offenderExternalMovements.setInternalScheduleReasonCode("IA");
			offenderExternalMovements.setMovementType("ADM");
			offenderExternalMovements.setMovementReasonCode("NEW");
			offenderExternalMovements.setDirectionCode("IN");
			offenderExternalMovements.setArrestAgencyLocId("");
			offenderExternalMovements.setToProvStatCode("");
			offenderExternalMovements.setEscortCode("");
			offenderExternalMovements.setFromAgyLocId("CCC");
			offenderExternalMovements.setToAgyLocId("CCC");
			offenderExternalMovements.setActiveFlag("N");
			offenderExternalMovements.setEscortText("");
			offenderExternalMovements.setCommentText("");
			offenderExternalMovements.setReportingDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToCity("");
			offenderExternalMovements.setFromCity("");
			offenderExternalMovements.setReportingTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setEventId(BigDecimal.valueOf(313132));
			offenderExternalMovements.setParentEventId(BigDecimal.valueOf(12456));
			offenderExternalMovements.setToCountryCode("");
			offenderExternalMovements.setOjLocationCode("");
			offenderExternalMovements.setApplicationDate(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setApplicationTime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setToAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setFromAddressId(BigDecimal.valueOf(13320));
			offenderExternalMovements.setSealFlag("");
			offenderExternalMovements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setCreateUserId("OMS_OWNER");
			offenderExternalMovements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			offenderExternalMovements.setModifyUserId("ADMINQA");
			final List<OffenderExternalMovements> insertList = new ArrayList<OffenderExternalMovements>();
			insertList.add(offenderExternalMovements);

			object.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(object);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtroju/offEmCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("offEmCommit", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderExternalMovementsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidtroju/cgfkOffEmToProvStatCodeRecordGroup")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
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
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidtroju/sysPflExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}