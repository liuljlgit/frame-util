package com.cloud.common.complexquery;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Criteria {

    /**
     * field comment:判断是否有值合法
     */
    public boolean isValid() {
        return criterions.size() > 0;
    }

    /**
     * field comment:一个Example有多个Criteria,
     */
    private List<Criterion> criterions;

    /**
     * field comment:类型,是and还是or 这个字段是为了两个表达式optStr CriteriaA optStr CriteriaB 如果是最前面一个Criteria,那么应该设置为""
     */
    private String opt;

    /**
     * IsNull、IsNotNull
     * @param condition
     */
    public void addCriterion(String condition) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setNoValue(true);
        criterions.add(criterion);
    }

    /**
     * EqualTo、NotEqualTo、GreaterThan、GreaterThanOrEqualTo、LessThan、LessThanOrEqualTo、Like、NotLike、In、NotIn
     * @param condition
     * @param value
     */
    public void addCriterion(String condition, Object value) {
        if (value == null) {
            throw new RuntimeException("value cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        if(value instanceof Collection){
            criterion.setList(value);
            criterion.setListValue(true);
        }else{
            criterion.setValue1(value);
            criterion.setOneValue(true);
        }
        criterions.add(criterion);
    }

    /**
     * Between、NotBetween
     * @param condition
     * @param value1
     * @param value2
     */
    public void addCriterion(String condition, Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("value1 and value2 cannot be null");
        }
        if(CollectionUtils.isEmpty(criterions)){
            criterions = new ArrayList<>();
        }
        Criterion criterion = new Criterion();
        criterion.setCondition(condition);
        criterion.setValue1(value1);
        criterion.setValue2(value2);
        criterion.setSecondValue(true);
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
