package com.backEnd.domain.security;

import java.util.List;

public interface FoodService {
	
	Food save(Food food);

	List<Food> findAll();
}
