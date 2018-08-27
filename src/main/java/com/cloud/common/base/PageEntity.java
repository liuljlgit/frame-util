package com.cloud.common.base;

public class PageEntity {
    /**
     * field comment: 当前页
     */
    private Integer page = 1;

    /**
     * field comment: 页面大小
     */
    private Integer pageSize = 1000;

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
        return (total-1)/pageSize+1;
    }

    public Integer getIndex() {
        return (page-1)*pageSize;
    }
}
