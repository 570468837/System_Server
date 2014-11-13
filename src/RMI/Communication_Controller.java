package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ResultMessage.ResultMessage;

public class Communication_Controller extends UnicastRemoteObject implements Communication{

	protected Communication_Controller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void voidCommand(String command,Object PO) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultMessage messageCommand(String command, Object PO) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}
	
}
