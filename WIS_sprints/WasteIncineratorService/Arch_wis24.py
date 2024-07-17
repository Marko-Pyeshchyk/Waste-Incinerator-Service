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
          wis=Custom('wis','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
     with Cluster('ctxbasicrobot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     with Cluster('ctxmd', graph_attr=nodeattr):
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
     with Cluster('ctxext', graph_attr=nodeattr):
          activator_mock=Custom('activator_mock','./qakicons/symActorSmall.png')
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> wis
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> op_robot
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_value &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<robot_move &nbsp; >',  fontcolor='blue') >> op_robot
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<weight &nbsp; >',  fontcolor='blue') >> wis
     activator_mock >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> incinerator
diag
