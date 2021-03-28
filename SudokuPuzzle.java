/*************************
 * Riley Stewart-Tonkin * LU ID: 0347567 *
 ************************/
public class SudokuPuzzle {
    public void solveSudoku(SudokuBoard board) {
        Timer timer = new Timer();
        timer.start();

        SingleThreadSolver singleSolver = new SingleThreadSolver();

        if (singleSolver.runSingleThreadSolver(board)) {
            timer.end();
            System.out.printf("This puzzle took about %.4f seconds to solve.\n\n", timer.getTotalTime());
            singleSolver.displayNumberOfSteps();
            board.printBoard();
            System.out.println("");
        } else {
            System.out.println("This puzzle is not solvable with the way it is right now. ");
        }
    }
}