package org.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.thrift.idl.CommonRet;
import org.thrift.idl.IProtocalService;

public class SyncClient {
	private IProtocalService.Client client;

	public void init() throws TTransportException {
		TFramedTransport transport = new TFramedTransport(new TSocket(
				"localhost", 8080));
		TCompactProtocol protocol = new TCompactProtocol(transport);
		client = new IProtocalService.Client(protocol);
		transport.open();
	}

	public IProtocalService.Client getClient() {
		return client;
	}

	public void setClient(IProtocalService.Client client) {
		this.client = client;
	}

	public static void main(String[] args) {
		SyncClient cli = new SyncClient();
		try {
			cli.init();
			CommonRet commonRet = new CommonRet();
			for (int i = 0; i < 100000; i++) {
				boolean b = cli.getClient().commonAnswer(commonRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
