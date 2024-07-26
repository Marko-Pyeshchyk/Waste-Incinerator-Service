/* Generated by AN DISI Unibo */ 
package it.unibo.sonar

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

class Sonar ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("START sonar")
						delay(1000) 
						subscribeToLocalActor("sonar_device") 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t024",targetState="handleSonar",cond=whenEvent("sonardata"))
				}	 
				state("handleSonar") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("sonardata(K)"), Term.createTerm("sonardata(K)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var D = payloadArg(0)  
								forward("sonar_value", "sonar_value($D)" ,"monitoring_device" ) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t025",targetState="handleSonar",cond=whenEvent("sonardata"))
				}	 
			}
		}
} 
