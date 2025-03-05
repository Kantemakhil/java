package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 *  class OtdholdtControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OtdholdtControllerMockTestCase extends AbstractMockTestCase {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderTransactionsExecuteQueryTestMethod() {
	try {
		final Offenders offenders = new Offenders();
		offenders.setCaseloadType("ITAG");
		offenders.setRootOffenderId(new BigDecimal(1019265));
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(offenders);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/otdholdt/offTxnExecuteQuery", offenders).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("In method offenderTransactionsExecuteQueryTestMethod : ", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderTransactionsCommitTestMethod() {
	try {
		final OffenderTransactions offTranCommitBean = new OffenderTransactions();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(offTranCommitBean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/otdholdt/offTxnCommit", offTranCommitBean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("In method offenderTransactionsCommitTestMethod : ", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderTransactionsRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/otdholdt/cgfkOffTxnSubAccountTypeRecordGroup?caseLoadId=ITAG").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("In method offenderTransactionsRecordGroupTestMethod: ", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void systemProfilesExecuteQueryTestMethod() {
	try {
		final SystemProfiles searchRecord= new SystemProfiles();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(searchRecord);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/otdholdt/sysPflExecuteQuery", searchRecord).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("In method systemProfilesExecuteQueryTestMethod : ", e);
		}
		}
}