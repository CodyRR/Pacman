import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Points {

	private ImageIcon ghostPoint[], fruits[], fruitPoint[];
	private BufferedImage spritesheet, spritesheet2;
	
	ImageIcon getGhostPoint(int input){
		
		return ghostPoint[input];
	}
	
	ImageIcon getFruits(int input){
		
		return fruits[input];
	}
	ImageIcon getFruitPoint(int input){
		
		return fruitPoint[input];
	}
	
	Image ImageResize(Image input, int ySize){
		
		Image img = input;
		BufferedImage newImage = (BufferedImage) img;
		
		for(int y = 0; y < newImage.getHeight(); y++){
		      for(int x = 0; x < newImage.getWidth(); x++){
		        int p = newImage.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = (p)&0xff;
		        
		        if(r == 0 && g == 0 && b == 0){
		        	p = (0<<24) | (r<<16) | (g<<8) | b;
		        }
		        else{
		        	p = (a<<24) | (r<<16) | (g<<8) | b;
		        }

		        //set new RGB
		        //p = (a<<24) | (0<<16) | (g<<8) | 0;

		        newImage.setRGB(x, y, p);
		      }
		}
		
		Image image = newImage.getScaledInstance(30, ySize, java.awt.Image.SCALE_SMOOTH);
		return image;
	}
	
	public Points(){
		
		try {
			spritesheet = ImageIO.read(new File("pacman.png"));
			spritesheet2 = ImageIO.read(new File("mspacman.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fruits = new ImageIcon[11];
		for(int i = 0, x = 489, x2 = 553; i < 11;i++){
			if(i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 7 || i == 8 || i == 10){
				fruits[i] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, 49, 14, 14), 30));
				x+=16;
			} else{
				fruits[i] = new ImageIcon( ImageResize(spritesheet2.getSubimage(x2, 1, 14, 14),30));
				x2+=16;
				if(i == 3){
					x2+=16;
				}
			}
		}
		
		ghostPoint = new ImageIcon[4];
		ghostPoint[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(456, 133, 15, 7),20));
		ghostPoint[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(472, 133, 15, 7),20));
		ghostPoint[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(488, 133, 15, 7),20));
		ghostPoint[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(504, 133, 16, 7),20));
		
		fruitPoint = new ImageIcon[11];
		fruitPoint[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(458, 148, 13, 7),20));
		fruitPoint[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(472, 148, 15, 7),20));
		fruitPoint[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(488, 148, 15, 7),20));
		fruitPoint[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(504, 148, 15, 7),20));
		fruitPoint[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(504, 148, 15, 7),20));
		fruitPoint[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(520, 148, 18, 7),20));
		fruitPoint[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(518, 164, 20, 7),20));
		fruitPoint[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(518, 164, 20, 7),20));
		fruitPoint[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(518, 180, 20, 7),20));
		fruitPoint[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(518, 196, 20, 7),20));
		fruitPoint[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(518, 196, 20, 7),20));
	}
}
