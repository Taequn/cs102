README

COSC 102
Project 3
Kirill Cherniakov

Epic Movie Search!

Over the course of this project, I managed to implement a graph data structure (I used a map of Strings (as keys) and Set of Strings (as values)). Then, I implemented BFS through that data structure. 

Essentially, algorithm follows the following steps:
1) Checks the starting actor/movie and add its neighbours to the queue.
2) Marks the starting node as explored.
3) Polls the first node from the queue
4) Checks if that node has already been visited.
5) If not, goes through its neighbors and adds them into the queue.
6) Marks that node as explored.
7) Loop through steps 3 to 6 until it finds the shortest path between targets.

I also did a bit of optimization that was not initially outlined. I made sure that the code throws errors when a user tries to look for something that's not in our database. In addition, when target1 equals to target2, the program would say that the user is looking for the same thing, and it would take 0 links to reach the target.

Hopefully, you enjoy reading my code as much as I enjoyed writing it!

