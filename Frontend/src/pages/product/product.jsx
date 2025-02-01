import { ProductCard } from "../../components/product-card/productCard";
import { SideBar } from "../../components/sidebar/sidebar";
import ClipLoader from "react-spinners/ClipLoader";
import "./product.css";
import { useEffect, useState } from "react";
import { useFilters } from "../../context/filterContext";
import { useNavigate } from "react-router";
import { useProducts } from "../../context/productContext";
import { toast } from "react-toastify";

export const Product = () => {
  //const { filteredData } = useFilters();
  const [data, setData] = useState([]);
  const [isFilterVisible, setIsFilterVisible] = useState(false);
  const { productState } = useProducts();
  const navigate = useNavigate();

  useEffect(() => {
    const getData = async () => {
      try {
        const response = await fetch('http://localhost:8080/catalogo', {
          method: "GET", 
          headers: {
            "Content-Type": "application/json",
          }
        });
        if (!response.ok) {
          toast.error("Something gone wrong!");
          return;
        }
        const data = await response.json();
        setData(data);
      } catch (error) {
        console.log(error);
      }
    };

    getData();
  }, []);
  
  return (
    <>
      <div className="product">
        <div className="text-filter">
          <p className="text">
            <p onClick={() => navigate("/")}>Home</p>{" "}
            <i class="fa-solid fa-angle-right fa-xs"></i>{" "}
            <span onClick={() => navigate("/catalogo")}>
              Browse Products ({data.length})
            </span>
          </p>
          <button onClick={() => setIsFilterVisible((prev) => !prev)}>
            <i class="fa-solid fa-filter" style={{ color: "#e80071" }}></i>{" "}
            Filters
          </button>
        </div>

        <div className="products">
          <div className="sidebar">
            <SideBar isFilterVisible={isFilterVisible} />
          </div>
          {productState.isProductLoading ? (
            <div className="loader">
              <ClipLoader color={`var(--primary-color)`} size={120} />
            </div>
          ) : (
            <div className="product-list">
              {data.length === 0 ? (
                <h2 style={{ textAlign: "center" }}>No Products Found.</h2>
              ) : (
                data.map((product) => {
                  return (
                    <li key={product._id}>
                      <ProductCard data={product} />
                    </li>
                  );
                })
              )}
            </div>
          )}
        </div>
      </div>
    </>
  );
};
