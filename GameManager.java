import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Set;

public class GameManager
{
  int n = 0;
  int playersSoFar = 0;
  int AIsSoFar = 0;
  int numberOfRealPlayers, numberOfAIs;
  int total;
  TronGUI tronGUI;
  Player[] players;
  Set<Player> playersAlive = new HashSet<Player>();
  int delay;

  public GameManager(TronGUI givenTronGUI, int realPlayers, int AIs, int speedDelay) throws Exception
  {
    if(realPlayers < 0 || AIs < 0) throw new IllegalArgumentException(
                     "Negative number of Players (" + realPlayers + ") or AIs (" + AIs + ")");

    total = realPlayers + AIs;

    if(total > 4) throw new ArrayIndexOutOfBoundsException("Too many Players or AIs: " + total + "/ 4");
    if(total == 0) throw new IllegalArgumentException("Zero number of Players/AIs");

    if(speedDelay <= 0) throw new IllegalArgumentException("Zero/negative speed delay: " + speedDelay);
    if(givenTronGUI == null) throw new NullPointerException("Invalid (null) interface");

    numberOfRealPlayers = realPlayers;
    numberOfAIs = AIs;
    players = new Player[total];
    tronGUI = givenTronGUI;
    delay = speedDelay;

    while(addPlayer()){};
    while(addAI()){}

    startGame();
  }//constructor

  public void startGame() throws Exception
  {
    for(Player player : players)
      playersAlive.add(player);

    while(!tronGUI.start)
      Thread.currentThread().sleep(100);

    while(true)
    {
      if(gg()) break;

      if(tronGUI.start)
        for(int i = 1; i <= total; i++)
          if(getPlayer(i) != null && !getPlayer(i).dead)
            getPlayer(i).move(movementConversion(i));
          else 
            playersAlive.remove(getPlayer(i));

      Thread.currentThread().sleep(delay);
    }//while

    restart();
  }//startGame

  public void restart() throws Exception
  {
    tronGUI.clearAll();
    tronGUI.start = false;

    for(int p = 1; p <= total; p++)
      getPlayer(p).respawn(getSpawnX(p), getSpawnY(p), getInitialDirection(p));

    startGame();
  }//restart

  public boolean addPlayer() throws Exception
  {
    if(playersSoFar == numberOfRealPlayers)
      return false;

    playersSoFar++;
    n++;

    players[n - 1] = new Player(n, total, getInitialDirection(n), getColor(n),
                                getSpawnX(n), getSpawnY(n), tronGUI);
    return true;
  }//add

  public boolean addAI() throws Exception
  {
    if(AIsSoFar == numberOfAIs)
      return false;

    AIsSoFar++;
    n++;

    players[n - 1] = new AI(n, total, getInitialDirection(n), getColor(n),
                            getSpawnX(n), getSpawnY(n), tronGUI);
    return true;
  }//add

  public boolean gg()
  {
    if(playersAlive.size() == 0)
    {
      JOptionPane.showMessageDialog(new JFrame(){}, "Draw!" + scoreScreen());
      return true;
    }//if
    if(playersAlive.size() == 1)
    {
      Player winner = playersAlive.iterator().next();
      winner.addToScore(1);

      JOptionPane.showMessageDialog(new JFrame(){}, winner + " wins!" + scoreScreen());
      return true;
    }//if
    else
      return false;
  }//allDead

  public String scoreScreen()
  {
    String scoreScreen = "\n\n Score \n";
    for(Player player : players)
      scoreScreen += player + ": " + player.score + "\n";

    return scoreScreen;     
  }//scoreScreen

  public String movementConversion(int playerNumber)
  {
    if(getPlayer(playerNumber) instanceof AI)
      return ((AI)getPlayer(playerNumber)).movingTo();

    if(playerNumber == 1)
    {
      if     (tronGUI.ARROWS == "^") return "UP";
      else if(tronGUI.ARROWS == "<") return "LEFT";
      else if(tronGUI.ARROWS == "v") return "DOWN";
      else if(tronGUI.ARROWS == ">") return "RIGHT";
      else  return getPlayer(1).currentDirection;
    }//if
    else if(playerNumber == 2)
    {
      if     (tronGUI.WASD == "w") return "UP";
      else if(tronGUI.WASD == "a") return "LEFT";
      else if(tronGUI.WASD == "s") return "DOWN";
      else if(tronGUI.WASD == "d") return "RIGHT";
      else  return getPlayer(2).currentDirection;
    }//if
    else if(playerNumber == 3)
    {
      if     (tronGUI.YGHJ == "y") return "UP";
      else if(tronGUI.YGHJ == "g") return "LEFT";
      else if(tronGUI.YGHJ == "h") return "DOWN";
      else if(tronGUI.YGHJ == "j") return "RIGHT";
      else  return getPlayer(3).currentDirection;
    }//if
    else if(playerNumber == 4)
    {
      if     (tronGUI.PLXX == "p") return "UP";
      else if(tronGUI.PLXX == "l") return "LEFT";
      else if(tronGUI.PLXX == ";") return "DOWN";
      else if(tronGUI.PLXX == "'") return "RIGHT";
      else  return getPlayer(4).currentDirection;
    }//if

    return "UP";
  }//movementConversion

  public String getInitialDirection(int playerNumber)
  {
    if(playerNumber == 1) 
      return "RIGHT";
    
    if(playerNumber == 2)
      if(total == 2)
        return "LEFT";
      else if(total == 3)
        return "UP";
      else if(total == 4)
        return "DOWN";

    if(playerNumber == 3)
      if(total == 3)
        return "LEFT";
      else if(total == 4)
        return "UP";

    if(playerNumber == 4)
      return "LEFT";

    return "RIGHT";
  }//getInitialDirection

  public Color getColor(int playerNumber)
  {
    if(playerNumber == 1) 
      return Color.BLUE;
    
    if(playerNumber == 2)
      return Color.RED;

    if(playerNumber == 3)
      return Color.GREEN;

    if(playerNumber == 4)
      return Color.YELLOW;

    return Color.WHITE;
  }//getColor

  public int getSpawnX(int playerNumber)
  {
    int gridSize = tronGUI.size;
 
    if(total == 1)
      return gridSize / 2;
    else if(total == 2)
    {
      if(playerNumber == 1)
        return gridSize / 4;
      else
        return 3 * gridSize / 4;
    }//else if
    else if(total == 3)
    {
      if(playerNumber == 1)
        return gridSize / 2;
      else if(playerNumber == 2)
        return gridSize / 4;
      else if(playerNumber == 3)
        return 3 * gridSize / 4;
    }//else if
    else if(total == 4)
    {
      if(playerNumber == 1 || playerNumber == 3)
        return gridSize / 4;
      else if(playerNumber == 2 || playerNumber == 4)
        return 3 * gridSize / 4;
    }//else if

    return 0;
  }//getSpawnX

  public int getSpawnY(int playerNumber)
  {
    int gridSize = tronGUI.size;

    if(total == 1 || total == 2)
      return gridSize / 2;
    else if(total == 3)
    {
      if(playerNumber == 1)
        return gridSize / 4;
      else if(playerNumber == 2 || playerNumber == 3)
        return 3 * gridSize / 4; 
    }//else if
    else if(total == 4)
    {
      if(playerNumber == 1 || playerNumber == 2)
        return gridSize / 4;
      else if(playerNumber == 3 || playerNumber == 4)
        return 3 * gridSize / 4;
    }//else if

    return 0;
  }//getSpawnY

  public Player getPlayer(int playerNumber)
  {
    return players[playerNumber - 1];
  }//getPlayer

}//GameManager
