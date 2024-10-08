package com.ijse.Couse.Work._3.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    List<Long> itemIds;
    List<Double> qty;
}
