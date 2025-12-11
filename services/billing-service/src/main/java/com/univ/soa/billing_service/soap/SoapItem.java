package com.univ.soa.billing_service.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class SoapItem {
    private String description;
    private Double price;
    private Integer quantity;
}