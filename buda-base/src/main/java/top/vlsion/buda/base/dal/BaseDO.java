package top.vlsion.buda.base.dal;

import top.vlsion.buda.base.enums.YesOrNo;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用的表结构字段
 *
 * @author : zhanghuang
 * @date : 2021-12-31 10:35
 */
public class BaseDO implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 是否删除
     *
     * @see top.vlsion.buda.base.enums.YesOrNo
     */
    private String isDeleted;

    /**
     * 插入前设置参数
     * 默认创建时间为当前时间，修改时间为当前时间，是否删除为n
     *
     * @param createBy 创建人
     * @param modifyBy 修改人
     */
    public void beforeInsert(String createBy, String modifyBy) {
        beforeInsert(createBy, new Date(), modifyBy, new Date(), YesOrNo.NO.getCode());
    }

    /**
     * 插入前设置参数
     *
     * @param createBy   创建人
     * @param createDate 创建日期
     * @param updateBy   修改人
     * @param updateDate 修改日期
     * @param yesOrNo    删除状态
     */
    public void beforeInsert(String createBy, Date createDate, String updateBy, Date updateDate, String yesOrNo) {
        setCreateBy(createBy);
        setCreateDate(createDate);
        setUpdateBy(updateBy);
        setUpdateDate(updateDate);
        setIsDeleted(yesOrNo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
