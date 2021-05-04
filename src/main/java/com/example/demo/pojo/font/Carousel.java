package com.example.demo.pojo.font;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Carousel implements Serializable {
    private int carousel_id;
    private String carousel_imgPath;
    private String carousel_title;
}
