import {
  Button,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  Container,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import weather from "../../axios-create";
import { useSelector } from "react-redux";

export default function Wishlist() {
  const [list, setList] = useState([]);
  const token = useSelector((state) => state.token);
  useEffect(() => {
    weather
      .get("/wishlist/get", { headers: { Authorization: token } })
      .then((res) => {
        console.log(res.data);
        setList(res.data);
      })
      .catch((err) => console.error(err));
  }, [token]);
  return (
    //TODO need to add weatherInfo.map() method and respective data
    <Container component="main" sx={{ pt: 5, pb: 5 }}>
      {list &&
        list.map((wishlist) => (
          <Card key={`${wishlist.lat} ${wishlist.lon} ${wishlist.username}`} sx={{ maxWidth: 345 }}>
            <CardActionArea>
              <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                  {`${wishlist.city},${wishlist.country}`}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  (`${wishlist.lat},${wishlist.lon}`)
                </Typography>
              </CardContent>
            </CardActionArea>
            <CardActions>
              <Button size="small" color="primary">
                Share
              </Button>
            </CardActions>
          </Card>
        ))}
    </Container>
  );
}
// @NotBlank
// 	private String city;
// 	@NotBlank
// 	private String country;
// 	@NotBlank
// 	private double lat;
// 	@NotBlank
// 	private double lon;
// 	@NotBlank
// 	private String username;
