from z3 import *

A = DeclareSort("A")
Attorneys, Attorneys1, Attorneys2, Attorneys4, Attorneys3, \
LeadAttorneys, CSuit, CasePolicy, \
Case3, Case3Info, Apple, Google = Consts('Attorneys Attorneys1 Attorneys2 Attorneys4 '
                                         'Attorneys3 LeadAttorneys CSuit '
                                         'CasePolicy Case3 Case3Info Apple Google', A)
ASSOCIATIONS = [(Attorneys, 'accept', Case3), (Attorneys, 'refuse', Case3), (LeadAttorneys, 'withdraw', Case3),
                (LeadAttorneys, 'disapprove', Case3)]

GRAPH = [(Attorneys, CasePolicy), (CSuit, LeadAttorneys), (LeadAttorneys, CasePolicy), (Attorneys3, Attorneys3),
         (Attorneys2, Attorneys2), (Attorneys1, Attorneys1),
         (Attorneys, Attorneys), (CSuit, CSuit), (LeadAttorneys, LeadAttorneys), (LeadAttorneys, Attorneys),
         (Case3, CasePolicy), (Case3Info, Case3), (Case3, Case3),
         (Apple, Case3), (Google, Case3)]






