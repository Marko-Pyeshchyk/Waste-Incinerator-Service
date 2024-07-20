%====================================================================================
% wis24_test_incinerator description   
%====================================================================================
event( burn_end, burn_end(N) ).
dispatch( activation_command, activation_command(N) ).
dispatch( burn_start, burn_start(N) ).
dispatch( ash_taken, ash_taken(N) ).
dispatch( event_sent, event_sent(X) ).
request( test_data, test_data(X) ).
reply( send_data, send_data(X) ).  %%for test_data
%====================================================================================
context(ctxwis, "localhost",  "TCP", "8080").
 qactor( incinerator, ctxwis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( wis_mock, ctxwis, "it.unibo.wis_mock.Wis_mock").
 static(wis_mock).
  qactor( activator_mock, ctxwis, "it.unibo.activator_mock.Activator_mock").
 static(activator_mock).
  qactor( test_observer, ctxwis, "it.unibo.test_observer.Test_observer").
 static(test_observer).
