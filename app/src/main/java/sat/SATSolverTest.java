package sat;

/*
import static org.junit.Assert.*;
import org.junit.Test;
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

import sat.env.*;
import sat.formula.*;


public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();


    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("/Users/lionellloh/AndroidStudioProjects/SATSolver-/app/src/main/java/sat/sampleCNF/2sat.cnf");

        Formula newFormula = new Formula();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        int i = 0;
        try {

            while ((st = br.readLine()) != null){
                String[] splitted = st.split("\\s+"); // Split by whitespace
//
                if (splitted[0].equals("c") || splitted[0].equals("p") || splitted[0].equals("")){

                    continue;

                }

                else {

//                    System.out.println(st);
                    Literal a;

                    Clause newClause = new Clause();
                    for (String s: splitted){

                        if (Integer.parseInt(s) < 0){
                            a = NegLiteral.make(s);
                        }

                        else if (Integer.parseInt(s) > 0) {
                            a = PosLiteral.make(s);
                        }

                        else {
                            continue;
                        }

                        newClause = newClause.add(a);

                    }
                    newFormula = newFormula.addClause(newClause);

                }


            }}catch(IOException ioe){

            System.out.println(ioe);
        }
        Environment output = SATSolver.solve(newFormula);
        System.out.println(output);


        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./BoolAssignment.txt", true), "utf-8")))


        {
//

            String string_output = output.toString();
            string_output  = string_output.substring(string_output.indexOf("[") + 1, string_output.indexOf("]"));
            System.out.println(string_output);

            String[] output_split = string_output.split(",");

            for (String s: output_split){

                String[] small_split = s.split("\\-\\>");
                String part1 = small_split[0].trim();
                String part2 = small_split[1].trim();
                String line = part1 + ":" +  part2 + "\n";
                writer.write(line);

//                System.out.println(part1);
//                System.out.println(part2);

            }


//            System.out.println("WORKS");


        }catch(IOException ioe) {

            System.out.println(ioe);

        }
    }


    // TODO: add the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability


    public void testSATSolver1(){
        // (a v b)
        Environment e = SATSolver.solve(makeFm(makeCl(a,b))	);
    }


    public void testSATSolver2(){
        // (~a)
        Environment e = SATSolver.solve(makeFm(makeCl(na)));
    }

    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }

    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }


}