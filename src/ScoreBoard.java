import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ScoreBoard {

	private static BufferedImage spritesheet;
	private static ImageIcon[] spritesW, spritesR, spritesP, spritesB, spritesO;
	
	ImageIcon getWhiteImage(int i){
		return spritesW[i];
	}
	
	ImageIcon getRedImage(int i){
		return spritesR[i];
	}
	
	ImageIcon getPinkImage(int i){
		return spritesP[i];
	}
	
	ImageIcon getBlueImage(int i){
		return spritesB[i];
	}
	
	ImageIcon getOrangeImage(int i){
		return spritesO[i];
	}
	
	Image ImageResize(Image input){
		
		Image img = input.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		return img;
	}
	
	
	ScoreBoard(){
		
		try {
			spritesheet = ImageIO.read(new File("text.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spritesW = new ImageIcon[44];
		spritesR = new ImageIcon[44];
		spritesP = new ImageIcon[44];
		spritesB = new ImageIcon[44];
		spritesO = new ImageIcon[44];
		int i = 0;
		for( int x = 1; x < 120;i++, x+=8){
			
			spritesW[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,0,7,7)));
			spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,32,7,7)));
			spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,64,7,7)));
			spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,96,7,7)));
			spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,128,7,7)));
		}
		
		for( int x = 1; x < 88;i++, x+=8){
			
			spritesW[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,8,7,7)));
			spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,40,7,7)));
			spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,72,7,7)));
			spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,104,7,7)));
			spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,136,7,7)));
		}
	
		spritesW[i] = new ImageIcon(ImageResize(spritesheet.getSubimage(90, 8, 5, 7)));
		spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(90,40,5,7)));
		spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(90,72,5,7)));
		spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(90,104,5,7)));
		spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(90,136,5,7)));
		i++;
		spritesW[i] = new ImageIcon(ImageResize(spritesheet.getSubimage(96, 8, 7, 7)));
		spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(96,40,7,7)));
		spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(96,72,7,7)));
		spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(96,104,7,7)));
		spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(96,136,7,7)));
		i++;
		spritesW[i] = new ImageIcon(ImageResize(spritesheet.getSubimage(103, 8, 6, 7)));
		spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(103,40,6,7)));
		spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(103,72,6,7)));
		spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(103,104,6,7)));
		spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(103,136,6,7)));
		i++;
		spritesW[i] = new ImageIcon(ImageResize(spritesheet.getSubimage(110, 8, 5, 7)));
		spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(110,40,5,7)));
		spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(110,72,5,7)));
		spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(110,104,5,7)));
		spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(110,136,5,7)));
		i++;
		spritesW[i] = new ImageIcon(ImageResize(spritesheet.getSubimage(116, 8, 5, 7)));
		spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(116,40,5,7)));
		spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(116,72,5,7)));
		spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(116,104,5,7)));
		spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(116,136,5,7)));
		i++;
		for( int x = 1; x < 104;i++, x+=8){
			
			spritesW[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,16,7,7)));
			spritesR[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,48,7,7)));
			spritesP[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,80,7,7)));
			spritesB[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,112,7,7)));
			spritesO[i]= new ImageIcon(ImageResize(spritesheet.getSubimage(x,144,7,7)));
		}
	}
}
