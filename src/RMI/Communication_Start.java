package RMI;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;


public class Communication_Start {
	public static void main(String []args){
		
		//用来关闭服务器的Frame
		JFrame serverFrame = new JFrame("高锰酸钾服务器");
		serverFrame.setSize(250, 100);
		serverFrame.setLocationRelativeTo(null);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverFrame.setVisible(true);
		
		try {
			Communication server=new Communication_Controller(); 
			LocateRegistry.createRegistry(8888);
			 try {
				Naming.bind("rmi://localhost:8888/MyServer",server);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("发生URL畸形异常！"); 
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("发生重复绑定对象异常！"); 
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
