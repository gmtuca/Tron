import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TronGUI extends JFrame implements KeyListener
{
  public int size;
  public JPanel[][] grid;
  public boolean start = false;

  public String ARROWS;
  public String WASD;
  public String YGHJ;
  public String PLXX;

  public Color freeSpaceColor = Color.BLACK;

  public TronGUI(int gridSize, int dimensionX, int dimensionY) throws Exception
  {
    if(gridSize <= 0) throw new IllegalArgumentException("Zero/Negative grid size");
    if(dimensionX <= 0 || dimensionY <= 0) throw new IllegalArgumentException("Zero/Negative grid pixel dimensions");
  
    size = gridSize;
    grid = new JPanel[size][size];
    addKeyListener(this);
    setTitle("Tron");
    Container contents = getContentPane();
    contents.setLayout(new GridLayout(size , size));
    contents.setPreferredSize(new Dimension(dimensionX, dimensionY));

    for(int y = 0; y < size; y++)
      for(int x = 0; x < size; x++)
      {
        grid[y][x] = new JPanel(new BorderLayout());
        grid[y][x].setSize(30,30);
        grid[y][x].setBackground(freeSpaceColor);
      }//for

   for(int y = 0; y < size; y++)
     for(int x = 0; x < size; x++)
       contents.add(grid[y][x]);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }//constructor

  public void setMotorcycle(int x, int y, Color color)  {
    grid[y][x].setBackground(color);
  }//setMotorcycle

  public void clearAll()
  {
    for(int y = 0; y < size; y++)
      for(int x = 0; x < size; x++)
        grid[y][x].setBackground(freeSpaceColor);

    ARROWS = "";
    WASD   = "";
    YGHJ   = "";
    PLXX   = "";
  }//clearAll

  public boolean gridIsFree(int x, int y)  {
    if(y < 0 || x < 0 || y >= size || x >= size)
      return false;

    return grid[y][x].getBackground() == freeSpaceColor;
  }//gridIsFree

  public void keyTyped(KeyEvent keyEvent) {
    //System.out.println("KEY TYPED");
  }//keyTyped

  public void keyPressed(KeyEvent keyEvent) {
    if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
      start = !start;
    else 
    {
      if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
        System.exit(0);

      if(keyEvent.getKeyCode() == KeyEvent.VK_UP)
        ARROWS = "^";
      if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN)
        ARROWS = "v";
      if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
        ARROWS = "<";
      if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        ARROWS = ">";

      if(keyEvent.getKeyCode() == KeyEvent.VK_W)
        WASD = "w";
      if(keyEvent.getKeyCode() == KeyEvent.VK_A)
        WASD = "a";
      if(keyEvent.getKeyCode() == KeyEvent.VK_S)
        WASD = "s";
      if(keyEvent.getKeyCode() == KeyEvent.VK_D)
        WASD = "d";

      if(keyEvent.getKeyCode() == KeyEvent.VK_Y)
        YGHJ = "y";
      if(keyEvent.getKeyCode() == KeyEvent.VK_G)
        YGHJ = "g";
      if(keyEvent.getKeyCode() == KeyEvent.VK_H)
        YGHJ = "h";
      if(keyEvent.getKeyCode() == KeyEvent.VK_J)
        YGHJ = "j";

      if(keyEvent.getKeyCode() == KeyEvent.VK_P)
        PLXX = "p";
      if(keyEvent.getKeyCode() == KeyEvent.VK_L)
        PLXX = "l";
      if(keyEvent.getKeyCode() == KeyEvent.VK_SEMICOLON)
        PLXX = ";";
      if(keyEvent.getKeyCode() == KeyEvent.VK_QUOTE)
        PLXX = "'";
    }//else
  }//keyPressed

  public void keyReleased(KeyEvent keyEvent) {
    //System.out.print("KEY RELEASED");
  }
}//class
