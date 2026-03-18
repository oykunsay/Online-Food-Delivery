import React, { useContext } from "react";
import { StoreContext } from "../../context/StoreContext";

const FoodDisplay = () => {
  // Context'ten veriyi alıyoruz
  const { foodList } = useContext(StoreContext);

  return (
    <div className="container mt-5">
      <div className="row">
        {foodList && foodList.length > 0 ? (
          foodList.map((food, index) => (
            <div
              key={index}
              className="col-12 col-sm-6 col-md-4 col-lg-3 mb-4 d-flex justify-content-center"
            >
              <div
                className="card"
                style={{ maxWidth: "320px", width: "100%" }}
              >
                <img
                  src={food.image}
                  className="card-img-top"
                  alt={food.name}
                />
                <div className="card-body">
                  <h5 className="card-title">{food.name}</h5>
                  <p
                    className="card-text text-muted"
                    style={{ fontSize: "0.9rem" }}
                  >
                    {food.description}
                  </p>
                  <div className="d-flex justify-content-between align-foods-center">
                    <span className="h5 mb-0">${food.price}</span>
                    <div>
                      <i className="bi bi-star-fill text-warning"></i>
                      <small className="text-muted ms-1">
                        ({food.rating || "4.5"})
                      </small>
                    </div>
                  </div>
                </div>
                <div className="card-footer d-flex justify-content-between bg-light">
                  <button className="btn btn-primary btn-sm">
                    Add to Cart
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    <i className="bi bi-heart"></i>
                  </button>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="col-12 text-center mt-4">
            <h4>No food foods available. Please add some food to display.</h4>
          </div>
        )}
      </div>
    </div>
  );
};

export default FoodDisplay;
