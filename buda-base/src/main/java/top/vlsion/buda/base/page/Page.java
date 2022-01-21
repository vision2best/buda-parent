package top.vlsion.buda.base.page;

import java.io.Serializable;

/**
 * 分页信息
 *
 * @author : zhanghuang
 * @date : 2021-12-31 11:03
 */
public class Page implements Serializable {
    /**
     * 页大小
     * 从 0 开始
     */
    private int pageSize = 1;

    /**
     * 当前页码
     * 默认页大小为 10
     */
    private int pageIndex = 10;

    /**
     * 总量
     */
    private int total;

    /**
     * 页总量
     */
    private int pageTotal;

    public Page(int pageSize, int pageIndex, int total) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.total = total;
    }

    public Page(int pageSize, int pageIndex, int total, int pageTotal) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.total = total;
        this.pageTotal = pageTotal;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
