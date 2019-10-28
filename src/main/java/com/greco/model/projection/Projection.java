package com.greco.model.projection;

import com.greco.model.SolarPanel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class Projection {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Object convert(Page<?> results, String projection) {
        if (results.getSize() == 0)
            return null;
        return convert(results, getClassByProjection(projection));
    }

    public static IProjectable convertSingle(Object result, String projection) {
            return (IProjectable)modelMapper.map(result, getClassByProjection(projection));
        }

    public static Object convert(Page<?> results) {
        return convert(results, "");
    }

    private static Page<Object> convert(Page<?> results, Class clazz) {
        if (clazz == null)
            return null;

        return results.map(o -> modelMapper.map(o, clazz));
    }

    private static Class getClassByProjection(String projection) {
        if (projection.equals("users"))
            return Users.class;
        if (projection.equals("solarPanel"))
            return SolarPanel.class;
        return null;
    }
}

