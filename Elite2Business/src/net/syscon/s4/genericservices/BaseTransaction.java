package net.syscon.s4.genericservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


public class BaseTransaction{

	/**
	 * Logger object used to print the log in the file
	 */
private static Logger log = LogManager.getLogger(BaseTransaction.class.getName());
	private DefaultTransactionDefinition objDef;

	private PlatformTransactionManager objPlatformTransactionManager;

	/**
	 * Constructor
	 */
	public BaseTransaction() {

		objDef = new DefaultTransactionDefinition();
	}

	/**
	 * begin the transaction process
	 * @return TransactionStatus
	 */
	public TransactionStatus beginTransaction() {

		objPlatformTransactionManager = getTransactionMgr();

		objDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		return objPlatformTransactionManager.getTransaction(objDef);

	}

	/**
	 * Start a new transaction, by suspending the current transaction
	 * @return
	 */
	public TransactionStatus beginNewTransaction() {
		objPlatformTransactionManager = getTransactionMgr();
		objDef.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return objPlatformTransactionManager.getTransaction(objDef);
	}

	/**
	 * commit the transaction after completion
	 * @param objStatus TransactionStatus
	 */
	public void commitTransaction(final TransactionStatus objStatus) {
		if (objStatus.isRollbackOnly()) {
			log.warn("The status is rollback only");
		}
		objPlatformTransactionManager.commit(objStatus);
	}

	/**
	 * loads the transaction bean
	 * @return PlatformTransactionManager
	 */
	public PlatformTransactionManager getTransactionMgr() {

		//return SpringContextLoader.getInstance().getObjTransactionMgr();

		PlatformTransactionManager platformTransactionManager = null;
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("businessBeans.xml");
			platformTransactionManager=  (PlatformTransactionManager) context.getBean("txManager");
		}catch(Exception e){
			log.error(e);
		}
		return platformTransactionManager;
	}

	/**
	 * rollback transaction if any condition fails
	 * @param objStatus TransactionStatus
	 */
	public void rollbackTransaction(final TransactionStatus objStatus) {

		objPlatformTransactionManager.rollback(objStatus);

	}

	/**
	 * @return PlatformTransactionManager
	 */
	public PlatformTransactionManager getObjcPlatformTransactionManager() {
		return objPlatformTransactionManager;
	}

	/**
	 * @param objcPlatformTransactionManager
	 */
	public void setObjcPlatformTransactionManager(final PlatformTransactionManager objcPlatformTransactionManager) {
		this.objPlatformTransactionManager = objcPlatformTransactionManager;
	}

}