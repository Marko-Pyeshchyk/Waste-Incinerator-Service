/* Generated by AN DISI Unibo */ 
package it.unibo.waste_storage

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

class Waste_storage ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outyellow("$name	START")
						delay(1000) 
						subscribeToLocalActor("scale_device") 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t10",targetState="handle_data",cond=whenEvent("scale_data"))
				}	 
				state("handle_data") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("scale_data(N)"), Term.createTerm("scale_data(W)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var W = payloadArg(0)  
								CommUtils.outyellow("$name	sending weight: $W")
								forward("weight", "weight($W)" ,"wis_mock" ) 
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t11",targetState="handle_data",cond=whenEvent("scale_data"))
				}	 
			}
		}
} 
