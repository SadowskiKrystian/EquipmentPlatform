package com.ksprogramming.equipment.enumes;

import java.util.HashMap;
import java.util.Map;

public enum Authority {
    ADMIN("ADMIN", "ROLE_ADMIN"),
    USER("USER", "ROLE_USER");
    private String code;
    private String codeWithRole;

    Authority(String code, String codeWithRole) {
        this.code = code;
        this.codeWithRole = codeWithRole;
    }

    public String getCode() {
        return code;
    }

    public String getCodeWithRole() {
        return codeWithRole;
    }

    private static final Map<String, Authority> authoritiesWithCodeWithRole = new HashMap<>();

    static {
        for(Authority authority : Authority.values()){
            authoritiesWithCodeWithRole.put(authority.codeWithRole, authority);
        }
    }
    public static Authority findByCodeWithRole(String codeWithRole){
        return authoritiesWithCodeWithRole.get(codeWithRole);
    }
}
