# dpml-swe

CSP implementations for Sudoku and Maximum Cut problems.

Jar usage:

- help

```
usage: dpml [-h]
            [-a {backtrack,pruning,node-consistent,arc-consistent,forward-check}]
            [-p PATH] {sudoku,maxcut}

Java CSP implementations for Sudoku and Maximum Cut.

positional arguments:
  {sudoku,maxcut}        Problem to solve. (default: maxcut)

named arguments:
  -h, --help             show this help message and exit
  -a {backtrack,pruning,node-consistent,arc-consistent,forward-check}, --alg {backtrack,pruning,node-consistent,arc-consistent,forward-check}, --algorithm {backtrack,pruning,node-consistent,arc-consistent,forward-check}
                         The algorithm variation to use.
  -p PATH, --path PATH   Path to an input file.  It  can either be a Sudoku
                         puzzle file, or a Graph file.
                         In the case of  sudoku,  the  input  needs to be a
                         CSV of a 9x9 board with  values from 0 to 9, where
                         0 represents an empty cell.
                         The graph  needs  to  follow  the  format  of  the
                         Stanford    graphs.    See    http://web.stanford.
                         edu/~yyye/yyye/Gset/.
```

- run Sudoku: `java -jar .\dpml-swe.jar sudoku --alg forward-check --path .\data\S2`
- run Maximum Cut: `java -jar .\dpml-swe.jar maxcut --alg forward-check --path .\data\G3`
