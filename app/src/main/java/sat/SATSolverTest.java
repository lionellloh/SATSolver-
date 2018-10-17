package sat;

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

        String filename = "2sat.cnf";
        File file = new File("/Users/lionellloh/AndroidStudioProjects/SATSolver-/app/src/main/java/sat/sampleCNF/" + filename);

        Formula newFormula = new Formula();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        int i = 0;
        try {
            System.out.println("Parsing file and creating Formula instance...");

//          Reading the file
            while ((st = br.readLine()) != null){
                String[] splitted = st.split("\\s+"); // Split by whitespace
                if (splitted[0].equals("c") || splitted[0].equals("p") || splitted[0].equals("")){

                    continue;

                }

                else {

                    Literal a;

                    Clause newClause = new Clause();
                    for (String s: splitted){

                        if (Integer.parseInt(s) < 0){

//                          Substring it to change -1 to 1
                            String s_input = s.substring(1, s.length());

//                          Convert 1 to ~1 using a Negliteral method
                            a = NegLiteral.make(s_input);

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

        System.out.println("SAT solver starts!!!");
        long started = System.nanoTime();

        Environment output = SATSolver.solve(newFormula);

        long time = System.nanoTime();
        long timeTaken = time - started;
        System.out.println("Time: " + timeTaken/1000000.0 + "ms");


        if (output == null){

            System.out.println("Unsatisfiable!");
        }

        else{
            System.out.println("Satisfiable!");

//            Writing to BoolAssignment.txt

            System.out.println("File writing in process...");
//            TODO: Clean a file completely each time I write.

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./app/src/main/java/sat/BoolAssignment.txt", false), "utf-8")))

            {
                String line = "Filename: " + filename + "\n";
                writer.write(line);

                } catch(IOException ioe) {

                System.out.println(ioe);

            }


//            Writing of satisfiable test cases
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./app/src/main/java/sat/BoolAssignment.txt", true), "utf-8")))

            {
                String string_output = output.toString();
                string_output  = string_output.substring(string_output.indexOf("[") + 1, string_output.indexOf("]"));
                String[] output_split = string_output.split(",");

                for (String s: output_split){

                    String[] small_split = s.split("\\-\\>");
                    String part1 = small_split[0].trim();
                    String part2 = small_split[1].trim();
                    String line = part1 + ":" +  part2 + "\n";
                    writer.write(line);
                }


            }catch(IOException ioe) {

                System.out.println(ioe);

            }


        }

//            TODO: Clean a file completely each time I write. 
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./app/src/main/java/sat/BoolAssignment.txt", true), "utf-8")))

        {
            String string_output = output.toString();
            string_output  = string_output.substring(string_output.indexOf("[") + 1, string_output.indexOf("]"));
            String[] output_split = string_output.split(",");

            for (String s: output_split){

                String[] small_split = s.split("\\-\\>");
                String part1 = small_split[0].trim();
                String part2 = small_split[1].trim();
                String line = part1 + ":" +  part2 + "\n";
                writer.write(line);
            }


        }catch(IOException ioe) {

            System.out.println(ioe);

        }

            System.out.println("File written!");
    }





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