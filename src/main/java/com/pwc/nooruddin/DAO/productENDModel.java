package com.pwc.nooruddin.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class productENDModel {
	private String productName;
	private double productPrice;
	private String catalogName;
}
