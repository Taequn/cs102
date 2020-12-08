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

     //Constructor
     //Gets passed all of the data from the read in file
     //in the form of a Set of String arrays.
     //Each string array represents one line of the source data
     //split on the forward slashes '/'.
     public MovieGraph(Set<String[]> data)
     {
          //Iterator<String[]> it = data.iterator();
          //while(it.hasNext()) {
               //System.out.println(Arrays.toString(it.next()));
          //     System.out.println(it.next()[0]);
          //}



          Iterator<String[]> iter = data.iterator();
          while(iter.hasNext()) {
               Set<String> movies = new HashSet<>();
               String[] currentLine = iter.next();

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

               foundationMap.put(currentLine[0], movies);
          }
          //debug code, remove once you have completed your implementation!
          System.out.println("Read in " + data.size() + " rows of data!");
          //How will you iterate over this data?
          //Check the Java API!

     }


     //Returns an ArrayList of Strings which is the shortest path of movies/actors between
     //target1 and target2.
     public List<String> getUnvisitedNeighbors(Set<String> neighbors){
          List<String> answer = new ArrayList<>();
          Iterator<String> iter = neighbors.iterator();
          while(iter.hasNext())
               answer.add(iter.next());

          return answer;
     }


     public ArrayList<String> findShortestPath(String target1, String target2) {
          Queue<String> queue = new LinkedList<>();
          Set<String> visited = new HashSet<>();
          ArrayList<String> shortestPath = new ArrayList<>();

          queue.add(target1);

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
          return null;  //placeholder
     }


}
