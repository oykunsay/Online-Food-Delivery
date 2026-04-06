import { createContext, useState, useEffect } from "react";
import axios from "axios";

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
      try {
        const response = await axios.post(
          `${url}/api/cart/add`,
          { foodId },
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setQuantities(response.data.items);
      } catch (error) {
        console.error("Ekleme hatası:", error);
      }
    }
  };

  const decreaseQty = async (foodId) => {
    setQuantities((prev) => ({
      ...prev,
      [foodId]: Math.max((prev[foodId] || 0) - 1, 0),
    }));

    if (token) {
      try {
        const response = await axios.post(
          `${url}/api/cart/remove`,
          { foodId },
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setQuantities(response.data.items);
      } catch (error) {
        console.error("Azaltma hatası:", error);
      }
    }
  };

  const removeFromCart = (foodId) => {
    setQuantities((prevQuantities) => {
      const updatedQuantities = { ...prevQuantities };
      delete updatedQuantities[foodId];
      return updatedQuantities;
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
      const response = await axios.get(`${url}/api/cart`, {
        headers: { Authorization: `Bearer ${tokenValue}` },
      });
      setQuantities(response.data.items || {});
    } catch (error) {
      console.error("Sepet verisi çekilemedi:", error);
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
    removeFromCart,
    token,
    setToken,
    url
  };

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};