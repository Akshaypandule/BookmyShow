package com.BookMyShow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ATMDto {

    @NotNull(message = "enter Valid Card details ")
    @Size(min = 16,max = 16,message = "Card number must be a 16 digit number")
    private Long cardNumber;
    @NotNull(message ="Please add card Holder name")
    private String cardHolderName;
    @NotNull(message ="Please add card Expire")
    private String cardExpire;
    @NotNull(message ="Please add CVV number")
    @Size(min = 3,max = 3,message = "Enter valid cvv")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer cvv;
}
