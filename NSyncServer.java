package org.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.thrift.idl.IProtocalService;
import org.thrift.idl.IProtocalService.Iface;
import org.thrift.idl.impl.ProtocalService;

public class NSyncServer {
	private void start() {
		try {
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(
					8080);
			IProtocalService.Processor<Iface> processor = new IProtocalService.Processor<Iface>(
					new ProtocalService());
			TNonblockingServer.Args arg = new TNonblockingServer.Args(
					serverTransport);
			// 高效率的、密集的二进制编码格式进行数据传输
			arg.protocolFactory(new TCompactProtocol.Factory());
			arg.transportFactory(new TFramedTransport.Factory());
			arg.processorFactory(new TProcessorFactory(processor));
			System.out.println("thrift server start");
			TServer server = new TNonblockingServer(arg);
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		NSyncServer srv = new NSyncServer();
		srv.start();
	}

}