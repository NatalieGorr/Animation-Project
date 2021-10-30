//this class creates cloud objects
import java.util.Random;
public class Cloud {
	
	int emptyStart;
	float cloudAstartX;
	float cloudAY;
	float widthA;
	
	float cloudBstartX;
	float cloudBY;
	float widthB;
	
	double speed;
	float height;
				
	//each "line" of clouds must have a gap the balloon can comfortably pass through
	Cloud(){
	Random num = new Random();
	this.emptyStart = num.nextInt(431);
	this.cloudAstartX = 0;
	this.widthA = emptyStart;
	this.cloudAY = 0;
	
	this.cloudBstartX = emptyStart+130;
	this.widthB = 500-cloudBstartX;
	this.cloudBY = 0;
	
	this.speed = 1;
	this.height = 30;	
	}
				

	public void update() {
		this.cloudAY += speed;
		this.cloudBY += speed;
	}
	
	//test if cloud is offscreen
	public boolean offscreen() {
		return (this.cloudAY > 500);
	
	}
	
	//Not a perfect collision test, testing if balloon is "stuck"
	public boolean touches(Balloon player) {
		return ((player.left+12 <this.widthA || player.right-12 > this.cloudBstartX) && ((player.ypos >= this.cloudAY)&&(player.ypos<=this.cloudAY)));

				
	}

}
