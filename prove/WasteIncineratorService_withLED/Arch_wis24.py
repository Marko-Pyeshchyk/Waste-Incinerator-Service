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
with Diagram('wis24Arch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxwis', graph_attr=nodeattr):
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
          scale_device=Custom('scale_device','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          activator_mock=Custom('activator_mock','./qakicons/symActorSmall.png')
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          sonar_device=Custom('sonar_device','./qakicons/symActorSmall.png')
     with Cluster('ctxbrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> op_robot
     scale_device >> Edge( label='scale_data', **eventedgeattr, decorate='true', fontcolor='red') >> waste_storage
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> wis
     sonar_device >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<led_on &nbsp; led_off &nbsp; led_flashing &nbsp; >',  fontcolor='blue') >> led
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<work &nbsp; >',  fontcolor='blue') >> wis
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_value &nbsp; >',  fontcolor='blue') >> monitoring_device
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_value &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; ash_taken &nbsp; >',  fontcolor='blue') >> incinerator
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<cmd<font color="darkgreen"> cmddone cmdfailed</font> &nbsp; >',  fontcolor='blue') >> basicrobot
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<take_RP &nbsp; take_ash &nbsp; go_home &nbsp; >',  fontcolor='blue') >> op_robot
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<weight &nbsp; >',  fontcolor='blue') >> wis
     activator_mock >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> incinerator
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; burn_end &nbsp; >',  fontcolor='blue') >> monitoring_device
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> wis
diag
