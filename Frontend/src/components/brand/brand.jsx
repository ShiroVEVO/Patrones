import { useNavigate } from "react-router";
import { useFilters } from "../../context/filterContext";
import "./brand.css";

export const Brand = () => {
  const { filterDispatch } = useFilters();
  const navigate = useNavigate();
  return (
    <>
      <h2>Categories</h2>
      <div className="brand">
        <img
          src={"https://test-bucket-dasp.s3.us-east-2.amazonaws.com/top-view-arrangement-different-seeds.jpg"}
          alt="Semillas"
          onClick={() => {
            navigate("/catalogo");
          }}
        />
        <img
          src={"https://test-bucket-dasp.s3.us-east-2.amazonaws.com/top-view-light-green-succulents-pots-standing-wooden-surface.jpg"}
          alt="Suculentas"
          onClick={() => {
            navigate("/catalogo");
          }}
        />
        <img
          src={"https://test-bucket-dasp.s3.us-east-2.amazonaws.com/natural-tropical-monstera-leaves-daylight.jpg"}
          alt="Follaje"
          onClick={() => {
            navigate("/catalogo");
          }}
        />
        <img
          src={"https://test-bucket-dasp.s3.us-east-2.amazonaws.com/cactus-plant-studio-still-life.jpg"}
          alt="Cactus"
          onClick={() => {
            navigate("/products");
          }}
        />
        <img
          src={"https://test-bucket-dasp.s3.us-east-2.amazonaws.com/bright-background-with-lots-chrysanthemum-flower-texture-concept-abstract-background-with-natural-vegetation-flowers.jpg"}
          alt="Flores"
          onClick={() => {
            navigate("/products");
          }}
        />
      </div>
    </>
  );
};
