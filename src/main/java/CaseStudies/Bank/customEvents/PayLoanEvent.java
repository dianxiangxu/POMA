package CaseStudies.Bank.customEvents;

import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class PayLoanEvent extends EventContext {
	 public PayLoanEvent(Node target) {
	        super("pay-loan", target);
	    }
}
