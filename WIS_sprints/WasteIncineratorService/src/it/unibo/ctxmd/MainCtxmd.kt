/* Generated by AN DISI Unibo */ 
package it.unibo.ctxmd
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "localhost", this, "wis24.pl", "sysRules.pl", "ctxmd"
	)
	//JAN Facade
	//JAN24 Display
}
