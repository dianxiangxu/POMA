package CaseStudies.Bank.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class OnlineEvent extends EventContext {
	 public OnlineEvent(Node target) {
	        super("online", target);
	    }
}
