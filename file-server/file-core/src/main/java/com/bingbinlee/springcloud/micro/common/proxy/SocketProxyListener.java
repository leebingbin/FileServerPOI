package com.bingbinlee.springcloud.micro.common.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketProxyListener{

	private static Logger LOGGER = LoggerFactory.getLogger(SocketProxyListener.class);

	/**
	 * @description Listen Port, Proxy Server Config
	 */
	@Value("${proxy.config}")
	private String proxyConfig;
	
	public void startProxyServer() {
		// 获取所有代理配置
		String[] proxyServer = proxyConfig.split("\\|");
		
		for (int i=0;i < proxyServer.length; i++) {
			if (proxyServer[i] != null && !"".equals(proxyServer[i])) {
				
				String[] oneProxyConfig = proxyServer[i].split(",");
				
				// 从配置文件中获取监听端口
				int listenPort = Integer.parseInt(oneProxyConfig[0]);
				String toUrlServer = oneProxyConfig[1];   // 代理服务IP
		        String toPortserver = oneProxyConfig[2];  // 代理服务端口
				
				// 如果端口已经被占用返回
				if(this.isLoclePortUsing(listenPort)){
					LOGGER.info("============提示：端口  " + listenPort + "  已被占用");
					return;
				}
				// 新定义线程来处理
				new Thread(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.SSS");
					ServerSocket serverSocket = null;
					try {
						serverSocket = new ServerSocket(listenPort);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					final ExecutorService tpe = Executors.newCachedThreadPool();
					LOGGER.info("==================================================");
					LOGGER.info("Proxy Server Start At " + sdf.format(new Date()));
					LOGGER.info("listening port:" + listenPort + " ..........");
					LOGGER.info("proxy server:" + toUrlServer + " : " + toPortserver +" ..........");
					LOGGER.info("==================================================");

					while (true) {
						Socket socket = null;
						try {
							socket = serverSocket.accept();
							socket.setKeepAlive(true);
							// 加入任务列表，等待处理
							tpe.execute(new ProxyTask(socket, toUrlServer, toPortserver));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}

	/**
	 *  true:already in using  false:not using
	 * @param port
	 */
	public static boolean isLoclePortUsing(int port){
		boolean flag = true;
		try {
			flag = isPortUsing("127.0.0.1", port);
		} catch (Exception e) {
		}
		return flag;
	}

	/***
	 *  true:already in using  false:not using
	 * @param host
	 * @param port
	 * @throws UnknownHostException
	 */
	public static boolean isPortUsing(String host,int port) throws UnknownHostException{
		boolean flag = false;
		InetAddress theAddress = InetAddress.getByName(host);
		try {
			Socket socket = new Socket(theAddress,port);
			flag = true;
			socket.close();
		} catch (IOException e) {

		}
		return flag;
	}

	/**
	 * 启动代理服务入口
	 * @param args
	 */
	public static void main(String[] args) {
		SocketProxyListener listener = new SocketProxyListener();
		listener.startProxyServer();
	}

}
