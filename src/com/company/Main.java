package com.company;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String location;
        Maze maze;
        ArrayList<Path> paths;
        Path shortestPath;
        char [][] map;
        Coordinate[] coordinates;
        boolean running = true;

        while (running) {
            try {
                System.out.println("Enter file location");
                location = sc.nextLine();

                //instantiating a maze is essentially what 'runs' the algorithm
                //the first path is built within the first constructor, and
                //subsequent paths occur within the constructors of paths.
                //It will not stop instantiating new paths until it has checked
                //all possible paths.
                maze = new Maze(location);

                //Now to retrieve the paths. Invalid paths have removed themselves from the list
                paths = Path.getPaths();

                if (paths.size() == 0) {
                    throw new NoValidPathException("Unable to find a valid path from 'A' to 'B'");
                }

                //Scanning valid paths for the shortest one
                shortestPath = paths.get(0);
                for (Path p : paths) {
                    if (p.getCount() < shortestPath.getCount())
                        shortestPath = p;
                }

                //Filling in visual representation of shortest path
                map = maze.getMap();
                coordinates = shortestPath.getCoordList();
                for (Coordinate c : coordinates) {
                    if (map[c.getY()][c.getX()] != 'A' && map[c.getY()][c.getX()] != 'B')
                        map[c.getY()][c.getX()] = '@';
                }

                //Displaying result
                System.out.println(maze);
                System.out.println("Shortest path count is: " + shortestPath.getCount());

                //clearing paths list to fix issue where rerunning app keeps shortest previous path
                paths.removeAll(paths);

            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the specified file. \n" +
                        "please check the file name, and ensure it exists within \n" +
                        "this application's working directory. \n");

            } catch (InvalidCharacterException | NoValidPathException e) {
                System.out.println(e.getMessage());
            } catch (OutOfMemoryError e) {
                System.out.println("The program has run out of memory, and is unable to \n" +
                        "process this maze");
                System.gc();
            } catch (AssertionError e){
              System.out.println("System was likely unable to detect Start/Finish points. \n" +
                      "Please ensure your maze contains start point 'A' and finishing point 'B'.");
            } catch (Exception e){
                System.out.println("Something has gone wrong.");
                e.printStackTrace();
            }
            finally {
                //Asking the user if they would like to run the program again
                String input = "";
                while (!input.equals("y") && !input.equals("n")) {
                    System.out.println("Try again? (y/n)");
                    input = sc.nextLine();
                }
                if (input.equals("n"))
                    running=false;
            }
        }
    }
}
