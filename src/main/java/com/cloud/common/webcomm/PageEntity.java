package com.cloud.common.webcomm;

import com.cloud.common.constant.IConst;

import java.util.Objects;

public class PageEntity{

    /**
     * field comment: 当前页,如果是-1,不启用分页
     */
    private Integer page = IConst.PAGE_NO_USE;

    /**
     * field comment: 页面大小
     */
    private Integer pageSize = IConst.DEFAULT_PAGE_SIZE;

    /**
     * field comment: 总数
     */
    private Integer total;

    /**
     * field comment: 总页数
     */
    private Integer totalPage;

    /**
     * field comment: 分页操作时，sql limit index,pageSize
     */
    private Integer index;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        if(Objects.nonNull(total) && Objects.nonNull(pageSize)){
            return (total-1)/pageSize+1;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getIndex() {
        return (page-1)*pageSize;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
