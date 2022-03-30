package top.vlsiion.buda.demo.batch.listener;

import org.springframework.batch.core.ItemProcessListener;

/**
 * @author : zhanghuang
 * @date : 2022-03-23 14:45
 */
public class PkslowProcessListener implements ItemProcessListener {
    @Override
    public void beforeProcess(Object o) {
        System.out.println("beforeProcess"+o.toString());
    }

    @Override
    public void afterProcess(Object o, Object o2) {
        System.out.println("afterProcess");
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        System.out.println("onProcessError");
    }
}

