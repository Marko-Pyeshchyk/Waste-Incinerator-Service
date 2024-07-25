package ServiceStatusGUI;

import unibo.basicomm23.coap.CoapConnection;
import unibo.basicomm23.utils.CommUtils;

public class ServiceStatusGUIBuilder {
    public static  WSHandler wsHandler;
    public static  ServiceStatusGUICore guiCore  ;
    protected   ActorOut outadapter;

    public ServiceStatusGUIBuilder( ){
        create();
    }

    public void create(){
        //C
        wsHandler    = new WSHandler();
        outadapter = new ActorOut( wsHandler );
        guiCore      = new ServiceStatusGUICore(outadapter);
        outadapter.setGuiCore(guiCore); //Injection
        wsHandler.setGuiCore(guiCore); //Injection

        //CommUtils.outred("FacadeBuilder create wsHandler=" + wsHandler);
//        List<String> config = QaksysConfigSupport.readConfig("facadeConfig.json");
//        if( config != null ) {
            String qakSysHost    = ApplSystemInfo.qakSysHost;
            String ctxportStr    = ApplSystemInfo.ctxportStr;
            String qakSysCtx     = ApplSystemInfo.qakSysCtx;
            String applActorName = ApplSystemInfo.applActorName;

            CoapObserver obs = new CoapObserver(guiCore, applActorName);
            String saddr   = qakSysHost + ":" + ctxportStr;
            String resource = qakSysCtx + "/" + applActorName;
            CommUtils.outblue("FacadeBuilder | coapConn : " + saddr + " " + resource);
            CoapConnection coapConn = new CoapConnection(qakSysHost + ":" + ctxportStr,
                    qakSysCtx + "/" + applActorName);
            CommUtils.outblue("FacadeBuilder | Stabilita coapConn : " + coapConn);
  
            coapConn.observeResource(obs);
        //}
    }
}
