# Meal Planner and Grocery App
## Consolidating your meal plans and grocery list for your convenience

For my CPSC 210 project I will create a meal planner desktop app. This app will allow users to create a meal plan 
in a calendar type format. The user will be able to view their meal plan in a daily display. There will also be an 
option for the user to generate a grocery list for a given day. This app provides a convenient way for people to 
keep all information about meal planning, recipes, and grocery lists in the same place.

I was inspired to make the app, because me and my roommates all have a lot of difficulty with meal prepping 
and grocery shopping. It is difficult to cook when you don't plan out the dishes you want to make and what groceries 
you need ahead of time. This app will be a way to make our lives a little bit easier and hopeful encourage us to cook
more as well. 

Main Functionality:
- User creates a meal plan in a calendar-type structure and app consolidates the plan as a grocery list
- This provides a way for people to create a meal plan once and not have to worry about also creating a grocery list 

## User Stories

### User Stories for Phase 1
- As a user, I want to add a dish to a certain day and have option to rate it from 1 to 5
- As a user, I want to add any number of ingredients I need to buy to make the dish
- As a user, I would like to have an option to view my meal plan daily
- As a user, I want to enter a day I want to shop for, and be given a grocery list of all items I need

### User Stories for Phase 2
- As a user, I want to be prompted to choose to save meal plan when I quit the application
- As a user, I want option for meal plan to be reloaded from the file every time I run application
- As a user, I want to be able to switch back and forth between months, so that I can meal plan months in advance

### User Stories for Phase 4

#### User Stories Phase 4: Task 2
- Use of Map Interface in MonthGUI.java and MealPlannerAppGUI.java
    - I represented the buttons in the MonthlyGUI display as Hashmap<Integer, JButton> monthButtons
    - monthButtons is created in MonthGUI and passed to MealPlannerAppGUI where action listeners are added
    - There is a button for each of the days of the month, a next month button, and a previous month button
    - The key for each of the day button is the day of the month it represents
    - The key for prev is -100
    - The key for next is 100

- Throwing a checked exception (ui package not in model package)
    - Note: this is not my chosen added Java construct for Task 2 (I just wanted to add documentation for it)
    - Because all the exceptions are thrown in the ui package there are no tests
    - Exceptions:
        - DishNotAcceptedException: 
            - Exception is thrown if user has not entered a valid dish 
            - To be valid, dish needs to have a name and meal type
        - FoodNotAcceptedException: 
            - Exception is thrown if user has not entered a valid food 
            - To be valid dish needs to have a name and meal type
        - EnumNotSelectedException: 
            - Exception is thrown if user has not selected a "type" from ComboBox 
            - Meal type for dish or food type for ingredient
    
