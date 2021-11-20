# POMA
POlicy Machine Analyzer: a tool for testing and verifying NGAC (Next Generation Access Control) policies.  

## Verification

### Interface

In order to create the obligation checker with graph and obligation(prohibition is in works), use one of the following constructors:

**With objects:**

```java
Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json");
String yml = new String(Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml")));
Obligation obligation = EVRParser.parse(yml);
ObligationChecker checker = new ObligationChecker(graph,obligation);
```


**With paths(only single graph json supported):**

```java
ObligationChecker checker = new ObligationChecker("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json",
"Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml");
```

Then, you need to set the output for smt code as follows: 

```java
checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
```

### Queries

**PREDICATES**

In order to solve the contraint, use the following format method call: 

```java
Solution solution = checker.solveConstraint("OBLIGATIONLABEL(obligation2);");
```

The solution object, hopefully, will contain a list of steps. 

The following are the queries currently supported: 
| Predicate  | Query Example |
| ------------- | ------------- |
| Obligation Label is reachable  | OBLIGATIONLABEL(obligation2); |
| Association xists  | ASSOCIATE(Attorneys,refuse,Case3);  |
| Permission exists  | PERMIT(Attorneys2U, accept, Case3Info); |
| Explicit assignment exists (no hierarchy)  | ASSIGN(Attorneys2U, Attorneys2); |
| Explicit + implicit assignment exists (hierarchy accounted for) | EXPLICITASSIGN(Attorneys2U, Attorneys); |
| Deny - permission does not exist | DENY(Attorneys2U, accept, Case3Info); |
| Hierarchy exists - either a is assigned to b or b is assigned to a(inheritance included) | HIERARCHY(Attorneys2U, Attorneys2); |

**CONNECTIVES**

Both "and" and "or" are supported. The format as follows: 

```java
"(OBLIGATIONLABEL(obligation2) AND ASSOCIATE(Attorneys,refuse,Case3));" 
```
```java
"(OBLIGATIONLABEL(obligation2) OR ASSOCIATE(Attorneys,refuse,Case3));"
```

Note that even though smt supports and/or with 3 or more elements, this feature is not included in this solver.

**NEGATION**

In order to use negation, simply do: 

```java
"NOT(OBLIGATIONLABEL(obligation2));"
```

**NOTE**: while queries of type _(Predicate1 AND (Predicate2 OR Predicate3))_ should work, there was no thorough testing of those. 

**TERMS**
Any terms that contains a "?" is considered to be a variables. Otherwise, it is a constant. Please give your variables valid names that you will recognize once the processing is done. 
