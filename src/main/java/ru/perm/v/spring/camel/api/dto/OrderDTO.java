package ru.perm.v.spring.camel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
	
	private Integer id;
	@NotNull(message = "Name may not be null")
	@Size(min = 5, message = "The name must be longer than 5 characters")
	private String name;
	@Min(value = 1, message = "Price must be higher than 1")
	private Number price;

}
