package com.backEnd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.backEnd.domain.security.Food;
import com.backEnd.domain.security.FoodService;
import com.backEnd.domain.security.UserService;


@Controller
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addFood(Model model) {
		Food food = new Food();
		model.addAttribute("food", food);
		return "addFood";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFoodPost(@ModelAttribute("food") Food food, HttpServletRequest request) {
		foodService.save(food);

		MultipartFile foodImage = food.getFoodImage();

		try {
			byte[] bytes = foodImage.getBytes();
			String name = food.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/food/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:foodList";
	}
	
	@RequestMapping("/foodList")
	public String foodList(Model model) {
		/*List<Food> foodList =foodService.findAll();*/
		
		return "foodList";
		
	}

	

}