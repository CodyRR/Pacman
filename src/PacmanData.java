import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class PacmanData {

	private int index, cycle;
	private char direction;
	private String status, idPlayer, style;
	private ImageIcon sprites[], options[];
	private BufferedImage spritesheet, spritesheet2;
	private Point location, startLocation;
	private Rectangle truePac, leftPac, rightPac, upPac, downPac;
	private Boolean left, right, up, down;
	
	//457
	void setIndex(int input){
		index = input;
	}
	
	int getIndex(){
		return index;
	}
	
	void setStatus(String input){
		status = input;
	}
	
	String getStatus(){
		return status;
	}
	
	String getIdPlayer(){
		return idPlayer;
	}
	
	void setDirection( char input){
		direction = input;
	}
	char getDirection(){
		return direction;
	}
	ImageIcon getImage(){
		
		return sprites[index];
	}
	
	ImageIcon getLiveImage(){
		return sprites[5];
	}
	
	ImageIcon getOptions(int num){
		return options[num];
	}
	
	Rectangle getTrue(){
		return truePac;
	}
	
	Rectangle getLeft(){
		return leftPac;
	}
	Rectangle getRight(){
		return rightPac;
	}
	Rectangle getUp(){
		return upPac;
	}
	Rectangle getDown(){
		return downPac;
	}
	void setLValid(Boolean input){
		left = input;
	}
	void setRValid(Boolean input){
		right = input;
	}
	void setUValid(Boolean input){
		up = input;
	}
	void setDValid(Boolean input){
		down = input;
	}
	Boolean getLValid(){
		return left;
	}
	Boolean getRValid(){
		return right;
	}
	Boolean getUValid(){
		return up;
	}
	Boolean getDValid(){
		return down;
	}
	
	void movement(){
		if(style.equals("Arcade") || style.equals("Champion")){
			
			switch(direction){
			
				case 'r':
					if(right){
						location.x+=5;
						if((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 1){
							index = 1;
							cycle = 2;
						} else if ((index == 2 || index == 5 || index == 8 || index == 11)&& cycle == 2){
							index = 0;
							cycle = 1;
						} else if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7 || index == 9 || index == 10 ){
							index = 2;
						}
					}
					break;
				case 'l':
					if(left){
						location.x-=5;
						if((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 1){
							index = 4;
							cycle = 2;
						} else if ((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 2){
							index = 3;
							cycle = 1;
						} else if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7 || index == 9 || index == 10){
							index = 5;
						}
					}
					break;
				case 'u':
					if(up){
						location.y-=5;
						if((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 1){
							index = 7;
							cycle = 2;
						} else if ((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 2){
							index = 6;
							cycle = 1;
						} else if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7 || index == 9 || index == 10){
						index = 8;
						}
					}
					break;
				case 'd':
					if(down){
						location.y+=5;
						if((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 1){
							index = 10;
							cycle = 2;
						} else if ((index == 2 || index == 5 || index == 8 || index == 11) && cycle == 2){
							index = 9;
							cycle = 1;
						} else if (index == 0 || index == 1 || index == 3 || index == 4 || index == 6 || index == 7 || index == 9 || index == 10){
						index = 11;
						}
					}
					break;
			}
		} else if(style.equals("SNES/Genesis")){
			

			switch(direction){
			
				case 'r':
					if(right){
						location.x+=5;
						if(index == 4 || index == 9 || index == 14 || index == 19){
							index = 3;
						}  else if(index == 3 || index == 8 || index == 13 || index == 18){
							if(cycle == 2){
								index = 2;
							} else if(cycle == 1){
								index = 4;
								cycle = 2;
							}
							
						} else if(index == 2 || index == 7 || index == 12 || index == 17){
							if(cycle == 2){
								index = 1;
							} else if(cycle == 1){
								index = 3;
							}
							
						} else if (index == 1 || index == 6 || index == 11 || index == 16){
							if(cycle == 2){
								index = 0;
								cycle = 1;
							} else if(cycle == 1){
								index = 2;
							}
							
						} else if (index == 0 || index == 5 || index == 10 || index == 15){
						
							index = 1;
						}
					}
					break;
				case 'l':
					if(left){
						location.x-=5;
						if(index == 4 || index == 9 || index == 14 || index == 19){
							index = 8;
						}  else if(index == 3 || index == 8 || index == 13 || index == 18){
							if(cycle == 2){
								index = 7;
							} else if(cycle == 1){
								index = 9;
								cycle = 2;
							}
							
						} else if(index == 2 || index == 7 || index == 12 || index == 17){
							if(cycle == 2){
								index = 6;
							} else if(cycle == 1){
								index = 8;
							}
							
						} else if (index == 1 || index == 6 || index == 11 || index == 16){
							if(cycle == 2){
								index = 5;
								cycle = 1;
							} else if(cycle == 1){
								index = 7;
							}
							
						} else if (index == 0 || index == 5 || index == 10 || index == 15){
						
							index = 6;
						}
					}
					break;
				case 'u':
					if(up){
						location.y-=5;
						if(index == 4 || index == 9 || index == 14 || index == 19){
							index = 13;
						}  else if(index == 3 || index == 8 || index == 13 || index == 18){
							if(cycle == 2){
								index = 12;
							} else if(cycle == 1){
								index = 14;
								cycle = 2;
							}
							
						} else if(index == 2 || index == 7 || index == 12 || index == 17){
							if(cycle == 2){
								index = 11;
							} else if(cycle == 1){
								index = 13;
							}
							
						} else if (index == 1 || index == 6 || index == 11 || index == 16){
							if(cycle == 2){
								index = 10;
								cycle = 1;
							} else if(cycle == 1){
								index = 12;
							}
							
						} else if (index == 0 || index == 5 || index == 10 || index == 15){
						
							index = 11;
						}
					}
					break;
				case 'd':
					if(down){
						location.y+=5;
						if(index == 4 || index == 9 || index == 14 || index == 19){
							index = 18;
						}  else if(index == 3 || index == 8 || index == 13 || index == 18){
							if(cycle == 2){
								index = 17;
							} else if(cycle == 1){
								index = 19;
								cycle = 2;
							}
							
						} else if(index == 2 || index == 7 || index == 12 || index == 17){
							if(cycle == 2){
								index = 16;
							} else if(cycle == 1){
								index = 18;
							}
							
						} else if (index == 1 || index == 6 || index == 11 || index == 16){
							if(cycle == 2){
								index = 15;
								cycle = 1;
							} else if(cycle == 1){
								index = 17;
							}
							
						} else if (index == 0 || index == 5 || index == 10 || index == 15){
						
							index = 16;
						}
					}
					break;
			}
			
		}
		truePac.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftPac.setBounds(truePac.x - 20, truePac.y,20,20);
		rightPac.setBounds(truePac.x + 20, truePac.y,20,20);
		upPac.setBounds(truePac.x, truePac.y - 20,20,20);
		downPac.setBounds(truePac.x, truePac.y + 20,20,20);
		
	}
	
	void changeDirection(){
		
		if(style.equals("Arcade") || style.equals("Champion")){
			switch(direction){
			case 'r':
				if(index == 4 || index == 7 || index == 10){
					index = 1;
				} else if(index == 5 || index == 8 || index == 11){
					index = 2;
				} else if(index == 3 || index == 6 || index== 9){
					index = 0;
				}
				break;
			case 'l':
				if(index == 1 || index == 7 || index == 10){
					index = 4;
				} else if(index == 2 || index == 8 || index == 11){
					index = 5;
				} else if(index == 0 || index == 6 || index== 9){
					index = 3;
				}
				break;
			case 'u':
				if(index == 1 || index == 4 || index == 10){
					index = 7;
				} else if(index == 2 || index == 5 || index == 11){
					index = 8;
				} else if(index == 0 || index == 3 || index== 9){
					index = 6;
				}
				break;
			case 'd':
				if(index == 1 || index == 4 || index == 7){
					index = 10;
				} else if(index == 2 || index == 5 || index == 8){
					index = 11;
				} else if(index == 0 || index == 3 || index== 6){
					index = 9;
				}
				break;
			}
		} else if(style.equals("SNES/Genesis")){
			switch(direction){
			case 'r':
				if(index == 9 || index == 14 || index == 19){
					index = 4;
				} else if(index == 8 || index == 13 || index == 18){
					index = 3;
				} else if(index == 7 || index == 12 || index== 17){
					index = 2;
				} else if(index == 6 || index == 11 || index == 16){
					index = 1;
				} else if(index == 5 || index == 10 || index== 15){
					index = 0;
				}
				break;
			case 'l':
				if(index == 4 || index == 14 || index == 19){
					index = 9;
				} else if(index == 3 || index == 13 || index == 18){
					index = 8;
				} else if(index == 2 || index == 12 || index== 17){
					index = 7;
				} else if(index == 1 || index == 11 || index == 16){
					index = 6;
				} else if(index == 0 || index == 10 || index== 15){
					index = 5;
				}
				break;
			case 'u':
				if(index == 4 || index == 9 || index == 19){
					index = 14;
				} else if(index == 3 || index == 8 || index == 18){
					index = 13;
				} else if(index == 2 || index == 7 || index== 17){
					index = 12;
				} else if(index == 1 || index == 6 || index == 16){
					index = 11;
				} else if(index == 0 || index == 5 || index== 15){
					index = 10;
				}
				break;
			case 'd':
				if(index == 4 || index == 9 || index == 14){
					index = 19;
				} else if(index == 3 || index == 8 || index == 13){
					index = 18;
				} else if(index == 2 || index == 7 || index== 12){
					index = 17;
				} else if(index == 1 || index == 6 || index == 11){
					index = 16;
				} else if(index == 0 || index == 5 || index== 10){
					index = 15;
				}
				break;
			}
		}
	}
	
	void respawn(){
		location = new Point(startLocation);
		truePac.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftPac.setBounds(truePac.x - 20, truePac.y,20,20);
		rightPac.setBounds(truePac.x + 20, truePac.y,20,20);
		upPac.setBounds(truePac.x, truePac.y - 20,20,20);
		downPac.setBounds(truePac.x, truePac.y + 20,20,20);
		
		if(idPlayer.equals("Player1")){
			index = 5;
			cycle = 1;
			direction = 'l';
		} else if (idPlayer.equals("Player2")){
			index = 2;
			cycle = 1;
			direction = 'r';
		}
		left = true;
		right = true;
		up = false;
		down = false;
	}
	
	Point getLocation(){
		return location;
	}
	
	Point getStartLocation(){
		return startLocation;
	}
	
	void setLocation(int x, int y){
		location = new Point(x-5,y-5);
		truePac.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftPac.setBounds(truePac.x - 20, truePac.y,20,20);
		rightPac.setBounds(truePac.x + 20, truePac.y,20,20);
		upPac.setBounds(truePac.x, truePac.y - 20,20,20);
		downPac.setBounds(truePac.x, truePac.y + 20,20,20);
	}
	
	void setStartLocation(int x, int y){
		
		startLocation = new Point(x,y);
	}
	
	Image ImageResize(Image input, Color background){
		
		Image img = input;
		BufferedImage newImage = (BufferedImage) img;
		
		for(int y = 0; y < newImage.getHeight(); y++){
		      for(int x = 0; x < newImage.getWidth(); x++){
		        int p = newImage.getRGB(x,y);
		        
		        int a = (p>>24)&0xff;
		        int r = (p>>16)&0xff;
		        int g = (p>>8)&0xff;
		        int b = (p)&0xff;
		        
		        if(r == background.getRed() && g == background.getGreen() && b == background.getBlue()){
		        	p = (0<<24) | (r<<16) | (g<<8) | b;
		        }
		        else{
		        	p = (a<<24) | (r<<16) | (g<<8) | b;
		        }

		        a = (p>>24)&0xff;
		        r = (p>>16)&0xff;
		        g = (p>>8)&0xff;
		        b = (p)&0xff;
		        //set new RGB
		        //p = (a<<24) | (0<<16) | (g<<8) | 0;

		        newImage.setRGB(x, y, p);
		      }
		}
		
		Image image = newImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		return image;
	}
	public PacmanData(String pac, String player,int styleNum, int xinput, int yinput){
		index = 5;
		cycle = 1;
		direction = 'l';
		status = "normal";
		idPlayer = player;
		try {
			//spritesheet = ImageIO.read(new File("pacman.png"));
			spritesheet = ImageIO.read(new File("PacSprites.png"));
			spritesheet2 = ImageIO.read(new File("mspacman.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		truePac = new Rectangle(xinput + 5, yinput + 5, 20, 20);
		leftPac = new Rectangle(truePac.x - 20, truePac.y,20,20);
		rightPac = new Rectangle(truePac.x + 20, truePac.y,20,20);
		upPac = new Rectangle(truePac.x, truePac.y -20,20,20);
		downPac = new Rectangle(truePac.x, truePac.y + 20,20,20);
		
		left = true;
		right = true;
		up = false;
		down = false;
		
		switch(styleNum){
		case 1:
			style = "Arcade";
			break;
		case 2:
			style = "SNES/Genesis";
			break;
		case 3:
			style = "Champion";
			break;
		case 4:
			style = "Arrangement";
			break;
		default:
			style = "Arcade";
			break;
		}
		
		if(style.equals("Arcade")){
			sprites = new ImageIcon[12];
	
			if(pac.equals("pacman")){/*
				sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(489, 1, 13, 13)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(457, 1, 13, 13)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(473, 1, 13, 13)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(489, 1, 13, 13)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(457, 17, 13, 13)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(473, 17, 13, 13)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(489, 1, 13, 13)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(457, 33, 13, 13)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(473, 33, 13, 13)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(489, 1, 13, 13)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(457, 49, 13, 13)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(473, 49, 13, 13)));*/
				sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(46, 10, 13, 13), new Color(0,0,0)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(14, 11, 13, 13), new Color(0,0,0)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(30, 11, 13, 13), new Color(0,0,0)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(46, 10, 13, 13), new Color(0,0,0)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(14, 27, 13, 13), new Color(0,0,0)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(30, 27, 13, 13), new Color(0,0,0)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(46, 10, 13, 13), new Color(0,0,0)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(14, 43, 13, 13), new Color(0,0,0)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(30, 43, 13, 13), new Color(0,0,0)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(46, 10, 13, 13), new Color(0,0,0)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(14, 59, 13, 13), new Color(0,0,0)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(30, 59, 13, 13), new Color(0,0,0)));
				
			} else if(pac.equals("mspacman")){
				/*sprites[0] = new ImageIcon( ImageResize(spritesheet2.getSubimage(489, 1, 15, 15)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet2.getSubimage(457, 1, 15, 15)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet2.getSubimage(473, 1, 15, 15)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet2.getSubimage(489, 17, 15, 15)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet2.getSubimage(457, 17, 15, 15)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet2.getSubimage(473, 17, 15, 15)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet2.getSubimage(489, 33, 15, 15)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet2.getSubimage(457, 33, 15, 15)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet2.getSubimage(473, 33, 15, 15)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet2.getSubimage(489, 49, 15, 15)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet2.getSubimage(457, 49, 15, 15)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet2.getSubimage(473, 49, 15, 15)));*/
				sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(102, 11, 15, 15), new Color(107,0,109)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(70, 11, 15, 15), new Color(107,0,109)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(86, 11, 15, 15), new Color(107,0,109)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(102, 27, 15, 15), new Color(107,0,109)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(70, 27, 15, 15), new Color(107,0,109)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(86, 27, 15, 15), new Color(107,0,109)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(102, 42, 15, 15), new Color(107,0,109)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(70, 42, 15, 15), new Color(107,0,109)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(86, 42, 15, 15), new Color(107,0,109)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(102, 59, 15, 15), new Color(107,0,109)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(70, 59, 15, 15), new Color(107,0,109)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(86, 59, 15, 15), new Color(107,0,109)));
			}
		} else if(style.equals("SNES/Genesis")){
			sprites = new ImageIcon[20];
			
			if(pac.equals("pacman")){
				sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(128, 28, 13, 13), new Color(107,0,109)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(144, 28, 13, 13), new Color(107,0,109)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(160, 28, 13, 13), new Color(107,0,109)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(176, 28, 13, 13), new Color(107,0,109)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(192, 28, 13, 13), new Color(107,0,109)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(190, 12, 13, 13), new Color(107,0,109)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(174, 12, 13, 13), new Color(107,0,109)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(158, 12, 13, 13), new Color(107,0,109)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(142, 12, 13, 13), new Color(107,0,109)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(126, 12, 13, 13), new Color(107,0,109)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(270, 11, 13, 13), new Color(107,0,109)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(254, 11, 13, 13), new Color(107,0,109)));
				sprites[12] = new ImageIcon( ImageResize(spritesheet.getSubimage(238, 11, 13, 13), new Color(107,0,109)));
				sprites[13] = new ImageIcon( ImageResize(spritesheet.getSubimage(222, 11, 13, 13), new Color(107,0,109)));
				sprites[14] = new ImageIcon( ImageResize(spritesheet.getSubimage(206, 11, 13, 13), new Color(107,0,109)));
				sprites[15] = new ImageIcon( ImageResize(spritesheet.getSubimage(270, 28, 13, 13), new Color(107,0,109)));
				sprites[16] = new ImageIcon( ImageResize(spritesheet.getSubimage(254, 28, 13, 13), new Color(107,0,109)));
				sprites[17] = new ImageIcon( ImageResize(spritesheet.getSubimage(238, 28, 13, 13), new Color(107,0,109)));
				sprites[18] = new ImageIcon( ImageResize(spritesheet.getSubimage(222, 28, 13, 13), new Color(107,0,109)));
				sprites[19] = new ImageIcon( ImageResize(spritesheet.getSubimage(206, 28, 13, 13), new Color(107,0,109)));
			} else if(pac.equals("mspacman")){
				sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(127, 59, 15, 15), new Color(107,0,109)));
				sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(143, 59, 15, 15), new Color(107,0,109)));
				sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(159, 59, 15, 15), new Color(107,0,109)));
				sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(175, 59, 15, 15), new Color(107,0,109)));
				sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(191, 59, 15, 15), new Color(107,0,109)));
				sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(189, 43, 15, 15), new Color(107,0,109)));
				sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(173, 43, 15, 15), new Color(107,0,109)));
				sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(157, 43, 15, 15), new Color(107,0,109)));
				sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(141, 43, 15, 15), new Color(107,0,109)));
				sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(125, 43, 15, 15), new Color(107,0,109)));
				sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(269, 42, 15, 15), new Color(107,0,109)));
				sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(253, 42, 15, 15), new Color(107,0,109)));
				sprites[12] = new ImageIcon( ImageResize(spritesheet.getSubimage(237, 42, 15, 15), new Color(107,0,109)));
				sprites[13] = new ImageIcon( ImageResize(spritesheet.getSubimage(221, 42, 15, 15), new Color(107,0,109)));
				sprites[14] = new ImageIcon( ImageResize(spritesheet.getSubimage(205, 42, 15, 15), new Color(107,0,109)));
				sprites[15] = new ImageIcon( ImageResize(spritesheet.getSubimage(269, 59, 15, 15), new Color(107,0,109)));
				sprites[16] = new ImageIcon( ImageResize(spritesheet.getSubimage(253, 59, 15, 15), new Color(107,0,109)));
				sprites[17] = new ImageIcon( ImageResize(spritesheet.getSubimage(237, 59, 15, 15), new Color(107,0,109)));
				sprites[18] = new ImageIcon( ImageResize(spritesheet.getSubimage(221, 59, 15, 15), new Color(107,0,109)));
				sprites[19] = new ImageIcon( ImageResize(spritesheet.getSubimage(205, 59, 15, 15), new Color(107,0,109)));
			}
		} else if(style.equals("Champion")){
			
			sprites = new ImageIcon[12];
			
			sprites[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(344, 10, 13, 13), new Color(107,0,109)));
			sprites[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(312, 10, 13, 13), new Color(107,0,109)));
			sprites[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(328, 10, 13, 13), new Color(107,0,109)));
			sprites[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(344, 10, 13, 13), new Color(107,0,109)));
			sprites[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(312, 25, 13, 13), new Color(107,0,109)));
			sprites[5] = new ImageIcon( ImageResize(spritesheet.getSubimage(328, 25, 13, 13), new Color(107,0,109)));
			sprites[6] = new ImageIcon( ImageResize(spritesheet.getSubimage(344, 10, 13, 13), new Color(107,0,109)));
			sprites[7] = new ImageIcon( ImageResize(spritesheet.getSubimage(313, 40, 13, 13), new Color(107,0,109)));
			sprites[8] = new ImageIcon( ImageResize(spritesheet.getSubimage(329, 40, 13, 13), new Color(107,0,109)));
			sprites[9] = new ImageIcon( ImageResize(spritesheet.getSubimage(344, 10, 13, 13), new Color(107,0,109)));
			sprites[10] = new ImageIcon( ImageResize(spritesheet.getSubimage(313, 55, 13, 13), new Color(107,0,109)));
			sprites[11] = new ImageIcon( ImageResize(spritesheet.getSubimage(329, 55, 13, 13), new Color(107,0,109)));
		}
		
		startLocation = new Point(xinput, yinput);
		location = new Point(xinput, yinput);
		
		options = new ImageIcon[5];
		options[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(30, 11, 13, 13), new Color(0,0,0)));
		options[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(86, 11, 15, 15), new Color(107,0,109)));
		options[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(160, 28, 13, 13), new Color(107,0,109)));
		options[3] = new ImageIcon( ImageResize(spritesheet.getSubimage(159, 59, 15, 15), new Color(107,0,109)));
		options[4] = new ImageIcon( ImageResize(spritesheet.getSubimage(328, 10, 13, 13), new Color(107,0,109)));
	}
}
