package ServiceStatusGUI;

 
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.tcp.TcpClientSupport;
import unibo.basicomm23.utils.CommUtils;
 

/*
Adapter verso il QActor che fa da facade
Usa il file facadeConfig.json
 */
public class ActorOut {
    private ServiceStatusGUICore guiCore;
    private WSHandler wsHandler;
    private  Interaction tcpConn;


    public ActorOut( WSHandler wsHandler ) {
        try {
            this.wsHandler        = wsHandler;
            String qakSysHost     = ApplSystemInfo.qakSysHost;
            String facadeportStr  = ApplSystemInfo.facadeportStr;
            int    ctxport        = ApplSystemInfo.ctxport;

             tcpConn = TcpClientSupport.connect(qakSysHost, ctxport, 20);
             CommUtils.outblue("OUTIN | Stabilita tcpConn: " + tcpConn + " con " + facadeportStr);
        } catch (Exception e) {
            tcpConn = null;
            CommUtils.outred("OUTIN | creation WARNING: " + e.getMessage());
        }
     }

     //Injection
     public void setGuiCore(ServiceStatusGUICore myguiCore){
          guiCore = myguiCore;
     }

    /*public void sendToOne(IApplMessage msg){
        wsHandler.sendToOne( msg  );
    }
    public void sendToOne(String msg){ wsHandler.sendToOne( msg  ); }*/

    public void sendToAll(String msg){ 
    	CommUtils.outmagenta("OUTIN | sendToAll  " + msg + " " + wsHandler);
    	wsHandler.sendToAll( msg  ); 
    }
}