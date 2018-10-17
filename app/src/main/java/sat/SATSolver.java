package sat;

import java.util.Iterator;

import immutable.EmptyImList;
import immutable.ImList;
import immutable.NonEmptyImList;
import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
import sat.formula.PosLiteral;

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
        Environment env = new Environment();
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
        if (clauses.isEmpty()) {


            return env;
        }

        //Find the smallest clause
        Clause smallest = clauses.first();
        for (Clause c: clauses) {
            if (c.size() < smallest.size()) {
                smallest = c;
            }
        }

        //Choose arbitrary literal
        Literal arbitrary = smallest.chooseLiteral();
        ImList<Clause> reduced = substitute(clauses, arbitrary);
        if (reduced == null) {

            return null;
        }

        if (arbitrary instanceof NegLiteral) {
            env = env.put(arbitrary.getVariable(), Bool.FALSE);
        } else {
            env = env.put(arbitrary.getVariable(), Bool.TRUE);
        }

        if (smallest.isUnit()) {
            return solve(reduced, env);
        } else {
            Environment sol = solve(reduced, env);

            if (sol == null) {
                reduced = substitute(reduced, arbitrary.getNegation());

                if (reduced == null) {
                    return null;
                }

                if (arbitrary instanceof NegLiteral) {
                    env = env.put(arbitrary.getVariable(), Bool.FALSE);
                } else {
                    env = env.put(arbitrary.getVariable(), Bool.TRUE);
                }
                return solve(reduced, env);
            } else {
                return sol;
            }
        }
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
        1. Iterate through the clauses list, if the clause is true, don't add it to the list
        2. If the clause contains the negation of the literal, remove the literal
        2a. Check if the clause is empty. If it is, solution is not satisfiable, return null
         */
        ImList<Clause> newList = new EmptyImList<>();
        for (Clause addClause: clauses) {
            if(addClause.contains(l) || addClause.contains(l.getNegation())) {

                addClause = addClause.reduce(l);

                if (addClause != null) {
                    if (addClause.isEmpty()) {

                        return null;
                    }
                    newList = newList.add(addClause);
                }
            } else {
                newList = newList.add(addClause);
            }
        }
        return newList;
    }
}