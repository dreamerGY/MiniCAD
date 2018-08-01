package myCAD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
 
public class DrawBorder extends JFrame{

	private static final long serialVersionUID = 6017720961776747017L;	
	public Color c=Color.RED; //声明颜色属性，并赋默认值	
	public JButton  bt ; //按钮属性	
	public JPanel panelcenter;	
	private Graphics2D g;
		
	ArrayList<Shape> list = new ArrayList<Shape>(); // 容器
	
	private String[] buttonName = {
			"取色","粗细","画笔","刷子","喷绘","文字",
			"直线","橡皮","矩形","多边","椭圆","圆角"
		};
	
	public void initFrame()throws Exception {		
		//设置窗体相关属性
		this.setSize(600,500);
		this.setTitle("MyCAD");
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
				
		addMenu(); // 把添加菜单作为一个方法封装起来
		
		//窗体添加主面板
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.add(panel);
				
		panelcenter = new JPanel(){
			private static final long serialVersionUID = -198046948784001221L;
			public void paint(Graphics g1) {
				g=(Graphics2D)g1;
				super.paint(g);            
				for (int i = 0; i <list.size(); i++) {
					Shape shape =(Shape)list.get(i);
					shape.Draw(g);
				}
			}
		};
	    panelcenter.setBackground(Color.white);
		panel.add(panelcenter);
		
		//主面板添加左面板
		JPanel panelleft = new JPanel();
		panelleft.setPreferredSize(new Dimension(60,0));
		panelleft.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		panelleft.setBackground(new Color(235,233,238));
		panel.add(panelleft,BorderLayout.WEST);
		
		//面板中添加按钮，按钮归类，统一管理

		ButtonGroup bg = new ButtonGroup();
		for(int i=4;i<16;i++){
			JRadioButton jrb = new JRadioButton(buttonName[i-4]);			
			//给按钮添加图片
			ImageIcon img1  = new ImageIcon("images/draw"+i+".jpg");
			ImageIcon img2  = new ImageIcon("images/draw"+i+"-1.jpg");
			ImageIcon img3  = new ImageIcon("images/draw"+i+"-2.jpg");
			ImageIcon img4  = new ImageIcon("images/draw"+i+"-3.jpg");
			jrb.setIcon(img1); //默认图片
			jrb.setRolloverIcon(img2); //鼠标停留在按钮上显示的图片
			jrb.setPressedIcon(img3); //鼠标按下按钮显示的图片
			jrb.setSelectedIcon(img4); //鼠标选中按钮显示的图片
			jrb.setBorder(null);//设置按钮没有边框
			//设置默认选中的按钮
			if(i==10){
				jrb.setSelected(true);
			}
			jrb.setActionCommand("pic"+i);
			
			bg.add(jrb);
			panelleft.add(jrb);
		}
		
		//主面板添加下方面板
		JPanel paneldown =new JPanel();
		paneldown.setPreferredSize(new Dimension(0,60));
		paneldown.setLayout(null);
		paneldown.setBackground(Color.gray);
		panel.add(paneldown, BorderLayout.SOUTH);
		
		//下方面板添加子面板
		JPanel paneldownchild = new JPanel();
		paneldownchild.setBackground(Color.cyan);
		paneldownchild.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		paneldownchild.setBounds(10,10,280,40);
		paneldown.add(paneldownchild);
		
		//按钮特效
		BevelBorder bb = new BevelBorder(0, Color.gray,Color.white);
		BevelBorder bb1 = new BevelBorder(1, Color.gray,Color.white);
		
		//下方子面板中的左面板
		JPanel left = new JPanel();
		left.setBackground(Color.white);
		left.setLayout(null);
		left.setBorder(bb);
		left.setPreferredSize(new Dimension(40,40));
		
		//左面板中的两棵颜色按钮
	    bt = new JButton();
		bt.setBounds(5, 5, 20, 20);
		bt.setBorder(bb1);
		bt.setBackground(Color.black);
		bt.setSize(20,20);
		JButton bt1 = new JButton();
		bt1.setBorder(bb1);
		bt1.setBounds(15,15,20,20);
		left.add(bt);
		left.add(bt1);
		
        //右面板
		JPanel right = new JPanel();
		right.setBackground(Color.BLUE);
		right.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		right.setPreferredSize(new Dimension(240,40));
		
		paneldownchild.add(left);
		paneldownchild.add(right);
		
		//给右面板的颜色按钮添加监听器，注意传递this对象
		ButtonHandler bHandler =new ButtonHandler(this);
		
		//颜色数组，用来设置按钮的背景颜色
		Color []colors = {new Color(0,56,67),new Color(89,3,14),new Color(189,3,14)
				,new Color(89,93,14),new Color(89,113,14),new Color(89,73,14)
				,new Color(89,3,14),new Color(89,3,14),new Color(29,83,14)
				,new Color(89,3,184),new Color(189,233,14),new Color(89,253,14)
				,new Color(89,93,14),new Color(89,89,94),new Color(1,3,14)
				,new Color(9,83,94),new Color(89,178,147),new Color(9,33,164)
				,new Color(34,23,14),new Color(89,173,154),new Color(8,193,194)
				,new Color(9,253,76),new Color(89,240,104),new Color(199,73,4)};
		
		//循环添加24个颜色按钮
		for(int i=0;i<24;i++){
			JButton bt3 = new JButton();
		    bt3.setBackground(colors[i]);
		    bt3.setPreferredSize(new Dimension(20,20));
		    bt3.setBorder(bb);
		    bt3.addActionListener(bHandler);
			right.add(bt3);
		}
		
		this.setVisible(true);				
		g=(Graphics2D)panelcenter.getGraphics(); //画笔必须在setVisible后才能拿		
		DrawHandler dHandler =new DrawHandler (g,bg,this,list); //传递画笔，按钮组管理对象，this对象				
		panelcenter.addMouseListener(dHandler); //添加普通鼠标监听器			
		panelcenter.addMouseMotionListener(dHandler); //添加鼠标拖动监听器			
	}
	
	public void addMenu()throws Exception{	
		JMenuBar bar = new JMenuBar(); //菜单条对象创建		
		JMenu menu=new JMenu("文件"); //菜单创建
		JMenu help=new JMenu("帮助");
		MenuHandler mHandler=new MenuHandler(this); //菜单项监听器创建
		//创建三个菜单项
		JMenuItem item0= new JMenuItem("新建");
		JMenuItem item= new JMenuItem("打开");
		JMenuItem item1= new JMenuItem("保存");
		JMenuItem content= new JMenuItem("查看帮助");
		//给每个菜单项添加监听器
		item0.addActionListener(mHandler);
		item.addActionListener(mHandler);
		item1.addActionListener(mHandler);
		content.addActionListener(mHandler);
		//将菜单条添加到窗体上
		this.setJMenuBar(bar);
		//将菜单添加到菜单条上
		bar.add(menu);
		bar.add(help);
		//将菜单项添加到菜单上
		menu.add(item0);
		menu.add(item);
		menu.add(item1);
		help.add(content);
	}	
}
