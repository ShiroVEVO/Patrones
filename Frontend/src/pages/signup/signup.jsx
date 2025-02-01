import { useNavigate } from "react-router";
import "./signup.css";
import { useEffect, useState } from "react";
import { useAuth } from "../../context/authContext";
import { toast } from 'react-toastify';
import ClipLoader from 'react-spinners/ClipLoader'

export const Signup = () => {
  const navigate = useNavigate();
  const { userSignup, authState, userCredentials, setUserCredentials } = useAuth();
  const [isPasswordVisible, setIsPasswordVisible] = useState(false);
  const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] =
    useState(false);
  const [doc_number, setDoc_number] = useState();
  const [doc_type, setDoc_type] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phone_number, setPhone_number] = useState();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [birthdate, setBirthdate] = useState("");
  const [roles, setRoles] = useState([]);
  const [role, setRole] = useState({
    id: 0,
    name: "",
  });

  useEffect(() => {
    const fetchRoles = async () => {
      try {
        const response = await fetch('http://localhost:8080/roles', {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          }
        });
        if (!response.ok) {
          throw new Error("Failed to fetch roles");
        }
        const data = await response.json();
        setRoles(data);
      } catch (error) {
        console.error("Error fetching roles:", error);
      }
    };

    fetchRoles();
  }, []);

  const handlePasswordClick = () => setIsPasswordVisible((prev) => !prev);
  const handleConfirmPasswordClick = () =>
    setIsConfirmPasswordVisible((prev) => !prev);

  const handleChange = (e) => {
    const { name, value} = e.target;
    if (name === "role") {
      const selectedRole = roles.find((r) => r.name === value);
      setRole({
        id: selectedRole.id,
        name: selectedRole.name,
      });
    }
  };

  const handleSignUp = async () => {
    if (
      !String(doc_number).trim() ||
      !String(doc_type).trim() ||
      !String(firstName).trim() ||
      !String(lastName).trim() ||
      !String(phone_number).trim() ||
      !String(email).trim() ||
      !String(password).trim() ||
      !String(confirmPassword).trim() ||
      !String(birthdate).trim()
    ) {
      toast.warning("Enter all credentials!")
    } else if (userCredentials.password !== userCredentials.confirmPassword) {
      toast.warning("Passwords don't match!")
    } else {
      try {
        const response = await fetch("http://localhost:8080/personas/guardar", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({"docNumber": doc_number, "docType": doc_type,"name": firstName,"lastname": lastName,"phoneNumber": phone_number}),
        });
    
        if (!response.ok) {
          throw new Error("Failed to post person data");
        }
        const data = await response.json();
        console.log(data);
        
      } catch (error) {
        console.error("Error posting person data:", error);
        throw error;
      }

      try {
        console.log(JSON.stringify({
          "accountDTO": {
            "email": email,
            "birthdate": birthdate,
            "rol": role,
            "personId": {
              "doc_type": doc_type,
              "doc_number": doc_number
            }
          },
          "password": password
      }));
        
        const response = await fetch("http://localhost:8080/cuentas/guardar", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            "accountDTO": {
              "email": email,
              "birthdate": birthdate,
              "rol": role,
              "personId": {
                "doc_type": doc_type,
                "doc_number": doc_number
              }
            },
            "password": password
        }),
        });
    
        const data = await response.json();
        console.log(data);
      } catch (error) {
        console.error("Error posting account data:", error);
      }
      //      userSignup(userCredentials);
    }
  };

  return (
    <>
      <div className="signup">
        <h1>Sign Up</h1>
        <div className="name">
          <div>
            <label for="first-name">First Name: </label>
            <input
              id="first-name"
              placeholder="Pedro"
              value={firstName}
              onChange={(e) =>
                setFirstName(e.target.value)
              }
            />
          </div>
          <div>
            <label for="last-name">Last Name: </label>
            <input
              id="last-name"
              placeholder="Navaja"
              value={lastName}
              onChange={(e) =>
                setLastName(e.target.value)
              }
            />
          </div>
        </div>
        <div>
          <label for="doc_type">Document type: </label>
          <input
            id="doc_type"
            placeholder="CC"
            value={doc_type}
            onChange={(e) =>
              setDoc_type(e.target.value)
            }
          />
        </div>
        <div className="name">
          <div>
            <label for="doc_number">Document number: </label>
            <input
              id="doc_number"
              placeholder="1234567890"
              value={doc_number}
              onChange={(e) =>
                setDoc_number(e.target.value)
              }
            />
          </div>

          <div>
            <label for="phone_number">Phone number:</label>
            <input
              type="number"
              id="phone_number"
              placeholder="3121122345"
              value={phone_number}
              onChange={(e) =>
                setPhone_number(e.target.value)
              }
            />
          </div>
        </div>
        <div>
          <label for="email">Email:</label>
          <input
            type="email"
            id="email"
            placeholder="pedronavaja@example.com"
            value={email}
            onChange={(e) =>
              setEmail(e.target.value)
            }
          />
        </div>
        <div>
          <label for="birthdate">birthdate:</label>
          <input
            type="date"
            id="birthdate"
            value={birthdate}
            onChange={(e) =>
              setBirthdate(e.target.value)
            }
          />
        </div>
        <div>
          <label for="password">Password: </label>
          <div className="password-wrapper">
            <input
              id="password"
              type={isPasswordVisible ? "text" : "password"}
              placeholder={isPasswordVisible ? "password" : "********"}
              value={password}
              onChange={(e) =>setPassword(e.target.value)}
            />
            <button onClick={handlePasswordClick}>
              {isPasswordVisible ? (
                <i class="fa-regular fa-eye-slash"></i>
              ) : (
                <i class="fa-regular fa-eye"></i>
              )}
            </button>
          </div>
        </div>
        <div>
          <label for="confirm-password">Confirm Password: </label>
          <div className="password-wrapper">
            <input
              id="password"
              type={isConfirmPasswordVisible ? "text" : "password"}
              placeholder={isConfirmPasswordVisible ? "password" : "********"}
              value={confirmPassword}
              onChange={(e) =>setConfirmPassword(e.target.value)
              }
            />
            <button onClick={handleConfirmPasswordClick}>
              {isConfirmPasswordVisible ? (
                <i class="fa-regular fa-eye-slash"></i>
              ) : (
                <i class="fa-regular fa-eye"></i>
              )}
            </button>
          </div>
        </div>

        <select
        name="role"
        idrole={role.idrole}
        value={role.name}
        onChange={handleChange}
        className="signup-select"
      >
        <option value="">Select a Rol</option>
        {roles.map((role) => (
          <option key={role.idrole} value={role.name}>
            {role.name}
          </option>
        ))}
      </select>

        <button className="button-signup" onClick={handleSignUp}>
          Signup
        </button>

        <p onClick={() => navigate("/login")}>
          Login to existing account <i class="fa-solid fa-angle-right"></i>
        </p>
      </div>
    </>
  );
};

