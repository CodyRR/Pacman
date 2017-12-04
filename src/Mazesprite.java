import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Mazesprite {

	private BufferedImage spritesheet;
	private ImageIcon[] mazeSprites;
	private ImageIcon[] mazeSpritesBar;
	private ImageIcon[] normalDot;
	private ImageIcon[] powerDot;
	private String style;
	private Color[][] mazeColors = 
		/*1*/{{new Color(255,0,0,255), new Color(33,33,255,255), new Color(222,222,255,255)},
		/*2*/{new Color(254,38,1,255), new Color(254,185,174,255), new Color(222,222,255,255)},
		/*3*/{new Color(225,225,255,255), new Color(71,185,255,255), new Color(255,252,4,255)},
		/*4*/{new Color(226,226,253,255), new Color(226,160,96,255), new Color(254,38,1,255)},
		/*5*/{new Color(254,185,82,255), new Color(33,51,255,255), new Color(222,222,255,255)},
		/*6*/{new Color(255,252,4,255), new Color(254,185,255,255), new Color( 1,254,255,255)},
		/*7*/{new Color(226,226,253,255), new Color(226,160,96,255), new Color(222,222,255,255)},
		/*8*/{new Color(128,136,136,255), new Color(64,64,64,255), new Color(255,252,4,255)},
		/*9*/{new Color(225,225,255,255), new Color(143,155,11,255), new Color( 29,223,11,255)},
		/*10*/{new Color(128,136,136,255), new Color(24,24,24,255), new Color(12,104,226,255)},
		/*11*/{new Color(1,40,178,255), new Color(0,0,109,255), new Color(222,222,255,255)},
		/*12*/{new Color(254,38,1,255), new Color(254,185,174,255), new Color(254,185,174,255)},
		/*13*/{new Color(255,252,4,255), new Color(254,185,255,255), new Color(222,222,255,255)},
		};
	
	private Color[][] mazewallColor = 
		/*1*/{{new Color(255,0,0,255), new Color(33,33,255,255)},
		/*2*/{new Color(254,38,1,255), new Color(254,185,174,255)},
		/*3*/{new Color(225,225,255,255), new Color(71,185,255,255)},
		/*4*/{new Color(226,226,253,255), new Color(226,160,96,255)},
		/*5*/{new Color(255,252,4,255), new Color(254,185,255,255)},
		/*6*/{new Color(128,136,136,255), new Color(64,64,64,255)},
		/*7*/{new Color(225,225,255,255), new Color(143,155,11,255)},
		/*8*/{new Color(128,136,136,255), new Color(24,24,24,255)},
		/*9*/{new Color(1,40,178,255), new Color(0,0,109,255)},
		};
	
	private Color[] dotColors = 
		{
			/*1*/new Color(222,222,255),
			/*2*/new Color(255,252,4),
			/*3*/new Color(254,38,1),
			/*4*/new Color(1,254,255),
			/*5*/new Color(29,223,11),
			/*6*/new Color(12,104,226),
		};
	
	
	private Color[][] mazeColorSG = 
		/*1 DarkBlue*/{{new Color(7,70,216),new Color(1,40,216),new Color(0,0,109),new Color(0,0,77),new Color(0,0,49),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*2 Pink*/{new Color(217,227,226),new Color(215,149,188),new Color(214,40,179),new Color(213,0,179),new Color(176,0,143),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*3*/{new Color(23,179,226),new Color(12,104,226),new Color(0,0,178),new Color(0,0,142),new Color(0,0,109),
				new Color(217,227,226),new Color(217,231,13),new Color(216,119,13),new Color(215,153,13),new Color(177,84,5), new Color(108,55,2)},
		/*4*/{new Color(216,189,117),new Color(215,116,56),new Color(176,52,4),new Color(141,25,3),new Color(107,0,2),
				new Color(217,227,226),new Color(214,111,188),new Color(213,0,179),new Color(176,0,109),new Color(107,0,77), new Color(76,0,49)},
		/*5*/{new Color(23,179,226),new Color(12,104,226),new Color(0,0,178),new Color(0,0,142),new Color(0,0,109),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*6*/{new Color(217,227,226),new Color(216,186,226),new Color(214,111,188),new Color(214,74,179),new Color(214,40,179),
				new Color(217,227,226),new Color(28,219,226),new Color(23,180,188),new Color(18,144,151),new Color(14,110,117), new Color(10,78,77)},
		/*7*/{new Color(216,189,117),new Color(215,116,56),new Color(176,52,4),new Color(141,25,3),new Color(107,0,2),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*8*/{new Color(143,152,151),new Color(109,117,117),new Color(49,49,49),new Color(49,49,49),new Color(24,24,24),
				new Color(217,227,226),new Color(217,231,13),new Color(216,119,13),new Color(215,153,13),new Color(177,84,5), new Color(108,55,2)},
		/*9*/{new Color(217,227,226),new Color(217,229,117),new Color(143,155,11),new Color(109,119,10),new Color(78,79,2),
				new Color(217,227,226),new Color(145,230,152),new Color(29,223,11),new Color(24,184,11),new Color(15,112,10),new Color(10,80,1)},
		/*10*/{new Color(109,117,117),new Color(77,77,77),new Color(24,24,24),new Color(24,24,24),new Color(0,0,0),
				new Color(217,227,226),new Color(28,219,226),new Color(17,141,226),new Color(12,104,226),new Color(0,35,216),new Color(0,0,142)},
		/*11*/{new Color(0,0,142),new Color(0,0,109),new Color(0,0,49),new Color(0,0,49),new Color(0,0,24),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*12*/{new Color(217,227,226),new Color(215,149,188),new Color(214,40,179),new Color(213,0,179),new Color(176,0,143),
				new Color(217,227,226),new Color(214,111,188),new Color(213,0,179),new Color(176,0,109),new Color(107,0,77), new Color(76,0,49)},
		/*13*/{new Color(217,227,226),new Color(216,186,226),new Color(214,111,188),new Color(214,74,179),new Color(214,40,179),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
		/*14*/{new Color(179,187,226),new Color(177,110,226),new Color(140,0,178),new Color(107,0,143),new Color(76,0,109),
				new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
				};
	
	private Color[][] mazewallColorSG = {
			
	
			/*1 Blue     */{new Color(7,70,216),new Color(1,40,216),new Color(0,0,109),new Color(0,0,77),new Color(0,0,49)},
			/*2 Pink     */{new Color(217,227,226),new Color(215,149,188),new Color(214,40,179),new Color(213,0,179),new Color(176,0,143)},
			/*3 LightBlue*/{new Color(23,179,226),new Color(12,104,226),new Color(0,0,178),new Color(0,0,142),new Color(0,0,109)},
			/*4 Orange   */{new Color(216,189,117),new Color(215,116,56),new Color(176,52,4),new Color(141,25,3),new Color(107,0,2)},
			/*5 LightPink*/{new Color(217,227,226),new Color(216,186,226),new Color(214,111,188),new Color(214,74,179),new Color(214,40,179)},
			/*6 Grey     */{new Color(143,152,151),new Color(109,117,117),new Color(49,49,49),new Color(49,49,49),new Color(24,24,24)},
			/*7 Green    */{new Color(217,227,226),new Color(217,229,117),new Color(143,155,11),new Color(109,119,10),new Color(78,79,2)},
			/*8 Black    */{new Color(109,117,117),new Color(77,77,77),new Color(24,24,24),new Color(24,24,24),new Color(0,0,0)},
			/*9 DarkBlue */{new Color(0,0,142),new Color(0,0,109),new Color(0,0,49),new Color(0,0,49),new Color(0,0,24)},
			/*10 Purple  */{new Color(179,187,226),new Color(177,110,226),new Color(140,0,178),new Color(107,0,143),new Color(76,0,109)},
			
	};
	
	private Color[][] dotColorsSG = 
		{
			/*1 White    */{new Color(217,227,226),new Color(179,188,188),new Color(143,152,151),new Color(109,117,117),new Color(77,77,77), new Color(49,49,49)},
			/*2 Yellow   */{new Color(217,227,226),new Color(217,231,13),new Color(216,119,13),new Color(215,153,13),new Color(177,84,5), new Color(108,55,2)},
			/*3 Pink     */{new Color(217,227,226),new Color(214,111,188),new Color(213,0,179),new Color(176,0,109),new Color(107,0,77), new Color(76,0,49)},
			/*4 Cyan     */{new Color(217,227,226),new Color(28,219,226),new Color(23,180,188),new Color(18,144,151),new Color(14,110,117), new Color(10,78,77)},
			/*5 Green    */{new Color(217,227,226),new Color(145,230,152),new Color(29,223,11),new Color(24,184,11),new Color(15,112,10),new Color(10,80,1)},
			/*6 LightBlue*/{new Color(217,227,226),new Color(28,219,226),new Color(17,141,226),new Color(12,104,226),new Color(0,35,216),new Color(0,0,142)},
		};

	
	ImageIcon getMazeSprite(int input){
		

		if(input == 42 || input == 43 || input == 44 || input == 37 ||input == 39 || input == 45){
			return mazeSprites[36];
		}
		return mazeSprites[input];
	}
	
	ImageIcon getMazeSpriteBar(Boolean goUp, Boolean goDown, Boolean goleft, Boolean goright, int input, int x, int y){
		
		System.out.println(goUp + " " +goDown + " " + goleft + " " + goright + " " + input + " " + x + " " + y);
		int index = 0;
		if(input == 36 || input == 37 || input == 39){
			if(goUp){
				if(goDown){
					if(goleft){
						if(goright){
							index = 4;
						}else{
							index = 5;
						}
					} else{
						if(goright){
							index = 3;
						}else{
							index = 9;
						}
					}
				} else{
					if(goleft){
						if(goright){
							index = 7;
						}else{
							index = 8;
						}
					} else{
						if(goright){
							index = 6;
						}else{
							//not possible
							index = 4;
						}
					}
				}
			}else{
				if(goDown){
					if(goleft){
						if(goright){
							index = 1;
						}else{
							index = 2;
						}
					} else{
						if(goright){
							index = 0;
						}else{
							//not possible
							index = 4;
						}
					}
				} else{
					if(goleft){
						if(goright){
							index = 10;
						}else{
							//not possible
							index = 4;
						}
					} else{
						if(goright){
							//not possible
							index = 4;
						}else{
							//not possible
							index = 4;
						}
					}
				}
			}
		} else if(input == 24){
			index = 14;
		} else if(input == 25){
			index = 12;
		} else if(input == 26){
			index = 20;
		} else if(input == 27){
			index = 18;
		} else if(input == 11 || input == 40){
			if((x>=11 && x<=16) && (y==12)){
				index = 13;
			} else{
				index = 11;
			}
		} else if(input == 10){
			if((x>=11 && x<=16) && (y==16)){
				index = 13;
			} else{
				index = 11;
			}
		} else{
			index = 11;
		}
		
		System.out.println(index);
		return mazeSpritesBar[index];
	}
	
	void changingColor(ImageIcon input, int color, int dotColor, Color background, Boolean isDot){
		

		Image img = input.getImage();
		BufferedImage newImage = (BufferedImage) img;
		
		for(int y = 0; y < newImage.getHeight(); y++){
		      for(int x = 0; x < newImage.getWidth(); x++){
		        int p = newImage.getRGB(x,y);

		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = (p)&0xff;
		        
			    if(isDot && r == background.getRed() && g == background.getGreen() && b == background.getBlue()){

			        p = (0<<24) | (r<<16) | (g<<8) | b;
			        a = (p>>24)&0xff;
			    }
			    else{
		        	p = (a<<24) | (r<<16) | (g<<8) | b;
		        }
			        
		        
		        
		        if(style.equals("arcade")){
			        if(r == 251 && g == 0 && b == 7){
			        	
			        	int red = mazewallColor[color][0].getRed();
			        	int green = mazewallColor[color][0].getGreen();
			        	int blue = mazewallColor[color][0].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 23 && g == 0 && b == 255){
			        	int red = mazewallColor[color][1].getRed();
			        	int green = mazewallColor[color][1].getGreen();
			        	int blue = mazewallColor[color][1].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 214 && g == 213 && b == 255){
			        	int red = dotColors[dotColor].getRed();
			        	int green = dotColors[dotColor].getGreen();
			        	int blue = dotColors[dotColor].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } 
			        else{
			        	
			        	p = (a<<24) | (r<<16) | (g<<8) | b;
			        }
		        } else if(style.equals("SNES/Genesis")){
		        	
		        	if(r == 216 && g == 226 && b == 225){
			        	int red = mazewallColorSG[color][0].getRed();
			        	int green = mazewallColorSG[color][0].getGreen();
			        	int blue = mazewallColorSG[color][0].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 215 && g == 149 && b == 188){
			        	int red = mazewallColorSG[color][1].getRed();
			        	int green = mazewallColorSG[color][1].getGreen();
			        	int blue = mazewallColorSG[color][1].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 214 && g == 40 && b == 179){
			        	int red = mazewallColorSG[color][2].getRed();
			        	int green = mazewallColorSG[color][2].getGreen();
			        	int blue = mazewallColorSG[color][2].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if(r == 213 && g == 0 && b == 179){
			        	int red = mazewallColorSG[color][3].getRed();
			        	int green = mazewallColorSG[color][3].getGreen();
			        	int blue = mazeColorSG[color][3].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 176 && g == 0 && b == 143){
			        	int red = mazewallColorSG[color][4].getRed();
			        	int green = mazewallColorSG[color][4].getGreen();
			        	int blue = mazewallColorSG[color][4].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } 
			        else if (r == 217 && g == 227 && b == 226){
			        	int red = dotColorsSG[dotColor][0].getRed();
			        	int green = dotColorsSG[dotColor][0].getGreen();
			        	int blue = dotColorsSG[dotColor][0].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 179 && g == 188 && b == 188){
			        	int red = dotColorsSG[dotColor][1].getRed();
			        	int green = dotColorsSG[dotColor][1].getGreen();
			        	int blue = dotColorsSG[dotColor][1].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 143 && g == 152 && b == 151){
			        	int red = dotColorsSG[dotColor][2].getRed();
			        	int green = dotColorsSG[dotColor][2].getGreen();
			        	int blue = dotColorsSG[dotColor][2].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 109 && g == 117 && b == 117){
			        	int red = dotColorsSG[dotColor][3].getRed();
			        	int green = dotColorsSG[dotColor][3].getGreen();
			        	int blue = dotColorsSG[dotColor][3].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        } else if (r == 77 && g == 77 && b == 77){
			        	int red = dotColorsSG[dotColor][4].getRed();
			        	int green = dotColorsSG[dotColor][4].getGreen();
			        	int blue = dotColorsSG[dotColor][4].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        }  else if (r == 49 && g == 49 && b == 49){
			        	int red = dotColorsSG[dotColor][5].getRed();
			        	int green = dotColorsSG[dotColor][5].getGreen();
			        	int blue = dotColorsSG[dotColor][5].getBlue();
			        	p = (a<<24) | (red<<16) | (green<<8) | blue;
			        }
		        	
			        else{
			        	
			        	p = (a<<24) | (r<<16) | (g<<8) | b;
			        }
		        }

		        //set new RGB
		        //p = (a<<24) | (0<<16) | (g<<8) | 0;

		        newImage.setRGB(x, y, p);
		      }
		}
		
		input = new ImageIcon(newImage);
	}
	
	public ImageIcon[] sendNormalDot(){
		return normalDot;
	}
	
	public ImageIcon[] sendPowerDot(){
		return powerDot;
	}
	
	public Mazesprite(int optionStyle, int color, int dotColor){
		try {
			spritesheet = ImageIO.read(new File("PacSprites.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int a = 315;
		
		switch(optionStyle){
		case 1:
			style = "arcade";
			a = 315;
			break;
		case 2:
			style = "SNES/Genesis";
			a = 348;
			break;
		case 3:
			style = "barB";
			a = 381;
			break;
		default:
			style = "arcade";	
		}
		
		
		
		mazeSprites = new ImageIcon[42];
		
		if(style.equals("arcade") || style.equals("SNES/Genesis") || style.equals("barB")){
			for(int i = 0, x = 10; i < 13; i++, x = x+9 ){
				if(x == 109 || x == 127 || x == 145){
					x = x+9;
				}
				mazeSprites[i] = new ImageIcon(spritesheet.getSubimage(x, a, 8, 8));
			}
			
			a = a+9;
			
			for(int i = 0, x = 10; i < 15; i++, x = x+9 ){
				if(x == 55){
					x = x+9;
				}
				mazeSprites[i+13] = new ImageIcon(spritesheet.getSubimage(x, a, 8, 8));
			}
			
			a = a+9;
			
			for(int i = 0, x = 10; i < 12; i++, x = x+9 ){
				if(x == 64 || x == 73 || x == 82 || x == 91 ){
					x = 100;
				}
				mazeSprites[i+28] = new ImageIcon(spritesheet.getSubimage(x, a, 8, 8));
	
			}
		
			mazeSprites[40] = new ImageIcon(spritesheet.getSubimage(154, a, 8, 8));
	
			mazeSprites[41] = new ImageIcon(spritesheet.getSubimage(163, a, 8, 8));
			
			try {
				spritesheet = ImageIO.read(new File("PacSprites.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(style.equals("arcade") || style.equals("barB")){
				
				normalDot = new ImageIcon[1];
				powerDot = new ImageIcon[2];
				
				normalDot[0] = new ImageIcon(spritesheet.getSubimage(127, 333, 8, 8));
				powerDot[0] = new ImageIcon(spritesheet.getSubimage(145, 333, 8, 8));
				powerDot[1] = new ImageIcon(spritesheet.getSubimage(118, 333, 8, 8));
				
				
			} else if(style.equals("SNES/Genesis")){
				
				normalDot = new ImageIcon[1];
				powerDot = new ImageIcon[2];
				
				normalDot[0] = new ImageIcon(spritesheet.getSubimage(127, 366, 8, 8));
				powerDot[0] = new ImageIcon(spritesheet.getSubimage(145, 366, 8, 8));
				powerDot[1] = new ImageIcon(spritesheet.getSubimage(118, 366, 8, 8));
			}
			
			for(int i = 0; i < mazeSprites.length; i++){
			
				changingColor(mazeSprites[i],color, dotColor, new Color(0,0,0), false);
			}
			
		} 
		
		if (style.equals("barB")){
			
			mazeSpritesBar = new ImageIcon[16];
			
			
			for(int i = 0, x = 10; i < 16; i++, x = x+9){
				
				mazeSpritesBar[i] = new ImageIcon(spritesheet.getSubimage(x,410, 8, 8));
			}
			
			
		}
		
		
		
		for(int i = 0; i < normalDot.length; i++){
			
			changingColor(normalDot[i],color, dotColor, new Color(0,0,0), true);
		}
		
		for(int i = 0; i < powerDot.length; i++){
			
			changingColor(powerDot[i],color, dotColor, new Color(0,0,0), true);
		}
		/*mazeSprites[0] = new ImageIcon(spritesheet.getSubimage(225, 27, 8, 8));
		mazeSprites[1] = new ImageIcon(spritesheet.getSubimage(234, 27, 8, 8));
		mazeSprites[2] = new ImageIcon(spritesheet.getSubimage(243, 27, 8, 8));
		mazeSprites[3] = new ImageIcon(spritesheet.getSubimage(252, 27, 8, 8));
		mazeSprites[4] = new ImageIcon(spritesheet.getSubimage(261, 27, 8, 8));*/
	}
}
