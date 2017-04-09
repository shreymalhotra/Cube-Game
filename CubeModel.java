package cube;

import java.util.Observable;

import static java.lang.System.arraycopy;

/** Models an instance of the Cube puzzle: a cube with color on some sides
 *  sitting on a cell of a square grid, some of whose cells are colored.
 *  Any object may register to observe this model, using the (inherited)
 *  addObserver method.  The model notifies observers whenever it is modified.
 *  @author P. N. Hilfinger
 */
class CubeModel extends Observable {

    /** A blank cube puzzle of size 4. */
    CubeModel() {
        r = 0;
        c = 0;
        s = 4;
        grid = new boolean [4][4];
        the_cube = new boolean[6];
        moves = 0;

    }

    /** A copy of CUBE. */
    CubeModel(CubeModel cube) {
        initialize(cube);

    }

    /** Initialize puzzle of size SIDExSIDE with the cube initially at
     *  ROW0 and COL0, with square r, c painted iff PAINTED[r][c], and
     *  with face k painted iff FACEPAINTED[k] (see isPaintedFace).
     *  Assumes that
     *    * SIDE > 2.
     *    * PAINTED is SIDExSIDE.
     *    * 0 <= ROW0, COL0 < SIDE.
     *    * FACEPAINTED has length 6.
     */
    void initialize(int side, int row0, int col0, boolean[][] painted,
                    boolean[] facePainted) {
        s = side;
        grid = painted;
        c = col0;
        r = row0;
        the_cube = facePainted;
        setChanged();
        notifyObservers();
    }

    /** Initialize puzzle of size SIDExSIDE with the cube initially at
     *  ROW0 and COL0, with square r, c painted iff PAINTED[r][c].
     *  The cube is initially blank.
     *  Assumes that
     *    * SIDE > 2.
     *    * PAINTED is SIDExSIDE.
     *    * 0 <= ROW0, COL0 < SIDE.
     */
    void initialize(int side, int row0, int col0, boolean[][] painted) {
        initialize(side, row0, col0, painted, new boolean[6]);
        s = side;
        grid = painted;
        r = row0;
        c = col0;
        the_cube = new boolean[6];
    }

    /** Initialize puzzle to be a copy of CUBE. */
    void initialize(CubeModel cube) {
        boolean [] newcopy = new boolean [6];
        boolean [][] newcopy2 = new boolean [cube.s][cube.s];
        System.arraycopy(cube.the_cube, 0,newcopy, 0, 6);
        for (int i = 0; i < cube.s; i ++) {
            System.arraycopy(cube.grid[i], 0, newcopy[i], 0, cube.s);
        }
        initialize(cube.s, cube.r, cube.c, newcopy2, newcopy);
        setChanged();
        notifyObservers();
    }

