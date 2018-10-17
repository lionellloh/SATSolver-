## SAT Solver

In this 2D challenge, we are implementing an Algorithm to tackle the Boolean Satisfiability problem. Basically, it is the problem of determining if there exists a combination of boolean values for the inputs to satisfy a given Boolean Formula. 

**For example:** 

Given a <u>Boolean Propositional Formula</u> (*Python syntax*): 

``` (!a or b) and (!b or c) and (!c or b) ```

The program will try to determine if the formula is satisfiable. For this specific formula, the program is satisfiable. One example of satisfiable input value is `b = true` and `c = true`. For these values of `b` and `c`, `a` can undertake any value. One example is enough for proof of satisfiability. 

To prove the opposite, which is unsatisfiability, the program does not implment an exhaustive approach. Instead, there are some ways to determine if an algorithm is definitely unsatisfiable due to the merits of the Conjunction Normal Form format (CNF). To find out more about this algorithm, click [here](http://www.dis.uniroma1.it/~liberato/ar/dpll/dpll.html).

**Definitions**: 

Literal: `a`,  `b`  or `c` . There are 3 different literals in this propositional formula.

Negated literal: `!a` or `!c` which is the same as `not a` 

Clause: `(!a or b)`

Disjuction: `or` 

Conjunction: `and`

In this challenge, we implemented the [DPLL](http://www.dis.uniroma1.it/~liberato/ar/dpll/dpll.html) Algorithm in Java - which makes use of recursion, backtracking and unit propagation. Interestingly, the DPLL Algorithm seems to be an implementation of **Depth-First Search (DFS)**. 



### CNF File

`*.cnf` files are essentially `.txt` files that are saved with a filename extension for convenience. 

For the simple formula below, the cnf file will be as such: 

  ``` (!a or b) and (!b or c) and (!c or b) or (!e or f) or (d or e or !a) ```

```
p cnf 6 5 
```

6 is the number of literals, 5 is the number of clause. If the line starts with p, it will be ignored. 

```
-1 2 0 
-2 3 0 
-3 2 0 
-5 6 0 
4 5 -1 0 
```

0 signifies the end of each line. 



### Usage of code 

```
git clone https://github.com/lionellloh/SATSolver-
```

[Companion Repo](https://github.com/lionellloh/32bitAdder) contains the 32 bit Adder code done in JSIM. The .bc file contains a partial representation of the 32 bit Brent Kung Architecture and the ri





### Team Members

- Billio Jeverson 
- Claire Tan 
- Eda Tan 
- Glenn Chia 
- Lionell Loh
- Sean Lew 