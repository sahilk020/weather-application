import { Button, Container, Typography } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";

export const City = ({ city }) => {
  const nav = useNavigate();
  function addToWishlist() {
    //axios request to add in wishlist then forward it to /wishlist endpoint
    //if already added show a snackbar or alert that it is already present in wishlist
  }

  function weatherInfo() {
    // make axios call to weather-service and get weather details then navigate to weather service and move
    //data there as well
  }

  return (
    <Container component="div" sx={{ border: "2px solid black", m: 2, p: 1.5 }}>
      <Typography
        sx={{ color: "rgb(233,110,80)" }}
        component="div"
      >{`${city.name}, ${city.state}, ${city.country}`}</Typography>
      <Typography
        sx={{ color: "rgb(233,110,80)" }}
        component="div"
      >{`(${city.lat},${city.lon})`}</Typography>
      <Button variant="outlined" sx={{ mr: 1 }} onClick={addToWishlist}>
        Add to Wishlist
      </Button>
      <Button variant="outlined" onClick={weatherInfo}>
        Show Weather Details
      </Button>
    </Container>
  );
};
// private String name;
// private double lat;
// private double lon;
// private String country;
// private String state;
