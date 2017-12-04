import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Dots {

	private int x,y;
	private BufferedImage spritesheet;
	private ImageIcon[] sprites;
	private int cycle, index, timer;
	private String style, type;
	
	public void movement(){
		
		if(style.equals("arcade")){
			
			if(type.equals("Dot")){
				index = 0;
			} else if(type.equals("PowerDot")){
				
				if(index == 0 && timer == 5){
					index = 1;
					timer = 0;
				} else if (index == 1 && timer == 5){
					index = 0;
					timer = 0;
				} else {
					timer++;
				}
			}
		}
	}
	
	public ImageIcon getDot(){
		return sprites[index];
	}
	
	public int returnXLocation(){
		return x;
	}
	
	public int returnYLocation(){
		return y;
	}
	
	public Dots(int optionStyle, String dotType, Mazesprite mazewall,int inputX, int inputY){
		
		x = inputX;
		y = inputY; 
		
		if(optionStyle ==1 || optionStyle == 2){
		
			switch(optionStyle){
			case 1:
				style = "arcade";
				break;
			case 2:
				style = "SNES/Genesis";
				break;
			default:
				style = "arcade";	
			}
			
			if(dotType.equals("Dot")){
				
				type = "Dot";
				sprites = new ImageIcon[1];
				sprites = mazewall.sendNormalDot();
			} else{
				
				type = "PowerDot";
				sprites = new ImageIcon[2];
				sprites = mazewall.sendPowerDot();
			}
			index = 0;
			cycle = 0;
			timer = 0;
			
			for(int i = 0; i< sprites.length ;i++){
				
			
				sprites[i] = new ImageIcon(((Image) sprites[i].getImage()).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
			}
		}
		
	}
	
}
