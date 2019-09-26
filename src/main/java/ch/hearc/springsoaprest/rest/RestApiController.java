package ch.hearc.springsoaprest.rest;

import ch.hearc.springsoaprest.repository.PaysRepository;
import ch.hearc.springsoaprest.soap.Pays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    private PaysRepository paysRepository;

    public RestApiController(PaysRepository paysRepository){
        this.paysRepository = paysRepository;
    }

    @RequestMapping(value = "/pays/{isoCode}", method = RequestMethod.GET)
    public ResponseEntity<Pays> getPaysByNom(@PathVariable("isoCode") String isoCode){
        return  ResponseEntity.ok(paysRepository.findPaysByIsoCode(isoCode));
    }

    @RequestMapping(value = "/pays", method = RequestMethod.GET)
    public ResponseEntity<List<Pays>> getAllPays(){
        return  ResponseEntity.ok(paysRepository.findAllPays());
    }

    @RequestMapping(value = "/pays", method = RequestMethod.POST)
    public ResponseEntity<PostPaysResponse> savePays(@RequestBody Pays pays){

        paysRepository.save(pays);

        return ResponseEntity.ok(new PostPaysResponse("[REST] Pays inséré: " + pays.getIsoCode()));
    }



}
