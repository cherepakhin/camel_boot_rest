package ru.perm.v.spring.camel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
	
	private Integer id;
	@NotNull(message = "Name may not be null")
	private String name;
	private double price;

}
