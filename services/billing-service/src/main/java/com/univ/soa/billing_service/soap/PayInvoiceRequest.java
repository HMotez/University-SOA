package com.univ.soa.billing_service.soap;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "payInvoiceRequest", namespace = "http://univ.com/soa/billing/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInvoiceRequest {

    @XmlElement(name = "id")
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
