package com.nttdata.bootcamp.bootcoin.expose;

import com.nttdata.bootcamp.bootcoin.business.BootCoinService;
import com.nttdata.bootcamp.bootcoin.model.BootCoin;
import com.nttdata.bootcamp.bootcoin.model.Customer;
import com.nttdata.bootcamp.bootcoin.model.dto.BootCoinKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link BootCoinController}<br/>
 * <b>Copyright</b>: &Copy; 2022 NTT DATA SAC. <br/>
 * <b>Company</b>: NTT DATA SAC. <br/>
 *
 * @author Yennyffer Lizana <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>{USERNAME}. (acronym) From (YEN)</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Feb. 01, 2022 (acronym) Creation class.</li>
 * </ul>
 * @version 1.0
 */


@RestController
@Slf4j
public class BootCoinController {
    @Autowired
    private BootCoinService bootCoinService;

    @Autowired
    private KafkaTemplate<String, BootCoinKafka> kafkaTemplate;
    private static final String TOPIC = "Kafka";

    @GetMapping("/api/v1/customers/public/{numberDocumentIdentity}")
    public String postKafka (@PathVariable("numberDocumentIdentity") final String numberDocumentIdentity) {
        log.info("Envio de mensajes kafka");
        kafkaTemplate.send(TOPIC, new BootCoinKafka(numberDocumentIdentity,"kafka@gmail.com","951476765"));
        return "Published successfully";
    }

    @GetMapping("api/v1/bootcoins/customers/{id}")
    public Mono<Customer> findByIdCustomerService(@PathVariable("id") String customerId) {
        log.info("Obtengo customer by id:", customerId);
        return bootCoinService.findByIdCustomerService(customerId);
    }

    @GetMapping("/api/v1/bootcoins/{id}")
    public Mono<BootCoin> byId(@PathVariable("id") String id) {
        log.info("byId>>>>>");
        return bootCoinService.findById(id);
    }

    @GetMapping("/api/v1/bootcoins/all")
    public Flux<BootCoin> findAll() {
        log.info("findAll>>>>>");
        return bootCoinService.findAll();
    }

    @PostMapping("/api/v1/bootcoins/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<BootCoin>> create(@RequestBody BootCoin bootCoin) {
        log.info("create>>>>>");
        return bootCoinService.create(bootCoin)
                .flatMap(p -> Mono.just(ResponseEntity.ok().body(p)))
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

    @PutMapping("/api/v1/bootcoins/{id}")
    public Mono<ResponseEntity<BootCoin>> update(@PathVariable String id,@RequestBody BootCoin bootCoin) {
        log.info("update>>>>>");
        return bootCoinService.update(bootCoin, id)
                .flatMap(BootCoinUpdate -> Mono.just(ResponseEntity.ok(BootCoinUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/api/v1/bootcoins/{id}")
    public Mono<ResponseEntity<BootCoin>> delete(@PathVariable("id") String id) {
        log.info("delete>>>>>");
        return bootCoinService.remove(id)
                .flatMap(bootCoin -> Mono.just(ResponseEntity.ok(bootCoin)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
