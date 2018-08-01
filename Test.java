package myCAD;

public class Test {
   
	public static void main(String[] args) throws Exception{
		DrawBorder db = new DrawBorder(); 
		db.initFrame();
		while(true) {
			db.repaint();
		}
		
	}
}