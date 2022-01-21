package top.vlsion.buda.rpc.register.simple.constant;

/**
 * 消息类型常量 表示仅支持的消息类型
 *
 * @author : zhanghuang
 * @date : 2022-01-17 14:40
 */
public final class MessageTypeConst {
    /**
     * 服务端注册请求
     */
    public static final String SERVER_REGISTER_REQ = "SERVER_REGISTER_REQ";
    /**
     * 服务端注销请求
     */
    public static final String SERVER_UN_REGISTER_REQ = "SERVER_UN_REGISTER_REQ";
    /**
     * 客户端订阅请求
     */
    public static final String CLIENT_SUBSCRIBE_REQ = "CLIENT_SUBSCRIBE_REQ";
    /**
     * 客户端取关请求
     */
    public static final String CLIENT_UN_SUBSCRIBE_REQ = "CLIENT_UN_SUBSCRIBE_REQ";
    /**
     * 客户端查询
     */
    public static final String CLIENT_LOOK_UP_SERVER_REQ = "CLIENT_LOOK_UP_SERVER_REQ";
    /**
     * 客户端查询服务接口
     */
    public static final String CLIENT_LOOK_UP_SERVER_RESP = "CLIENT_LOOK_UP_SERVER_RESP";
    /**
     * 服务端注册通知客户端请求
     */
    public static final String SERVER_REGISTER_NOTIFY_CLIENT_REQ = "SERVER_REGISTER_NOTIFY_CLIENT_REQ";
    /**
     * 服务端取消注册通知客户端请求
     */
    public static final String SERVER_UNREGISTER_NOTIFY_CLIENT_REQ = "SERVER_UNREGISTER_NOTIFY_CLIENT_REQ";
    /**
     * 注册中心新增通知
     */
    public static final String REGISTER_CENTER_ADD_NOTIFY = "REGISTER_CENTER_ADD_NOTIFY";
    /**
     * 注册中心销毁通知
     */
    public static final String REGISTER_CENTER_REMOVE_NOTIFY = "REGISTER_CENTER_REMOVE_NOTIFY";
    /**
     * 服务端心跳
     */
    public static final String SERVER_HEARTBEAT_REQ = "SERVER_HEARTBEAT_REQ";

    private MessageTypeConst() {
    }

}
