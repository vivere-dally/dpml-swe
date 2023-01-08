package sbuciu;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import sbuciu.maxcut.*;
import sbuciu.maxcut.io.GraphIO;
import sbuciu.maxcut.model.MaxCutSolution;
import sbuciu.sudoku.Sudoku;
import sbuciu.sudoku.SudokuBacktrack;
import sbuciu.sudoku.SudokuPRNodeConsistency;
import sbuciu.sudoku.SudokuSSForwardCheck;
import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.SudokuSolution;

import java.io.IOException;
import java.util.Arrays;

public class DPML {
    public static void main(String[] args) throws IOException {
        final ArgumentParser parser = ArgumentParsers.newFor("dpml")
                .build()
                .defaultHelp(true)
                .description("Java CSP implementations for Sudoku and Maximum Cut.");

        parser.addArgument("problem")
                .choices("sudoku", "maxcut")
                .setDefault("maxcut")
                .help("Problem to solve.");
        parser.addArgument("-a", "--alg", "--algorithm")
                .choices("backtrack", "pruning", "node-consistent", "arc-consistent", "forward-check")
                .help("The algorithm variation to use.");
        parser.addArgument("-p", "--path")
                .setDefault("D:\\ICA\\DPML\\software\\dpml-swe\\data\\S3")
                .help("Path to an input file. It can either be a Sudoku puzzle file, or a Graph file."
                        .concat(System.lineSeparator())
                        .concat("In the case of sudoku, the input needs to be a CSV of a 9x9 board with values from 0 to 9, where 0 represents an empty cell.")
                        .concat(System.lineSeparator())
                        .concat("The graph needs to follow the format of the Stanford graphs. See http://web.stanford.edu/~yyye/yyye/Gset/.")
                        .concat(System.lineSeparator())
                        .concat("The jar comes with some default input files: use src/main/resources/<file> where <file> is: G1,G2,G3 for MaxCut, and S1,S2,S3 for Sudoku."));

        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }

        String path = ns.getString("path");
        switch (ns.getString("problem")) {
            case "sudoku":
                Sudoku sudoku;
                switch (ns.getString("alg")) {
                    case "backtrack":
                        sudoku = new SudokuBacktrack(Board.read(path));
                        break;
                    case "node-consistent":
                        sudoku = new SudokuPRNodeConsistency(Board.read(path));
                        break;
                    case "forward-check":
                        sudoku = new SudokuSSForwardCheck(Board.read(path));
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported algorithm");
                }

                System.out.printf("Running %s Maximum Cut %n", ns.getString("alg"));
                final SudokuSolution sudokuSolution = sudoku.solve();
                System.out.println("Solution\n" + sudokuSolution.encode());

                break;
            case "maxcut":
                MaxCut maxCut;
                switch (ns.getString("alg")) {
                    case "backtrack":
                        maxCut = new MaxCutBacktrack(GraphIO.read(path));
                        break;

                    case "pruning":
                        maxCut = new MaxCutPRPruning(GraphIO.read(path));
                        break;

                    case "arc-consistent":
                        maxCut = new MaxCutPRArcConsistent(GraphIO.read(path));
                        break;

                    case "forward-check":
                        maxCut = new MaxCutSSForwardCheck(GraphIO.read(path));
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported algorithm");
                }

                System.out.printf("Running %s Maximum Cut %n", ns.getString("alg"));
                final MaxCutSolution sol = maxCut.solve();
                System.out.println("Found solution with cost: " + sol.getCost());
                System.out.println("Partition: " + Arrays.toString(sol.getPartitions()));
                break;
            default:
                throw new IllegalArgumentException("Unsupported problem");
        }
    }
}
