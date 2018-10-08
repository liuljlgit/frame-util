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
        criterions.add(criterion);
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


    /*以下为common的sql语句*/
    public void andColIsNull(String col){
        addCriterion("and "+col+" is null");
    }
    public void orColIsNull(String col){
        addCriterion("or "+col+" is null");
    }
    public void andColIsNotNull(String col){
        addCriterion("and "+col+" is not null");
    }
    public void orColIsNotNull(String col){
        addCriterion("or "+col+" is not null");
    }
    public void andColEqualTo(String col,Object val){
        addCriterion("and "+col+" = ",val);
    }
    public void orColEqualTo(String col,Object val){
        addCriterion("or "+col+" = ",val);
    }
    public void andColNotEqualTo(String col,Object val){
        addCriterion("and "+col+" <> ",val);
    }
    public void orColNotEqualTo(String col,Object val){
        addCriterion("or "+col+" <> ",val);
    }
    public void andColGreaterThan(String col,Object val){
        addCriterion("and "+col+" > ",val);
    }
    public void orColGreaterThan(String col,Object val){
        addCriterion("or "+col+" > ",val);
    }
    public void andColGreaterThanOrEqualTo(String col,Object val){
        addCriterion("and "+col+" >= ",val);
    }
    public void orColGreaterThanOrEqualTo(String col,Object val){
        addCriterion("or "+col+" >= ",val);
    }
    public void andColLessThan(String col,Object val){
        addCriterion("and "+col+" < ",val);
    }
    public void orColLessThan(String col,Object val){
        addCriterion("or "+col+" < ",val);
    }
    public void andColLessThanOrEqualTo(String col,Object val){
        addCriterion("and "+col+" <= ",val);
    }
    public void orColLessThanOrEqualTo(String col,Object val){
        addCriterion("or "+col+" <= ",val);
    }
    public void andColLike(String col,Object val){
        addCriterion("and "+col+" like ",val);
    }
    public void orColLike(String col,Object val){
        addCriterion("or "+col+" like ",val);
    }
    public void andColNotLike(String col,Object val){
        addCriterion("and "+col+" not like ",val);
    }
    public void orColNotLike(String col,Object val){
        addCriterion("or "+col+" not like ",val);
    }
    public void andColIn(String col,Object val){
        addCriterion("and "+col+" in ",val);
    }
    public void orColIn(String col,Object val){
        addCriterion("or "+col+" in ",val);
    }
    public void andColNotIn(String col,Object val){
        addCriterion("and "+col+" not in ",val);
    }
    public void orColNotIn(String col,Object val){
        addCriterion("or "+col+" not in ",val);
    }
    public void andColBetween(String col,Object val1,Object val2){
        addCriterion("and "+col+" between ",val1,val2);
    }
    public void orColBetween(String col,Object val1,Object val2){
        addCriterion("or "+col+" between ",val1,val2);
    }
    public void andColNotBetween(String col,Object val1,Object val2){
        addCriterion("and "+col+" not between ",val1,val2);
    }
    public void orColNotBetween(String col,Object val1,Object val2){
        addCriterion("or "+col+" not between ",val1,val2);
    }

}
