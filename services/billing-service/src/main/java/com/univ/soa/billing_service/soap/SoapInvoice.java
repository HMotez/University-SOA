package com.univ.soa.billing_service.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class SoapInvoice {
    private Long id;
    private Long studentId;
    private Double amount;
    private String status;
    private String createdAt;
}