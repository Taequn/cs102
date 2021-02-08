/*
 *
 MovieGraph.java
 COSC 102, Colgate University

 Your code goes here.
 See instructions for explanation of methods.

 */

import java.io.*;
import java.util.*;



public class MovieGraph {
     protected Map<String, Set<String>> foundationMap = new HashMap<>();

     //Helper function, so there would be no MAGIC NUMBERS and MAGIC LOOPS
     public void creatingGraph(Set<String> movies, String[] currentLine){
          for (int i=1; i<currentLine.length; i++) {
               String actorName = currentLine[i];
               String currentMovie = currentLine[0];

               movies.add(actorName);

               if (!foundationMap.containsKey(actorName)){
                    Set<String> aMovie = new HashSet<>();
                    aMovie.add(currentMovie);
                    foundationMap.put(actorName, aMovie);
               }
               else {
                    Set<String> tempMovies = new HashSet<>(foundationMap.get(actorName));
                    tempMovies.add(currentMovie);
                    foundationMap.put(actorName, tempMovies);
               }
          }
     }

     public MovieGraph(Set<String[]> data) {
          Iterator<String[]> iter = data.iterator();
          while(iter.hasNext()) {
               Set<String> movies = new HashSet<>();
               String[] currentLine = iter.next();

               creatingGraph(movies, currentLine);

               foundationMap.put(currentLine[0], movies);
          }
     }


     public ArrayList<String> findShortestPath(String target1, String target2) {
          if (!foundationMap.containsKey(target1) ||
                  !foundationMap.containsKey(target2)){
               System.out.println("Movies Search error; can't find the input in the database");
               System.exit(1);
          }

          if (target1.equals(target2)) {
               System.out.println("That's the same input! What's the point in searching?");
               System.exit(1);
          }

          Queue<String> queue = new LinkedList<>();
          Set<String> visited = new HashSet<>();
          ArrayList<String> shortestPath = new ArrayList<>();


          queue.add(target1);
          // Use dictionary to track the path for 100. c:

          while(!queue.isEmpty()){
               String path = queue.poll();
               String[] topArray = path.split("/");
               String topQueue = topArray[topArray.length-1];

               if(!visited.contains(topQueue)){
                    for(String neighbor:foundationMap.get(topQueue)){
                         visited.add(topQueue);
                         String newPath = path+"/"+neighbor;
                         queue.add(newPath);
                         if(neighbor.equals(target2)) {
                              for (String bits : newPath.split("/"))
                                   shortestPath.add(bits);
                              return shortestPath;
                         }
                    }
               }
          }
          return shortestPath;
     }


}
