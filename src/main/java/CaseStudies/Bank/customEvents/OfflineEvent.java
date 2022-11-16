package CaseStudies.Bank.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class OfflineEvent extends EventContext {
	 public OfflineEvent(Node target) {
	        super("offline", target);
	    }
}
