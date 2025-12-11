package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(
        name = "createInvoiceRequest",
        namespace = "http://univ.com/soa/billing/soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateInvoiceRequestXml {

    @XmlElement(name = "studentId", required = true)
    private Long studentId;

    @XmlElement(name = "items")
    private List<SoapItem> items = new ArrayList<>();

    // --------- GETTERS & SETTERS ---------

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<SoapItem> getItems() {
        return items;
    }

    public void setItems(List<SoapItem> items) {
        this.items = items;
    }
}
