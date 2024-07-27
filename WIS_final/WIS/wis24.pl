%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( rp_number, rp_number(N) ).
dispatch( kg, kg(N) ).
dispatch( burn_end, burn_end(X) ).
dispatch( sonar_value, sonar_value(K) ).
dispatch( sonar_value, sonar_value(X,Y) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
request( getenvmap, getenvmap(X) ).
reply( envmap, envmap(MAP) ).  %%for getenvmap
dispatch( cmd, cmd(MOVE) ). %MOVE = a|d|l|r|h   
request( cmd, cmd(MOVE,T) ). %MOVE = w|s|p (stepSynch)
reply( cmddone, cmddone(R) ).  %%for cmd
reply( cmdfailed, cmdfailed(T,CAUSE) ).  %%for cmd
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
request( moverobot, moverobot(TARGETX,TARGETY) ).
reply( moverobotdone, moverobotok(ARG) ).  %%for moverobot
reply( moverobotfailed, moverobotfailed(PLANDONE,PLANTODO) ).  %%for moverobot
dispatch( take_RP, take_RP(X) ).
dispatch( take_ash, take_RP(X) ).
dispatch( go_home, take_RP(X) ).
dispatch( work, work(X) ).
dispatch( map, map(X) ).
dispatch( primo, primo(X) ).
dispatch( secondo, secondo(X) ).
dispatch( terzo, terzo(X) ).
dispatch( quarto, quarto(X) ).
dispatch( quinto, quinto(X) ).
request( test_data, test_data(X) ).
reply( test_data, test_data(X) ).  %%for test_data
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
context(ctxbrobot, "192.168.1.59",  "TCP", "8020").
context(ctxmd, "10.0.0.10",  "TCP", "8091").
 qactor( basicrobot, ctxbrobot, "external").
  qactor( monitoring_device, ctxmd, "external").
  qactor( op_robot, ctxwis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( wis, ctxwis, "it.unibo.wis.Wis").
 static(wis).
  qactor( activator_mock, ctxwis, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
  qactor( test_observer, ctxwis, "it.unibo.test_observer.Test_observer").
 static(test_observer).
