package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(namespace = "http://univ.com/soa/billing/soap", name = "getInvoiceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class GetInvoiceResponse {
    private SoapInvoice invoice;
}