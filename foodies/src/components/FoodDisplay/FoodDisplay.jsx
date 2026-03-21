import React, { useContext } from "react";
import { StoreContext } from "../../context/StoreContext";
import FoodItem from "../FoodItem/FoodItem";

const FoodDisplay = () => {
  const { foodList } = useContext(StoreContext);
  console.log("FoodList'in ilk elemanı:", foodList[0]);
  return (
    <div className="container">
      <div className="row">
        {foodList.length > 0 ? (
          foodList.map((food, index) => (
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
