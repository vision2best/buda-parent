package top.vlsiion.buda.demo.batch.listener;


import javax.batch.api.chunk.listener.ItemWriteListener;
import java.util.List;

/**
 * @author : zhanghuang
 * @date : 2022-03-23 14:39
 */
public class PkslowWriteListener implements ItemWriteListener {

    @Override
    public void beforeWrite(List<Object> items) throws Exception {
        System.out.println("beforeWrite" + items);
    }


    @Override
    public void afterWrite(List<Object> items) throws Exception {
        System.out.println("afterWrite" + items);
    }


    @Override
    public void onWriteError(List<Object> items, Exception ex) throws Exception {
        System.out.println("onWriteError"+items);
    }
}
