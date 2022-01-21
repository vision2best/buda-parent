package top.vlsion.buda.rpc.register.domain.message.body;

import java.io.Serializable;

/**
 * 服务心跳检测
 *
 * @author : zhanghuang
 * @date : 2022-01-17 13:38
 */
public class ServerHeartbeatBody implements Serializable {

    private String ip;

    private int port;

    private long time;

    public String ip() {
        return ip;
    }

    public ServerHeartbeatBody ip(String ip) {
        this.ip = ip;
        return this;
    }

    public int port() {
        return port;
    }

    public ServerHeartbeatBody port(int port) {
        this.port = port;
        return this;
    }

    public long time() {
        return time;
    }

    public ServerHeartbeatBody time(long time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return "ServerHeartbeatBody{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", time=" + time +
                '}';
    }
}
