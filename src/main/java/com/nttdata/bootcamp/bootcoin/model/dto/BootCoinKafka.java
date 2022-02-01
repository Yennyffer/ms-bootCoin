package com.nttdata.bootcamp.bootcoin.model.dto;

import com.nttdata.bootcamp.bootcoin.model.BootCoin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link BootCoinKafka}<br/>
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
public class BootCoinKafka {
    private String numberDocumentIdentity;
    private String email;
    private String telephone;
}
