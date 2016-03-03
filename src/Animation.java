//T Harvey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    static int direction;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	if(xloc >= (frameWidth-imgWidth) || xloc < 0 || yloc > (frameHeight-imgHeight) || yloc < 0)
    		transition();
    	
    	move(direction);
    	g.drawImage(pics[direction][picNum], xloc, yloc, Color.gray, this);
    	System.out.println(xloc + " " + yloc + " " + direction);
    	
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
    
    public void move(int d) {
    	switch(d) {
    		case 0:
    			xloc+=xIncr;
    			yloc+=yIncr;
    			break;
    		case 1:
    			xloc-=xIncr;
    			yloc+=yIncr;
    			break;
    		case 2:
    			xloc-=xIncr;
    			yloc-=yIncr;
    			break;
    		case 3:
    			xloc+=xIncr;
    			yloc-=yIncr;
    			break;
    	}
    }
    
    public int transition() {
    	direction = (direction + 1) % 7;
    	return direction;
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
    public Animation(){
    	BufferedImage[] img = createImage();
    	pics = new BufferedImage[8][10];
    	for(int j = 0;j < 8;j++) {
    		for(int i = 0; i < frameCount; i++){
    			pics[j][i] = img[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    	}
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage[] createImage(){
    	BufferedImage[] bufferedImage = new BufferedImage[10];
    	try {
    		bufferedImage[0] = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    		
    		bufferedImage[1] = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
    		
    		bufferedImage[2] = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
    		
    		bufferedImage[3] = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
    		
    		bufferedImage[4] = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    		bufferedImage[5] = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
    		bufferedImage[6] = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
    		bufferedImage[7] = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
    		
    		/*bufferedImage[4] = ImageIO.read(new File("images/orc/orc_fire_southeast.png"));
    		bufferedImage[5] = ImageIO.read(new File("images/orc/orc_fire_southwest.png"));
    		bufferedImage[6] = ImageIO.read(new File("images/orc/orc_fire_northwest.png"));
    		bufferedImage[7] = ImageIO.read(new File("images/orc/orc_fire_northeast.png"));*/
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}