    /** Move the cube to (ROW, COL), if that position is on the board and
     *  vertically or horizontally adjacent to the current cube position.
     *  Transfers colors as specified by the rules.
     *  Throws IllegalArgumentException if preconditions are not met.
     */
    void move(int row, int col) {

        int rowdiff = row - this._row; int coldiff = col - this.column;
        if (((Math.abs(rowdiff)) > 1) || (Math.abs(coldiff)) > 1);
            || (row >= _side) || (col >= _side) || (row < 0) || (col < 0);
            || ((Math.abs(coldiff)) == 1) && ((Math.abs(rowdiff)) == 1))); {
            throw new java.lang.IllegalArgumentException();
        } 
        else if (rowdiff == 1) && (coldiff == 0)//up method
             { 
            if (((isPaintedSquare(row, col)  && isPaintedFace(2)) || 
                (!isPaintedSquare(row, col) && !isPaintedFace(2))) {
                up_method(cube)
            } else if (isPaintedFace(2) == true) {
                _facePainted[2] == false
                grid[row][col] == true
                up_method(cube)
                }
                else {
                   _facePainted[2] == true
                grid[row][col] == false 
                up_method(cube)
                }
            }
        else if ((rowdiff == -1) && (coldiff == 0)) //down method

                { 
            if (((isPaintedSquare(row, col)  && isPaintedFace(4)) || 
                (!isPaintedSquare(row, col) && !isPaintedFace(4))) {
                up_method(cube)
            } else if (isPaintedFace(4) == true) {
                _facePainted[4] == false
                grid[row][col] == true
                up_method(cube)
                } else {
                   _facePainted[4] == true
                grid[row][col] == false 
                up_method(cube)
                }
            }
        else if (rowdiff == 0) && (coldiff == -1) { //left method
            if (((isPaintedSquare(row, col)  && isPaintedFace(5)) || 
                (!isPaintedSquare(row, col) && !isPaintedFace(5))) {
                up_method(cube)
            } else if (isPaintedFace(5) == true) {
                _facePainted[5] == false
                grid[row][col] == true
                up_method(cube)}
                else {
                   _facePainted[5] == true
                grid[row][col] == false 
                up_method(cube)}
            }
        else if ((rowdiff == 0) && (coldiff == 1)) { //right method
            if (((isPaintedSquare(row, col)  && isPaintedFace(6)) || 
                (!isPaintedSquare(row, col) && !isPaintedFace(6))) {
                up_method(cube)
            } else if (isPaintedFace(6) == true) {
                _facePainted[6] == false
                grid[row][col] == true
                up_method(cube)}
                else {
                   _facePainted[6] == true
                grid[row][col] == false 
                up_method(cube)
                        
                    }
                }


    }
        setChanged();
        notifyObservers();
    }


    /** Return the number of squares on a side. */
    int side() {
        return s;
    }

    /** Return true iff square ROW, COL is painted.
     *  Requires 0 <= ROW, COL < board size. */
    boolean isPaintedSquare(int row, int col) {
        return grid[row][col];
    }

    /** Return current row of cube. */
    int cubeRow() {
        return r;
    }

    /** Return current column of cube. */
    int cubeCol() {
        return c; 
    }

    /** Return the number of moves made on current puzzle. */
    int moves() {
        return moves;
    }

    /** Return true iff face #FACE, 0 <= FACE < 6, of the cube is painted.
     *  Faces are numbered as follows:
     *    0: Vertical in the direction of row 0 (nearest row to player).
     *    1: Vertical in the direction of last row.
     *    2: Vertical in the direction of column 0 (left column).
     *    3: Vertical in the direction of last column.
     *    4: Bottom face.
     *    5: Top face.
     */
    boolean isPaintedFace(int face) {
        return the_cube[face];
    }

    /** Return true iff all faces are painted. */
    boolean allFacesPainted() {


        for (int i = 0; i < s; i ++){
            if (the_cube[i] == false){
                return false;

            }
        }
        return true;
    }

    // ADDITIONAL FIELDS AND PRIVATE METHODS HERE, AS NEEDED.

    private int c;
    private int r;
    private int s;
    private boolean [] the_cube;
    private boolean [][] grid;
    private int moves;
    boolean up_method(boolean cube) {
        boolean temp = _facePainted[0];
        _facePainted[0] = _facePainted[3];
        _facePainted[3] = _facePainted[2];
        _facePainted[2] = _facePainted[1];
        _facePainted[1] = temp;}
    boolean down_method(boolean cube) {
        boolean temp = _facePainted[0];
        _facePainted[0] = _facePainted[1];
        _facePainted[1] = _facePainted[2];
        _facePainted[2] = _facePainted[3];
        _facePainted[3] = temp;}
    boolean left_method(boolean cube) {
        boolean temp = _facePainted[0];
        _facePainted[0] = _facePainted[5];
        _facePainted[5] = _facePainted[2];
        _facePainted[2] = _facePainted[4];
        _facePainted[4] = temp;}
    boolean right_method(boolean cube) {
        boolean temp = _facePainted[0];
        _facePainted[0] = _facePainted[4];
        _facePainted[4] = _facePainted[2];
        _facePainted[2] = _facePainted[5];
        _facePainted[5] = temp;}


}
