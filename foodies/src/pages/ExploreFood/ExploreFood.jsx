import React from "react";
import FoodDisplay from "../../components/FoodDisplay/FoodDisplay";

const ExploreFood = () => {
  const [category, setCategory] = React.useState("All");
  const [searchText, setSearchText] = React.useState("");
  return (
    <div>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <form onSubmit={(e) => e.preventDefault()}>
              <div className="input-group mb-3">
                <select
                  className="form-select mt-2"
                  style={{ maxWidth: "150px" }}
                  onChange={(e) => setCategory(e.target.value)}
                  value={category}
                >
                  <option value="All">All</option>
                  <option value="Pizza">Pizza</option>
                  <option value="Burger">Burger</option>
                  <option value="Salad">Salad</option>
                  <option value="Milkshake">Milkshake</option>
                  <option value="Fries">Fries</option>
                  <option value="Dessert">Dessert</option>
                </select>
                <input
                  type="text"
                  className="form-control mt-2"
                  placeholder="Search your favorite dish..."
                  onChange={(e) => setSearchText(e.target.value)}
                  value={searchText}
                />
                <button className="btn btn-primary mt-2" type="submit">
                  <i className="bi bi-search"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>

      <FoodDisplay category={category} searchText={searchText} />
    </div>
  );
};

export default ExploreFood;
