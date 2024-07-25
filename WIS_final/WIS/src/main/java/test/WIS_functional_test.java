package main.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ColorsOut;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;

public class WIS_functional_test {
	private static Interaction connSupport;
	private static Interaction connSupport2;
	private static Process p;

	/*
	 * Utilty to show the output of a process activated with Runtime.getRuntime().exec
	 * See activateSystemUsingGradle
	 */
	public static void showOutput(Process proc, String color){
		new Thread(){
			public void run(){
				try {
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				ColorsOut.outappl("Here is the standard output of the command:\n", color);
				while (true){
					String s = stdInput.readLine();
					if ( s != null ) ColorsOut.outappl( s, color );
				} 
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	 
	/*
	 *  Method to activate the system
	 *  using gradle
	 */
	public static void activateSystemUsingGradle() { 
		Thread th = new Thread(){
			public void run(){
				try {
					CommUtils.outmagenta("TestWISCtx activateSystemUsingGradle ");
					Process p = Runtime.getRuntime().exec("./gradlew.bat run");
					showOutput(p,ColorsOut.BLACK);
				} catch ( Exception e) {
					CommUtils.outred("TestWISCtx activate ERROR " + e.getMessage());
				}
			}
		};
		th.start();
	}  

	/*
	 *  Method to activate the system
	 *  using the distribution
	 */
	public static void activateSystemUsingDeploy() { 
		Thread th = new Thread(){
			public void run(){
				try {
					CommUtils.outmagenta("TestWISCtx activateSystemUsingDeploy ");
					p = Runtime.getRuntime().exec("./build/distributions/wis24-1.0/bin/wis24.bat");
					showOutput(p,ColorsOut.BLACK);
				} catch ( Exception e) {
					CommUtils.outred("TestWISCtx activate ERROR " + e.getMessage());
				}
			}
		};
		th.start();
	}  

	/*
	 * Before all the tests
	 */
	@Before
	public void activate() {
		CommUtils.outmagenta("TestWISCtx activate ");
		activateSystemUsingGradle();
		//activateSystemUsingDeploy();
	}
	/*
	 * After each test	
	 */
	@After
	public void down() throws InterruptedException { 
         CommUtils.outcyan("end of test ");	
	}

	/*
	 * Connect with the observer
	 * Request info to the observer
	 * Assertion on the answer of the observer
	 */
		
	@Test
	public void testWISFunctional() {
		IApplMessage req  = CommUtils.buildRequest( "tester", "test_data", "test_data(50)", "test_observer");
 		try {
  			 CommUtils.outmagenta("testWISFunctional ======================================= ");
			while( connSupport == null ) {
 				connSupport = ConnectionFactory.createClientSupport(ProtocolType.tcp, "localhost", "8080");
 				CommUtils.outcyan("testWISFunctional another connect attempt ");
 				Thread.sleep(1000);
 			}
 			CommUtils.outcyan("CONNECTED to test_observer " + connSupport);
			IApplMessage reply = connSupport.request(req);
			String answer = reply.msgContent();
			assertEquals(answer, "test_data(ok)");
		} catch (Exception e) {
			CommUtils.outred("test_observer ERROR " + e.getMessage());
			fail("testRequest " + e.getMessage());
		}
	}
}

