package com.nttdata.bootcamp.bootcoin.business.impl;

import com.nttdata.bootcamp.bootcoin.business.BootCoinService;
import com.nttdata.bootcamp.bootcoin.model.BootCoin;
import com.nttdata.bootcamp.bootcoin.model.Customer;
import com.nttdata.bootcamp.bootcoin.model.Tarife;
import com.nttdata.bootcamp.bootcoin.repository.BootCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link BootCoinServiceImpl}<br/>
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

@Slf4j
@Service
public class BootCoinServiceImpl implements BootCoinService {

    @Autowired
    private BootCoinRepository bootCoinRepository;
    @Autowired
    private WebClient webClientUser;
    private static final String MONEDA = "SOLES";
    private static final String ESTADO_ACTIVO_BOOTCOIN = "ACTIVO";
    private static final String MODO_PAGO_COMPRA = "COMPRA";
    private static final String MODO_PAGO_VENTA = "VENTA";

    @Override
    public Mono<BootCoin> create(BootCoin bootCoin) {
        try {
            if( !bootCoin.getCustomer().getNumberDocumentIdentity().isBlank()) {
                    if (findCustomerByDniService(bootCoin.getCustomer().getNumberDocumentIdentity()) != null){

                        Random numeroTransaccion = new Random();

                        bootCoin.setNumeroTransaccion(Long.toString(numeroTransaccion.nextLong()));
                        bootCoin.setModoPago(MODO_PAGO_COMPRA);
                        bootCoin.setStatus(ESTADO_ACTIVO_BOOTCOIN);
                        bootCoin.setMonto(50.5);
                        bootCoin.getTarifa().setTarifaCompra(1.5);
                        bootCoin.getTarifa().setMoneda(MONEDA);
                        bootCoin.getTarifa().setStatus(ESTADO_ACTIVO_BOOTCOIN);

                        return bootCoinRepository.insert(bootCoin);
                    }else{
                        log.info("Ingresaremos los datos solicitados por no ser cliente:");
                        Random numeroTransaccion2 = new Random();

                        bootCoin.getCustomer().setNumberDocumentIdentity("72159999");
                        bootCoin.getCustomer().setEmail("joseperez@gmail.com");
                        bootCoin.getCustomer().setTelephone("956896235");
                        bootCoin.setNumeroTransaccion(Long.toString(numeroTransaccion2.nextLong()));
                        bootCoin.setModoPago(MODO_PAGO_COMPRA);
                        bootCoin.setStatus(ESTADO_ACTIVO_BOOTCOIN);
                        bootCoin.setMonto(50.5);
                        bootCoin.getTarifa().setTarifaCompra(1.5);
                        bootCoin.getTarifa().setMoneda(MONEDA);
                        bootCoin.getTarifa().setStatus(ESTADO_ACTIVO_BOOTCOIN);
                    }

                }
        
        }catch(Exception e) {
            System.out.println("Servicio en mantenimiento.");
        }
        return bootCoinRepository.insert(bootCoin);
    }

    @Override
    public Mono<BootCoin> findById(String bootCointId) {
        log.info("--Obteniendo un cliente--");
        return bootCoinRepository.findById(bootCointId).switchIfEmpty(Mono.empty())
                .filter(c -> c.getStatus().equalsIgnoreCase(ESTADO_ACTIVO_BOOTCOIN));
    }

    @Override
    public Flux<BootCoin> findAll() {
        return bootCoinRepository.findAll();
    }

    @Override
    public Mono<BootCoin> update(BootCoin bootCoin, String bootCointId) {
        return bootCoinRepository.save(bootCoin);
    }

    @Override
    public Mono<BootCoin> remove(String bootCointId) {
        return bootCoinRepository
                .findById(bootCointId)
                .flatMap(p -> bootCoinRepository.deleteById(p.getId()).thenReturn(p));
    }


    @Override
    public Mono<Customer> findCustomerByDniService(String dni) {
        log.info("Obteniendo cliente con número documento Identidad:", dni);
        return webClientUser.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/customers/numberDocumentIdentity/" + dni)
                        .build())
                .retrieve()
                .bodyToMono(Customer.class);
    }

}
