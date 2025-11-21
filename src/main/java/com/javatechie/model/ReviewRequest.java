package com.javatechie.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private Long productId;
    private String review;
}
