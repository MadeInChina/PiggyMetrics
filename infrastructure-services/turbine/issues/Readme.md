curl localhost:8989
curl: (52) Empty reply from server

18-05-06 14:24:03.307  WARN 44237 --- [o-eventloop-3-1] i.n.c.AbstractChannelHandlerContext      : An exception '{}' [enable DEBUG level for full stacktrace] was thrown by a user handler's exceptionCaught() method while handling the following exception:

java.lang.NoSuchMethodError: rx.internal.operators.NotificationLite.instance()Lrx/internal/operators/NotificationLite;
	at io.reactivex.netty.protocol.http.UnicastContentSubject$State$BufferedObserver.<init>(UnicastContentSubject.java:243) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.UnicastContentSubject$State$BufferedObserver.<init>(UnicastContentSubject.java:241) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.UnicastContentSubject$State.<init>(UnicastContentSubject.java:197) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.UnicastContentSubject.create(UnicastContentSubject.java:132) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.UnicastContentSubject.create(UnicastContentSubject.java:122) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.UnicastContentSubject.create(UnicastContentSubject.java:117) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.server.ServerRequestResponseConverter$RequestState.createRxRequest(ServerRequestResponseConverter.java:176) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.server.ServerRequestResponseConverter$RequestState.access$100(ServerRequestResponseConverter.java:168) ~[rx-netty-0.3.18.jar:na]
	at io.reactivex.netty.protocol.http.server
	(ServerRequestResponseConverter.java:176) ~[rx-netty-0.3.18.jar:na]
  at io.reactivex.netty.protocol.http.server.ServerRequestResponseConverter$RequestState.access$100(ServerRequestResponseConverter.java:168) ~[rx-netty-0.3.18.jar:na]



MacBook-Pro:turbine hanrenwei$ mvn dependency:tree|grep 0.4.9
[INFO] |  |  |  |  +- io.reactivex:rxnetty-contexts:jar:0.4.9:runtime
[INFO] |  |  |  |  \- io.reactivex:rxnetty-servo:jar:0.4.9:runtime
[INFO] |  |  |  \- io.reactivex:rxnetty:jar:0.4.9:runtime
MacBook-Pro:turbine hanrenwei$ mvn dependency:tree|grep 0.3.18
[INFO] |  |  +- com.netflix.rxnetty:rx-netty:jar:0.3.18:compile


jar confilct
+- org.springframework.cloud:spring-cloud-starter-netflix-turbine-stream:jar:2.0.0.RC1:compile
|  +- com.netflix.turbine:turbine-core:jar:2.0.0-DP.2:compile
[INFO] |  |  +- net.sf.jopt-simple:jopt-simple:jar:4.8:compile
[INFO] |  |  +- com.netflix.rxnetty:rx-netty:jar:0.3.18:compile
[INFO] |  |  |  +- io.netty:netty-codec-http:jar:4.1.23.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec:jar:4.1.23.Final:compile
[INFO] |  |  |  \- io.netty:netty-transport-native-epoll:jar:4.1.23.Final:compile
[INFO] |  |  |     +- io.netty:netty-common:jar:4.1.23.Final:compile
[INFO] |  |  |     +- io.netty:netty-buffer:jar:4.1.23.Final:compile
[INFO] |  |  |     +- io.netty:netty-transport-native-unix-common:jar:4.1.23.Final:compile
[INFO] |  |  |     \- io.netty:netty-transport:jar:4.1.23.Final:compile
[INFO] |  |  |        \- io.netty:netty-resolver:jar:4.1.23.Final:compile
[INFO] |  |  +- org.codehaus.jackson:jackson-mapper-asl:jar:1.9.2:compile
[INFO] |  |  \- org.codehaus.jackson:jackson-core-asl:jar:1.9.2:compile


[INFO] +- org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:jar:2.0.0.RC1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-netflix-ribbon:jar:2.0.0.RC1:compile
[INFO] |  |  +- com.netflix.ribbon:ribbon:jar:2.2.5:compile
[INFO] |  |  |  +- com.netflix.ribbon:ribbon-transport:jar:2.2.5:runtime
[INFO] |  |  |  |  +- io.reactivex:rxnetty-contexts:jar:0.4.9:runtime
[INFO] |  |  |  |  \- io.reactivex:rxnetty-servo:jar:0.4.9:runtime
[INFO] |  |  |  \- io.reactivex:rxnetty:jar:0.4.9:runtime




================
curl localhost:8989

8-05-06 15:14:23.690  WARN 46633 --- [on(3)-127.0.0.1] c.c.c.ConfigServicePropertySourceLocator : Could not locate PropertySource: I/O error on GET request for "http://localhost:8888/application/default": Connection refused; nested exception is java.net.ConnectException: Connection refused
2018-05-06 15:14:39.916  INFO 46633 --- [o-eventloop-3-1] o.s.c.n.t.s.TurbineStreamConfiguration   : SSE Request Received
2018-05-06 15:14:49.562  INFO 46633 --- [o-eventloop-3-1] o.s.c.n.t.s.TurbineStreamConfiguration   : Starting aggregation
Exception in thread "RxComputationScheduler-1" java.lang.IllegalStateException: Fatal Exception thrown on Scheduler.Worker thread.
	at rx.internal.schedulers.ScheduledAction.run(ScheduledAction.java:59)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:266)
	at java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
Caused by: java.lang.AbstractMethodError
	at io.netty.util.ReferenceCountUtil.touch(ReferenceCountUtil.java:77)
	at io.netty.channel.DefaultChannelPipeline.touch(DefaultChannelPipeline.java:116)
	at io.netty.channel.AbstractChannelHandlerContext.write(AbstractChannelHandlerContext.java:810)
	at io.netty.channel.AbstractChannelHandlerContext.write(AbstractChannelHandlerContext.java:723)
	at io.netty.channel.AbstractChannelHandlerContext.write(AbstractChannelHandlerContext.java:704)
	at io.netty.channel.DefaultChannelPipeline.write(DefaultChannelPipeline.java:1056)
	at io.netty.channel.AbstractChannel.write(AbstractChannel.java:290)


MacBook-Pro:turbine hanrenwei$ mvn dependency:tree|grep 0.3.18
[INFO] |  |  +- com.netflix.rxnetty:rx-netty:jar:0.3.18:compile
MacBook-Pro:turbine hanrenwei$ mvn dependency:tree|grep 0.4.9
[INFO] |  |  |  |  |  +- io.reactivex:rxnetty-contexts:jar:0.4.9:runtime
[INFO] |  |  |  |  |  \- io.reactivex:rxnetty-servo:jar:0.4.9:runtime
[INFO] |  |  |  |  \- io.reactivex:rxnetty:jar:0.4.9:runtime

