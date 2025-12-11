package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "payInvoiceResponse", namespace = "http://univ.com/soa/billing/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class PayInvoiceResponse {

    @XmlElement(name = "invoice")
    private SoapInvoice invoice;

    public SoapInvoice getInvoice() { return invoice; }
    public void setInvoice(SoapInvoice invoice) { this.invoice = invoice; }
}
