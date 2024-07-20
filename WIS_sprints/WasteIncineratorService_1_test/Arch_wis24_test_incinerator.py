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
with Diagram('wis24_test_incineratorArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxwis', graph_attr=nodeattr):
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          wis_mock=Custom('wis_mock','./qakicons/symActorSmall.png')
          activator_mock=Custom('activator_mock','./qakicons/symActorSmall.png')
          test_observer=Custom('test_observer','./qakicons/symActorSmall.png')
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     incinerator >> Edge( label='burn_end', **eventedgeattr, decorate='true', fontcolor='red') >> wis_mock
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> wis_mock
     wis_mock >> Edge(color='blue', style='solid',  decorate='true', label='<event_sent &nbsp; >',  fontcolor='blue') >> test_observer
     activator_mock >> Edge(color='blue', style='solid',  decorate='true', label='<activation_command &nbsp; >',  fontcolor='blue') >> incinerator
     wis_mock >> Edge(color='blue', style='solid',  decorate='true', label='<burn_start &nbsp; ash_taken &nbsp; >',  fontcolor='blue') >> incinerator
diag
