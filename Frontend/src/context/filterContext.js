import { createContext, useContext, useReducer } from "react";
import { filterReducer } from "../reducer/filterReducer";
import { useProducts } from "./productContext";

const FilterContext = createContext();

export const FilterProvider = ({ children }) => {
  const { productState } = useProducts();
  const initialFilter = {
    category: [],
    brands: [],
    rating: 5,
    sort: "featured",
    search: "",
    includeOutOfStock: false
  };

  const [filterState, filterDispatch] = useReducer(
    filterReducer,
    initialFilter
  );

  let filteredData = productState?.productData;

  if (filterState.includeOutOfStock) {
    filteredData = productState?.productData;
  }

  if (filterState.sort === "high-to-low") {
    filteredData = [...filteredData].sort((a, b) => b.price - a.price);
  } else if (filterState.sort === "low-to-high") {
    filteredData = [...filteredData].sort((a, b) => a.price - b.price);
  } else if (filterState.sort === "featured") {
    filteredData = [...filteredData];
  }

  return (
    <>
      <FilterContext.Provider
        value={{ filterState, filterDispatch, filteredData }}
      >
        {children}
      </FilterContext.Provider>
    </>
  );
};

export const useFilters = () => useContext(FilterContext);
