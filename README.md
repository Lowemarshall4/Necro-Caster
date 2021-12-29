# Necro-Caster
A 2D RPG made in Java

Necro-Caster is a 2D Role Playing game that I created my sophomore spring semester of college. Inspiration for the gameplay was drawn from
Pokemon style games as you walk around on a 2D tiled map and enter turn based battles to progress your character. In my iteration, you are a 
necromancer that utilizes his unique items and abilities to shift the battles to your favor. 

Necro-Caster's UI was created using a mix of Java's AWT and Swing API's to render the graphics to the screen following a MVC approach
to the design architecture.
The 2D tilemap's were created by utilizing a tile map engine I created that allowed me to render entire multi-layer maps simply
by representing each layer as a matrix of integer ID's. Each ID corresponds to a specific .png that is rendered in place of the ID.
These .png's can be found in the resources folder of the project.

The data used for the creation of many of the objects in the game is fetched from a MYSQL database that I created to allow me to easily add or alter gameplay elements.
The sql file to generate the database table can be found inside java/Database/
The code for the connection to a a database can be found inside java/GUI/Controller starting at line 521

NOTE('S): The software may have versioning issues
        : You will need to create your own MYSQL database and run included sql file through to setup the tables needed to start the game
