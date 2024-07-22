/* Generated by AN DISI Unibo */ 
package it.unibo.led

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

class Led ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outmagenta("START led")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t021",targetState="handleOn",cond=whenDispatch("led_on"))
					transition(edgeName="t022",targetState="handleOff",cond=whenDispatch("led_off"))
					transition(edgeName="t023",targetState="handleFlashing",cond=whenDispatch("led_flashing"))
				}	 
				state("handleOn") { //this:State
					action { //it:State
						CommUtils.outmagenta("LED ON")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t024",targetState="handleOn",cond=whenDispatch("led_on"))
					transition(edgeName="t025",targetState="handleOff",cond=whenDispatch("led_off"))
					transition(edgeName="t026",targetState="handleFlashing",cond=whenDispatch("led_flashing"))
				}	 
				state("handleOff") { //this:State
					action { //it:State
						CommUtils.outmagenta("LED OFF")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t027",targetState="handleOn",cond=whenDispatch("led_on"))
					transition(edgeName="t028",targetState="handleOff",cond=whenDispatch("led_off"))
					transition(edgeName="t029",targetState="handleFlashing",cond=whenDispatch("led_flashing"))
				}	 
				state("handleFlashing") { //this:State
					action { //it:State
						CommUtils.outmagenta("LED FLASHING")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t030",targetState="handleOn",cond=whenDispatch("led_on"))
					transition(edgeName="t031",targetState="handleOff",cond=whenDispatch("led_off"))
					transition(edgeName="t032",targetState="handleFlashing",cond=whenDispatch("led_flashing"))
				}	 
			}
		}
} 
