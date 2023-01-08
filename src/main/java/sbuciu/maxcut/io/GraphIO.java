package sbuciu.maxcut.io;

import sbuciu.maxcut.model.Graph;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphIO {
    public static Graph read(String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("path does not exist.");
        }

        final List<String> lines = Files.readAllLines(Paths.get(path));
        final int n = Integer.parseInt(lines.get(0));

        final Graph graph = new Graph(n);
        for (int i = 1; i < lines.size(); i += 1) {
            final String[] edge = lines.get(i).split(" ");
            final int u = Integer.parseInt(edge[0]) - 1,
                    v = Integer.parseInt(edge[1]) - 1;

            graph.addEdge(u, v);
        }

        return graph;
    }
}
