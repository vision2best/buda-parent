package top.vlsion.buda.rpc.register;

import top.vlsion.buda.rpc.register.core.RegisterBs;

/**
 * @author : zhanghuang
 * @date : 2022-01-17 16:13
 */
public class RegisterTest {
    public static void main(String[] args) {
        RegisterBs.newInstance().start();
    }
}
