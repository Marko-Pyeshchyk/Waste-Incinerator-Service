%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( rp_number, rp_number(N) ).
dispatch( kg, kg(N) ).
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
context(ctxext, "127.0.0.1",  "TCP", "8081").
context(ctxext2, "127.0.0.1",  "TCP", "8082").
 qactor( waste_storage, ctxwis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
  qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( wis_mock, ctxwis, "it.unibo.wis_mock.Wis_mock").
 static(wis_mock).
  qactor( activator_mock, ctxext, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
  qactor( external_agent_ws, ctxext2, "it.unibo.external_agent_ws.External_agent_ws").
 static(external_agent_ws).
