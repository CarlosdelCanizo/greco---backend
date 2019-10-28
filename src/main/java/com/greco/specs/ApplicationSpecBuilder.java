package com.greco.specs;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class ApplicationSpecBuilder<T> {

    private final List<SearchCriteria> paramsAnd;

    public ApplicationSpecBuilder() {
        paramsAnd = new ArrayList<>();
    }

    public ApplicationSpecBuilder<T> and(String key, String operation, Object value) {
        paramsAnd.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<T> build(char option) {
        if (paramsAnd.isEmpty()) return null;

        List<Specification<T>> specsAnd = new ArrayList<>();
        for (SearchCriteria param : paramsAnd) {
            specsAnd.add(new ApplicationSpec<>(param));
        }
        Specification<T> result = specsAnd.get(0);

        if(option == ','){
            for (int i = 1; i < specsAnd.size(); i++) {
                result = Specification.where(result).and(specsAnd.get(i));
            }
            return result;
        } else if(option == '_'){
            for (int i = 1; i < specsAnd.size(); i++) {
                result = Specification.where(result).or(specsAnd.get(i));
            }
            return result;
        } else {
            return null;
        }
    }
}
