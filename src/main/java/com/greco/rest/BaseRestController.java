package com.greco.rest;

import java.util.regex.Matcher;

import com.greco.specs.ApplicationSpecBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.regex.Pattern;

public class BaseRestController<T> {

    protected static final String DEFAULT_PAGE = "0";
    protected static final String DEFAULT_SIZE = "20";
    protected static final String DEFAULT_SORT = "asc";

    private static final String QUERY_REGEX_PATTERN = "([a-zA-Z][a-zA-Z0-9.]*)(::|:|-|\\$|@|&)([\\p{N}\\p{L}\\s-_+@.:\\/\\\\]+),";
    private static final String QUERY_REGEX_PATTERN_MULTIPLE = "([a-zA-Z][a-zA-Z0-9.]*)(::|:|-|\\$|@|&)([\\p{N}\\p{L}\\s-_+@.:\\/\\\\]+)_";

    protected PageRequest pageRequest(int page, int size, String sort, String field) {

        Sort.Direction d = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page, size, d, field);
    }

    protected Specification<T> specs(String search) {

        ApplicationSpecBuilder<T> builder = new ApplicationSpecBuilder<>();

        if(search.indexOf('_') > 0){

            Pattern patternMultiple = Pattern.compile(QUERY_REGEX_PATTERN_MULTIPLE);
            Matcher matcherMultiple = patternMultiple.matcher(search + "_");

            while (matcherMultiple.find()) {
                builder.and(matcherMultiple.group(1), matcherMultiple.group(2), matcherMultiple.group(3));
            }
            return builder.build('_');

        } else {

            Pattern pattern = Pattern.compile(QUERY_REGEX_PATTERN);
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                builder.and(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            return builder.build(',');

        }
    }
}
