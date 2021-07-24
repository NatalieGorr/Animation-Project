//this class creates balloon object
public class Balloon {
		
		float xpos;
		float ypos;
		float radius;
			
		Balloon(){
			this.xpos = 250;
			this.ypos = 350;
			this.radius = 60;
		}
		
		//functions(dependent on x position of mouse)
		public void mouseMove(int mouseX) {
			this.xpos = mouseX;	
		}
		
}
