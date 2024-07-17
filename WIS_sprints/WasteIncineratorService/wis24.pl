%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( robot_move, robot_move(X) ).
dispatch( weight, weight(X) ).
dispatch( activation_command, activation_command(N) ).
dispatch( sonar_value, sonar_value(K) ).
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
context(ctxmd, "localhost",  "TCP", "8040").
context(ctxext, "localhost",  "TCP", "8060").
 qactor( basicrobot, ctxbasicrobot, "external").
  qactor( wis, ctxwis, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( op_robot, ctxwis, "it.unibo.op_robot.Op_robot").
 static(op_robot).
  qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( monitoring_device, ctxmd, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxmd, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxmd, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( activator_mock, ctxext, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
