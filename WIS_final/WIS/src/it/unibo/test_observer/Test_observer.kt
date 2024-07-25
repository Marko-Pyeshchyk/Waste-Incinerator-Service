/* Generated by AN DISI Unibo */ 
package it.unibo.test_observer

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Test_observer ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 
				var Level=9999
				var New=9999
				var Times=0
				var FLAG=-1
				var Number=0
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						CommUtils.outcyan("$name	START")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t046",targetState="send_rp",cond=whenRequest("test_data"))
				}	 
				state("send_rp") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("test_data(X)"), Term.createTerm("test_data(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 
												var KG=payloadArg(0).toDouble() 
												Number=kotlin.math.round(KG/50).toInt()
								forward("kg", "kg($KG)" ,"waste_storage" ) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t047",targetState="handle_sonar",cond=whenDispatch("sonar_value"))
				}	 
				state("handle_sonar") { //this:State
					action { //it:State
						CommUtils.outcyan("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						if( checkMsgContent( Term.createTerm("sonar_value(K)"), Term.createTerm("sonar_value(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var New=payloadArg(0).toInt()  
								CommUtils.outmagenta("NEW:	$New	LEVEL:	$Level")
								if(  Level-New<5 && Level-New>-5  
								 ){ 
													Level=New 
													Times++
								if(  Times==5  
								 ){ 
														FLAG++ 
								}
								}
								else
								 { 
								 					Times=0 
								 					Level=New
								 }
								CommUtils.outcyan("Flag: $FLAG		Times: $Times	NUMBER:	$Number")
								if(  FLAG==Number  
								 ){answer("test_data", "test_data", "test_data(ok)"   )  
								 System.exit(0)  
								}
								else
								 {if(  FLAG>Number  
								  ){answer("test_data", "test_data", "test_data(fail)"   )  
								  System.exit(0)  
								 }
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t048",targetState="handle_sonar",cond=whenDispatch("sonar_value"))
				}	 
			}
		}
} 