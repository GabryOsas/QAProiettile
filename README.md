# QAProiettile
Source Code del plugin QAProiettile

# **API DOCS**
## EVENTS
### PlayerInjectEvent
This event is triggered when a player is hit by a gun.
```java
@EventHandler
public void onPlayerInjectEvent(PlayerInjectEvent e){
        e.getPlayer(); //Return Player / attacker
        e.getGun(); //Return GUN
        e.getTarget(); //Return Target
        e.setCancelled(); //Boolean method to cancel the event
}
```
### PlayerReviveTargetEvent
This event is triggered when the player is healed.
```java
@EventHandler
public void onPlayerReviveTargetEvent(PlayerReviveTargetEvent e){
        e.getPlayer(); //Return Player / attacker
        e.getTarget(); //Return Target
        e.setCancelled(); //Boolean method to cancel the event
}
```
## METHODS
### QAProiettileAPI
This class allows you to call different methods.
```java
QAProiettileAPI qaProiettileAPI = new QAProiettileAPI();
qaProiettileAPI.getPlayers(); //This method returns a list of injured players.
qaProiettileAPI.hasFerite(); //This method is boolean request a player and check if the player is hurt.
qaProiettileAPI.addPlayer(); //This method calls for a player and adds the player to the injured players list.
qaProiettileAPI.removePlayer(); //This method claims a player and removes him from the injured players list.
```
