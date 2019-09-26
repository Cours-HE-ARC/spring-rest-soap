package ch.hearc.springsoaprest.soap;

import ch.hearc.springsoaprest.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PaysSoapEndpoint {

    private static final String NAMESPACE_URI = "http://hearc.ch/springsoaprest/soap";


    private PaysRepository paysRepository;

    @Autowired
    public PaysSoapEndpoint(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaysByCodeRequest")
    @ResponsePayload
    public GetPaysByCodeResponse getPays(@RequestPayload GetPaysByCodeRequest request) {

        GetPaysByCodeResponse response = new GetPaysByCodeResponse();
        response.setPays(paysRepository.findPaysByIsoCode(request.getIsoCode()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPaysRequest")
    @ResponsePayload
    public GetAllPaysResponse getPays() {

        GetAllPaysResponse response = new GetAllPaysResponse();

        paysRepository.findAllPays().forEach(pays -> {
            response.getPays().add(pays);
        });


        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaysRequest")
    @ResponsePayload
    public AddPaysResponse addPays(@RequestPayload AddPaysRequest request) {

        AddPaysResponse response = new AddPaysResponse();

        paysRepository.save(request.getPays());

        response.setMessage("[SOAP] Pays inséré: " + request.getPays().getIsoCode());
        return response;
    }

}
