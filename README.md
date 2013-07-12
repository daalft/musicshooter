# Music Shooter

## What is it?
MusicShooter is a game where you control a vessel (spaceship) and shoot at notes. Each note that is hit produces the 
note's sound. 

## How do I play it?
You control a vessel. You can *navigate* the vessel **up, down, right** and **left** using the arrow keys on the keyboard. 
You can *shoot* by pressing **Space bar** on the keyboard.  
The *speed* of the notes can be regulated using the key **a** on the keyboard to *accelerate* the notes and
**s** to *slow down* the notes.  
Minimum speed is 0 (the notes are not moving at all) and the maximum speed is 3.

## How do I install it?
First of all, you must have Java 1.6 or higher installed. You need Apache Ant installed.  
Get all files from this repository, open _command line_, type `ant jar`  
This should automatically build the project and output a _jar file_ called MusicShooter.jar in the current
directory.  
Run `java -jar MusicShooter.jar` on the _command line_ to start the game.

## Where are the levels?
I planned to include levels with specific notes (possibly forming songs) that also travel in other directions (*e.g. 
downwards or irregular paths*), lives and the possibility of a game over. I haven't gotten around to implementing that
yet. 

## It's not working!!
The game is based on the Java Midi Interface from the Java Sound API. If your computer doesn't support MIDI, it won't
work. Probably.  
If you get other errors trying to play, feel free to contact me.
