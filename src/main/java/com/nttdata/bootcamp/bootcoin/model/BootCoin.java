package com.nttdata.bootcamp.bootcoin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link BootCoin}<br/>
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "bootcoin")
public class BootCoin {
    @Id
    private String id = UUID.randomUUID().toString();
    private double monto;
    private String modoPago;
    private String numeroTransaccion = UUID.randomUUID().toString();
    private Customer customer;
    private Tarife tarifa;
    private String status;

}
