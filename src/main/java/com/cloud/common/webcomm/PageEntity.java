package com.cloud.common.webcomm;

public class PageEntity {
    /**
     * field comment: 是否启用分页
     */
    private boolean usePage = Boolean.FALSE;

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
        if(usePage){
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

    public boolean getUsePage() {
        return usePage;
    }

    public void setUsePage(boolean usePage) {
        this.usePage = usePage;
    }
}
