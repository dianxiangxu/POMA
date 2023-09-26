package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import POMA.Verification.ReachabilityAnalysis.model.Solution;

public class SolutionSimulator {
    private Solution solution;
    private Graph graph;
    private Obligation obligation;

    public SolutionSimulator(Solution solution, Graph graph, Obligation obligation) {
        this.solution = solution;
        this.graph = graph;
        this.obligation = obligation;
    }

    public void simulate() throws Exception {
        PDP pdp = getPDP(graph, obligation);
        int i = 1;
        for (ObligationFiring firing : solution.getObligationFirings()) {
            String subject = firing.getSubject();
            String event = firing.getEvent();
            String target = firing.getTarget();
            executeEvent(i, subject, event, target, pdp);
            i++;
        }
    }

    private void executeEvent(int i, String subject, String event, String target, PDP pdp) throws Exception {
        PReviewDecider decider = new PReviewDecider(graph);
        if (!decider.check(subject, "", target, event)) {
            System.out
                    .println("The following permission was not available: " + subject + " : " + event + " : " + target
                            + "; at step " + i);
            return;
        }
        
        pdp.getEPP().processEvent(new EventContext(event, graph.getNode(target)), subject, "");

        System.out.println("The event " + event + " at step: " + i + " was simulated successfully");
    }

//   private void SimulateWithPredicated(String pre, String post) {
//       IFormula formulaPre = pre.isEmpty() ? null : parseQuery(pre);
//		IFormula formulaPost = post.isEmpty() ? null : parseQuery(post);
//   } 
    
    static PDP getPDP(Graph graph, Obligation obligation) throws Exception {
        EPPOptions eppOptions = new EPPOptions();

        PDP pdp = new PDP(new PAP(graph, null, new MemObligations()), eppOptions);
        if (graph.exists("super_pc_rep")) {
            graph.deleteNode("super_pc_rep");
        }
        pdp.getPAP().getObligationsPAP().add(obligation, true);
        
        return pdp;
    }
}
