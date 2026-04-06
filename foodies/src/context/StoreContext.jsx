import { createContext, useState, useEffect } from "react";
import axios from "axios";
import addToCartApi, { removeFromCart as removeFromCartApi, fetchCartData } from "../service/cartService";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
  const [foodList, setFoodList] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [token, setToken] = useState("");

  const url = "http://localhost:8080";

  const increaseQty = async (foodId) => {
    setQuantities((prev) => ({
      ...prev,
      [foodId]: (prev[foodId] || 0) + 1,
    }));
    if (token) {
      await addToCartApi(foodId, token);
    }
  };

  const decreaseQty = async (foodId) => {
    if (quantities[foodId] > 0) {
      setQuantities((prev) => ({
        ...prev,
        [foodId]: prev[foodId] - 1,
      }));
      
      if (token) {
        await removeFromCartApi(foodId, token);
      }
    }
  };

  const removeCompletelyFromCart = (foodId) => {
    setQuantities((prev) => {
      const updated = { ...prev };
      delete updated[foodId];
      return updated;
    });
  };

  const fetchFoodList = async () => {
    try {
      const response = await axios.get(`${url}/api/foods`);
      setFoodList(response.data);
    } catch (error) {
      console.error("Yemek listesi çekilemedi:", error);
    }
  };

  const loadCartData = async (tokenValue) => {
    try {
      const items = await fetchCartData(tokenValue);
      setQuantities(items || {});
    } catch (error) {
      console.error("Sepet verisi yüklenemedi:", error);
    }
  };

  useEffect(() => {
    async function loadData() {
      await fetchFoodList();
      const storedToken = localStorage.getItem("token");
      if (storedToken) {
        setToken(storedToken);
        await loadCartData(storedToken);
      }
    }
    loadData();
  }, []);

  const contextValue = {
    foodList,
    increaseQty,
    decreaseQty,
    quantities,
    removeFromCart: removeCompletelyFromCart,
    token,
    setToken,
    url,
    setQuantities,
    loadCartData,
  };

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};