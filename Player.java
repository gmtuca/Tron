import java.awt.Color;

public class Player
{
  public TronGUI tronGUI;
  public int playerNumber;
  public Color playerColor;
  public int currentX;
  public int currentY;
  public String currentDirection;
  public boolean dead = false;
  public int score;

  public Player(int givenPlayerNumber, int playerOutOf, String givenDirection,
                Color givenColor, int spawnX, int spawnY, TronGUI givenTronGUI) throws Exception
  {
    tronGUI = givenTronGUI;
    playerNumber = givenPlayerNumber;
    playerColor = givenColor;
    currentDirection = givenDirection;
    currentX = spawnX;
    currentY = spawnY;
    score = 0;

    tronGUI.setMotorcycle(currentX, currentY, playerColor);
  }//constructor

  public void respawn(int x, int y, String givenDirection)
  {
    dead = false;
    currentX = x;
    currentY = y;
    currentDirection = givenDirection;

    tronGUI.setMotorcycle(x, y, playerColor);
  }//teleport

  public void move(String direction)
  {
    if(backOnItself(direction))
      move(currentDirection);
    else
    {
      int moveX = 0, moveY = 0;

      if      (direction == "UP")     { moveX =  0; moveY = -1; }
      else if (direction == "DOWN")   { moveX =  0; moveY =  1; }
      else if (direction == "RIGHT")  { moveX =  1; moveY =  0; }
      else if (direction == "LEFT")   { moveX = -1; moveY =  0; }

      currentDirection = direction;
      currentX += moveX;
      currentY += moveY;

      if(currentX < 0 || currentY < 0 ||
         currentX > tronGUI.size - 1  ||
         currentY > tronGUI.size - 1  || !tronGUI.gridIsFree(currentX, currentY))
        kill();
      else
        tronGUI.setMotorcycle(currentX,currentY, playerColor);
    }//else
  }//moveTo

  public void kill()
  {
    dead = true;
  }//kill

  public boolean backOnItself(String direction)
  {
    return((currentDirection == "UP" && direction == "DOWN") ||
           (currentDirection == "DOWN" && direction == "UP") ||
           (currentDirection == "RIGHT" && direction == "LEFT") ||
           (currentDirection == "LEFT" && direction == "RIGHT"));
  }//backOnItself

  public void addToScore(int i)
  {
    score += i;
  }//addScore
  
  public void resetScore()
  {
    score = 0;
  }//resetScore

  @Override
  public String toString()
  {
    return "Player " + playerNumber;
  }//toString

}//class
