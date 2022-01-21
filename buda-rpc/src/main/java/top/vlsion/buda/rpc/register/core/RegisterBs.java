package top.vlsion.buda.rpc.register.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import top.vlsion.buda.common.utils.ArgsUtil;
import top.vlsion.buda.rpc.common.exception.RpcRuntimeException;
import top.vlsion.buda.rpc.register.api.config.RegisterConfig;
import top.vlsion.buda.rpc.register.simple.client.RegisterClientService;
import top.vlsion.buda.rpc.register.simple.client.impl.DefaultRegisterClientService;
import top.vlsion.buda.rpc.register.simple.handler.RegisterCenterServerHandler;
import top.vlsion.buda.rpc.register.simple.server.RegisterServerService;
import top.vlsion.buda.rpc.register.simple.server.impl.DefaultRegisterServerService;

/**
 * 注册中心默认配置
 *
 * @author : zhanghuang
 * @date : 2022-01-17 15:54
 */
public class RegisterBs implements RegisterConfig {
    /**
     * 服务端
     */
    private final RegisterServerService registerServerService;

    /**
     * 客户端
     */
    private final RegisterClientService registerClientService;

    /**
     * 端口
     */
    private int port;

    private RegisterBs() {
        registerServerService = new DefaultRegisterServerService();
        registerClientService = new DefaultRegisterClientService();
    }

    public static RegisterBs newInstance() {
        RegisterBs registerBs = new RegisterBs();
        registerBs.port(9527);
        return registerBs;
    }

    /**
     * 注册中心服务端口
     *
     * @param port 端口号
     * @return this
     */
    @Override
    public RegisterConfig port(int port) {
        ArgsUtil.notNegative(port, "port");
        this.port = port;
        return this;
    }

    /**
     * 启动服务
     *
     * @return this
     */
    @Override
    public RegisterConfig start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(workerGroup, bossGroup)
                    .channel(NioServerSocketChannel.class)
                    // 打印日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new RegisterCenterServerHandler())
                    // 这个参数影响的是还没有被accept 取出的连接
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 这个参数只是过一段时间内客户端没有响应，服务端会发送一个 ack 包，以判断客户端是否还活着。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的链接
            ChannelFuture channelFuture = serverBootstrap.bind(port).syncUninterruptibly();

        } catch (Exception e) {
            throw new RpcRuntimeException(e);
        }
        return this;
    }
}
