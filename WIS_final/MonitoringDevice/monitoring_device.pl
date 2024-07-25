%====================================================================================
% monitoring_device description   
%====================================================================================
dispatch( burn_start, burn_start(N) ).
dispatch( burn_end, burn_end(X) ).
dispatch( sonar_value, sonar_value(K) ).
dispatch( led_on, led_on(N) ).
dispatch( led_off, led_off(N) ).
dispatch( led_flashing, led_flashing(N) ).
event( sonardata, sonardata(K) ).
%====================================================================================
context(ctxmd, "localhost",  "TCP", "8080").
context(ctxwis, "192.168.1.50",  "TCP", "8080").
 qactor( wis, ctxwis, "external").
  qactor( monitoring_device, ctxmd, "it.unibo.monitoring_device.Monitoring_device").
 static(monitoring_device).
  qactor( led, ctxmd, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctxmd, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( sonar_device, ctxmd, "it.unibo.sonar_device.Sonar_device").
 static(sonar_device).
