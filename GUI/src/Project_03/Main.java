package Project_03;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


class Sqr {
    private int squareXLocation;
    private int squareSize;
    private int squareYLocation = -squareSize;
    private int fallSpeed = 2;
    Random rand = new Random();

    Sqr() {

        generateRandomXLocation();
        generateSquareSize();

    }

    int generateRandomXLocation() {
        return squareXLocation = rand.nextInt(Game.WINDOW_WIDTH - 2*squareSize);
    }


    int generateSquareSize() {
        return squareSize = 40;
    }


    void paint(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(squareXLocation, squareYLocation, squareSize, squareSize);
        squareYLocation += fallSpeed;
    }

    int getSquareXLocation() {
        return squareXLocation;
    }

    int getSquareYLocation() {
        return squareYLocation;
    }

}


class Game extends JPanel implements MouseListener {
    private SqrCreator sc;
    static final int WINDOW_WIDTH = 300;
    static final int WINDOW_HEIGHT = 500;
    static ArrayList<Sqr> squares = new ArrayList<>();


    Game() {
        sc = new SqrCreator();
        sc.start();
        setBackground(Color.DARK_GRAY);

        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        addMouseListener(this);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<Sqr> iterator = squares.iterator();
        while (iterator.hasNext()) {
            Sqr square = iterator.next();
            square.paint(g);
            if (square.getSquareYLocation() >= getHeight()) {
                iterator.remove();

                Main.playerScore =Main.killedSquares * 100 / Main.totalBlocks;
                Main.scoreLabel.setText("Score: " + Main.playerScore + "%");
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Iterator<Sqr> iterator = squares.iterator();
        while (iterator.hasNext()) {
            Sqr square = iterator.next();
            if (e.getX() >= square.getSquareXLocation() && e.getX() <= square.getSquareXLocation() + 40 && e.getY() >= square.getSquareYLocation() && e.getY() <= square.getSquareYLocation() + 40) {
                iterator.remove();
                Main.killedSquares++;
                Main.totalBlocks++;
                Main.playerScore = Main.killedSquares * 100 / Main.totalBlocks;
                Main.scoreLabel.setText("Score: " + Main.playerScore + "%");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


class SqrCreator extends Thread{
    @Override
    public void run(){
        while(Main.totalBlocks<=70){
            Game.squares.add(new Sqr());
            Main.totalBlocks++;
            try {
                sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Main.playerScore > 36) {
            JOptionPane.showMessageDialog(null, "You won! My congratulations!");
            Main.lock = false;
            return;
        }else{
            JOptionPane.showMessageDialog(null, "You lost( Try again! You can do it!");
            Main.lock = false;
        }
    }
}



public class Main extends JFrame {
    public static boolean lock;
    public static Thread mainThread;
    static int killedSquares = 0;
    static int totalBlocks = 0;
    static int playerScore = 0;
    private JPanel score;
    static JLabel scoreLabel;

    Main(){
        lock = true;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        score = new JPanel();
        Border border = BorderFactory.createTitledBorder("Score");
        score.setBorder(border);
        score.setSize(new Dimension(400,100));
        scoreLabel = new JLabel("Score: 0%");
        score.add(scoreLabel);
        add(score, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(400,500));
        add(new Game(), BorderLayout.CENTER);
        pack();
        setTitle("Falling Squares");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();

        mainThread = new Thread(){
            @Override
            public void run(){
                while(lock){
                    main.repaint();

                    try {
                        sleep(7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainThread.start();
    }
}
