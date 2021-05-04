package com.example.demo.pojo.font.gategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VoCategory implements Serializable {
    private int code;
    private Object category;
    private String msg;
}
