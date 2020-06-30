package org.cannonbank.core.Entities;

import com.nimbusds.oauth2.sdk.GeneralException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    private String country;
    @Digits(integer = 10 /*precision*/, fraction = 10 /*scale*/)
    private float value;

}
