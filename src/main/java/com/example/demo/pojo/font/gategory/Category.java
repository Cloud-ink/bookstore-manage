package com.example.demo.pojo.font.gategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category implements Serializable {
    private int category_id;
    private String category_name;
    private String category_status;
}
