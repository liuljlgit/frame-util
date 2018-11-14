package com.cloud.common.complexquery;

import com.cloud.common.webcomm.PageEntity;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 复查查询
 */
public class QueryExample extends PageEntity implements Serializable {
    private String orderByClause;
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

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }


}
