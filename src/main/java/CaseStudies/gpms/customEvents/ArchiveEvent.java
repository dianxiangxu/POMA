package CaseStudies.gpms.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class ArchiveEvent extends EventContext {
	
	
	 public ArchiveEvent(Node target) {
	        super("archive", target);
	    }
	 
	 
	 }
