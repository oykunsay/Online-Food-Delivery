import React, { useContext } from "react";
import { StoreContext } from "../../context/StoreContext";
import FoodItem from "../FoodItem/FoodItem";

const FoodDisplay = ({ category }) => {
  const { foodList } = useContext(StoreContext);
  const filteredFoods =
    category === "All"
      ? foodList
      : foodList.filter((food) => food.category === category);
  return (
    <div className="container">
      <div className="row">
        {filteredFoods.length > 0 ? (
          filteredFoods.map((food, index) => (
            <FoodItem
              key={index}
              name={food.name}
              description={food.description}
              id={food.id}
              imageUrl={food.imageUrl}
              price={food.price}
            />
          ))
        ) : (
          <div className="text-center mt-4">
            <h4>No food foods available. Please add some food to display.</h4>
          </div>
        )}
      </div>
    </div>
  );
};

export default FoodDisplay;
