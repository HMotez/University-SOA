package com.univ.soa.billing_service.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList; // <-- Ajout de l'import

@XmlRootElement(namespace = "http://univ.com/soa/billing/soap", name = "createInvoiceRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
public class CreateInvoiceRequestXml {
    private Long studentId;

    @XmlElement(name = "items")
    // 🛑 CORRECTION ICI : Initialiser la liste pour éviter le NPE
    private List<SoapItem> items = new ArrayList<>();
}