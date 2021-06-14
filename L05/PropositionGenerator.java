
import java.util.*;

/**
 * A PropositionGenerator object is capable of generating a random CNFProposition proposition.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class PropositionGenerator {
    private final Random r;
    private int numVariables;
    private int numClauses;
    private int varsPerClause;
    private List<Variable> variables;

    /**
     * Construct a proposition generator
     * @param numVariables number of variables across all clauses
     * @param numClauses number of clauses in generated proposition
     * @param varsPerClause number of variables per clause in generated proposition
     */
    public PropositionGenerator(int numVariables, int numClauses, int varsPerClause) {
        this(numVariables, numClauses, varsPerClause, 0);
    }

    /**
     /**
     * Construct a proposition generator
     * @param numVariables number of variables across all clauses
     * @param numClauses number of clauses in generated proposition
     * @param varsPerClause number of variables per clause in generated proposition
     * @param seed a random seed
     */
    public PropositionGenerator(int numVariables, int numClauses, int varsPerClause, long seed) {
        this.numVariables = numVariables;
        this.numClauses = numClauses;
        this.varsPerClause = varsPerClause;
        generateVariables();
        r = new Random(seed);
    }

    /**
     * Returns a randomly generated CNFProposition proposition.
     * @return a CNFProposition object
     */
    public CNFProposition generateRandomInstance() {
        Set<Clause> clauses = new HashSet<>();
        int duplicateClauses = 0;
        for (int i = 0; i < numClauses; ) {
            Clause c = generateClause();
            if (clauses.add(c)) {   // check that c is not duplicate of previously generated clause
                i++;
                duplicateClauses = 0;
            } else {
                duplicateClauses++;
                if (duplicateClauses > 100) {
                    throw new RuntimeException("Giving up random clause generation after " +
                            duplicateClauses + " duplicate clauses were generated.");
                }
            }
        }
        return new CNFProposition(clauses);
    }

    private void generateVariables() {
        variables = new ArrayList<>();
        for (int i = 0; i < numVariables; i++) {
            variables.add(new Variable("p" + (i+1)));
        }
    }

    private Clause generateClause() {
        List<Literal> literals = new LinkedList<>();
        Collections.shuffle(variables, r);
        for (int i = 0; i < varsPerClause; i++) {
            Variable v = variables.get(i);
            boolean isPositive = false;
            if (r.nextBoolean()) {
                isPositive = true;
            }
            literals.add(new Literal(v, isPositive));
        }
        return new Clause(literals);
    }

    public static void main(String[] args) {
        PropositionGenerator generator = new PropositionGenerator(4, 3, 3);
        CNFProposition proposition = generator.generateRandomInstance();
        System.out.println("Randomly generated CNFProposition proposition: " + proposition);
    }
}
