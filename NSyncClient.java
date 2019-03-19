package org.thrift;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.thrift.idl.CommonRet;
import org.thrift.idl.IProtocalService;

public class NSyncClient {
	public static void main(String[] args) {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(
					"localhost", 8080);
			TProtocolFactory protocol = new TCompactProtocol.Factory();
			IProtocalService.AsyncClient asyncClient = new IProtocalService.AsyncClient(
					protocol, clientManager, transport);
			System.out.println("Client calls .....");
			MethodCallback callBack = new MethodCallback();
			CommonRet commonRet = new CommonRet();
			asyncClient.commonAnswer(commonRet, callBack);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
