package com.exception;

import com.mocktest.Food;

public class FoodService {

	String region[] = { "Indian", "Middle East", "Europe", "Italian", "Asian" };

	public Food validateFood(Food food) throws InvalidFoodDetailsException {
		if (food.getFoodItemName() == null) {
			throw new InvalidFoodDetailsException("Food name is required");
		}
		if (food.getCost() < 10 || food.getCost() > 1500) {
			throw new InvalidFoodDetailsException("Food cost should be between 10 and 1500");
		}
		if (!containsRegion(food.getRegion())) {
			throw new InvalidFoodDetailsException("Invalid food region");
		}
		if (food.getAllReviews() == null) {
			throw new InvalidFoodDetailsException("Reviews of food should be null");
		}
		return food;
	}

	private boolean containsRegion(String region) {
		for (String r : this.region) {
			if (r.equals(region)) {
				return true;
			}
		}
		return false;
	}
}