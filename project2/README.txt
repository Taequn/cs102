README

COSC 102
Project 2
Kirill Cherniakov

Colgate Quad Adventures

In this Project 2, I worked on the scrolling game that follows the most basic rules. You're playing as a Colgate university student (pay attention to his jacket!), and you need to collect alcohol and avoid homework, professors, dogs and police.

In my creative version, I added several important features that differ from the basic game:
-- Different assets
I used arrays and random number generators to populate the grid with different assets.

-- Background scrolling
I used grid measurements to create 20 images of the background that scrolls as algorithms populate the grid. This creates an illusion as if a player is moving through the map.

-- Increasing difficulty
As player accumulates more points, the game slowly increases its tick rate. So if they actually want to win, they'll need to do their best while facing true challenge. I made it within regulateUI method.

-- Implemented basic UI
I limited the first two rows of the grid from player movement and assets generation. Then, I designed UI and implemented it in that space. When the game starts, the first method spawns three hearts. Then, as players take hits, regulateUI method removes hearts. The same method is responsible for putting stars into the UI. I wanted to show the progression of the players, so every 100 points, the players are given a star.

-- Win/Lose conditions.
If a player loses their 3 hearts, the game is over. The moment the player collects 3 stars and earns 250 points, the victory is theirs. Each result has a unique splash screen.

-- Turning of the player asset
I felt that it can't be right that a player goes backward without turning, so every time the player presses the left arrow button, I mirror their asset.

Hopefully, you will have fun playing my game!