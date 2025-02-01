import { useNavigate } from "react-router";
import "./productCard.css";
import { useCart } from "../../context/cartContext";
import { isItemInCart } from "../../utils/isItemPresentInCart";
import { isItemInWishlist } from "../../utils/isItemPresentInWishlist";
import { toast } from "react-toastify";
import { useWishlist } from "../../context/wishlistContext";
import { useThrottle } from "../../utils/useThrottle";

export const ProductCard = ({ data }) => {
  const {
    idproduct,
    images,
    name,
    description,
    associatedCost,
    stock,
  } = data;
  const image = images && images.length > 0 ? images[0].image_url : null;
  const navigate = useNavigate();
  const { cart, addCartData, isCartUpdate } = useCart();
  const { wishlist, addWishlistData, removeWishlistData, isWishlistUpdate } = useWishlist();
  

  const addToWishlist = () => {
    toast.success("Added to wishlist!");
  }

  const addWishlistHandler = useThrottle(addToWishlist, 400)

  return (
    <>
      <div className="product-card">
        {!stock && <span className="overlay">OUT OF STOCK</span>}
        {isItemInWishlist(wishlist, idproduct) ? (
          <i
            class="fa-solid fa-heart fa-lg add-wishlist"
            style={{ color: "#ff0000" }}
            onClick={() => {
              removeWishlistData(idproduct);
              toast.warning("Item removed from wishlist!");
            }}
          ></i>
        ) : (
          <i
            class="fa-regular fa-heart fa-lg add-wishlist"
            disabled={isWishlistUpdate}
            onClick={addWishlistHandler}
          ></i>
        )}
        <div onClick={() => navigate(`/catalogo/${idproduct}`)}>
          <img src={image} alt={name} />
  
          <p className="product-name">
            {name.length > 50 ? name.substring(0, 50) + "..." : name}
          </p>
          <div className="associatedCost-rating">
            <div className="associatedCost">
              <h3>${associatedCost}</h3>
              
            </div>
          </div>
        </div>

        <button
          disabled={!stock || isCartUpdate}
          onClick={() => {
              if (isItemInCart(cart, idproduct)) {
                navigate("/cart");
              } else {
                addCartData(data);
                toast.success("Added to cart!");
              }
          }}
        >
          <i class="fa-solid fa-cart-shopping"></i> {isItemInCart(cart, idproduct) ? "Go to Cart" : "Add to Cart"}
        </button>
      </div>
    </>
  );
};
