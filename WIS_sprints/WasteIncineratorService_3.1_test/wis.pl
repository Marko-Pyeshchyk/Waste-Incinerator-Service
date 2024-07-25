%====================================================================================
% wis description   
%====================================================================================
dispatch( sonar_value, sonar_value(K) ).
dispatch( led_on, led_on(N) ).
dispatch( led_off, led_off(N) ).
dispatch( led_flashing, led_flashing(N) ).
dispatch( burn_end, burn_end(X) ).
dispatch( burn_start, burn_start(X) ).
event( sonardata, sonardata(K) ).
%====================================================================================
context(ctx_md, "localhost",  "TCP", "8099").
 qactor( wis, ctx_md, "it.unibo.wis.Wis").
 static(wis).
  qactor( monitoring_device, ctx_md, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctx_md, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctx_md, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctx_md, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
