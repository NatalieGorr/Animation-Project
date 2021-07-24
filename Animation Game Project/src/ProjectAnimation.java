//this class draws the code out with the main method using processing
//This mini-game has a floating "balloon"(red circle) which follows the x coordinate of the mouse to try 
//to avoid the "clouds"(white rectangles) which will trap the balloon.

import processing.core.PApplet;
import processing.core.PImage;
public class ProjectAnimation extends PApplet {
	
	//fields
	Balloon player = new Balloon();
	//array for "clouds"
	Cloud[] cloud = new Cloud[0];
	int score;
	PImage sky;
	
	public static void main(String[] args) {
		PApplet.main("ProjectAnimation");
	}
	
	//setup
	public void settings() {
		size(500, 500);
		cloud = (Cloud[]) append(cloud, new Cloud());
		sky = loadImage("bigstock-Blue-sky-24967379.jpg");
	}
		
	//draw function
	public void draw() {
		background(0, 204, 255);
		image(sky, 0, 0);
		frameRate(250);

		//add obstacles(clouds) based on the rate of the draw function
		if (frameCount % 200 == 0) {
			cloud = (Cloud[]) append(cloud, new Cloud());
		}
		
		//cloud
		for (int i = cloud.length-1; i>=0; i--) {
			fill(255); 
			rect(cloud[i].cloudAstartX,cloud[i].cloudAY,cloud[i].widthA,cloud[i].height);
			rect(cloud[i].cloudBstartX,cloud[i].cloudBY,cloud[i].widthB,cloud[i].height);
			cloud[i].update();
			
			//test if the balloon gets stuck in the cloud
			if (cloud[i].touches(player)){
					noLoop();
					textSize(70);
					fill(0);
					text("Game Over", 20, 240);
					textSize(40);
					text("score: "+score, 20, 280);
					textSize(20);
					text("(Click mouse to play again, press 'q' to quit)", 20, 310);					
			}
			
			//delete extra obstacles from array(reverse array, shorten, then reverse again)
			if (cloud[i].offscreen()) {
				score += 1;
				for(int f=0; f<cloud.length/2; f++){
					  Cloud temp = cloud[f];
					  cloud[f] = cloud[cloud.length-f-1];
					  cloud[cloud.length -f -1] = temp;
				}
				cloud = (Cloud[]) shorten(cloud);
				for(int f=0; f<cloud.length/2; f++){
					  Cloud temp = cloud[f];
					  cloud[f] = cloud[cloud.length-f-1];
					  cloud[cloud.length -f -1] = temp;
				}
			}
			fill(255,0,0);
			ellipse(player.xpos,player.ypos,player.radius,player.radius);
			player.mouseMove(mouseX);	
		}		
		

	}
	public void mousePressed() { 
		loop();
		score = 0;
	}
	public void keyPressed() {
		  if ((key == 'q') || (key == 'Q')) {
		    exit();
		  }
	}
}
