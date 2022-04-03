package com.codinglevel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackEntity {

    @NotBlank(message = "name is required")
    private String name;
    @Email()
    @NotBlank(message = "Enter valid email")
    private String email;
    @NotBlank(message = "Message is required")
    @Min(value = 500, message = "Min is 500 words")
    private String message;
}
