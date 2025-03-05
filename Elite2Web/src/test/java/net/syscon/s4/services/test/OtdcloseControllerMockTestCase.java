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
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OtdcloseControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OtdcloseControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderSubAccountsExecuteQueryTestMethod() {
		try {
			final OffenderSubAccounts bean = new OffenderSubAccounts();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/otdclose/oOffSubaExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderSubAccountsCommitTestMethod() {
		try {
			final OffenderSubAccountsCommitBean bean = new OffenderSubAccountsCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/otdclose/offSubaCommit", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderSubAccountsRecordGroupTestMethod() {
		try {
			final ReferenceCodes bean = new ReferenceCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/otdclose/cgfkOffTxnPayeeCodeRecordGroup", bean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderTransactionsExecuteQueryTestMethod() {
		try {
			final OffenderTransactions bean = new OffenderTransactions();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/otdclose/offTxnExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderTransactionsCommitTestMethod() {
		try {
			final OffenderTransactionsCommitBean bean = new OffenderTransactionsCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/otdclose/offTxnCommit", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
			final SystemProfiles bean = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/otdcloseSysPflExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}