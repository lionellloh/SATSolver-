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
        File file = new File("/C:/Users/Eda Tan/Desktop/Term4/50.001/Project-2D/project-2d-starting/sampleCNF/s8sat.cnf");

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

        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }

	
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