import React from "react";

const FoodItem = ({ name, description, id, imageUrl, price }) => {
  return (
    <div className="col-12 col-sm-6 col-md-4 col-lg-3 mb-4 d-flex justify-content-center">
      <div className="card" style={{ maxWidth: "320px" }}>
        <img
          src={imageUrl}
          className="card-img-top"
          alt="Product Image"
          style={{ objectFit: "cover" }}
          height={300}
          width={300}
        />

        <div className="card-body">
          <h5 className="card-title">{name}</h5>
          <p className="card-text">{description}</p>
          <div className="d-flex justify-content-between align-items-center">
            <span className="h5 mb-0">&#x24;{price.toFixed(2)}</span>
            <div>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-half text-warning"></i>
            </div>
          </div>
        </div>
        <div className="card-footer d-flex justify-content-between bg-light">
          <button className="btn btn-primary btn-sm">Add to Cart</button>
          <button className="btn btn-outline-secondary btn-sm">
            <i className="bi bi-heart"></i>
          </button>
        </div>
      </div>
    </div>
  );
};

export default FoodItem;
