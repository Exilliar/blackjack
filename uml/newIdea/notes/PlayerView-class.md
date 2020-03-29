# Problem
The CLI class needs to know certain things about each player, however it should not be able to interact directly with the player class.

## Things that the CLI class needs to know about each player
1. Their name
2. Their hand
3. Their money
4. Their bet
5. Whether they're playing the round

## Solution
Create a PlayerView class which will take an input of a Player object into it's constructor. It will store only the info that the CLI class needs, and only have getter methods, no setters. It will have an id so that the dealer can know which PlayerView to give to the CLI class when asked for.
```
PlayerView
{
    id : int
    name : String
    hand : Hand
    money : int
    bet : int
    playingRound : boolean

    getID() : int
    getName() : String
    getHand() : Hand
    getMoney() : int
    getBet() : int
    getPlayinground() : boolean

    PlayerView(p : Player, i : int)
}
```

## Possible issue
Everything apart from the name of the player is likely to change during the game, as this class will have no setters, and will not be pulling directly from the player, it will always store old data about the player.

## Solution
Change the PlayerView class to store the Player object, but only have getter methods for the things that the CLI class needs. As it is storing the Player object, the values that it returns will be the current versions of those objects.
```
PlayerView
{
    id : int
    player : Player

    getID() : int
    getName() : String
    getHand() : Hand
    getMoney() : int
    getBet() : int
    getPlayingRound() : boolean

    PlayerView(p : Player, i : int)
}
```

## Possible issue
If the Hand class is returned, then the CLI class will be able to interact with it which means it could add cards to the hand. Not the biggest issue in the world, but still not ideal

## Solution
Split the getHand() method into 2 methods, getCards() and getTotalHandValue(). These will be pulled from the Player object's Hand object, but it will stop the player being able to directly change anything in the backend.
```
PlayerView
{
    id : int
    player : Player

    getID() : int
    getName() : String
    getCards() : Card[]
    getTotalHandValue() : int
    getMoney() : int
    getBet() : int
    getPlayingRound() : boolean

    PlayerView(p : Player, i : int)
}
```

## Possible issue
If an Ace object is returned in the getCards() method then the CLI class could use the switchValue() method in the Ace object to change it's value.

## Solution
In the getCards() method, it should check for if the card is an Ace, and if it is, then create a new instance of Card which is not an Ace, but has the current value of the Ace card

## Possible issue
If a Card object is returned, then the CLI class would need to know what a Card class is. This may not seem like a problem, but if I want this to act as a library then limiting the number of classes that a programmer needs to import would be good.

## Solution
Create 2 new getters to replace getCards. A getCardValues() and a getCardNames() (and possibly a getCardImages() in the future)
```
PlayerView
{
    id : int
    player : Player

    getID() : int
    getName() : String
    getCardNames() : String
    getCardValues() : int[]
    getTotalHandValue() : int
    getMoney() : int
    getBet() : int
    getPlayingRound() : boolean

    PlayerView(p : Player, i : int)
}
```

# Reasons for doing all this
So at this point, I feel it's important to justify why I went through all this for a problem that seems not that important.

While making this program I made the decision that I wanted for the CLI class to be able to be changed to any other class which would allow for interaction between the user and the rest of the objects. For example, I eventually want to add a GUI class which will replace the CLI class, and I did not want to have to change anything apart from adding that class.

In order to do this, I divided the program into a backend and a frontend. The backend is everything apart from the CLI class, and the frontend is the CLI class.

Defining the other classes as a backend, and making the decision that the CLI class should be able to be replaced with any other class, means that I wanted there to be certain protections for the backend. This is so that unexpected behaviour which could break the methods in the backend is not able to occur. I could add those protections to every method that the CLI could possibly interact with, but that would be alot of extra code that would likely never have to be used. So instead I decided that I only wanted the frontend to be able to interact with the Dealer class, then the Dealer class could have those protections. The PlayerView class (and any other view classes that I may make in the future) will not have to have any of these protections, as they cannot edit any data, only show it.