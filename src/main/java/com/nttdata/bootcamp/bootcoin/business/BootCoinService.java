package com.nttdata.bootcamp.bootcoin.business;

import com.nttdata.bootcamp.bootcoin.model.BootCoin;
import com.nttdata.bootcamp.bootcoin.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link BootCoinService}<br/>
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

public interface BootCoinService {

    Mono<BootCoin> create(BootCoin bootCoin);

    Mono<BootCoin> findById(String bootCointId);

    Flux<BootCoin> findAll();

    Mono<BootCoin> update(BootCoin bootCoin, String bootCointId);

    Mono<BootCoin> remove(String bootCointId);
    Mono<Customer> findCustomerByDniService(String dni);
}