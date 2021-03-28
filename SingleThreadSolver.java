/*****************************************
 * Riley Stewart-Tonkin * LU ID: 0347567 *
 ****************************************/

public class SingleThreadSolver {
    private int numberOfSteps;

    public SingleThreadSolver() {
        numberOfSteps = 0;
    }

    /***
     * Starts at the first location in the board and check whether it is valid or
     * not This function is to ensure the user cannot skip values.
     * 
     * @param board
     * @return
     */
    public boolean runSingleThreadSolver(SudokuBoard board) {
        return singleSolver(board, 0);
    }

    /***
     * 
     * @param board
     * @param index
     * @return
     */
    private boolean singleSolver(SudokuBoard board, int index) {
        if (index < board.GRIDSIZE * board.GRIDSIZE) {
            int row = index / board.GRIDSIZE;
            int column = index % board.GRIDSIZE;

            if (board.getValue(row, column) != 0) {
                return singleSolver(board, index + 1);
            } else {
                for (int value = 1; value <= board.GRIDSIZE; value++) {
                    if (isBoardValid(board, row, column, value)) {
                        board.setValue(row, column, value);
                        numberOfSteps++;

                        if (singleSolver(board, index + 1)) {
                            return true;
                        } else {
                            board.setValue(row, column, 0);
                        }
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean isBoardValid(SudokuBoard board, int row, int column, int value) {
        int rowSubGrid = row / board.ROWS * board.ROWS;
        int columnSubGrid = column / board.COLUMNS * board.COLUMNS;

        for (int i = 0; i < board.GRIDSIZE; i++) {

            if (board.getValue(row, i) == value) {
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