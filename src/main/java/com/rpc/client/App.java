package com.rpc.client;

import org.rpc.server.api.IHelloService;

public class App {
	
	public static void main(String[] args) {
		RpcProxyClient rpcProxyClient = new RpcProxyClient();
		IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
		
		System.out.println(helloService.sayHello("William"));
		
	}
}
