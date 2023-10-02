package CaseStudies.Bank.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class ApplyLoanEvent extends EventContext {
	 public ApplyLoanEvent(Node target) {
	        super("apply-loan", target);
	    }
}
