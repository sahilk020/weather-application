import React from "react";
import background from '../../assets/weather.jpg'
const Home = () => {
  const containerStyle = {
    position: "relative",
    minHeight: "100vh",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    padding: "20px",
    color: "#fff",
    textShadow: "1px 1px 3px rgba(0, 0, 0, 0.5)",
    backgroundImage: `url(${background})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
  };

  const overlayStyle = {
    position: "absolute",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    backgroundColor: "rgba(0, 0, 0, 0.5)", // Adjust the opacity and color here
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
  };

  return (
    <div style={containerStyle}>
      <div style={overlayStyle}>
        <h1>Welcome to our Weather Application</h1>
        <p>Thank you for visiting our website.</p>
      </div>
    </div>
  );
};

export default Home;
