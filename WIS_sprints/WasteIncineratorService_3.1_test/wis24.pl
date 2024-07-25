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
dispatch( led_on, led_on(N) ).
dispatch( led_off, led_off(N) ).
dispatch( led_flashing, led_flashing(N) ).
event( sonardata, sonardata(K) ).
dispatch( state, state(X) ).
dispatch( level, level(X) ).
dispatch( burn, burn(X) ).
dispatch( test, test(X) ).
request( test_data, test_data(X) ).
reply( test_data, test_data(X) ).  %%for test_data
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
 qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( wis_mock, ctxwis, "it.unibo.wis_mock.Wis_mock").
 static(wis_mock).
  qactor( activator_mock, ctxwis, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
  qactor( external_agent_ws, ctxwis, "it.unibo.external_agent_ws.External_agent_ws").
 static(external_agent_ws).
  qactor( monitoring_device, ctxwis, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxwis, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxwis, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctxwis, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
  qactor( test_observer, ctxwis, "it.unibo.test_observer.Test_observer").
 static(test_observer).
