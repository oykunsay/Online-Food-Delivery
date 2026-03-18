import { createContext } from "react";
import { useState, useEffect } from "react";
import axios from "axios";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
  const [foodList, setFoodList] = useState([]);

  const fetchFoodList = async () => {
    const response = await axios.get("http://localhost:8080/api/foods");
    setFoodList(response.data);
    console.log(response.data);
  };

  const contextValue = {
    foodList,
    fetchFoodList,
  };

  useEffect(() => {
    async function loadData() {
      const data = await fetchFoodList();
      setFoodList(data);
    }
    loadData();
  }, []);

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};
