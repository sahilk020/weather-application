import {
  Box,
  Button,
  Container,
  Grid,
  InputAdornment,
  TextField,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
export default function SearchCity() {
    const [city,setCity] = useState('');
  let handleSubmit = () => {
    console.log(city)
  };
  return (
    <Container component="div">
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
        <Grid item xs={12}>
          <TextField
            id="search"
            label="Search"
            variant="standard"
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
            onChange={(e)=>setCity(e.target.value)}
          />
          <Button type="submit"
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
      </Box>
      <Typography variant="h4"></Typography>
    </Container>
  );
}
