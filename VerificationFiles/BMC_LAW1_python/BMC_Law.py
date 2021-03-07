from z3 import *
import sys

# INITIAL STATE
from BMC_LAW1.initial_state import *

partial_orders = [PartialOrder(A, 0)]
s = Solver()


def add_assignments_from_graph(k):
    for assignment in GRAPH:
        s.add(partial_orders[k](assignment[0], assignment[1]))


def create_events(k):
    global event1
    global event2
    global event3
    global event4
    global event5
    print()
    print('STEP: ', k)
    event1 = Int('event1' + str(k))
    event2 = Int('event2' + str(k))
    event3 = Int('event3' + str(k))
    event4 = Int('event4' + str(k))
    event5 = Int('event5' + str(k))


def transition_check(k):

    print(s.check(Not(partial_orders[k](Attorneys, Attorneys))) == unsat and s.check(
        Not(partial_orders[k](Google, Case3))) == unsat)
    check = False
    for a in ASSOCIATIONS:
        if a[1] == 'accept':
            if s.check(Not(partial_orders[k](Attorneys, a[0]))) == unsat and s.check(
                    Not(partial_orders[k](Google, a[2]))):
                s.add(event1 == 1)
                check = True
                break
        if not check:
            s.add(event1 == 0)
        check = False
    if s.check(Not(partial_orders[k](Attorneys, Attorneys))) == unsat and s.check(
            Not(partial_orders[k](Google, Case3))):
        s.add(event1 == 1)
    else:
        s.add(event1 == 0)
    print(s.check(Not(partial_orders[k](Attorneys1, Attorneys))) == unsat and s.check(
        Not(partial_orders[k](Google, Case3))) == unsat)
    if s.check(Not(partial_orders[k](Attorneys1, Attorneys))) == unsat and s.check(
            Not(partial_orders[k](Google, Case3))):
        s.add(event2 == 1)
    else:
        s.add(event2 == 0)
    print(s.check(Not(partial_orders[k](Attorneys2, Attorneys))) == unsat and s.check(
        Not(partial_orders[k](Google, Case3))) == unsat)
    if s.check(Not(partial_orders[k](Attorneys2, Attorneys))) == unsat and s.check(
            Not(partial_orders[k](Google, Case3))):
        s.add(event3 == 1)
    else:
        s.add(event3 == 0)
    print(s.check(Not(partial_orders[k](Attorneys3, Attorneys))) == unsat and s.check(
        Not(partial_orders[k](Google, Case3))) == unsat)
    if s.check(Not(partial_orders[k](Attorneys3, Attorneys))) == unsat and s.check(
            Not(partial_orders[k](Google, Case3))):
        s.add(event4 == 1)
    else:
        s.add(event4 == 0)


def transition(k):

    response1 = Xor(event1 != 1, partial_orders[k](Attorneys1, Attorneys))
    response2 = Xor(event2 != 1, partial_orders[k](Attorneys2, Attorneys1))
    response3 = Xor(event3 != 1, partial_orders[k](Attorneys3, Attorneys2))
    response4 = Xor(event4 != 1, partial_orders[k](Attorneys4, Attorneys3))
    response5 = Xor(event5 != 1, partial_orders[k](CSuit, Attorneys4))

    s.add(response1, response2, response3, response4, response5)


def check_goal_state(k):
    return s.check(Not(partial_orders[k](Attorneys3, Attorneys))) == unsat and s.check(
            Not(partial_orders[k](Google, Case3)))


def delete_assignment(child, parent):
    GRAPH.remove((child, parent))


def add_assignment_or_node(child, parent):
    GRAPH.append((child, parent))


def add_association(child, ar, parent):
    ASSOCIATIONS.append((child, ar, parent))


def remove_association(child, ar, parent):
    ASSOCIATIONS.remove((child, ar, parent))


def delete_node(node, graph):
    GRAPH = [x for x in graph if x[0] != node or x[1] != node]
    return GRAPH


def bmc(k_limit):
    add_assignments_from_graph(0)
    create_events(0)
    transition_check(0)
    k = 1
    while k < k_limit:
        partial_orders.append(PartialOrder(A, k))
        add_assignments_from_graph(k)
        transition(k)
        create_events(k)
        transition_check(k)
        if check_goal_state(k):
            print('Goal reached')
            break
        k += 1


bmc(4)














#print(s.model)




#GRAPH = [PO(Attorneys, CasePolicy), PO(CSuit, LeadAttorneys), PO(LeadAttorneys, CasePolicy),
#       PO(CSuit, CSuit), PO(LeadAttorneys, Attorneys), PO(Case3, CasePolicy), PO(Case3Info, Case3),
#      PO(Apple, Case3), PO(Google, Case3)]
#for assignment in GRAPH:
  #  s.add(assignment)

#print(s.check(Not(partial_orders[0](Attorneys, Attorneys))) == unsat and s.check(Not(partial_orders[0](Google, Case3))) == unsat)
#sys.exit()

#while True:




