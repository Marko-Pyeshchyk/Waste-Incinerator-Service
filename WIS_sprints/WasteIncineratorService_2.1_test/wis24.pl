%====================================================================================
% wis24 description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( rp_number, rp_number(N) ).
dispatch( kg, kg(N) ).
dispatch( remove_rp, remove_rp(N) ).
request( test_request, test_request(N) ).
reply( test_reply, test_reply(N) ).  %%for test_request
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
  qactor( test_observer, ctxwis, "it.unibo.test_observer.Test_observer").
 static(test_observer).
