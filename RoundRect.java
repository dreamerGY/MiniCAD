package myCAD;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
 
public class RoundRect extends Shape implements Serializable{

	private static final long serialVersionUID = -4824334005119616462L;
	//�����������ԣ�Բ�Ǿ��εĽǵ������̶�
	public int arcWidth,arcHeight;
	
 
	public RoundRect(){
		
	}
	
	public RoundRect(int x1,int y1,int x2,int y2,int i,int j,Color color ,int width){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.arcWidth=i;
		this.arcHeight=j;
		this.color=color;
		this.width=width;
	}
    
	public void Draw(Graphics2D g) {
		g.setColor(this.color);
		g.setStroke(new BasicStroke(width));
		g.drawRoundRect(x1, y1, x2, y2, this.arcWidth, this.arcHeight);
	}
 
}
