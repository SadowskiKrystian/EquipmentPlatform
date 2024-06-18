package com.ksprogramming.equipment.api;

import java.time.LocalDateTime;

public class EquipmentRemoveRequest {
    private LocalDateTime removeDate;

    public EquipmentRemoveRequest() {
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }
}
