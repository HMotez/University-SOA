package com.univ.soa.billingservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.univ.soa.billingservice.dto.CreateInvoiceRequest;
import com.univ.soa.billingservice.dto.InvoiceResponse;
import com.univ.soa.billingservice.service.BillingService;
import com.univ.soa.billingservice.soap.*;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class BillingSoapEndpoint {

    private static final String NAMESPACE_URI = "http://univ.com/soa/billing/soap";
    private final BillingService billingService;

    public BillingSoapEndpoint(BillingService billingService) {
        this.billingService = billingService;
    }

    // [EXISTANT] Handle Create Invoice
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createInvoiceRequest")
    @ResponsePayload
    public CreateInvoiceResponseXml createInvoice(@RequestPayload CreateInvoiceRequestXml request) {
        // 1. Convert SOAP input to DTO
        CreateInvoiceRequest dto = new CreateInvoiceRequest();
        dto.setStudentId(request.getStudentId());

        // Correction nécessaire pour les listes: initialiser si null
        if (request.getItems() == null) {
            // Ceci est une vérification de sécurité si la correction dans CreateInvoiceRequestXml a été manquée
            request.setItems(java.util.Collections.emptyList());
        }

        dto.setItems(request.getItems().stream()
                .map(i -> new CreateInvoiceRequest.Item(i.getDescription(), i.getPrice(), i.getQuantity()))
                .collect(Collectors.toList()));

        // 2. Call Business Logic
        InvoiceResponse result = billingService.createInvoice(dto);

        // 3. Convert result back to SOAP
        CreateInvoiceResponseXml response = new CreateInvoiceResponseXml();
        response.setInvoice(mapToSoapInvoice(result));
        return response;
    }

    // [MODIFIÉ] Handle Get Invoice (Ajout de la vérification de l'ID)
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInvoiceRequest")
    @ResponsePayload
    public GetInvoiceResponse getInvoice(@RequestPayload GetInvoiceRequest request) {

        // 🛑 VÉRIFICATION CLÉ POUR ÉVITER LE NPE DE SPRING DATA JPA
        if (request.getId() == null) {
            throw new IllegalArgumentException("Invoice ID must be provided (Field 'id' is null).");
        }

        InvoiceResponse result = billingService.getById(request.getId());

        GetInvoiceResponse response = new GetInvoiceResponse();
        if (result != null) {
            response.setInvoice(mapToSoapInvoice(result));
        }
        return response;
    }

    // [AJOUTÉ] Handle Pay Invoice (Nécessite la classe PayInvoiceRequest)
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "payInvoiceRequest")
    @ResponsePayload
    public PayInvoiceResponse payInvoice(@RequestPayload PayInvoiceRequest request) {

        // 🛑 VÉRIFICATION CLÉ POUR ÉVITER LE NPE DE SPRING DATA JPA
        if (request.getId() == null) {
            throw new IllegalArgumentException("Invoice ID must be provided to pay the invoice (Field 'id' is null).");
        }

        InvoiceResponse result = billingService.payInvoice(request.getId());

        PayInvoiceResponse response = new PayInvoiceResponse();
        response.setInvoice(mapToSoapInvoice(result)); // La méthode payInvoice lève une RuntimeException si non trouvée
        return response;
    }

    // [AJOUTÉ] Handle Get All Invoices (Nécessite la classe GetAllInvoicesRequest)
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllInvoicesRequest")
    @ResponsePayload
    public GetAllInvoicesResponse getAllInvoices(@RequestPayload GetAllInvoicesRequest request) {

        List<InvoiceResponse> results = billingService.getAll();

        GetAllInvoicesResponse response = new GetAllInvoicesResponse();

        // Mappage de la liste de DTOs en liste de SoapInvoice
        List<SoapInvoice> soapInvoices = results.stream()
                .map(this::mapToSoapInvoice)
                .collect(Collectors.toList());

        // Vous devez vous assurer que votre GetAllInvoicesResponse a une méthode setInvoice (ou setInvoices)
        // qui accepte une liste, selon la structure de votre XSD.
        // En supposant que le DTO SOAP a une List<SoapInvoice> appelée 'invoice'.
        response.setInvoice(soapInvoices);
        return response;
    }

    // [EXISTANT] Helper method
    private SoapInvoice mapToSoapInvoice(InvoiceResponse source) {
        SoapInvoice target = new SoapInvoice();
        target.setId(source.getId());
        target.setStudentId(source.getStudentId());
        target.setAmount(source.getAmount());
        target.setStatus(source.getStatus());
        target.setCreatedAt(source.getCreatedAt().toString());
        return target;
    }
}