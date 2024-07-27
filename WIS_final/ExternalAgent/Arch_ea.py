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
with Diagram('eaArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxea', graph_attr=nodeattr):
          external_agent=Custom('external_agent','./qakicons/symActorSmall.png')
     with Cluster('ctxwis', graph_attr=nodeattr):
          waste_storage=Custom('waste_storage(ext)','./qakicons/externalQActor.png')
     external_agent >> Edge(color='blue', style='solid',  decorate='true', label='<kg &nbsp; >',  fontcolor='blue') >> waste_storage
diag
