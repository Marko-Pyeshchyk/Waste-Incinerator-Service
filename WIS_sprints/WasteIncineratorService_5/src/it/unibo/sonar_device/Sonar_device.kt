/* Generated by AN DISI Unibo */ 
package it.unibo.sonar_device

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

class Sonar_device ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 
				lateinit var process : Process
				lateinit var out : java.io.BufferedReader
				var Distance = 0
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outyellow("$name	START")
						 
									process = Runtime.getRuntime().exec("python sonar.py")
									out = process.getInputStream().bufferedReader()
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="readOut", cond=doswitch() )
				}	 
				state("readOut") { //this:State
					action { //it:State
						 
									var Distance =  out.readLine()	
						emitLocalStreamEvent("sonardata", "sonardata($Distance)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="readOut", cond=doswitch() )
				}	 
			}
		}
} 
