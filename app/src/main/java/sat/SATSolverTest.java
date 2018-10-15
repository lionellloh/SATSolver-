package sat;
import java.io.*;
/*
import static org.junit.Assert.*;

import org.junit.Test;
*/

import sat.env.*;
import sat.formula.*;
import java.io.*;

public class SATSolverTest {

    public static void main(String[] args) throws FileNotFoundException  {

        File file = new File("/Users/lionellloh/AndroidStudioProjects/SATSolver-/app/src/main/java/sat/sampleCNF/2sat.cnf");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        int i = 0;
        try {
            while ((st = br.readLine()) != null){
                i+=1;
                if(i<=2){
                    continue;
                }

                String output = "Answer dsdasad: " + st + "\n";
                System.out.println(output);
                System.out.println(st);

                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./BoolAssignment.txt", true), "utf-8")))
                {
                    writer.write(output);
                }



                }

        }catch(IOException ioe) {

            System.out.println(ioe);

        }


    }



    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();



	
	// TODO: add the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability
    
	
    public void testSATSolver1(){
    	// (a v b)
    	Environment e = SATSolver.solve(makeFm(makeCl(a,b))	);
/*
    	assertTrue( "one of the literals should be set to true",
    			Bool.TRUE == e.get(a.getVariable())  
    			|| Bool.TRUE == e.get(b.getVariable())	);
    	
*/    	
    }
    
    
    public void testSATSolver2(){
    	// (~a)
    	Environment e = SATSolver.solve(makeFm(makeCl(na)));
/*
    	assertEquals( Bool.FALSE, e.get(na.getVariable()));
*/    	
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