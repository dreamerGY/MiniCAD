package myCAD;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
 
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
 
//���Ļ�������ࣺ
public class DrawHandler implements MouseListener,MouseMotionListener{
	
	private Graphics2D g;
	private int x1,y1,x2,y2,ox,oy,x3,y3;
	private ButtonGroup bg;
	private String command;
	private Color color;
	private DrawBorder db;
	private ArrayList<Shape> list;
	private boolean flag=true;
	private int gWidth = 1; // ���ʴ�ϸ
	private static final  Stroke s1 = new BasicStroke(1);
	private static final  Stroke s2 = new BasicStroke(10);
	private static final  Stroke s3 = new BasicStroke(15);
	
	private Random r =new Random();

    //���캯��
	public DrawHandler (Graphics g2, ButtonGroup bg2, DrawBorder db1,ArrayList<Shape> list) {
		g=(Graphics2D)g2;
		bg=bg2;
		db=db1;
		this.list=list;
	}
 

    //��갴���¼�����
	public void mousePressed(MouseEvent e) {
		//��ȡ��갴�µ������
		x1=e.getX();
		y1=e.getY();					
		//�ж�ѡ�����������е��ĸ���ť��ѡ�У�  
        ButtonModel bm=bg.getSelection();//�õ���ť���б�ѡ�еİ�ť  
        command=bm.getActionCommand();//�õ�ѡ�а�ť������    
    	
        if("pic5".equals(command)) {
    		String input;
        	input = JOptionPane.showInputDialog("ͼ��������ϸ��1��10֮���������: ");
        	while(true) {
        		try {
            		gWidth = Integer.parseInt(input);
            		break;
            	} catch (NumberFormatException e1) {
            		input = JOptionPane.showInputDialog("ͼ��������ϸ��1��10֮���������: ");
            	}
        	}
        	
    	}
    	
        if("pic9".equals(command))  
        {  
        	String input;
        	input = JOptionPane.showInputDialog("Please input the text: ");
        	Shape text = new Text(x1, y1, g.getColor(),gWidth,input);
        	text.Draw(g);
        	list.add(text);
        }
	}
 
	public void mouseReleased(MouseEvent e) {  
        //��ȡ����ͷŵ�����  
        x2=e.getX();  
        y2=e.getY();              
        //���ѡ�е��ǻ���ֱ�ߵİ�ť����ô������갴�µ��������ͷŵ����߻���ֱ�ߣ�����ȷ��һ��ֱ�ߣ�  
        if("pic10".equals(command))  
        {  
        	Shape line = new Line(x1, y1, x2, y2,g.getColor(),gWidth);
        	line.Draw(g);
        	list.add(line);
        }
        //ͬ��ѡ�е��Ǿ��ΰ�ť����ô���ƾ���  
        else if("pic12".equals(command)){  
        	Shape rect = new Rect(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),g.getColor(),gWidth);
        	rect.Draw(g);
        	list.add(rect);
        }
        //������Բ  
        else if("pic14".equals(command)){  
        	Shape oval = new Oval(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),g.getColor(),gWidth);
        	oval.Draw(g);
        	list.add(oval);
        } 
        //����Բ�Ǿ���
        else if("pic15".equals(command)){
        	Shape roundrect = new RoundRect(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),40,40,g.getColor(),gWidth);
        	roundrect.Draw(g);
        	list.add(roundrect);
        }
        //���ƶ����
        else if("pic13".equals(command)){      	
        	//��һ�λ�ֱ�ߣ����ñ�־
        	if(flag){
        		Shape line = new Line(x1, y1, x2, y2,g.getColor(),gWidth);
            	line.Draw(g);
            	list.add(line);
                flag=false;
              //��¼�������ͷŵ����꣬��Ϊ�´λ���ֱ�ߵ����
                x3=x2;
                y3=y2;
                //��¼��һ��������꣬���Ʒ�յ�����
                ox=x1;
                oy=y1;
        	}
        	else{
        		Shape line = new Line(x3, y3, x2, y2,g.getColor(),gWidth);
             	line.Draw(g);
             	list.add(line);
        		  //��¼�ϴ�����ͷŵ�����
                 x3=x2;
                 y3=y2; 
        	}
        }
        //ȡɫ����
        else if("pic4".equals(command)){    	
        	//�õ���������Ǹ�����
    		int x=e.getXOnScreen();
    		int y=e.getYOnScreen();
    		
    		try {  			
				Robot robot = new Robot();//Robot���ʹ��			
				//�õ��������Ǹ�����
			    Rectangle rect = new Rectangle(x,y,1,1);
			    //���ɸþ��εĻ���ͼƬ
				BufferedImage bi =robot.createScreenCapture(rect);
				//�õ�ͼƬ�ı�����ɫ
				int  c =bi.getRGB(0, 0);
				//������ɫ���з�װ
				Color color = new Color(c);
				//��ȡɫ��ȡ����ͼƬ���óɻ��ʵ���ɫ
				db.c=color;
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
    	}         
    }  
 
	public void mouseClicked(MouseEvent e) {
		//�����ͼ��˫�����
		int count =e.getClickCount();
		if(count==2 && "pic13".equals(command)){
			Shape line = new Line(ox, oy, x2, y2,g.getColor(),gWidth);
         	line.Draw(g);
         	list.add(line);	
			flag=true;
		}
	}
 
	public void mouseEntered(MouseEvent e) {
		    color=db.c;//���û�����ɫ  
			g.setColor(color);
			g.setStroke(s1);
	}
 
	public void mouseExited(MouseEvent e) {
		
	}
 
	public void mouseDragged(MouseEvent e) {
		
		int x=e.getX();
		int y=e.getY();
		
		//���ʹ���
		if("pic6".equals(command)){
			
			Shape line = new Line(x1, y1, x, y,g.getColor(),1);
         	line.Draw(g);
         	list.add(line);
			x1=x;
			y1=y;
		}
		//��Ƥ������
		else if("pic11".equals(command)){
			db.c=Color.white;
			g.setColor(db.c);
			g.setStroke(s3);
			
			Shape line = new Line(x1, y1, x, y,g.getColor(),15);
         	line.Draw(g);
         	list.add(line);
			
			x1=x;
			y1=y;
		}
		//ˢ�ӹ���
		else if("pic7".equals(command)){
			g.setStroke(s2);//���û��� ��ϸ
			
			Shape line = new Line(x1, y1, x, y,g.getColor(),10);
         	line.Draw(g);
         	list.add(line);
         	
			x1=x;
			y1=y;
		}
		//��Ͱ����
		else if("pic8".equals(command)){
			//�������30��-15��15֮�������
			for (int i = 0; i < 30; i++) {
				int xp=r.nextInt(31)-15;
				int yp=r.nextInt(31)-15;
				//��x��y��������ԭ��
				
				Shape line = new Line(x+xp, y+yp, x+xp, y+yp,g.getColor(),1);
	         	line.Draw(g);
	         	list.add(line);
			}
			
		}				
	}
 
	public void mouseMoved(MouseEvent e) {
		
	} 
	 
}
