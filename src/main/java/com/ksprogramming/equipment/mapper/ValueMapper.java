package com.ksprogramming.equipment.mapper;

import com.ksprogramming.equipment.api.ValuePostRequest;
import com.ksprogramming.equipment.api.ValuePutRequest;
import com.ksprogramming.equipment.data.ValueData;

import java.util.ArrayList;
import java.util.List;

public class ValueMapper {
    public static List<ValueData> putRequestToDataList(List<ValuePutRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }

    public static List<ValueData> postRequestToDataList(List<ValuePostRequest> values) {
        List<ValueData> list = new ArrayList<>();
        values.forEach(value -> {
            list.add(new ValueData(value.getId(), value.getValue(), value.getAttributeName()));
        });
        return list;
    }
}
