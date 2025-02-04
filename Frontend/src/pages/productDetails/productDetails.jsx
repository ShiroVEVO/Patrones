import { useNavigate, useParams } from "react-router";
import "./productDetails.css";
import { useEffect, useState } from "react";
import { getProduct } from "../../utils/getProduct";
import { useAuth } from "../../context/authContext";
import { useCart } from "../../context/cartContext";
import { isItemInCart } from "../../utils/isItemPresentInCart";
import { isItemInWishlist } from "../../utils/isItemPresentInWishlist";
import { useWishlist } from "../../context/wishlistContext";
import { toast } from "react-toastify";
import BarLoader from "react-spinners/BarLoader";
import { useProducts } from "../../context/productContext";

export const ProductDetails = () => {
  const [singleProduct, setSingleProduct] = useState({});
  const { productID } = useParams();
  const { token } = useAuth();
  const { cart, addCartData, isCartUpdate } = useCart();
  const { wishlist, addWishlistData, isWishlistUpdate } = useWishlist();
  const { productState, productDispatch } = useProducts();
  const navigate = useNavigate();

  const getSingleProduct = async () => {
    const currentURL = window.location.pathname;
    const URL = 'http://localhost:8080' + currentURL;
    console.log("currentURL", currentURL);
    try {
      const response = await fetch(URL, {
        method: "GET", 
        headers: {
          "Content-Type": "application/json",
        }
      });
      if (!response.ok) {
        toast.error("OH NO!");
        return;
      }
      const data = await response.json();
      console.log("data");
      console.log(data);
      setSingleProduct(data);
    } catch (error) {
      console.log(error);
    }
    
    // try {
    //   productDispatch({ type: "detail_loading", payload: true });
    //   const product = await getProduct(productID);
    //   setSingleProduct(product?.product);
    //   productDispatch({ type: "detail_loading", payload: false });
    // } catch (e) {
    //   console.log(e);
    // }
  };

  useEffect(() => {
    getSingleProduct();
  }, []);

  if (Object.keys(singleProduct).length === 0) {
    return null;
  }

  const {
    _id,
    description,
    images,
    name,
    associatedCost,
    originalPrice,
    stock,
  } = singleProduct;

  const image = images && images.length > 0 ? images[0].image_url : null;

  return (
    <>
      <p className="text details">
        <p onClick={() => navigate("/")}>Home</p>{" "}
        <i class="fa-solid fa-angle-right fa-xs"></i>{" "}
        <p onClick={() => navigate("/catalogo")}>Browse Products</p>
        <i class="fa-solid fa-angle-right fa-xs"></i>
        <span>Product Details</span>
      </p>
      {productState.isDetailLoading ? (
        <BarLoader color={`var(--primary-color)`} size={60} />
      ) : (
        <div className="product-details">
          <img src={image} alt={name} />
          <div className="product-detail">
          <h1>{name}</h1>
            <hr />
            <p className="title-product">{description}</p>
            <div className="product-price">
              <h2>${associatedCost}</h2>
            </div>
            <p className="stock">
              <strong>Availability: </strong>
              {!stock ? "Not in Stock" : "In Stock"}
            </p>
            <div className="wishlist-cart">
              <button
                className="wishlist-btn"
                disabled={isWishlistUpdate}
                onClick={() => {}}
              >
                {isItemInWishlist(wishlist, _id)
                  ? "Go to Wishlist"
                  : "Add to Wishlist"}
              </button>

              <button
                className="cart-btn"
                disabled={stock || isCartUpdate}
                onClick={() => {
                  if (token) {
                    if (isItemInCart(cart, _id)) {
                      navigate("/cart");
                    } else {
                      addCartData(singleProduct);
                      toast.success("Added to cart!");
                    }
                  } else {
                    toast.warning("Please login to proceed");
                    navigate("/login");
                  }
                }}
              >
                <i class="fa-solid fa-cart-shopping"></i> {isItemInCart(cart, _id) ? "Go to Cart" : "Add to Cart"}
              </button>
            </div>
          </div>
        </div>
      )}

      <hr className="mid-hr" />
    </>
  );
};
