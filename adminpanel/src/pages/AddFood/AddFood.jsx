import React, { useEffect, useState } from "react";
import { assets } from "../../assets/assets";
import axios from "axios";
import { toast } from "react-toastify";
const AddFood = () => {
  const [image, setImage] = useState(null);
  const [data, setData] = useState({
    name: "",
    description: "",
    price: "",
    category: "Dessert",
  });

  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData((data) => ({ ...data, [name]: value }));
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    if (!image) {
      toast.error("Please select an image");
      return;
    }

    const formData = new FormData();
    const foodBlob = new Blob([JSON.stringify(data)], {
      type: "application/json",
    });

    formData.append("food", foodBlob);
    formData.append("file", image);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/foods",
        formData,
        {},
      );

      if (response.status === 201 || response.status === 200) {
        toast.success("Food added successfully.");
        setData({
          name: "",
          description: "",
          price: "",
          category: "Dessert",
        });
        setImage(null);
      }
    } catch (error) {
      console.error("Error adding food:", error);
      toast.error("Failed to add food.");
    }
  };

  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-4">
          <div className="card-body">
            <h2 className="mb-4">Add Food</h2>
            <form onSubmit={onSubmitHandler}>
              <div className="mb-3">
                <label htmlFor="image" className="form-label">
                  <img
                    src={image ? URL.createObjectURL(image) : assets.upload}
                    alt=""
                    width={98}
                  />
                </label>
                <input
                  type="file"
                  className="form-control"
                  id="image"
                  hidden
                  onChange={(e) => setImage(e.target.files[0])}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="name" className="form-label">
                  Name
                </label>
                <input
                  type="text"
                  placeholder="Dessert"
                  className="form-control"
                  id="name"
                  required
                  name="name"
                  onChange={onChangeHandler}
                  value={data.name}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="description" className="form-label">
                  Description
                </label>
                <textarea
                  className="form-control"
                  placeholder="Write content here..."
                  id="description"
                  rows="5"
                  required
                  name="description"
                  onChange={onChangeHandler}
                  value={data.description}
                ></textarea>
              </div>
              <div className="mb-3">
                <label htmlFor="category" className="form-label">
                  Category
                </label>
                <select
                  className="form-control"
                  id="category"
                  required
                  name="category"
                  onChange={onChangeHandler}
                  value={data.category}
                >
                  <option value="dessert">Dessert</option>
                  <option value="burger">Burger</option>
                  <option value="pizza">Pizza</option>
                  <option value="salad">Salad</option>
                  <option value="fries">Fries</option>
                  <option value="milkshake">Milkshake</option>
                </select>
              </div>
              <div className="mb-3">
                <label htmlFor="price" className="form-label">
                  Price
                </label>
                <input
                  type="number"
                  placeholder="&#x24;200"
                  className="form-control"
                  id="price"
                  required
                  name="price"
                  onChange={onChangeHandler}
                  value={data.price}
                />
              </div>
              <button type="submit" className="btn btn-primary">
                Save
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
export default AddFood;
