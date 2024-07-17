/* Generated by AN DISI Unibo */ 
package it.unibo.wis_mock

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

class Wis_mock ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outgreen("$name		START")
						subscribeToLocalActor("incinerator") 
						delay(1000) 
						forward("burn_end", "burn_end(X)" ,"monitoring_device" ) 
						delay(1000) 
						forward("burn_start", "burn_start(X)" ,"monitoring_device" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t15",targetState="ready",cond=whenDispatch("activation_command"))
				}	 
				state("ready") { //this:State
					action { //it:State
						CommUtils.outgreen("$name		READY to guide the robot")
						forward("burn_start", "burn_start(N)" ,"incinerator" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t16",targetState="retrieve_ash",cond=whenEvent("burn_end"))
				}	 
				state("retrieve_ash") { //this:State
					action { //it:State
						CommUtils.outgreen("$name		robot will retrieve ash")
						forward("ash_taken", "ash_taken(N)" ,"incinerator" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
