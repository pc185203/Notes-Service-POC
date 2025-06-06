import {useState} from "react";
import "./LoginPage.css";
import {login} from "./authService";
import {useNavigate} from "react-router-dom";

export const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async(e) => {
    e.preventDefault();
    try {
        await login(username,password);
        navigate("/home");
    }
    catch(e) {
        document.getElementById("error").innerText = e.message;
        setError(true);
        setTimeout(() =>
        {
          setError(false);
          document.getElementById("error").innerText = "no error for now";
        }, 1000);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Username</label>
            <div className="input-wrapper">
              <input
                type="text"
                placeholder="Type your username"
                className="input-field"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
          </div>
          <div className="form-group">
            <label>Password</label>
            <div className="input-wrapper">
              <input
                type="password"
                placeholder="Type your password"
                className="input-field"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>
          <p id="error" className={`error-message ${error?"show":""}`}>no error for now</p>
          <button type="submit" className="submit-btn">Login</button>
        </form>
      </div>
    </div>
  );
};


