package com.ksprogramming.equipment.data;

import com.ksprogramming.equipment.enumes.Authority;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static List<UserAuthorityData> userAuthoritiesArrayToList(String[] authorities) {
        List<UserAuthorityData> list = new ArrayList<>();
        for (String authority : authorities) {
            list.add(new UserAuthorityData(Authority.valueOf(authority).getCodeWithRole()));
        }
        return list;
    }
}
