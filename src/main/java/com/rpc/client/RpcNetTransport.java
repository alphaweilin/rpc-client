package com.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.rpc.server.api.RpcRequest;

public class RpcNetTransport {
	private String host;
	private int port;
	
	public RpcNetTransport(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	//get connection
	private Socket newSocket() throws IOException {
		Socket socket = null;
		socket = new Socket(host, port);
		return socket;
	}
	
	
	public Object send(RpcRequest request) {
		ObjectOutputStream objectOutputStream=null;
		ObjectInputStream objectInputStream=null;
		try {
			Socket socket = newSocket();
			//1写入流到网络
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(request);
			objectOutputStream.flush();
			
			//2读取返回值
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			return objectInputStream.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
}
