%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( weight, weight(N) ).
event( scale_data, scale_data(N) ).
dispatch( burn_end, burn_end(X) ).
dispatch( sonar_value, sonar_value(K) ).
dispatch( led_on, led_on(N) ).
dispatch( led_off, led_off(N) ).
dispatch( led_flashing, led_flashing(N) ).
event( sonardata, sonardata(K) ).
request( engage, engage(OWNER,STEPTIME) ).
reply( engagedone, engagedone(ARG) ).  %%for engage
reply( engagerefused, engagerefused(ARG) ).  %%for engage
dispatch( disengage, disengage(ARG) ).
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
dispatch( primo, primo(X) ).
dispatch( secondo, secondo(X) ).
dispatch( terzo, terzo(X) ).
dispatch( quarto, quarto(X) ).
dispatch( quinto, quinto(X) ).
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
context(ctxbrobot, "127.0.0.1",  "TCP", "8020").
 qactor( basicrobot, ctxbrobot, "external").
  qactor( op_robot, ctxwis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( scale_device, ctxwis, "it.unibo.scale_device.Scale_device").
 static(scale_device).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( wis_mock, ctxwis, "it.unibo.wis_mock.Wis_mock").
 static(wis_mock).
  qactor( activator_mock, ctxwis, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
  qactor( monitoring_device, ctxwis, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxwis, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxwis, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctxwis, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
