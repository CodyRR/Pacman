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


public class Ghosts {
	
	private int index, speed, boxCycle, boxTop, boxBottem, boxX;
	private char direction;
	private String status, id, style;
	private ImageIcon[] sprites, options;
	private BufferedImage spritesheet;
	private Point location, startLocation;
	private Rectangle trueGhost, leftGhost, rightGhost, upGhost, downGhost, target;
	private Boolean left, right, up, down, inBox, goingInBox, letOut;
	
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
	
	String getId(){
		return id;
	}
	
	void setSpeed(int input){
		speed = input;
	}
	
	void setDirection( char input){
		direction = input;
	}
	char getDirection(){
		return direction;
	}
	
	Point getLocation(){
		return location;
	}
	
	ImageIcon getImage(){
		
		return sprites[index];
	}
	
	ImageIcon getOption(int num){
		return options[num];
	}
	
	Rectangle getTrue(){
		return trueGhost;
	}
	
	Rectangle getLeft(){
		return leftGhost;
	}
	Rectangle getRight(){
		return rightGhost;
	}
	Rectangle getUp(){
		return upGhost;
	}
	Rectangle getDown(){
		return downGhost;
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
		
		if((status.equals("Normal") || status.equals("Blue")) && !inBox){
			switch(direction){
			
				case 'r':
					if(right){
						location.x+=speed;
						if(status.equals("Normal")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8 || index == 10){
								index = 1;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9 || index == 11){
								index = 0;
							}
						} else if(status.equals("Blue")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8){
								index = 9;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9){
								index = 8;
							}
						} else if(index == 10){
							index = 11;
						}else if(index == 11){
							index = 10;
						}
						
					}
					break;
				case 'l':
					if(left){
						location.x-=speed;
						
						if(status.equals("Normal")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8 || index == 10){
								index = 3;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9 || index == 11){
								index = 2;
							}
						} else if(status.equals("Blue")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8){
								index = 9;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9){
								index = 8;
							}
						} else if(index == 10){
							index = 11;
						}else if(index == 11){
							index = 10;
						}
					}
					break;
				case 'u':
					if(up){
						location.y-=speed;
						if(status.equals("Normal")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8 || index == 10){
								index = 5;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9 || index == 11){
								index = 4;
							}
						} else if(status.equals("Blue")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8){
								index = 9;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9){
								index = 8;
							} else if(index == 10){
								index = 11;
							}else if(index == 11){
								index = 10;
							}
						}
					}
					break;
				case 'd':
					if(down){
						location.y+=speed;
						if(status.equals("Normal")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8 || index == 10){
								index = 7;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9 || index == 11){
								index = 6;
							}
						} else if(status.equals("Blue")){
							if(index == 0 || index == 2 || index == 4 || index == 6 || index == 8){
								index = 9;
							} else if (index == 1 || index == 3 || index == 5 || index == 7 || index == 9){
								index = 8;
							} else if(index == 10){
								index = 11;
							}else if(index == 11){
								index = 10;
							}
						}
						
					}
					break;
			}
		} else if (!inBox){
			switch(direction){
			
			case 'r':
				if(right){
					location.x+=speed;
				}
				break;
			case 'l':
				if(left){
					location.x-=speed;
				}
				break;
			case 'u':
				if(up){
					location.y-=speed;
				}
				break;
			case 'd':
				if(down){
					location.y+=speed;
				}
				break;
			}
		}
		trueGhost.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftGhost.setBounds(trueGhost.x - 20, trueGhost.y,20,20);
		rightGhost.setBounds(trueGhost.x + 20, trueGhost.y,20,20);
		upGhost.setBounds(trueGhost.x, trueGhost.y - 20,20,20);
		downGhost.setBounds(trueGhost.x, trueGhost.y + 20,20,20);
		
			
	}
	void directionMovement(){
		
		int xTarget = (target.x + (target.x + target.width))/2;
		int yTarget = (target.y + (target.y + target.height))/2;
		//System.out.println("Target is "+xTarget+", "+yTarget);
		int xValue = (rightGhost.x + (rightGhost.x + rightGhost.width))/2;
		int yValue = (rightGhost.y + (rightGhost.y + rightGhost.height))/2;
		double rightValue = Math.sqrt(((xTarget - xValue)*(xTarget-xValue)) + ((yTarget - yValue)*(yTarget-yValue)));
		//System.out.println("Right is "+xValue+", "+yValue + "  Distance from Target is " + rightValue);
		xValue = (leftGhost.x + (leftGhost.x + leftGhost.width))/2;
		yValue = (leftGhost.y + (leftGhost.y + leftGhost.height))/2;
		double leftValue = Math.sqrt(((xTarget - xValue)*(xTarget-xValue)) + ((yTarget - yValue)*(yTarget-yValue)));
		//System.out.println("Left is "+xValue+", "+yValue + "  Distance from Target is " + leftValue);
		xValue = (upGhost.x + (upGhost.x + upGhost.width))/2;
		yValue = (upGhost.y + (upGhost.y + upGhost.height))/2;
		double upValue = Math.sqrt(((xTarget - xValue)*(xTarget-xValue)) + ((yTarget - yValue)*(yTarget-yValue)));
		//System.out.println("Up is "+xValue+", "+yValue + "  Distance from Target is " + upValue);
		xValue = (downGhost.x + (downGhost.x + downGhost.width))/2;
		yValue = (downGhost.y + (downGhost.y + downGhost.height))/2;
		double downValue = Math.sqrt(((xTarget - xValue)*(xTarget-xValue)) + ((yTarget - yValue)*(yTarget-yValue)));
		//System.out.println("Down is "+xValue+", "+yValue + "  Distance from Target is " + downValue);
		double small = -1;
		char tempDirection = 0;
		if(status.equals("Normal") || status.equals("Eaten")){
			switch(direction){
			
				case 'r':
					if(right){
						small = rightValue;
						tempDirection = 'r';
					}
					
					if(up){
						if(small == -1){
							small = upValue;
							tempDirection = 'u';
						} else{
							if(small > upValue){
								small = upValue;
								tempDirection = 'u';
							}
						}
					}
					
					if(down){
						if(small == -1){
							small = downValue;
							tempDirection = 'd';
						} else{
							if(small > downValue){
								small = downValue;
								tempDirection = 'd';
							}
						}
					}
					
					if(small == -1){
						tempDirection = 'l';
					}
					break;
				case 'l':
					if(left){
						small = leftValue;
						tempDirection = 'l';
					}
					
					if(up){
						if(small == -1){
							small = upValue;
							tempDirection = 'u';
						} else{
							if(small > upValue){
								small = upValue;
								tempDirection = 'u';
							}
						}
					}
					
					if(down){
						if(small == -1){
							small = downValue;
							tempDirection = 'd';
						} else{
							if(small > downValue){
								small = downValue;
								tempDirection = 'd';
							}
						}
					}
					
					if(small == -1){
						tempDirection = 'r';
					}
					break;
				case 'u':
					if(right){
						small = rightValue;
						tempDirection = 'r';
					}
					
					if(left){
						if(small == -1){
							small = leftValue;
							tempDirection = 'l';
						} else{
							if(small > leftValue){
								small = leftValue;
								tempDirection = 'l';
							}
						}
					}
					
					if(up){
						if(small == -1){
							small = upValue;
							tempDirection = 'u';
						} else{
							if(small > upValue){
								small = upValue;
								tempDirection = 'u';
							}
						}
					}
					
					if(small == -1){
						tempDirection = 'd';
					}
					break;
				case 'd':
					if(right){
						small = rightValue;
						tempDirection = 'r';
					}
					
					if(left){
						if(small == -1){
							small = leftValue;
							tempDirection = 'l';
						} else{
							if(small > leftValue){
								small = leftValue;
								tempDirection = 'l';
							}
						}
					}
					
					if(down){
						if(small == -1){
							small = downValue;
							tempDirection = 'd';
						} else{
							if(small > downValue){
								small = downValue;
								tempDirection = 'd';
							}
						}
					}
					
					if(small == -1){
						tempDirection = 'u';
					}
					break;
			}
		}
		else{
			char available[] = new char[]{0,0,0};
			int num = 0;
			switch(direction){
			case 'r':
				if(right){
					available[num] = 'r';
					num++;
				}
				if(up){
					available[num] = 'u';
					num++;
				}
				if(down){
					available[num] = 'd';
					num++;
				}
				break;
			case 'l':
				if(left){
					available[num] = 'l';
					num++;
				}
				if(up){
					available[num] = 'u';
					num++;
				}
				if(down){
					available[num] = 'd';
					num++;
				}
				break;
			case 'u':
				if(right){
					available[num] = 'r';
					num++;
				}
				if(left){
					available[num] = 'l';
					num++;
				}
				if(up){
					available[num] = 'u';
					num++;
				}
				break;
			case 'd':
				if(right){
					available[num] = 'r';
					num++;
				}
				if(left){
					available[num] = 'l';
					num++;
				}
				if(down){
					available[num] = 'd';
					num++;
				}
				break;
			
			}
			
			if(num == 0){
				tempDirection = direction;
			} else{
				Random rand = new Random();
				int randomNum = rand.nextInt(num);
				tempDirection = available[randomNum];
			}
		}
		setDirection(tempDirection);
	}
	
	void whileBox(){
		
		if(boxCycle ==1){
			setLocation(location.x +5,location.y+10);
			direction = 'd';
			if(status.equals("Normal")){
				if(index == 0 || index == 2 || index == 4 || index ==6){
					index = 7;
				} else if (index == 1 || index == 3 || index == 5 ||index == 7){
					index = 6;
				}
			} else if(status.equals("Blue")){
				if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8){
					index = 9;
				} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9){
					index = 8;
				} else if(index == 10){
					index = 11;
				} else if(index == 11){
					index = 10;
				}
			}
			/*if(index == 6){
				index = 7;
			} else if (index == 7){
				index = 6;
			} else if (index == 8){
				index = 9;
			} else if (index == 9){
				index = 8;
			} else if (index == 10){
				index = 11;
			} else if (index == 11){
				index = 10;
			}*/
		}else if(boxCycle == 2){
			setLocation(location.x+5,location.y);
			direction = 'u';
			if(status.equals("Normal")){
				if(index == 0 || index == 2 || index == 4 || index ==6){
					index = 5;
				} else if (index == 1 || index == 3 || index == 5 ||index == 7){
					index = 4;
				}
			} else if(status.equals("Blue")){
				if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
					index = 9;
				} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
					index = 8;
				}
			}
			/*if(index == 4){
				index = 5;
			} else if (index == 5){
				index = 4;
			} else if (index == 8){
				index = 9;
			} else if (index == 9){
				index = 8;
			} else if (index == 10){
				index = 11;
			} else if (index == 11){
				index = 10;
			}*/
		}
		
		if(location.y == boxBottem){
			boxCycle = 2;
			direction = 'u';
			if(index == 6 || index == 2 || index == 0){
				index = 4;
			} else if (index == 7 || index == 3 || index == 1){
				index = 5;
			} else if (index == 8){
				index = 9;
			} else if (index == 9){
				index = 8;
			} else if (index == 10){
				index = 11;
			} else if (index == 11){
				index = 10;
			}

		}else if (location.y == boxTop){
			boxCycle = 1;
			direction = 'd';
			if(index == 0 || index == 2 || index == 4){
				index = 6;
			} else if (index == 5 || index == 1 || index == 3){
				index = 7;
			} else if (index == 8){
				index = 9;
			} else if (index == 9){
				index = 8;
			} else if (index == 10){
				index = 11;
			} else if (index == 11){
				index = 10;
			}
		}
	}
	void inBox(){
		
		if(location.y != boxBottem-20){
			direction = 'd';
			index = 15;
			setLocation(location.x+5,location.y+10);
		}
		
		if(location.y == boxBottem-20){
			if(id.equals("Blue")){
				if(location.x == boxX-20){
					goingInBox = false;
					status = "Normal";
					
					index = 0;
				} else {
					direction = 'r';
					index = 12;
					setLocation(location.x+10, location.y+5);
				}
			} else if(id.equals("Orange")){
				if(location.x == boxX+20){
					goingInBox = false;
					status = "Normal";
					index = 2;
				} else {
					direction = 'l';
					index = 13;
					setLocation(location.x, location.y+5);
				}
			} else {
				goingInBox = false;
				status = "Normal";
				index = 6;
			}
		}
	}
	
	void outBox(){
		
		if(id.equals("Blue")){
			if(location.x != boxX -60){
				setLocation(location.x,location.y+5);
				if(status.equals("Normal")){
					if(index == 0 || index == 2 || index == 4 || index ==6){
						index = 3;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7){
						index = 2;
					}
				} else if(status.equals("Blue")){
					if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
						index = 9;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
						index = 8;
					}
				}
			} else{
				if(location.y != boxTop -45){
					setLocation(location.x+5, location.y );
					if(status.equals("Normal")){
						if(index == 0 || index == 2 || index == 4 || index ==6){
							index = 5;
						} else if (index == 1 || index == 3 || index == 5 ||index == 7){
							index = 4;
						}
					} else if(status.equals("Blue")){
						if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
							index = 9;
						} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
							index = 8;
						}
					}
				} else{
					inBox = false;
					direction = 'l';
				}
			}
		} else if(id.equals("Orange")){
			if(location.x != boxX + 60){
				setLocation(location.x+10,location.y+5);
				if(status.equals("Normal")){
					if(index == 0 || index == 2 || index == 4 || index ==6){
						index = 1;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7){
						index = 0;
					}
				} else if(status.equals("Blue")){
					if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
						index = 9;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
						index = 8;
					}
				}
			} else{
				if(location.y != boxTop -45){
					setLocation(location.x+5, location.y );
					if(status.equals("Normal")){
						if(index == 0 || index == 2 || index == 4 || index ==6){
							index = 5;
						} else if (index == 1 || index == 3 || index == 5 ||index == 7){
							index = 4;
						}
					} else if(status.equals("Blue")){
						if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
							index = 9;
						} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
							index = 8;
						}
					}
				} else{
					inBox = false;
					direction = 'l';
				}
			}
		} else {
			if(location.y != boxTop -45){
				setLocation(location.x+5, location.y );
				if(status.equals("Normal")){
					if(index == 0 || index == 2 || index == 4 || index ==6){
						index = 5;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7){
						index = 4;
					}
				} else if(status.equals("Blue")){
					if(index == 0 || index == 2 || index == 4 || index ==6 || index == 8 || index ==10){
						index = 9;
					} else if (index == 1 || index == 3 || index == 5 ||index == 7 || index == 9 || index == 11){
						index = 8;
					}
				}
			} else{
				inBox = false;
				direction = 'l';
			}
		}
	}
	
	void setBox(Boolean input){
		inBox = input;
	}
	
	boolean getBox(){
		return inBox;
	}
	
	boolean getGoingInBox(){
		return goingInBox;
	}
	
	void setGoingInBox(Boolean input){
		goingInBox = input;
	}
	
	boolean getOutBox(){
		return letOut;
	}
	
	void setOutBox(Boolean input){
		letOut = input;
	}
	
	void reverse(){
		switch(direction){
		case 'r':
			direction = 'l';
			break;
		case 'l':
			direction = 'r';
			break;
		case 'u':
			direction = 'd';
			break;
		case 'd':
			direction = 'u';
			break;
		}
	}
	void setLocation(int x, int y){
		location = new Point(x-5,y-5);
		trueGhost.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftGhost.setBounds(trueGhost.x - 20, trueGhost.y,20,20);
		rightGhost.setBounds(trueGhost.x + 20, trueGhost.y,20,20);
		upGhost.setBounds(trueGhost.x, trueGhost.y - 20,20,20);
		downGhost.setBounds(trueGhost.x, trueGhost.y + 20,20,20);
	}
	
	void setTarget(Rectangle input){
		target = input;
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

		        //set new RGB
		        //p = (a<<24) | (0<<16) | (g<<8) | 0;

		        newImage.setRGB(x, y, p);
		      }
		}
		
		Image image = newImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		return image;
	}
	
	void respawn(){
		//System.out.print("We are respawn");
		location = new Point(startLocation);
		trueGhost.setBounds(location.x + 5, location.y + 5, 20, 20);
		leftGhost.setBounds(trueGhost.x - 20, trueGhost.y,20,20);
		rightGhost.setBounds(trueGhost.x + 20, trueGhost.y,20,20);
		upGhost.setBounds(trueGhost.x, trueGhost.y - 20,20,20);
		downGhost.setBounds(trueGhost.x, trueGhost.y + 20,20,20);
		index = 0;
		speed = 5;
		if(id.equals("Red")){
			direction = 'r';
			inBox = false;
			letOut = true;
			boxCycle = 1;
		}else if (id.equals("Pink")){
			direction = 'u';
			inBox = true;
			letOut = false;
			boxCycle = 1;
		}else if (id.equals("Blue")){
			direction = 'u';
			inBox = true;
			letOut = false;
			boxCycle = 2;
		}else if (id.equals("Orange")){
			direction = 'u';
			inBox = true;
			letOut = false;
			boxCycle = 2;
		}
		left = true;
		right = true;
		up = false;
		down = false;
		goingInBox = false;
		status = "Normal";
	}
	
	public Ghosts(String input, int styleNum, int xlocat, int ylocat){
		
		speed = 5;
		if(input.equals("Red")){
			boxTop = ylocat +45;
			boxBottem = ylocat + 75;
			direction = 'r';
			inBox = false;
			letOut = true;
		} else{
			boxTop = ylocat -15;
			boxBottem = ylocat + 15;
			direction = 'u';
			inBox = true;
			letOut = false;
		}
		
		index = 0;
		sprites = new ImageIcon[16];
		int y =0, champX = 0;
		id = input;
		status = "Normal";
		if(input.equals("Red")){
			y = 167;
			boxX = xlocat;
			boxCycle = 1;
			champX = 358;
		} else if(input.equals("Pink")){
			y = 183;
			boxX = xlocat;
			boxCycle = 1;
			champX = 374;
		} else if (input.equals("Blue")){
			y= 199;
			boxX = xlocat + 20;
			boxCycle = 2;
			champX = 390;
		} else if (input.equals("Orange")){
			y = 215;
			boxX = xlocat - 20;
			boxCycle = 2;
			champX = 406;
		}

		goingInBox = false;
		try {
			spritesheet = ImageIO.read(new File("PacSprites.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			for(int i = 0, x = 14;i < 8; i++,x+=16){
				sprites[i] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, y, 14, 14), new Color(0,0,0)));
			}
			
			for(int i = 8,x = 142; i < 12; i++,x+=16){
				sprites[i] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, 167, 14, 14), new Color(0,0,0)));
			}
			
			for(int i = 12,x = 142; i < 16; i++,x+=16){
				sprites[i] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, 183, 14, 14), new Color(0,0,0)));
			}
		} else if(style.equals("SNES/Genesis")){
			int num[] = {7,6,3,2,1,0,5,4,9,8,11,10,15,13,12,14};
			for(int i = 0, x = 216;i < 8; i++,x+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, y, 14, 14), new Color(107,0,109)));
			}
			
			for(int i = 8,x = 216; i < 12; i++,x+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, 231, 14, 14), new Color(107,0,109)));
			}
			
			for(int i = 12,x = 280; i < 16; i++,x+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(x, 231, 14, 14), new Color(107,0,109)));
			}
		} else if(style.equals("Champion")){
			int num[] = {0,1,6,7,2,3,4,5,8,9,10,11,12,15,13,14};
			
			for(int i = 0, champY = 163;i < 8; i++,champY+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(champX, champY, 14, 14), new Color(107,0,109)));
			}
			
			for(int i = 8, champY = 163; i < 12; i++,champY+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(422, champY, 14, 14), new Color(107,0,109)));
			}
			
			for(int i = 12,champY = 227; i < 16; i++,champY+=16){
				sprites[num[i]] = new ImageIcon( ImageResize(spritesheet.getSubimage(422, champY, 14, 14), new Color(107,0,109)));
			}
		}
		
		options = new ImageIcon[3];
		
		options[0] = new ImageIcon( ImageResize(spritesheet.getSubimage(14, 167, 14, 14), new Color(0,0,0)));
		options[1] = new ImageIcon( ImageResize(spritesheet.getSubimage(296, 167, 14, 14), new Color(107,0,109)));
		options[2] = new ImageIcon( ImageResize(spritesheet.getSubimage(358, 163, 14, 14), new Color(107,0,109)));
		
		startLocation = new Point(xlocat,ylocat);
		location = new Point(xlocat,ylocat);
		
		trueGhost = new Rectangle(xlocat + 5, ylocat + 5, 20, 20);
		leftGhost = new Rectangle(trueGhost.x - 20, trueGhost.y,20,20);
		rightGhost = new Rectangle(trueGhost.x + 20, trueGhost.y,20,20);
		upGhost = new Rectangle(trueGhost.x, trueGhost.y -20,20,20);
		downGhost = new Rectangle(trueGhost.x, trueGhost.y + 20,20,20);
		
		left = true;
		right = true;
		up = false;
		down = false;
		
	}

}
