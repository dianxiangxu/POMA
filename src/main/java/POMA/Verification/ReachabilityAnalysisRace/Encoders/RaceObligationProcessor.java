package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import POMA.Utils;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;

public class RaceObligationProcessor {

	public static void main(String[] args) throws Exception {
//		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/AssignANDGrant.yml")));
		Obligation obligation = EVRParser.parse(yml);
		RaceObligationProcessor rop = new RaceObligationProcessor();
		Map<String, List<Rule>> obligationGroups = rop.getObligationGroups(obligation);
		List<Rule> combinedObligations = rop.combineObligations(obligationGroups);
//        List<List<Rule>> permutations = generatePermutations(obligation.getRules());
//
//        for(List<Rule> permutation: permutations) {
//        	for(Rule rule : permutation) {
//        		System.out.println(rule.getLabel());
//        	}
//        	System.out.println("-------");
//        }
		System.out.println();
	}

	public List<Rule> getObligationsWithRaces(Obligation obligation) {
		Map<String, List<Rule>> obligationGroups = getObligationGroups(obligation);
		return combineObligations(obligationGroups);
	}

	private Map<String, List<Rule>> getObligationGroups(Obligation obligation) {
		List<Rule> rules = obligation.getRules();
		return rules.stream()
				.collect(Collectors.groupingBy(rule -> rule.getEventPattern().getOperations().get(0).toString()));
	}

	private List<Rule> combineObligations(Map<String, List<Rule>> groupedObligations) {
		List<Rule> resultingObligations = new ArrayList<Rule>();
		for (String key : groupedObligations.keySet()) {
			List<Rule> obligations = groupedObligations.get(key);
			if (obligations.size() < 2) {
				continue;
			}
			List<List<Rule>> permutations = generatePermutations(obligations);

			for (List<Rule> permutation : permutations) {
				Rule newObligation = new Rule();
				newObligation.setEventPattern(permutation.get(0).getEventPattern());
//				List<Action> actions = obligationToAdd.getResponsePattern().getActions();
				newObligation.setResponsePattern(new ResponsePattern());
				String combinedLabels = permutation.stream().map(Rule::getLabel).collect(Collectors.joining("_"));
				newObligation.setLabel(combinedLabels);

				for (int i = 0; i < permutation.size(); i++) {
					for (Action action : permutation.get(i).getResponsePattern().getActions()) {
						newObligation.getResponsePattern().addAction(action);
					}
				}

				resultingObligations.add(newObligation);

			}
		}
		return resultingObligations;
	}

	private List<List<Rule>> generatePermutations(List<Rule> objects) {
		List<List<Rule>> permutations = new ArrayList<>();
		permute(objects, 0, permutations);
		return permutations;
	}

	// Recursive method to generate permutations
	private void permute(List<Rule> arr, int k, List<List<Rule>> permutations) {
		for (int i = k; i < arr.size(); i++) {
			Collections.swap(arr, i, k);
			permute(arr, k + 1, permutations);
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			permutations.add(new ArrayList<>(arr));
		}
	}
//    public static List<String> generateAllPermutations(List<String> elements) {
//        List<String> allPermutations = new ArrayList<>();
//        for (int length = 2; length <= elements.size(); length++) {
//            generatePermutations(elements, new ArrayList<>(), length, 0, allPermutations);
//        }
//        return allPermutations;
//    }
//
//    private static void generatePermutations(List<String> elements, List<String> current, int length, int index, List<String> allPermutations) {
//        if (current.size() == length) {
//            allPermutations.addAll(permute(new ArrayList<>(current), 0));
//            return;
//        }
//
//        for (int i = index; i < elements.size(); i++) {
//            current.add(elements.get(i));
//            generatePermutations(elements, current, length, i + 1, allPermutations);
//            current.remove(current.size() - 1);
//        }
//    }
//
//    private static List<String> permute(List<String> arr, int index) {
//        List<String> permutations = new ArrayList<>();
//        if (index == arr.size() - 1) {
//            permutations.add(String.join("_", arr));
//            return permutations;
//        }
//
//        for (int i = index; i < arr.size(); i++) {
//            java.util.Collections.swap(arr, i, index);
//            permutations.addAll(permute(arr, index + 1));
//            java.util.Collections.swap(arr, i, index);
//        }
//        return permutations;
//    }
}
