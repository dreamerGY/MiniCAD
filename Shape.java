package myCAD;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
 
//����ͼ�θ��ࣺShape��
public abstract class Shape implements Serializable{		
	//�������Զ����ɵ����	 
	private static final long serialVersionUID = 1L;
	
	public  int x1,y1,x2,y2;//����ͼ�ε�����
	public Color color;//������ɫ
	public int width;//���ʴ�ϸ
	
	//�����Draw����
	public abstract void Draw(Graphics2D g);
 
}