package top.vlsiion.buda.demo.batch.listener;

import org.springframework.batch.core.ItemReadListener;

/**
 * @author : zhanghuang
 * @date : 2022-03-23 14:43
 */
public class PkslowReadListener implements ItemReadListener {
    @Override
    public void beforeRead() {
        System.out.println("beforeRead");
    }

    @Override
    public void afterRead(Object o) {
        System.out.println("afterRead");
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("onReadError");
    }
}
