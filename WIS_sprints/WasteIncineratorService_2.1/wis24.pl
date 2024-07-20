%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( weight, weight(N) ).
event( scale_data, scale_data(N) ).
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
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
