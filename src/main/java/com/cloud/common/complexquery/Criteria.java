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
    public void andTableColIsNull(String col){
        addCriterion("and "+col+" is null");
    }
    public void orTableColIsNull(String col){
        addCriterion("or "+col+" is null");
    }
    public void andTableColIsNotNull(String col){
        addCriterion("and "+col+" is not null");
    }
    public void orTableColIsNotNull(String col){
        addCriterion("or "+col+" is not null");
    }
    public void andTableColEqualTo(String col,Object val){
        addCriterion("and "+col+" = ",val);
    }
    public void orTableColEqualTo(String col,Object val){
        addCriterion("or "+col+" = ",val);
    }
    public void andTableColNotEqualTo(String col,Object val){
        addCriterion("and "+col+" <> ",val);
    }
    public void orTableColNotEqualTo(String col,Object val){
        addCriterion("or "+col+" <> ",val);
    }
    public void andTableColGreaterThan(String col,Object val){
        addCriterion("and "+col+" > ",val);
    }
    public void orTableColGreaterThan(String col,Object val){
        addCriterion("or "+col+" > ",val);
    }
    public void andTableColGreaterThanOrEqualTo(String col,Object val){
        addCriterion("and "+col+" >= ",val);
    }
    public void orTableColGreaterThanOrEqualTo(String col,Object val){
        addCriterion("or "+col+" >= ",val);
    }
    public void andTableColLessThan(String col,Object val){
        addCriterion("and "+col+" < ",val);
    }
    public void orTableColLessThan(String col,Object val){
        addCriterion("or "+col+" < ",val);
    }
    public void andTableColLessThanOrEqualTo(String col,Object val){
        addCriterion("and "+col+" <= ",val);
    }
    public void orTableColLessThanOrEqualTo(String col,Object val){
        addCriterion("or "+col+" <= ",val);
    }
    public void andTableColLike(String col,Object val){
        addCriterion("and "+col+" like ",val);
    }
    public void orTableColLike(String col,Object val){
        addCriterion("or "+col+" like ",val);
    }
    public void andTableColNotLike(String col,Object val){
        addCriterion("and "+col+" not like ",val);
    }
    public void orTableColNotLike(String col,Object val){
        addCriterion("or "+col+" not like ",val);
    }
    public void andTableColIn(String col,Object val){
        addCriterion("and "+col+" in ",val);
    }
    public void orTableColIn(String col,Object val){
        addCriterion("or "+col+" in ",val);
    }
    public void andTableColNotIn(String col,Object val){
        addCriterion("and "+col+" not in ",val);
    }
    public void orTableColNotIn(String col,Object val){
        addCriterion("or "+col+" not in ",val);
    }
    public void andTableColBetween(String col,Object val1,Object val2){
        addCriterion("and "+col+" between ",val1,val2);
    }
    public void orTableColBetween(String col,Object val1,Object val2){
        addCriterion("or "+col+" between ",val1,val2);
    }
    public void andTableColNotBetween(String col,Object val1,Object val2){
        addCriterion("and "+col+" not between ",val1,val2);
    }
    public void orTableColNotBetween(String col,Object val1,Object val2){
        addCriterion("or "+col+" not between ",val1,val2);
    }

}
