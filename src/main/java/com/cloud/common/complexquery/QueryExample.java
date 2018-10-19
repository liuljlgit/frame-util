package com.cloud.common.complexquery;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 复查查询
 */
public class QueryExample implements Serializable {
    private String orderByClause;
    private Integer index;
    private Integer pageSize;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }


}
