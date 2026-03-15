import React, { useEffect, useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import "./ListFood.css";
import { getFoodList, removeFoodById } from "../../services/foodService";

const ListFood = () => {
  const [list, setList] = useState([]);
  const fetchList = async () => {
    try {
      const data = await getFoodList();
      setList(data);
    } catch (error) {
      toast.error("Failed to fetch food list");
    }
  };

  const removeFood = async (id) => {
    try {
      const success = await removeFoodById(id);
      if (success) {
        toast.success("Food removed successfully");
        await fetchList();
      } else {
        toast.error("Failed to remove food");
      }
    } catch (error) {
      toast.error("Failed to remove food");
    }
  };
  useEffect(() => {
    fetchList();
  }, []);
  return (
    <div className="py-5 row justify-content-center">
      <div className="col-11 card">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Image</th>
              <th scope="col">Name</th>
              <th scope="col">Category</th>
              <th scope="col">Price</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {list.map((food, index) => (
              <tr key={index}>
                <td>
                  <img
                    src={food.imageUrl}
                    alt={food.name}
                    style={{ width: "48px", height: "48px" }}
                  />
                </td>
                <td>{food.name}</td>
                <td>{food.category}</td>
                <td>&#x24;{food.price}</td>
                <td className="text-danger">
                  <i
                    className="bi bi-x-circle-fill"
                    onClick={() => removeFood(food.id)}
                  ></i>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListFood;
