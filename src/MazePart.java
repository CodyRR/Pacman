import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class MazePart {

	private ImageIcon mazeSprite;
	private String type;
	private String eatenValid;

	
	ImageIcon getMazeWall(){
		return mazeSprite;
	}
	
	String getType(){
		return type;
	}
	
	String getEatenValid(){
		return eatenValid;
	}
	
	void setEatenValid(String input){
		eatenValid = input;
	}
	
	public MazePart(int input, Mazesprite mazewall){
		
		Image img = (mazewall.getMazeSprite(input)).getImage();
		BufferedImage newImage = (BufferedImage) img;
		
	
		mazeSprite = new ImageIcon(newImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	
		if(input >= 0 && input < 36){
			type = new String("Wall");
			eatenValid = new String("None");
		} else if (input == 36 || input == 42 || input == 43){
			type = new String("Path");
			eatenValid = new String("None");
		} else if (input == 37){
			type = new String("Dot");
			eatenValid = new String("Not");
		} else if (input == 39){
			type = new String("PowerDot");
			eatenValid = new String("Not");
		} else if (input == 40 || input == 41){
			type = new String("Wall");
			eatenValid = new String("None");
		}
	}
	public MazePart(int input, Mazesprite mazewall, Boolean[] paths, int x, int y){
		
		Image img = (mazewall.getMazeSpriteBar(paths[0], paths[1], paths[2], paths[3], input, x, y)).getImage();

		//Image img = (mazewall.getMazeSprite(input)).getImage();
		BufferedImage newImage = (BufferedImage) img;
		
	
		mazeSprite = new ImageIcon(newImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
	
		if(input >= 0 && input < 36){
			type = new String("Wall");
			eatenValid = new String("None");
		} else if (input == 36 || input == 42 || input == 43){
			type = new String("Path");
			eatenValid = new String("None");
		} else if (input == 37){
			type = new String("Dot");
			eatenValid = new String("Not");
		} else if (input == 39){
			type = new String("PowerDot");
			eatenValid = new String("Not");
		} else if (input == 40 || input == 41){
			type = new String("Wall");
			eatenValid = new String("None");
		}
	}
}
