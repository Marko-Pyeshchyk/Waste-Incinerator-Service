%====================================================================================
% ea description   
%====================================================================================
dispatch( kg, kg(X) ).
%====================================================================================
context(ctxea, "localhost",  "TCP", "8088").
context(ctxwis, "192.168.1.56",  "TCP", "8080").
 qactor( waste_storage, ctxwis, "external").
  qactor( external_agent, ctxea, "it.unibo.external_agent.External_agent").
 static(external_agent).
