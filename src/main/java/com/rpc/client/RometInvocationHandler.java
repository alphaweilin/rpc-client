package com.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.rpc.server.api.RpcRequest;

public class RometInvocationHandler implements InvocationHandler {
	private String host;
	private int port;	

	public RometInvocationHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Hello Proxy: invoke");
		
		//1组装数据报文
		RpcRequest request = new RpcRequest();
		request.setClassName(method.getDeclaringClass().getName());
		request.setMethodName(method.getName());
		request.setParameters(args);
		request.setTypes(method.getParameterTypes());	
		//2建立网络通信
		RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
		//3发起远程传输(result)
		return rpcNetTransport.send(request);
	}

}
