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

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.OffenderOffenderIdentifierUtility;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdaliasControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcdaliasControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Test method to fetch the offenders
	 */
	@Test
	public void offendersExecuteQuery() {
		try {
			final Offenders offender = new Offenders();
			offender.setOffenderId(Long.valueOf(1019061));
			offender.setRootOffenderId(BigDecimal.valueOf(1019082));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offender);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offNameSearchOffenders", offender)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019082)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Test method to fetch the workingNameOffenderID
	 */
	@Test
	public void getWorkingNameOffenderIDTestMethod() {
		final Offenders offender = new Offenders();
		offender.setRootOffenderId(BigDecimal.valueOf(1019182));
		try {

			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/getWorkingNameOffenderID", offender)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(offender))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.agyLocId").value("CTAG"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the selected offender offenderIdentifiers
	 */
	@Test
	public void offidExecuteQueryTestMethod() {
		final Offenders bean = new Offenders();
		bean.setOffenderId(Long.valueOf(1019913));
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offIdSearchOffenderIdentifiers", bean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(bean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());

			resultActions.andExpect(jsonPath("$[0].identifier", is("0001019912")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the all offenderIdentifiers
	 */
	@Test
	public void offIdAllSearchOffenderIdentifiersTestMethod() {
		final Offenders bean = new Offenders();
		bean.setOffenderId(Long.valueOf(1019061));
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offIdAllSearchOffenderIdentifiers", bean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(bean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].rootOffenderId").value("1019061"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the all offenderbookings
	 */
	@Test
	public void offenderBookingsExecuteQueryTestMethod() {
		final OffenderBookings bean = new OffenderBookings();
		bean.setOffenderBookId(Long.valueOf(18773));
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/reqNameSearchOffenderBookings", bean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(bean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019061)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch records
	 */
	@Test
	public void offbkgoncheckdeletemasteroffnamecurTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalias/offBkgOnCheckDeleteMasteroffNameCur?rootOffenderId=1019182")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the records
	 */
	@Test
	public void offnameoncheckdeletemasteroffidcurTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalias/offNameOnCheckDeleteMasteroffIdCur?offenderId=1019182")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the offIdpreinsert
	 */
	@Test
	public void offidpreinsertTestMethod() {
		try {
			final OffenderIdentifier offenderIden = new OffenderIdentifier();
			offenderIden.setOffenderId(Long.valueOf(1019181));
			this.mockMvc
					.perform(post("/api/ocdalias/offIdPreInsert?param=1019182").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$", is(3)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Test method to fetch the cgrichkoffendersc
	 */
	@Test
	public void cgrichkOffenderscTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalias/cgrichkOffenderSc?offenderId=1019182")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * Test method to insert the offenders
	 */
	@Test
	public void offnameCommit() {
		final OffendersCommitBean commitBean = new OffendersCommitBean();
		final List<Offenders> insertList = new ArrayList<>();
		final List<Offenders> updateList = new ArrayList<>();
		final Offenders off1 = new Offenders();
		off1.setOffenderId(Long.valueOf(0));
		off1.setNameSequence("1");
		off1.setIdSourceCode("NEQ");
		off1.setLastName("DENY");
		off1.setFirstName("DANIAL");
		off1.setSexCode("M");
		off1.setCreateDate(new Date());
		final Offenders off2 = new Offenders();
		off2.setOffenderId(Long.valueOf(0));
		off2.setNameSequence("1");
		off2.setIdSourceCode("SEQ");
		off2.setLastName("DAN");
		off2.setFirstName("LONDY");
		off2.setSexCode("M");
		off2.setCreateDate(new Date());
		final Offenders off3 = new Offenders();
		off3.setOffenderId(Long.valueOf(1298348000));
		off3.setIdSourceCode("Test");
		off3.setLastName("Jhon1");
		off3.setSexCode("M");
		off3.setNameType("WORKING");
		final Offenders off4 = new Offenders();
		off4.setOffenderId(Long.valueOf(1298348001));
		off4.setIdSourceCode("ss");
		off4.setLastName("MAXI");
		off4.setSexCode("M");
		off3.setNameType("WORKING");
		insertList.add(off1);
		insertList.add(off2);
		updateList.add(off3);
		updateList.add(off4);
		commitBean.setInsertList(insertList);
		commitBean.setUpdateList(updateList);
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offNameCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(commitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to update the offenderIdentifiers
	 */
	@Test
	public void offidSaveUpdate() {
		final OffenderIdentifiersCommitBean commitBean = new OffenderIdentifiersCommitBean();
		final List<OffenderIdentifier> insertList = new ArrayList<>();
		final List<OffenderIdentifier> updateList = new ArrayList<>();
		final OffenderIdentifier oddId1 = new OffenderIdentifier();
		oddId1.setIdentifierType("#DOC");
		oddId1.setIdentifier("2222565");
		oddId1.setOffenderIdSeq("1");
		oddId1.setRootOffenderId(BigDecimal.valueOf(1019182));
		oddId1.setOffenderId(1019182);
		final OffenderIdentifier oddId2 = new OffenderIdentifier();
		oddId2.setIdentifierType("DL");
		oddId2.setIdentifier("2222500");
		oddId2.setRootOffenderId(BigDecimal.valueOf(1019182));
		oddId2.setOffenderId(1019182);
		insertList.add(oddId2);
		updateList.add(oddId1);
		commitBean.setInsertList(insertList);
		commitBean.setUpdateList(updateList);
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offIdCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(commitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to change working name
	 */
	@Test
	public void changeWorkingNameTestMethod() {
		try {
			final List<String> updatelist = new ArrayList<>();
			updatelist.add("1019090");
			updatelist.add("1019090");
			updatelist.add("18815");
			this.mockMvc
					.perform(post("/api/ocdalias/changeWorkingName", updatelist).contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(updatelist))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$.firstName", is("TESTQM2")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to fetch the records
	 */
	@Test
	public void checkPncExistsgetPncExTestMethod() {
		try {
			this.mockMvc
					.perform(post("/api/ocdalias/checkPncExistsgetPncEx?rootOffenderId=1019182")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to delete the offenders, offenderIdentifiers
	 */
	@Test
	public void offOffidDeleteTest() {
		final OffenderOffenderIdentifierUtility utilityBean = new OffenderOffenderIdentifierUtility();
		final List<OffenderIdentifier> delidenLit = new ArrayList<>();
		final List<Offenders> delList = new ArrayList<>();
		final OffenderIdentifier oddId1 = new OffenderIdentifier();
		oddId1.setOffenderId(1298347734);
		oddId1.setOffenderIdSeq("1");
		final OffenderIdentifier oddId2 = new OffenderIdentifier();
		oddId2.setOffenderId(1298347734);
		oddId1.setOffenderIdSeq("1");
		delidenLit.add(oddId1);
		delidenLit.add(oddId2);
		final Offenders off1 = new Offenders();
		off1.setOffenderId(Long.valueOf(1298347790));
		final Offenders off2 = new Offenders();
		off2.setOffenderId(Long.valueOf(1298348007));
		delList.add(off1);
		delList.add(off2);
		utilityBean.setOffenderIdentifierList(delidenLit);
		utilityBean.setOffenderList(delList);
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/offOffIdDelte", utilityBean).contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(utilityBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to update the offenderBooking
	 */
	@Test
	public void offbkgCommitTestMethod() {
		final OffenderBookingsCommitBean utilityBean = new OffenderBookingsCommitBean();
		final List<OffenderBookings> offBookList = new ArrayList<>();
		final OffenderBookings offBook1 = new OffenderBookings();
		offBook1.setOffenderBookId(Long.valueOf(18815));
		offBook1.setLivingUnitId(BigDecimal.valueOf(0));
		final OffenderBookings offBook2 = new OffenderBookings();
		offBook2.setOffenderBookId(Long.valueOf(18816));
		offBook2.setLivingUnitId(BigDecimal.valueOf(0));
		offBookList.add(offBook1);
		offBookList.add(offBook2);
		utilityBean.setUpdateList(offBookList);
		try {
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdalias/reqNameCommit", utilityBean).contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(utilityBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Test method to count the Ids
	 */
	@Test
	public void countOfOffenderIDTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/ocdalias/countOfOffenderID?pRootOffenderId=1024764")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$").value(2));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
