package com.stream1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mocktest.Food;
import com.mocktest.Review;
import com.mocktest.Type;

public class BusinessOperation {

	List<Food> allFoods;

	public BusinessOperation() {
		allFoods = new ArrayList<>();

		List reviewList1 = Arrays.asList(new Review("A", 4, "Good"), new Review("B", 2, "Not good"),
				new Review("C", 4, "Good"));
		List reviewList2 = Arrays.asList(new Review("A", 5, "Good"), new Review("B", 1, "Not good"),
				new Review("C", 3, "Good"), new Review("D", 4, "very Good"), new Review("E", 5, "nice"));

		List reviewList3 = Arrays.asList(new Review("A", 4, "Good"), new Review("B", 1, "Not good"));

		allFoods.add(new Food("Dal Rice", 150, Type.VEG, "Indian", reviewList1));
		allFoods.add(new Food("Veg Noodles", 200, Type.VEG, "Asian", reviewList2));
		allFoods.add(new Food("Mix Veg", 150, Type.VEG, "Indian", reviewList2));
		allFoods.add(new Food("Veg Soup", 120, Type.VEG, "Europe", reviewList3));
		allFoods.add(new Food("Tandori Bread", 50, Type.VEG, "Middle East", reviewList3));

		allFoods.add(new Food("Chicken Tikka", 320, Type.NON_VEG, "Indian", reviewList2));
		allFoods.add(new Food("Chicken Biryani", 420, Type.NON_VEG, "Indian", reviewList2));
		allFoods.add(new Food("Fish Curry", 480, Type.NON_VEG, "Indian", reviewList1));
		allFoods.add(new Food("Chicken Pasta", 320, Type.NON_VEG, "Italian", reviewList3));
	}

	public int avgCostByCategory(Type category) {

		int avg = (int) allFoods.stream()

				.filter(food -> food.getCategory() == category)

				.mapToInt(Food::getCost)

				.average()

				.orElse(0.0);

		return avg;

	}

	public List<Food> increaseCostOfFoodByRegion(String filterRegion) {

		double increasePercentage = 1.20; // 20% increase

		List<Food> updatedList = allFoods.stream()

				.filter(food -> food.getRegion().equalsIgnoreCase(filterRegion))

				.map(food -> new Food(food.getFoodItemName(), (int) (food.getCost() * increasePercentage), food.getCategory(),
						food.getRegion(), food.getAllReviews()))

				.collect(Collectors.toList());

		return updatedList;

	}

	public Map<String, Integer> getFoodDetails() {
		Map<String, Integer> detailMap = new HashMap<>();

		for (Food food : allFoods) {

			int totalReviews = food.getAllReviews().size();

			detailMap.put(food.getFoodItemName(), totalReviews);

		}
		return detailMap;
	}
}