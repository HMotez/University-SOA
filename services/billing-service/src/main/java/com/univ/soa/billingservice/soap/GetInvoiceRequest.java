package com.univ.soa.billingservice.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(namespace = "http://univ.com/soa/billing/soap")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class GetInvoiceRequest {

    // S'assurer que le nom du champ et l'annotation correspondent à votre XSD
    @XmlElement(name = "id", namespace = "http://univ.com/soa/billing/soap")
    private Long id;
}