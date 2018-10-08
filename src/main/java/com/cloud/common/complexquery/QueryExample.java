package com.cloud.common.complexquery;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 复查查询
 */
public class QueryExample {
    private String orderByClause;
    private int index;
    private int pageSize;
    private List<Criteria> criterias;

    public Criteria andCriteria(){
        Criteria criteria = new Criteria();
        criteria.setOpt("and");
        addCriteria(criteria);
        return criteria;
    }

    public Criteria orCriteria(){
        Criteria criteria = new Criteria();
        criteria.setOpt("or");
        addCriteria(criteria);
        return criteria;
    }

    private void addCriteria(Criteria criteria){
        if(CollectionUtils.isEmpty(criterias)){
            criterias = new ArrayList<>();
        }
        criterias.add(criteria);
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }
}
