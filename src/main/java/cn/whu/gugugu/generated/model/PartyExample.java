package cn.whu.gugugu.generated.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PartyExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andPartyIdIsNull() {
            addCriterion("party_id is null");
            return (Criteria) this;
        }

        public Criteria andPartyIdIsNotNull() {
            addCriterion("party_id is not null");
            return (Criteria) this;
        }

        public Criteria andPartyIdEqualTo(String value) {
            addCriterion("party_id =", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdNotEqualTo(String value) {
            addCriterion("party_id <>", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdGreaterThan(String value) {
            addCriterion("party_id >", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdGreaterThanOrEqualTo(String value) {
            addCriterion("party_id >=", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdLessThan(String value) {
            addCriterion("party_id <", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdLessThanOrEqualTo(String value) {
            addCriterion("party_id <=", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdLike(String value) {
            addCriterion("party_id like", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdNotLike(String value) {
            addCriterion("party_id not like", value, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdIn(List<String> values) {
            addCriterion("party_id in", values, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdNotIn(List<String> values) {
            addCriterion("party_id not in", values, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdBetween(String value1, String value2) {
            addCriterion("party_id between", value1, value2, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartyIdNotBetween(String value1, String value2) {
            addCriterion("party_id not between", value1, value2, "partyId");
            return (Criteria) this;
        }

        public Criteria andPartySubjectIsNull() {
            addCriterion("party_subject is null");
            return (Criteria) this;
        }

        public Criteria andPartySubjectIsNotNull() {
            addCriterion("party_subject is not null");
            return (Criteria) this;
        }

        public Criteria andPartySubjectEqualTo(String value) {
            addCriterion("party_subject =", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectNotEqualTo(String value) {
            addCriterion("party_subject <>", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectGreaterThan(String value) {
            addCriterion("party_subject >", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectGreaterThanOrEqualTo(String value) {
            addCriterion("party_subject >=", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectLessThan(String value) {
            addCriterion("party_subject <", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectLessThanOrEqualTo(String value) {
            addCriterion("party_subject <=", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectLike(String value) {
            addCriterion("party_subject like", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectNotLike(String value) {
            addCriterion("party_subject not like", value, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectIn(List<String> values) {
            addCriterion("party_subject in", values, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectNotIn(List<String> values) {
            addCriterion("party_subject not in", values, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectBetween(String value1, String value2) {
            addCriterion("party_subject between", value1, value2, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartySubjectNotBetween(String value1, String value2) {
            addCriterion("party_subject not between", value1, value2, "partySubject");
            return (Criteria) this;
        }

        public Criteria andPartyDetailIsNull() {
            addCriterion("party_detail is null");
            return (Criteria) this;
        }

        public Criteria andPartyDetailIsNotNull() {
            addCriterion("party_detail is not null");
            return (Criteria) this;
        }

        public Criteria andPartyDetailEqualTo(String value) {
            addCriterion("party_detail =", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailNotEqualTo(String value) {
            addCriterion("party_detail <>", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailGreaterThan(String value) {
            addCriterion("party_detail >", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailGreaterThanOrEqualTo(String value) {
            addCriterion("party_detail >=", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailLessThan(String value) {
            addCriterion("party_detail <", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailLessThanOrEqualTo(String value) {
            addCriterion("party_detail <=", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailLike(String value) {
            addCriterion("party_detail like", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailNotLike(String value) {
            addCriterion("party_detail not like", value, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailIn(List<String> values) {
            addCriterion("party_detail in", values, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailNotIn(List<String> values) {
            addCriterion("party_detail not in", values, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailBetween(String value1, String value2) {
            addCriterion("party_detail between", value1, value2, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDetailNotBetween(String value1, String value2) {
            addCriterion("party_detail not between", value1, value2, "partyDetail");
            return (Criteria) this;
        }

        public Criteria andPartyDateIsNull() {
            addCriterion("party_date is null");
            return (Criteria) this;
        }

        public Criteria andPartyDateIsNotNull() {
            addCriterion("party_date is not null");
            return (Criteria) this;
        }

        public Criteria andPartyDateEqualTo(Date value) {
            addCriterion("party_date =", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateNotEqualTo(Date value) {
            addCriterion("party_date <>", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateGreaterThan(Date value) {
            addCriterion("party_date >", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("party_date >=", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateLessThan(Date value) {
            addCriterion("party_date <", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateLessThanOrEqualTo(Date value) {
            addCriterion("party_date <=", value, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateIn(List<Date> values) {
            addCriterion("party_date in", values, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateNotIn(List<Date> values) {
            addCriterion("party_date not in", values, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateBetween(Date value1, Date value2) {
            addCriterion("party_date between", value1, value2, "partyDate");
            return (Criteria) this;
        }

        public Criteria andPartyDateNotBetween(Date value1, Date value2) {
            addCriterion("party_date not between", value1, value2, "partyDate");
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

        public Criteria andDepositEqualTo(Integer value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Integer value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Integer value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Integer value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Integer value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Integer value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Integer> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Integer> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Integer value1, Integer value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Integer value1, Integer value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Float value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Float value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Float value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Float value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Float value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Float> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Float> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Float value1, Float value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Float value1, Float value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeIsNull() {
            addCriterion("longtitude is null");
            return (Criteria) this;
        }

        public Criteria andLongtitudeIsNotNull() {
            addCriterion("longtitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongtitudeEqualTo(Float value) {
            addCriterion("longtitude =", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeNotEqualTo(Float value) {
            addCriterion("longtitude <>", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeGreaterThan(Float value) {
            addCriterion("longtitude >", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("longtitude >=", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeLessThan(Float value) {
            addCriterion("longtitude <", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeLessThanOrEqualTo(Float value) {
            addCriterion("longtitude <=", value, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeIn(List<Float> values) {
            addCriterion("longtitude in", values, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeNotIn(List<Float> values) {
            addCriterion("longtitude not in", values, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeBetween(Float value1, Float value2) {
            addCriterion("longtitude between", value1, value2, "longtitude");
            return (Criteria) this;
        }

        public Criteria andLongtitudeNotBetween(Float value1, Float value2) {
            addCriterion("longtitude not between", value1, value2, "longtitude");
            return (Criteria) this;
        }

        public Criteria andOriginatorIsNull() {
            addCriterion("originator is null");
            return (Criteria) this;
        }

        public Criteria andOriginatorIsNotNull() {
            addCriterion("originator is not null");
            return (Criteria) this;
        }

        public Criteria andOriginatorEqualTo(String value) {
            addCriterion("originator =", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotEqualTo(String value) {
            addCriterion("originator <>", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorGreaterThan(String value) {
            addCriterion("originator >", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorGreaterThanOrEqualTo(String value) {
            addCriterion("originator >=", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLessThan(String value) {
            addCriterion("originator <", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLessThanOrEqualTo(String value) {
            addCriterion("originator <=", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorLike(String value) {
            addCriterion("originator like", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotLike(String value) {
            addCriterion("originator not like", value, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorIn(List<String> values) {
            addCriterion("originator in", values, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotIn(List<String> values) {
            addCriterion("originator not in", values, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorBetween(String value1, String value2) {
            addCriterion("originator between", value1, value2, "originator");
            return (Criteria) this;
        }

        public Criteria andOriginatorNotBetween(String value1, String value2) {
            addCriterion("originator not between", value1, value2, "originator");
            return (Criteria) this;
        }

        public Criteria andTotalSumIsNull() {
            addCriterion("total_sum is null");
            return (Criteria) this;
        }

        public Criteria andTotalSumIsNotNull() {
            addCriterion("total_sum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSumEqualTo(Integer value) {
            addCriterion("total_sum =", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotEqualTo(Integer value) {
            addCriterion("total_sum <>", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThan(Integer value) {
            addCriterion("total_sum >", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_sum >=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThan(Integer value) {
            addCriterion("total_sum <", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThanOrEqualTo(Integer value) {
            addCriterion("total_sum <=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumIn(List<Integer> values) {
            addCriterion("total_sum in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotIn(List<Integer> values) {
            addCriterion("total_sum not in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumBetween(Integer value1, Integer value2) {
            addCriterion("total_sum between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_sum not between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeIsNull() {
            addCriterion("participate_time is null");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeIsNotNull() {
            addCriterion("participate_time is not null");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeEqualTo(Date value) {
            addCriterion("participate_time =", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeNotEqualTo(Date value) {
            addCriterion("participate_time <>", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeGreaterThan(Date value) {
            addCriterion("participate_time >", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("participate_time >=", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeLessThan(Date value) {
            addCriterion("participate_time <", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeLessThanOrEqualTo(Date value) {
            addCriterion("participate_time <=", value, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeIn(List<Date> values) {
            addCriterion("participate_time in", values, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeNotIn(List<Date> values) {
            addCriterion("participate_time not in", values, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeBetween(Date value1, Date value2) {
            addCriterion("participate_time between", value1, value2, "participateTime");
            return (Criteria) this;
        }

        public Criteria andParticipateTimeNotBetween(Date value1, Date value2) {
            addCriterion("participate_time not between", value1, value2, "participateTime");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(Integer value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(Integer value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(Integer value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(Integer value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(Integer value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<Integer> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<Integer> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(Integer value1, Integer value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(Integer value1, Integer value2) {
            addCriterion("mode not between", value1, value2, "mode");
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