import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import weather from "../../axios-create";
import { useSelector } from "react-redux";
import { Container, Grid, Typography } from "@mui/material";
import { WeatherMain } from "./WeatherMain";

export default function Weather() {
  const { state } = useLocation();
  const token = useSelector((state) => state.token);
  const [weatherInfo, setWeatherInfo] = useState({
    city: { name: "", id: 0, country: "", sunrise: 0, sunset: 0, timezone: 0 },
    cnt: 0,
    cod: "",
    message: "",
    list: [],
  });
  console.log(`lat : ${state.lat}, lon: ${state.lon}`);

  function numToDate(num){
    let date = new Date(1000*num);
    return date.toString();

  }

  useEffect(() => {
    weather
      .post("/weather/current", state, { headers: { Authorization: token } })
      .then((res) => {
        console.log(res.data);
        setWeatherInfo(res.data);
      })
      .catch((err) => console.log(err));
  }, [state, token]);
  return (
    <Container component="div" sx={{my:2}}>
      <Grid item xs={12}>
        <Typography component='h1' variant='h4'>{`${weatherInfo.city.name}, ${weatherInfo.city.country}`}</Typography>
        <Typography component='body' variant='body1' sx={{my:1}}><b>Sunrise : </b>{numToDate(weatherInfo.city.sunrise)}</Typography>
        <Typography component='body' variant='body1'><b>Sunset : </b>{numToDate(weatherInfo.city.sunset)}</Typography>
        <Container component='div' sx={{mt:5,mb:5,px:0}}>
        {weatherInfo.list.map((value,index)=><WeatherMain key={index} mainInfo={value}/>)}
        </Container>
      </Grid>
    </Container>
  );
}
