package com.example.helloworld;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by Ikari Shinji on 4/7/2015.
 */
public class HelloWorld {

    private static boolean butter;
    private static int count;

    public static void main(String[] args) {

        String[][] grid;
        int columns = 24;
        int rows = 8;

        grid = new String[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (j==0 || j == columns - 1 || i == 0 || i == rows - 1) {
                    grid[j][i] = ".";
                } else {
                    grid[j][i] = ".";
                }

            }

        }

        //sanity check
        //pick direction
        //pick position
        //check for availability
        //place word
        //intersection maker method

        ArrayList<Point> gridPos = new ArrayList<Point>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gridPos.add(new Point(i, j));
            }

        }






        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (j == columns - 1) {
                    System.out.println(grid[j][i]);
                } else {
                    System.out.print(grid[j][i]);
                }






            }

        }

        /*

        String curr = null;
        try {
            curr = new Scanner(new File("wordlist.txt")).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] splitWord = curr.split("(?!^)");
        for (int i = 0; i < splitWord.length; i++) {
            System.out.println(splitWord[i]);
        }

        Point myPoint = randPos(columns - splitWord.length, rows);

        for (int i = 0; i < splitWord.length; i++) {
            grid[myPoint.x + i][myPoint.y] = curr.charAt(i);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (j == columns - 1) {
                    System.out.println(grid[j][i]);
                } else {
                    System.out.print(grid[j][i]);
                }






            }

        }*/



        int leftLimit = 65; // letter 'a'
        int rightLimit = 90; // letter 'z'
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (new Random().nextFloat() * (rightLimit - leftLimit));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);

        //String input = "1 fish 2 fish red fish blue fish";
        Scanner s = null;
        try {
            s = new Scanner(new File("wordlist.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (s.hasNext()) {
            String fitword = s.next();
            String[] splitWord = fitword.split("(?!^)");

            Random rand = new Random();
            int direction  = rand.nextInt(7);

            Point thisPoint = new Point();

            if (direction == 0) thisPoint = randPos(columns - splitWord.length, rows);
            if (direction == 1) thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
            if (direction == 2) thisPoint = randPos(columns, rows - splitWord.length);
            if (direction == 3) {
                thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
                thisPoint.x = thisPoint.x + splitWord.length;
            }
            if (direction == 4) {
                thisPoint = randPos(columns - splitWord.length, rows);
                thisPoint.x = thisPoint.x + splitWord.length;
            }
            if (direction == 5) {
                thisPoint = randPos(columns - splitWord.length,  rows - splitWord.length);
                thisPoint.x = thisPoint.x + splitWord.length;
                thisPoint.y = thisPoint.y + splitWord.length;
            }
            if (direction == 6) {
                thisPoint = randPos(columns, rows - splitWord.length);
                thisPoint.y = thisPoint.y + splitWord.length;
            }
            if (direction == 7) {
                thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
                thisPoint.y = thisPoint.y + splitWord.length;
            }


            System.out.println();
            System.out.print("(" + thisPoint.x + ", ");
            System.out.print(thisPoint.y + ")");
            System.out.println();
            System.out.println("direction is: " + direction);
            System.out.println();
/*
            if(fitString(grid, fitword, thisPoint)) {
                for (int i = 0; i < splitWord.length; i++) {
                    grid[thisPoint.x + i][thisPoint.y] = splitWord[i];
                }
            } */

            if (splitWord.length <= columns && splitWord.length <= rows) {

                while (!fitString(grid, fitword, thisPoint, direction)){
                    if (direction == 0) thisPoint = randPos(columns - splitWord.length, rows);
                    if (direction == 1) thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
                    if (direction == 2) thisPoint = randPos(columns, rows - splitWord.length);
                    if (direction == 3) {
                        thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
                        thisPoint.x = thisPoint.x + splitWord.length;
                    }
                    if (direction == 4) {
                        thisPoint = randPos(columns - splitWord.length, rows);
                        thisPoint.x = thisPoint.x + splitWord.length;
                    }
                    if (direction == 5) {
                        thisPoint = randPos(columns - splitWord.length,  rows - splitWord.length);
                        thisPoint.x = thisPoint.x + splitWord.length;
                        thisPoint.y = thisPoint.y + splitWord.length;
                    }
                    if (direction == 6) {
                        thisPoint = randPos(columns, rows - splitWord.length);
                        thisPoint.y = thisPoint.y + splitWord.length;
                    }
                    if (direction == 7) {
                        thisPoint = randPos(columns - splitWord.length, rows - splitWord.length);
                        thisPoint.y = thisPoint.y + splitWord.length;
                    }
                    System.out.println();
                    System.out.print("(" + thisPoint.x + ", ");
                    System.out.print(thisPoint.y + ")");
                    System.out.println("direction is: " + direction);
                    System.out.println();
                }

                for (int i = 0; i < splitWord.length; i++) {
                    grid[thisPoint.x][thisPoint.y] = splitWord[i];
                    if (direction == 0) thisPoint = Right(thisPoint);
                    if (direction == 1) thisPoint = RightDown(thisPoint);
                    if (direction == 2) thisPoint = Down(thisPoint);
                    if (direction == 3) thisPoint = LeftDown(thisPoint);
                    if (direction == 4) thisPoint = Left(thisPoint);
                    if (direction == 5) thisPoint = LeftUp(thisPoint);
                    if (direction == 6) thisPoint = Up(thisPoint);
                    if (direction == 7) thisPoint = RightUp(thisPoint);
                }


                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {

                        if (j == columns - 1) {
                            System.out.println(grid[j][i]);
                        } else {
                            System.out.print(grid[j][i]);
                        }







                    }

                }
                System.out.println();


            }

        }


        s.close();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (grid[j][i] == ".") {
                    int randomLimitedInt = leftLimit + (int)
                            (new Random().nextFloat() * (rightLimit - leftLimit));

                    grid[j][i] = String.valueOf((char) randomLimitedInt);
                }

            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (j == columns - 1) {
                    System.out.println(grid[j][i].toUpperCase());
                    System.out.println();
                } else {
                    System.out.print(grid[j][i].toUpperCase() + "  ");
                }
            }
        }




        if (butter) System.out.println("what the fudge!");

        /*
        List<String> tester = new ArrayList<String>();

        for (int i = 0; i < 6; i++) {
            tester.add(String.valueOf(i));
        }
        System.out.println(tester.get(0));
        tester.remove(0);
        System.out.println(tester.get(0));
        */


        /*for (int i = 0; i < 65535; i++) {

            buffer.delete(0,buffer.length());
            buffer.append(i + " = " + (char)i);
            String keylist = buffer.toString();
            System.out.println(keylist);



        }*/

    }

    static Point randPos(int rows, int columns) {

        Random rand = new Random();
        int x = rand.nextInt(rows);
        int y = rand.nextInt(columns);

        Point randpoint = new Point(x, y);

        return randpoint;

    }

    static boolean fitString (String[][] grid, String fitword, Point currIns, int direction) {



        String[] splitWord = fitword.split("(?!^)");
        List<String> wordList = new ArrayList<String>();

        for (int i = 0; i < splitWord.length; i++) {
            wordList.add(splitWord[i]);
        }

        System.out.println(wordList.size());

        Point pstor = new Point(currIns.x, currIns.y);

        if(butter || grid[currIns.x][currIns.y] != "." && !wordList.get(0).equalsIgnoreCase(grid[currIns.x][currIns.y])) {

            System.out.println("Placement Overlap Error, Retrying");

            butter = false;
            return false;

        } else if (wordList.get(0).equalsIgnoreCase(grid[currIns.x][currIns.y]) || grid[currIns.x][currIns.y] == ".") {

            count++;
            //grid[currIns.x][currIns.y] = wordList.get(0);
            wordList.remove(0);
            if (wordList.size() == 0) {
                return true;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < wordList.size(); i++) {
                sb.append(wordList.get(i));
            }
            String nextString = sb.toString();
            if (direction == 0) pstor = Right(pstor);
            if (direction == 1) pstor = RightDown(pstor);
            if (direction == 2) pstor = Down(pstor);
            if (direction == 3) pstor = LeftDown(pstor);
            if (direction == 4) pstor = Left(pstor);
            if (direction == 5) pstor = LeftUp(pstor);
            if (direction == 6) pstor = Up(pstor);
            if (direction == 7) pstor = RightUp(pstor);
            if(fitString(grid, nextString, new Point(pstor.x, pstor.y), direction)) {
                return true;
            } else {
                return false;
            }

        }
        butter = false;
        System.out.println("go fuck yourself");
        return false;


    }

    public static Boolean ContainsAllNulls(List<String> arrList)
    {
        if(arrList != null)
        {
            for(Object a : arrList)
                if(a != null) return false;
        }

        return true;
    }

    public static Point Right(Point init) {
        Point rpoint = new Point (init.x + 1, init.y);
        return rpoint;
    }

    public static Point RightDown (Point init) {
        Point rpoint = new Point (init.x + 1, init.y + 1);
        return rpoint;
    }

    public static Point Down(Point init) {
        Point rpoint = new Point (init.x, init.y + 1);
        return rpoint;
    }

    public static Point LeftDown(Point init) {
        Point rpoint = new Point (init.x - 1, init.y + 1);
        return rpoint;
    }

    public static Point Left(Point init) {
        Point rpoint = new Point (init.x - 1, init.y);
        return rpoint;
    }

    public static Point LeftUp(Point init) {
        Point rpoint = new Point (init.x - 1, init.y - 1);
        return rpoint;
    }

    public static Point Up(Point init) {
        Point rpoint = new Point (init.x, init.y - 1);
        return rpoint;
    }

    public static Point RightUp (Point init) {
        Point rpoint = new Point (init.x + 1, init.y - 1);
        return rpoint;
    }




}
