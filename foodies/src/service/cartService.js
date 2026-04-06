import React from "react";

const API_URL = "http://localhost:8080/api/cart";

const addToCart = async (foodId, token) => {
  try {
    const response = await fetch(`${API_URL}/add`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ foodId }),
    });
    return await response.json();
  } catch (error) {
    console.error("Error adding to cart:", error);
    throw error;
  }
};

export default addToCart;

export const removeFromCart = async (foodId, token) => {
  try {
    const response = await fetch(`${API_URL}/remove`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ foodId }),
    });
    return await response.json();
  } catch (error) {
    console.error("Error removing from cart:", error);
    throw error;
  }
};

export const fetchCartData = async (token) => {
  try {
    const response = await fetch(`${API_URL}`, { 
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await response.json();
    return data.items || {}; 
  } catch (error) {
    console.error("Error fetching cart data:", error);
    throw error;
  }
};