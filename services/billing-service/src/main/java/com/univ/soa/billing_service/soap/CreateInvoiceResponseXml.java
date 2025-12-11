package com.univ.soa.billing_service.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(namespace = "http://univ.com/soa/billing/soap", name = "createInvoiceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class CreateInvoiceResponseXml {
    private SoapInvoice invoice;
}