import axios from "axios";

const API_URL = "http://localhost:8080/api/foods";

export const getFoodList = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching food list:", error);
    throw error;
  }
};

export const removeFoodById = async (id) => {
  try {
    const response = await axios.delete(`${API_URL}/${id}`);
    return response.status === 204 || response.status === 200;
  } catch (error) {
    console.error("Error removing food:", error);
    throw error;
  }
};
