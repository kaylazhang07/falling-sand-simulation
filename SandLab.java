/*
Kayla Zhang
Per 3
 */
import java.awt.*;
import java.util.*;

public class SandLab
{
    public static void main(String[] args)
    {
        SandLab lab = new SandLab(120, 80);
        lab.run();
    }

    //add constants for particle types here
    public static final int EMPTY = 0;
    public static final int METAL = 1;
    public static final int SAND = 2;
    public static final int WATER = 3;

    //do not add any more fields
    private int[][] grid;
    private SandDisplay display;

    public SandLab(int numRows, int numCols)
    {
        grid = new int [numRows][numCols];
        String[] names;
        names = new String[4];
        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND]= "Sand";
        names[WATER]= "Water";
        display = new SandDisplay("Falling Sand", numRows, numCols, names);
    }

    //called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, int tool)
    {
        grid [row][col]=tool;
    }

    //copies each element of grid into the display
    public void updateDisplay()
    {
        for (int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[0].length; j++){
                if (grid[i][j]==0){
                    display.setColor(i,j,Color.BLACK);
                }
                else if (grid[i][j]==1){
                    display.setColor(i, j, Color.GRAY);
                }
                else if (grid [i][j]==2){
                    display.setColor(i, j, Color.YELLOW);
                }
                else if (grid[i][j]==3){
                    display.setColor(i, j, Color.BLUE);
                }
            }
        }
    }

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step()
    {
        int xRandom = (int)(Math.random()*grid.length);
        int yRandom = (int)(Math.random()*grid[0].length);

        int direction = (int)(Math.random()*4);

        if (xRandom<grid.length-1&&yRandom<grid[0].length-1) {

            if (grid[xRandom][yRandom] == 2 && (grid[xRandom + 1][yRandom] == 0 || grid[xRandom + 1][yRandom] == 3)) {
                grid[xRandom][yRandom] = 0;
                grid[xRandom + 1][yRandom] = 2;
            }
            if (xRandom<grid.length-1&&yRandom<grid[0].length-1) {
                if (direction == 0) { // down
                    if (xRandom!=grid.length-1&&grid[xRandom][yRandom] == 3 && grid[xRandom + 1][yRandom] == 0) {
                        grid[xRandom][yRandom] = 0;
                        grid[xRandom + 1][yRandom] = 3;

                    }
                } else if (direction == 1) {
                    if (yRandom!=0&&grid[xRandom][yRandom] == 3 && grid[xRandom][yRandom - 1] == 0) {
                        grid[xRandom][yRandom] = 0;
                        grid[xRandom][yRandom - 1] = 3;
                    }
                } else if (direction == 2) {
                    if (yRandom!=grid[0].length&&grid[xRandom][yRandom] == 3 && grid[xRandom][yRandom + 1] == 0) {
                        grid[xRandom][yRandom] = 0;
                        grid[xRandom][yRandom + 1] = 3;
                    }
                }
            }
        }
    }

    //do not modify
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null)  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
        }
    }
}
