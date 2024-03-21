# Mineralz Evolution 

# Mineralz Evolution as a Minecraft Plugin Game (outdated project from 2014) 
## Game Idee
The Game Idee is from the StarCraft2-Mineralz-Evolution Arcade Mod.

## Game Description:
As with the old Arcade ME (SC2) mod, you have several building types. One is the main base to be able to build further buildings.
## Short Game Description:
A short game description if you don't know Mineralz Evolution from SC2. 

The game is about stopping enemies with your base. 
There is the tower, which is supposed to destroy and stop the enemies. The tower regularly takes damage from the enemy and needs to be healed, but in order for the healing building to heal it needs electricity and that's what the generator is for.

On the other hand, we have the minerals that are needed to improve the buildings because the enemies are also getting stronger and stronger. Now you as the player have to decide whether you should improve the minerals so that you get more or whether you should improve the buildings first. The aim is to survive as long as possible.

### Building Types 
- Base 
   * This is the main base from which everything in the game is controlled. Build other building types and upgrades. 
- Tower
   * The Tower is only intended for defense, because evil animals (in Minecraft Zoombies) come regularly and they are getting stronger and stronger. 
- Healer
   * The Healer Healt the Tower and need Power.
- Generator
  * The Generator generate Power / electricity

All Build Types see here: 
https://github.com/blackmutzi/bukkit-plugin-game-mineralz-evolution/tree/master/src/com/plugins/mutzii/build

### Mineralz the Resource
There are 4 types of minerals. Blue, red, green and purple. The minerals are made of wool.

### Minecraft specification
- AdminTool
  * The AdminTool (Material.BLAZE_ROD) is there to define the playing field and the starting point of the enemy and in which direction they should run. 

### Minecraft Commands:
- /game debug 
  * for cheating Minearalz
- /game join 
  * Joining the Game 
- /game leave
  * Leaving the Game 
- /game restart
  * Restarting the Game 
- /game send [-r,-g,-b,-m] [player-name] [amount]
  * Send to another Player Mineralz. Example: /game send -r Max 200 
- /game status
  * Show Player Stats

All Commands see here: https://github.com/blackmutzi/bukkit-plugin-game-mineralz-evolution/tree/master/src/com/plugins/mutzii/commandmanager

## Programming Tools
- depend: Java Development Kit (JDK)
- depend: bukkit server [Get Bukkit](https://getbukkit.org/)
- IDE: IntelliJ IDEA


