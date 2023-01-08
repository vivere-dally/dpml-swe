package sbuciu;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import sbuciu.maxcut.*;
import sbuciu.maxcut.io.GraphIO;
import sbuciu.maxcut.model.MaxCutSolution;

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
                .choices("backtrack", "pruning", "arc-consistent", "forward-check")
                .help("The algorithm variation to use.");
        parser.addArgument("-p", "--path")
                .setDefault("src/main/resources/G3")
                .help("Path to a graph file. It is mandatory that it respects the format from the Stanford graphs.\nSee http://web.stanford.edu/~yyye/yyye/Gset/.");

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
