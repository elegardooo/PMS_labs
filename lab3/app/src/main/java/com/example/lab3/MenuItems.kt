package com.example.lab3

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double
)

val entreeMenuItems = listOf(
    MenuItem(
        name = "Grilled Chicken",
        description = "Tender grilled chicken breast with a side of garlic mashed potatoes and steamed vegetables.",
        price = 12.99
    ),
    MenuItem(
        name = "Beef Steak",
        description = "Juicy beef steak cooked to perfection, served with a side of roasted potatoes and green beans.",
        price = 18.99
    ),
    MenuItem(
        name = "Vegetable Stir-Fry",
        description = "A mix of fresh vegetables stir-fried with tofu in a savory sauce, served over rice.",
        price = 10.99
    ),
    MenuItem(
        name = "Pasta Primavera",
        description = "Penne pasta with seasonal vegetables in a creamy Alfredo sauce.",
        price = 11.49
    )
)

val sideDishMenuItems = listOf(
    MenuItem(
        name = "Garlic Mashed Potatoes",
        description = "Creamy mashed potatoes infused with roasted garlic.",
        price = 3.99
    ),
    MenuItem(
        name = "Steamed Broccoli",
        description = "Fresh broccoli florets steamed to perfection.",
        price = 2.99
    ),
    MenuItem(
        name = "Roasted Vegetables",
        description = "A mix of seasonal vegetables roasted with herbs and olive oil.",
        price = 4.49
    ),
    MenuItem(
        name = "French Fries",
        description = "Crispy golden fries, lightly salted.",
        price = 2.49
    )
)

val accompanimentMenuItems = listOf(
    MenuItem(
        name = "Garlic Bread",
        description = "Toasted bread topped with garlic and parsley butter.",
        price = 3.49
    ),
    MenuItem(
        name = "Caesar Salad",
        description = "Crisp romaine lettuce with Caesar dressing, croutons, and parmesan cheese.",
        price = 5.99
    ),
    MenuItem(
        name = "Coleslaw",
        description = "A refreshing mix of cabbage and carrots in a tangy dressing.",
        price = 2.99
    )
)