import { useFilters } from "../../context/filterContext";
import { useProducts } from "../../context/productContext";
import "./sidebar.css";

export const SideBar = ({ isFilterVisible }) => {
  const { productState } = useProducts();
  const { filterDispatch, filterState } = useFilters();
  return (
    <>
      <div
        className="filters"
        style={{ display: isFilterVisible ? "block" : "" }}
      >
        <div className="filter">
          <h2>Filters</h2>
          <button
            onClick={() => {
              filterDispatch({ type: "clear_filters" });
            }}
          >
            Clear Filters
          </button>
        </div>

        <h4>Brands</h4>
        <div className="brand-filter">
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Follaje")}
              onChange={() =>
                filterDispatch({ type: "filter_by_categories", payload: "Follaje" })
              }
            />{" "}
            Follaje
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Cactus")}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_categories",
                  payload: "Cactus",
                })
              }
            />{" "}
            Cactus
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Flores")}
              onChange={() =>
                filterDispatch({ type: "filter_by_categories", payload: "Flores" })
              }
            />{" "}
            Flores
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Suculentas")}
              onChange={() =>
                filterDispatch({ type: "filter_by_categories", payload: "Suculentas" })
              }
            />{" "}
            Suculentas
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Interiores")}
              onChange={() =>
                filterDispatch({ type: "filter_by_categories", payload: "Interiores" })
              }
            />{" "}
            Interiores
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Exteriores")}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_categories",
                  payload: "Exteriores",
                })
              }
            />{" "}
            Exteriores
          </label>
          <label>
            <input
              type="checkbox"
              checked={filterState?.brands.includes("Semillas")}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_categories",
                  payload: "Semillas",
                })
              }
            />{" "}
            Semillas
          </label>
        </div>

        <h4>Sort By Price:</h4>
        <div className="price-filter">
          <label>
            <input
              type="radio"
              name="sort"
              checked={filterState.sort === "featured"}
              onChange={() =>
                filterDispatch({ type: "filter_by_sort", payload: "featured" })
              }
            />{" "}
            Featured
          </label>
          <label>
            <input
              type="radio"
              name="sort"
              checked={filterState.sort === "high-to-low"}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_sort",
                  payload: "high-to-low",
                })
              }
            />{" "}
            High to Low
          </label>
          <label>
            <input
              type="radio"
              name="sort"
              checked={filterState.sort === "low-to-high"}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_sort",
                  payload: "low-to-high",
                })
              }
            />{" "}
            Low to High
          </label>
        </div>

        <h4>Availability</h4>
        <div className="stock-filter">
          <label>
            <input
              type="checkbox"
              checked={filterState.includeOutOfStock}
              onChange={() =>
                filterDispatch({
                  type: "filter_by_availability",
                  payload: !filterState.includeOutOfStock,
                })
              }
            />{" "}
            Include Out of Stock
          </label>
        </div>
      </div>
    </>
  );
};
