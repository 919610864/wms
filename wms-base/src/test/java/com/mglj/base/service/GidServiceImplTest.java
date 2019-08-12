//package com.mglj.base.service;
//
//
//import com.mglj.base.domain.GidServer;
//import com.mglj.base.service.api.GidServerService;
//import com.mglj.base.service.impl.GidServiceImpl;
//import com.mglj.base.util.NetUtils;
//import mockit.Expectations;
//import mockit.Injectable;
//import mockit.Tested;
//import org.junit.Test;
//
//import java.net.UnknownHostException;
//import java.security.SecureRandom;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//
//import static org.junit.Assert.assertEquals;
//
//public class GidServiceImplTest {
//
//	@Tested
//	GidServiceImpl gidService;
//
//	@Injectable
//	GidServerService gidServerService;
//
//	private AtomicLong count = new AtomicLong(0);
//	private Set<Long> set = new HashSet<Long>();
//	private AtomicInteger repeat = new AtomicInteger(0);
//	AtomicInteger incr = new AtomicInteger(new SecureRandom().nextInt(4096));
//
//	@Test
//	public void test() {
//		assertEquals(4096, 0x1000);
//		assertEquals(4095, 0x0FFF);
//		assertEquals(1023, 0x03FF);
//		assertEquals(1024, 0x0400);
//		long millis = System.currentTimeMillis();
//		System.out.println((millis << 22) | (8 << 12) | (incr.incrementAndGet() & 0x0FFF));
//
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTimeInMillis(0);
//		System.out.println(calendar.getTimeInMillis());
//		System.out.println(calendar.toString());
//		Calendar calendar0 = new GregorianCalendar(1970, 0, 1);
//		System.out.println(calendar0.getTimeInMillis());
//		Calendar calendar1 = new GregorianCalendar(2000, 0, 1);
//		System.out.println(calendar1.getTimeInMillis());
//		Calendar calendar2 = new GregorianCalendar(2017, 7, 1);
//		System.out.println(calendar2.getTimeInMillis());
//		System.out.println(calendar2.toString());
//		System.out.println("offset: " + (System.currentTimeMillis() - calendar2.getTimeInMillis()));
//		Calendar calendar3 = Calendar.getInstance();
//		System.out.println(calendar3.getTimeInMillis());
//		System.out.println(calendar3.toString());
//		System.out.println(System.currentTimeMillis());
//		System.out.println(new Date().getTime());
//	}
//
//	@Test
//	public void testIncr() {
//		AtomicInteger incr = new AtomicInteger(Integer.MIN_VALUE);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		incr = new AtomicInteger(0);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		incr = new AtomicInteger(Integer.MAX_VALUE);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//		System.out.println(incr.getAndIncrement() % 256 & 0x7F);
//
//	}
//
//	@Test
//	public void testFor() {
//		AtomicInteger incr = new AtomicInteger(0);
//		int sequence = incr.addAndGet(5);
//		System.out.println("sequence=" + sequence);
//		for(int i = 5 - 1; i >= 0; i--) {
//			System.out.println(((sequence - i) % 256) & 0x7F);
//    	}
//	}
//
//	@Test
//	public void testThread1() throws InterruptedException, ExecutionException, UnknownHostException {
//		String ip = NetUtils.getHostAddress();
//		GidServer gidServer = new GidServer();
//		gidServer.setId(1);
//		gidServer.setIp(ip);
//		gidServer.setSequence(10);
//		new Expectations() {{
//			gidServerService.findOneGidServerByIp(ip);
//			result = gidServer;
//		}};
//
//		ExecutorService service = Executors.newFixedThreadPool(100);
//		long cost = System.currentTimeMillis();
//		for(;;) {
//			service.execute(new Runnable() {
//				@Override
//				public void run() {
//					Long s = gidService.generate();
//					if(!set.add(s)) {
//						repeat.incrementAndGet();
//					}
//				}
//			});
//			if(count.incrementAndGet() > 1000000) {
//				break;
//			}
//		}
//		service.awaitTermination(5000, TimeUnit.MILLISECONDS);
//		System.out.println("cost=" + (System.currentTimeMillis() - cost));
//		System.out.println("set=" + set.size() + ", repeat=" + repeat);
//	}
//
//	@Test
//	public void testThread2() throws InterruptedException, ExecutionException, UnknownHostException {
//		String ip = NetUtils.getHostAddress();
//		GidServer gidServer = new GidServer();
//		gidServer.setId(1);
//		gidServer.setIp(ip);
//		gidServer.setSequence(10);
//		new Expectations() {{
//			gidServerService.findOneGidServerByIp(ip);
//			result = gidServer;
//		}};
//
//		System.out.println(gidService.generate());
//		int threadSize = 1;
//		long cost = System.currentTimeMillis();
//		CountDownLatch latch = new CountDownLatch(threadSize);
//		for(int i = 0; i < threadSize; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for(;;) {
//						gidService.generate();
////						Long s = gidService.generate();
////						if(!set.add(s)) {
////							repeat.incrementAndGet();
////						}
//						if(count.incrementAndGet() > 1000000) {
//							latch.countDown();
//							break;
//						}
//					}
//				}
//			}).start();
//		}
//		latch.await();
//		System.out.println("cost=" + (System.currentTimeMillis() - cost));
////		System.out.println("set=" + set.size() + ", repeat=" + repeat);
//	}
//
//	@Test
//	public void testMove() {
//		int a = 1;
//		System.out.println(a + ", " + Long.toBinaryString(a));
//		System.out.println((a << 8) + ", " + Long.toBinaryString(a << 8));
//		long millis = System.currentTimeMillis();
//		String s = Long.toBinaryString(millis);
//		System.out.println(s + ", " + s.length());
//		s = Long.toBinaryString(millis << 13);
//		System.out.println((millis << 13));
//		System.out.println(s + ", " + s.length());
//		s = Long.toBinaryString(millis << 13 | a << 8);
//		System.out.println(s + ", " + s.length());
//	}
//
//}
