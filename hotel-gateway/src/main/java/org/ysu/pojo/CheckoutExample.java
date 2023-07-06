package org.ysu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckoutExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discount_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discount_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(BigDecimal value) {
            addCriterion("discount_price =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(BigDecimal value) {
            addCriterion("discount_price <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(BigDecimal value) {
            addCriterion("discount_price >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_price >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(BigDecimal value) {
            addCriterion("discount_price <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_price <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<BigDecimal> values) {
            addCriterion("discount_price in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<BigDecimal> values) {
            addCriterion("discount_price not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_price between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_price not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(Integer value) {
            addCriterion("days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(Integer value) {
            addCriterion("days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(Integer value) {
            addCriterion("days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(Integer value) {
            addCriterion("days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(Integer value) {
            addCriterion("days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<Integer> values) {
            addCriterion("days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<Integer> values) {
            addCriterion("days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(Integer value1, Integer value2) {
            addCriterion("days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("days not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andHotelCostIsNull() {
            addCriterion("hotel_cost is null");
            return (Criteria) this;
        }

        public Criteria andHotelCostIsNotNull() {
            addCriterion("hotel_cost is not null");
            return (Criteria) this;
        }

        public Criteria andHotelCostEqualTo(BigDecimal value) {
            addCriterion("hotel_cost =", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostNotEqualTo(BigDecimal value) {
            addCriterion("hotel_cost <>", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostGreaterThan(BigDecimal value) {
            addCriterion("hotel_cost >", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hotel_cost >=", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostLessThan(BigDecimal value) {
            addCriterion("hotel_cost <", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hotel_cost <=", value, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostIn(List<BigDecimal> values) {
            addCriterion("hotel_cost in", values, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostNotIn(List<BigDecimal> values) {
            addCriterion("hotel_cost not in", values, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hotel_cost between", value1, value2, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andHotelCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hotel_cost not between", value1, value2, "hotelCost");
            return (Criteria) this;
        }

        public Criteria andShopCostIsNull() {
            addCriterion("shop_cost is null");
            return (Criteria) this;
        }

        public Criteria andShopCostIsNotNull() {
            addCriterion("shop_cost is not null");
            return (Criteria) this;
        }

        public Criteria andShopCostEqualTo(BigDecimal value) {
            addCriterion("shop_cost =", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostNotEqualTo(BigDecimal value) {
            addCriterion("shop_cost <>", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostGreaterThan(BigDecimal value) {
            addCriterion("shop_cost >", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_cost >=", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostLessThan(BigDecimal value) {
            addCriterion("shop_cost <", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_cost <=", value, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostIn(List<BigDecimal> values) {
            addCriterion("shop_cost in", values, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostNotIn(List<BigDecimal> values) {
            addCriterion("shop_cost not in", values, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_cost between", value1, value2, "shopCost");
            return (Criteria) this;
        }

        public Criteria andShopCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_cost not between", value1, value2, "shopCost");
            return (Criteria) this;
        }

        public Criteria andMealCostIsNull() {
            addCriterion("meal_cost is null");
            return (Criteria) this;
        }

        public Criteria andMealCostIsNotNull() {
            addCriterion("meal_cost is not null");
            return (Criteria) this;
        }

        public Criteria andMealCostEqualTo(BigDecimal value) {
            addCriterion("meal_cost =", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostNotEqualTo(BigDecimal value) {
            addCriterion("meal_cost <>", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostGreaterThan(BigDecimal value) {
            addCriterion("meal_cost >", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("meal_cost >=", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostLessThan(BigDecimal value) {
            addCriterion("meal_cost <", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("meal_cost <=", value, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostIn(List<BigDecimal> values) {
            addCriterion("meal_cost in", values, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostNotIn(List<BigDecimal> values) {
            addCriterion("meal_cost not in", values, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("meal_cost between", value1, value2, "mealCost");
            return (Criteria) this;
        }

        public Criteria andMealCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("meal_cost not between", value1, value2, "mealCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostIsNull() {
            addCriterion("phone_cost is null");
            return (Criteria) this;
        }

        public Criteria andPhoneCostIsNotNull() {
            addCriterion("phone_cost is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneCostEqualTo(BigDecimal value) {
            addCriterion("phone_cost =", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostNotEqualTo(BigDecimal value) {
            addCriterion("phone_cost <>", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostGreaterThan(BigDecimal value) {
            addCriterion("phone_cost >", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("phone_cost >=", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostLessThan(BigDecimal value) {
            addCriterion("phone_cost <", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("phone_cost <=", value, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostIn(List<BigDecimal> values) {
            addCriterion("phone_cost in", values, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostNotIn(List<BigDecimal> values) {
            addCriterion("phone_cost not in", values, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("phone_cost between", value1, value2, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andPhoneCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("phone_cost not between", value1, value2, "phoneCost");
            return (Criteria) this;
        }

        public Criteria andRealCostIsNull() {
            addCriterion("real_cost is null");
            return (Criteria) this;
        }

        public Criteria andRealCostIsNotNull() {
            addCriterion("real_cost is not null");
            return (Criteria) this;
        }

        public Criteria andRealCostEqualTo(BigDecimal value) {
            addCriterion("real_cost =", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostNotEqualTo(BigDecimal value) {
            addCriterion("real_cost <>", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostGreaterThan(BigDecimal value) {
            addCriterion("real_cost >", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_cost >=", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostLessThan(BigDecimal value) {
            addCriterion("real_cost <", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_cost <=", value, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostIn(List<BigDecimal> values) {
            addCriterion("real_cost in", values, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostNotIn(List<BigDecimal> values) {
            addCriterion("real_cost not in", values, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_cost between", value1, value2, "realCost");
            return (Criteria) this;
        }

        public Criteria andRealCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_cost not between", value1, value2, "realCost");
            return (Criteria) this;
        }

        public Criteria andReDepositIsNull() {
            addCriterion("re_deposit is null");
            return (Criteria) this;
        }

        public Criteria andReDepositIsNotNull() {
            addCriterion("re_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andReDepositEqualTo(BigDecimal value) {
            addCriterion("re_deposit =", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositNotEqualTo(BigDecimal value) {
            addCriterion("re_deposit <>", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositGreaterThan(BigDecimal value) {
            addCriterion("re_deposit >", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("re_deposit >=", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositLessThan(BigDecimal value) {
            addCriterion("re_deposit <", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("re_deposit <=", value, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositIn(List<BigDecimal> values) {
            addCriterion("re_deposit in", values, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositNotIn(List<BigDecimal> values) {
            addCriterion("re_deposit not in", values, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("re_deposit between", value1, value2, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andReDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("re_deposit not between", value1, value2, "reDeposit");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("payment_type is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(Integer value) {
            addCriterion("payment_type =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(Integer value) {
            addCriterion("payment_type <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(Integer value) {
            addCriterion("payment_type >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment_type >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(Integer value) {
            addCriterion("payment_type <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("payment_type <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<Integer> values) {
            addCriterion("payment_type in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<Integer> values) {
            addCriterion("payment_type not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(Integer value1, Integer value2) {
            addCriterion("payment_type between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("payment_type not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyIsNull() {
            addCriterion("received_money is null");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyIsNotNull() {
            addCriterion("received_money is not null");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyEqualTo(BigDecimal value) {
            addCriterion("received_money =", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyNotEqualTo(BigDecimal value) {
            addCriterion("received_money <>", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyGreaterThan(BigDecimal value) {
            addCriterion("received_money >", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("received_money >=", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyLessThan(BigDecimal value) {
            addCriterion("received_money <", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("received_money <=", value, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyIn(List<BigDecimal> values) {
            addCriterion("received_money in", values, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyNotIn(List<BigDecimal> values) {
            addCriterion("received_money not in", values, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("received_money between", value1, value2, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andReceivedMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("received_money not between", value1, value2, "receivedMoney");
            return (Criteria) this;
        }

        public Criteria andChangexIsNull() {
            addCriterion("changex is null");
            return (Criteria) this;
        }

        public Criteria andChangexIsNotNull() {
            addCriterion("changex is not null");
            return (Criteria) this;
        }

        public Criteria andChangexEqualTo(BigDecimal value) {
            addCriterion("changex =", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexNotEqualTo(BigDecimal value) {
            addCriterion("changex <>", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexGreaterThan(BigDecimal value) {
            addCriterion("changex >", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("changex >=", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexLessThan(BigDecimal value) {
            addCriterion("changex <", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexLessThanOrEqualTo(BigDecimal value) {
            addCriterion("changex <=", value, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexIn(List<BigDecimal> values) {
            addCriterion("changex in", values, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexNotIn(List<BigDecimal> values) {
            addCriterion("changex not in", values, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("changex between", value1, value2, "changex");
            return (Criteria) this;
        }

        public Criteria andChangexNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("changex not between", value1, value2, "changex");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}