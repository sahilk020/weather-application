import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import weather from "../../axios-create";
import { useSelector } from "react-redux";

export default function Weather() {
  const { state } = useLocation();
  const token = useSelector((state) => state.token);
  const [weatherInfo, setWeatherInfo] = useState({});
  console.log(`lat : ${state.lat}, lon: ${state.lon}`);

  useEffect(() => {
    weather
      .post("/weather/current", state, { headers: { Authorization: token } })
      .then((res) => {
        console.log(res.data);
        setWeatherInfo(res.data)
      })
      .catch((err) => console.log(err));
  },[state,token]);
  return <div>Weather</div>;
}
