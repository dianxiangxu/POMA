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

In order to solve the contraint, use the following format method call: 

```java
Solution solution = checker.solveConstraint("OBLIGATIONLABEL(obligation2);");
```

The solution object, hopefully, will contain a list of steps. 

The following are the queries currently supported: 



