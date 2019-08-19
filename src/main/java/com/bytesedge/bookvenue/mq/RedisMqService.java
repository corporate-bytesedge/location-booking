package com.bytesedge.bookvenue.mq;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytesedge.bookvenue.core.ServerConfig;
import com.wedeploy.jrsmq.QueueMessage;
import com.wedeploy.jrsmq.RedisSMQ;
import com.wedeploy.jrsmq.RedisSMQConfig;

public class RedisMqService implements Runnable {
	private static Logger log = LoggerFactory.getLogger(RedisMqService.class);
	private String queueName = null;
	private Boolean keepRun = true;
	private static RedisSMQ rsmq = null;
	private static final String poolType = "mq";
	
	public static void init() throws Exception {
		RedisSMQConfig config = new RedisSMQConfig();
		config.host(ServerConfig.getStringProperty(ServerConfig.REDIS_IP_ADDR + poolType, "127.0.0.1"));
		config.port(ServerConfig.getIntegerProperty(ServerConfig.REDIS_PORT + poolType, 6379));
		config.timeout(30000);
		rsmq = new RedisSMQ(config);
		log.error("MSG : Successfully connected to RedisMqService. Creating required Queues...");
		
		// Start a Queue
		Set<String> queueList = rsmq.listQueues().exec();
		if(queueList != null && !queueList.isEmpty()) {
			boolean queueFound = false;
			for(String queue : queueList) {
				if(queue.equals("app01")) {
					// Queue found
					queueFound = true;
					break;
				}
			}
			if(queueFound) {
				log.error("MSG : Queue already Created.");
			} else {
				rsmq.createQueue().qname("app01").exec();
				log.error("MSG : Successfully Queue Created.");		
			}
		} else {
			rsmq.createQueue().qname("app01").exec();
			log.error("MSG : Successfully Queue Created.");			
		}
	}
	
	private RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandler() {
	    @Override
	    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
	    	log.error("####JOB REJECTED######" + r.toString() + " is rejected");
	    }
	};
	
	// Get the ThreadFactory implementation to use
	private ThreadFactory threadFactory = Executors.defaultThreadFactory();
	
	// creating the ThreadPoolExecutor
	private ThreadPoolExecutor executorPool = new ThreadPoolExecutor(1, 1, 180,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2048),
			threadFactory, rejectionHandler);
	
	public RedisMqService(String queueName) {
		this.queueName = queueName;
		
		// Create Queue
		
		// Start a Thread
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public static String sendMessage(String queueName, String msg) {
		return rsmq.sendMessage().qname(queueName).message(msg).exec();
	}
	
	@Override
	public void run() {
		while (keepRun) {
			try {
				QueueMessage msg = rsmq.receiveMessage().qname(queueName).exec();
				if(msg != null) {
					// Process this message
					executorPool.execute(new MsgDataWorker(msg.id(), msg.message()));
				} else {
					// Wait for some time
					try {
						keepRun.wait(60000);
					} catch (InterruptedException e1) {
					}
				}
			} catch (Exception e) {
				synchronized(keepRun) {
					try {
						keepRun.wait(60000);
					} catch (InterruptedException e1) {
					}
				}
			}
		}
	}
}