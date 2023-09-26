package CaseStudies.Bank.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class DeleteEvent extends EventContext {
	 public DeleteEvent(Node target) {
	        super("delete", target);
	    }
}
