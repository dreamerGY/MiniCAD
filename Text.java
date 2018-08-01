package myCAD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Text extends Shape implements Serializable{
	
	private static final long serialVersionUID = -1596864145951960042L;
	private String s1;
	//子类构造函数
	public Text(int x1,int y1,Color color,int width,String s1){
		this.x1=x1;
		this.y1=y1;
		this.color=color;
		this.width=width;
		this.s1=s1;
	}
	
  //重写父类的Draw方法，实现文字的绘制
	public void Draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		if (s1 != null)
			g.drawString(s1, x1, y1);
	}
}





