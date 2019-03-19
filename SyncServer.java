package org.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.thrift.idl.IProtocalService;
import org.thrift.idl.IProtocalService.Iface;
import org.thrift.idl.impl.ProtocalService;

public class SyncServer {

	private void start() {
		try {
			TServerSocket serverTransport = new TServerSocket(8080);
			IProtocalService.Processor<Iface> processor = new IProtocalService.Processor<Iface>(
					new ProtocalService());
			TThreadPoolServer.Args arg = new TThreadPoolServer.Args(
					serverTransport);
			// 高效率的、密集的二进制编码格式进行数据传输
			arg.protocolFactory(new TCompactProtocol.Factory());
			arg.transportFactory(new TFramedTransport.Factory());
			arg.processorFactory(new TProcessorFactory(processor));
			arg.maxWorkerThreads(10000);
			arg.minWorkerThreads(50);
			System.out.println("thrift server start");
			TThreadPoolServer server = new TThreadPoolServer(arg);
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SyncServer srv = new SyncServer();
		srv.start();
	}
}
