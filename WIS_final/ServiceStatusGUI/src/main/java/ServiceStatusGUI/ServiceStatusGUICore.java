package ServiceStatusGUI;


import unibo.basicomm23.utils.CommUtils;

public class ServiceStatusGUICore {
	private   ActorOut outadapter;
	
	public ServiceStatusGUICore(ActorOut outadapter) {
		this.outadapter=outadapter;
		ApplSystemInfo.setup();
	}
	
	//Chiamato da CoapObserver
    public void handleMsgFromActor(String msg, String requestId) {
        CommUtils.outcyan("AGC | hanldeMsgFromActor " + msg + " requestId=" + requestId) ;
        updateMsg( "AGC "+msg );
    }

	public void updateMsg( String msg ) {
        CommUtils.outblue("AGC updateMsg " + msg);
        outadapter.sendToAll(msg);
        //potrei mandare a M2M ... che poi manda la risposta a REST POST
        //M2MController.m2mCtrl.setAnswer(msg);
    }
}
