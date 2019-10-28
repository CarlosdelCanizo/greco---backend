package com.greco.specs;

import org.springframework.data.jpa.domain.Specification;
import java.util.Date;
import javax.persistence.metamodel.PluralAttribute;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.persistence.criteria.*;

public class ApplicationSpec<T> implements Specification<T> {

    private static final String GREATER_THAN = "@";
    private static final String GREATER_THAN_OR_EQUAL_TO = "&";
    private static final String LESS_THAN = "-";
    private static final String LESS_THAN_OR_EQUAL_TO = "$";
    private static final String EQUAL = "::";
    private static final String LIKE = ":";
    private static final String NOT_EQUAL = "!::";

    private static final String KEY_SEPARATOR = "\\.";

    private SearchCriteria criteria;

    public ApplicationSpec(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        try {
            String value = criteria.getValue().toString().toLowerCase();

            // Plural attribute (ManyToMany & OneToMany) - Singular attribute (ManyToOne)
            boolean isPluralAttribute = isPluralAttribute(root, criteria.getKey().split(KEY_SEPARATOR)[0]);
            query.distinct(isPluralAttribute);

            // Plural & singular attributes expression
            Expression expression;
            if (isPluralAttribute)
                expression = createExpressionPlural(root, criteria.getKey());
            else
                expression = createExpression(root, criteria.getKey());

            // Long types controller (avoid Like and lower)
            if (expression.getJavaType() == String.class)
                expression = builder.lower(expression);
            else if (criteria.getOperation().equals(LIKE))
                    criteria.setOperation(EQUAL);

            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    if (expression.getJavaType() == Date.class)
                        return builder.greaterThan(expression, convertDate(value));
                    return builder.greaterThan(expression, value);
                case GREATER_THAN_OR_EQUAL_TO:
                    if (expression.getJavaType() == Date.class)
                        return builder.greaterThanOrEqualTo(expression, convertDate(value));
                    return builder.greaterThanOrEqualTo(expression, value);
                case LESS_THAN:
                    if (expression.getJavaType() == Date.class)
                        return builder.lessThan(expression, convertDate(value));
                    return builder.lessThan(expression, value);
                case LESS_THAN_OR_EQUAL_TO:
                    if (expression.getJavaType() == Date.class)
                        return builder.lessThanOrEqualTo(expression, convertDate(value));
                    return builder.lessThanOrEqualTo(expression, value);
                case EQUAL:
                    if (expression.getJavaType() == Date.class)
                        return builder.equal(expression, convertDate(value));
                    if (expression.getJavaType() == Boolean.class)
                        return builder.equal(expression,Boolean.valueOf(value));
                    return builder.equal(expression, value);
                case LIKE:
                    return builder.like(expression, "%" + value + "%");
                case NOT_EQUAL:
                    if (expression.getJavaType() == Date.class)
                        return builder.notEqual(expression, convertDate(value));
                    if (expression.getJavaType() == Boolean.class)
                        return builder.notEqual(expression, Boolean.valueOf(value));
                    return builder.notEqual(expression, value);
                default:
                    return null;
                }
        }
        catch (IllegalStateException | IllegalArgumentException e) {
            return null;
        }
    }

    // Convert string date format yyyy-MM-dd into a Date object
    private Date convertDate(String stringDate)
    {
        Date returnDate = null;
        try {
            if(stringDate.charAt(2) != ':'){
                returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
            } else {
                returnDate = new SimpleDateFormat("HH:mm:ss").parse(stringDate);
            }
        } catch (ParseException e) {}
        return returnDate;
    }

    private Expression createExpressionPlural(Root<T> root, String key)
    {
        String[] keys = key.split(KEY_SEPARATOR);
        Join<Object, Object> join = root.join(keys[0]);
        return join.get(keys[1]);
    }

    @SuppressWarnings("rawtypes")
    private Expression createExpression(Root<T> root, String key) {

        String[] keys = key.split(KEY_SEPARATOR);
        Path join = root;
        for (String keyItem : keys)
            join = join.get(keyItem);
        return join;
    }

    private boolean isPluralAttribute(Root<T> root, String key)
    {
        for (PluralAttribute attrib : root.getModel().getDeclaredPluralAttributes())
            if (attrib.getName().equals(key))
                return true;
        return false;
    }
}
