package com.workintech.zoo.exceptions;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class ZooErrorResponse {

    private int status;
    private String message;
    private long timestamp;


}
