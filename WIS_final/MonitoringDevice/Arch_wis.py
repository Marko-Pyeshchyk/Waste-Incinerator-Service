### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('wisArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxwis', graph_attr=nodeattr):
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          activator_mock=Custom('activator_mock','./qakicons/symActorSmall.png')
          external_agent_ws=Custom('external_agent_ws','./qakicons/symActorSmall.png')
     with Cluster('ctxbrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
          monitoring_device=Custom('monitoring_device(ext)','./qakicons/externalQActor.png')
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> op_robot
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> wis
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; getenvmap<font color="darkgreen"> envmap</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<work &nbsp; map &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; ash_taken &nbsp; >',  fontcolor='blue') >> incinerator
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<cmd<font color="darkgreen"> cmddone cmdfailed</font> &nbsp; >',  fontcolor='blue') >> basicrobot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<take_RP &nbsp; take_ash &nbsp; go_home &nbsp; map &nbsp; >',  fontcolor='blue') >> op_robot
     external_agent_ws >> Edge(color='blue', style='solid',  decorate='true', label='<kg &nbsp; >',  fontcolor='blue') >> waste_storage
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<rp_number &nbsp; >',  fontcolor='blue') >> wis
     activator_mock >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> incinerator
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; burn_end &nbsp; >',  fontcolor='blue') >> monitoring_device
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<kg &nbsp; >',  fontcolor='blue') >> waste_storage
diag
