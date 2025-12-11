package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(
        name = "payInvoiceRequest",
        namespace = "http://univ.com/soa/billing/soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInvoiceRequest {

    @XmlElement(name = "id", required = true)
    private Long id;

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }
}
