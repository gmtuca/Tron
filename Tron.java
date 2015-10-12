public class Tron
{
  public static void main(String[] args) throws Exception
  {
    int numberOfRealPlayers = 2;
    int numberOfAIs = 0;
    int pixels = 700;
    int squares = 50;
    int speedDelay = 30;
 
    // PLAY $players $ais $gridSize $windowSize $speedDelay
    // to run the game

    if(args.length >= 1)
      numberOfRealPlayers = Integer.parseInt(args[0]);
    if(args.length >= 2)
      numberOfAIs = Integer.parseInt(args[1]);
    if(args.length >= 3)
      squares = Integer.parseInt(args[2]);
    if(args.length >= 4)
      pixels = Integer.parseInt(args[3]);
    if(args.length == 5)
      speedDelay = Integer.parseInt(args[4]);

    try
    {
      TronGUI tronGUI = new TronGUI(squares, pixels, pixels);

      GameManager gameManager = new GameManager(tronGUI, numberOfRealPlayers,
                                                numberOfAIs,speedDelay);
    }//try
    catch(Exception ex)
    {
      System.err.println(ex);
    }//catch
  }//main
}//class
