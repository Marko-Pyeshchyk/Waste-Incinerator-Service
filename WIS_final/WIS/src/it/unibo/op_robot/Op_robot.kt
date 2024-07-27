/* Generated by AN DISI Unibo */ 
package it.unibo.op_robot

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

class Op_robot ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 
				var X = 0
				var Y = 0
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outcyan("$name	START")
						delay(500) 
						subscribeToLocalActor("incinerator") 
						request("engage", "engage($MyName,330)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="ready",cond=whenReply("engagedone"))
					transition(edgeName="t01",targetState="end",cond=whenReply("engagerefused"))
				}	 
				state("end") { //this:State
					action { //it:State
						CommUtils.outcyan("$name ENDS ")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("ready") { //this:State
					action { //it:State
						CommUtils.outcyan("$name	READY")
						forward("work", "work(waiting_home)" ,"wis" ) 
						forward("cmd", "cmd(a)" ,"basicrobot" ) 
						forward("cmd", "cmd(d)" ,"basicrobot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t12",targetState="menage_RP",cond=whenDispatch("take_RP"))
					transition(edgeName="t13",targetState="going_HOME",cond=whenDispatch("go_home"))
					transition(edgeName="t14",targetState="menage_ash",cond=whenDispatch("take_ash"))
					interrupthandle(edgeName="t15",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("handle_map") { //this:State
					action { //it:State
						request("getenvmap", "getenvmap(0)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t06",targetState="handle_envmap",cond=whenReply("envmap"))
				}	 
				state("handle_envmap") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("envmap(MAP)"), Term.createTerm("envmap(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var MAP = "'"+payloadArg(0)+"'"  
								forward("map", "map($MAP)" ,"wis" ) 
						}
						returnFromInterrupt(interruptedStateTransitions)
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("menage_RP") { //this:State
					action { //it:State
						
									X = 0
									Y = 4
						forward("work", "work(moving_to_WASTEIN)" ,"wis" ) 
						request("moverobot", "moverobot(0,4)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t07",targetState="menage_RP2",cond=whenReply("moverobotdone"))
					transition(edgeName="t08",targetState="handle_obstacle",cond=whenReply("moverobotfailed"))
					interrupthandle(edgeName="t09",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("menage_RP2") { //this:State
					action { //it:State
						
									X = 3
									Y = 1
						forward("work", "work(taking_RP)" ,"wis" ) 
						delay(3000) 
						forward("work", "work(moving_to_BURNIN)" ,"wis" ) 
						request("moverobot", "moverobot(3,2)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t010",targetState="menage_RP3",cond=whenReply("moverobotdone"))
					transition(edgeName="t011",targetState="handle_obstacle",cond=whenReply("moverobotfailed"))
					interrupthandle(edgeName="t012",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("menage_RP3") { //this:State
					action { //it:State
						forward("work", "work(putting_RP)" ,"wis" ) 
						delay(3000) 
						forward("work", "work(waste_in_incinerator)" ,"wis" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="going_HOME", cond=doswitch() )
				}	 
				state("menage_ash") { //this:State
					action { //it:State
						
									X = 5
									Y = 3
						forward("work", "work(moving_to_BURNOUT)" ,"wis" ) 
						request("moverobot", "moverobot(4,3)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t013",targetState="menage_ash2",cond=whenReply("moverobotdone"))
					transition(edgeName="t014",targetState="handle_obstacle",cond=whenReply("moverobotfailed"))
					interrupthandle(edgeName="t015",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("menage_ash2") { //this:State
					action { //it:State
						
									X = 6
									Y = 4
						forward("work", "work(taking_ash)" ,"wis" ) 
						delay(3000) 
						forward("work", "work(ash_taken)" ,"wis" ) 
						forward("work", "work(moving_to_ASHOUT)" ,"wis" ) 
						request("moverobot", "moverobot(6,4)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t016",targetState="menage_ash3",cond=whenReply("moverobotdone"))
					transition(edgeName="t017",targetState="handle_obstacle",cond=whenReply("moverobotfailed"))
					interrupthandle(edgeName="t018",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("menage_ash3") { //this:State
					action { //it:State
						forward("work", "work(dumping_ash)" ,"wis" ) 
						delay(3000) 
						forward("work", "work(job_done)" ,"wis" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="job_done", cond=doswitch() )
				}	 
				state("job_done") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t119",targetState="menage_RP",cond=whenDispatch("take_RP"))
					transition(edgeName="t120",targetState="going_HOME",cond=whenDispatch("go_home"))
					interrupthandle(edgeName="t121",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("going_HOME") { //this:State
					action { //it:State
						
									X = 0
									Y = 0
						forward("work", "work(going_home)" ,"wis" ) 
						request("moverobot", "moverobot(0,0)" ,"basicrobot" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t022",targetState="ready",cond=whenReply("moverobotdone"))
					transition(edgeName="t023",targetState="handle_obstacle",cond=whenReply("moverobotfailed"))
					interrupthandle(edgeName="t024",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
				state("handle_obstacle") { //this:State
					action { //it:State
						CommUtils.outcyan("$name	OBSTACLE hit")
						delay(3000) 
						if(  X==0 && Y==4  
						 ){forward("primo", "primo(a)" ,name ) 
						}
						else
						 {if(  X==3 && Y==1  
						  ){forward("secondo", "secondo(a)" ,name ) 
						 }
						 else
						  {if(  X==5 && Y==3  
						   ){forward("terzo", "terzo(a)" ,name ) 
						  }
						  else
						   {if(  X==6 && Y==4  
						    ){forward("quarto", "quarto(a)" ,name ) 
						   }
						   else
						    {if(  X==0 && Y==0  
						     ){forward("quinto", "quinto(a)" ,name ) 
						    }
						    }
						   }
						  }
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t025",targetState="menage_RP",cond=whenDispatch("primo"))
					transition(edgeName="t026",targetState="menage_RP2",cond=whenDispatch("secondo"))
					transition(edgeName="t027",targetState="menage_ash",cond=whenDispatch("terzo"))
					transition(edgeName="t028",targetState="menage_ash2",cond=whenDispatch("quarto"))
					transition(edgeName="t029",targetState="going_HOME",cond=whenDispatch("quinto"))
					interrupthandle(edgeName="t030",targetState="handle_map",cond=whenDispatch("map"),interruptedStateTransitions)
				}	 
			}
		}
} 
