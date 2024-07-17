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
 qactor( md, ctxwis, "it.unibo.md.Md").
 static(md).
  qactor( led, ctxwis, "it.unibo.led.Led").
 static(led).
