import java.awt.Color;

public class AI extends Player
{
  public AI(int givenPlayerNumber, int playerOutOf, String givenDirection,
            Color givenColor, int spawnX, int spawnY, TronGUI givenTronGUI) throws Exception
  {
    super(givenPlayerNumber, playerOutOf, givenDirection,
          givenColor, spawnX, spawnY, givenTronGUI);
  }//constructor

  public String movingTo()
  {
    if(playerNumber == 1)
    {
      if(canGoRight()) return "RIGHT";
      if(canGoUp())    return "UP";
      if(canGoLeft())  return "LEFT";
      if(canGoDown())  return "DOWN";
    }//if
    else if(playerNumber == 2)
    {
      if(canGoDown())  return "DOWN";
      if(canGoRight()) return "RIGHT";
      if(canGoUp())    return "UP";
      if(canGoLeft())  return "LEFT";
    }//else if
    else if(playerNumber == 3)
    {
      if(canGoUp())    return "UP";
      if(canGoDown())  return "DOWN";
      if(canGoRight()) return "RIGHT";
      if(canGoLeft())  return "LEFT";
    }//else if
    else if(playerNumber == 4)
    {
      if(canGoLeft())  return "LEFT";
      if(canGoUp())    return "UP";
      if(canGoRight()) return "RIGHT";
      if(canGoDown())  return "DOWN";
    }//else if
    return "UP";
  }//movingTo

  public boolean canGoLeft()
  {
    return tronGUI.gridIsFree(currentX - 1, currentY);
  }//canGoLeft

  public boolean canGoUp()
  {
    return tronGUI.gridIsFree(currentX, currentY - 1);
  }//canGoRight

  public boolean canGoDown()
  {
    return tronGUI.gridIsFree(currentX, currentY + 1);
  }//canGoDown

  public boolean canGoRight()
  {
    return tronGUI.gridIsFree(currentX + 1, currentY);
  }//canGoRight

  @Override
  public String toString()
  {
    return "AI " + playerNumber;
  }//toString


}//class
