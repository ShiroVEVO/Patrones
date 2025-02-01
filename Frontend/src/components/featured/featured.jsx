import { ProductCard } from "../product-card/productCard";
import "./featured.css";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";

export const Featured = () => {
  const [data, setData] = useState([]);

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
          console.log("data");
          
          console.log(data);
          setData(data);
        } catch (error) {
          console.log(error);
        }
      };
  
      getData();
    }, []);

  return (
    <>
      <h2>Products You May Like ✨</h2>
      <div className="featured">
        {data.map((data) => {
          return (
            <div>
              <ProductCard data={data} />
            </div>
          );
        })}
      </div>
    </>
  );
};
