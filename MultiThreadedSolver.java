/***************************************
 * Riley Stewart-Tonkin LU ID: 0347567 *
 **************************************/

public class MultiThreadedSolver implements Callable<Boolean> {
    private static int numberOfSteps;
    private SudokuBoard board;
    private final int rowThread;

    public MultiThreadedSolver(SudokuBoard board, int rowThread) {
        this.board = board;
        this.rowThread = rowThread;
    }

    @Override
    public Boolean call() throws Exception {
        return runMultiSolver();
    }

    public boolean runMultiSolver() {
        return multiSolver(0);
    }

    private boolean multiSolver(int index) {
        if (index < board.GRIDSIZE) {
            int column = index % board.GRIDSIZE;

            if (board.getValue(rowThread, column) != 0) {
                return multiSolver(index + 1);
            } else {
                for (int value = 1; value <= board.GRIDSIZE; value++) {
                    if (isBoardValid(rowThread, column, value)) {
                        board.setValue(rowThread, column, value);
                        numberOfSteps++;

                        if (naiveSolver(index + 1)) {
                            return true;
                        } else {
                            board.setValue(rowThread, column, 0);
                        }
                    }
                }
            }

            return false;
        } else {
            return true;
        }
    }

    private boolean isBoardValid(int rowThread, int column, int value) {
        int rowSubGrid = rowThread / board.ROWS * board.ROWS;
        int columnSubGrid = column / board.COLUMNS * board.COLUMNS;

        for (int i = 0; i < board.GRIDSIZE; i++) {

            if (board.getValue(rowThread, i) == value) {
                return false;
            } else if (board.getValue(i, column) == value) {
                return false;
            } else if (board.getValue(rowSubGrid + i % board.ROWS, columnSubGrid + i / board.COLUMNS) == value) {
                return false;
            }
        }

        return true;
    }

    public void displayNumberOfSteps() {
        System.out.println("It took " + numberOfSteps + " steps to solve this puzzle. \n");
    }
}