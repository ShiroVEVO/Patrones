import { createContext, useContext, useEffect, useReducer } from "react";
import { productReducer } from "../reducer/productReducer";
import axios from "axios";
import { toast } from "react-toastify";

const ProductContext = createContext();

export const ProductProdvider = ({ children }) => {
  const initialState = {
    isProuductLoading: false,
    isCategoryLoading: false,
    isDetailLoading: false,
    productData: [],
    categoriesData: [],
  };
  const [productState, productDispatch] = useReducer(
    productReducer,
    initialState
  );

  const getData = async () => {
  };

  const getCategories = async () => {
    try {
      productDispatch({type: 'categories_loading', payload: true})
      const { status, data } = await axios({
        method: "GET",
        url: "/api/categories",
      });
      if (status === 200) {
        productDispatch({ type: "set_category", payload: data.categories });
        productDispatch({type: 'categories_loading', payload: false})
      }
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getData();
  }, []);

  useEffect(() => {
    getCategories();
  }, []);

  return (
    <ProductContext.Provider value={{ productState, productDispatch }}>
      {children}
    </ProductContext.Provider>
  );
};

export const useProducts = () => useContext(ProductContext);
