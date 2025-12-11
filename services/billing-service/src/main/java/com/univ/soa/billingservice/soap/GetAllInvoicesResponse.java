package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "getAllInvoicesResponse", namespace = "http://univ.com/soa/billing/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllInvoicesResponse {

    @XmlElement(name = "invoice")
    private List<SoapInvoice> invoice = new ArrayList<>();

    public List<SoapInvoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<SoapInvoice> invoice) {
        this.invoice = invoice;
    }
}
