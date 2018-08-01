package myCAD;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//�˵���������ࣺ����ʵ���˲�ͬ�˵�����ʱ���߼�
public class MenuHandler implements ActionListener{
	
	public DrawBorder paint;
	
	//���췽��
	public MenuHandler(DrawBorder paint){
		this.paint=paint;
		
	}
	//�������ľ���ʵ���߼�
	public void actionPerformed(ActionEvent e) {		
		//�ж����ĸ��˵������
		String command =e.getActionCommand();		
		
		// �½���ʱ��ֻ��Ҫ�������������еĶ�����գ�Ȼ���м�����ػ�Ϳ�����		 
		if("�½�".equals(command)){			
			int value=JOptionPane.showConfirmDialog(null, "�Ƿ���Ҫ���浱ǰ�ļ���", "��ʾ��Ϣ", 0);
			if(value==0){				
				saveFile();
			}
			if(value==1){
				paint.list.removeAll(paint.list);
				paint.panelcenter.repaint();
			}
		}
		/*
		 * ���߼�ʵ�֣�
		 * ������򿪲˵���ʱ������Ӧ�������������Ķ�����Ȼ������ػ�
		 * Ȼ���ٰѴ򿪵��ļ����ö�������������
		 * ���������Ϣȡ������ת������Ӧ��ͼ�ζ���
		 * ��ȡ������ͼ�ζ�����ӵ���������
		 * �����м仭�壬��ȡ������ͼ�ν��л���
		 */
		else if("��".equals(command)){			
			int value=JOptionPane.showConfirmDialog(null, "�Ƿ���Ҫ���浱ǰ�ļ���", "��ʾ��Ϣ", 0);
			if(value==0){				
				saveFile();
				
			}
			if(value==1){
				//�����������Ķ���
				paint.list.removeAll(paint.list);
				paint.panelcenter.repaint();
				
				try {
					//����ѡ��Ի���ѡ����Ҫ������ļ�
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File file =chooser.getSelectedFile();
					//���Ϊѡ���ļ�
					if(file==null){
						JOptionPane.showMessageDialog(null, "û��ѡ���ļ�");
					}
					else {
						//ѡ������Ӧ���ļ��������ѡ�е��ļ���������������
						FileInputStream fis = new FileInputStream(file);
						ObjectInputStream ois = new ObjectInputStream(fis);
						//���������Ķ���ת���ɸ��������������н���
						ArrayList<Shape> list =(ArrayList<Shape>)ois.readObject();
						//������������ľ�����󣬽�ȡ�����Ķ��󱣴浽��������
						for (int i = 0; i <list.size(); i++) {
							Shape shape=(Shape)list.get(i);
							paint.list.add(shape);
							//�������Ļ����repaint()�����������������ͼ�λ��Ƴ���
							paint.panelcenter.repaint();
						}
						ois.close();
					}					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}									
		}
		else if("����".equals(command)){			
			saveFile();						
		}	
	}
	
	public void saveFile(){
		//ѡ��Ҫ�����λ���Լ��ļ����ֺ���Ϣ
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		File file =chooser.getSelectedFile();
		
		if(file==null){
			JOptionPane.showMessageDialog(null, "û��ѡ���ļ�");
		}
		else {
			try {
				//����Ҫ������ļ��������������
				FileOutputStream fis = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fis);
				//���������������Ƶ�ͼ�����ö�����ȫ��д��ѡ�е��ļ���
				oos.writeObject(paint.list);
			    JOptionPane.showMessageDialog(null, "����ɹ���");
				oos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
 
}