import SearchIcon from "@mui/icons-material/Search";
import {
  Box,
  Button,
  Container,
  Grid,
  InputAdornment,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { City } from "./City";
import weather from "../../axios-create";
import { useSelector } from "react-redux";
export default function SearchCity() {
  const [city, setCity] = useState("");
  const [result, setResult] = useState([]);
  const token = useSelector(state=>state.token)
  let handleSubmit = (e) => {
    e.preventDefault();
    console.log(city);
    weather.get(`/weather/search/${city}`,{headers:{'Authorization':token}}).then(res=>setResult(res.data)).catch(err=>console.error(err));
  };
  return (
    <Container component="main">
      <Box
        component="form"
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          pt: 5,
          pb: 5,
        }}
        onSubmit={handleSubmit}
      >
        <Grid item xs={12} sx={{p:5}}>
          <TextField
            id="search"
            label="Search"
            variant="outlined"
            size="small"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
            sx={{
              backgroundColor: "#ffffff",
              borderRadius: 2,
              m: 1,
            }}
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
          <Button
            type="submit"
            sx={{
              backgroundColor: "rgb(200, 200, 200);",
              borderRadius: 1,
              my: 1,
              px: 1,
              py: 1,
            }}
          >
            <SearchIcon />
          </Button>
        </Grid>
        <Grid item xs={12}>
          {result.map((loc) => (
            <City key={loc.lat + loc.lon} city={loc} />
          ))}
        </Grid>
      </Box>
    </Container>
  );
}
