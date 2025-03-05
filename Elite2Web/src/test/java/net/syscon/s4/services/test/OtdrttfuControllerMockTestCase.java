package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
import net.syscon.s4.inmate.beans.OffenderTrustTransfersCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OtdrttfuControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OtdrttfuControllerMockTestCase extends AbstractMockTestCase{
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderTrustTransfersExecuteQueryTestMethod() {
		try {
			 final OffenderTrustTransfers offenderTrust = new OffenderTrustTransfers();
			 final ObjectMapper mapper = new ObjectMapper();
			 final String jsonInString = mapper.writeValueAsString(offenderTrust);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/otdrttfu/offTtExecuteQuery", offenderTrust)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(" In method offenderTrustTransfersExecuteQueryTestMethod ", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderTrustTransfersCommitTestMethod() {
		try {
			 final OffenderTrustTransfersCommitBean offTrustTran = new OffenderTrustTransfersCommitBean();
			 final ObjectMapper mapper = new ObjectMapper();
			 final String jsonInString = mapper.writeValueAsString(offTrustTran);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/otdrttfu/offTtCommit", offTrustTran)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(" In method offenderTrustTransfersCommitTestMethod ", e);
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
			final OffenderTransactions offTrans = new OffenderTransactions();
			final ObjectMapper mapper = new ObjectMapper();
			 final String jsonInString = mapper.writeValueAsString(offTrans);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/otdrttfu/offTxnExecuteQuery", offTrans)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(" In method offenderTransactionsExecuteQueryTestMethod ", e);
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
			final OffenderTransactionsCommitBean offTrans = new OffenderTransactionsCommitBean();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offTrans);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/otdrttfu/offTxnCommit", offTrans)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(" In method offenderTransactionsCommitTestMethod ", e);
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
			final SystemProfiles sysProf = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(sysProf);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/otdrttfu/sysPflExecuteQuery", sysProf)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(" In method systemProfilesExecuteQueryTestMethod ", e);
		}
	}

}