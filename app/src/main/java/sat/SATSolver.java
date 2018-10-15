package sat;

import java.util.Iterator;

import immutable.EmptyImList;
import immutable.ImList;
import immutable.NonEmptyImList;
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;

/**
 * A simple DPLL SAT solver. See http://en.wikipedia.org/wiki/DPLL_algorithm
 */
public class SATSolver {
    /**
     * Solve the problem using a simple version of DPLL with backtracking and
     * unit propagation. The returned environment binds literals of class
     * bool.Variable rather than the special literals used in clausification of
     * class clausal.Literal, so that clients can more readily use it.
     * 
     * @return an environment for which the problem evaluates to Bool.TRUE, or
     *         null if no such environment exists.
     */
    public static Environment solve(Formula formula) {
        // TODO: implement this.
        Environment env = new Environment();
        /*for (Iterator<Clause> formulaiterator = formula.iterator(); formulaiterator.hasNext();) { //iterates through formula
            for (Iterator<Literal> clauseiterator = formulaiterator.next().iterator(); clauseiterator.hasNext();) { //iterators through clauses to get literals
                env.put(formulaiterator.next().iterator().next().getVariable(), null);
            }
        }*/
        return solve(formula.getClauses(), env);
    }

    /**
     * Takes a partial assignment of variables to values, and recursively
     * searches for a complete satisfying assignment.
     * 
     * @param clauses
     *            formula in conjunctive normal form
     * @param env
     *            assignment of some or all variables in clauses to true or
     *            false values.
     * @return an environment for which all the clauses evaluate to Bool.TRUE,
     *         or null if no such environment exists.
     */
    private static Environment solve(ImList<Clause> clauses, Environment env) {
        // TODO: implement this.
        if (clauses.isEmpty()) {
            return env;
        }

        //Set the smallest clause
        Clause smallest = clauses.first();


        return env;
    }

    /**
     * given a clause list and literal, produce a new list resulting from
     * setting that literal to true
     * 
     * @param clauses
     *            , a list of clauses
     * @param l
     *            , a literal to set to true
     * @return a new list of clauses resulting from setting l to true
     */
    private static ImList<Clause> substitute(ImList<Clause> clauses,
            Literal l) {
        /*Whichever clause has the literal must be removed from the list
        1. Iterate through the clauses list, if the clause contains the literal, remove that clause
        2. If the clause contains the negation of the literal, remove the literal
         */
        ImList<Clause> newList = new EmptyImList<>();
        for (Iterator<Clause> clauseIterator = clauses.iterator(); clauseIterator.hasNext();) {
            Clause addClause = clauseIterator.next();
            if(addClause.contains(l) || addClause.contains(l.getNegation())) {
                addClause = addClause.reduce(l);
                /*if (addClause != null) {
                    if (addClause.isEmpty()) {
                        addClause = null;
                    }
                }*/
            }
            newList.add(addClause);
        }
        return newList;
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
